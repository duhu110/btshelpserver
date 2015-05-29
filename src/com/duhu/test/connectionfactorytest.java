package com.duhu.test;

import com.duhu.util.ConnetionFactory;
import com.mysql.jdbc.Connection;

public class connectionfactorytest {

	public static void main(String[] args) throws Exception {
		ConnetionFactory cFactory = ConnetionFactory.getInstance();
		Connection connection = cFactory.makeConnection();
		System.out.println(connection.getAutoCommit());
	}

}
