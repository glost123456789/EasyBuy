package cn.zr.daoImpl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.zr.dao.EasybuyOrderDao;
import cn.zr.entity.EasyBuyOrder;

public class EasybuyOrderDaoImpl extends BaseDaoImpl implements EasybuyOrderDao {

	@Override
	public List<EasyBuyOrder> getOrderByUserId(String id) {
		String sql="select * from easybuy_order where eo_user_id=?";
		ResultSet resultSet=super.query(sql, new Object[]{id});
		List<EasyBuyOrder> orders=new ArrayList<EasyBuyOrder>();
		try {
			while(resultSet.next())
			{
				EasyBuyOrder order=new EasyBuyOrder();
				order.setEo_id(resultSet.getInt(1));
				order.setEo_user_id(resultSet.getString(2));
				order.setEo_user_name(resultSet.getString(3));
				order.setEo_user_address(resultSet.getString(4));
				order.setEo_create_time(resultSet.getDate(5));
				order.setEo_cost(resultSet.getFloat(6));
				order.setEo_cost(resultSet.getInt(7));
				orders.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orders;
	}

	@Override
	public int addOrder(EasyBuyOrder order) {
		String sql="insert into easybuy_order (eo_id,eo_user_id,eo_user_name,eo_user_address,eo_create_time,eo_cost,eo_status) value(?,?,?,?,?,?,?)";
		return super.op(sql, new Object[]{order.getEo_id(),order.getEo_user_id(),order.getEo_user_name(),order.getEo_user_address(),order.getEo_create_time(),order.getEo_cost(),order.getEo_status()});
	}

	@Override
	public List<EasyBuyOrder> getOrderByPage(int currentpage, int pagesize) {
		String sql="select * from easybuy_order limit ?,?";
		ResultSet resultSet=super.query(sql, new Object[]{(currentpage-1)*pagesize,pagesize});
		List<EasyBuyOrder> orders=new ArrayList<EasyBuyOrder>();
		try {
			while(resultSet.next())
			{
				EasyBuyOrder order=new EasyBuyOrder();
				order.setEo_id(resultSet.getInt(1));
				order.setEo_user_id(resultSet.getString(2));
				order.setEo_user_name(resultSet.getString(3));
				order.setEo_user_address(resultSet.getString(4));
				order.setEo_create_time(resultSet.getDate(5));
				order.setEo_cost(resultSet.getFloat(6));
				order.setEo_status(resultSet.getInt(7));
				orders.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orders;
	}

	@Override
	public int deleteOrder(int id) {
		String sql="delete from easybuy_order where eo_id=?";
		return super.op(sql, new Object[]{id});
	}

	@Override
	public int ModifyOrder(EasyBuyOrder order) {
		String sql="update easybuy_order set eo_user_id=?,eo_user_name=?,eo_user_address=?,eo_create_time=?,eo_cost=?,eo_status=? where eo_id=?";
		return super.op(sql, new Object[]{order.getEo_user_id(),order.getEo_user_name(),order.getEo_user_address(),order.getEo_create_time(),order.getEo_cost(),order.getEo_status(),order.getEo_id()});
	}
	
	@Override
	public int GetIdByDate(Date date) {
		String sql="select eo_id from easybuy_order where eo_create_time=?";
		ResultSet resultSet=super.query(sql, new Object[]{date});
		try {
			if(resultSet.next())
			{
				return resultSet.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public EasyBuyOrder getOrderById(int id) {
		// TODO Auto-generated method stub
		String sql="select * from easybuy_order where eo_id=?";
		ResultSet resultSet=super.query(sql, new Object[]{id});
		EasyBuyOrder order=new EasyBuyOrder();
		try {
			if(resultSet.next())
			{
				order.setEo_id(resultSet.getInt(1));
				order.setEo_user_id(resultSet.getString(2));
				order.setEo_user_name(resultSet.getString(3));
				order.setEo_user_address(resultSet.getString(4));
				order.setEo_create_time(resultSet.getDate(5));
				order.setEo_cost(resultSet.getFloat(6));
				order.setEo_status(resultSet.getInt(7));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order;
	}

	@Override
	public List<EasyBuyOrder> getOrdersByUserName(String name) {
		// TODO Auto-generated method stub
		String sql="select * from easybuy_order where eo_user_name=?";
		ResultSet resultSet=super.query(sql, new Object[]{name});
		List<EasyBuyOrder> orders=new ArrayList<EasyBuyOrder>();
		try {
			while(resultSet.next())
			{
				EasyBuyOrder order=new EasyBuyOrder();
				order.setEo_id(resultSet.getInt(1));
				order.setEo_user_id(resultSet.getString(2));
				order.setEo_user_name(resultSet.getString(3));
				order.setEo_user_address(resultSet.getString(4));
				order.setEo_create_time(resultSet.getDate(5));
				order.setEo_cost(resultSet.getFloat(6));
				order.setEo_status(resultSet.getInt(7));
				orders.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orders;
	}

	@Override
	public List<EasyBuyOrder> getAllOrder() {
		// TODO Auto-generated method stub
		String sql="select * from easybuy_order";
		ResultSet resultSet=super.query(sql, null);
		List<EasyBuyOrder> orders=new ArrayList<EasyBuyOrder>();
		try {
			while(resultSet.next())
			{
				EasyBuyOrder order=new EasyBuyOrder();
				order.setEo_id(resultSet.getInt(1));
				order.setEo_user_id(resultSet.getString(2));
				order.setEo_user_name(resultSet.getString(3));
				order.setEo_user_address(resultSet.getString(4));
				order.setEo_create_time(resultSet.getDate(5));
				order.setEo_cost(resultSet.getFloat(6));
				order.setEo_status(resultSet.getInt(7));
				orders.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orders;
	}

}
