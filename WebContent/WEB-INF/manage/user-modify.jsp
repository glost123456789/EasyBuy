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
<script type="text/javascript" src="scripts/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="scripts/function.js"></script>
<title>后台管理 - 易买网</title>
</head>
<body>
<div id="header" class="wrap">
	<div id="logo"><img src="images/logo.gif" /></div>
	<div class="help"><a href="indexservlet">返回前台页面</a></div>
	<div class="navbar">
		<ul class="clearfix">
			<li><a href="easybuyuserservlet?option=6">首页</a></li>
			<li class="current"><a href="easybuyuserservlet?option=7">用户</a></li>
			<li><a href="productviewservlet?option=2">商品</a></li>
			<li><a href="orderservlet?option=1">订单</a></li>
			<li><a href="commentservlet?option=3">留言</a></li>
			<li><a href="newsservlet?option=2">新闻</a></li>
		</ul>
	</div>
</div>
<div id="childNav">
	<div class="welcome wrap">
		管理员${myuser.eu_user_id }您好，今天是
		<jsp:useBean id="now" class="java.util.Date" scope="page"/>
		<fmt:formatDate value="${now}" pattern="yyyy年MM月dd日" />，欢迎回到管理后台。
	</div>
</div>
<div id="position" class="wrap">
	您现在的位置：<a href="indexservlet">易买网</a> &gt; 管理后台
</div>
<div id="main" class="wrap">
	<div id="menu-mng" class="lefter">
		<div class="box">
			<dl>
				<dt>用户管理</dt>
				<dd><a href="easybuyuserservlet?option=7">用户管理</a></dd>
			    <dt>商品信息</dt>
				<dd><em><a href="categoryservlet?option=2">新增</a></em><a href="categoryservlet?option=1">分类管理</a></dd>
				<dd><em><a href="productviewservlet?option=6">新增</a></em><a href="productviewservlet?option=2">商品管理</a></dd>
				<dt>订单管理</dt>
				<dd><a href="orderservlet?option=1">订单管理</a></dd>
				<dt>留言管理</dt>
				<dd><a href="commentservlet?option=3">留言管理</a></dd>
				<dt>新闻管理</dt>
				<dd><em><a href="newsservlet?option=6">新增</a></em><a href="newsservlet?option=2">新闻管理</a></dd>
			</dl>
		</div>
	</div>
	<div class="main">
		<h2>修改用户</h2>
		<div class="manage">
			<form id="regForm" action="easybuyuserservlet?option=10" method="post">
				<table class="form">
					<tr>
						<td class="field">用户名(*)：</td>
						<td><input type="text" class="text" name="userId" value="${modifyuser.eu_user_id }" readonly="readonly" /></td>
					</tr>
					<tr>
						<td class="field">真实姓名(*)：</td>
						<td><input type="text" class="text" name="userName" value="${modifyuser.eu_user_name }" /><span></span></td>
					</tr>
					<tr>
						<td class="field">登录密码(*)：</td>
						<td><input type="text" class="text" name="password" id="password" value="${modifyuser.eu_password }" /><span></span></td>
					</tr>
                    <tr>
						<td class="field">确认密码(*)：</td>
						<td><input type="text" class="text" name="confirmPassword" value="${modifyuser.eu_password }" /><span></span></td>
					</tr>
					<tr>
						<td class="field">性别(*)：</td>
						<td><input type="radio" name="sex" value="1" checked="checked" />男 <input type="radio" name="sex" value="1" />女</td>
					</tr>
					<tr>
						<td class="field">出生日期：</td>
						<td>
							<input id="birthday" class="text" type="text" name="birthday" value="${modifyuser.eu_birthday }" /><span></span>
						</td>
					</tr>
					<tr>
						<td class="field">手机(*)：</td>
						<td><input type="text" class="text" name="mobile" value="${modifyuser.eu_mobile }" /><span></span></td>
					</tr>
					<tr>
						<td class="field">地址(*)：</td>
						<td><input type="text" class="text" name="address" value="${modifyuser.eu_address }" /><span></span></td>
					</tr>					
					<tr>
						<td></td>
						<td><label class="ui-blue"><input type="submit" name="submit" value="更新" /></label></td>
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