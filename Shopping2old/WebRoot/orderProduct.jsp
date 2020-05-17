<%@page import="com.shop.entity.Product"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品</title>

<link rel="stylesheet" type="text/css" href="css/easyui.css">
<link rel="stylesheet" type="text/css" href="css/icon.css">

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>

<style type="text/css">
	.products{
		list-style:none;
		margin-right:300px;
		margin-left:300px;
		padding:0px;
		height:100%;
	}
	.products li{
		display:inline;
		float:left;
		margin:10px;
	}
	.item{
		display:block;
		text-decoration:none;
	}
	.item img{
		border:1px solid #333;
	}
	.item p{
		margin:0;
		font-weight:bold;
		text-align:center;
		color:#c3c3c3;
	}
	.cart{
		position:fixed;
		right:0;
		top:0;
		width:280px;
		height:100%;
		background:#ccc;
		padding:0px 10px;
	}
	.message{
		position:fixed;
		left:0;
		top:0;
		width:280px;
		height:100%;
		background:#ccc;
		padding:0px 10px;
	}
	h1{
		text-align:center;
		color:#555;
	}
	h2{
		position:absolute;
		font-size:16px;
		left:10px;
		bottom:20px;
		color:#555;
	}
	.total{
		margin:0;
		text-align:right;
		padding-right:20px;
	}
</style>
<script type="text/javascript">
	var data = {"total":0,"rows":[]};
	var totalCost = 0;
	$(function(){
		$('#cartcontent').datagrid({
			singleSelect:true
		});
		$('.item').draggable({
			revert:true,
			proxy:'clone',
			onStartDrag:function(){
				$(this).draggable('options').cursor = 'not-allowed';
				$(this).draggable('proxy').css('z-index',10);
			},
			onStopDrag:function(){
				$(this).draggable('options').cursor='move';
			}
		});
		$('.cart').droppable({
			onDragEnter:function(e,source){
				$(source).draggable('options').cursor='auto';
			},
			onDragLeave:function(e,source){
				$(source).draggable('options').cursor='not-allowed';
			},
			onDrop:function(e,source){
				var id = $(source).find('p:eq(0)').html();
				var name = $(source).find('p:eq(1)').html();
				var price = $(source).find('p:eq(2)').html();
				location.href="/Shopping2/addCarServlet?id="+id+"&name="+name+"&price="+price;
			}
		});
	});
</script>

</head>
<body style="margin:0;padding:0;height:100%;background:#fafafa;" id="body">
<div class="message">
	<a href="ProductClassfyServlet?type=phone">手机</a>
</div>

<ul class="products">
		<c:forEach items="${sessionScope.products}" var="item">
			<li>
				<a href="#" class="item">
				<img src="image/${item.productName}.jpg" height=160px width=160px>
					<div>
						<p hidden>${item.productId}</p>
						<p>${item.productName}</p>
						<p>${item.productPrice}</p>
						<p>库存：${item.quanty}</p>
					</div>
				</a>
			</li>
		</c:forEach>
</ul>

<div class="cart">
	<h1>购物车</h1>
	<div style="background:#fff">
	<table id="cartcontent" fitColumns="true" style="width:300px;height:auto;">
		<thead>
			<tr>
				<th field="name" width=140>Name</th>
				<th field="quanty" width=60 align="right">Quanty</th>
				<th field="price" width=60 align="right">Price</th>
			</tr>
			<c:forEach items="${sessionScope.productcars}" var="item">
				<tr>
					<th field="name" width=140>${item.productName}</th>
					<th field="quanty" width=60 align="right">${item.quanty}</th>
					<th field="price" width=60 align="right">${item.productPrice}</th>
					<th width=60 align="right"><a href="/Shopping2/addCarQuantyServlet?productId=${item.productId}&add=true">+</a></th>
					<th width=60 align="right"><a href="/Shopping2/addCarQuantyServlet?productId=${item.productId}&quanty=${item.quanty}">-</a></th>
				</tr>
			</c:forEach>
		</thead>
	</table>
	</div>
	<p class="total">合计: ${sum}</p>
	<p><a href="/Shopping2/addCarQuantyServlet?delAll=true">清空购物车</a></p>
	<p><a href="ProductManageServlet">结算购物车</a></p>
	<h2>拖动商品添加到购物车</h2>
</div>
</body>
</html>