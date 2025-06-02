package com.example.demo.model;

import java.util.List;

public class HexaState {
    private String hexa_bi;
    private List<List<Integer>> yaos;
    private String changed_bi;
    private String hexaName;
    private List<YaoType> yaoTypes;

    public HexaState(String hexa_bi, List<List<Integer>> yaos, String changed_bi, String hexaName, List<YaoType> yaoTypes){
        this.hexa_bi = hexa_bi;
        this.yaos = yaos;
        this.changed_bi = changed_bi;
        this.hexaName = hexaName;
        this.yaoTypes = yaoTypes;
    }

    public String getHexa_bi() { return hexa_bi; }
    public List<List<Integer>> getYaos() { return yaos; }
    public String getChanged_bi() { return changed_bi; }
    public String getHexaName() { return hexaName; }
    public List<YaoType> getYaoTypes() {
        return yaoTypes;
    }

    public void setYaoTypes(List<YaoType> yaoTypes) {
        this.yaoTypes = yaoTypes;
    }

    public HexaState(){}


}
