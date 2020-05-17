package com.shop.entity;

//��Ʒ��Ϣ
public class Product {
	private String productId;				//��ƷID
	private String productName;				//��Ʒ����
	private double productPrice;			//��Ʒ�۸�
	private int quanty;						//��Ʒ����
	private String type;	
	
	public Product() {
		super();
	}
	
	public Product(String productId) {
		super();
		this.productId = productId;
	}
	
	public Product(String productId, String productName, double productPrice,String type) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.type=type;
	}
	
	
	
	public Product(String productId, double productPrice, int quanty) {
		super();
		this.productId = productId;
		this.productPrice = productPrice;
		this.quanty = quanty;
	}

	public Product(String productId, String productName, double productPrice, int quanty,String type) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.quanty = quanty;
		this.type=type;
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

	public String getType(){
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
