package cn.zr.entity;

import java.sql.Date;

public class EasyBuyOrder {
	private int eo_id;
	private String eo_user_id;
	private String eo_user_name;
	private String eo_user_address;
	private Date eo_create_time;
	private float eo_cost;
	private int eo_status;
	public EasyBuyOrder() {
		super();
	}
	public EasyBuyOrder(int eo_id, String eo_user_id, String eo_user_name, String eo_user_address, Date eo_create_time,
			float eo_cost, int eo_status) {
		super();
		this.eo_id = eo_id;
		this.eo_user_id = eo_user_id;
		this.eo_user_name = eo_user_name;
		this.eo_user_address = eo_user_address;
		this.eo_create_time = eo_create_time;
		this.eo_cost = eo_cost;
		this.eo_status = eo_status;
	}
	public int getEo_id() {
		return eo_id;
	}
	public void setEo_id(int eo_id) {
		this.eo_id = eo_id;
	}
	public String getEo_user_id() {
		return eo_user_id;
	}
	public void setEo_user_id(String eo_user_id) {
		this.eo_user_id = eo_user_id;
	}
	public String getEo_user_name() {
		return eo_user_name;
	}
	public void setEo_user_name(String eo_user_name) {
		this.eo_user_name = eo_user_name;
	}
	public String getEo_user_address() {
		return eo_user_address;
	}
	public void setEo_user_address(String eo_user_address) {
		this.eo_user_address = eo_user_address;
	}
	public Date getEo_create_time() {
		return eo_create_time;
	}
	public void setEo_create_time(Date eo_create_time) {
		this.eo_create_time = eo_create_time;
	}
	public float getEo_cost() {
		return eo_cost;
	}
	public void setEo_cost(float eo_cost) {
		this.eo_cost = eo_cost;
	}
	public int getEo_status() {
		return eo_status;
	}
	public void setEo_status(int eo_status) {
		this.eo_status = eo_status;
	}
	
}
