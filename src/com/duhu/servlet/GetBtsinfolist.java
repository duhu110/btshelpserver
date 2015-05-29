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
 * Servlet implementation class GetBtsinfolist
 */
@WebServlet("/GetBtsinfolist.do")
public class GetBtsinfolist extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BtsinfoDao btsinfoDao = new BtsinfoDaoImpl();
	private int btsid;
	List<String> strings = new ArrayList<String>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetBtsinfolist() {
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
		this.btsid = (int) object.get("btsid");

		Connection connection = null;
		try {
			connection = ConnetionFactory.getInstance().makeConnection();
			connection.setAutoCommit(false);
			strings = btsinfoDao.slect(connection, btsid);
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
				System.out.println("DBERROR");
			}
		}
		JSONArray array = JSONArray.fromObject(strings);

		System.out.println(array.toString() + "把这个传过去");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter printWriter = null;
		try {
			printWriter = response.getWriter();
			printWriter.print(array.toString());
		} finally {
			if (printWriter != null) {
				printWriter.close();
			}
		}

	}

}
