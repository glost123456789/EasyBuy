package cn.zr.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import cn.zr.dao.EasybuyProductCategoryDao;
import cn.zr.dao.EasybuyproductDao;
import cn.zr.daoImpl.EasybuyProductCategoryDaoImpl;
import cn.zr.entity.EasybuyProductCategory;
import cn.zr.service.EasybuyCategoryService;

public class EasybuyCategoryServiceImpl implements EasybuyCategoryService {
	EasybuyProductCategoryDao categoryDao=new EasybuyProductCategoryDaoImpl();
	@Override
	public EasybuyProductCategory getCategoryById(int id) {
		// TODO Auto-generated method stub
		return categoryDao.getCategoryById(id);
	}
	@Override
	public List<EasybuyProductCategory> getAllCategory() {
		return categoryDao.getAllCategory();
	}
	@Override
	public int ModifyCategory(EasybuyProductCategory category) {
		// TODO Auto-generated method stub
		if(category!=null)
		{
			return categoryDao.ModifyCategory(category);
		}
		return 0;
	}
	@Override
	public int DeleteCategory(int id) {
		if(id>0)
		{
			return categoryDao.DeleteCategory(id);
		}
		return 0;
	}
	@Override
	public int AddCategory(EasybuyProductCategory category) {
		if(category!=null)
		{
			return categoryDao.AddCategory(category);
		}
		return 0;
	}
	@Override
	public List<EasybuyProductCategory> getSortCategoriesByPage(int currentpage, int size) {
		List<EasybuyProductCategory> rootcategories=categoryDao.getCategoriesByParent(0);
		List<EasybuyProductCategory> categories=new ArrayList<EasybuyProductCategory>();
		List<EasybuyProductCategory> showcategories=new ArrayList<EasybuyProductCategory>();
		int step=0;
		for(int i=0;i<rootcategories.size();i++)
		{
			List<EasybuyProductCategory> childcategories=categoryDao.getCategoriesByParent(rootcategories.get(i).getEpc_id());
			categories.add(rootcategories.get(i));
			categories.addAll(childcategories);
			step+=childcategories.size()+1;
			if(step>=currentpage*size)
			{
				break;
			}
		}
		for(int i=(currentpage-1)*size;i<(currentpage*size);i++)
		{
			if(i>=categories.size())
			{
				break;
			}
			showcategories.add(categories.get(i));
		}
		return showcategories;
	}
	@Override
	public int GetCount() {
		return categoryDao.getCount();
	}
	@Override
	public List<EasybuyProductCategory> getCategoriesByParent(int id) {
		// TODO Auto-generated method stub
		if(id>0)
		{
			return categoryDao.getCategoriesByParent(id);
		}
		return null;
	}

}
