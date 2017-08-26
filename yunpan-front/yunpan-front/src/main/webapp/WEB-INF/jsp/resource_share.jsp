<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>资源分享页面</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
		<link href="${pageContext.request.contextPath}/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.3.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/cookieUtil.js" ></script>
   		<script src="${pageContext.request.contextPath}/js/jquery.ztree.all.min.js" type="text/javascript"></script>
		<style type="text/css">
			.middle_height{
				min-height: 550px;
			}
			.strong{
				font-weight: bold;
			}
			.red{
				color: red;
			}
			.margin_top{
				margin-top: 6px;
			}
			.row.margin_top .col-lg-5 label{
				float: left;
			}		
			label~ul{
				list-style: none;
			}		
			label~ul li{
				float: left;
				cursor: pointer;
			}
			label~ul li:after{
				float: left;
				content: "/";
				padding:0 4px;
			}
			.panel.panel-primary .panel-body table{
				width: 100%;
				text-align: center;
				margin-bottom: 10px;
			}
			.panel.panel-primary .panel-body table tr{
				height: 40px;
				border-bottom: 1px solid rgb(221, 221, 221);
			}
			.panel.panel-primary .panel-body table tr td.mis{
				width: 20px;
			}
			.panel.panel-primary .panel-body table tr td.min{
				width: 150px;
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
		      <img  class="navbar-brand" alt="Brand" src="${pageContext.request.contextPath}/images/logo.jpg" width="50px" height="100px">
		      <a class="navbar-brand" href="#">云盘-system</a>
		    </div>
		
		    <!-- Collect the nav links, forms, and other content for toggling -->
		    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		      <ul class="nav navbar-nav navbar-right">
		         <li><a href="${pageContext.request.contextPath}/index">登录</a></li>
		         <li><a href="${pageContext.request.contextPath}/toRegister">注册</a></li>
		      </ul>
		    </div><!-- /.navbar-collapse -->
		  </div><!-- /.container-fluid -->
		</nav>
		<div class="container-fluid middle_height">
			<div class="row">
				<div class="col-lg-6">
					<c:if test="${shared_user != null}">
					<span class="strong red">${shared_user}</span> 于 <span class="strong red" id="time"> 2016年10月10日 </span> 分享
					</c:if>
				</div>
				<div class="col-lg-6">
					<a href="javascript:void(0)" class="btn btn-primary col-lg-offset-8" onclick="save()">保存到我的云盘</a>
					<a href="javascript:void(0)" class="btn btn-primary" onclick="downloads()">下载</a>
				</div>
			</div>
			<div class="row margin_top">
				<div class="col-lg-5">
					<label>当前路径：</label>
					<span>${path}</span>
					<span><a href="javascript:void(0)" onclick="goback()">返回根目录</a></span>
				</div>
			</div>
			<div class="panel panel-primary ">
			  <div class="panel-heading">
				<label>
				        <input type="checkbox"> 全选
				</label>
			  </div>
			  <div class="panel-body">
					<table>
						<tr>
							<td class="mis"></td>							
							<td>文件名</td>
							<td>文件类型</td>
							<td class="min">分享时间</td>
							<td class="min">下载次数</td>
						</tr>
						<c:if test="${shareResourceBeans !=null}">
							<c:forEach var="shareResourceBean"  items="${shareResourceBeans}">
								<tr class='data'>
									<td><input type="checkbox" value="${shareResourceBean.resource_id}" /> </td>							
									<td><img src="${pageContext.request.contextPath}/images/FileType/Middle/${shareResourceBean.fileType}"  width="30px" height="30px" /> 
										<c:if test="${shareResourceBean.isFloder == 1}">
											<a href='${pageContext.request.contextPath}/files/shares?resourceId=${shareResourceBean.resource_id}&resource_path=${path}/${shareResourceBean.resource_name}'>${shareResourceBean.resource_name}</a>
										</c:if>
										<c:if test="${shareResourceBean.isFloder == 0}">
											<a href='${shareResourceBean.sharedResourceUrl}'>${shareResourceBean.resource_name}</a>
										</c:if>
									</td>
									<td>${shareResourceBean.share_type}</td>
									<td>${shareResourceBean.create_time}</td>
									<td>${shareResourceBean.downloadNum}</td>
								</tr>
							</c:forEach>
						</c:if>
						
						<c:if test="${shareResourceBeans ==null}">
							<tr class='data'>
								<td colspan="5">没有内容了！</td>							
							</tr>
						</c:if>
						
					</table>							
			  </div>
			</div>
			
			
		</div>
			
		<div class="foot">
			<span>Copyright © yunpan-system 2016-2017</span>
		</div>
		
		<!-- 文件目录框 -->
		<div class="modal fade" id="saveMyYunPan" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel">选择目录</h4>
			      </div>
			      <div class="modal-body" id="fileMenu">
			    		<ul id="tree1" class="ztree"></ul>
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-primary"  id="Filesave">保存</button>
			      </div>
			    </div>
			  </div>
		</div>
		
		
		
		<script type="text/javascript">
				function add0(m){return m<10?'0'+m:m }
				function format(timestamp)
				{
				//shijianchuo是整数，否则要parseInt转换
				var time = new Date(timestamp);
				var y = time.getFullYear();
				var m = time.getMonth()+1;
				var d = time.getDate();
				var h = time.getHours();
				var mm = time.getMinutes();
				var s = time.getSeconds();
				return y+'年'+add0(m)+'月'+add0(d)+'日 '+add0(h)+'时'+add0(mm)+'分'+add0(s)+'秒';
				}

				function getSelected(){
					return $(".panel.panel-primary .panel-body input[type='checkbox']:checked")
				}

				function downloads(){
					var $list=getSelected()
					if($list.length==1){
						 var type= $($list[0]).parents(".data").find("td:eq(2)").text();
						 if(type=="文件"){
							alert("文件夹不能下载！")
						  }else{
								window.location.href="${pageContext.request.contextPath}/files/download?fileid="+$($list[0]).val()
						}	
						
					}else{
						alert("请选择一项进行下载！")
						
					}
				}
				function save(){
					var $list=getSelected()
					if($list.length==1){
						 var type= $($list[0]).parents(".data").find("td:eq(2)").text();
						 if(type=="文件"){
							alert("文件夹暂时还不能保存到我的云盘！")
						  }else{
							   var resourceId= $($list[0]).parents(".data").find("td input").val()//资源id	
								$("#saveMyYunPan").modal('show')
						}	
						
					}else{
						alert("请选择一项进行保存到我的云盘！")
						
					}
				}


				
				function goback(){
					if(cookieUtil.getCookie("root_path")){
						window.location.href="${pageContext.request.contextPath}"+unescape(cookieUtil.getCookie("root_path"));
					}else{
						alert("地址已失效！请重新进入该地址")
					}	
				}


				
				$(function(){


					

					if("${root_path}"){
						cookieUtil.setCookie("root_path", "${root_path}", "/", 30*60)
					}
					
					$(".panel.panel-primary .panel-heading input[type='checkbox']").click(function(){
						if(this.checked){//当全选按钮被选中时，全选
							var $checkboxs= $(this).parents(".panel.panel-primary").find(".panel-body table tr td input[type='checkbox']");
							var size=$checkboxs.length;
							for (var i=0;i<size;i++) {
								$checkboxs[i].checked=true; //全选
							}
						}
					});
					$(".panel.panel-primary .panel-body table tr td input[type='checkbox']").click(function(){ //当其中一个没有全选的时候，全选按钮取消对勾状态
						if(!this.checked){
							$(".panel.panel-primary .panel-heading input[type='checkbox']")[0].checked=false;
						}
						
					});
					
					$("#time").text(format("${create_date}"))

					var $node= $(".panel-body .data")

					for(var i=0;i<$node.length;i++){
						$($node[i]).find("td:eq(3)").text(format($($node[i]).find("td:eq(3)").text()))		
					}	
					
				});
					
			
		</script>
		
		
		<script type="text/javascript">
   		var treeObj =null
		var setting = {
			async: {
				enable: true,
				url:"${pageContext.request.contextPath}/files/layer/path/list/0",
				autoParam:["id=parent_id"],
				dataFilter: filter
			},
			view: {
				selectedMulti: false
			}	,
			check:{
				enable: true,
				chkboxType : { "Y": "", "N": "" }
				
				},
			data: {
				simpleData: {
					enable: true
				}
			}
		};

		function filter(treeId, parentNode, childNodes) {
			if (!childNodes) return null;
			for (var i=0, l=childNodes.length; i<l; i++) {
				childNodes[i].name = childNodes[i].flie_menu_name;
				childNodes[i].id = childNodes[i].file_menu_id;
				childNodes[i].isParent = true;
			}
			return childNodes;
		}


		$(document).ready(function(){
			
			$.post("${pageContext.request.contextPath}/files/layer/path/list/0",{parent_id:1},function(data){

				var nodes=[];
				if(data !=null && data.length>0){
					for(var i=0;i<data.length;i++){
							var node={
								name:"",
								parent_id:"",
								isParent:true
							}
							node.name=data[i].flie_menu_name
							node.id=data[i].file_menu_id
							node.isParent=true
							nodes.push(node)
						
					}
					treeObj =$.fn.zTree.init($("#tree1"), setting,nodes);

				}

				},"json")

			$("#Filesave").click(function(){
				var nodes=treeObj.getCheckedNodes(true);
				var ids=[];
				for(var i=0;i<nodes.length;i++){
						ids.push(nodes[i].id)
					}
					if(ids.length==1){

						var resourceId=$(getSelected()[0]).val()
						
						$.post("${pageContext.request.contextPath}/files/save/share",{resourceId:resourceId,parentId:ids.pop()},function(data){
						
								if(data.status=="200"){
									alert("保存成功！")
									$("#saveMyYunPan").modal('hide')
								}else{
									alert(data.error_message)
								}			
							
							})

					}else{
						alert("只能选一个目录")	
					}
				})
		   
		});
	</script>
		
	</body>
</html>
