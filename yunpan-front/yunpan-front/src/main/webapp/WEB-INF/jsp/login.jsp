<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>云盘系统前台页面</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.3.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js" ></script>
		<style>
			.navbar-brand{
				padding: 5px 5px 5px 40px;
				cursor:pointer
			}
			.navbar-text{
				margin-left: 0px;
				cursor:pointer
			}
			.navbar-right p.text-danger{
				color: red;
			}
			.login{
				position: absolute;
				right: 40px;
				width: 300px;
				background: white;
				padding: 10px;
				opacity: 0.95;
				z-index:999
			}
			.login .foot{
				padding: 10px;
			}
			.foot a{
				cursor: pointer;
			}
			.margin-top{
				margin-top: 40px;
			}
			.clearboth{
				clear: both;
			}
			.carousel-indicators li{
				margin: 1px 8px;
			 }
		</style>
	</head>
	<body>
		<nav class="navbar navbar-default" role="navigation">
		  <div class="container-fluid">
		    <div class="navbar-header">
		      <a class="navbar-brand" href="#">
		        <img alt="Brand" width="40px" height="40px" id="web_image">
		      </a>
		       <strong class="navbar-text hidden-xs" id="web_title"></strong>
		    </div>
		    <div class="navbar-right hidden-xs">
		    </div>
		  </div>
		</nav>
		<div class="container-fluid">
			<div class="row " style="position: relative;" id="scroll">
				<!--轮播图部分-->
					<!--轮播图片部分结束 -->
					<div class="login">
						<h4>欢迎使用云盘-system</h4>
						<form role="form" action="tologin" method="post">
						  <div class="form-group">
						    <label for="exampleInputEmail1">账号</label>
						    <input type="email" class="form-control" placeholder="请输入你的邮箱账号" name="account"/>
						  </div>
						  <div class="form-group">
						    <label for="exampleInputPassword1">密码</label>
						    <input type="password" class="form-control" placeholder="请输入密码" name="passwd" />
						  </div>
						  <div class="checkbox">
						    <label>
						      <input type="checkbox" name="autologin"> 下次自动登录
						    </label>
						    <label>
						    	<font color='red'>${tip}</font>
						    </label>
						  </div>
						  
						  <input type="submit" value="登录" class="btn btn-success form-control"/>
						</form>
						 <div class="foot">
						 	<div class="container-fluid">
							 	<div class="row">
							 		<div class="col-md-6 col-xs-6 text-right"><a href="${pageContext.request.contextPath}/toRegister">注册新账号</a></div>
							 		<div class="col-md-6 col-xs-6 text-left"><a href="${pageContext.request.contextPath}/toforgetPass">忘记密码</a></div>
							 	</div>
						 	</div>	
						 </div>
					</div>
					<!--login部分结束 -->
			</div>
		</div>
		<!--中间部分结束 -->
	<div class="container margin-top hidden-xs hidden-sm">
		<div class="row"  id="footer">
		</div>
	</div>	
			
		
		<script>
			$(function(){
				
				function pos(){
					if($(".container-fluid .row").height()==0){
						return;
					}
					$(".login").css("top",($(".container-fluid .row").height()-$(".login").height())/2);
				}
				
				pos();
				
				window.onresize=function(){
						pos();
				}

				$("form input[name='account'],form input[name='passwd']").focusout(function(){
						$(".checkbox label font").text("")
					})


				



					$.post("${pageContext.request.contextPath}/content/foot",null,function(data){
						if (data !=null && data.length>0){
							for (var i=0;i<data.length;i++){
								var $node=$("<div class='col-lg-offset-3 col-lg-6'>"+data[i].content+"</div>");
								$("#footer").append($node)		
							 }
							}	
						},'json'); 


				$.post("${pageContext.request.contextPath}/content/right/header",null,function(data){
					if (data !=null && data.length>0){
						for (var i=0;i<data.length;i++){
							if(data[i].title.indexOf("S")>=0){
								var $node=$("<p class='text-danger navbar-text navbar-left '>"+data[i].content+"</p>");
								$(".navbar-right.hidden-xs").append($node)	
							}else{
								var $node=$("<p class='navbar-text navbar-left '>"+data[i].content+"</p>");
								$(".navbar-right.hidden-xs").append($node)
							}			
						 }
						}	
					},'json'); 

				$.post("${pageContext.request.contextPath}/content/left/header",null,function(data){
					if (data !=null && data.length>0){
						for (var i=0;i<data.length;i++){
							if(data[i].title.indexOf("logo")>=0){
								$("#web_image").attr("src",data[i].content);
							}else{
								$("#web_title").text(data[i].content);
							}			
						 }
						}	
					},'json'); 


				$.post("${pageContext.request.contextPath}/content/main/scroll",null,function(data){
					if (data !=null && data.length>0){
						var $generic=$("<div id='carousel-example-generic' class='carousel slide hidden-sm hidden-xs' data-ride='carousel'>");	
						var $ol=$(" <ol class='carousel-indicators'></ol>")
						var $warper=$("<div class='carousel-inner' role='listbox'></div>")
						$generic.append($ol)
						$generic.append($warper)
						for (var i=0;i<data.length;i++){
							var $node1=	"";
							var $node2=	"";
							if(i==0){
								$node1=	"<li data-target='#carousel-example-generic' data-slide-to="+i+" class='active'></li>"
								$node2=	"<div class='item active'><img src="+data[i].content+" alt='' width='100%'><div class='carousel-caption'> </div> </div>"
							}else{
								$node1=	"<li data-target='#carousel-example-generic' data-slide-to="+i+"></li>"
								$node2=	"<div class='item'><img src="+data[i].content+" alt='' width='100%'><div class='carousel-caption'> </div> </div>"
							}
							$ol.append($node1);
							$warper.append($node2);
							}
						$("#scroll").append($generic)
						pos();
						}	
					},'json');
							
			})
			
			
			
		</script>
		
		
		
		
	</body>
</html>
