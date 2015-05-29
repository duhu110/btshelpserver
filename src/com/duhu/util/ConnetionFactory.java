package com.duhu.util;

import java.io.InputStream;
import java.sql.DriverManager;
import java.util.Properties;

import com.mysql.jdbc.Connection;

public class ConnetionFactory {
	private static String driverString;
	private static String dburlString;
	private static String user;
	private static String password;
	private static final ConnetionFactory factory = new ConnetionFactory();
	private Connection connection;
	static {
		Properties properties = new Properties();
		try {
			InputStream inputStream = ConnetionFactory.class.getClassLoader()
					.getResourceAsStream("dbconfig.properties");
			properties.load(inputStream);
		} catch (Exception e) {
			System.out.println("连接数据库错误");
		}
		driverString = properties.getProperty("driver");
		dburlString = properties.getProperty("dburl");
		user = properties.getProperty("user");
		password = properties.getProperty("password");
		
	}
	private ConnetionFactory() {
		
	}
	public static ConnetionFactory getInstance(){
		return factory;
	}
	public Connection makeConnection() {
		try {
			Class.forName(driverString);
			connection = (Connection) DriverManager.getConnection(dburlString, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
}
