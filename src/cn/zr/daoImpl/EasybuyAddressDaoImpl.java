package cn.zr.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.zr.dao.EasybuyAddressDao;
import cn.zr.entity.EasybuyAddress;

public class EasybuyAddressDaoImpl extends BaseDaoImpl implements EasybuyAddressDao{

	@Override
	public List<EasybuyAddress> getAddressByUserId(String id) {
		// TODO Auto-generated method stub
		List<EasybuyAddress> addresses=new ArrayList<EasybuyAddress>();
		String sql="select * from easybuy_address where ad_user_id=?";
		ResultSet rSet=super.query(sql, new Object[]{id});
		try {
			while(rSet.next())
			{
				EasybuyAddress address=new EasybuyAddress();
				address.setAd_id(rSet.getInt(1));
				address.setAd_user_id(rSet.getString(2));
				address.setAd_address(rSet.getString(3));
				addresses.add(address);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return addresses;
	}

	@Override
	public int ModifyAddress(EasybuyAddress address) {
		// TODO Auto-generated method stub
		String sql="update easybuy_address set ad_user_id=?,ad_address=? where ad_id=?";
		return super.op(sql, new Object[]{address.getAd_user_id(),address.getAd_address(),address.getAd_id()});
	}

	@Override
	public int AddAddress(EasybuyAddress address) {
		String sql="insert into easybuy_address (ad_user_id,ad_address) value(?,?)";
		return super.op(sql, new Object[]{address.getAd_user_id(),address.getAd_address()});
	}

	@Override
	public int DeleteAddress(int id) {
		String sql="delete from easybuy_address where ad_id=?";
		return super.op(sql, new Object[]{id});
	}

}
