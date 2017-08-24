package cn.zr.dao;

import java.sql.Date;
import java.util.List;

import cn.zr.entity.EasyBuyOrder;

public interface EasybuyOrderDao extends BaseDao{
	public List<EasyBuyOrder> getOrderByUserId(String id);
	
	/*
	 * 增加订单
	 */
	public int addOrder(EasyBuyOrder order);
	
	/*
	 * 按页面获取订单
	 */
	public List<EasyBuyOrder> getOrderByPage(int currentpage,int pagesize);
	
	/*
	 * 删除订单
	 */
	public int deleteOrder(int id);
	
	/*
	 * 修改订单
	 */
	public int ModifyOrder(EasyBuyOrder order);
	
	/*
	 * 根据时间查询当前的编号
	 */
	public int GetIdByDate(Date date);
	
	/*
	 * 按订单编号获取订单
	 */
	public EasyBuyOrder getOrderById(int id);
	
	/*
	 * 根据姓名获取订单
	 */
	public List<EasyBuyOrder> getOrdersByUserName(String name);
	/*
	 * 获取全部订单
	 */
	public List<EasyBuyOrder> getAllOrder();
}
