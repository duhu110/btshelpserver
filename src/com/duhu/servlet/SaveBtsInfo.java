package com.duhu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.duhu.dao.BtsinfoDao;
import com.duhu.dao.impl.BtsinfoDaoImpl;
import com.duhu.util.ConnetionFactory;
import com.mysql.jdbc.Connection;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class SaveBtsInfo
 */
@WebServlet("/SaveBtsInfo.do")
public class SaveBtsInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String result;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SaveBtsInfo() {
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
		System.out.println("获取从客户端过来的data" + data);
		JSONObject object = JSONObject.fromObject(data);
		int btsid = object.getInt("btsid");
		JSONArray array = object.getJSONArray("list");
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < array.size(); i++) {
			list.add((String) array.get(i));
		}
		Connection connection = null;
		if (dopanduan(btsid)) {

			try {
				connection = ConnetionFactory.getInstance().makeConnection();
				connection.setAutoCommit(false);

				BtsinfoDao btsinfoDao = new BtsinfoDaoImpl();
				btsinfoDao.delete(connection, btsid);
				connection.commit();
				System.out.println("info表中有数据，执行删除");
			} catch (Exception e) {
				try {
					connection.rollback();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}

		}
		try {
			connection = ConnetionFactory.getInstance().makeConnection();
			connection.setAutoCommit(false);

			BtsinfoDao btsinfoDao = new BtsinfoDaoImpl();
			btsinfoDao.update(connection, btsid, list);
			System.out.println("info表中数据执行增加");
			connection.commit();
			result="success";
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
				result="unsuccess";
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

	private boolean dopanduan(int btsid) {
		boolean result = false;
		Connection connection = null;
		try {
			connection = ConnetionFactory.getInstance().makeConnection();
			connection.setAutoCommit(false);

			BtsinfoDao btsinfoDao = new BtsinfoDaoImpl();
			if (btsinfoDao.panduan(connection, btsid)) {
				System.out.println("BTSinfoCHECKEND");
				result = true;
			} else {
				System.out.println("BTSinfoUNCHECKED");
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
