<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'promsg.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/item.css">
<style type="text/css">
#nav {
	height: 100px;
	border-bottom: 3px #4C8FBD solid;
	margin-bottom: 1px;
}

#c_nav {
	height: 100px;
	width: 1000px;
	position: relative;
	left: 50%;
	margin-left: -500px;
}

#c_nav div{
	float: left;
}

#logo{
	height: 59px;
	width: 117px;
	background: url("<%=request.getContextPath()%>/img/logo.png") no-repeat;
	position: relative;
	top: 20px;
	left: 50px;
}

#search{
	width: 700px;
	position: relative;
	left: 150px;
}

#in {
	height: 40px;
	width: 500px;
	position: relative;
	top: 27px;
	border-radius: 6px;
	border: 1px #DBDBDB solid;
	font-size: 20px;
}

#su{
	height: 40px;
	width: 120px;
	background: #4C8FBD;
	font-size: 18px;
	color: #fff;
	font-weight: bold;
	position: relative;
	top: 27px;
	border-radius: 6px;
	border: 1px #4C8FBD solid;
	cursor: pointer;
}

#con {
	margin-top: 20px;
}

#c_con {
	width: 1000px;
	position: relative;
	left: 50%;
	margin-left: -500px;
}
	

#up {
	height: 450px;
	border: 1px #4C8FBD solid;
}

#up div {
	float: left;
}

#left {
	width: 400px;
	height: 450px;
	border-right: 1px #4C8FBD solid;
}

#left_u, #left_u_big {
	margin-top: 5px;
	width: 400px;
	height: 380px;
	text-align: center;
	
}

#left_u_big {
    border: 1px red solid;
    position:absolute;
	left: 405px; /* 原图在缩略图的右边 */
	top: 0px;
	overflow: hidden; /* 隐藏溢出内容 */
	display: none;
	z-index: 10;
}
/* 放大镜框 */
/* 这里按2.5倍放大，400px/160px=2.5 */
.magnifier {
	width: 160px;
	height: 160px;
	background: rgba(254, 238, 167, .4); /* 半透明效果 */
	position: absolute;
	left: 0;
	top: 0;
	display: none;
}

#left_u img {
    width:400px;
    height:380px;
	display: none;
}
#left_u_big img {
    width:1000px;
    height:950px;
	display: none;
}

#left_d {
	width: 400px;
	text-align: center;
}

#left_d li {
	position: relative;
	top: 10px;
	width: 55px;
	height: 55px;
	float: left;
	list-style: none;
	cursor: pointer;
}

#left_d li:HOVER {
	border: 2px #4C8FBD solid;
}

#right {
	width: 590px;
	height: 450px;
}

#right table {
	margin-top: 10px;
	margin-bottom: 40px;
	margin-left: 5px;
}

.tr1 {
	height: 60px;
}

.tr2 {
	background: #FFF2E8;
	height: 45px;
}

.td1 {
	margin-top: 10px;
	font-size: 18px;
	color: #3C3C3C;
}

.td2 {
	color: #6c6c6c;
	font-size: 14px;
	width: 100px;
}

.td3 {
	font-size: 14px;
}

.td4 {
	font-size: 18px;
	color: #71150F;
}

#minus, #plus {
	width: 20px;
	font-size: 14px;
	position: relative;
	top: 10px;
}

#quantity {
	position: relative;
	top: 10px;
	width: 50px;
	font-size: 18px;
	text-align: center;
}

#right ul li {
	list-style: none;
	height: 70px;
	width: 400px;
	margin-left: 10px;
}

.lis {
	position: relative;
	top: 10px;
	font-size: 14px;
	color: #6c6c6c;
}

#btn1, #btn2 {
	background: #4C8FBD;
	width: 120px;
	height: 40px;
	font-size: 18px;
	color: #fff;
	border-radius: 6px;
	margin-right: 5px;
	border: 1px #4C8FBD solid;
	cursor: pointer;
}

#down {
	clear: both;
	height: 80px;
	width: 500px;
}

#copy {
	height: 30px;
	background: #4C8FBD;
}

