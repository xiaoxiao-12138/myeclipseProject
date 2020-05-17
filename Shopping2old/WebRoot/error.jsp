<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'error.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/time.css">

  </head>
  
  <body>
	  	<div class="counter">
		<div class="nums">
		<span class="in">3</span>
		<span>2</span>
		<span>1</span>
		<span>0</span>
		</div>
		<h4>请先登录哟~</h4>
		</div>
		
		<div class="final">
			<% 
				response.setHeader("refresh", "3; login.html");
			%>
		</div>

		<script type="text/javascript" src="js/time.js"></script>

		<div style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';">
		</div>
  </body>
</html>
