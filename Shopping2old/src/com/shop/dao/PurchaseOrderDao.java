package com.shop.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.shop.db.DateSourseUtil;
import com.shop.entity.Product;
import com.shop.entity.PurchaseOrder;

//���������
public class PurchaseOrderDao {
	
	//���붩����Ϣ
	public static void updatePurchaseOrder(String orderId,String userId,Product product,int quanty) throws SQLException{
		QueryRunner runner = new QueryRunner(DateSourseUtil.getDataSource());
		runner.update("insert into purchaseOrder values (?,?,?,?,?,?);",orderId,userId,product.getProductId(),product.getProductName(),product.getProductPrice(),quanty);
	} 

	//��ѯ������Ϣ
	public static List<PurchaseOrder> queryPurchaseOrder(String orderId) throws SQLException{
		QueryRunner runner = new QueryRunner(DateSourseUtil.getDataSource());
		List<PurchaseOrder> purchaseOrders = runner.query("select * from purchaseOrder where orderId = ?;",new BeanListHandler<PurchaseOrder>(PurchaseOrder.class),orderId);
		return purchaseOrders;
	} 
	
	//��ѯ����������Ϣ
	public static List<PurchaseOrder> SelectPurchaseByUserId(String userId) throws SQLException{
		QueryRunner runner = new QueryRunner(DateSourseUtil.getDataSource());
		List<PurchaseOrder> purchaseOrders = runner.query("select * from purchaseOrder where userId = ?;",new BeanListHandler<PurchaseOrder>(PurchaseOrder.class),userId);
		return purchaseOrders;
	} 
	
	//��ѯȫ��������Ϣ
	public static List<PurchaseOrder> SelectAllPurchase() throws SQLException{
		QueryRunner runner = new QueryRunner(DateSourseUtil.getDataSource());
		List<PurchaseOrder> purchaseOrders = runner.query("select * from purchaseOrder group by orderId;",new BeanListHandler<PurchaseOrder>(PurchaseOrder.class));
		System.out.println(purchaseOrders+"fanhuizhi");
		return purchaseOrders;
	} 
	

}
