<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="UTF-8">
		<title>修改页面</title>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery1.42.min.js" ></script>
		<style type="text/css">
			.dataSerizeals table{
				width: 350px;
				margin-bottom: 20px;
			}
			.dataSerizeals table tr{
				height: 50px;
			}
			.dataSerizeals table tr td select{
				width: 173px;
				padding: 2px;
			}
			.buttons button{
				padding: 5px 10px;
				background-color: cornflowerblue;
				color: white;
				border-radius: 4px;
				margin-left: 54px;
			}
		</style>
	</head>
	<body>
	<form action="update" method="post">
		<div class="dataSerizeals">
		<input type="hidden" name="orange_id"  value="${orangezation.orange_id}"/>
			<table>
				<tr>
					<td>组织名称:</td>
					<td><input type="text" name="orange_name" value="${orangezation.orange_name}"/></td>
				</tr>
				<tr>
					<td>组织拥有者:</td>
					<td>
						<select id="orangzation_owner" name="organze_user_id">
						</select>
					</td>
				</tr>
				<tr>
					<td>组织创建者:</td>
					<td>
						<select id="orangzation_creater" name="creater">
						</select>
					</td>
				</tr>
			</table>	
		</div>
		
		<div class="buttons">
			<button class="close">取消</button>
			<button class="ok">确认</button>
		</div>
	</form>
	<script type="text/javascript">
		$(function(){
			var dialog = frameElement.dialog;
			$.post("${pageContext.request.contextPath}/manager/user/all",null,function(data){
					if(data != null && data.length >0){
						for(var i=0;i<data.length;i++){
							var $node="";
							if(data[i].user_id == "${orangezation.organze_user_id}"){
								var $node="<option value="+data[i].user_id+" selected='selected'>"+data[i].user_name+"</option>";
								$("#orangzation_owner").append($node);
							}else{
								var $node="<option value="+data[i].user_id+">"+data[i].user_name+"</option>";
								$("#orangzation_owner").append($node);
							}
							if(data[i].user_id == "${orangezation.creater}"){
								var $node="<option value="+data[i].user_id+" selected='selected'>"+data[i].user_name+"</option>";
								$("#orangzation_creater").append($node);
							}else{
								var $node="<option value="+data[i].user_id+">"+data[i].user_name+"</option>";
								$("#orangzation_creater").append($node);
							}
						}
					}

				},"json");
			$(".ok").click(function(){
				$("form:last").submit();	
				//parent.window.location.reload(true);//刷新当前框架
				 parent.parent.window.location.reload(true);//刷新主框架
				//parent.parent.window.location.href="${pageContext.request.contextPath}/manager";
			});
			$(".close").click(function(){
				dialog.close()
			});
		})
	</script>
	
	</body>
</html>
