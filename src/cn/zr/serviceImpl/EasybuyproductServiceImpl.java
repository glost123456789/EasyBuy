package cn.zr.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import cn.zr.dao.EasybuyProductCategoryDao;
import cn.zr.dao.EasybuyproductDao;
import cn.zr.daoImpl.EasybuyProductCategoryDaoImpl;
import cn.zr.daoImpl.EasybuyproductDaoImpl;
import cn.zr.entity.EasybuyProduct;
import cn.zr.entity.EasybuyProductCategory;
import cn.zr.service.EasybuyproductService;

public class EasybuyproductServiceImpl implements EasybuyproductService {
	private EasybuyproductDao easybuyproductDao=new EasybuyproductDaoImpl();
	private EasybuyProductCategoryDao categorydao=new EasybuyProductCategoryDaoImpl();
	@Override
	public List<EasybuyProduct> getProductByPage(int currentpage, int pagesize) {
		// TODO Auto-generated method stub
		return easybuyproductDao.getProductbyPage(currentpage, pagesize);
	}
	@Override
	public EasybuyProduct getProductById(int id) {
		// TODO Auto-generated method 
		if(id>0)
		{
			return easybuyproductDao.getProductById(id);
		}
		return null;
	}
	@Override
	public int DeleteProduct(int id) {
		if(id>0)
		{
			return easybuyproductDao.DeleteProduct(id);
		}
		return 0;
	}
	@Override
	public int ModifyProduct(EasybuyProduct product) {
		if(product!=null)
		{
			return easybuyproductDao.Modify(product);
		}
		return 0;
	}
	@Override
	public int AddProduct(EasybuyProduct product) {
		if(product!=null)
		{
			return easybuyproductDao.AddProduct(product);
		}
		return 0;
	}
	@Override
	public int GetCount() {
		// TODO Auto-generated method stub
		return easybuyproductDao.getCount();
	}
	@Override
	public List<EasybuyProduct> GetproductsbyCategoryAndPage(int category, int currentpage, int pagesize) {
		// TODO Auto-generated method stub
		EasybuyProductCategory thiscategory=categorydao.getCategoryById(category);
		List<EasybuyProduct> currentproducts=new ArrayList<EasybuyProduct>();
		if(thiscategory.getEpc_parent_id()==0)
		{
			List<EasybuyProduct> allproducts=easybuyproductDao.GetproductsByParentCategory(category);
			for(int i=0;i<allproducts.size();i++)
			{
				if(i>=((currentpage-1)*pagesize) && i<(currentpage*pagesize))
				{
					currentproducts.add(allproducts.get(i));
				}
			}
		}else{
		List<EasybuyProduct> allproducts=easybuyproductDao.GetproductsByCategory(category);
		for(int i=0;i<allproducts.size();i++)
		{
			if(i>=((currentpage-1)*pagesize) && i<(currentpage*pagesize))
			{
				currentproducts.add(allproducts.get(i));
			}
		}
		}
		return currentproducts;
	}
	@Override
	public int GetCountByCategory(int category) {
		EasybuyProductCategory category2=categorydao.getCategoryById(category);
		if(category2.getEpc_parent_id()==0)
		{
			return easybuyproductDao.GetproductsByParentCategory(category).size();
		}
		return easybuyproductDao.GetproductsByCategory(category).size();
	}
	@Override
	public List<EasybuyProduct> GetproductsByBrandAndPage(String brand, int currentpage, int pagesize) {
		// TODO Auto-generated method stub
		List<EasybuyProduct> products=new ArrayList<EasybuyProduct>();
		if(brand!=null && !brand.equals(""))
		{
			List<EasybuyProduct> allproducts=easybuyproductDao.GetproductsByBrand(brand);
			for(int i=0;i<allproducts.size();i++)
			{
				if(i>=(currentpage-1)*pagesize && i<currentpage*pagesize)
				{
					products.add(allproducts.get(i));
				}
			}
			return products;
		}else{
			List<EasybuyProduct> allproducts=easybuyproductDao.GetproductsByAllBrand();
			for(int i=0;i<allproducts.size();i++)
			{
				if(i>=(currentpage-1)*pagesize && i<currentpage*pagesize)
				{
					products.add(allproducts.get(i));
				}
			}
			return products;
		}
	}
	@Override
	public int GetCountByBrand(String brand) {
		// TODO Auto-generated method stub
		if(brand!=null && !brand.equals("")){
			return easybuyproductDao.GetproductsByBrand(brand).size();
		}
		return easybuyproductDao.GetproductsByAllBrand().size();
	}
	@Override
	public List<EasybuyProduct> GetPromotiveByCategoryAndPage(int category,int currentpage,int pagesize) {
		// TODO Auto-generated method stub
		List<EasybuyProduct> allproducts=easybuyproductDao.GetPromotesByCategory(category);
		List<EasybuyProduct> products=new ArrayList<EasybuyProduct>();
		for(int i=0;i<allproducts.size();i++)
		{
			if(i>=(currentpage-1)*pagesize && i<currentpage*pagesize)
			{
				products.add(allproducts.get(i));
			}
		}
		return products;
	}
	@Override
	public int getPromotiveCountByCategory(int categroy) {
		return easybuyproductDao.GetPromotesByCategory(categroy).size();
	}
	
}
