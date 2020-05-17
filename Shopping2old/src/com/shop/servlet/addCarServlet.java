package com.shop.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.dao.ProductDao;
import com.shop.dao.PurchaseCarDao;
import com.shop.entity.Product;
import com.shop.entity.PurchaseCar;

public class addCarServlet extends HttpServlet {
     
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String userId = (String) request.getSession().getAttribute("userId"); 
		String productName = request.getParameter("name");
		String productId = request.getParameter("id");
		double productPrice = Double.parseDouble(request.getParameter("price"));
        int quanty = 1;
        PurchaseCar purchasecar = new PurchaseCar(userId, productId, productName, productPrice, quanty);
        try {
        	if(PurchaseCarDao.isExist(userId, productId)){
        		PurchaseCarDao.addCarQuanty(userId, productId);
        	}
        	else{
        		PurchaseCarDao.addCarProduct(purchasecar);
        	}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
		List<PurchaseCar> productcars = null;
		try {
			productcars = PurchaseCarDao.CarProduct(userId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		double sum = 0;
		for (PurchaseCar purchaseCar : productcars) {
			sum += purchaseCar.getProductPrice()*purchaseCar.getQuanty();
		}
		request.getSession().setAttribute("sum", sum);	
		request.getSession().setAttribute("productcars", productcars);
		response.sendRedirect("orderProduct.jsp");	//Ìø×ªÖÁ	orderProduct.jsp Ò³Ãæ
    }
 
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
 
    }
}