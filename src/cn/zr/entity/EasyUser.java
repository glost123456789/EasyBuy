package cn.zr.entity;

import java.sql.Date;

public class EasyUser {
	private String eu_user_id;
	private	String eu_user_name;
	private String eu_password;
	private String eu_sex;
	private Date eu_birthday;
	private String eu_identity_code;
	private String eu_email;
	private String eu_mobile;
	private double eu_login;
	private int eu_status;
	private String eu_address;
	public EasyUser() {
		super();
	}
	public EasyUser(String en_user_id, String en_user_name, String en_upassword, String eu_sex, Date eu_birthday,
			String eu_identity_code, String eu_email, String eu_mobile, double eu_login, int eu_status,String eu_address) {
		super();
		this.eu_user_id = en_user_id;
		this.eu_user_name = en_user_name;
		this.eu_password = en_upassword;
		this.eu_sex = eu_sex;
		this.eu_birthday = eu_birthday;
		this.eu_identity_code = eu_identity_code;
		this.eu_email = eu_email;
		this.eu_mobile = eu_mobile;
		this.eu_login = eu_login;
		this.eu_status = eu_status;
		this.eu_address=eu_address;
	}
	
	public String getEu_address() {
		return eu_address;
	}
	public void setEu_address(String eu_address) {
		this.eu_address = eu_address;
	}
	public String getEu_user_id() {
		return eu_user_id;
	}
	public void setEu_user_id(String en_user_id) {
		this.eu_user_id = en_user_id;
	}
	public String getEu_user_name() {
		return eu_user_name;
	}
	public void setEu_user_name(String en_user_name) {
		this.eu_user_name = en_user_name;
	}
	public String getEu_password() {
		return eu_password;
	}
	public void setEu_password(String en_upassword) {
		this.eu_password = en_upassword;
	}
	public String getEu_sex() {
		return eu_sex;
	}
	public void setEu_sex(String eu_sex) {
		this.eu_sex = eu_sex;
	}
	public Date getEu_birthday() {
		return eu_birthday;
	}
	public void setEu_birthday(Date eu_birthday) {
		this.eu_birthday = eu_birthday;
	}
	public String getEu_identity_code() {
		return eu_identity_code;
	}
	public void setEu_identity_code(String eu_identity_code) {
		this.eu_identity_code = eu_identity_code;
	}
	public String getEu_email() {
		return eu_email;
	}
	public void setEu_email(String eu_email) {
		this.eu_email = eu_email;
	}
	public String getEu_mobile() {
		return eu_mobile;
	}
	public void setEu_mobile(String eu_mobile) {
		this.eu_mobile = eu_mobile;
	}
	public double getEu_login() {
		return eu_login;
	}
	public void setEu_login(double eu_login) {
		this.eu_login = eu_login;
	}
	public int getEu_status() {
		return eu_status;
	}
	public void setEu_status(int eu_status) {
		this.eu_status = eu_status;
	}
	
	public boolean equals(Object obj) {   
        if (obj instanceof EasyUser) {   //判断obj是不是EasyUser或者其子类的实体类
        	EasyUser u = (EasyUser) obj;   
            return this.eu_user_id.equals(u.getEu_user_id())   
                    && this.eu_user_name.equals(u.getEu_user_name())
                    && this.eu_password.equals(u.getEu_password())
                    && this.eu_sex.equals(u.getEu_sex())
                    && this.eu_birthday.equals(u.getEu_birthday())
                    && this.eu_identity_code.equals(u.getEu_identity_code())
                    && this.eu_email.equals(u.getEu_email())
                    && this.eu_mobile.equals(u.getEu_mobile())
                    && this.eu_login==u.getEu_login()
                    && this.eu_status==u.getEu_status()
                    && this. eu_address.equals(u.getEu_address());
        }   
        return super.equals(obj);  
	}
	
}
