<%@page import="com.shop.entity.Product"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'orderProduct.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
<style>
        * {
            margin: 0;
            padding: 0;
        }
        body,
        html {
            font-size: 1px;
        }
        img {
            border: 0;
        }
        a {
            color: #green;
            text-decoration: none;
            outline: none;
        }
        ol,
        ul,
        li {
            list-style: none;
        }
        .head-nav {
            width: 50%;
            height: 5px;
            background: write;
        }
        .head-nav-con {
            width: 1200px;
            height: 60px;
            margin: auto;
        }
        .head-nav-con ul li {
            width: 170px;
			float: left;
			font-size: 20px;
            text-align: center;
        }
        .head-nav-con ul li:hover {
            background: white;
        }
        .head-nav-con ul a {
            display: block;
            text-decoration: none;
            text-align: center;
            line-height: 60px;
            color: white;
        }
        .head-nav-con .drop-down {
            position: relative;
        }
        .head-nav-con .drop-down-content {
            padding: 0;
            display: none;
            position: absolute;
            z-index: 1;
            background: green;
        }
        .head-nav-con .drop-down-content li:hover > a {
            background-color: #ff9c01;
        }
        .head-nav-con .drop-down:hover .drop-down-content {
            display: block;
        }
        .head-nav-con .drop-down-2 {
            position: relative;
        }
        .head-nav-con .drop-down-content-2 {
            padding: 0;
            display: none;
            position: absolute;
            z-index: 1;
            background-color: green;
            right: -170px;
            top: 0;
        }
        .head-nav-con .drop-down-content-2 > li {
            float: none;
            background-color: green;
        }
        .head-nav-con .drop-down-content-2 li:hover a {
            background-color: #ff9c01;
        }
        .head-nav-con .drop-down-2:hover .drop-down-content-2 {
            display: block;
        }
    </style>
  
<%-- <script defer="defer">

function test(obj){
if(obj.checked==true){
alert("选中");
subMit();

}else{
alert("未选中");
subMit1();
}
}

function subMit(){
alert("执行选中的函数");
<%= 123 %>
}

function subMit1(){
alert("执行未选中的函数");
}
</script>  --%> 

<script defer="defer">
	function checkbox()													//此方法用于校验是否有勾选商品
	{
		var str=document.getElementsByName("productId");
		var objarray=str.length;
		var chestr="";
		
		for (i=0;i<objarray;i++)
		{
		 	if(str[i].checked == true)
		{
			chestr+=str[i].value+",";
		}
		}
	
		if(chestr == "")
		{
		 alert("请先选购商品！");
		}
		else
		{
		 document.getElementById('myform').submit();
		}
	}
</script>
  
<body>

						
  <div style="text-align: center">
  <form  id="myform" action="ProductManageServlet"  method='post'>
  	<table border="1" cellpadding="5" align="center">
  			<tr>
  				<td colspan='6'>
  				<div style="text-align: center">
    						<!--头部-->
    							<div class="header">
 						        <div class="head-nav">
       					        <div class="head-nav-con clearFloat">
                					 <ul>
                    					<li class="drop-down">分类
               					        	<ul class="drop-down-content">
                           						<li class="drop-down-2">家用电器
                                					<ul class="drop-down-content-2">
                                    						<li></li>
                                    						<li><a href="ProductClassfyServlet?type=washer">洗衣机</a></li>
                                    				
                                					</ul>
                            					</li>
                           						<li class="drop-down-2">食物
                                				</li>
                                				<li class="drop-down-2"><a href="ProductSelectServlet">全部商品
                                				</li>
                        					</ul>
                    					</li>
                					</ul>
            				</div>
       						</div>
    						</div>
						</div>
  				</td>
  			</tr>
		    <tr>
		        <th>商品编号</th>
		        <th>商品名称</th>
		        <th>单价</th>
		        <th>库存</th>
		        <th>购买数量</th>
		        <th>购物篮</th>
		    </tr>		AA
    		<%
		    	List<Product> products = (List<Product>)session.getAttribute("products");				//获取session中的 全部的商品信息 
		    		        			for(Product product : products){
		    %>
		    	<tr>
		        <td><%=product.getProductId() %></td>
		        <td><%=product.getProductName() %></td>
		        <td><%=product.getProductPrice() %></td>
		        <td><%=product.getQuanty() %></td>
		        <td><input type="text" name='<%=product.getProductId()%>' value='1' size='1'></td>
		        <td><input type="checkbox" name='productId' value='<%=product.getProductId()%>'></td>
		   		</tr>
			<% 	
	    		}
	    	%>
	   
			</table>
			<br>
		 	<input type="button" value='订购发送' onclick="checkbox();" >								<!-- 提交表单前调用checkbox()方法校验，确保有购买商品 -->
		</form>
	<a href='success.jsp'>返回</a>
  </div>
  </body>
</html>
