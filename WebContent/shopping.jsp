<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="scripts/function.js"></script>
<title>易买网 - 首页</title>
</head>
<body>
<div id="header" class="wrap">
	<div id="logo"><img src="images/logo.gif" /></div>
	<div class="help">
	<c:choose>
		<c:when test="${empty myuser }">
			<a href="easybuyuserservlet?option=3">登录</a>
		</c:when>
		<c:otherwise>
			<a href="shoppingservlet?option=9" id="shoppingBag" class="shopping">购物车${myshopbuscount }件</a>
			亲爱的${myuser.eu_user_id }欢迎你！
			<c:choose>
				<c:when test="${not empty myshopbus }">
					<a class="button" id="logout" href="#">注销</a>
				</c:when>
				<c:otherwise>
					<a class="button" id="logout1" href="#">注销</a>
				</c:otherwise>
			</c:choose>
				<a href="commentservlet?option=1">留言</a>
		</c:otherwise>
	</c:choose>
	<a href="easybuyuserservlet?option=1">注册</a>
	<c:if test="${myuser.eu_status==1 }">
			<a href="easybuyuserservlet?option=6">后台管理</a>
	</c:if>
	
	</div>
	<div class="navbar">
		<ul class="clearfix">
			<li class="current"><a href="indexservlet">首页</a></li>
			<li><a href="productviewservlet?option=7&categoryid=17">图书</a></li>
			<li><a href="productviewservlet?option=7&categoryid=1">百货</a></li>
			<li><a href="mainservlet?option=1">品牌</a></li>
			<li><a href="mainservlet?option=4">促销</a></li>
		</ul>
	</div>
</div>
<div id="childNav">
	<div class="wrap">
		<ul class="clearfix">
			<li class="first"><a href="productviewservlet?option=7&categoryid=18">音乐</a></li>
			<li><a href="productviewservlet?option=7&categoryid=19">影视</a></li>
			<li><a href="productviewservlet?option=7&categoryid=20">少儿</a></li>
			<li><a href="productviewservlet?option=7&categoryid=21">动漫</a></li>
			<li><a href="productviewservlet?option=7&categoryid=22">小说</a></li>
			<li><a href="productviewservlet?option=7&categoryid=23">外语</a></li>
			<li><a href="productviewservlet?option=7&categoryid=24">数码相机</a></li>
			<li><a href="productviewservlet?option=7&categoryid=25">笔记本</a></li>
			<li><a href="productviewservlet?option=7&categoryid=26">羽绒服</a></li>
			<li><a href="productviewservlet?option=7&categoryid=27">秋冬靴</a></li>
			<li><a href="productviewservlet?option=7&categoryid=28">运动鞋</a></li>
			<li><a href="productviewservlet?option=7&categoryid=29">美容护肤</a></li>
			<li><a href="productviewservlet?option=7&categoryid=5">家纺用品</a></li>
			<li><a href="productviewservlet?option=7&categoryid=30">婴幼奶粉</a></li>
			<li><a href="productviewservlet?option=7&categoryid=11">饰品</a></li>
			<li class="last"><a href="#">Investor Relations</a></li>
		</ul>
	</div>
</div>
<div id="position" class="wrap">
	您现在的位置：<a href="indexservlet">易买网</a> &gt; 购物车
</div>
<div class="wrap">
	<div id="shopping">
		<form action="shoppingservlet?option=4" method="post">
			<table>
				<tr>
					<th>商品名称</th>
					<th>商品价格</th>
					<th>购买数量</th>
					<th>操作</th>
				</tr>
				<c:forEach var="shop" items="${myshoppingbus }" varStatus="status">
				<tr id="product_id_${status.index }">
					<td class="thumb"><img src="files/${shop.product_file_name }" /><a href="productviewservlet?option=1&productname=${shop.product_id }">${myshopproduct[status.index].ep_name }</a></td>
					<td class="price" id="price_id_0">
						<span>￥${shop.product_price }</span>
						<input type="hidden" value="${myshopproduct[status.index].ep_price }" name="price" />
						<input type="hidden" value="${shop.id }" name="id"/>
					</td>
					<td class="number">
                        <span name="del">-</span>
                        <input id="number_id_0" type="text" name="number" value="${shop.product_count }" />
                        <span name="add">+</span>
					</td>
					<td class="delete"><a href="javascript:void(0)">删除</a></td>
				</tr>
				</c:forEach>
			</table>
            <div class="total"><span id="total">总计：￥0</span></div>
			<div class="button"><input type="submit" value="" /></div>
		</form>
	</div>
	<script type="text/javascript">
		document.write("Cookie中记录的购物车商品ID："+ getCookie("product") + "，可以在动态页面中进行读取");
	</script>
</div>
<div id="footer">
	Copyright &copy; 2016 ZR All Rights Reserved. 沪ICP证1000001号
</div>
</body>
</html>