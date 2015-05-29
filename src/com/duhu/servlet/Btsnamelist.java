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

import com.duhu.dao.BtsnameListDao;
import com.duhu.dao.impl.BtsnameListDaoImpl;
import com.duhu.entity.Btsname;
import com.duhu.util.ConnetionFactory;
import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class Btsnamelist
 */
@WebServlet("/Btsnamelist.do")
public class Btsnamelist extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BtsnameListDao btsnameListDao = new BtsnameListDaoImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Btsnamelist() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		java.util.List<Btsname> btsnames = new ArrayList<Btsname>();
		btsnames = this.selectlist();
        JSONArray array = JSONArray.fromObject(btsnames);
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	private List<Btsname> selectlist() {
		List<Btsname> list = new ArrayList<Btsname>();
		Connection connection = null;
		try {
			connection = ConnetionFactory.getInstance().makeConnection();
			connection.setAutoCommit(false);
			list = btsnameListDao.slect(connection);
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
