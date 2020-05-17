package com.shop.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.dao.PurchaseCarDao;
import com.shop.entity.PurchaseCar;

/**
 * Servlet implementation class addCarQuantyServlet
 */
public class addCarQuantyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addCarQuantyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String userId = (String) request.getSession().getAttribute("userId");
		String productId = request.getParameter("productId");
		if(request.getParameter("delAll")!=null){
			try {
				PurchaseCarDao.delAllCar(userId);
				
				List<PurchaseCar> productcars = PurchaseCarDao.CarProduct(userId);
				double sum = 0;
				request.getSession().setAttribute("sum", sum);	
				request.getSession().setAttribute("productcars", productcars);
				response.sendRedirect("orderProduct.jsp");		//跳转至	orderProduct.jsp 页面
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			if (request.getParameter("add") == null) {
				if(request.getParameter("quanty").equals("1")){
					PurchaseCarDao.delCar(userId, productId);
				}else{
					PurchaseCarDao.subCarQuanty(userId, productId);
				}
			}else{
				PurchaseCarDao.addCarQuanty(userId, productId);
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
		response.sendRedirect("orderProduct.jsp");	//跳转至	orderProduct.jsp 页面

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
