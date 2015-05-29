package com.duhu.dao;

import java.sql.SQLException;
import java.util.List;

import com.duhu.entity.BasicData;
import com.mysql.jdbc.Connection;

public interface BasicDataDao {

	public List<BasicData> slect(Connection connection,int btsid) throws SQLException;
	public void update(Connection connection,BasicData basicData)throws SQLException;
	public boolean panduan(Connection connection,BasicData basicData)throws SQLException;
	public void save(Connection connection,BasicData basicData)throws SQLException;
}
