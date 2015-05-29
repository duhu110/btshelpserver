package com.duhu.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.duhu.dao.BtsCheckDao;
import com.duhu.entity.BtsCheck;
import com.mysql.jdbc.Connection;

public class BtsCheckDaoImpl implements BtsCheckDao {

	
	@Override
	public void save(Connection connection, BtsCheck btsCheck)
			throws SQLException {
		String sql = "INSERT INTO tbl_btscheck (btsid,checktime,people,shebei_biaoshi,shebei_banka,"
				+"shebei_zouxian,shebei_jietou,shebei_gaopin,shebei_fengshan,shebei_gaojing,"
			+"shebei_qingjie,jichu_zhanzhi,jichu_tiankui,jichu_shigong,huanjing_wendu,huanjing_shidu,"
			+"huanjing_qingjie,huanjing_xiaofang,huanjing_qita,tiankui_xian,tiankui_mifeng,"
			+"tiankui_jiedi,tiankui_wanqu,tieta,kongtiao_gaojing,kongtiao_yunxing,kongtiao_qingjie,"
			+"donglixitong,fangleijiedi,chuanshu_banka,chuanshu_gaojing,chuanshu_jietou)"
			+"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = connection.prepareCall(sql);
		ps.setInt(1, btsCheck.getBtsid());
		System.out.println("SAVEtotb_btscheck_ID" +btsCheck.getBtsid());
		ps.setString(2, btsCheck.getChecktime());
		ps.setString(3, btsCheck.getPeople());
		ps.setString(4, btsCheck.getShebei_biaoshi());
		ps.setString(5, btsCheck.getShebei_banka());
		ps.setString(6, btsCheck.getShebei_zouxian());
		ps.setString(7, btsCheck.getShebei_jietou());
		ps.setString(8, btsCheck.getShebei_gaopin());
		ps.setString(9, btsCheck.getShebei_fengshan());
		ps.setString(10, btsCheck.getShebei_gaojing());
		ps.setString(11, btsCheck.getShebei_qingjie());
		ps.setString(12, btsCheck.getJichu_zhanzhi());
		ps.setString(13, btsCheck.getJichu_tiankui());
		ps.setString(14, btsCheck.getJichu_shigong());
		ps.setString(15, btsCheck.getHuanjing_wendu());
		ps.setString(16, btsCheck.getHuanjing_shidu());
		ps.setString(17, btsCheck.getHuanjing_qingjie());
		ps.setString(18, btsCheck.getHuanjing_xiaofang());
		ps.setString(19, btsCheck.getHuanjing_qita());
		ps.setString(20, btsCheck.getTiankui_xian());
		ps.setString(21, btsCheck.getTiankui_mifeng());
		ps.setString(22, btsCheck.getTiankui_jiedi());
		ps.setString(23, btsCheck.getTiankui_wanqu());
		ps.setString(24, btsCheck.getTieta());
		ps.setString(25, btsCheck.getKongtiao_gaojing());
		ps.setString(26, btsCheck.getKongtiao_yunxing());
		ps.setString(27, btsCheck.getKongtiao_qingjie());
		ps.setString(28, btsCheck.getDonglixitong());
		ps.setString(29, btsCheck.getFangleijiedi());
		ps.setString(30, btsCheck.getChuanshu_banka());
		ps.setString(31, btsCheck.getChuanshu_gaojing());
		ps.setString(32, btsCheck.getChuanshu_jietou());
		ps.execute();
		System.out.println("SAVE_success");
		//ps.close();
	}

	@Override
	public BtsCheck selectlast(Connection connection, int btsid)
			throws SQLException {
		BtsCheck btsCheck = new BtsCheck();
		String sql = "SELECT checktime,people,shebei_biaoshi,shebei_banka,"
						+"shebei_zouxian,shebei_jietou,shebei_gaopin,shebei_fengshan,shebei_gaojing,"
						+"shebei_qingjie,jichu_zhanzhi,jichu_tiankui,jichu_shigong,huanjing_wendu,huanjing_shidu,"
						+"huanjing_qingjie,huanjing_xiaofang,huanjing_qita,tiankui_xian,tiankui_mifeng,"
						+"tiankui_jiedi,tiankui_wanqu,tieta,kongtiao_gaojing,kongtiao_yunxing,kongtiao_qingjie,"
						+"donglixitong,fangleijiedi,chuanshu_banka,chuanshu_gaojing,chuanshu_jietou  from tbl_btscheck "
						+ "where id=(select max(id) from tbl_btscheck)and btsid =?";
		PreparedStatement ps = connection.prepareCall(sql);
		ps.setInt(1, btsid);
		ps.execute();
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			btsCheck.setChecktime(rs.getString(1));
			btsCheck.setPeople(rs.getString(2));
			btsCheck.setShebei_banka(rs.getString(4));
			btsCheck.setShebei_biaoshi(rs.getString(3));
			btsCheck.setShebei_zouxian(rs.getString(5));
			btsCheck.setShebei_jietou(rs.getString(6));
			btsCheck.setShebei_gaopin(rs.getString(7));
			btsCheck.setShebei_fengshan(rs.getString(8));
			btsCheck.setShebei_gaojing(rs.getString(9));
			btsCheck.setShebei_qingjie(rs.getString(10));
			btsCheck.setJichu_zhanzhi(rs.getString(11));
			btsCheck.setJichu_tiankui(rs.getString(12));
			btsCheck.setJichu_shigong(rs.getString(13));
			btsCheck.setHuanjing_wendu(rs.getString(14));
			btsCheck.setHuanjing_shidu(rs.getString(15));
			btsCheck.setHuanjing_qingjie(rs.getString(16));
			btsCheck.setHuanjing_xiaofang(rs.getString(17));
			btsCheck.setHuanjing_qita(rs.getString(18));
			btsCheck.setTiankui_xian(rs.getString(19));
			btsCheck.setTiankui_mifeng(rs.getString(20));
			btsCheck.setTiankui_jiedi(rs.getString(21));
			btsCheck.setTiankui_wanqu(rs.getString(22));
			btsCheck.setTieta(rs.getString(23));
			btsCheck.setKongtiao_gaojing(rs.getString(24));
			btsCheck.setKongtiao_yunxing(rs.getString(25));
			btsCheck.setKongtiao_qingjie(rs.getString(26));
			btsCheck.setDonglixitong(rs.getString(27));
			btsCheck.setFangleijiedi(rs.getString(28));
			btsCheck.setChuanshu_banka(rs.getString(29));
			btsCheck.setChuanshu_gaojing(rs.getString(30));
			btsCheck.setChuanshu_jietou(rs.getString(31));
			
		}
		return btsCheck;
	}

}
