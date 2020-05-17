package com.shop.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.dao.ProductDao;
import com.shop.dao.UserDao;
import com.shop.entity.Product;
import com.shop.entity.User;

public class AddUserServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
		 * Constructor of the object.
		 */
	public AddUserServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		
		//封装product对象
		String userId = request.getParameter("username");
		String userPassword = request.getParameter("password");
		System.out.println(userId + " " + userPassword);
		/*String gender = request.getParameter("gender");
		String provice = request.getParameter("province");
		String city = request.getParameter("city");
		String adress = request.getParameter("adress");
		User user = new User(userId, password,gender, provice, city, adress);*/
		User user = new User(userId, userPassword);
		String succ = "  <h3>注册完成！已跳转回登录页</h3> ";
		String fail = "  <h3>用户已存在</h3> ";
		//将商品信息保存到数据库中
		try {
			if(UserDao.isExist(userId)){
				UserDao.addUser(user);
				request.setAttribute("tips",succ);
				response.sendRedirect("login.html");
				//request.getRequestDispatcher("login.html").forward(request,response);
			}else{
				request.setAttribute("tips",fail);
				request.getRequestDispatcher("addUser.jsp").forward(request,response);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
		 * The doPost method of the servlet. <br>
		 *
		 * This method is called when a form has its tag value method equals to post.
		 * 
		 * @param request the request send by the client to the server
		 * @param response the response send by the server to the client
		 * @throws ServletException if an error occurred
		 * @throws IOException if an error occurred
		 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

	/**
		 * Initialization of the servlet. <br>
		 *
		 * @throws ServletException if an error occurs
		 */
	public void init() throws ServletException {
		// Put your code here
	}

}
