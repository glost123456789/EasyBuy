package cn.zr.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.fabric.xmlrpc.base.Array;

import cn.zr.dao.EasyBuyUserDao;
import cn.zr.entity.EasyUser;
import cn.zr.entity.EasybuyProduct;

public class EasyBuyUserDaoImpl extends BaseDaoImpl implements EasyBuyUserDao {

	@Override
	public int Increase(EasyUser easyUser) {
		String sql="insert into easybuy_user (eu_user_id,eu_user_name,eu_password,eu_sex,eu_birthday,eu_identity_code,eu_email,eu_mobile,eu_status,eu_address,eu_login)"
				+ "	value (?,?,?,?,?,?,?,?,?,?,?)";
		return super.op(sql, new Object[]{easyUser.getEu_user_id(),easyUser.getEu_user_name(),easyUser.getEu_password(),easyUser.getEu_sex(),easyUser.getEu_birthday(),easyUser.getEu_identity_code(),easyUser.getEu_email(),easyUser.getEu_mobile(),easyUser.getEu_status(),easyUser.getEu_address(),easyUser.getEu_login()});
	}

	@Override
	public String getUserPwdById(String id) {
		String sql="select eu_password from easybuy_user where eu_user_id=?";
		ResultSet resultSet=super.query(sql, new Object[]{id});
		String pwd=null;
		try {
			if(resultSet.next())
			{
				pwd=resultSet.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pwd;
	}

	@Override
	public EasyUser getUserById(String id) {
		String sql="select * from easybuy_user where eu_user_id=?";
		ResultSet resultSet=super.query(sql, new Object[]{id});
		EasyUser easyUser=new EasyUser();
		try {
			if(resultSet.next())
			{
				easyUser.setEu_user_id(resultSet.getString(1));
				easyUser.setEu_user_name(resultSet.getString(2));
				easyUser.setEu_password(resultSet.getString(3));
				easyUser.setEu_sex(resultSet.getString(4));
				easyUser.setEu_birthday(resultSet.getDate(5));
				easyUser.setEu_identity_code(resultSet.getString(6));
				easyUser.setEu_email(resultSet.getString(7));
				easyUser.setEu_mobile(resultSet.getString(8));
				easyUser.setEu_address(resultSet.getString(9));
				easyUser.setEu_login(resultSet.getDouble(10));
				easyUser.setEu_status(resultSet.getInt(11));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return easyUser;
	}

	@Override
	public int setUserLoginState(String id,float state) {
		String sql="update easybuy_user set eu_login=? where eu_user_id=?";
		return super.op(sql, new Object[]{state,id});
	}

	@Override
	public List<EasyUser> getUsersByPage(int currentpage, int pageround) {
		String sql="select * from easybuy_user limit ?,?";
		List<EasyUser> users=new ArrayList<EasyUser>();
		ResultSet resultSet=super.query(sql, new Object[]{(currentpage-1)*pageround,pageround});
		try {
			while(resultSet.next())
			{
				EasyUser easyUser=new EasyUser();
				easyUser.setEu_user_id(resultSet.getString(1));
				easyUser.setEu_user_name(resultSet.getString(2));
				easyUser.setEu_password(resultSet.getString(3));
				easyUser.setEu_sex(resultSet.getString(4));
				easyUser.setEu_birthday(resultSet.getDate(5));
				easyUser.setEu_identity_code(resultSet.getString(6));
				easyUser.setEu_email(resultSet.getString(7));
				easyUser.setEu_mobile(resultSet.getString(8));
				easyUser.setEu_address(resultSet.getString(9));
				easyUser.setEu_login(resultSet.getDouble(10));
				easyUser.setEu_status(resultSet.getInt(11));
				users.add(easyUser);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public int DeleteUserById(String id) {
		String sql="delete from easybuy_user where eu_user_id=?";
		return super.op(sql, new Object[]{id});
	}

	@Override
	public int ModifyUserByUserId(EasyUser modifyuser) {
		String sql="update easybuy_user set  eu_user_name=?,eu_password=?,eu_sex=?,eu_birthday=?,eu_email=?,eu_mobile=?,eu_address=?,eu_login=?,eu_status=? where eu_user_id=?";
		
		return super.op(sql, new Object[]{modifyuser.getEu_user_name(),modifyuser.getEu_password(),modifyuser.getEu_sex(),modifyuser.getEu_birthday(),
											modifyuser.getEu_email(),modifyuser.getEu_mobile(),modifyuser.getEu_address(),modifyuser.getEu_login(),modifyuser.getEu_status(),modifyuser.getEu_user_id()});
	}

	@Override
	public int getCount() {
		int result=-1;
		String sql="select count(*) from easybuy_user";
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
