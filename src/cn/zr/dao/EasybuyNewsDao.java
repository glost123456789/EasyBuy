package cn.zr.dao;

import java.util.List;
import cn.zr.entity.*;
public interface EasybuyNewsDao extends BaseDao{
	/*
	 * 按页获取新闻
	 */
	public List<EasybuyNews> getNewsByPage(int currentpage,int pagesize);
	
	/*
	 * 增加新闻
	 */
	public int AddNews(EasybuyNews news);
	
	/*
	 * 删除新闻
	 */
	public int DeleteNews(int id);
	
	/*
	 * 修改新闻
	 */
	public int ModifyNews(EasybuyNews news);
	
	/*
	 * 获取一条新闻
	 */
	public EasybuyNews getNewsById(int id);
	
	/*
	 * 获取总数
	 */
	public int GetCount();
}
