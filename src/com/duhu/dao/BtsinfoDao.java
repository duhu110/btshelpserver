package com.duhu.dao;

import java.sql.SQLException;
import java.util.List;

import com.mysql.jdbc.Connection;

public interface BtsinfoDao {
	public List<String> slect(Connection connection,int btsid) throws SQLException;
	public void update(Connection connection,int btsid,List<String> list)throws SQLException;
	public void delete(Connection connection,int btsid)throws SQLException;
	public boolean panduan(Connection connection,int btsid)throws SQLException;
}
