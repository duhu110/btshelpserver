package com.duhu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.duhu.dao.BtsnameListDao;
import com.duhu.dao.impl.BtsnameListDaoImpl;
import com.duhu.util.ConnetionFactory;
import com.mysql.jdbc.Connection;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class GetBtsname
 */
@WebServlet("/GetBtsname.do")
public class GetBtsname extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int btsid;
	private String btsname;
	BtsnameListDao btsnameListDao =  new BtsnameListDaoImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetBtsname() {
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
		this.btsid = (int) object.get("btsid");
		System.out.println("查询基站名字ID是："+btsid);
		Connection connection = null;
		try {
			connection = ConnetionFactory.getInstance().makeConnection();
			connection.setAutoCommit(false);
			btsname = btsnameListDao.slect(connection, btsid);
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
				System.out.println("DBERROR");
			}
		}
		JSONObject object2 = new JSONObject();
		object2.put("BTSNAME", btsname);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter printWriter = null;
		try {
			printWriter = response.getWriter();
			printWriter.print(object2);
			System.out.println("传过去name"+btsname);
		} finally {
			if (printWriter != null) {
				printWriter.close();
			}
		}
	}

}
