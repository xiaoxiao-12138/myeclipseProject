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

import com.shop.dao.PurchaseOrderDao;
import com.shop.entity.PurchaseOrder;

public class SelectPurchaseOrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderId = request.getParameter("orderId");
		System.out.println("orderId"+orderId);
		List<PurchaseOrder> purchaseOrders = new ArrayList<PurchaseOrder>();
		
		/*try {
			purchaseOrders = PurchaseOrderDao.SelectAllPurchase();
			System.out.println(purchaseOrders);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		
		if(orderId == null || orderId == ""){
			try {
				List<PurchaseOrder> purchaseOrders1 = PurchaseOrderDao.SelectAllPurchase();
				for(int i = 0; i < purchaseOrders1.size(); i++){
    				List<PurchaseOrder> purchaseOrders2 = PurchaseOrderDao.queryPurchaseOrder(purchaseOrders1.get(i).getOrderId());
    				for(int j = 0; j < purchaseOrders2.size(); j++){
    					List<PurchaseOrder> purchaseOrders3 = PurchaseOrderDao.queryPurchaseOrder(purchaseOrders2.get(j).getOrderId());
    					for(int k = 0; k < purchaseOrders3.size(); k++){
    						purchaseOrders.add(purchaseOrders3.get(k));
    					}
    				}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			try {
				purchaseOrders = PurchaseOrderDao.queryPurchaseOrder(orderId);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		request.getSession().setAttribute("purchaseOrders", purchaseOrders);
		request.getRequestDispatcher("selectPurchaseOrder.jsp").forward(request, response);
 	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
