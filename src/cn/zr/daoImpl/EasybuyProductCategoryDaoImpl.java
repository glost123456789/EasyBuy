package cn.zr.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import cn.zr.dao.EasybuyProductCategoryDao;
import cn.zr.entity.EasybuyProductCategory;

public class EasybuyProductCategoryDaoImpl extends BaseDaoImpl implements EasybuyProductCategoryDao {

	@Override
	public EasybuyProductCategory getCategoryById(int id) {
		String sql="select * from easybuy_product_category where epc_id=?";
		ResultSet resultSet=super.query(sql, new Object[]{id});
		EasybuyProductCategory category=new EasybuyProductCategory();
		try {
			if(resultSet.next())
			{
				category.setEpc_id(id);
				category.setEpc_name(resultSet.getString(2));
				category.setEpc_parent_id(resultSet.getInt(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return category;
	}

	@Override
	public int getCategoryIdByName(String name) {
		String sql="select epc_id from easybuy_product_category where epc_name=?";
		ResultSet rSet=super.query(sql, new Object[]{name});
		int id=0;
		try {
			if(rSet.next())
			{
				id=rSet.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public List<EasybuyProductCategory> getAllCategory() {
		String sql="select * from easybuy_product_category";
		ResultSet resultSet=super.query(sql, null);
		List<EasybuyProductCategory> categories=new ArrayList<EasybuyProductCategory>();
		try {
			while(resultSet.next())
			{
				EasybuyProductCategory category=new EasybuyProductCategory();
				category.setEpc_id(resultSet.getInt(1));
				category.setEpc_name(resultSet.getString(2));
				category.setEpc_parent_id(resultSet.getInt(3));
				categories.add(category);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categories;
	}

	@Override
	public List<EasybuyProductCategory> getCategoryByPage(int currentpage, int pagesize) {
		String sql="select * from easybuy_product_category limit ?,?";
		ResultSet resultSet=super.query(sql, new Object[]{(currentpage-1)*pagesize,pagesize});
		List<EasybuyProductCategory> categories=new ArrayList<EasybuyProductCategory>();
		try {
			while(resultSet.next())
			{
				EasybuyProductCategory category=new EasybuyProductCategory();
				category.setEpc_id(resultSet.getInt(1));
				category.setEpc_name(resultSet.getString(2));
				category.setEpc_parent_id(resultSet.getInt(3));
				categories.add(category);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categories;
	}

	@Override
	public int ModifyCategory(EasybuyProductCategory category) {
		String sql="update easybuy_product_category set epc_name=?,epc_parent_id=? where epc_id=?";
		return super.op(sql, new Object[]{category.getEpc_name(),category.getEpc_parent_id(),category.getEpc_id()});
	}

	@Override
	public int AddCategory(EasybuyProductCategory category) {
		String sql="insert into easybuy_product_category (epc_name,epc_parent_id) value(?,?)";
		// TODO Auto-generated method stub
		return super.op(sql, new Object[]{category.getEpc_name(),category.getEpc_parent_id()});
	} 

	@Override
	public int DeleteCategory(int id) {
		// TODO Auto-generated method stub
		String sql="delete from easybuy_product_category where epc_id=?";
		return super.op(sql, new Object[]{id});
	}

	@Override
	public List<EasybuyProductCategory> getCategoriesByParent(int id) {
		String sql="select * from easybuy_product_category where epc_parent_id=?";
		ResultSet resultSet=super.query(sql, new Object[]{id});
		List<EasybuyProductCategory> categories=new ArrayList<EasybuyProductCategory>();
		try {
			while(resultSet.next())
			{
				EasybuyProductCategory category=new EasybuyProductCategory();
				category.setEpc_id(resultSet.getInt(1));
				category.setEpc_name(resultSet.getString(2));
				category.setEpc_parent_id(resultSet.getInt(3));
				categories.add(category);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categories;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		int result=-1;
		String sql="select count(*) from easybuy_product_category";
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

}
