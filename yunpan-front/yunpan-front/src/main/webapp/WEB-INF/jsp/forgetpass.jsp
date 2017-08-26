<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>忘记密码页面</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.3.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js" ></script>
		<style>
		.container {
			min-height: 576px;
		}
		
		* {
			font-size: 9pt;
			border: 0;
			margin: 0;
			padding: 0;
		}
		
		ul {
			display: block;
			margin: 0;
			padding: 0;
			list-style: none;
		}
		
		.project {
			height: 50px;
			background: #F2950D;
			border-radius: 3px;
			behavior: url(js/pie.htc);
			margin-bottom: 15px;
		}
		
		.proleft {
			font-size: 16px;
			line-height: 50px;
			background: #FFF3E1;
			width: 192px;
			float: left;
			text-align: center;
			border-radius: 3px;
			behavior: url(js/pie.htc);
		}
		
		.project li {
			float: left;
			font-size: 16px;
			text-align: center;
			color: #fff;
			line-height: 50px;
			background: url(./images/bz00.jpg) no-repeat right;
			height: 50px;
			position: relative;
			min-width: 163px;
		}
		
		.profinish {
			color: #000 !important;
		}
		
		li {
			display: block;
			margin: 0;
			padding: 0;
			list-style: none;
		}
		
		.project li b {
			font-size: 20px;
			font-weight: normal;
			padding-right: 6px;
		}
		
		.start {
			background: #EE5A10 !important;
		}
		
		.prover i {
			background: none !important;
		}
		
		.profinish i {
			background: url(./images/bz02.jpg) no-repeat right;
			height: 50px;
			line-height: 50px;
		}
		
		.project li i {
			width: 17px;
			padding-left: 8px;
			position: absolute;
			height: 50px;
			right: 0;
			top: 0;
		}
		
		.start i {
			background: url(./images/bz01.jpg) no-repeat right;
		}
		.choice_area{
			position: relative;
			height: 500px;
		}
		.area{
			position: absolute;
			top: 100px;
			left: 400px;
			width: 500px;
			height: 300px;		
		}
		.area .content{
			width: 400px;
			position: relative;
			top: 0px;
			left: 0px;
		}
		.area .content button.prev1{
			position: absolute;
			top: 0px;
			left: -100px;
		}
		.top{
			margin-top: 60px;
		}
		.row.content button{
			margin-left: 10px;
		}
		.row.content form.form-inline input{
			width: 275px;
		}
		
		.foot {
			color: black;
			width: 100%;
			text-align: center;
			margin-bottom: 10px;
			clear: both;
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
		         <li><a href="${pageContext.request.contextPath}/index">返回登录</a></li>
		      </ul>
		    </div><!-- /.navbar-collapse -->
		  </div><!-- /.container-fluid -->
		</nav>
		<!--内容部分 -->
		<div class="container">
			<div class="row">
				<ul class="project">
					<div class="proleft">重设密码步骤</div>
					<li class="profinish prover "><span>&nbsp;</span><b>1</b>输入账号信息<i>&nbsp;</i></li>
					<li class="profinish "><span>&nbsp;</span><b>2</b>输入邮箱信息<i>&nbsp;</i></li>
					<li class="start"><span>&nbsp;</span><b>3</b>重设密码<i>&nbsp;</i></li>
					<li><span>&nbsp;</span><b>4</b>完成<i>&nbsp;</i></li>
				</ul>
			</div>
			<div class="choice_area">
				<!-- 输入账号部分-->
				<div class="area hidden">
					<div class="row content top">
					<form class="form-inline">
					  <div class="form-group">
					    <input type="text" class="form-control" placeholder="请输入您的昵称" id="nickname">
					  </div>
					  <button type="button" class="btn btn-warning next_step">下一步</button>
					</form>
				</div>
				</div>
				<!-- 输入邮箱部分-->
				<div class="area hidden">
					<div class="row content top">
					<form class="form-inline">
					  <div class="form-group">
					    <input type="text" class="form-control" placeholder="请输入您的邮箱账号" id="email">
					  </div>
					  <button type="button" class="btn btn-warning next_step">下一步</button>
					  <button type="button" class="btn btn-warning prev1 pre_step">上一步</button>
					</form>
				</div>
			</div>
			<!-- 重置密码部分-->
				<div class="area hidden">
					<div class="row content top">
						<form class="form-horizontal">
						  <div class="form-group">
						  	 <label for="inputEmail3" class="col-sm-3 control-label">预留的手机号</label>
						    <div class="col-sm-9">
						      <input type="text" class="form-control" placeholder="请输入预留的手机号" id="phone">
						    </div>
						  </div>
						  <div class="form-group">
						  	<label for="inputEmail3" class="col-sm-3 control-label">新密码</label>
						    <div class="col-sm-9">
						      <input type="password" class="form-control" placeholder="重设密码" id="newpass">
						    </div>
						  </div>
						  <div class="form-group">
						  	<label for="inputEmail3" class="col-sm-3 control-label">确认密码</label>
						    <div class="col-sm-9">
						      <input type="password" class="form-control" placeholder="确认修改的密码" id="enterNewpass">
						    </div>
						  </div>
						  <div class="form-group">
						  	<div class="col-sm-offset-3 col-sm-4">
						      <button type="button" class="btn btn-warning pre_step">上一步</button>
						    </div>
						    <div class="col-sm-offset-1 col-sm-4">
						      <button type="button" class="btn btn-warning next_step">确 认 修 改</button>
						    </div>
						   
						  </div>
						</form>
					</div>
				</div>
				<!-- 修改成功部分-->
				<div class="area">
					<div class="row content top">
						<h1 style="color: #EE5A10;">修改成功！</h1>
						<a href="${pageContext.request.contextPath}/index" class="btn btn-primary">去登录</a>
					</div>
				</div>
				
		</div>
		<!--版权声明 -->
		<div class="foot">
			<span>Copyright © yunpan-system 2016-2017</span>
		</div>
		<script type="text/javascript">
			var i=-1;//当前位置
			//设置当前处于哪一步的函数,默认从-1开始
			function pos(i){
					$("ul.project li").removeClass("profinish").removeClass("start").removeClass("prover");
					if(i>=0){
						$("ul.project li").eq(i).addClass("profinish");	
					}
					$("ul.project li").eq(i+1).addClass("start");
					$(".choice_area .area").addClass("hidden")
					$(".choice_area .area").eq(i+1).removeClass("hidden")

					
				
			}
			
			$(function(){
				//初始化部分
				pos(-1);
				
				$(".next_step").click(function(){
					i++;
					if(i==0){
						var nick= $("#nickname").val()
						if(nick.trim()== ""){
							i=-1
							alert("用户昵不能为空！")
							return
						}
					}else if(i==1){
						var email=$("#email").val()
						var email_reg= /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;
						if(!email_reg.test(email)){
							alert("请输入正确的邮箱格式！")
							i=0
							return	
						}
					}else if(i==2){
						var phone=$("#phone").val()
						var newpass=$("#newpass").val()
						var enterNewpass=$("#enterNewpass").val()
						var phone_reg=/^1\d{10}$/;
						var password=/^[a-zA-Z0-9]{6,32}$/;
						 if(!phone_reg.test(phone)){
								 alert("手机号格式不正确！")
								 i=1
								 return
							}else if(!password.test(newpass)){
								alert("密码长度应该为6-32位！")
								i=1
								return
							}else if(enterNewpass != newpass){
								alert("两次密码输入的不一致！")
								i=1
								return
							}
					//当全通过以后，即可向后台发送请求，修改密码

					}

					if(i==2){
						var nick= $("#nickname").val()
						var email=$("#email").val()
						var phone=$("#phone").val()
						var newpass=$("#newpass").val()
						$.post("${pageContext.request.contextPath}/updatePass",{user_name:nick,user_email:email,user_pwd:newpass,user_phone:phone},function(data){
							 if(data.result){
								 pos(i);
							 }else{
								alert(data.error_message);	
								i=1
							 }
						});

					}else{
						pos(i);
					}
					
				})
				
				$(".pre_step").click(function(){
					i--;
					pos(i);
					
				})
				
			})
			
			
			
		</script>
		
		
	</body>
</html>
