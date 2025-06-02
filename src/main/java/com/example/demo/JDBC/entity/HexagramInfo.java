package com.example.demo.JDBC.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.List;
@Entity
public class HexagramInfo {
    @Id
    private final Hexa hexagram;
    private final List<Lines> lines;

    public HexagramInfo(Hexa hexagram, List<Lines> lines) {
        this.hexagram = hexagram;
        this.lines = lines;
    }

    public Hexa getHexagram() {
        return hexagram;
    }

    public List<Lines> getLines() {
        return lines;
    }
}