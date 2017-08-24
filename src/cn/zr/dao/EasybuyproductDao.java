package cn.zr.dao;

import java.util.List;

import org.omg.CORBA.PUBLIC_MEMBER;

import cn.zr.entity.EasybuyProduct;

public interface EasybuyproductDao extends BaseDao {
	/*
	 * 根据商品名获取商品
	 */
	public EasybuyProduct getProductByName(String name);
	
	/*
	 * 按页获取商品
	 */
	public List<EasybuyProduct> getProductbyPage(int currentpage,int pagesize);
	
	/*
	 * 根据ID获取商品
	 */
	public EasybuyProduct getProductById(int id);
	
	/*
	 * 删除商品
	 */
	public int DeleteProduct(int id);
	
	/*
	 * 修改商品
	 */
	public int Modify(EasybuyProduct product);
	
	/*
	 * 增加商品
	 */
	public int AddProduct(EasybuyProduct product);
	
	/*
	 * 获取数据条数
	 */
	public int getCount();
	
	/*
	 * 根据子分类获取商品
	 */
	public List<EasybuyProduct> GetproductsByCategory(int category);
	
	/*
	 * 根据父分类获取商品
	 */
	public List<EasybuyProduct> GetproductsByParentCategory(int pcategory);
	
	/*
	 * 搜索全部品牌商品
	 */
	public List<EasybuyProduct> GetproductsByAllBrand();
	
	/*
	 * 按品牌搜索商品
	 */
	public List<EasybuyProduct> GetproductsByBrand(String Brand);
	
	/*
	 * 在促销商品中按分类查找
	 * 如果id=0搜索所有的促销商品
	 */
	public List<EasybuyProduct> GetPromotesByCategory(int categoryid);
	
}
