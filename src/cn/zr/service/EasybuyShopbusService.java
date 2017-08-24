package cn.zr.service;

import java.util.List;

import cn.zr.entity.EasybuyShopbus;

public interface EasybuyShopbusService {

	/*
	 * 按用户获取购物车数据
	 */
	public List<EasybuyShopbus> getShopbusByUserId(String id);
	
	/*
	 * 增加购物车内容
	 */
	public int AddShopbus(EasybuyShopbus shop);
	
	//获取某一条购物车记录
	public EasybuyShopbus getShopById(int id);
	
	//修改某一条购物车内容
	public int ModifyShopbus(EasybuyShopbus shop);
	
	//删除一条购物记录
	public int DeleteShopbus(int id);
	
	//按用户清除购物记录
	public int DeleteshopbusByUserId(String id);
	
	//按用户获取购物车数据
	public int getCountByUserId(String id);
}
