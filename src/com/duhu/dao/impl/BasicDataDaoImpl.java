package com.duhu.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.duhu.dao.BasicDataDao;
import com.duhu.entity.BasicData;
import com.mysql.jdbc.Connection;

public class BasicDataDaoImpl implements BasicDataDao {

	@Override
	public List<BasicData> slect(Connection connection, int btsid)
			throws SQLException {
		List<BasicData> list = new ArrayList<BasicData>();
		String sql = "SELECT jingdu,weidu,"
				+ "fangweijiao_0,fangweijiao_1,fangweijiao_2,"
				+ "tagao,xiaqingjiao_0,xiaqingjiao_1,xiaqingjiao_2,haiba  FROM tbl_basicdata WHERE btsid = ?";
		PreparedStatement ps = connection.prepareCall(sql);
		ps.setInt(1, btsid);
		ps.execute();
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			BasicData basicData = new BasicData();
			basicData.setJindu(rs.getString(1));
			basicData.setWeidu(rs.getString(2));
			basicData.setFangweijiao0(rs.getString(3));
			basicData.setFangweijiao1(rs.getString(4));
			basicData.setFangweijiao2(rs.getString(5));
			basicData.setGuagao(rs.getString(6));
			basicData.setXiaqingjiao0(rs.getString(7));
			basicData.setXiaqingjiao1(rs.getString(8));
			basicData.setXiaqingjiao2(rs.getString(9));
			basicData.setHaiba(rs.getString(10));
			list.add(basicData);
		}
		return list;
	}

	@Override
	public void update(Connection connection, BasicData basicData)
			throws SQLException {
		// TODO Auto-generated method stub
		String updatesql = "UPDATE tbl_basicdata set jingdu=?,weidu=?,"
				+ "fangweijiao_0=?,fangweijiao_1=?,fangweijiao_2=?,"
				+ "tagao=?,xiaqingjiao_0=?,xiaqingjiao_1=?,xiaqingjiao_2=?,haiba=?where btsid = ?;";
		 PreparedStatement ps = connection.prepareCall(updatesql);
		 ps.setString(1,basicData.getJindu());
		 ps.setString(2,basicData.getWeidu());
		 ps.setString(3,basicData.getFangweijiao0());
		 ps.setString(4,basicData.getFangweijiao1());
		 ps.setString(5,basicData.getFangweijiao2());
		 ps.setString(6,basicData.getGuagao());
		 ps.setString(7,basicData.getXiaqingjiao0());
		 ps.setString(8,basicData.getXiaqingjiao1());
		 ps.setString(9,basicData.getXiaqingjiao2());
		 ps.setString(10, basicData.getHaiba() );
		 ps.setInt(11,basicData.getBtsid());
		 ps.execute();
	}

	@Override
	public boolean panduan(Connection connection, BasicData basicData)
			throws SQLException {
		boolean result = false;
		String sqlString = "SELECT * FROM tbl_basicdata WHERE btsid=?";
		PreparedStatement preparedStatement = connection.prepareCall(sqlString);
		preparedStatement.setInt(1, basicData.getBtsid());
		//preparedStatement.execute();
		ResultSet rSet = preparedStatement.executeQuery();
		if (rSet.next()) {
			System.out.println("tbl_basicdata表中有记录");
            result=true;
		} else {
			System.out.println("tbl_basicdata表中没记录");
            result=false;
		}
		return result;
	}

	@Override
	public void save(Connection connection, BasicData basicData)
			throws SQLException {
		String sql = "INSERT INTO tbl_basicdata (jingdu,weidu,"
				+ "fangweijiao_0,fangweijiao_1,fangweijiao_2,"
				+ "tagao,xiaqingjiao_0,xiaqingjiao_1,xiaqingjiao_2,haiba,btsid) values(?,?,?,?,?,?,?,?,?,?,?)";
		 PreparedStatement ps = connection.prepareCall(sql);
		 ps.setString(1,basicData.getJindu());
		 ps.setString(2,basicData.getWeidu());
		 ps.setString(3,basicData.getFangweijiao0());
		 ps.setString(4,basicData.getFangweijiao1());
		 ps.setString(5,basicData.getFangweijiao2());
		 ps.setString(6,basicData.getGuagao());
		 ps.setString(7,basicData.getXiaqingjiao0());
		 ps.setString(8,basicData.getXiaqingjiao1());
		 ps.setString(9,basicData.getXiaqingjiao2());
		 ps.setString(10, basicData.getHaiba() );
		 ps.setInt(11,basicData.getBtsid());
		 ps.execute();		
	}

	
}
