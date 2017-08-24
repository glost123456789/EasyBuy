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
<title>后台管理 - 易买网</title>
</head>
<body>
<div id="header" class="wrap">
	<div id="logo"><img src="images/logo.gif" /></div>
	<div class="help"><a href="indexservlet">返回前台页面</a></div>
	<div class="navbar">
		<ul class="clearfix">
			<li><a href="easybuyuserservlet?option=6">首页</a></li>
			<li><a href="easybuyuserservlet?option=7">用户</a></li>
			<li><a href="productviewservlet?option=2">商品</a></li>
			<li><a href="orderservlet?option=1">订单</a></li>
			<li class="current"><a href="commentservlet?option=3">留言</a></li>
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
		<h2>留言管理</h2>
		<div class="manage">
			<table class="list">
				<tr>
					<th>ID</th>
					<th>姓名</th>
					<th>留言内容</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
				<c:forEach var="comment" items="${comments }" varStatus="status">
					<tr>
					<td class="first w4 c">${status.index }</td>
					<td class="w1 c">${comment.ec_nick_name }</td>
					<td>${comment.ec_content }</td>
					<c:choose>
						<c:when test="${not empty comment.ec_reply }">
							<td class="w1 c">已回复</td>
							<td class="w1 c"><a href="commentservlet?option=4&id=${comment.ec_id }">修改</a> <a class="manageDel" href="commentservlet?option=6&id=${comment.ec_id }">删除</a></td>
						</c:when>
						<c:otherwise>
							<td class="w1 c"></td>
							<td class="w1 c"><a href="commentservlet?option=4&id=${comment.ec_id }">回复</a> <a class="manageDel" href="commentservlet?option=6&id=${comment.ec_id }">删除</a></td>
						</c:otherwise>
					</c:choose>			
				</tr>
				</c:forEach>
		
			</table>
			<div class="pager">
				<ul class="clearfix">
					<li><a href="commentservlet?option=3&currentpage=1">首页</a></li>
					<li>...</li>
					<c:choose>
						<c:when test="${totalpage<6 }">
						<c:forEach begin="1" end="${totalpage }" var="pageNumber">
							<c:choose>
								<c:when test="${currentpage==pageNumber }">
									<li><a class="current">${pageNumber }</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="commentservlet?option=3&currentpage=${pageNumber }">${pageNumber }</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						</c:when>
						<c:otherwise>
								<c:if test="${currentpage-3>0 && currentpage+3<totalpage}">
						<c:forEach begin="${currentpage-3 }" end="${currentpage+3 }" var="pageNumber">
							<c:choose>
								<c:when test="${currentpage==pageNumber }">
									<li><a class="current">${pageNumber }</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="commentservlet?option=3&currentpage=${pageNumber }">${pageNumber }</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:if>
					<c:if test="${currentpage-3<=0 }">
						<c:forEach begin="1" end="6" var="pageNumber">
							<c:choose>
								<c:when test="${currentpage==pageNumber }">
									<li><a class="current">${pageNumber }</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="commentservlet?option=3&currentpage=${pageNumber }">${pageNumber }</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:if>
					<c:if test="${currentpage-3>=totalpage }">
						<c:forEach begin="${totalpage-5 }" end="${totalpage }" var="pageNumber">
							<c:choose>
								<c:when test="${currentpage==pageNumber }">
									<li><a class="current">${pageNumber }</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="commentservlet?option=3&currentpage=${pageNumber }">${pageNumber }</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:if>
						</c:otherwise>
					</c:choose>
                    <li>...</li>
					<li><a href="commentservlet?option=3&currentpage=${totalpage }">尾页</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2016 ZR All Rights Reserved. 沪ICP证1000001号
</div>
</body>
</html>