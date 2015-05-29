package com.duhu.jdbc;

import java.sql.DriverManager;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class JDBCTest {
	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/btshelper_dp", "root",
					"123456");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public static void insert() {
		Connection connection = getConnection();
		try {
			String sql = "INSERT INTO tbl_user(name,password)"
					+ "VALUES('TEST3','111111')";
			Statement statement = (Statement) connection.createStatement();
			int count = statement.executeUpdate(sql);
			System.out.println("插入" + count + "条记录");
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public static void update() {
		Connection connection = getConnection();
		try {
			String sql = "UPDATE  tbl_user SET password = '222222' WHERE name = 'TEST3'";
			Statement statement = (Statement) connection.createStatement();
			int count = statement.executeUpdate(sql);
			System.out.println("更新" + count + "条记录");
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
public static void delete() {
	Connection connection = getConnection();
	try {
		String sql = "DELETE FROM tbl_user WHERE name = 'TEST3'";
		Statement statement = (Statement) connection.createStatement();
		int count = statement.executeUpdate(sql);
		System.out.println("删除" + count + "条记录");
		connection.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
}
	public static void main(String[] args) {
		//insert();
		//update();
		delete();
	}
}
