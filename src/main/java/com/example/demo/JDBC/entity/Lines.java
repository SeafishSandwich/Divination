package com.example.demo.JDBC.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;

@Entity
@IdClass(LineId.class)
public class Lines {

	@Id
	private int lineId;

	@Id
	private String langCode;

	private String lineContent;
	private String omen;
	private String explanation;

	public Lines() {}

	public Lines(int lineId, String langCode, String lineContent, String omen, String explanation) {
		this.lineId = lineId;
		this.langCode = langCode;
		this.lineContent = lineContent;
		this.omen = omen;
		this.explanation = explanation;
	}

	// getters & setters ...
	public int getLineId() { return lineId; }
	public void setLineId(int lineId) { this.lineId = lineId; }

	public String getLangCode() { return langCode; }
	public void setLangCode(String langCode) { this.langCode = langCode; }

	public String getLineContent() { return lineContent; }
	public void setLineContent(String lineContent) { this.lineContent = lineContent; }

	public String getOmen() { return omen; }
	public void setOmen(String omen) { this.omen = omen; }

	public String getExplanation() { return explanation; }
	public void setExplanation(String explanation) { this.explanation = explanation; }

	@Override
	public String toString() {
		return omen + "\n" + explanation;
	}
}