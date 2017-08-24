package cn.zr.dao;

import java.sql.ResultSet;

public interface BaseDao {
	/*
	 * 查找操作
	 */
	public ResultSet query(String sql,Object params[]);
	
	/*
	 * 增删改操作
	 */
	
	public int op(String sql,Object params[]);
	
}
