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
<form action="${pageContext.request.contextPath}/manager/rule/add" method="post">
		<div class="dataSerizeals">
			<table>
				<tr>
					<td>规则名称:</td>
					<td><input type="text" name="rule_name"/></td>
				</tr>
				<tr>
					<td style="vertical-align: baseline;">规则描述:</td>
					<td><textarea rows="10" cols="70" name="rule_desc" ></textarea> </td>
				</tr>
				<tr>
					<td>规则特征符:</td>
					<td><input type="text" name='rule_sysmbol'/></td>
				</tr>
				<tr>
					<td>规则控制字段:</td>
					<td><input type="text" name="rule_control_field" /></td>
				</tr>
				<tr>
					<td style="vertical-align: baseline;">规则控制内容:</td>
					<td><textarea rows="10" cols="70" name="rule_control_content" ></textarea> </td>
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
			$(".ok").click(function(){
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