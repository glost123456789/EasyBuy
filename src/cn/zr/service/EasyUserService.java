package cn.zr.service;

import java.util.List;

import cn.zr.entity.EasyUser;

public interface EasyUserService {
	/*
	 * 增加用户
	 */
	public int IncreaseUser(EasyUser easyUser);
	
	/*
	 * 用户登录验证
	 */
	public EasyUser LoginAndGetUser(String id,String pwd);
	
	/*
	 * 用户注销登录
	 */
	public int Logout(String id);
	
	/*
	 * 按当前页和页数获取Users
	 */
	public List<EasyUser> getUsersByPage(int currentpage,int pageround);
	
	/*
	 * 删除用户
	 */
	public int DeleteUserById(String id);
	
	/*
	 * 获取用户
	 */
	public EasyUser getUserById(String id);
	
	/*
	 * 修改用户信息
	 */
	public int ModifyUserById(EasyUser modifyuser);
	
	/*
	 * 获取总数
	 */
	public int GetCount();
}
