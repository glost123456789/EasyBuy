package cn.zr.service;

import java.util.List;

import cn.zr.entity.EasybuyProduct;

public interface EasybuyproductService {
	/*
	 * 查询某一页的产品数据
	 * currentpage 表示当前页码
	 * pagesize表示一页里的条数
	 * 返回一个产品资料的集合
	 */
	public List<EasybuyProduct>	getProductByPage(int currentpage,int pagesize);
	
	
	/*
	 * 通过id获取商品
	 */
	public EasybuyProduct getProductById(int id);
	
	/*
	 * 删除商品
	 */
	public int DeleteProduct(int id);
	
	/*
	 * 修改商品
	 */
	public int ModifyProduct(EasybuyProduct product);
	
	/*
	 * 增加商品
	 */
	public int AddProduct(EasybuyProduct product);
	
	/*
	 * 获取总数
	 */
	public int GetCount();
	
	/*
	 * 按页获取某一分类下的商品
	 */
	public List<EasybuyProduct> GetproductsbyCategoryAndPage(int category,int currentpage,int pagesize);
	
	/*
	 * 获取某一类商品总数
	 */
	public int GetCountByCategory(int category);
	
	/*
	 * 按页获取某一品牌下的商品
	 * brand为null时按全部商品获取
	 */
	public List<EasybuyProduct> GetproductsByBrandAndPage(String brand,int currentpage,int pagesize);
	
	/*
	 * 获取某一品牌下的商品数
	 * brand为null时获取全部品牌的商品个数
	 */
	public int GetCountByBrand(String brand);
	
	/*
	 * 获取促销商品下的某一种类商品
	 * category=0时，获取所有促销商品
	 */
	public List<EasybuyProduct> GetPromotiveByCategoryAndPage(int category,int currentpage,int pagesize);
	
	/*
	 * 获取促销商品下的某一类商品总数
	 * category=0时，获取所有促销商品
	 */
	public int getPromotiveCountByCategory(int categroy);
	
}
