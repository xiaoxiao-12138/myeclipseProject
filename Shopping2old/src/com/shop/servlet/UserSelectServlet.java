package com.shop.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.dao.UserDao;
import com.shop.entity.User;

//查询数据库中用户、密码是否正确
public class UserSelectServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userId = request.getParameter("userId");
		String userPassword = request.getParameter("userPassword");
		User user = null;
		try {
			user = UserDao.selectByUserId(userId, userPassword);					//通过登录界面的用户名和密码，查询数据库中用户、密码是否正确
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(user != null){															
			request.getSession().setAttribute("userId", user.getUserId());						//将用户名放到session作用域中
			request.getSession().setAttribute("userPassword", user.getUserPassword());			//将密码放到session作用域中
			
			if(user.getUserId().equals("root")){
				request.getSession().setAttribute("userId", user.getUserId());
				request.getRequestDispatcher("ProductSelectServlet").forward(request,response);	//如果是卖家则跳转到ProductSelectServlet
			}else{
				request.getRequestDispatcher("ProductSelectServlet").forward(request,response);	//存在则跳转到ProductSelectServlet
			}
			
		}else{
			request.getRequestDispatcher("error.jsp").forward(request,response);	//不存在则跳转到error.jsp
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
