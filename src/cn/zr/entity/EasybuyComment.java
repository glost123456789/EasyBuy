package cn.zr.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class EasybuyComment {
	private int ec_id;
	private String ec_content;
	private Timestamp  ec_create_time;
	private String ec_reply;
	private Timestamp ec_reply_time;
	private String ec_nick_name;
	public EasybuyComment() {
		super();
	}
	public EasybuyComment(int en_id, String en_content, Timestamp ec_create_time, String ec_reply, Timestamp ec_reply_time,
			String ec_nick_name) {
		super();
		this.ec_id = en_id;
		this.ec_content = en_content;
		this.ec_create_time = ec_create_time;
		this.ec_reply = ec_reply;
		this.ec_reply_time = ec_reply_time;
		this.ec_nick_name = ec_nick_name;
	}
	public int getEc_id() {
		return ec_id;
	}
	public void setEc_id(int en_id) {
		this.ec_id = en_id;
	}
	public String getEc_content() {
		return ec_content;
	}
	public void setEc_content(String en_content) {
		this.ec_content = en_content;
	}
	public Timestamp getEc_create_time() {
		return ec_create_time;
	}
	public void setEc_create_time(Timestamp ec_create_time) {
		this.ec_create_time = ec_create_time;
	}
	public String getEc_reply() {
		return ec_reply;
	}
	public void setEc_reply(String ec_reply) {
		this.ec_reply = ec_reply;
	}
	public Timestamp getEc_reply_time() {
		return ec_reply_time;
	}
	public void setEc_reply_time(Timestamp ec_reply_time) {
		this.ec_reply_time = ec_reply_time;
	}
	public String getEc_nick_name() {
		return ec_nick_name;
	}
	public void setEc_nick_name(String ec_nick_name) {
		this.ec_nick_name = ec_nick_name;
	}
	
	
}
