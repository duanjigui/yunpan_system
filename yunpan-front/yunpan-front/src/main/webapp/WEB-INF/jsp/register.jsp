<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>注册页面</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.3.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js" ></script>
		<style type="text/css">
			.container{
				min-height: 576px;
			}
			.padding_top{
				padding-top: 70px;
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
		      <img  class="navbar-brand" alt="Brand" src="images/logo.jpg" width="50px" height="100px">
		      <a class="navbar-brand" href="#">云盘-system</a>
		    </div>
		
		    <!-- Collect the nav links, forms, and other content for toggling -->
		    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		      <ul class="nav navbar-nav navbar-right">
		         <li><a href="${pageContext.request.contextPath}/index">我已注册,去登录</a></li>
		      </ul>
		    </div><!-- /.navbar-collapse -->
		  </div><!-- /.container-fluid -->
		</nav>
		<!--内容注册部分 -->
		<div class="container">
			<div class="col-lg-6 col-lg-offset-3 padding_top">
				<!--注册内容 -->
				<form class="form-horizontal" id="form">
				  <div class="form-group">
				    <label for="error_box" class="col-sm-6 control-label" id="error_box" style="color: red"></label>
				  </div>
				  
				  <div class="form-group">
				    <label for="inputEmail3" class="col-sm-2 control-label">用户邮箱</label>
				    <div class="col-sm-10">
				      <input type="email" class="form-control" id="Email" placeholder="请输入你要注册的邮箱" name="user_email" >
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="inputPassword3" class="col-sm-2 control-label">手机号码</label>
				    <div class="col-sm-10">
				      <input type="tel" class="form-control" id="Phone" placeholder="请输入你要注册的手机号码" name="user_phone">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="inputPassword3" class="col-sm-2 control-label">昵称</label>
				    <div class="col-sm-10">
				      <input type="text" class="form-control" id="nickName" placeholder="请输入你要注册的昵称" name="user_name">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
				    <div class="col-sm-10">
				      <input type="password" class="form-control" id="Password" placeholder="请输入你的密码" name="user_pwd">
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="inputPassword3" class="col-sm-2 control-label">确认密码</label>
				    <div class="col-sm-10">
				      <input type="password" class="form-control" id="enterPassword" placeholder="请输入你的确认密码" name="ent_user_pwd">
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="col-sm-offset-2 col-sm-10">
				      <div class="checkbox">
				        <label>
				          <input type="checkbox" id="reader"> 我已阅读<<用户注册协议>>
				        </label>
				      </div>
				    </div>
				  </div>
				  <div class="form-group">
				    <div class="col-sm-offset-5 col-sm-10">
				      <button type="button" class="btn btn-primary disabled" id="register">确认注册</button>
				    </div>
				  </div>
				</form>
				
				
			</div>
		</div>
		
		<!--版权声明 -->
		<div class="foot">
			<span>Copyright © yunpan-system 2016-2017</span>
		</div>
		
		<script type="text/javascript">

			$(function(){

				$("#register").click(function(){

					//首先进行数据校验
					
					var email = $("#Email").val()
					var Phone = $("#Phone").val()
					var nickName = $("#nickName").val()
					var Password = $("#Password").val()
					var enterPassword = $("#enterPassword").val()
					var reader = $("#reader").prop("checked")  //选中为true
					
					var email_reg= /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
					var phone_reg=/^1\d{10}$/;
					var password=/^[a-zA-Z0-9]{6,32}$/;

					if(!email_reg.test(email)){
						$("#error_box").text("邮箱格式不正确！")
					}else if(!phone_reg.test(Phone)){
						$("#error_box").text("手机号格式不正确！")
					}else if(!password.test(Password)){
						$("#error_box").text("密码长度应该为6-32位！")
					}else if(!password.test(nickName)){
						$("#error_box").text("昵称长度应该为6-32位！")
					}else if(enterPassword != Password){
						$("#error_box").text("两次密码输入的不一致！")
					}else if(reader ==false){
						$("#error_box").text("请阅读协议！")
					}else{
						$.post("${pageContext.request.contextPath}/Register",$("#form").serialize(),function(data){
							if(data.registe_result =="success"){
								alert(data.emaill + " 创建成功！")
								window.location.href="${pageContext.request.contextPath}/index"
							}else{
								$("#error_box").text(data.validate_error_message)
							}

					},"json")

					}
					
						
				})



			})






		</script>
		
	</body>
</html>
