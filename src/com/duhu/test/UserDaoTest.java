package com.duhu.test;

import com.duhu.util.ConnetionFactory;
import com.mysql.jdbc.Connection;

public class UserDaoTest {

	public static void main(String[] args) {
		Connection connection = null;
		try {
			connection = ConnetionFactory.getInstance().makeConnection();
			connection.setAutoCommit(false);
			
//			UserDao userDao = new UserDaoImpl();  
//			User user = new User();
//			user.setName("test3");
//			user.setPassword("111111222");
//			if (userDao.panduan(connection, user)) {
//				System.out.println("dui");
//			}else {
//				System.out.println("budui");
//			}
//			BtsCheckDao btsCheckDao = new BtsCheckDaoImpl();
//			BtsCheck btsCheck = new BtsCheck();
//			btsCheck.setBtsid("1");
//			//btsCheck.setChecktimeDate(2015-3-6 13:50:09);
//			btsCheck.setShebei("合格");
//			btsCheck.setCheckproblem("asdasdasdasdasd");
//			btsCheckDao.save(connection, btsCheck);
//			System.out.println("success");
			
//			BasicDataDao basicDataDao = new BasicDataDaoImpl();
//			BasicData basicData = new BasicData();
//			basicData.setBtsid("1");
//			basicData.setJindu("333");
//			basicDataDao.updatejindu(connection, basicData);
			
			connection.commit();
			
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
				System.out.println("error");
			}
		}

	}

}
