package cn.zr.entity;

public class EasybuyProduct {
	private int ep_id;
	private String ep_name;
	private String ep_description;
	private float ep_price;
	private int ep_stock;
	private int epc_id;
	private int epc_child_id;
	private String ep_file_name;
	private String ep_brand;
	private float ep_origin_price;
	public EasybuyProduct() {
		super();
	}
	public EasybuyProduct(int ep_id, String ep_name, String ep_description, float ep_price, int ep_stock, int epc_id,
			int epc_child_id, String ep_file_name,String ep_brand,float ep_origin_price) {
		super();
		this.ep_id = ep_id;
		this.ep_name = ep_name;
		this.ep_description = ep_description;
		this.ep_price = ep_price;
		this.ep_stock = ep_stock;
		this.epc_id = epc_id;
		this.epc_child_id = epc_child_id;
		this.ep_file_name = ep_file_name;
		this.ep_brand=ep_brand;
		this.ep_origin_price=ep_origin_price;
	}
	
	public float getEp_origin_price() {
		return ep_origin_price;
	}
	public void setEp_origin_price(float ep_origin_price) {
		this.ep_origin_price = ep_origin_price;
	}
	public String getEp_brand() {
		return ep_brand;
	}
	public void setEp_brand(String ep_brand) {
		this.ep_brand = ep_brand;
	}
	public int getEp_id() {
		return ep_id;
	}
	public void setEp_id(int ep_id) {
		this.ep_id = ep_id;
	}
	public String getEp_name() {
		return ep_name;
	}
	public void setEp_name(String ep_name) {
		this.ep_name = ep_name;
	}
	public String getEp_description() {
		return ep_description;
	}
	public void setEp_description(String ep_description) {
		this.ep_description = ep_description;
	}
	public float getEp_price() {
		return ep_price;
	}
	public void setEp_price(float ep_price) {
		this.ep_price = ep_price;
	}
	public int getEp_stock() {
		return ep_stock;
	}
	public void setEp_stock(int ep_stock) {
		this.ep_stock = ep_stock;
	}
	public int getEpc_id() {
		return epc_id;
	}
	public void setEpc_id(int epc_id) {
		this.epc_id = epc_id;
	}
	public int getEpc_child_id() {
		return epc_child_id;
	}
	public void setEpc_child_id(int epc_child_id) {
		this.epc_child_id = epc_child_id;
	}
	public String getEp_file_name() {
		return ep_file_name;
	}
	public void setEp_file_name(String ep_file_name) {
		this.ep_file_name = ep_file_name;
	}
	
}
