<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'addProduct.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    	<form action="AddProductServlet" method="post">
    		商品编号:<input type='text' name='productId'><br><br>
    		商品名称:<input type='text' name='productName'><br><br>
    		商品单价:<input type='text' name='productPrice'><br><br>
    		商品库存:<input type='text' name='quanty'><br><br>
    		商品类型:<input type='text' name='type'><br><br>
    				<input type="submit" value="增加">
    	</form>
    	
    	
  </body>
</html>
