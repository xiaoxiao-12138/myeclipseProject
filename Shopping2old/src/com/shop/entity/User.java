package com.shop.entity;

//用户类
public class User {
	private String userId;					//用户ID
	private String userPassword;			//密码
	private String gender;
	private String provice;
	private String city;
	private String adress;
	
	public User() {
		super();
	}
	
	public User(String userId, String userPassword) {
		super();
		this.userId = userId;
		this.userPassword = userPassword;
	}
	
	public User(String userId, String userPassword,String gender, String provice, String city, String adress) {
		super();
		this.userId = userId;
		this.userPassword = userPassword;
		this.gender=gender;
		this.provice = provice;
		this.city = city;
		this.adress = adress;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getProvice() {
		return provice;
	}

	public void setProvice(String provice) {
		this.provice = provice;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
}
