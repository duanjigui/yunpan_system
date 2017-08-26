<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>修改密码界面</title>
	</head>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery1.42.min.js" ></script>
	<style type="text/css">
		*{
			margin: 0;
			padding: 0;
			font-family: "微软雅黑";
		}		
		body,html{
			width: 100%;
			height: 100%;
		}
		h2{
			padding: 2px 18px;
		}
		.error-box{
			color: red;
			width: 200px;
		}
		form div.boxer{
			width: 700px;
			height: 300px;
			padding: 20px;
			
		} 
		form div.boxer .row{
			width: 600px;
			height: 25px;
			margin-bottom: 10px;
		}
		form div.boxer .row label{
			height: 100%;
			width: 88px;
			display: inline-block;
		}
		form div.boxer .row label.error-box{
			width: 200px;
		}
		form div.boxer .row input{
			height: 23px;
		}
		form div.boxer button{
			margin: 20px 0 0 96px;
			width: 70px;
			height: 35px;
			border-radius: 7px;
			background: rgba(74, 74, 255, 0.77);
			color: white;
			
		}
	</style>
	<body>
		<h2>密码修改</h2>
		<div>
			<form method="post" action="${pageContext.request.contextPath}/Updatepasswd">
				<div class="boxer">
					<div class="row">
						<label>原密码:</label>
						<input type="password" name="o_pwd" />
						<label class="error-box"></label>
					</div>
					<div class="row">
						<label>新密码:</label>
						<input type="password" name="n_pwd" />
						<label class="error-box"></label>
					</div>
					<div class="row">
						<label>确认新密码:</label>
						<input type="password" name="e_n_pwd" />
						<label class="error-box"></label>
					</div>
					<button type="submit">确认修改</button>
				</div>
			</form>				
		</div>
		
		<script type="text/javascript">
			$(function(){
				var new_pwd="";
				var regx=/^[\u4E00-\u9FA5\uf900-\ufa2d\w\.\s]{6,18}$/;
				$('.row input').focusout(function(){
					$(".error-box").text("")
					if (!$(this).val().match(regx)){
						$(this).parents(".row").find(".error-box").text("密码至少是6·18位！")
						return
					}
					if($(this).attr('name')=='e_n_pwd' && $(this).val()!=new_pwd){
						$(this).parents(".row").find(".error-box").text("两次输入的密码不一致！")
						return
					}
				})
				$(".row input[name='e_n_pwd']").focusin(function(){
					new_pwd= $(".row input[name='n_pwd']").val()
				})
				
				$("form").submit(function(){
					
					var len= $('.row input').length;
					mes=[false,false,false]
					
					for(var i=0;i<len;i++){
						if($($('.row input')[i]).val().match(regx)){
							mes[i]=true	;							
						}else{
							mes[i]=false;
							$('.row:eq('+i+')').find(".error-box").text("密码至少是6·18位！！")
						}
					}
					
					if($(".row input[name='e_n_pwd']").val()!=$(".row input[name='n_pwd']").val()){
						mes[2]=false;
						$('.row:eq(2)').find(".error-box").text("两次输入的密码不一致！")
					}
					for(var j=0;j<mes.length;j++){
						if(mes[j]==false){
							return false;
						}
					}
					return true;
				})
			})
				
		</script>
		
	</body>
</html>
