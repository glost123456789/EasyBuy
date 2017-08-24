package cn.zr.entity;

import java.util.ArrayList;
import java.util.List;

public class EasyOrderAll {
	private EasyBuyOrder myOrder;
	private List<EasybuyOrderdetail> orderdetails=new ArrayList<EasybuyOrderdetail>();
	private List<EasybuyProduct> product=new ArrayList<EasybuyProduct>();
	public EasyBuyOrder getMyOrder() {
		return myOrder;
	}
	public void setMyOrder(EasyBuyOrder myOrder) {
		this.myOrder = myOrder;
	}
	public List<EasybuyOrderdetail> getOrderdetails() {
		return orderdetails;
	}
	public void setOrderdetails(List<EasybuyOrderdetail> orderdetails) {
		this.orderdetails = orderdetails;
	}
	
	public void addDetail(EasybuyOrderdetail orderdetail)
	{
		this.orderdetails.add(orderdetail);
	}
	public void addDetails(List<EasybuyOrderdetail> details)
	{
		this.orderdetails.addAll(details);
	}
	public List<EasybuyProduct> getProduct() {
		return product;
	}
	public void setProduct(List<EasybuyProduct> product) {
		this.product = product;
	}
	public void addProduct(EasybuyProduct product)
	{
		this.product.add(product);
	}
	public void addProducts(List<EasybuyProduct> products)
	{
		this.product.addAll(products);
	}
}
