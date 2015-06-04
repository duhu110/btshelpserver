package com.duhu.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.duhu.dao.BtsnameListDao;
import com.duhu.entity.Btsname;
import com.mysql.jdbc.Connection;

public class BtsnameListDaoImpl implements BtsnameListDao {

	@Override
	public List<Btsname> slect(Connection connection)
			throws SQLException {
		List<Btsname> list = new ArrayList<Btsname>();
		String sql = "SELECT btsid,btsname  FROM tbl_basicdata";
		PreparedStatement ps = connection.prepareCall(sql);
		ps.execute();
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {  
			Btsname btsname = new Btsname();  
            btsname.setBtsid(rs.getInt(1));
            btsname.setBtsnameString(rs.getString(2));
            
            list.add(btsname);  
        }  
		return list;
	}

	@Override
	public String slect(Connection connection, int btsid) throws SQLException {
		String name = null;
		String sql = "SELECT btsname  FROM tbl_basicdata where btsid = ?";
		PreparedStatement ps = connection.prepareCall(sql);
		ps.setInt(1, btsid);
		ps.execute();
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {  
		name = rs.getString(1);}
		return name;
	}

}
