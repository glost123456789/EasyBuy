package cn.zr.dao;

import java.util.List;

import cn.zr.entity.EasybuyProductCategory;

public interface EasybuyProductCategoryDao extends BaseDao {
	/*
	 * 根据分类ID寻找分类数据
	 */
	public EasybuyProductCategory getCategoryById(int id);
	
	/*
	 * 根据分类名获取Id
	 */
	public int getCategoryIdByName(String name);
	
	
	/*
	 * 获取全部分类
	 */
	public List<EasybuyProductCategory> getAllCategory();
	
	/*
	 * 获取一页分类信息
	 */
	public List<EasybuyProductCategory> getCategoryByPage(int currentpage,int pagesize);
	
	/*
	 * 修改分类
	 */
	public int ModifyCategory(EasybuyProductCategory category);
	
	/*
	 * 增加分类
	 */
	public int AddCategory(EasybuyProductCategory category);
	
	/*
	 * 删除分类
	 */
	public int DeleteCategory(int id);
	
	/*
	 * 按根分类获取
	 */
	public List<EasybuyProductCategory> getCategoriesByParent(int id);
	
	/*
	 * 获取总数
	 */
	public int getCount();
}
