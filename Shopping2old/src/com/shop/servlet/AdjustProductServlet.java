package com.shop.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.dao.ProductDao;

public class AdjustProductServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String txt = request.getParameter("txt");
		String[] t = txt.split(" ");
		String productId = t[0]; 
		String productPrice = t[1];
		String quanty = t[2];
		
		try {
			ProductDao.adjustProductPrice(productId, productPrice);
			ProductDao.adjustProductQuanty(productId, quanty);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("ProductSelectServlet").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
