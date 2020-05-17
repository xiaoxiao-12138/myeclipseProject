package com.shop.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.dao.ProductDao;
import com.shop.dao.PurchaseCarDao;
import com.shop.dao.PurchaseOrderDao;
import com.shop.entity.Product;
import com.shop.entity.PurchaseCar;
import com.shop.entity.PurchaseOrder;

public class ProductManageServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		List<PurchaseCar> purchasecars=(List<PurchaseCar>) request.getSession().getAttribute("productcars");
		List<Integer> List_Quanty = new ArrayList<Integer>();	
		for (PurchaseCar purchaseCar : purchasecars) {
			List_Quanty.add(purchaseCar.getQuanty());
		}
		
		Vector<String> vc = new Vector<String>();
		for (PurchaseCar purchaseCar : purchasecars) {
			vc.add(purchaseCar.getProductId());
		}
		String[] productIds = new String[vc.size()];
		for(int i=0;i<vc.size();i++){
			productIds[i]=vc.get(i);
		}
		
		List<Product> List_Products = new ArrayList<Product>();					//存放购买的商品信息product对象
		for(int i = 0; i < productIds.length; i++){
			try {
				Product product = ProductDao.isEnoughQuanty(productIds[i], (int)List_Quanty.get(i));
				if(product != null){
					List_Products.add(product);
				}
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}
		}
	
		if(List_Products.size() == productIds.length){	//判断不为null的product对象与用户购买的商品数量是否相等
			String userId = (String) request.getSession().getAttribute("userId");
			try {
				PurchaseCarDao.delAllCar(userId);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			List<PurchaseCar> productcars = null;
			try {
				productcars = PurchaseCarDao.CarProduct(userId);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			double add = 0;
			for (PurchaseCar purchaseCar : productcars) {
				add += purchaseCar.getProductPrice()*purchaseCar.getQuanty();
			}
			request.getSession().setAttribute("sum", add);	
			request.getSession().setAttribute("productcars", productcars);
			
			double sum = 0;
			String orderId = null;
			out.print("<h3>交易成功</h3><br>");
			response.getWriter().print("<h3>你所订购商品:</h3><br>");
			out.print("<table border='1' cellpadding='5'>");
			out.print("<tr>");
			out.print("<th>序号</th>");
			out.print("<th>商品编号</th>");
			out.print("<th>商品名称</th>");
			out.print("<th>商品数量</th>");
			out.print("<th>商品价格</th>");
			out.print("</tr>");
			
			orderId = PurchaseOrder.getOrderIdByUUId();
			for(int i = 0; i < productIds.length; i++){
				try {
					ProductDao.updateProduct(productIds[i], (int)List_Quanty.get(i));			//更新库存数量
					PurchaseOrderDao.updatePurchaseOrder(orderId,userId,List_Products.get(i),(int)List_Quanty.get(i));	//更新订单表
					double Price = (int)List_Quanty.get(i) * List_Products.get(i).getProductPrice();
					sum += Price;
					
					out.print("<tr>");
					out.print("<td>"+(i+1)+"</td>");
					out.print("<td>"+List_Products.get(i).getProductId()+"</td>");
					out.print("<td>"+List_Products.get(i).getProductName()+"</td>");
					out.print("<td>"+(int)List_Quanty.get(i)+"</td>");
					out.print("<td>"+Price+"</td>");
					out.print("</tr>");
					
				} catch (NumberFormatException | SQLException e) {
					e.printStackTrace();
				}				
			}
			out.print("</table><br>");
			out.print("你一共消费: " + sum + " 元<br>");
			out.print("你的订单号: " + orderId);
			out.print("<br><a href='orderProduct.jsp'>返回</a>");
		}else {
			out.print("购买失败：由于某些商品库存不足，请重新选购！<br>");
			out.print("<a href='success.jsp'>首页</a>");
			out.print("<a href='orderProduct.jsp'>返回</a>");
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
