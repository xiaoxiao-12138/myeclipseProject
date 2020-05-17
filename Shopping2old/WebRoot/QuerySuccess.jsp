<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.shop.entity.PurchaseOrder"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'QuerySuccess.jsp' starting page</title>
    
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
    	<% 
    		List<PurchaseOrder> purchaseOrders = (List<PurchaseOrder>)request.getSession().getAttribute("purchaseOrders");
    		double Price = 0.0;
    	%>
    	订单号：<%=request.getParameter("orderId") %>
 		<table border='1' cellpadding='5'>
 			<tr>
 				<th colspan="4"><font color="green">订单清单</font></th>
 			</tr>
 			<tr>
 				<th>商品编号</th>
 				<th>商品名称</th>
 				<th>商品单价</th>
 				<th>购买数量</th>
 			</tr>
 			<% 
 				for(PurchaseOrder purchaseOrder : purchaseOrders){
 			%>
			<tr>
 				<td><%=purchaseOrder.getProductId() %></td>
 				<td><%=purchaseOrder.getProductName() %></td>
 				<td><%=purchaseOrder.getProductPrice() %></td>
 				<td><%=purchaseOrder.getQuanty() %></td>
 			</tr>
			<% 
				Price += purchaseOrder.getProductPrice() * purchaseOrder.getQuanty();
				}
			%>
 		</table>
 		<%="一共消费了"+Price+"元" %><br><br>
 		<a href='success.jsp'>首页</a>
 		<a href='Query.jsp'>返回</a>
 </body>
</html>