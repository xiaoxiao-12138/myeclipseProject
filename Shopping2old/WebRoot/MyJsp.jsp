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
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="css/reset.css">
	<link rel="stylesheet" href="css/carts.css">

<script>
window.onload=function(){
	var oBtn1=document.getElementById('btn1');
	var oBtn2=document.getElementById('btn2');
	oBtn1.onclick=function(){
		location.href="SelectPurchaseOrderServlet";
	}
	oBtn2.onclick=function(){
		documnet.getElementById('myform').submit();
	}
}

function aaa(){
	location.href="selectPurchaseOrder.jsp";
}


function adjustProduct() {
  /* var txt=documnet.getElementById("adjustproduct"); */
  /* txt = txt.getAttribute("myAttr"); */
  /* var person = prompt("请输入商品单价和库存，用空格隔开：", ""); */
   /* location.href=""; */
   var txt=document.getElementById('adjustproduct').getAttribute("myAttr");
   var inf = prompt("请输入商品单价和库存，用空格隔开：", "");
   txt = txt+" "+inf;
   location.href="AdjustProductServlet?txt="+txt;
}

</script>

<style type="text/css">
*{margin:0;padding:0;list-style-type:none;}
a,img{border:0;}

/* searchbox */
.searchbox{width:1000px;height:80px;margin:40px auto 0 300px;}
.searchbox ul{ height:35px; width:500px; list-style:none; margin-left:20px}
.searchbox ul li{ float:left}
.searchbox ul li a{ float:left; line-height:35px; padding:0 20px; text-decoration:none; color:#000; font-size:14px; font-weight:bold;}
.searchbox ul li .style1{ background-color:#000; color:#fff}
.searchbox ul li .style2{ background-color:#f00;color:#fff}
.searchbox ul li .style3{ background-color:#F90;color:#fff}
.bodys input{ height:35px;line-height:30px;width:700px;padding:0 10px;float:left;}
.bodys .one{ border:#000 3px solid}
.bodys .two{ border:#f00 3px solid}
.bodys .three{ border:#F90 3px solid}
.bodys .one1{ background-color:#f00; }
.bodys .two2{ background-color:#f00;}
.bodys .three3{ background-color:#F90;}
.bodys button{float:left;border:0;height:35px;width:100px; color:#FFF; line-height:36px;text-align:center;overflow:hidden;}
</style>


  </head>
  
  <body>

	<form action="ProductClassfyServlet" id='myform' method="post">
	  	<div class="searchbox">
			<div class="bodys">
				<p style="display: block;"><button type="button" class="one3" style="background-color: orange;" id='btn1'  onclick='aaa()' >查询订单</button><input type="text" value="" id="" class="two" placeholder="输入商品名称" name='productName'><button class="one1" id='btn2'>搜索</button></p>
			</div>
		</div>
	</form>
	
<section class="cartMain">
	<div class="cartMain_hd">
		<ul class="order_lists cartTop">
			
			<li class="list_con">商品名称</li>
			<li class="list_info">商品编号</li>
			<li class="list_price">单价</li>
			<li class="list_amount">库存数量</li>
			<li class="list_sum">商品类型</li>
			<li class="list_op">操作</li>
		</ul>
	</div>

	<div class="cartBox">
		<div class="shop_info">
			
			<div class="shop_name">
				店铺：<a href="javascript:;">小小购物城</a>
			</div>
		</div>
		<div class="order_content">
			
			
			<%
    		List<Product> products = (List<Product>)session.getAttribute("products");				//获取session中的 全部的商品信息 
 	    	    		int sum = products.size();
 	    	    		List<String> productIds = new ArrayList<>();
 	    	    		for(int i = 0; i < products.size(); i++){
 	    	    		productIds.add(products.get(i).getProductId());			//添加商品ID到队列中
    	%>
			<ul class="order_lists">
				
				<li class="list_con">
					<div class="list_img"><a href="javascript:;"><img src="image/<%=products.get(i).getProductName() %>.jpg" alt=""></a></div>
					<div class="list_text"><a href="javascript:;"><%=products.get(i).getProductName() %></a></div>
				</li>
				<li class="list_info">
					<p><%=products.get(i).getProductId() %></p>
				</li>
				<li class="list_price">
					<p class="price">￥<%=products.get(i).getProductPrice() %></p>
				</li>
				<li class="list_amount">
					<div class="amount_box">
						
						<%=products.get(i).getQuanty() %>
						
					</div>
				</li>
				<li class="list_sum">
					<p class="sum_price"><%=products.get(i).getType() %></p>
				</li>
				<li class="list_op">
					<p class="del"><a href="DeleteProductServlet?productId=<%=products.get(i).getProductId()%>" class="delBtn">清空库存</a></p>
					<p class="del"><a href="javascript:;" id='adjustproduct' class="delBtn" onclick="adjustProduct()" myAttr="<%=products.get(i).getProductId()%>">修改商品</a></p>
				</li>
			</ul>
			<%
				}
			 %>
			
		</div>
	</div>

	
				

	
	<!--底部-->
	<div class="bar-wrapper">
		<div class="bar-right">
			<div class="piece">商品共计<strong class="piece_num"><%=sum%></strong>件</div>
			
			<div class="calBtn"></div>
		</div>
	</div>
</section>









</body>
</html>
