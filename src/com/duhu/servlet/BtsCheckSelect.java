package com.duhu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.duhu.dao.BtsCheckDao;
import com.duhu.dao.impl.BtsCheckDaoImpl;
import com.duhu.util.ConnetionFactory;
import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class BtsCheckSelect
 */
@WebServlet("/BtsCheckSelect")
public class BtsCheckSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int btsid;
	//private String result;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BtsCheckSelect() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String data = request.getParameter("data");
		System.out.println("BtsCheckSelectdatafromapp"+data);
		JSONObject object = JSONObject.fromObject(data);
		JSONObject object2 = new JSONObject();
		btsid = (int) object.get("btsid");
			Connection connection = null;
			try {
				connection = ConnetionFactory.getInstance().makeConnection();
				connection.setAutoCommit(false);

				BtsCheckDao btsCheckDao = new BtsCheckDaoImpl();
				com.duhu.entity.BtsCheck btsCheck = new com.duhu.entity.BtsCheck();

				btsCheck = btsCheckDao.selectlast(connection, btsid);
				connection.commit();
				
			    //	result="success";
				//JSONObject object2 = new JSONObject();
				object2.put("getChecktime", btsCheck.getChecktime());
				object2.put("getPeople", btsCheck.getPeople());
				object2.put("getShebei_biaoshi", btsCheck.getShebei_biaoshi());
				object2.put("getShebei_banka", btsCheck.getShebei_banka());
				object2.put("getShebei_zouxian", btsCheck.getShebei_zouxian());
				object2.put("getShebei_jietou", btsCheck.getShebei_jietou());
				object2.put("getShebei_gaopin", btsCheck.getShebei_gaopin());
				object2.put("getShebei_fengshan", btsCheck.getShebei_fengshan());
				object2.put("getShebei_gaojing", btsCheck.getShebei_gaojing());
				object2.put("getShebei_qingjie", btsCheck.getShebei_qingjie());
				object2.put("getJichu_zhanzhi", btsCheck.getJichu_zhanzhi());
				object2.put("getJichu_tiankui", btsCheck.getJichu_tiankui());
				object2.put("getJichu_shigong", btsCheck.getJichu_shigong());
				object2.put("getHuanjing_wendu", btsCheck.getHuanjing_wendu());
				object2.put("getHuanjing_shidu", btsCheck.getHuanjing_shidu());
				object2.put("getHuanjing_qingjie", btsCheck.getHuanjing_qingjie());
				object2.put("getHuanjing_xiaofang", btsCheck.getHuanjing_xiaofang());
				object2.put("getHuanjing_qita", btsCheck.getHuanjing_qita());
				object2.put("getTiankui_xian", btsCheck.getTiankui_xian());
				object2.put("getTiankui_mifeng", btsCheck.getTiankui_mifeng());
				object2.put("getTiankui_jiedi", btsCheck.getTiankui_jiedi());
				object2.put("getTiankui_wanqu", btsCheck.getTiankui_wanqu());
				object2.put("getTieta", btsCheck.getTieta());
				object2.put("getKongtiao_gaojing", btsCheck.getKongtiao_gaojing());
				object2.put("getKongtiao_yunxing", btsCheck.getKongtiao_yunxing());
				object2.put("getKongtiao_qingjie", btsCheck.getKongtiao_qingjie());
				object2.put("getDonglixitong", btsCheck.getDonglixitong());
				object2.put("getFangleijiedi", btsCheck.getFangleijiedi());
				object2.put("getChuanshu_banka", btsCheck.getChuanshu_banka());
				object2.put("getChuanshu_gaojing", btsCheck.getChuanshu_gaojing());
				object2.put("getChuanshu_jietou", btsCheck.getChuanshu_jietou());
				System.out.println("selectChuanshu_jietou"+btsCheck.getChuanshu_jietou());
				
			} catch (Exception e) {
				try {
					connection.rollback();
				} catch (Exception e2) {
					e2.printStackTrace();
				//	result="savefaild";
				}
			}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter printWriter = null;
		try {
			printWriter = response.getWriter();
			printWriter.print(object2.toString());
		} finally{
			if (printWriter!=null) {
				printWriter.close();
			}
		}
		

	}

}
