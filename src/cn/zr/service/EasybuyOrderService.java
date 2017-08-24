package cn.zr.service;

import java.util.List;

import cn.zr.entity.EasyBuyOrder;
import cn.zr.entity.EasyOrderAll;
import cn.zr.entity.EasybuyOrderdetail;

public interface EasybuyOrderService {
	public List<EasyBuyOrder> getOrdersByUserId(String userId);
	
	public int AddOrder(EasyBuyOrder order,List<EasybuyOrderdetail> detail);
	
	public List<EasybuyOrderdetail> getOrderdetail(int id);
	
	public List<EasyBuyOrder> getOrderbyPage(int currentpage,int size);
	
	//根据订单编号获取id
	public EasyBuyOrder getOrderById(int id);
	
	//根据订货人获取订单
	public List<EasyBuyOrder> getOrdersByUserName(String name);
	
	//获取一页订单与详情
	public List<EasyOrderAll> getOrderDetailByPage(int currentpage,int size);
	
	//订单总数
	public int GetCount();
	
	//按用户获取订单详情
	public List<EasyOrderAll> getDetailsByUserName(String name);
	
	//按订单号获取详情
	public List<EasyOrderAll> getDetailsByOrder(int id);
	
	//修改订单
	public int ModifyOrder(EasyBuyOrder order);
	
}
