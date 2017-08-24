<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			<li><a href="indexservlet">首页</a></li>
			<li><a href="productviewservlet?option=7&categoryid=17">图书</a></li>
			<li><a href="productviewservlet?option=7&categoryid=1">百货</a></li>
			<li class="current"><a href="mainservlet?option=1">品牌</a></li>
			<li><a href="mainservlet?option=4">促销</a></li>
		</ul>
	</div>
</div>
<div id="childNav">
	<div class="wrap">
		<ul class="clearfix">
			<li class="first"><a href="mainservlet?option=3&brandid=1">华为</a></li>
			<li><a href="mainservlet?option=3&brandid=2">三星</a></li>
			<li><a href="mainservlet?option=3&brandid=3">苹果</a></li>
			<li><a href="mainservlet?option=3&brandid=4">得力</a></li>
			<li><a href="mainservlet?option=3&brandid=5">长城</a></li>
			<li><a href="mainservlet?option=3&brandid=6">德芙</a></li>
			<li><a href="mainservlet?option=3&brandid=7">联想</a></li>
			<li><a href="mainservlet?option=3&brandid=8">奔驰</a></li>
			<li><a href="mainservlet?option=3&brandid=9">奥迪</a></li>
			<li><a href="mainservlet?option=3&brandid=10">优衣库</a></li>
			<li><a href="mainservlet?option=3&brandid=11">耐克</a></li>
			<li><a href="mainservlet?option=3&brandid=12">乔丹</a></li>
			<li><a href="mainservlet?option=3&brandid=13">唐狮</a></li>
			<li><a href="mainservlet?option=3&brandid=14">阿迪达斯</a></li>
			<li><a href="mainservlet?option=3&brandid=15">360°</a></li>
			<li><a href="mainservlet?option=3&brandid=16">新百伦</a></li>
			<li><a href="mainservlet?option=3&brandid=17">捷安特</a></li>
			<li class="last"><a href="#">Investor Relations</a></li>
		</ul>
	</div>
</div>
<div id="position" class="wrap">
	您现在的位置：<a href="indexservlet">易买网</a> &gt;品牌   <c:if test="${not empty thisbrand }">&gt; ${thiscategory.epc_name }</c:if>
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
		<div class="spacer"></div>
		<div class="last-view">
			<h2>最近浏览</h2>
				<dl class="clearfix">
				<c:forEach var="lastproduct" items="${lastproducts }">
					<dt><img width="50px" height="50px" src="files/${lastproduct.ep_file_name }" /></dt>
					<dd><a href="productviewservlet?productname=${lastproduct.ep_id }&option=1"  target="_self">${lastproduct.ep_name }</a></dd>
					<dt>&nbsp;</dt>
					<dd>&nbsp;</dd>
				</c:forEach>
		 		 </dl>
			<script type="text/javascript">
				document.write("Cookie中记录的购物车商品ID："+ getCookie("product") + "，可以在动态页面中进行读取");
			</script>
		</div>
	</div>
	<div class="main">
		<div class="product-list">
			<div> 
				<h3 style="float:left" class="pinpai">全部商品 </h3>
				<form action="mainservlet?option=3" style="float:right"class="query" method="post">品牌搜索：<input type="text" name="querybrand">&nbsp&nbsp<input type="submit" value="搜索"><span id="BrandErrMsg" class="BrandMsg"></span></form>	
				<hr>	
		   </div>
			<div class="clear"></div>
			<ul class="product clearfix">
			<c:forEach var="product" items="${products }">
				<li>
					<dl>
						<dt><a href="productviewservlet?productname=${product.ep_id }&option=1" target="_self"><img src="files/${product.ep_file_name }" /></a></dt>
						<dd class="title"><a href="productviewservlet?productname=${product.ep_id }&option=1" target="_self">${product.ep_name }</a></dd>
						<dd class="price">￥${product.ep_price }&nbsp&nbsp${product.ep_brand }</dd>
					</dl>
				</li>
			</c:forEach>
			</ul>
			<div class="clear"></div>
			<div class="pager">
				<ul class="clearfix">
					<li><a href="mainservlet?option=2&currentpage=1">首页</a></li>
					<li>...</li>
					<c:choose>
						<c:when test="${totalpage<6 }">
						<c:forEach begin="1" end="${totalpage }" var="pageNumber">
							<c:choose>
								<c:when test="${currentpage==pageNumber }">
									<li><a class="current">${pageNumber }</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="mainservlet?option=2&currentpage=${pageNumber }">${pageNumber }</a></li>
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
									<li><a href="mainservlet?option=2&currentpage=${pageNumber }">${pageNumber }</a></li>
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
									<li><a href="mainservlet?option=2&currentpage=${pageNumber }">${pageNumber }</a></li>
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
									<li><a href="mainservlet?option=2&currentpage=${pageNumber }">${pageNumber }</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:if>
						</c:otherwise>
					</c:choose>
                    <li>...</li>
					<li><a href="mainservlet?option=2&currentpage=${totalpage }">尾页</a></li>
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