<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>资源分享页面</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
		<link href="${pageContext.request.contextPath}/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.3.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/cookieUtil.js" ></script>
   		<script src="${pageContext.request.contextPath}/js/jquery.ztree.all.min.js" type="text/javascript"></script>
		<style type="text/css">
			.middle_height{
				min-height: 550px;
			}
			.strong{
				font-weight: bold;
			}
			.red{
				color: red;
			}
			.margin_top{
				margin-top: 6px;
			}
			.row.margin_top .col-lg-5 label{
				float: left;
			}		
			label~ul{
				list-style: none;
			}		
			label~ul li{
				float: left;
				cursor: pointer;
			}
			label~ul li:after{
				float: left;
				content: "/";
				padding:0 4px;
			}
			.panel.panel-primary .panel-body table{
				width: 100%;
				text-align: center;
				margin-bottom: 10px;
			}
			.panel.panel-primary .panel-body table tr{
				height: 40px;
				border-bottom: 1px solid rgb(221, 221, 221);
			}
			.panel.panel-primary .panel-body table tr td.mis{
				width: 20px;
			}
			.panel.panel-primary .panel-body table tr td.min{
				width: 150px;
			}
			
			.foot{
				color:black;
				width: 100%;
				text-align: center;
				margin-bottom: 10px;
			}
			
		</style>
	</head>
	<body>
		<nav class="navbar navbar-default" role="navigation">
		  <div class="container-fluid">
		    <!-- Brand and toggle get grouped for better mobile display -->
		    <div class="navbar-header">
		      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
		        <span class="sr-only">Toggle navigation</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		      </button>
		      <img  class="navbar-brand" alt="Brand" src="${pageContext.request.contextPath}/images/logo.jpg" width="50px" height="100px">
		      <a class="navbar-brand" href="#">云盘-system</a>
		    </div>
		
		    <!-- Collect the nav links, forms, and other content for toggling -->
		    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		      <ul class="nav navbar-nav navbar-right">
		         <li><a href="#">登录</a></li>
		         <li><a href="#">注册</a></li>
		      </ul>
		    </div><!-- /.navbar-collapse -->
		  </div><!-- /.container-fluid -->
		</nav>
		<div class="container-fluid middle_height">
			<h1>该文件已被删除！</h1>			
		</div>
			
		<div class="foot">
			<span>Copyright © yunpan-system 2016-2017</span>
		</div>
	</body>
</html>
