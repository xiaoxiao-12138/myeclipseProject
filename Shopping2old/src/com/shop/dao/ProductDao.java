package com.shop.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.shop.db.DateSourseUtil;
import com.shop.entity.Product;

//商品信息操作
public class ProductDao {

	//查询全部商品
	public static List<Product> selectAllProduct() throws SQLException{
		QueryRunner runner = new QueryRunner(DateSourseUtil.getDataSource());
		List<Product> products = runner.query("select * from Product;", new BeanListHandler<Product>(Product.class));
		return products;
	}
	
	//查询分类后的商品
	public static List<Product> selectOneProduct(String type) throws SQLException{
		QueryRunner runner = new QueryRunner(DateSourseUtil.getDataSource());
		List<Product> products = runner.query("select * from Product where type = ?;", new BeanListHandler<Product>(Product.class),type);
		return products;
	}
	
	//模糊查询商品
	public static List<Product> vagueSelectProduct(String name) throws SQLException{
		QueryRunner runner = new QueryRunner(DateSourseUtil.getDataSource());
		name = "%" + name + "%";
		List<Product> products = runner.query("select * from Product where productName like ?;", new BeanListHandler<Product>(Product.class),name);
		return products;
	}
	

	//判断购买的数量是否大于库存的
	public static Product isEnoughQuanty(String productId,int productQuanty) throws SQLException{
		QueryRunner runner = new QueryRunner(DateSourseUtil.getDataSource());
		Product product = runner.query("select * from Product where productId = ? ;", new BeanHandler<Product>(Product.class), productId);
		
		//如果购买数量大于库存数量，返回实例对象，否则返回null
		if(productQuanty <= product.getQuanty()){
			return product;
		}
		return null;
	}
	
	//通过商品编号和购买的数量，更新库存的数量
	public static void updateProduct(String productId,int productQuanty) throws SQLException{
		QueryRunner runner = new QueryRunner(DateSourseUtil.getDataSource());
		runner.update("update product set quanty = quanty - ? where productId = ? ;", productQuanty,productId);
	}
	
	//通过商品对象（ID，单价，数量）来重新保存商品
	public static void saveProduct(Product Products) throws SQLException{
		QueryRunner runner = new QueryRunner(DateSourseUtil.getDataSource());
		runner.update("update product set productPrice=?,quanty=? where productId = ? ;",Products.getProductPrice(),Products.getQuanty(),Products.getProductId());
	}
	
	//通过商品对象（ID，名称，单价，数量）来添加新的商品
	public static void addProduct(Product Product) throws SQLException{
		QueryRunner runner = new QueryRunner(DateSourseUtil.getDataSource());
		runner.update("insert into product values(?,?,?,?,?);",Product.getProductId(),Product.getProductName(),Product.getProductPrice(),Product.getQuanty(),Product.getType());
	}
	
	//通过商品对象（ID）来删除商品
	public static void deleteProduct(Product Product) throws SQLException{
		QueryRunner runner = new QueryRunner(DateSourseUtil.getDataSource());
		runner.update("update product set quanty = 0 where productId = ? ;",Product.getProductId());
	}
	
	//通过商品对象（ID，单价）来修改商品单价
	public static void adjustProductPrice(String productId,String produPrice) throws SQLException{
		QueryRunner runner = new QueryRunner(DateSourseUtil.getDataSource());
		runner.update("update product set productPrice = ? where productId = ? ;",Double.valueOf(produPrice),productId);
	}
	
	//通过商品对象（ID，库存）来修改商品单价
	public static void adjustProductQuanty(String productId,String quanty) throws SQLException{
		QueryRunner runner = new QueryRunner(DateSourseUtil.getDataSource());
		runner.update("update product set quanty = ? where productId = ? ;",Integer.valueOf(quanty),productId);
	}
}
