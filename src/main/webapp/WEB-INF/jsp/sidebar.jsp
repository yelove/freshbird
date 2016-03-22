<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- 左侧导航栏部分 -->
<nav class="navbar-default navbar-side" role="navigation">
	<div class="sidebar-collapse">
		<ul class="nav" id="main-menu">
			<li>
				<div class="user-img-div">
					<!-- <img src="assets/img/user.png" class="img-thumbnail" /> -->
					<div class="inner-text">
						唐健 <br /> <small>最后一次登录 : 一天前 </small>
					</div>
				</div>
			</li>
			<li><a class="active-menu" href="main"> <i
					class="fa fa-dashboard "></i>公司首页
			</a></li>
			<li><a href="#"><i class="fa fa-desktop "></i>接待客服 <span
					class="fa arrow"></span></a>
				<ul class="nav nav-second-level">
					<li><a href="suborder"><i class="fa fa-coffee"></i>订单业务</a></li>
					<li><a href="graborder"><i class="fa fa-coffee "></i>抢单页面</a></li>
					<li><a href="#"><i class="fa fa-coffee "></i>我的抢单</a></li>
				</ul></li>
			<li><a href="#"><i class="fa fa-yelp "></i>抢单客服 <span
					class="fa arrow"> </span></a>
				<ul class="nav nav-second-level">
					<li><a href="#"><i class="fa fa-coffee "></i>抢单页面</a></li>
					<li><a href="#"><i class="fa fa-coffee"></i>我的抢单</a></li>
					<li><a href="#"><i class="fa fa-coffee "></i>我的补单</a></li>
				</ul></li>
			<li><a href="#"><i class="fa fa-bicycle "></i>管理中心<span
					class="fa arrow"></span></a>
				<ul class="nav nav-second-level">
					<li><a href="#"><i class="fa fa-coffee "></i>管理员管理</a></li>
					<li><a href="#"><i class="fa fa-coffee "></i>管理员抢单</a></li>
					<li><a href="#"><i class="fa fa-coffee "></i>信息统计</a></li>
					<li><a href="#"><i class="fa fa-coffee "></i>密码修改</a></li>
				</ul></li>
		</ul>
	</div>
</nav>