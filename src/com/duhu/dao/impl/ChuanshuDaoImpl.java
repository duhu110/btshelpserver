package com.duhu.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.duhu.dao.ChuanshuDao;
import com.mysql.jdbc.Connection;

public class ChuanshuDaoImpl implements ChuanshuDao {

	@Override
	public List<String> slect(Connection connection, int btsid)
			throws SQLException {
		List<String> list = new ArrayList<String>();
		String sql = "SELECT btschuanshu  FROM tbl_chuanshu WHERE btsid = ?";
		PreparedStatement ps = connection.prepareCall(sql);
		ps.setInt(1, btsid);
		ps.execute();
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			list.add(rs.getString(1));
		}
		return list;
	}

	@Override
	public void update(Connection connection, int btsid, List<String> list)
			throws SQLException {
		for (int i = 0; i < list.size(); i++) {
			String updatesql = "insert into tbl_chuanshu(btsid,btschuanshu) values(?,? )";
			PreparedStatement ps = connection.prepareCall(updatesql);
			ps.setInt(1, btsid);
			ps.setString(2, list.get(i));

			ps.execute();
			System.out.println("insert数据"+list.get(i));
		}

	}

	@Override
	public void delete(Connection connection, int btsid) throws SQLException {
		String sql = "SELECT id  FROM tbl_chuanshu WHERE btsid = ?";
		PreparedStatement ps = connection.prepareCall(sql);
		ps.setInt(1, btsid);
		ps.execute();
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			String updatesql = "DELETE from tbl_chuanshu where id = ?";
			PreparedStatement ps1 = connection.prepareCall(updatesql);
			ps1.setInt(1, rs.getInt(1));
			ps1.execute();
		}

	}

	@Override
	public boolean panduan(Connection connection, int btsid)
			throws SQLException {
		boolean result = false;
		String sqlString = "SELECT * FROM tbl_chuanshu WHERE btsid=?";
		PreparedStatement preparedStatement = connection.prepareCall(sqlString);
		preparedStatement.setInt(1, btsid);
		//preparedStatement.execute();
		ResultSet rSet = preparedStatement.executeQuery();
		if (rSet.next()) {
			System.out.println("tbl_chuanshu表中有记录");
            result=true;
		} else {
			System.out.println("tbl_chuanshu表中没记录");
            result=false;
		}
		return result;
	}

}
