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

//调用ProductDao类中selectAllProduct()方法，查询数据库中的全部商品信息，再将结果放在Session中
public class ProductSelectServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = (String) request.getSession().getAttribute("userId");
		List<Product> products = null;
		List<PurchaseCar> productcars = null;
		double sum = 0;
		
		try {
			products = ProductDao.selectAllProduct();//调用ProductDao类中selectAllProduct()方法，查询数据库中的全部商品信息
			productcars = PurchaseCarDao.CarProduct(user);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for (PurchaseCar purchaseCar : productcars) {
			sum += purchaseCar.getProductPrice()*purchaseCar.getQuanty();
		}
		
		request.getSession().setAttribute("products", products);//查询将结果放在Session中
		request.getSession().setAttribute("productcars", productcars);	
		request.getSession().setAttribute("sum", sum );	
		
		if(!(request.getSession().getAttribute("userId").equals("root"))){
			
			request.getRequestDispatcher("orderProduct.jsp").forward(request, response);	//跳转至	orderProduct.jsp 页面
		}else{
			request.getRequestDispatcher("MyJsp.jsp").forward(request, response);	//跳转至	manageProduct.jsp 页面
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
