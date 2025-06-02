package com.example.demo.JDBC.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;

@Entity
@IdClass(HexaId.class)
public class Hexa {

	@Id
	private String hexaId;

	@Id
	private String langCode;

	private String hexaName;
	private String hexaContent;

	public Hexa() {}

	public Hexa(String hexaId, String langCode, String hexaName, String hexaContent) {
		this.hexaId = hexaId;
		this.langCode = langCode;
		this.hexaName = hexaName;
		this.hexaContent = hexaContent;
	}

	// getter 和 setter
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
