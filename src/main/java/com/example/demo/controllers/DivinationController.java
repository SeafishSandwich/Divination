package com.example.demo.controllers;

import com.example.demo.JDBC.entity.UserSession;
import com.example.demo.model.DivinationSession;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/div")

public class DivinationController {
    @Autowired
    private final DivinationSession divinationSession;

    public DivinationController(DivinationSession divinationSession){
        this.divinationSession = divinationSession;
    }

    //submit question
    @PostMapping("/saveQuestion")
    @ResponseBody
    public Map<String, Object> saveQuestion(@RequestBody Map<String, String> payload, HttpSession session) {
        Map<String, Object> result = new HashMap<>();

        UserSession userSession = (UserSession) session.getAttribute("userSession");
        System.out.println("userId = " + userSession.getUserId()); // test

        String question = payload.get("question");
        if (question == null || question.trim().isEmpty()) {
            result.put("success", false);
            result.put("message", "Question cannot be empty");
            return result;
        }

        try {
            // 临时代码，先用假图片代替 imageBytes
            byte[] dummyImage = new byte[]{0, 1, 2, 3};  // TODO: 替换为真实 imageBytes
            divinationSession.saveQuestion(dummyImage, question);

            result.put("success", true);
            result.put("message", "Question saved successfully");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success", false);
            result.put("message", "Failed to save question");
        }
        return result;
    }

    @PostMapping("/start")
    @ResponseBody
    public Map<String, Object> startDivination(@RequestParam String langCode, HttpSession session) {
        UserSession userSession = (UserSession) session.getAttribute("userSession");
        try {
            return divinationSession.startThrowCoin(langCode);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", e.getMessage());
            return error;
        }
    }

    @PostMapping("/getState")
    @ResponseBody
    public Map<String, Object> startThrowCoin(@RequestParam String langCode, HttpSession session) {
        UserSession userSession = (UserSession) session.getAttribute("userSession");
        try {
            return divinationSession.startThrowCoin(langCode);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", e.getMessage());
            return error;
        }
    }


    @PostMapping("/getDetail")
    @ResponseBody
    public Map<String, Object> getHexaDetail(@RequestParam String hexaId, @RequestParam String langCode, HttpSession session) {
        UserSession userSession = (UserSession) session.getAttribute("userSession");
        try {
            return divinationSession.getHexagramInfo(hexaId, langCode);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("message", e.getMessage());
            return error;
        }
    }


}
