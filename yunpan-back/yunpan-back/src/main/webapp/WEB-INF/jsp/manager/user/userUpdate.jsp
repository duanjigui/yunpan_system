<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<link
	href="${pageContext.request.contextPath}/js/ligerUI/skins/Aqua/css/ligerui-all.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/js/ligerUI/skins/Gray/css/all.css"
	rel="stylesheet" type="text/css" />
<script
	src="${pageContext.request.contextPath}/js/jquery/jquery-1.9.0.min.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/js/ligerUI/js/core/base.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/js/ligerUI/js/plugins/ligerForm.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/js/ligerUI/js/plugins/ligerDateEditor.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/js/ligerUI/js/plugins/ligerComboBox.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/js/ligerUI/js/plugins/ligerCheckBox.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/js/ligerUI/js/plugins/ligerButton.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/js/ligerUI/js/plugins/ligerDialog.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/js/ligerUI/js/plugins/ligerRadio.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/js/ligerUI/js/plugins/ligerSpinner.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/js/ligerUI/js/plugins/ligerTextBox.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/js/ligerUI/js/plugins/ligerGrid.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/CustomersData.js"></script>
<script type="text/javascript"> 
        var groupicon = "${pageContext.request.contextPath}/js/ligerUI/skins/icons/communication.gif";
		var g;
         $(function ()
        { 
        	 var dialog = frameElement.dialog;
	           function save(){ 
				//将异步ajax请求改为同步方法
				$("#updateform").attr("action","${pageContext.request.contextPath}/manager/user/update");
				$("#updateform .row._orginze input[type='hidden']").val($("#updateform .row select[name='user_orginze_id']").find("option:selected").text());
				$("form:last").submit();
				//parent.window.location.reload(true);//刷新当前框架
				parent.parent.window.location.reload(true);//刷新主框架
				}
			function close(){
				dialog.close()
			}

			$(".close").click(function(){
					close();
			})
			$(".save").click(function(){
				save();
			})

			//加载组织信息
			$.post("${pageContext.request.contextPath}/manager/orianzation/listAll",null,function(data){
				if(data != null){
					for(var i=0;i<data.length;i++){
						var $node="";
						if("${user.user_orginze_id}"==data[i].orange_id){
							$node="<option value="+data[i].orange_id+" selected='selected'>"+data[i].orange_name+"</option>";
							}else{
								$node="<option value="+data[i].orange_id+">"+data[i].orange_name+"</option>";
								}
						$("#warp .row select[name='user_orginze_id']").append($node);
					}
				}else{
					$("#warp .row select[name='user_orginze_id']").append("<option>没有组织信息</option>");
				}
				},"json");
			
        });  
    </script>
<style type="text/css">
#warp {
	font-family: "微软雅黑";
	font-size: 12px;
	padding-bottom: 200px;
}

#warp .row {
	height: 30px;
	padding: 10px;
	line-height: 30px;
}

#warp .row label {
	width: 85px;
	display: inline-block;
}

#warp .row select {
	width: 173px;
}

button {
	background: rgba(16, 0, 255, 0.63);
	color: white;
	padding: 7px 16px;
	border-radius: 4px;
}
</style>
</head>
<body style="padding: 10px">
	<form id='updateform' method="post">
	<input type="hidden" name="user_id" value="${user.user_id}" />
		<div id="warp">
			<div class="row">
				<label>用户昵称：</label> <input type="text" name="user_name" value="${user.user_name}" />
			</div>
			<div class="row">
				<label>邮箱：</label> <input type="text" name="user_email" value="${user.user_email}"/>
			</div>
			<div class="row">
				<label>用户手机号：</label> <input type="text" name="user_phone" value="${user.user_phone}"/>
			</div>
			<div class="row">
				<label>用户类型：</label> <select name="user_type">
				
				<c:if test="${user.user_type == null or user.user_type == '0'}">
				<option value="0" selected='selected'>普通用户</option>
				<option value="1">超级管理员</option>
				<option value="2">部门管理员</option>
				</c:if>
				<c:if test="${user.user_type == '1'}">
				<option value="0">普通用户</option>
				<option value="1" selected='selected'>超级管理员</option>
				<option value="2">部门管理员</option>
				</c:if>
				<c:if test="${user.user_type == '2'}">
				<option value="0">普通用户</option>
				<option value="1">超级管理员</option>
				<option value="2" selected='selected'>部门管理员</option>
				</c:if>
				</select>
			</div>
			<div class="row _orginze">
				<input type="hidden"  name="user_orginze_name" />
				<label>用户所属组织：</label> <select name="user_orginze_id">
				</select>
			</div>
			<div class="row">
				<label>用户状态：</label>
				<select name="is_delete">
					<c:if test="${user.is_delete == 0}">
						<option value="1">启用</option>
						<option value="0" selected="selected">禁用</option>
					</c:if>
					<c:if test="${user.is_delete == 1}">
						<option value="1" selected="selected">启用</option>
						<option value="0">禁用</option>
					</c:if>
				</select>
			</div>
			<div class="row">
				<label>备注：</label>
				<textarea name="other" rows="10" cols="70"
					style="vertical-align: text-top;">${user.other}</textarea>
			</div>

		</div>
		<button class="save">修改</button>
		<button class="close">关闭</button>
	</form>
</html>