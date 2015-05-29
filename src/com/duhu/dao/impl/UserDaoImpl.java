package com.duhu.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.duhu.dao.UserDao;
import com.duhu.entity.User;
import com.mysql.jdbc.Connection;

public class UserDaoImpl implements UserDao {

	@Override
	public void save(Connection connection, User user) throws SQLException {
		PreparedStatement ps = connection
				.prepareCall("INSERT INTO tbl_user(name,password) VALUES(?,?)");
		ps.setString(1, user.getName());
		ps.setString(2, user.getPassword());
		ps.execute();
	}

	@Override
	public void update(Connection connection,User user)
			throws SQLException {
		String updatesql = "UPDATE tbl_user SET password = ? WHERE name = ?";
		PreparedStatement ps = connection.prepareCall(updatesql);
		ps.setString(1, user.getPassword());
		ps.setString(2, user.getName());
		ps.execute();

	}

	@Override
	public void delete(Connection connection, User user) throws SQLException {
		String deletesql = "DELETE FROM tbl_user WHERE name = ?";
		PreparedStatement ps = connection.prepareCall(deletesql);
		ps.setString(1, user.getName());
		ps.execute();
	}

	@Override
	public boolean panduan(Connection connection, User user)
			throws SQLException {
		boolean result = false;
		String sqlString = "SELECT * FROM tbl_user WHERE name=? AND password=?";
		PreparedStatement preparedStatement = connection.prepareCall(sqlString);
		preparedStatement.setString(1, user.getName());
		preparedStatement.setString(2, user.getPassword());
		//preparedStatement.execute();
		ResultSet rSet = preparedStatement.executeQuery();
		if (rSet.next()) {
			System.out.println("PASS");
            result=true;
		} else {
			System.out.println("FAIl");
            result=false;
		}
		return result;

	}

}
