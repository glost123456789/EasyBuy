package cn.zr.serviceImpl;

import java.util.List;

import javax.xml.stream.events.Comment;

import cn.zr.dao.EasybuyCommentDao;
import cn.zr.daoImpl.EasybuyCommentDaoImpl;
import cn.zr.entity.EasybuyComment;
import cn.zr.service.EasybuyCommentService;

public class EasybuyCommentServiceImpl implements EasybuyCommentService{
	private EasybuyCommentDao Commentdao=new EasybuyCommentDaoImpl();
	@Override
	public List<EasybuyComment> getCommentByPage(int currentpage, int size) {
		// TODO Auto-generated method stu
		return Commentdao.getCommentByPage(currentpage, size);
	}

	@Override
	public int DeleteCommentById(int id) {
		// TODO Auto-generated method stub
		if(id>0)
		{
			return Commentdao.Deletecomment(id);
		}
		return 0;
	}

	@Override
	public int AddComment(EasybuyComment comment) {
		if(comment!=null)
		{
			return Commentdao.Addcomment(comment);
		}
		return 0;
		
	}

	@Override
	public int ModifyComment(EasybuyComment comment) {
		// TODO Auto-generated method stub
		if (comment!=null) {
			return Commentdao.Updatecomment(comment);
		}
		return 0;
		
	}

	@Override
	public EasybuyComment getCommentById(int id) {
		if(id>0)
		{
			return Commentdao.getCommentById(id);
		}
		return null;
	}

	@Override
	public int GetCount() {
		// TODO Auto-generated method stub
		return Commentdao.getCount();
	}

}
