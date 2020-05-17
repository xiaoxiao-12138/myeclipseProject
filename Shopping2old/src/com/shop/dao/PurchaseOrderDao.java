package com.shop.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.shop.db.DateSourseUtil;
import com.shop.entity.Product;
import com.shop.entity.PurchaseOrder;

//订单表操作
public class PurchaseOrderDao {
	
	//插入订单信息
	public static void updatePurchaseOrder(String orderId,String userId,Product product,int quanty) throws SQLException{
		QueryRunner runner = new QueryRunner(DateSourseUtil.getDataSource());
		runner.update("insert into purchaseOrder values (?,?,?,?,?,?);",orderId,userId,product.getProductId(),product.getProductName(),product.getProductPrice(),quanty);
	} 

	//查询订单信息
	public static List<PurchaseOrder> queryPurchaseOrder(String orderId) throws SQLException{
		QueryRunner runner = new QueryRunner(DateSourseUtil.getDataSource());
		List<PurchaseOrder> purchaseOrders = runner.query("select * from purchaseOrder where orderId = ?;",new BeanListHandler<PurchaseOrder>(PurchaseOrder.class),orderId);
		return purchaseOrders;
	} 
	
	//查询单个订单信息
	public static List<PurchaseOrder> SelectPurchaseByUserId(String userId) throws SQLException{
		QueryRunner runner = new QueryRunner(DateSourseUtil.getDataSource());
		List<PurchaseOrder> purchaseOrders = runner.query("select * from purchaseOrder where userId = ?;",new BeanListHandler<PurchaseOrder>(PurchaseOrder.class),userId);
		return purchaseOrders;
	} 
	
	//查询全部订单信息
	public static List<PurchaseOrder> SelectAllPurchase() throws SQLException{
		QueryRunner runner = new QueryRunner(DateSourseUtil.getDataSource());
		List<PurchaseOrder> purchaseOrders = runner.query("select * from purchaseOrder group by orderId;",new BeanListHandler<PurchaseOrder>(PurchaseOrder.class));
		System.out.println(purchaseOrders+"fanhuizhi");
		return purchaseOrders;
	} 
	

}
