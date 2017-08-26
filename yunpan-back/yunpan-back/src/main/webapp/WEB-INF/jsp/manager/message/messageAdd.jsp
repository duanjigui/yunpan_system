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
<form>
		<div class="dataSerizeals">
			<table>
				<tr>
					<td>消息级别:</td>
					<td><input type="text" name="mes_leavl" /></td>
				</tr>
				<tr>
					<input type="hidden" name="mes_send_user_name" />
					<td>消息接收者:</td>
					<td>
						<select id="mes_send_user_name" name="mes_send_user_id">
							<option value="0">无</option>
						</select>
					</td>
				</tr>
				<tr>
					<input type="hidden" name="mes_send_group_name" />
					<td>消息发送群组:</td>
					<td>
						<select id="mes_send_group_id" name="mes_send_group_id">
							<option value="0">无</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>消息状态:</td>
					<td>
						<select name="mes_is_delete">
							<option value="0">启用</option>
							<option value="1">停用</option>
						</select>
					</td>
				</tr>
				<tr>
					<td style="vertical-align: baseline;">消息内容:</td>
					<td><textarea rows="10" cols="70" name="mes_content" ></textarea> </td>
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
							var $node="<option value="+data[i].user_id+">"+data[i].user_name+"</option>";
							$(".dataSerizeals table tr:eq(1) td select").append($node);
						}
					}

				},"json");
			$.post("${pageContext.request.contextPath}/manager/orianzation/listAll",null,function(data){
				if(data != null && data.length >0){
					for(var i=0;i<data.length;i++){
						var $node="<option value="+data[i].orange_id+">"+data[i].orange_name+"</option>";
						$(".dataSerizeals table tr:eq(2) td select").append($node);
					}
				}

			},"json");


			
			$(".ok").click(function(){
				var selected= $(".dataSerizeals table tr:eq(1) td select").find("option:selected").text();
				if(selected != "空"){
					$("table tr:eq(1) input[type='hidden']").val(selected);
				}
				var selected2= $(".dataSerizeals table tr:eq(2) td select").find("option:selected").text();
				if(selected2 != "空"){
					$("table tr:eq(2) input[type='hidden']").val(selected2);
				}

				$.post("${pageContext.request.contextPath}/message/add",$("form").serialize(),function(data){
					//parent.window.location.reload(true);//刷新当前框架
					parent.parent.window.location.reload(true);//刷新主框架
				})

				
				
			});
			$(".close").click(function(){
				dialog.close()
			});
		})
	</script>

</body>
</html>