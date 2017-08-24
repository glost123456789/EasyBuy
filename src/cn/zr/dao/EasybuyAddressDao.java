package cn.zr.dao;

import java.util.List;

import cn.zr.entity.EasybuyAddress;

public interface EasybuyAddressDao extends BaseDao{
	/*
	 * 获取用户获取所有地址
	 */
	public List<EasybuyAddress> getAddressByUserId(String id);
	
	/*
	 * 修改用户地址
	 */
	public int ModifyAddress(EasybuyAddress address);
	
	/*
	 * 增加用户地址
	 */
	public int AddAddress(EasybuyAddress address);
	
	/*
	 * 删除地址
	 */
	public int DeleteAddress(int id);
	
}
