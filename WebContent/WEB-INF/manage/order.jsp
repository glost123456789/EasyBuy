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
			<li class="current"><a href="orderservlet?option=1">订单</a></li>
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
		<h2>订单管理</h2>
		<div class="manage">
			<div class="search">				
			</div>
			<div class="spacer"></div>
            <form id="orderForm" method="post"  action="orderservlet?option=2">
                 订单号：<input type="text" class="text" name="entityId" id="entityId" />
                 订货人：<input type="text" class="text" name="userName" />
                 <label class="ui-blue"><input type="submit" name="submit" value="查询" /><span>${orderMsg }</span></label>
            </form>
            <c:choose>
            	<c:when test="${empty singleorder }">
            		<table class="list">
				<c:forEach var="order" items="${details }" varStatus="orderstatus">
				<c:set var="myrowspan" value="0"></c:set>
				<c:forEach var="detailrow" items="${order.orderdetails }" varStatus="detailstatus">		
							<c:set var="myrowspan" value="${myrowspan+1 }"></c:set>
				</c:forEach>
				<tr>
					<th colspan="2">单号：${order.myOrder.eo_id }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 时间：${order.myOrder.eo_create_time }</th>					
					<th colspan="2">状态:<span><c:if test="${order.myOrder.eo_status==1 }">待审核</c:if>
										<c:if test="${order.myOrder.eo_status==2 }">审核通过</c:if>
										<c:if test="${order.myOrder.eo_status==3 }">配货</c:if>
										<c:if test="${order.myOrder.eo_status==4 }">发货</c:if>
										<c:if test="${order.myOrder.eo_status==5 }">收货确认</c:if></span>
										<c:if test="${order.myOrder.eo_status!=5 }">
										<select onchange="return changeorderstatus(this,${order.myOrder.eo_id })">						    
												<option value="1" <c:if test="${order.myOrder.eo_status==1 }">selected</c:if> >待审核</option>
												<option value="2" <c:if test="${order.myOrder.eo_status==2 }">selected</c:if> >审核通过</option>
												<option value="3" <c:if test="${order.myOrder.eo_status==3 }">selected</c:if> >配货</option>
												<option value="4" <c:if test="${order.myOrder.eo_status==4 }">selected</c:if>>发货</option>
												<option value="5" <c:if test="${order.myOrder.eo_status==5 }">selected</c:if> >收货确认</option></select>							
										</c:if></th>					
				</tr>				
					<c:set var="trowspan" value="1"></c:set>
					<c:forEach	var="detail" items="${order.orderdetails }" varStatus="detailstatus">
						<tr>
							<td class="first w4 c"><img src="files/${order.product[detailstatus.index].ep_file_name }" />${order.product[detailstatus.index].ep_name }</td>
							<td>${detail.eod_cost }</td>
							<td>${detail.eod_quantity }</td>
							<c:if test="${trowspan==1 }"><td class="w1 c" rowspan="${myrowspan }">总计：${order.myOrder.eo_cost }</td></c:if>	
							<c:set var="trowspan" value="${trowspan+1 }"></c:set>				
						</tr>
					</c:forEach>
					<c:remove var="trowspan"/>
				</c:forEach>		
			</table>
			<div class="pager">
				<ul class="clearfix">
					<li><a href="orderservlet?option=1&currentpage=1">首页</a></li>
					<li>...</li>
					<c:choose>
						<c:when test="${totalpage<=6 }">
						<c:forEach begin="1" end="${totalpage }" var="pageNumber">
							<c:choose>
								<c:when test="${currentpage==pageNumber }">
									<li><a class="current">${pageNumber }</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="orderservlet?option=1&currentpage=${pageNumber }">${pageNumber }</a></li>
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
									<li><a href="orderservlet?option=1&currentpage=${pageNumber }">${pageNumber }</a></li>
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
									<li><a href="orderservlet?option=1&currentpage=${pageNumber }">${pageNumber }</a></li>
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
									<li><a href="orderservlet?option=1&currentpage=${pageNumber }">${pageNumber }</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:if>
						</c:otherwise>
					</c:choose>
                    <li>...</li>
					<li><a href="orderservlet?option=1&currentpage=${totalpage }">尾页</a></li>
				</ul>
			</div>
            	</c:when>
            	<c:otherwise>
            		<table class="list">
				<c:forEach var="order" items="${details }" varStatus="orderstatus">
				<c:set var="myrowspan" value="0"></c:set>
				<c:forEach var="detailrow" items="${order.orderdetails }" varStatus="detailstatus">		
							<c:set var="myrowspan" value="${myrowspan+1 }"></c:set>
				</c:forEach>
				<tr>
					<th colspan="2">单号：${order.myOrder.eo_id }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 时间：${order.myOrder.eo_create_time }</th>					
					<th colspan="2">状态:<span id="thisorderstatus"><c:if test="${order.myOrder.eo_status==1 }">待审核</c:if>
										<c:if test="${order.myOrder.eo_status==2 }">审核通过</c:if>
										<c:if test="${order.myOrder.eo_status==3 }">配货</c:if>
										<c:if test="${order.myOrder.eo_status==4 }">发货</c:if>
										<c:if test="${order.myOrder.eo_status==5 }">收货确认</c:if></span>
										<c:if test="${order.myOrder.eo_status!=5 }"><select name="status" >						    
												<option value="1" <c:if test="${order.myOrder.eo_status==1 }">selected</c:if> >待审核</option>
												<option value="2" <c:if test="${order.myOrder.eo_status==2 }">selected</c:if> >审核通过</option>
												<option value="3" <c:if test="${order.myOrder.eo_status==3 }">selected</c:if> >配货</option>
												<option value="4" <c:if test="${order.myOrder.eo_status==4 }">selected</c:if>>发货</option>
												<option value="5" <c:if test="${order.myOrder.eo_status==5 }">selected</c:if> >收货确认</option></select>							
										</c:if></th>					
				</tr>				
					<c:set var="trowspan" value="1"></c:set>
					<c:forEach	var="detail" items="${order.orderdetails }" varStatus="detailstatus">
						<tr>
							<td class="first w4 c"><img src="files/${order.product[detailstatus.index].ep_file_name }" />${order.product[detailstatus.index].ep_name }</td>
							<td>${detail.eod_cost }</td>
							<td>${detail.eod_quantity }</td>
							<c:if test="${trowspan==1 }"><td class="w1 c" rowspan="${myrowspan }">总计：${order.myOrder.eo_cost }</td></c:if>	
							<c:set var="trowspan" value="${trowspan+1 }"></c:set>				
						</tr>
					</c:forEach>
					<c:remove var="trowspan"/>
				</c:forEach>		
				</table>
            	</c:otherwise>
            </c:choose>
			
		</div>
	</div>
	<div class="clear"></div>
</div>
<div id="footer">
	Copyright &copy; 2016 ZR All Rights Reserved. 沪ICP证1000001号
</div>
</body>
</html>