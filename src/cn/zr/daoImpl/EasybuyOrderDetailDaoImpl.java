package cn.zr.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.zr.dao.EasybuyOrderDetailDao;
import cn.zr.entity.EasybuyOrderdetail;

public class EasybuyOrderDetailDaoImpl extends BaseDaoImpl implements EasybuyOrderDetailDao {

	@Override
	public List<EasybuyOrderdetail> getDetailsbyOrderId(int id) {
		// TODO Auto-generated method stub
		String sql="select * from easybuy_order_detail where eo_id=?";
		ResultSet rSet=super.query(sql, new Object[]{id});
		List<EasybuyOrderdetail> details=new ArrayList<EasybuyOrderdetail>();
		try {
			while(rSet.next())
			{
				EasybuyOrderdetail orderdetail=new EasybuyOrderdetail();
				orderdetail.setEod_id(rSet.getInt(1));
				orderdetail.setEo_id(rSet.getInt(2));
				orderdetail.setEp_id(rSet.getInt(3));
				orderdetail.setEod_quantity(rSet.getInt(4));
				orderdetail.setEod_cost(rSet.getFloat(5));
				details.add(orderdetail);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return details;
	}

	@Override
	public int DeleteOrderDetail(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int AddOrderDetail(EasybuyOrderdetail detail) {
		String sql="insert into easybuy_order_detail (eo_id,ep_id,eod_quantity,eod_cost) value(?,?,?,?)";
		return super.op(sql, new Object[]{detail.getEo_id(),detail.getEp_id(),detail.getEod_quantity(),detail.getEod_cost()});
	}

	@Override
	public int getCount() {
		int result=-1;
		String sql="select count(*) from easybuy_order_detail";
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
