package cn.zr.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.zr.dao.EasybuyproductDao;
import cn.zr.entity.EasybuyProduct;

public class EasybuyproductDaoImpl extends BaseDaoImpl implements EasybuyproductDao {

	@Override
	public EasybuyProduct getProductByName(String name) {
		EasybuyProduct product=new EasybuyProduct();
		String sql="select * from easybuy_product where ep_name=?";
		ResultSet resultSet=super.query(sql, new Object[]{name});
		try {
			if(resultSet.next())
			{
				product.setEp_id(resultSet.getInt(1));
				product.setEp_name(resultSet.getString(2));
				product.setEp_description(resultSet.getString(3));
				product.setEp_price(resultSet.getFloat(4));
				product.setEp_stock(resultSet.getInt(5));
				product.setEpc_id(resultSet.getInt(6));
				product.setEpc_child_id(resultSet.getInt(7));
				product.setEp_file_name(resultSet.getString(8));
				product.setEp_brand(resultSet.getString(9));
				product.setEp_origin_price(resultSet.getFloat(10));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public List<EasybuyProduct> getProductbyPage(int currentpage, int pagesize) {
		String sql="select * from easybuy_product limit ?,?";
		List<EasybuyProduct> products=new ArrayList<EasybuyProduct>();
		ResultSet resultSet=super.query(sql, new Object[]{(currentpage-1)*pagesize,pagesize});
		try {
			while(resultSet.next())
			{
				EasybuyProduct product=new EasybuyProduct();
				product.setEp_id(resultSet.getInt(1));
				product.setEp_name(resultSet.getString(2));
				product.setEp_description(resultSet.getString(3));
				product.setEp_price(resultSet.getFloat(4));
				product.setEp_stock(resultSet.getInt(5));
				product.setEpc_id(resultSet.getInt(6));
				product.setEpc_child_id(resultSet.getInt(7));
				product.setEp_file_name(resultSet.getString(8));
				product.setEp_brand(resultSet.getString(9));
				product.setEp_origin_price(resultSet.getFloat(10));
				products.add(product);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}

	public EasybuyProduct getProductById(int id)
	{
		EasybuyProduct product=new EasybuyProduct();
		String sql="select * from easybuy_product where ep_id=?";
		ResultSet resultSet=super.query(sql, new Object[]{id});
		try {
			if(resultSet.next())
			{
				product.setEp_id(resultSet.getInt(1));
				product.setEp_name(resultSet.getString(2));
				product.setEp_description(resultSet.getString(3));
				product.setEp_price(resultSet.getFloat(4));
				product.setEp_stock(resultSet.getInt(5));
				product.setEpc_id(resultSet.getInt(6));
				product.setEpc_child_id(resultSet.getInt(7));
				product.setEp_file_name(resultSet.getString(8));
				product.setEp_brand(resultSet.getString(9));
				product.setEp_origin_price(resultSet.getFloat(10));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public int DeleteProduct(int id) {
		String sql="delete from easybuy_product where ep_id=?";
		return super.op(sql, new Object[]{id});
	}

	@Override
	public int Modify(EasybuyProduct product) {
		String sql="update easybuy_product set ep_name=?,ep_description=?,ep_price=?,ep_stock=?,epc_id=?,epc_child_id=?,ep_file_name=?,ep_brand=? where ep_id=?";
		return super.op(sql, new Object[]{product.getEp_name(),
										product.getEp_description(),
										product.getEp_price(),product.getEp_stock(),product.getEp_id(),product.getEpc_child_id(),product.getEp_file_name(),product.getEp_brand(),product.getEp_id()});
	}

	@Override
	public int AddProduct(EasybuyProduct product) {
		String sql="insert into easybuy_product (ep_name,ep_description,ep_price,ep_stock,epc_id,epc_child_id,ep_file_name,ep_brand) value(?,?,?,?,?,?,?,?)";
		return super.op(sql, new Object[]{product.getEp_name(),product.getEp_description(),product.getEp_price(),product.getEp_stock(),product.getEp_id(),product.getEpc_child_id(),product.getEp_file_name(),product.getEp_brand()});
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		int result=-1;
		String sql="select count(*) from easybuy_product";
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

	@Override
	public List<EasybuyProduct> GetproductsByCategory(int category) {
		// TODO Auto-generated method stub
		String sql="select * from easybuy_product where epc_child_id=?";
		List<EasybuyProduct> products=new ArrayList<EasybuyProduct>();
		ResultSet resultSet=super.query(sql, new Object[]{category});
		try {
			while(resultSet.next())
			{
				EasybuyProduct product=new EasybuyProduct();
				product.setEp_id(resultSet.getInt(1));
				product.setEp_name(resultSet.getString(2));
				product.setEp_description(resultSet.getString(3));
				product.setEp_price(resultSet.getFloat(4));
				product.setEp_stock(resultSet.getInt(5));
				product.setEpc_id(resultSet.getInt(6));
				product.setEpc_child_id(resultSet.getInt(7));
				product.setEp_file_name(resultSet.getString(8));
				product.setEp_brand(resultSet.getString(9));
				product.setEp_origin_price(resultSet.getFloat(10));
				products.add(product);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}

	@Override
	public List<EasybuyProduct> GetproductsByParentCategory(int pcategory) {
		// TODO Auto-generated method stub
		String sql="select * from easybuy_product where epc_id=?";
		List<EasybuyProduct> products=new ArrayList<EasybuyProduct>();
		ResultSet resultSet=super.query(sql, new Object[]{pcategory});
		try {
			while(resultSet.next())
			{
				EasybuyProduct product=new EasybuyProduct();
				product.setEp_id(resultSet.getInt(1));
				product.setEp_name(resultSet.getString(2));
				product.setEp_description(resultSet.getString(3));
				product.setEp_price(resultSet.getFloat(4));
				product.setEp_stock(resultSet.getInt(5));
				product.setEpc_id(resultSet.getInt(6));
				product.setEpc_child_id(resultSet.getInt(7));
				product.setEp_file_name(resultSet.getString(8));
				product.setEp_brand(resultSet.getString(9));
				product.setEp_origin_price(resultSet.getFloat(10));
				products.add(product);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}

	@Override
	public List<EasybuyProduct> GetproductsByAllBrand() {
		// TODO Auto-generated method stub
		String sql="select * from easybuy_product where ep_brand is not null and ep_brand <> ' '";
		List<EasybuyProduct> products=new ArrayList<EasybuyProduct>();
		ResultSet resultSet=super.query(sql, null);
		try {
			while(resultSet.next())
			{
				EasybuyProduct product=new EasybuyProduct();
				product.setEp_id(resultSet.getInt(1));
				product.setEp_name(resultSet.getString(2));
				product.setEp_description(resultSet.getString(3));
				product.setEp_price(resultSet.getFloat(4));
				product.setEp_stock(resultSet.getInt(5));
				product.setEpc_id(resultSet.getInt(6));
				product.setEpc_child_id(resultSet.getInt(7));
				product.setEp_file_name(resultSet.getString(8));
				product.setEp_brand(resultSet.getString(9));
				product.setEp_origin_price(resultSet.getFloat(10));
				products.add(product);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}

	@Override
	public List<EasybuyProduct> GetproductsByBrand(String Brand) {
		// TODO Auto-generated method stub
		String sql="select * from easybuy_product where ep_brand=?";
		List<EasybuyProduct> products=new ArrayList<EasybuyProduct>();
		ResultSet resultSet=super.query(sql, new Object[]{Brand});
		try {
			while(resultSet.next())
			{
				EasybuyProduct product=new EasybuyProduct();
				product.setEp_id(resultSet.getInt(1));
				product.setEp_name(resultSet.getString(2));
				product.setEp_description(resultSet.getString(3));
				product.setEp_price(resultSet.getFloat(4));
				product.setEp_stock(resultSet.getInt(5));
				product.setEpc_id(resultSet.getInt(6));
				product.setEpc_child_id(resultSet.getInt(7));
				product.setEp_file_name(resultSet.getString(8));
				product.setEp_brand(resultSet.getString(9));
				product.setEp_origin_price(resultSet.getFloat(10));
				products.add(product);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}

	@Override
	public List<EasybuyProduct> GetPromotesByCategory(int categoryid) {
		// TODO Auto-generated method stub
		String sql=null;
		ResultSet resultSet=null;
		List<EasybuyProduct> products=new ArrayList<EasybuyProduct>();
		if(categoryid==0)
		{
			sql="select * from easybuy_product where ep_origin_price is not null and ep_origin_price <> '0.0'";
		    resultSet=super.query(sql,null);
		}else{
			sql="select * from easybuy_product where ep_origin_price is not null and ep_origin_price <> '0.0' and epc_child_id=?";
			resultSet=super.query(sql, new Object[]{categoryid});
		}
		try {
			while(resultSet.next())
			{
				EasybuyProduct product=new EasybuyProduct();
				product.setEp_id(resultSet.getInt(1));
				product.setEp_name(resultSet.getString(2));
				product.setEp_description(resultSet.getString(3));
				product.setEp_price(resultSet.getFloat(4));
				product.setEp_stock(resultSet.getInt(5));
				product.setEpc_id(resultSet.getInt(6));
				product.setEpc_child_id(resultSet.getInt(7));
				product.setEp_file_name(resultSet.getString(8));
				product.setEp_brand(resultSet.getString(9));
				product.setEp_origin_price(resultSet.getFloat(10));
				products.add(product);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return products;
	}
}
