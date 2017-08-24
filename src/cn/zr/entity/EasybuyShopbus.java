package cn.zr.entity;

public class EasybuyShopbus {
	private int id;
	private String user_id;
	private int product_id;
	private float product_price;
	private int product_count;
	private String product_file_name;
	public EasybuyShopbus() {
		super();
	}
	public EasybuyShopbus(int id, String user_id, int product_id, float product_price, int product_count,String product_file_name) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.product_id = product_id;
		this.product_price = product_price;
		this.product_count = product_count;
		this.product_file_name=product_file_name;
	}
	
	public String getProduct_file_name() {
		return product_file_name;
	}
	public void setProduct_file_name(String product_file_name) {
		this.product_file_name = product_file_name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public float getProduct_price() {
		return product_price;
	}
	public void setProduct_price(float product_price) {
		this.product_price = product_price;
	}
	public int getProduct_count() {
		return product_count;
	}
	public void setProduct_count(int product_count) {
		this.product_count = product_count;
	}
	
}
