package com.shop.entity;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.UUID;

//订单类
public class PurchaseCar {			//订单ID
	private String userId;				//用户ID
	private String productId;			//商品ID
	private String productName;			//商品名称
	private double productPrice;		//商品单价
	private int quanty;					//购买数量
	
	public PurchaseCar() {
		super();
	}

	public PurchaseCar( String userId, String productId, String productName, double productPrice,int quanty) {
		super();
		this.userId = userId;
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.quanty = quanty;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public int getQuanty() {
		return quanty;
	}

	public void setQuanty(int quanty) {
		this.quanty = quanty;
	}

	//生成唯一订单号
	 public static String getOrderIdByUUId() {
		 int machineId = 1;
		 int hashCodeV = UUID.randomUUID().toString().hashCode();
		 if(hashCodeV < 0) {											//有可能是负数
			 hashCodeV = - hashCodeV;
		 }
		 return machineId+ String.format("%015d", hashCodeV);
	 }
}