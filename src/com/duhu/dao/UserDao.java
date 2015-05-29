package com.duhu.dao;

import java.sql.SQLException;
import com.duhu.entity.User;
import com.mysql.jdbc.Connection;


public interface UserDao {
	
	public void save(Connection connection, User user) throws SQLException;
	public void update(Connection connection, User user)throws SQLException;
	public void delete(Connection connection,User user)throws SQLException;
	public boolean panduan(Connection connection,User user)throws SQLException;

}
