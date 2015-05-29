package com.duhu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.duhu.dao.UserDao;
import com.duhu.dao.impl.UserDaoImpl;
import com.duhu.entity.User;
import com.duhu.util.ConnetionFactory;
import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login.do")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String loginnameString;
	private String loginpasswordString;

	public Login() {

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("---get---");
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("---post---");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		loginnameString = request.getParameter("LoginName");
		loginpasswordString = request.getParameter("LoginPassword");
		System.out.println(loginnameString);
		System.out.println(loginpasswordString);
		PrintWriter outPrintWriter = null;
		try {
			outPrintWriter = response.getWriter();
			if (dopanduan()) {
				outPrintWriter.print("chenggong");
			} else {
				outPrintWriter.print("buchenggong");
			}
		} finally {
			if (outPrintWriter != null) {
				outPrintWriter.close();
			}
		}

	}

	private boolean dopanduan() {
		boolean result = false;
		Connection connection = null;
		try {
			connection = ConnetionFactory.getInstance().makeConnection();
			connection.setAutoCommit(false);

			UserDao userDao = new UserDaoImpl();
			User user = new User();
			user.setName(loginnameString);
			user.setPassword(loginpasswordString);
			if (userDao.panduan(connection, user)) {
				System.out.println("dui");
				result = true;
			} else {
				System.out.println("budui");
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
