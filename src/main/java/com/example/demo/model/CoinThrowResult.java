package com.example.demo.model;

import java.util.List;

public class CoinThrowResult {
    private List<Integer> faces;
    private boolean isYang;
    private boolean isChanging;
    private YaoType yaoType;
    

    public CoinThrowResult(List<Integer> faces, boolean isYang, boolean isChanging, YaoType yaoType) {
        this.faces = faces;
        this.isYang = isYang;
        this.isChanging = isChanging;
        this.yaoType = yaoType;
    }

    // getters
    public List<Integer> getFaces() {
        return faces;
    }

    public boolean isYang() {
        return isYang;
    }

    public boolean isChanging() {
        return isChanging;
    }

    public YaoType getYaoType() {
        return yaoType;
    }

    @Override
    public String toString() {
        return "Coins: " + faces +
                " → YaoType: " + yaoType +
                " → isYang: " + isYang +
                " → isChanging: " + isChanging;
    }
}