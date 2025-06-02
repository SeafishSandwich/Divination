package com.example.demo.JDBC.entity;

import java.io.Serializable;
import java.util.Objects;

public class LineId implements Serializable {
    private int lineId;
    private String langCode;

    public LineId() {}

    public LineId(int lineId, String langCode) {
        this.lineId = lineId;
        this.langCode = langCode;
    }

    // equals 和 hashCode 非常关键！
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LineId)) return false;
        LineId that = (LineId) o;
        return lineId == that.lineId && Objects.equals(langCode, that.langCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lineId, langCode);
    }
}

