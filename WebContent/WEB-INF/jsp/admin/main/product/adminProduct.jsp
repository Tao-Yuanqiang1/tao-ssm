<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'list.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/content.css">
<style type="text/css">
#down table {
	position: relative;
	left: 50%;
	margin-left: -500px;
	top: 50px;
	border-top: 1px #999 solid;
	border-right: 1px #999 solid;
}

#down tr {
	height: 60px;
}

#down tr td {
	text-align: center;
	border-left: 1px #999 solid;
	border-bottom: 1px #999 solid;
}

#up a:VISITED, #up a:LINK {
	color: #4C8FBD;
	text-decoration: none;
}

#up a:HOVER {
	color: #4C8FBD;
	text-decoration: none;
}

.head {
	background: #438EB9;
	color: #fff;
}

tbody tr:nth-child(odd) {
	background: #e5e5e5;
}

tbody tr:nth-child(even) {
	background: #d5d5d5;
}
</style>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	
</script>
</head>

<body>
	<div id="up">
		<ul>
			<li><span><a href="admin/welcome">平台首页 </a>&gt; 订单管理 &gt;
					订单列表</span></li>
		</ul>
	</div>
	<div id="down">
		<table width="1000px" cellpadding="0" cellspacing="0">
			<thead>
				<tr class="head">
					<td>序号</td>
					<td>用户名称</td>
					<td>订单序号</td>
					<td>订单价格</td>
					<td>下单时间</td>
					<td>发货地址</td>
					<td>订单状态</td>
					<td>操作</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${orders }" var="order" varStatus="i">
					<tr title="">
						<td>${i.index + 1 }</td>
						<td>${order.user.username }</td>
						<td>${order.id }</td>
						<td>${order.price }</td>
						<td>${order.date } </td>
						<td>${order.address.base }</td>
						<td><c:if test="${order.status eq 1 }">
								<span style="color: #f00">未付款</span>
							</c:if> <c:if test="${order.status eq 2 }">
								<span>等待发货</span>
							</c:if> <c:if test="${order.status eq 3 }">
								<span>卖家已发货</span>
							</c:if> <c:if test="${order.status eq 4 }">
								<span>退款处理中</span>
							</c:if> <c:if test="${order.status eq 5 }">
								<span>卖家已退款</span>
							</c:if>
						</td>
						<td>
							<c:if test="${order.status eq 2 }">
								<a href="admin/${order.id }/delivery">发货</a>&nbsp; 
							</c:if>
							<c:if test="${order.status eq 4 }">
								<a href="admin/${order.id }/refund" class="del">退款</a>
							</c:if>																	
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>
