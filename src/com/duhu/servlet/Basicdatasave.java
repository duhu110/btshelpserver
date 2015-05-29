package com.duhu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.duhu.dao.BasicDataDao;
import com.duhu.dao.impl.BasicDataDaoImpl;
import com.duhu.entity.BasicData;
import com.duhu.util.ConnetionFactory;
import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class Basicdatasave
 */
@WebServlet("/BDSAVE.DO")
public class Basicdatasave extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int btsid;
	private String result; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Basicdatasave() {
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
		request.setCharacterEncoding("UTF-8");
		String data = request.getParameter("data");
		System.out.println("获取从客户端过来的data"+data);
		JSONObject object = JSONObject.fromObject(data);
		btsid = (int) object.get("btsid");
		String jindu = object.getString("jindu");
		String weidu = object.getString("weidu");
		String fangweijiao0 = object.getString("fangweijiao0");
		String fangweijiao1 = object.getString("fangweijiao1");
		String fangweijiao2 = object.getString("fangweijiao2");
		String guagao = object.getString("guagao");
		String xiaqingjiao0 = object.getString("xiaqingjiao0");
		String xiaqingjiao1 = object.getString("xiaqingjiao1");
		String xiaqingjiao2 = object.getString("xiaqingjiao2");
		String haiba = object.getString("haiba");
		if (dopanduan()) {
			Connection connection = null;
			try {
				connection = ConnetionFactory.getInstance().makeConnection();
				connection.setAutoCommit(false);

				BasicDataDao basicDataDao = new BasicDataDaoImpl();
				BasicData basicData = new BasicData();
				basicData.setBtsid(btsid);
				basicData.setJindu(jindu);
				basicData.setWeidu(weidu);
				basicData.setFangweijiao0(fangweijiao0);
				basicData.setFangweijiao1(fangweijiao1);
				basicData.setFangweijiao2(fangweijiao2);
				basicData.setGuagao(guagao);
				basicData.setXiaqingjiao0(xiaqingjiao0);
				basicData.setXiaqingjiao1(xiaqingjiao1);
				basicData.setXiaqingjiao2(xiaqingjiao2);
				basicData.setHaiba(haiba);
				basicDataDao.update(connection, basicData);

				connection.commit();
				result="success";

			} catch (Exception e) {
				try {
					connection.rollback();
				} catch (Exception e2) {
					e2.printStackTrace();
					result="updatefaild";
				}
			}
		} else {
			Connection connection = null;
			try {
				connection = ConnetionFactory.getInstance().makeConnection();
				connection.setAutoCommit(false);

				BasicDataDao basicDataDao = new BasicDataDaoImpl();
				BasicData basicData = new BasicData();
				basicData.setBtsid(btsid);
				basicData.setJindu(jindu);
				basicData.setWeidu(weidu);
				basicData.setFangweijiao0(fangweijiao0);
				basicData.setFangweijiao1(fangweijiao1);
				basicData.setFangweijiao2(fangweijiao2);
				basicData.setGuagao(guagao);
				basicData.setXiaqingjiao0(xiaqingjiao0);
				basicData.setXiaqingjiao1(xiaqingjiao1);
				basicData.setXiaqingjiao2(xiaqingjiao2);
				basicData.setHaiba(haiba);
				basicDataDao.save(connection, basicData);
				connection.commit();
				result="success";
				System.out.println("basicdatasavesuccess");

			} catch (Exception e) {
				try {
					connection.rollback();
				} catch (Exception e2) {
					e2.printStackTrace();
					result="savefaild";
					System.out.println("basicdatasavefaild");
				}
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
	private boolean dopanduan() {
		boolean result = false;
		Connection connection = null;
		try {
			connection = ConnetionFactory.getInstance().makeConnection();
			connection.setAutoCommit(false);

			BasicDataDao basicDataDao = new BasicDataDaoImpl();
			BasicData basicData = new BasicData();
			basicData.setBtsid(btsid);
			if (basicDataDao.panduan(connection, basicData)) {
				System.out.println("BASICDATACKEND");
				result = true;
			} else {
				System.out.println("BASICDATAUNCHECKED");
				result = false;
			}

			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

}

