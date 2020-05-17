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
		
		List<Product> List_Products = new ArrayList<Product>();					//��Ź������Ʒ��Ϣproduct����
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
	
		if(List_Products.size() == productIds.length){	//�жϲ�Ϊnull��product�������û��������Ʒ�����Ƿ����
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
			out.print("<h3>���׳ɹ�</h3><br>");
			response.getWriter().print("<h3>����������Ʒ:</h3><br>");
			out.print("<table border='1' cellpadding='5'>");
			out.print("<tr>");
			out.print("<th>���</th>");
			out.print("<th>��Ʒ���</th>");
			out.print("<th>��Ʒ����</th>");
			out.print("<th>��Ʒ����</th>");
			out.print("<th>��Ʒ�۸�</th>");
			out.print("</tr>");
			
			orderId = PurchaseOrder.getOrderIdByUUId();
			for(int i = 0; i < productIds.length; i++){
				try {
					ProductDao.updateProduct(productIds[i], (int)List_Quanty.get(i));			//���¿������
					PurchaseOrderDao.updatePurchaseOrder(orderId,userId,List_Products.get(i),(int)List_Quanty.get(i));	//���¶�����
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
			out.print("��һ������: " + sum + " Ԫ<br>");
			out.print("��Ķ�����: " + orderId);
			out.print("<br><a href='orderProduct.jsp'>����</a>");
		}else {
			out.print("����ʧ�ܣ�����ĳЩ��Ʒ��治�㣬������ѡ����<br>");
			out.print("<a href='success.jsp'>��ҳ</a>");
			out.print("<a href='orderProduct.jsp'>����</a>");
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
