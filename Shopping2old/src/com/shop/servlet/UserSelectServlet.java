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

//��ѯ���ݿ����û��������Ƿ���ȷ
public class UserSelectServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userId = request.getParameter("userId");
		String userPassword = request.getParameter("userPassword");
		User user = null;
		try {
			user = UserDao.selectByUserId(userId, userPassword);					//ͨ����¼������û��������룬��ѯ���ݿ����û��������Ƿ���ȷ
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(user != null){															
			request.getSession().setAttribute("userId", user.getUserId());						//���û����ŵ�session��������
			request.getSession().setAttribute("userPassword", user.getUserPassword());			//������ŵ�session��������
			
			if(user.getUserId().equals("root")){
				request.getSession().setAttribute("userId", user.getUserId());
				request.getRequestDispatcher("ProductSelectServlet").forward(request,response);	//�������������ת��ProductSelectServlet
			}else{
				request.getRequestDispatcher("ProductSelectServlet").forward(request,response);	//��������ת��ProductSelectServlet
			}
			
		}else{
			request.getRequestDispatcher("error.jsp").forward(request,response);	//����������ת��error.jsp
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
