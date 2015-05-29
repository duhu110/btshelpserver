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

import com.duhu.dao.BasicDataDao;
import com.duhu.dao.impl.BasicDataDaoImpl;
import com.duhu.util.ConnetionFactory;
import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class BasicData
 */
@WebServlet("/BD.do")
public class BasicData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BasicDataDao basicDataDao = new BasicDataDaoImpl();
	private int btsid;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BasicData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String data = request.getParameter("data");
		System.out.println("获取从客户端过来的data"+data);
		JSONObject object = JSONObject.fromObject(data);
		this.btsid = (int) object.get("btsid");

		java.util.List<com.duhu.entity.BasicData> basicDatas = new ArrayList<com.duhu.entity.BasicData>();
		basicDatas = this.selectlist(btsid);
		
		
        JSONArray array = JSONArray.fromObject(basicDatas);
		
        System.out.println(array.toString()+"把这个传过去");
        response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter printWriter = null;
		try {
			printWriter = response.getWriter();
			printWriter.print(array.toString());
		} finally{
			if (printWriter!=null) {
				printWriter.close();
			}
		}
	
	
	}
	private List<com.duhu.entity.BasicData> selectlist(int BTSID) {
		List<com.duhu.entity.BasicData> list = new ArrayList<com.duhu.entity.BasicData>();
		Connection connection = null;
		try {
			connection = ConnetionFactory.getInstance().makeConnection();
			connection.setAutoCommit(false);
			list = basicDataDao.slect(connection,BTSID);
			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
				System.out.println("DBERROR");
			}
		}
		return list;
	}
}
