package com.shop.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.dao.ProductDao;
import com.shop.entity.Product;

public class AddProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//封装product对象
		String productId = request.getParameter("productId");
		String productName = request.getParameter("productName");
		String productPrice = request.getParameter("productPrice");
		String quanty = request.getParameter("quanty");
		String type = request.getParameter("type");
		Product product = new Product(productId,productName,Double.parseDouble(productPrice),Integer.parseInt(quanty),type);
		
		//将商品信息保存到数据库中
		try {
			ProductDao.addProduct(product);
			request.getRequestDispatcher("UserSelectServlet?userId="+request.getSession().getAttribute("userId")+"&userPassword="+request.getSession().getAttribute("userPassword")).forward(request, response);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
