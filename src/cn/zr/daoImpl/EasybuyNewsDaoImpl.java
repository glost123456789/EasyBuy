package cn.zr.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.NEW;

import cn.zr.dao.EasybuyNewsDao;
import cn.zr.entity.*;

public class EasybuyNewsDaoImpl extends BaseDaoImpl implements EasybuyNewsDao {

	@Override
	public int DeleteNews(int id) {
		// TODO Auto-generated method stub
		String sql="delete from easybuy_news where en_id=?";
		return super.op(sql, new Object[]{id});
	}

	@Override
	public List<EasybuyNews> getNewsByPage(int currentpage, int pagesize) {
		String sql="select * from easybuy_news limit ?,?";
		int count=GetCount();
		int start=((count-currentpage*pagesize)>=0)?(count-currentpage*pagesize):0;
		int size=((count-currentpage*pagesize)>=0)?(pagesize):(count-(currentpage-1)*pagesize);
		ResultSet rSet=super.query(sql, new Object[]{start,size});
		List<EasybuyNews> newses=new ArrayList<EasybuyNews>();
		try {
			while(rSet.next())
			{
				EasybuyNews news=new EasybuyNews();
				news.setEn_id(rSet.getInt(1));
				news.setEn_title(rSet.getString(2));
				news.setEn_content(rSet.getString(3));
				news.setEn_create_time(rSet.getTimestamp(4));
				newses.add(0,news);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newses;
	}

	@Override
	public int AddNews(EasybuyNews news) {
		// TODO Auto-generated method stub
		String sql="insert into easybuy_news (en_title,en_content) value(?,?)";
		return super.op(sql, new Object[]{news.getEn_title(),news.getEn_content()});
	}

	@Override
	public int ModifyNews(EasybuyNews news) {
		String sql="update easybuy_news set en_title=?,en_content=? where en_id=?";
		return super.op(sql, new Object[]{news.getEn_title(),news.getEn_content(),news.getEn_id()});
	}

	@Override
	public EasybuyNews getNewsById(int id) {
		// TODO Auto-generated method stub
		String sql="select * from easybuy_news where en_id=?";
		ResultSet rSet=super.query(sql, new Object[]{id});
		EasybuyNews news=new EasybuyNews();
		try {
			if(rSet.next())
			{
				news.setEn_id(rSet.getInt(1));
				news.setEn_title(rSet.getString(2));
				news.setEn_content(rSet.getString(3));
				news.setEn_create_time(rSet.getTimestamp(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return news;
	}

	@Override
	public int GetCount() {
		// TODO Auto-generated method stub
		int result=-1;
		String sql="select count(*) from easybuy_news";
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
