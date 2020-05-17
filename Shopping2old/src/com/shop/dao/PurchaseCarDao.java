package com.shop.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.shop.db.DateSourseUtil;
import com.shop.entity.PurchaseCar;
import com.shop.entity.User;


public class PurchaseCarDao {
	//查询购物车全部商品
		public static List<PurchaseCar> CarProduct(String userId) throws SQLException{
			QueryRunner runner = new QueryRunner(DateSourseUtil.getDataSource());
			List<PurchaseCar> PurchaseCars = runner.query("select * from purchasecar where userid =?;",new BeanListHandler<PurchaseCar>(PurchaseCar.class),userId);
			return PurchaseCars;
		}
		
		public static void addCarProduct(PurchaseCar purchasecar) throws SQLException{
			QueryRunner runner = new QueryRunner(DateSourseUtil.getDataSource());
			runner.update("insert into purchasecar values(?,?,?,?,?);",purchasecar.getUserId(),purchasecar.getProductId(),purchasecar.getProductName(),purchasecar.getProductPrice(),purchasecar.getQuanty());
		}
		
		
		public static void addCarQuanty(String userId,String productId) throws SQLException{
			QueryRunner runner = new QueryRunner(DateSourseUtil.getDataSource());
			runner.update("update purchasecar set quanty = quanty + 1 where userId=? and productId=? ;", userId,productId);
		}
		
		public static void subCarQuanty(String userId,String productId) throws SQLException{
			QueryRunner runner = new QueryRunner(DateSourseUtil.getDataSource());
			runner.update("update purchasecar set quanty = quanty - 1 where userId=? and productId=? ;", userId,productId);
		}

		public static void delCar(String userId, String productId) throws SQLException {
			QueryRunner runner = new QueryRunner(DateSourseUtil.getDataSource());
			runner.update("delete from purchasecar where userId=? and productId=? ;", userId,productId);
		}
		
		public static void delAllCar(String userId) throws SQLException {
			QueryRunner runner = new QueryRunner(DateSourseUtil.getDataSource());
			runner.update("delete from purchasecar where userId=?;", userId);
		}
		
		public static boolean isExist(String userId,String productId) throws SQLException{
			QueryRunner runner = new QueryRunner(DateSourseUtil.getDataSource());
			PurchaseCar purchaseCar = runner.query("select * from purchasecar where userId=? and productId=?",new BeanHandler<PurchaseCar>(PurchaseCar.class),userId,productId);
			if(purchaseCar == null){
				return false;
			}else{
				return true;
			}
		}
}
