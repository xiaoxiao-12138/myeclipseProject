package com.shop.entity;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.UUID;

//������
public class PurchaseCar {			//����ID
	private String userId;				//�û�ID
	private String productId;			//��ƷID
	private String productName;			//��Ʒ����
	private double productPrice;		//��Ʒ����
	private int quanty;					//��������
	
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

	//����Ψһ������
	 public static String getOrderIdByUUId() {
		 int machineId = 1;
		 int hashCodeV = UUID.randomUUID().toString().hashCode();
		 if(hashCodeV < 0) {											//�п����Ǹ���
			 hashCodeV = - hashCodeV;
		 }
		 return machineId+ String.format("%015d", hashCodeV);
	 }
}