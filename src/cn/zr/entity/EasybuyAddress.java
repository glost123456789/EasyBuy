package cn.zr.entity;

public class EasybuyAddress {
	private int ad_id;
	private String ad_user_id;
	private String ad_address;
	public EasybuyAddress() {
		super();
	}
	public EasybuyAddress(int ad_id, String ad_user_id, String ad_address) {
		super();
		this.ad_id = ad_id;
		this.ad_user_id = ad_user_id;
		this.ad_address = ad_address;
	}
	public int getAd_id() {
		return ad_id;
	}
	public void setAd_id(int ad_id) {
		this.ad_id = ad_id;
	}
	public String getAd_user_id() {
		return ad_user_id;
	}
	public void setAd_user_id(String ad_user_id) {
		this.ad_user_id = ad_user_id;
	}
	public String getAd_address() {
		return ad_address;
	}
	public void setAd_address(String ad_address) {
		this.ad_address = ad_address;
	}
	
}
