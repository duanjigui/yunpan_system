<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>资源分享页面（输入密码页面）</title>
		<style type="text/css">
			body,html{
				margin: 0;
				padding: 0;
				width: 100%;
				height: 100%;
				background: #eef2f6;
				font-family: "微软雅黑";
			}
			.center{
				margin: 0 auto;
				width: 400px;
				padding:150px 0px;
			}
			.center .title{
				margin: 0 auto;
				width: 222px;
			}
			.title span{
				font-family:"黑体";
				font-weight: bold;
				font-size: 24px;
				line-height: 50px;
			}
			.content{
				margin-top: 20px;
				box-shadow: 0px 0px 10px rgba(171,198,235,.3);
			}
			.content .head{
				font-size: 13px;
				background: #438aee;
				padding: 10px;
				color: white;
			}
			.content .head img{
				border-radius:50%;
				vertical-align: middle;
			}
			.content .head span{
				margin-left: 10px;
			}
			.content .body{
				padding: 10px;
				background: white;
				height: 200px;
			}
			.content .body input{
				width: 280px;
				height: 35px;
				padding: 0 4px;
			}
			.content .body button{
				width: 80px;
				height: 35px;
				border-radius: 8px;
				background: #438aee;
				color: white;
			}
			.foot{
				position: absolute;
				bottom: 10px;
				width: 100%;
				text-align: center;
				font-family: "微软雅黑";
			}
		</style>
	</head>
	<body>
		<div class="center">
			<div class="title">
				<img src="${pageContext.request.contextPath}/images/logo.jpg" width="50px" height="50px" style="vertical-align: middle;"/> 
				<span>云盘-system</span>
			</div>
			<div class="content">
				<div class="head">
					<img src="${pageContext.request.contextPath}/images/bg.jpg" width="40px" height="40px"/>
					<span>${shared_user}</span>&nbsp;给您加密分享了文件
				</div>
				<div class="body">
					<h6>请输入提取码:</h6>
					<form action="${pageContext.request.contextPath}/files/file/enterPass" method="post">
						<input type="hidden" name="shareResourceId" value="${shareResourceBeans[0].share_id}" >
						<input type="hidden" name="url" value="${root_path}" >
						<input type="text" name="code" />	
						<button type="submit">提取文件</button>
					</form>
				</div>
			</div>
		</div>
		
		<div class="foot">
			<span>Copyright © yunpan-system 2016-2017</span>
		</div>
	
	
	</body>
</html>
