package com.shop.db;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

//数据源获取
public class DateSourseUtil {
	public static DataSource getDataSource(){
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/AA");
			return ds;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
