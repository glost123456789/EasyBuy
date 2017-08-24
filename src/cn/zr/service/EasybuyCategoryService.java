package cn.zr.service;

import java.util.List;

import cn.zr.entity.EasybuyProductCategory;

public interface EasybuyCategoryService {
	public EasybuyProductCategory getCategoryById(int id);
	
	/*
	 * 获取全部商品分类
	 */
	public List<EasybuyProductCategory> getAllCategory();
	
	/*
	 * 修改分类
	 */
	public int ModifyCategory(EasybuyProductCategory category);
	
	/*
	 * 删除分类
	 */
	public int DeleteCategory(int id);
	
	/*
	 * 增加分类
	 */
	
	public int AddCategory(EasybuyProductCategory category);
	
	/*
	 * 排列分类
	 */
	public List<EasybuyProductCategory> getSortCategoriesByPage(int currentpage,int size);
	
	/*
	 * 获取分类总数
	 */
	public int GetCount();
	
	/*
	 * 按根分类获取
	 */
	public List<EasybuyProductCategory> getCategoriesByParent(int id);
}
