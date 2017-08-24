<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="scripts/jquery.js"></script>
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
<div id="position0" class="wrap">
	您现在的位置：<a href="servlet">易买网</a> &gt; 结账
</div>
<div id="main" class="wrap">
	<div class="lefter">
		<div class="box">
			<h2>商品分类</h2>
			<dl>
				<dt>图书音像</dt>
				<dd><a href="productviewservlet?option=7&categoryid=17">图书</a></dd>
				<dd><a href="productviewservlet?option=7&categoryid=18">音乐</a></dd>
				<dt>百货</dt>
				<dd><a href="productviewservlet?option=7&categoryid=3">运动健康</a></dd>
				<dd><a href="productviewservlet?option=7&categoryid=4">服装</a></dd>
				<dd><a href="productviewservlet?option=7&categoryid=5">家居</a></dd>
				<dd><a href="productviewservlet?option=7&categoryid=6">美妆</a></dd>
				<dd><a href="productviewservlet?option=7&categoryid=7">母婴</a></dd>
				<dd><a href="productviewservlet?option=7&categoryid=8">食品</a></dd>
				<dd><a href="productviewservlet?option=7&categoryid=9">手机数码</a></dd>
				<dd><a href="productviewservlet?option=7&categoryid=10">家具首饰</a></dd>
				<dd><a href="productviewservlet?option=7&categoryid=11">手表饰品</a></dd>
				<dd><a href="productviewservlet?option=7&categoryid=12">鞋包</a></dd>
				<dd><a href="productviewservlet?option=7&categoryid=13">家电</a></dd>
				<dd><a href="productviewservlet?option=7&categoryid=14">电脑办公</a></dd>
				<dd><a href="productviewservlet?option=7&categoryid=15">玩具文具</a></dd>
				<dd><a href="productviewservlet?option=7&categoryid=16">汽车用品</a></dd>
			</dl>
		</div>
	</div>
</div>
<div id="news" class="right-main">
		<h1>&nbsp;</h1>
		<div class="content">
            <form action="shoppingservlet?option=6" method="post">
                收货地址:<input name="addr" id="addr" type="button"  value="添加新地址" />
                <span id="span"></span> <br />
                <input name="address" type="radio" id="address0" checked="checked" /><span>${myuser.eu_address }</span><br />
                <c:forEach var="address" items="${addresses }">
                	<input name="address" id="address1" type="radio" value="${address.ad_address }"/><span>${address.ad_address }</span><br />
                </c:forEach>
                <div class="button">  <input type="submit" value="结账" />    </div>
            </form>
		</div>
	</div>
<div class="clear"></div>
<div id="position1" class="wrap"></div>
<div class="wrap">
	<div id="shopping"></div>
</div>
<div id="footer">
	Copyright &copy; 2016 ZR All Rights Reserved. 沪ICP证1000001号
</div>
</body>
</html>