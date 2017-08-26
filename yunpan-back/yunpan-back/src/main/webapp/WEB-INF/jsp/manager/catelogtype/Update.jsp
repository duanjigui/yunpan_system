<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
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
<form action="${pageContext.request.contextPath}/manager/catelogtype/update" method="post">
		<div class="dataSerizeals">
			<table>
				<input type="hidden"  name="catelog_type_id" value="${catelogeType.catelog_type_id}" />
				<tr>
					<td>内容类型名称:</td>
					<td><input type="text" name="catelog_type_name" value="${catelogeType.catelog_type_name}"/></td>
				</tr>
				<tr>
					<td>排序:</td>
					<td><input type="text" name="sort" value="${catelogeType.sort}"/></td>
				</tr>
				<tr>
					<input type="hidden" name="parent_id" />
					<td>父权限:</td>
					<td>
						<select id="auth_p_id" name="parent_name">
							<option value="0">无</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>状态:</td>
					<td>
						<select name="status">
							<c:if test="${catelogeType.status == 1}">
								<option value="1" selected='selected'>启用</option>
								<option value="0">停用</option>
							</c:if>
							<c:if test="${catelogeType.status == 0}">
								<option value="1">启用</option>
								<option value="0" selected='selected'>停用</option>
							</c:if>
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
			$.post("${pageContext.request.contextPath}/manager/catelogtype/all",null,function(data){
					if(data != null && data.length >0){
						for(var i=0;i<data.length;i++){
							if(data[i].catelog_type_id != "${catelogeType.catelog_type_id}" ){
							var $node="";
							if(data[i].auth_id=="${catelogeType.parent_id}"){
								$node="<option value="+data[i].catelog_type_id+" selected='selected'>"+data[i].catelog_type_name+"</option>";
							}else{
								$node="<option value="+data[i].catelog_type_id+">"+data[i].catelog_type_name+"</option>";
							}
							$(".dataSerizeals table tr td select#auth_p_id").append($node);
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