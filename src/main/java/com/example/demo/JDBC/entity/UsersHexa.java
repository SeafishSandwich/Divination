package com.example.demo.JDBC.entity;

import jakarta.persistence.*;

@Entity
public class UsersHexa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 正确的主键类型

    @Lob
    private byte[] imageBytes;

    private String question;

    public UsersHexa() {} // 必须有无参构造函数

    public UsersHexa(byte[] imageBytes, String question) {
        this.imageBytes = imageBytes;
        this.question = question;
    }

    public Long getId() { return id; }
    public byte[] getImageBytes() { return imageBytes; }
    public String getQuestion() { return question; }

    public void setImageBytes(byte[] imageBytes) { this.imageBytes = imageBytes; }
    public void setQuestion(String question) { this.question = question; }
}