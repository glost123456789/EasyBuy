package cn.zr.service;

import java.util.List;

import cn.zr.entity.EasybuyComment;

public interface EasybuyCommentService {
	//获取某一页的留言
	public List<EasybuyComment> getCommentByPage(int currentpage,int size);
	
	//删除某一条留言
	public int DeleteCommentById(int id);
	
	//增加一条留言 
	public int AddComment(EasybuyComment comment);
	
	//修改一条留言
	
	public int ModifyComment(EasybuyComment comment);
	
	//获取某一条留言
	public EasybuyComment getCommentById(int id);
	
	//获取总数
	public int GetCount();
}
