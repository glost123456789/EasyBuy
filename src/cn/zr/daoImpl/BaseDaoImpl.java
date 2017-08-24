package cn.zr.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.zr.JNDIUtil.JNDIUtil;
import cn.zr.dao.BaseDao;

public class BaseDaoImpl implements BaseDao {
	Connection connection=JNDIUtil.getCon();
	@Override
	public ResultSet query(String sql, Object[] params) {
		ResultSet rsResultSet=null;
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			if(params!=null && params.length!=0)
			{
				for(int i=0;i<params.length;i++)
				{
					preparedStatement.setObject(i+1, params[i]);
				}
			}
			rsResultSet=preparedStatement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rsResultSet;
	}

	@Override
	public int op(String sql, Object[] params) {
		int count=-1;
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			if(params!=null && params.length!=0)
			{
				for(int i=0;i<params.length;i++)
				{
					preparedStatement.setObject(i+1, params[i]);
				}
			}
			count=preparedStatement.executeUpdate();
			ResultSet rs =preparedStatement.getGeneratedKeys();
			if(rs.next() && count>0)
			{
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}

}