#c_copy {
	text-align: center;
	color: #fff;
	font-family: arial;
	position: relative;
	top: 7px;
}
</style>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
		$(function(){
			//商品图片只显示一张大图
			$("#left_u img").css("display","none").filter(":eq(0)").show();
			//鼠标经过小图时,大图位置切换图片
			$("#left_d img").each(function(n) {
				$(this).mouseover(function(){
					$("#left_u img").css("display","none").filter(":eq(" + n + ")").show();
				});
			});
			//商品图片只显示一张大图
			$("#left_u_big img").css("display","none").filter(":eq(0)").show();
			//鼠标经过小图时,大图位置切换图片
			$("#left_d img").each(function(n) {
				$(this).mouseover(function(){
					$("#left_u_big img").css("display","none").filter(":eq(" + n + ")").show();
				});
			});
			
			var quantity = $("#quantity");
			var n = $("#n").html();
			//购买数量减事件处理
			$("#minus").click(function() {
				var num = parseInt(quantity.val());
				if(num > 1) {
					quantity.val(num - 1);
				}
			});
			//购买数量加事件处理
			$("#plus").click(function() {
				var num = parseInt(quantity.val());
				if(num < n) {
					quantity.val(num + 1);
				}
			});
			//购买数量光标失去焦点事件:判断输入的数字是否大于库存数量
			quantity.blur(function() {
				var num = parseInt(quantity.val());
				if(num > n) {
					alert("最大购买数量" + n + "个");
					$(this).val(n);
				}
			});
			
			var form = $("#form");
			//立即购买事件
			$("#btn1").click(function() {
				form.attr("action", "buy_now");
				form.submit();
			});
			//加入购物车事件
			$("#btn2").click(function(){
				$.ajax({
					type:"post",
					url:"cart/addCart",
					data:{id:${product.id}, quantity:$("#quantity").val()},
					success:function(data) {
						if(data == "yes") {
							alert("添加购物车成功");
							//页面强制刷新,用于显示或更改购物中心的商品记录数
							window.location.reload();
						}
						else {
							alert("添加购物车失败");
						}
					},
					error:function(data) {
						alert("您还没有登录,请先登录");
						//强制页面跳转
						window.location.href = "<%=request.getContextPath()%>/user/login";
					}
				});
			});
			/*
	        放大镜实现原理：
	        1. “缩略图”窗口与“原图”窗口中放置的是同一个图片，但“缩略图”窗口中的图片被缩小为400px，
	        而“原图”窗口中的图片保持原始大小，溢出部分设为隐藏
	        2. 先确定缩放比例，我们假定原图大小为1000px，缩略图大小为400px，缩放比例为2.5
	        3. 首先实现“放大镜”框跟随鼠标移动的功能（我们让鼠标处于“放大镜”框的中心）
	        4. 其次实现“原图”窗口中的图片随“放大镜”框的移动而相应移动
	        5. 鼠标向右移动，“原图”窗口中的图片向左移动，它们的方向是相反的！这是实现原理中的关键环节
	        6. “放大镜”框的大小不是随意设定的，它与放大倍数有关，如果要放大2.5倍，
	        则“原图”窗口的大小也应该是“放大镜”框的大小的2.5倍
	        */
	 
	        // 当鼠标进入“缩略图”窗口时，显示“原图”窗口和“放大镜”框
	        $('#left_u').mouseover(function (e) {
	            $('#left_u_big').css('display', 'block');
	            $('.magnifier').css('display', 'block');
	        })
	        // 当鼠标在“缩略图”窗口中移动时
	        $('#left_u').mousemove(function (e) {
	            // 一、首先实现“放大镜”框跟随鼠标移动的功能（我们让鼠标处于“放大镜”框的中心）
	 
	            // 获取鼠标当前位置
	            var pageX = e.pageX;
	            var pageY = e.pageY;
	            // 获取“缩略图”窗口在整个文档中的偏移位置
	            var offsetX = $('#left_u').offset().left;
	            var offsetY = $('#left_u').offset().top;
	            // 计算鼠标在缩略图中的相对位置
	            var relativeX = pageX - offsetX;
	            var relativeY = pageY - offsetY;
	            // 考虑到鼠标处于“放大镜”框的中心，我们要根据鼠标位置计算“放大镜”框的位置
	            var magOffsetX = $('.magnifier').width() / 2;
	            var magOffsetY = $('.magnifier').height() / 2;
	            $('.magnifier').css({ left: relativeX - magOffsetX + 'px',
	                top: relativeY - magOffsetY + 'px' });
	            // 获取“放大镜”框的新位置，后面会用到
	            var magX = $('.magnifier').position().left;
	            var magY = $('.magnifier').position().top;
	 
	            // 二、处理越界情况
	 
	            // 确定边界
	            var maxMagX = $('#left_u').width() - $('.magnifier').width()
	            var maxMagY = $('#left_u').height() - $('.magnifier').height()
	            // 左边界
	            if (magX <= 0) { $('.magnifier').css('left', '0px'); }
	            // 右边界
	            if (magX >= maxMagX) { $('.magnifier').css('left', maxMagX + 'px'); }
	            // 上边界
	            if (magY <= 0) { $('.magnifier').css('top', '0px'); }
	            // 下边界
	            if (magY >= maxMagY) { $('.magnifier').css('top', maxMagY + 'px'); }
	 
	            // 三、其次实现“原图”窗口中的图片随“放大镜”框的移动而相应移动
	 
	            // 按照之前确定的缩放比例移动“原图”窗口中的图片
	            // 注意：图片的移动方向与鼠标的移动方向是相反的！
	            var left_u_bigX = magX * 2.5;
	            var left_u_bigY = magY * 2.5;
	            $('#left_u_big img').css({ left: -left_u_bigX + 'px', top: -left_u_bigY + 'px' });
	        })
	        // 当鼠标离开“缩略图”窗口时，隐藏“原图”窗口和“放大镜”框
	        $('#left_u').mouseout(function () {
	            $('#left_u_big').css('display', 'none');
	            $('.magnifier').css('display', 'none');
	        });
		});
        
	</script>
