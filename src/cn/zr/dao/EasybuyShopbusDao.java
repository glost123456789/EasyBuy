package cn.zr.dao;

import java.util.List;

import cn.zr.entity.EasybuyShopbus;

public interface EasybuyShopbusDao extends BaseDao {
	//全部购物车数据
	public List<EasybuyShopbus> getAllShopBus();
	
	//获取某用户的购物车数据
	public List<EasybuyShopbus> getShopBusByUserId(String id);
	
	//增加购物车内容
	public int AddShopbus(EasybuyShopbus shop);
	
	//获取某一条购物车记录
	public EasybuyShopbus getShopById(int id);
	
	//修改某一条购物车记录
	public int Modify(EasybuyShopbus shop);
	
	//删除一条购物记录
	public int RemoveShopById(int id);
	
	//按用户清空购物车
	public int RemoveShopByUserId(String id);
	
}
