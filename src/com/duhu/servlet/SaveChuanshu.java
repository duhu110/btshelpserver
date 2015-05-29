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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.duhu.dao.ChuanshuDao;
import com.duhu.dao.impl.ChuanshuDaoImpl;
import com.duhu.util.ConnetionFactory;
import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class SaveChuanshu
 */
@WebServlet("/SaveChuanshu.do")
public class SaveChuanshu extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String result; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveChuanshu() {
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

				ChuanshuDao chuanshuDao = new ChuanshuDaoImpl();
				chuanshuDao.delete(connection, btsid);
				connection.commit();
				System.out.println("btschuanshu表中有数据，执行删除");
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

			ChuanshuDao chuanshuDao = new ChuanshuDaoImpl();
			chuanshuDao.update(connection, btsid, list);
			System.out.println("btschuanshu表中数据执行增加");
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

			ChuanshuDao chuanshuDao = new ChuanshuDaoImpl();
			if (chuanshuDao.panduan(connection, btsid)) {
				System.out.println("btschuanshuCHECKEND");
				result = true;
			} else {
				System.out.println("btschuanshuUNCHECKED");
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
