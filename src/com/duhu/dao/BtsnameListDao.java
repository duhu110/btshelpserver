package com.duhu.dao;

import java.sql.SQLException;
import java.util.List;

import com.duhu.entity.Btsname;
import com.mysql.jdbc.Connection;

public interface BtsnameListDao {
	public List<Btsname> slect(Connection connection) throws SQLException;
}
