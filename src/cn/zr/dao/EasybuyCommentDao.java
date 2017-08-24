package cn.zr.dao;

import java.util.List;

import cn.zr.entity.EasybuyComment;

public interface EasybuyCommentDao extends BaseDao{
	/*
	 * 获取留言
	 * 按页获取
	 */
	public List<EasybuyComment> getCommentByPage(int currentpage,int pagesize);
	/*
	 * 增加留言
	 */
	public int Addcomment(EasybuyComment comment);
	
	/*
	 *删除留言
	 */
	public int Deletecomment(int id);
	
	/*
	 * 修改留言
	 */
	public int Updatecomment(EasybuyComment comment);
	
	/*
	 *根据ID获取某一项留言 
	 */
	public EasybuyComment getCommentById(int id);
	
	/*
	 * 获取数据条数
	 */
	public int getCount();
	
}
