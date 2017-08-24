package cn.zr.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.zr.dao.EasybuyCommentDao;
import cn.zr.entity.EasybuyComment;

public class EasybuyCommentDaoImpl  extends BaseDaoImpl implements EasybuyCommentDao {
	
	@Override
	public List<EasybuyComment> getCommentByPage(int currentpage, int pagesize) {
		int count=getCount();
		String sql="select * from easybuy_comment limit ?,?";
		int start=((count-currentpage*pagesize)>=0)?(count-currentpage*pagesize):0;
		int size=((count-currentpage*pagesize)>=0)?(pagesize):(count-(currentpage-1)*pagesize);
		ResultSet rSet=super.query(sql, new Object[]{start,size});
		List<EasybuyComment> comments=new ArrayList<EasybuyComment>();
		try {
			while(rSet.next())
			{
				EasybuyComment comment=new EasybuyComment();
				comment.setEc_id(rSet.getInt(1));
				comment.setEc_content(rSet.getString(2));
				comment.setEc_create_time(rSet.getTimestamp(3));
				comment.setEc_reply(rSet.getString(4));
				comment.setEc_reply_time(rSet.getTimestamp(5));
				comment.setEc_nick_name(rSet.getString(6));
				comments.add(0,comment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comments;
	}

	@Override
	public int Addcomment(EasybuyComment comment) {
		String sql="insert into easybuy_comment (ec_content,ec_create_time,ec_reply,ec_reply_time,ec_nick_name) value(?,?,?,?,?)";
		return super.op(sql, new Object[]{comment.getEc_content(),comment.getEc_create_time(),comment.getEc_reply(),comment.getEc_reply_time(),comment.getEc_nick_name()});
	}

	@Override
	public int Deletecomment(int id) {
		String sql="delete from easybuy_comment where ec_id=?";
		return super.op(sql, new Object[]{id});
	}

	@Override
	public int Updatecomment(EasybuyComment comment) {
		String sql="update easybuy_comment set ec_content=?,ec_create_time=?,ec_reply=?,ec_reply_time=?,ec_nick_name=? where ec_id=?";
		return super.op(sql, new Object[]{comment.getEc_content(),comment.getEc_create_time(),comment.getEc_reply(),comment.getEc_reply_time(),comment.getEc_nick_name(),comment.getEc_id()});
	}

	@Override
	public EasybuyComment getCommentById(int id) {
		String sql="select * from easybuy_comment where ec_id=?";
		ResultSet rSet=super.query(sql, new Object[]{id});
		EasybuyComment comment=new EasybuyComment();
		try {
			if(rSet.next())
			{
				comment.setEc_id(rSet.getInt(1));
				comment.setEc_content(rSet.getString(2));
				comment.setEc_create_time(rSet.getTimestamp(3));
				comment.setEc_reply(rSet.getString(4));
				comment.setEc_reply_time(rSet.getTimestamp(5));
				comment.setEc_nick_name(rSet.getString(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return comment;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		int result=-1;
		String sql="select count(*) from easybuy_comment";
		ResultSet rSet=super.query(sql, null);
		try {
			if(rSet.next())
			{
				result=rSet.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
