<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/content.css">
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
		
		#up a:VISITED,#up a:LINK {
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
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.8.3.js"></script>
	<script type="text/javascript">
		
	</script>
  </head>
  
  <body>
  	<div id="up">
	   <ul>
	    	<li><span><a href="admin/welcome">平台首页 </a>&gt; 商品管理 &gt; 商品列表</span></li>
	   </ul>
	</div>
	<div id="down">
	    <table width="1000px" cellpadding="0" cellspacing="0">
	    	<thead>
		    	<tr class="head">
		    		<td>序号</td>
		    		<td>商品名称</td>
		    		<td>商品价格</td>
		    		<td>商品库存</td>
		    		<td>商品销量</td>
		    		<td>商品折扣</td>
		    		<td>商品状态</td>
		    		<td>商品类别</td>
		    		<td>操作</td>
		    	</tr>
	    	</thead>
	    	<tbody>
				<c:forEach items="${products }" var="product" varStatus="i">
			    	<tr title="商品详情：${product.describe }商品售价：${product.price * product.discount * 0.1 }">
			    		<td>${i.index + 1 }</td>
			    		<td>${product.name }</td>
			    		<td>${product.price }</td>
			    		<td>${product.inventory }</td>
			    		<td>${product.sales }</td>
			    		<td>${product.discount }</td>
			    		<td>
			    			<c:if test="${product.status eq 1 }">上架</c:if>
			    			<c:if test="${product.status eq 2 }">下架</c:if>
			    		</td>
			    		<td>${product.category.name }</td>
			    		<td>
			    			<a href="product/${product.id }">商品详情</a>&nbsp;
		    				<a href="product/${product.id }/update">修改</a>&nbsp;
		    				<a href="product/${product.id }/delete" class="del">删除</a>&nbsp;
			    		</td>
			    	</tr>
				</c:forEach>
			</tbody>
	    </table>
    </div>
  </body>
</html>
