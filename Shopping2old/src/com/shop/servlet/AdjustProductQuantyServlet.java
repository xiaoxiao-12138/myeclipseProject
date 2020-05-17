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
		
		List<String> productIds = (List<String>) request.getSession().getAttribute("productIds");	//��ø�����ƷID
		String[] productPrices = request.getParameterValues("productPrice");	//��ø�����Ʒ����
		String[] quantys = request.getParameterValues("quanty");				//��ø�����Ʒ���� 
		List<Product> Products = new ArrayList<Product>();						//����һ��Product����
		
		for(int i = 0; i < productIds.size(); i++){								//��װProduct����
			Products.add(new Product(productIds.get(i), Double.parseDouble(productPrices[i]), Integer.parseInt(quantys[i])));
		}
		
		
		if(Products != null){
			for(int i = 0; i < Products.size(); i++){
				try {
					ProductDao.saveProduct(Products.get(i));				//���÷�������ÿ��Product����
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			
			//����ˢ����Ʒ��Ϣ
			request.getRequestDispatcher("UserSelectServlet?userId="+request.getSession().getAttribute("userId")+"&userPassword="+request.getSession().getAttribute("userPassword")).forward(request, response);
			
			//out.print("<script>alert(\"����ɹ�!\");</script>");
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}
