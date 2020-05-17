<%@page import="com.shop.entity.PurchaseOrder"%>
<%@page import="com.shop.dao.PurchaseOrderDao"%>
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
		window.location.href="MyJsp.jsp";
	}
	oBtn2.onclick=function(){
		documnet.getElementById('myform').submit();
	}
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
	<form action="SelectPurchaseOrderServlet" id='myform' method="post">
	  	<div class="searchbox">
			<div class="bodys">
				<p style="display: block;"><button type="button" class="one3" style="background-color: orange;" id='btn1'>查询商品</button><input type="text" value="" id="" class="two" placeholder="输入订单号" name='orderId'><button class="one1" id='btn2'>搜索</button></p>
			</div>
		</div>
	</form>
	
<section class="cartMain">
	<div class="cartMain_hd">
		<ul class="order_lists cartTop">
			
			<li class="list_con">商品名称</li>
			<li class="list_info">商品编号</li>
			<li class="list_price">商品单价</li>
			<li class="list_amount">购买数量</li>
			<li class="list_sum">用户账号</li>
			<li class="list_op">订单号</li>
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
    			//List<PurchaseOrder> purchaseOrders1 = PurchaseOrderDao.SelectAllPurchase();
    			List<PurchaseOrder> purchaseOrders1 = (List<PurchaseOrder>)request.getSession().getAttribute("purchaseOrders");
    			System.out.println("123");
    			System.out.println(purchaseOrders1);
				System.out.println(purchaseOrders1.get(0).getClass() +"  "+ PurchaseOrder.class);
				int  sum = purchaseOrders1.size();
				for(int j = 0; j < purchaseOrders1.size(); j++){
    		%>
				    	<ul class="order_lists">
				
				<li class="list_con">
					<div class="list_img"><a href="javascript:;"><img src="image/<%=purchaseOrders1.get(j).getProductName()%>.jpg" alt=""></a></div>
					<div class="list_text"><a href="javascript:;"><%=purchaseOrders1.get(j).getProductName()%></a></div>
				</li>
				<li class="list_info">
					<p><%=purchaseOrders1.get(j).getProductId()%></p>
				</li>
				<li class="list_price">
					<p class="price">￥<%=purchaseOrders1.get(j).getProductPrice()%></p>
				</li>
				<li class="list_amount">
					<div class="amount_box">
						
						<%=purchaseOrders1.get(j).getQuanty()%>
						
					</div>
				</li>
				<li class="list_sum">
					<p class="sum_price"><%=purchaseOrders1.get(j).getUserId()%></p>
				</li>
				<li class="list_op">
					<p class="del"><a href="javascript:;" class="delBtn"><%=purchaseOrders1.get(j).getOrderId()%></a></p>
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
