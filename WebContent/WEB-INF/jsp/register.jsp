<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="scripts/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="scripts/My97DatePicker/WdatePicker.js"></script>
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
<div id="register" class="wrap">
	<div class="shadow">
		<em class="corner lb"></em>
		<em class="corner rt"></em>
		<div class="box">
			<h1>欢迎注册易买网</h1>
			<ul class="steps clearfix">
				<li class="current"><em></em>填写注册信息</li>
				<li class="last"><em></em>注册成功</li>
			</ul>
			<form id="regForm" method="post" action="easybuyuserservlet?option=2" >
				<table>
					<tr>
						<td class="field">用户名(*)：</td>
						<td>
							<input class="text" type="text" name="userId"  id="userId" maxlength="10"  />
							<span></span>
						</td>
					</tr>
					<tr>
						<td class="field">真实姓名(*)：</td>
						<td><input class="text" type="text" name="userName" /><span></span></td>
					</tr>
					<tr>
						<td class="field">登录密码(*)：</td>
						<td><input class="text" type="password" id="password" name="password" /><span></span></td>
					</tr>
					<tr>
						<td class="field">确认密码(*)：</td>
						<td><input class="text" type="password" name="confirmPassword" /><span></span></td>
					</tr>
					<tr>
						<td class="field">性别(*)：</td>
						<td>
						  <input class="radio" type="radio" name="sex" value="male" checked="checked">
						  男性</input>
						  <input class="radio" type="radio" name="sex" value="female"						  
						  >女性</input>
						  <span></span></td>
					</tr>
					<tr>
						<td class="field">出生日期：</td>
						<td><input id="birthday" class="text" type="text" name="birthday" /><span></span></td>
					</tr>
					<tr>
						<td class="field">身份证：</td>
						<td><input class="text" type="text" name="identityCode" /><span></span></td>
					</tr>
					<tr>
						<td class="field">电子邮件：</td>
						<td><input class="text" type="text" name="email" /><span></span></td>
					</tr>
					<tr>
						<td class="field">手机(*)：</td>						
						<td><input class="text" type="text" name="mobile" /><span></span></td>
					</tr>
					<tr>
						<td class="field">收货地址(*)：</td>
						<td><input class="text" type="text" name="address" /><span></span></td>
					</tr>
					<tr>
						<td></td>
						<td><label class="ui-green"><input type="submit" name="submit" value="提交注册" /></label></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2016 ZR All Rights Reserved. 沪ICP证1000001号
</div>
</body>
</html>