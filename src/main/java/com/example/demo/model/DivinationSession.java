package com.example.demo.model;


import com.example.demo.JDBC.DAO.HexaDAO;
import com.example.demo.JDBC.entity.Hexa;
import com.example.demo.JDBC.entity.HexagramInfo;
import com.example.demo.JDBC.entity.Lines;
import com.example.demo.JDBC.entity.UsersHexa;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;


@Component
@SessionScope
public class DivinationSession {

    private final HexaDAO hexaDao;
    private String hexa_bi;
    private String changed_bi;
    private List<List<Integer>> yaos;
    private  String hexaName;
    private List<YaoType> yaoTypes;

    public DivinationSession(DataSource dataSource) {
        this.hexaDao = new HexaDAO(dataSource);
    }

    @Transactional
    public void saveQuestion(int userId, String question) throws SQLException {
        hexaDao.saveQuestion(userId, question);
    }

    //扔硬币
    @Transactional(readOnly = true)
    public List<CoinThrowResult> throwCoins(String langCode) throws SQLException {
        List<CoinThrowResult> results = new ArrayList<>();
        List<YaoType> typeList = new ArrayList<>();
        this.yaos = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            List<Integer> faces = new ArrayList<>();// 3 faces
            int backs = 0;

            for (int j = 0; j < 3; j++) {
                int face = Math.random() < 0.5 ? 0 : 1; // 0: 字（正）, 1: 背
                faces.add(face);
                if (face == 1) backs++;
            }
            yaos.add(faces);

            YaoType yaoType;
            boolean isYang;
            boolean isChanging;

            switch (backs) {
                case 0 -> {
                    yaoType = YaoType.LAO_YIN;     // ×
                    isYang = false;
                    isChanging = true;
                }
                case 1 -> {
                    yaoType = YaoType.SHAO_YANG;   // ′
                    isYang = true;
                    isChanging = false;
                }
                case 2 -> {
                    yaoType = YaoType.SHAO_YIN;    // ″
                    isYang = false;
                    isChanging = false;
                }
                case 3 -> {
                    yaoType = YaoType.LAO_YANG;    // ○
                    isYang = true;
                    isChanging = true;
                }
                default -> throw new IllegalStateException("Invalid coin state");
            }
            typeList.add(yaoType);
            results.add(new CoinThrowResult(faces, isYang, isChanging, yaoType));
        }
        yaoTypes = typeList;
        return results; //results is an arrayList with 6 yaos
    }


    //hexa's binary
    @Transactional(readOnly = true)
    public HexaState getHexagramBinary(List<CoinThrowResult> results, boolean isChanging) {
        StringBuilder binary = new StringBuilder();
        for (CoinThrowResult result : results) {
            boolean yang = result.isYang();
            binary.insert(0, yang ? "1" : "0"); // 反转顺序
        }
        hexa_bi = binary.toString();
        return getState();
    }

    @Transactional(readOnly = true)
    public HexaState getChangesHexagramBinary(List<CoinThrowResult> results, boolean isChanging) {
        boolean haschanging = false;
        for (CoinThrowResult result : results) {
            if (result.isChanging()) {
                haschanging = true;
                break;
            }
        }
        if (haschanging){
            StringBuilder binary = new StringBuilder();
            for (CoinThrowResult result : results) {
                boolean yang = result.isYang();
                if (isChanging && result.isChanging()) {
                    yang = !yang; // 变爻反转阴阳
                }
                binary.insert(0, yang ? "1" : "0"); // 反转顺序
            }
            changed_bi = binary.toString();
        }else{
            changed_bi = null;
        }
        return getState();
    }

    @Transactional(readOnly = true)
    public HexaState getHexaname(String hexaId, String langCode) throws SQLException {
        hexaName = hexaDao.getHexaName(hexaId, langCode);
        return  getState();
    }

    //Give data to Hexa State
    public HexaState getState(){
        return new HexaState(hexa_bi, yaos, changed_bi, hexaName, yaoTypes);
    }


    @Transactional
    public Map<String, Object> getHexagramInfo(String hexaId, String langCode) throws SQLException {
        Optional<Hexa> hexagram = hexaDao.getHexagram(hexaId, langCode);
        List<Lines> lines = hexaDao.getLine(hexaId, langCode);

        if (hexagram.isEmpty()) {
            throw new IllegalArgumentException("Couldn't find " + hexaId + " (" + langCode + ")");
        }

        Map<String, Object> result = new HashMap<>();
        result.put("hexagram", hexagram.get());
        result.put("lines", lines);
        result.put("success", true);

        return result;
    }


    //爻详细（爻辞，兆辞，解释）
    @Transactional(readOnly = true)
    public List<String> getHexagramYaoContents(String hexaId, String langCode) throws SQLException {
        List<Lines> lines = hexaDao.getLine(hexaId, langCode);

        List<String> contents = new ArrayList<>();
        for (Lines line : lines) {
            contents.add(line.getLineContent());
        }
        return contents;
    }

    @Transactional
    public Map<String, Object> startThrowCoin(String langCode) throws SQLException, JsonProcessingException {
        List<CoinThrowResult> results = throwCoins(langCode);
        // 生成本卦和变卦
        getHexagramBinary(results, false);
        getChangesHexagramBinary(results, true);
        getHexaname(hexa_bi, langCode);
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("state", getState());
        System.out.println(new ObjectMapper().writeValueAsString(getState()));
        return response;
    }


    @Transactional
    public void saveHexaScreenshot(int userId, MultipartFile imageFile, String question) throws IOException, SQLException {
        if (imageFile != null && !imageFile.isEmpty()) {
            byte[] imageBytes = imageFile.getBytes();
            hexaDao.saveHexa(userId, imageBytes, question);
        }
    }

    @Transactional
    public Optional<UsersHexa> getLatestUserHexa(int userId) throws SQLException {
        return hexaDao.getLatestUserHexa(userId);
    }



}