package com.shop.dao;


import java.sql.SQLException;
import org.apache.commons.dbutils.*;
import org.apache.commons.dbutils.handlers.BeanHandler;
import com.shop.db.DateSourseUtil;
import com.shop.entity.User;

//用户表操作
public class UserDao {
	
	//通过账号密码查询用户
	public static User selectByUserId(String userId, String userPassword) throws SQLException{
		QueryRunner runner = new QueryRunner(DateSourseUtil.getDataSource());
		User user = runner.query("select * from User where userId=? and userPassword=?",new BeanHandler<User>(User.class),userId,userPassword);
		return user;
	}
	
	/*public static void addUser(User user) throws SQLException{
		QueryRunner runner = new QueryRunner(DateSourseUtil.getDataSource());
		runner.update("insert into User values(?,?,?,?,?,?);",user.getUserId(),user.getUserPassword(),user.getGender(),user.getProvice(),user.getCity(),user.getAdress());
	}*/
	
	public static void addUser(User user) throws SQLException{
		QueryRunner runner = new QueryRunner(DateSourseUtil.getDataSource());
		runner.update("insert into User values(?,?);",user.getUserId(),user.getUserPassword());
	}
	
	public static boolean isExist(String userId) throws SQLException{
		QueryRunner runner = new QueryRunner(DateSourseUtil.getDataSource());
		User user = runner.query("select * from User where userId=?",new BeanHandler<User>(User.class),userId);
		if(user == null){
			return true;
		}else{
			return false;
		}
	}
	
}
