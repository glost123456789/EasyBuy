package cn.zr.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.zr.dao.EasybuyShopbusDao;
import cn.zr.entity.EasybuyShopbus;

public class EasybuyShopbusDaoImpl extends BaseDaoImpl  implements EasybuyShopbusDao {

	@Override
	public List<EasybuyShopbus> getAllShopBus() {
		String sql="select * from easybuy_shopbus";
		List<EasybuyShopbus> shopbus=new ArrayList<EasybuyShopbus>();
		ResultSet rSet=super.query(sql, null);
		try {
			while(rSet.next())
			{
				EasybuyShopbus shop=new EasybuyShopbus();
				shop.setId(rSet.getInt(1));
				shop.setUser_id(rSet.getString(2));
				shop.setProduct_id(rSet.getInt(3));
				shop.setProduct_price(rSet.getFloat(4));
				shop.setProduct_count(rSet.getInt(5));
				shop.setProduct_file_name(rSet.getString(6));
				shopbus.add(shop);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return shopbus;
	}

	@Override
	public List<EasybuyShopbus> getShopBusByUserId(String id) {
		// TODO Auto-generated method stub
		String sql="select * from easybuy_shopbus where user_id=?";
		List<EasybuyShopbus> shopbus=new ArrayList<EasybuyShopbus>();
		ResultSet rSet=super.query(sql, new Object[]{id});
		try {
			while(rSet.next())
			{
				EasybuyShopbus shop=new EasybuyShopbus();
				shop.setId(rSet.getInt(1));
				shop.setUser_id(rSet.getString(2));
				shop.setProduct_id(rSet.getInt(3));
				shop.setProduct_price(rSet.getFloat(4));
				shop.setProduct_count(rSet.getInt(5));
				shop.setProduct_file_name(rSet.getString(6));
				shopbus.add(shop);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return shopbus;
	}

	@Override
	public int AddShopbus(EasybuyShopbus shop) {
		String sql="insert into easybuy_shopbus (user_id,product_id,product_price,product_count,product_file_name) value(?,?,?,?,?)";
		return super.op(sql, new Object[]{shop.getUser_id(),shop.getProduct_id(),shop.getProduct_price(),shop.getProduct_count(),shop.getProduct_file_name()});
	}

	@Override
	public EasybuyShopbus getShopById(int id) {
		String sql="select * from easybuy_shopbus where id=?";
		ResultSet rSet=super.query(sql, new Object[]{id});
		EasybuyShopbus shop=new EasybuyShopbus();
		try {
			if(rSet.next())
			{
				shop.setId(rSet.getInt(1));
				shop.setUser_id(rSet.getString(2));
				shop.setProduct_id(rSet.getInt(3));
				shop.setProduct_price(rSet.getFloat(4));
				shop.setProduct_count(rSet.getInt(5));
				shop.setProduct_file_name(rSet.getString(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return shop;
	}

	@Override
	public int Modify(EasybuyShopbus shop) {
		String sql="update easybuy_shopbus set product_price=?,product_count=? where id=?";
		return super.op(sql, new Object[]{shop.getProduct_price(),shop.getProduct_count(),shop.getId()});
	}

	@Override
	public int RemoveShopById(int id) {
		// TODO Auto-generated method stub
		String sql="delete from easybuy_shopbus where id=?";
		return super.op(sql, new Object[]{id});
	}

	@Override
	public int RemoveShopByUserId(String id) {
		String sql="delete from easybuy_shopbus where user_id=?";
		return super.op(sql, new Object[]{id});
	}

}