</head>

<body>
	<jsp:include page="head.jsp"></jsp:include>
	<div id="nav">
		<div id="c_nav">
			<div id="logo"></div>
			<div id="search">
				<form action="index.html" method="post">
					<input type="text" name="name" id="in" autocomplete="off">
					<input type="submit" value="搜&nbsp;索" id="su">
				</form>
			</div>
		</div>
	</div>
	<div id="con">
		<div id="c_con">
			<div id="up">
				<div id="left">
					<div id="left_u">
						<c:forEach items="${product.imgs }" var="img">
							<img src="<%=request.getContextPath()%>/image/${img.name}"
								height="360px" />
							<div class="magnifier"></div>
						</c:forEach>
					</div>
					<div id="left_u_big">
						<c:forEach items="${product.imgs }" var="img">
							<img src="<%=request.getContextPath()%>/image/${img.name}"
								style="position: absolute;" width="400" height="360px" />
						</c:forEach>
					</div>
					<div id="left_d">
						<ul>
							<c:forEach items="${product.imgs }" var="img">
								<li><img
									src="<%=request.getContextPath()%>/image/${img.name}"
									height="55px" /></li>
							</c:forEach>
						</ul>
					</div>
				</div>
				<div id="right">
					<table width="580" cellpadding="0" cellspacing="0">
						<tr class="tr1">
							<td colspan="2" class="td1">${product.name }&nbsp;<br><font>${product.describe }</font></td>
						</tr>
						<tr class="tr2">
							<td class="td2">&nbsp;&nbsp;价格</td>
							<td class="td3">￥<del class="td3">&nbsp;${product.price }</del></td>
						</tr>
						<tr class="tr2">
							<td class="td2">&nbsp;&nbsp;优惠价</td>
							<td class="td4">￥&nbsp;<fmt:formatNumber type="number"
									value="${product.price * product.discount * 0.1 }"></fmt:formatNumber></td>
						</tr>
						<tr class="tr2">
							<td class="td2">&nbsp;&nbsp;销量</td>
							<td class="td3">${product.sales }</td>
						</tr>
					</table>
					<form method="post" id="form">
						<input type="hidden" name="id" value="${product.id }">
						<ul>
							<li><span class="lis">购买数量</span> <input type="button"
								value="-" id="minus"> <input type="text" value="1"
								name="quantity" id="quantity"> <input type="button"
								value="+" id="plus"> <span class="lis">件(库存</span><span
								class="lis" id="n">${product.inventory }</span><span class="lis">件)</span>
							</li>
							<li><input type="button" value="立即购买" id="btn1"> <input
								type="button" value="加入购物车" id="btn2"></li>
						</ul>
					</form>
				</div>
			</div>
		</div>
		<div id="down"></div>
	</div>
	<div id="copy">
		<div id="c_copy">&copy;版权所有@SXAU-陶沅玱</div>
	</div>
</body>
</html>
