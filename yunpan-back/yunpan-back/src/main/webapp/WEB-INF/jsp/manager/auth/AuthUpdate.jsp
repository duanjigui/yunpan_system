<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery1.42.min.js" ></script>
		<style type="text/css">
			.dataSerizeals table{
				width: 800px;
				margin-bottom: 20px;
			}
			.dataSerizeals table tr{
				margin-bottom: 20px;
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
<form action="${pageContext.request.contextPath}/manager/auth/update" method="post">
		<div class="dataSerizeals">
			<table>
				<input type="hidden"  name="auth_id" value="${auth.auth_id}" />
				<input type="hidden"  name="is_parent" value="${auth.is_parent}" />
				<tr>
					<td>权限名称:</td>
					<td><input type="text" name="auth_name" value="${auth.auth_name}"/></td>
				</tr>
				<tr>
					<td>权限标识符:</td>
					<td><input type="text" name="auth_identity" value="${auth.auth_identity}"/></td>
				</tr>
				<tr>
					<td>url:</td>
					<td><input type="text" name="url" value="${auth.url}"/></td>
				</tr>
				<tr>
					<td>排序:</td>
					<td><input type="text" name="sort" value="${auth.sort}"/></td>
				</tr>
				<tr>
					<input type="hidden" name="auth_p_name" />
					<td>父权限:</td>
					<td>
						<select id="auth_p_id" name="auth_p_id" readonly = "readonly" >
							<option value="0">无</option>	
						</select>
					</td>
				</tr>
				<tr>
					<td style="vertical-align: baseline;">权限描述:</td>
					<td><textarea rows="10" cols="70" name="auth_desc" >${auth.auth_desc}</textarea> </td>
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
			$.post("${pageContext.request.contextPath}/manager/auth/all",null,function(data){
					if(data != null && data.length >0){
						for(var i=0;i<data.length;i++){
							if(data[i].auth_id != "${auth.auth_id}" ){
							var $node="";
							if(data[i].auth_id=="${auth.auth_p_id}"){
								$node="<option value="+data[i].auth_id+" selected='selected'>"+data[i].auth_name+"</option>";
							}else{
								$node="<option value="+data[i].auth_id+">"+data[i].auth_name+"</option>";
							}
							$(".dataSerizeals table tr td select").append($node);
							}
						}
					}

				},"json");
			$(".ok").click(function(){
				var selected= $(".dataSerizeals table tr td select").find("option:selected").text();
				$("table tr:eq(4) input[type='hidden']").val(selected);
				$("form:last").submit();	
				//parent.window.location.reload(true);//刷新当前框架
				parent.parent.window.location.reload(true);//刷新主框架
			});
			$(".close").click(function(){
				dialog.close()
			});
		})
	</script>

</body>
</html>