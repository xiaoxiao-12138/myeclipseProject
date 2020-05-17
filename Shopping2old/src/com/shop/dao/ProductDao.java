package com.shop.dao;
//55555
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.shop.db.DateSourseUtil;
import com.shop.entity.Product;
//12138
//��Ʒ��Ϣ����
public class ProductDao {

	//��ѯȫ����Ʒ
	public static List<Product> selectAllProduct() throws SQLException{
		QueryRunner runner = new QueryRunner(DateSourseUtil.getDataSource());
		List<Product> products = runner.query("select * from Product;", new BeanListHandler<Product>(Product.class));
		return products;
	}
	
	//��ѯ��������Ʒ
	public static List<Product> selectOneProduct(String type) throws SQLException{
		QueryRunner runner = new QueryRunner(DateSourseUtil.getDataSource());
		List<Product> products = runner.query("select * from Product where type = ?;", new BeanListHandler<Product>(Product.class),type);
		return products;
	}
	
	//ģ����ѯ��Ʒ
	public static List<Product> vagueSelectProduct(String name) throws SQLException{
		QueryRunner runner = new QueryRunner(DateSourseUtil.getDataSource());
		name = "%" + name + "%";
		List<Product> products = runner.query("select * from Product where productName like ?;", new BeanListHandler<Product>(Product.class),name);
		return products;
	}
	

	//�жϹ���������Ƿ���ڿ���
	public static Product isEnoughQuanty(String productId,int productQuanty) throws SQLException{
		QueryRunner runner = new QueryRunner(DateSourseUtil.getDataSource());
		Product product = runner.query("select * from Product where productId = ? ;", new BeanHandler<Product>(Product.class), productId);
		
		//��������������ڿ������������ʵ�����󣬷��򷵻�null
		if(productQuanty <= product.getQuanty()){
			return product;
		}
		return null;
	}
	
	//ͨ����Ʒ��ź͹�������������¿�������
	public static void updateProduct(String productId,int productQuanty) throws SQLException{
		QueryRunner runner = new QueryRunner(DateSourseUtil.getDataSource());
		runner.update("update product set quanty = quanty - ? where productId = ? ;", productQuanty,productId);
	}
	
	//ͨ����Ʒ����ID�����ۣ������������±�����Ʒ
	public static void saveProduct(Product Products) throws SQLException{
		QueryRunner runner = new QueryRunner(DateSourseUtil.getDataSource());
		runner.update("update product set productPrice=?,quanty=? where productId = ? ;",Products.getProductPrice(),Products.getQuanty(),Products.getProductId());
	}
	
	//ͨ����Ʒ����ID�����ƣ����ۣ�������������µ���Ʒ
	public static void addProduct(Product Product) throws SQLException{
		QueryRunner runner = new QueryRunner(DateSourseUtil.getDataSource());
		runner.update("insert into product values(?,?,?,?,?);",Product.getProductId(),Product.getProductName(),Product.getProductPrice(),Product.getQuanty(),Product.getType());
	}
	
	//ͨ����Ʒ����ID����ɾ����Ʒ
	public static void deleteProduct(Product Product) throws SQLException{
		QueryRunner runner = new QueryRunner(DateSourseUtil.getDataSource());
		runner.update("update product set quanty = 0 where productId = ? ;",Product.getProductId());
	}
	
	//ͨ����Ʒ����ID�����ۣ����޸���Ʒ����
	public static void adjustProductPrice(String productId,String produPrice) throws SQLException{
		QueryRunner runner = new QueryRunner(DateSourseUtil.getDataSource());
		runner.update("update product set productPrice = ? where productId = ? ;",Double.valueOf(produPrice),productId);
	}
	
	//ͨ����Ʒ����ID����棩���޸���Ʒ����
	public static void adjustProductQuanty(String productId,String quanty) throws SQLException{
		QueryRunner runner = new QueryRunner(DateSourseUtil.getDataSource());
		runner.update("update product set quanty = ? where productId = ? ;",Integer.valueOf(quanty),productId);
	}
}
