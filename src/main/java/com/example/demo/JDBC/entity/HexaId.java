package com.example.demo.JDBC.entity;

import java.io.Serializable;
import java.util.Objects;

public class HexaId implements Serializable {

    private String hexaId;
    private String langCode;

    // 必须有无参构造方法
    public HexaId() {}

    public HexaId(String hexaId, String langCode) {
        this.hexaId = hexaId;
        this.langCode = langCode;
    }

    // getter 和 setter（可选但推荐）
    public String getHexaId() {
        return hexaId;
    }

    public void setHexaId(String hexaId) {
        this.hexaId = hexaId;
    }

    public String getLangCode() {
        return langCode;
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }

    // 必须重写 equals 和 hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HexaId)) return false;
        HexaId that = (HexaId) o;
        return Objects.equals(hexaId, that.hexaId) &&
                Objects.equals(langCode, that.langCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hexaId, langCode);
    }
}