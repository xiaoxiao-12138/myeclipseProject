package com.shop.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.dao.ProductDao;
import com.shop.entity.Product;

public class AdjustProductQuantyServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		List<String> productIds = (List<String>) request.getSession().getAttribute("productIds");	//获得各个商品ID
		String[] productPrices = request.getParameterValues("productPrice");	//获得各个商品单价
		String[] quantys = request.getParameterValues("quanty");				//获得各个商品数量 
		List<Product> Products = new ArrayList<Product>();						//创建一个Product数组
		
		for(int i = 0; i < productIds.size(); i++){								//封装Product对象
			Products.add(new Product(productIds.get(i), Double.parseDouble(productPrices[i]), Integer.parseInt(quantys[i])));
		}
		
		
		if(Products != null){
			for(int i = 0; i < Products.size(); i++){
				try {
					ProductDao.saveProduct(Products.get(i));				//调用方法保存每个Product对象
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			
			//重新刷新商品信息
			request.getRequestDispatcher("UserSelectServlet?userId="+request.getSession().getAttribute("userId")+"&userPassword="+request.getSession().getAttribute("userPassword")).forward(request, response);
			
			//out.print("<script>alert(\"保存成功!\");</script>");
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}
