package com.duhu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.duhu.dao.BtsCheckDao;
import com.duhu.dao.impl.BtsCheckDaoImpl;
import com.duhu.util.ConnetionFactory;
import com.mysql.jdbc.Connection;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class BtsCheck
 */
@WebServlet("/BtsCheck.do")
public class BtsCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int btsid;
	private String result;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BtsCheck() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String data = request.getParameter("data");
		System.out.println("btscheck shoudaoshuju"+data);
		JSONObject object = JSONObject.fromObject(data);
		btsid = (int) object.get("btsid");
			Connection connection = null;
			try {
				connection = ConnetionFactory.getInstance().makeConnection();
				connection.setAutoCommit(false);

				BtsCheckDao btsCheckDao = new BtsCheckDaoImpl();
				com.duhu.entity.BtsCheck btsCheck = new com.duhu.entity.BtsCheck();
				btsCheck.setBtsid(btsid);
				btsCheck.setChecktime(object.getString("setChecktime"));
				btsCheck.setPeople(object.getString("setPeople"));
				btsCheck.setShebei_banka(object.getString("setShebei_banka"));
				btsCheck.setShebei_biaoshi(object.getString("setShebei_biaoshi"));
				btsCheck.setShebei_zouxian(object.getString("setShebei_zouxian"));
				btsCheck.setShebei_jietou(object.getString("setShebei_jietou"));
				btsCheck.setShebei_gaopin(object.getString("setShebei_gaopin"));
				btsCheck.setShebei_fengshan(object.getString("setShebei_fengshan"));
				btsCheck.setShebei_gaojing(object.getString("setShebei_gaojing"));
				btsCheck.setShebei_qingjie(object.getString("setShebei_qingjie"));
				btsCheck.setJichu_zhanzhi(object.getString("setJichu_zhanzhi"));
				btsCheck.setJichu_tiankui(object.getString("setJichu_tiankui"));
				btsCheck.setJichu_shigong(object.getString("setJichu_shigong"));
				btsCheck.setHuanjing_wendu(object.getString("setHuanjing_wendu"));
				btsCheck.setHuanjing_shidu(object.getString("setHuanjing_shidu"));
				btsCheck.setHuanjing_qingjie(object.getString("setHuanjing_qingjie"));
				btsCheck.setHuanjing_xiaofang(object.getString("setHuanjing_xiaofang"));
				btsCheck.setHuanjing_qita(object.getString("setHuanjing_qita"));
				btsCheck.setTiankui_xian(object.getString("setTiankui_xian"));
				btsCheck.setTiankui_mifeng(object.getString("setTiankui_mifeng"));
				btsCheck.setTiankui_jiedi(object.getString("setTiankui_jiedi"));
				btsCheck.setTiankui_wanqu(object.getString("setTiankui_wanqu"));
				btsCheck.setTieta(object.getString("setTieta"));
				btsCheck.setKongtiao_gaojing(object.getString("setKongtiao_gaojing"));
				btsCheck.setKongtiao_yunxing(object.getString("setKongtiao_yunxing"));
				btsCheck.setKongtiao_qingjie(object.getString("setKongtiao_qingjie"));
				btsCheck.setDonglixitong(object.getString("setDonglixitong"));
				btsCheck.setFangleijiedi(object.getString("setFangleijiedi"));
				btsCheck.setChuanshu_banka(object.getString("setChuanshu_banka"));
				btsCheck.setChuanshu_gaojing(object.getString("setChuanshu_gaojing"));
				btsCheck.setChuanshu_jietou(object.getString("setChuanshu_jietou"));
				
				btsCheckDao.save(connection, btsCheck);

				connection.commit();
				result="success";

			} catch (Exception e) {
				try {
					connection.rollback();
				} catch (Exception e2) {
					e2.printStackTrace();
					result="savefaild";
				}
			}
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter printWriter = null;
		try {
			printWriter = response.getWriter();
			printWriter.print(result);
		} finally{
			if (printWriter!=null) {
				printWriter.close();
			}
		}
		

	}

}
