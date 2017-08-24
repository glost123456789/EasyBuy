package cn.zr.serviceImpl;

import java.util.List;

import cn.zr.dao.EasybuyNewsDao;
import cn.zr.daoImpl.EasybuyNewsDaoImpl;
import cn.zr.entity.EasybuyNews;
import cn.zr.service.EasybuyNewsService;

public class EasybuyNewsServiceImpl implements EasybuyNewsService {
	private EasybuyNewsDao newdao=new EasybuyNewsDaoImpl();
	@Override
	public List<EasybuyNews> getNewsByPage(int currentpage, int size) {
		// TODO Auto-generated method stub
		return newdao.getNewsByPage(currentpage, size);
	}

	@Override
	public int DeleteNews(int id) {
		// TODO Auto-generated method stub
		if(id>0)
		{
			return newdao.DeleteNews(id);
		}
		return 0;
	}

	@Override
	public int AddNews(EasybuyNews news) {
		// TODO Auto-generated method stub
		if(news!=null)
		{
			return newdao.AddNews(news);
		}
		return 0;
	}

	@Override
	public int Modify(EasybuyNews news) {
		// TODO Auto-generated method stub
		if(news!=null)
		{
			return newdao.ModifyNews(news);
		}
		return 0;
	}

	@Override
	public EasybuyNews getNewsById(int id) {
		// TODO Auto-generated method stub
		if(id>0)
		{
			return newdao.getNewsById(id);
		}
		return null;
	}

	@Override
	public int GetCount() {
		// TODO Auto-generated method stub
		return newdao.GetCount();
	}

}
