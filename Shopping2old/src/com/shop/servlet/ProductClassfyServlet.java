package com.shop.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.dao.ProductDao;
import com.shop.entity.Product;

public class ProductClassfyServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		List<Product> products = null;
		String type = (String) request.getParameter("type");
		String productName = (String) request.getParameter("productName");
		PrintWriter out = response.getWriter();
		
		try {
			if(type != null){
				products = ProductDao.selectOneProduct(type);						//调用ProductDao类中selectOneProduct()方法，查询数据库中的分类商品信息
			}else if(productName != null){
				products = ProductDao.vagueSelectProduct(productName);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		request.getSession().setAttribute("products", products);			//查询将结果放在Session中			
		if(type != null){
			request.getRequestDispatcher("orderProduct.jsp").forward(request, response);	//跳转至	orderProduct.jsp 页面
		}else if(productName != null){
			request.getRequestDispatcher("MyJsp.jsp").forward(request, response);	//跳转至	orderProduct.jsp 页面
			//response.sendRedirect("ProductSelectServlet");
		}
		//request.getRequestDispatcher("orderProduct.jsp").forward(request, response);	//跳转至	orderProduct.jsp 页面
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
