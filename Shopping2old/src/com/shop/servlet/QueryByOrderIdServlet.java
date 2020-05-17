package com.shop.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.dao.PurchaseOrderDao;
import com.shop.entity.PurchaseOrder;

//通过订单号orderId查询商品信息
public class QueryByOrderIdServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String orderId = request.getParameter("orderId");
		List<PurchaseOrder> purchaseOrders = null;
		try {
			purchaseOrders = PurchaseOrderDao.queryPurchaseOrder(orderId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(purchaseOrders.size() > 0 ){
			request.getSession().setAttribute("purchaseOrders", purchaseOrders);
			request.getRequestDispatcher("QuerySuccess.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("QueryFailure.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
