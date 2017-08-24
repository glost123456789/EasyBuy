package cn.zr.service;

import java.util.List;

import cn.zr.entity.EasybuyNews;

public interface EasybuyNewsService {
	//获取最新的15条新闻
	public List<EasybuyNews> getNewsByPage(int currentpage,int size);
	
	//删除新闻
	public int DeleteNews(int id);
	
	//增加新闻
	public int AddNews(EasybuyNews news);
	
	//修改新闻
	public int Modify(EasybuyNews news);
	
	//获取某一条新闻
	public EasybuyNews getNewsById(int id);
	
	//获取总数
	public int GetCount();
}
