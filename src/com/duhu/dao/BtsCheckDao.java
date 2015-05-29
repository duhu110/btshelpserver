package com.duhu.dao;

import java.sql.SQLException;
import com.duhu.entity.BtsCheck;
import com.mysql.jdbc.Connection;


public interface BtsCheckDao {
	
	public void save(Connection connection, BtsCheck btsCheck) throws SQLException;
	public BtsCheck selectlast(Connection connection, int btsid) throws SQLException;
}
