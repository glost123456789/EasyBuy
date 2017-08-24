package cn.zr.dao;

import java.util.List;

import cn.zr.entity.EasyUser;

public interface EasyBuyUserDao extends BaseDao{
	/*
	 * 增加用户
	 */
	public int Increase(EasyUser easyUser);
	
	/*
	 * 查询当前用户密码
	 */
	public String getUserPwdById(String id);
	
	/*
	 * 获取用户信息
	 */
	public EasyUser getUserById(String id);
	
	/*
	 * 设置用户登录状态
	 */
	public int setUserLoginState(String id,float state);
	
	/*
	 * 根据页面与范围获取用户
	 * currentpage 当前页
	 * pageround 一页的数量
	 */
	public List<EasyUser> getUsersByPage(int currentpage,int pageround);
	
	/*
	 *删除用户
	 */
	public int DeleteUserById(String id);
	
	/*
	 * 修改数据
	 */
	public int ModifyUserByUserId(EasyUser modifyuser);
	
	/*
	 * 获取数据条数
	 */
	public int getCount();
	
	
}
