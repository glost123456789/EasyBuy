package cn.zr.dao;

import java.util.List;

import cn.zr.entity.EasybuyOrderdetail;

public interface EasybuyOrderDetailDao {
	/*
	 * 获取当前订单的明细
	 */
	public List<EasybuyOrderdetail> getDetailsbyOrderId(int id);
	
	/*
	 * 删除订单的明细
	 */
	public int DeleteOrderDetail(int id);
	
	/*
	 *增加订单明细
	 */
	public int AddOrderDetail(EasybuyOrderdetail detail);
	
	/*
	 * 获取所有明细数量
	 */
	public int getCount();
	

}
