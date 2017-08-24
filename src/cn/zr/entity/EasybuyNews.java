package cn.zr.entity;

import java.sql.Timestamp;

public class EasybuyNews {
	private int en_id;
	private String en_title;
	private String en_content;
	private Timestamp en_create_time;
	
	
	public EasybuyNews() {
		super();
	}
	public EasybuyNews(int en_id, String en_title, String en_content, Timestamp en_create_time) {
		super();
		this.en_id = en_id;
		this.en_title = en_title;
		this.en_content = en_content;
		this.en_create_time = en_create_time;
	}
	public int getEn_id() {
		return en_id;
	}
	public void setEn_id(int en_id) {
		this.en_id = en_id;
	}
	public String getEn_title() {
		return en_title;
	}
	public void setEn_title(String en_title) {
		this.en_title = en_title;
	}
	public String getEn_content() {
		return en_content;
	}
	public void setEn_content(String en_content) {
		this.en_content = en_content;
	}
	public Timestamp getEn_create_time() {
		return en_create_time;
	}
	public void setEn_create_time(Timestamp en_create_time) {
		this.en_create_time = en_create_time;
	}
	
}
