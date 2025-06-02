package com.example.demo.JDBC.entity;

public class Hexa {
    private String hexaId;
    private String langCode;
    private String hexaName;
    private String hexaContent;
    
    public Hexa() {
    	
    }
    
	public Hexa(String hexaId, String langCode, String hexaName, String hexaContent) {
		this.hexaId = hexaId;
		this.langCode = langCode;
		this.hexaName = hexaName;
		this.hexaContent = hexaContent;
	}

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
	public String getHexaName() {
		return hexaName;
	}
	public void setHexaName(String hexaName) {
		this.hexaName = hexaName;
	}
	public String getHexaContent() {
		return hexaContent;
	}
	public void setHexaContent(String hexaContent) {
		this.hexaContent = hexaContent;
	}
}