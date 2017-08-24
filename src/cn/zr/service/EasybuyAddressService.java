package cn.zr.service;

import java.util.List;

import cn.zr.entity.EasybuyAddress;

public interface EasybuyAddressService {
	//增加地址
	public int Addaddress(EasybuyAddress address);
	
	//按用户获取地址
	public List<EasybuyAddress> getAddressbyUser(String id);
	
	//删除地址
	public int DeleteAddress(int id);
}
