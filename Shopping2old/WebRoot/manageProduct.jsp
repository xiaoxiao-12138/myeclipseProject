<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.shop.entity.Product"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'manageProduct.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<!-- <script defer="defer">
	function ondelete()													//此方法用于校验是否有勾选商品
	{
		if(confirm("确定此删除商品?")){
			
		}
		else{
			
		}
	}
</script> -->
  </head>
  
  <body>
   <div style="text-align: center">
	<form action="AdjustProductQuantyServlet"  method='post'>
  		<table border="1" cellpadding="5" align="center">
		    <tr>
		        <th>商品编号</th>
		        <th>商品名称</th>
		        <th>单价</th>
		        <th>库存</th>
		    </tr>
    	<%
    		List<Product> products = (List<Product>)session.getAttribute("products");				//获取session中的 全部的商品信息 
    	    	    		List<String> productIds = new ArrayList<>();
    	    	    		for(int i = 0; i < products.size(); i++){
    	    	    		productIds.add(products.get(i).getProductId());			//添加商品ID到队列中
    	%>
		    	<tr>
		        <td><%=products.get(i).getProductId() %></td>
		        <td><%=products.get(i).getProductName() %></td>
		        <td><input type="text" name='productPrice' value='<%=products.get(i).getProductPrice() %>' size="8"></td>
		        <td><input type="text" name='quanty' value='<%=products.get(i).getQuanty() %>' size="5"></td>
		   		
		   		</tr>
	    <% 	
	    	}
	    	request.getSession().setAttribute("productIds", productIds);
	    %>
			    <tr>
			    	<td colspan="4" align="center" ><a href='addProduct.jsp'><font color="red">增加商品</font></a></td>
			    </tr>
		</table>
		<br>
		<input type="submit" value='保存'  >	
	</form>
	<a href='login.jsp'>登录页</a>
  </div>
  </body>
</html>
