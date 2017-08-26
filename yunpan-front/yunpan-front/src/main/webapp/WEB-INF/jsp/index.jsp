<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>主页</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/webuploader.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/smartMenu.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/messageTip.min.css" />
		<link href="${pageContext.request.contextPath}/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.3.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-smartMenu.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/webuploader.min.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/messageTip.min.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/cookieUtil.js" ></script>
		<script src="${pageContext.request.contextPath}/js/jquery.ztree.all.min.js" type="text/javascript"></script>
		<style type="text/css">
			.middle_height{
				min-height: 550px;
			}
			.foot{
				color:black;
				width: 100%;
				text-align: center;
				margin-bottom: 10px;
			}
			.container-fluid.middle_height {
				padding: 0px;
			}
			.nav-stacked > li{
				height: 78px;
			}
			.noborder{
				border: 2px solid #f8f8f8;
				margin-top: 9px;
				padding: 8px 2px;
				box-shadow: 0px 0px 1px #f8f8f8;
				margin-left: -4px;
			}
			#upload{
				width:100px;
				height:50px
			 }
			 #menu_list ul li a{
				text-align: center;
			}
			#displayFiles{
				font-family: "微软雅黑";
				font-size: small;
				height: 500px;
    			overflow: scroll;
			}
			#displayFiles .check_box{
					width: 5%;
					display: inline-block;
					padding:0 6px;
			}
			#displayFiles .file_name{
					width: 45%;
					display: inline-block;
			}
			
			#displayFiles .file_name input{
			  padding: 0 8px
			}
			#displayFiles .file_name i{
				padding:0px 4px;
				border:1px solid rgba(136, 145, 251, 0.73);
				margin-left:8px;
				color:#1578e2
			}
			
			#displayFiles .file_name label{
				font-weight: normal;
				margin-left: 10px;
			}
			#displayFiles .file_icon{
					width: 15%;
					display: inline-block;
			}
			#displayFiles .file_icon em{
				margin-right: 10px;
				display: none;
				color: #90beff;
				font-size: 16px;
			}
			#displayFiles .file_size{
					width: 10%;
					display: inline-block;
			}
			#displayFiles .updateTime{
					width: 15%;
					display: inline-block;
			}
			#displayFiles .yunpan_title{
				margin-top: 5px;
				margin-bottom: 10px;
				color: darkgray;
			}
			#displayFiles .yunpan_content{
				overflow: auto;
			}
			#displayFiles .yunpan_content .files{
				cursor: pointer;
				padding: 10px 0px;
				border-top: 1px solid white;
				border-bottom: 1px solid white;
			}
			#displayFiles .yunpan_content .files:hover{
				border-top: 1px solid rgba(21, 33, 183, 0.18);
				border-bottom: 1px solid rgba(21, 33, 183, 0.18);
				background-color:rgba(196, 219, 254, 0.17)
			}
			#welcomeTip{
				margin-left: 25px;
				color: darkgray;
			}
			.cur_path{
				padding: 0 6px 
			}
			.cur_path span{
				float:right;
				margin-right: 13px;
			}
			.rename{
				display:inline-block
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
		    	 <ul class="nav navbar-nav" id="nav">
			      </ul>
		      <ul class="nav navbar-nav navbar-right" id='nav_right'>
		      </ul>
		    </div><!-- /.navbar-collapse -->
		  </div><!-- /.container-fluid -->
		</nav>
		
		<!--内容部分 -->
		<div class="container-fluid middle_height">
				<div class="col-xs-2" style="padding-left: 2px;" id="menu_list">
				
				</div>
				
				<!--右侧部分 -->
				<div class="col-xs-10">
					<div class="row">
						<div class="col-xs-3">
						 <div class="btns">
					        <a id="upload">选择文件</a>
					        <a class="btn btn-primary" style="vertical-align: top;height: 40px;line-height: 27px;" id="createFloder">新建文件夹</a>
					    </div>
					</div>
					<div class="col-xs-offset-9">
							<form class="form-inline" id="search">
								<div class="form-group">
									<input type="text" class="form-control" placeholder="搜索您的文件" />
								</div>
								<button type="button" class="btn btn-info">搜索</button>
							</form>
					</div>	
					</div>
					<div class="row">
						<div class="col-xs-12">
							<!--存放文件显示列表 -->
							<div class="col-xs-12 middle_height noborder" id="displayFiles">
								<div class="cur_path">
									当前路径：<label>${current_path}</label>
									<c:if test="${current_path ne '/'}">
										<span><a href="${pageContext.request.contextPath}/files/chpwd?menu_id=${parent_path_id}&parent_path=${current_path_id}">返回上级</a></span>
										<span><a href="${pageContext.request.contextPath}/files/chpwd?menu_id=1">返回根目录</a></span>
									</c:if>
								</div>
								<div class="yunpan_title content">
									<span class="check_box"><input type="checkbox"/></span>
									<span class="file_name">文件名</span>
									<span class="file_icon"></span>
									<span class="file_size">大小</span>
									<span class="updateTime">修改日期</span>
								</div>
								<div class="yunpan_content content">
								
								
								</div>								
							</div>
							
							
						</div>
					</div>
					
				</div>
				
			</div>
			
		</div>	
		<!--版权部分 -->	
		<div class="foot">
			<span>Copyright © yunpan-system 2016-2017</span>
		</div>
		
		
		<!-- 模态信息弹出框 -->
		
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel">个人信息</h4>
			      </div>
			      <div class="modal-body" id="persion_message">
			    		<form class="form-horizontal">
						  <div class="form-group">
						    <label for="inputEmail3" class="col-sm-2 control-label">用户邮箱</label>
						    <div class="col-sm-10">
						      <input type="email" class="form-control"  disabled="disabled" id="m_us_email">
						    </div>
						  </div>
						  <div class="form-group">
						    <label for="inputPassword3" class="col-sm-2 control-label">用户昵称</label>
						    <div class="col-sm-10">
						      <input type="text" class="form-control"  disabled="disabled" id="m_us_nick">
						    </div>
						  </div>
						   <div class="form-group">
						    <label for="inputPassword3" class="col-sm-2 control-label" >手机号码</label>
						    <div class="col-sm-10">
						      <input type="text" class="form-control" disabled="disabled" id="m_us_phone">
						    </div>
						  </div>
						   <div class="form-group">
						    <label for="inputPassword3" class="col-sm-2 control-label ">备注</label>
						    <div class="col-sm-10">
						      <input type="text" class="form-control" disabled="disabled" id="m_us_other">
						    </div>
						  </div>
						  
						</form>
			    		
			    		
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			      </div>
			    </div>
			  </div>
		</div>
			
		
		
		<div class="modal fade" id="message" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title" id="myModalLabel">消息通知</h4>
			      </div>
			      <div class="modal-body" id="message_info">
			    		
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			      </div>
			    </div>
			  </div>
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
				$(function(){

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
					return y+'-'+add0(m)+'-'+add0(d)+' '+add0(h)+':'+add0(mm)+':'+add0(s);
					}


					function loadMenu(){
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
								window.treeObj=treeObj
							}
	
							},"json")


					}

					loadMenu();

					
					function loadPage(type,parent_id,name){
						//首先清空节点
						$("#displayFiles .yunpan_content.content").empty()			

						$.post("${pageContext.request.contextPath}/files/layer/path/list/"+type,{parent_id:parent_id,name:name},function(data){

							if(data!=null && data.length >0){
							for(var i=0;i<data.length;i++){
								var filename= data[i].flie_menu_name;
								if(filename.length>20){
									filename= filename.substring(0,20) 
									filename=filename+"..."
								}

								var time= data[i].update_time;
								time=format(time);
								var $files=$("<div class='files'></div>")	
								var $check_box=$("<span class='check_box'><input type='checkbox' value='"+data[i].file_menu_id+"' _typ='floder'/></span>");
								var $file_name=$("<span class='file_name'><img src='${pageContext.request.contextPath}/images/FileType/Middle/"+data[i].fileType+"' width='30px' height='30px' /><label><a href='#'>"+filename+"</a></label></span>")
								var $file_icon=$("<span class='file_icon toolbar'><em class='glyphicon glyphicon-download-alt'></em><em class='glyphicon glyphicon-share'></em><em class='glyphicon glyphicon-remove'></em></span>")
								var $file_size=$("<span class='file_size'>"+data[i].size+"</span>")
								var $updateTime=$("<span class='updateTime'>"+time+"</span>")
								$files.append($check_box)
								$files.append($file_name)
								$files.append($file_icon)
								$files.append($file_size)
								$files.append($updateTime)
								$("#displayFiles .yunpan_content").append($files);
								$files.click(function(){
									$("#displayFiles input[type='checkbox']").prop("checked",false)
									$(this).find("input[type='checkbox']").prop("checked",true)
									
								})
								$file_name.click(function(){
									var menu_id= $(this).parents(".files").find("input[type='checkbox']").val();
									window.location.href="${pageContext.request.contextPath}/files/chpwd?menu_id="+menu_id+"&parent_path=${current_path_id}";
								})
								$files.hover(function(){
									$(this).find(".file_icon.toolbar em").show()
								},function(){
									$(this).find(".file_icon.toolbar em").hide()
								})
								$file_icon.find("em").click(function(){
									 var cls= $(this).attr("class")
									 if(cls=="glyphicon glyphicon-download-alt"){
									 		download()
									 }else if(cls=="glyphicon glyphicon-share"){
										 	var $list= getSelected()
								        	if($list.size()==1){
								        		var type= $($list[0]).attr("_typ")
												share(type,0,$($list[0]).val())
										     }
									 }else if(cls=="glyphicon glyphicon-remove"){
										    var	type="floder"
											var $list= getSelected()
									        if($list.size()==1){
									        		deleted(type,$($list[0]).val())
										     }    
									 }
								})
								
								
							}
							}else{
								//$("#displayFiles .yunpan_content").append("没有文件，请上传文件吧！");
							}
			
						},"json");


						window.loadPage=loadPage
						
						$.post("${pageContext.request.contextPath}/files/layer/filepath/list/"+type,{parent_id:parent_id,name:name},function(data){
							if(data!=null && data.length >0){
								for(var i=0;i<data.length;i++){	
							var filename= data[i].resource_name;
							if(filename.length>20){
								filename= filename.substring(0,20) 
								filename=filename+"..."
							}
							var time= data[i].update_time;
							time=format(time);
							var $files=$("<div class='files'></div>")	
							var $check_box=$("<span class='check_box'><input type='checkbox' value='"+data[i].resource_id+"' _typ='file' /></span>");
							var $file_name=$("<span class='file_name'><img src='${pageContext.request.contextPath}/images/FileType/Middle/"+data[i].fileType+"' width='30px' height='30px' /><label><a href='"+data[i].resource_url+"' target='_blank'>"+filename+"</a></label></span>")
							var $file_icon=$("<span class='file_icon toolbar'><em class='glyphicon glyphicon-download-alt'></em><em class='glyphicon glyphicon-share'></em><em class='glyphicon glyphicon-remove'></em></span>")
							var $file_size=$("<span class='file_size'>"+data[i].size+"</span>")
							var $updateTime=$("<span class='updateTime'>"+time+"</span>")
							$files.append($check_box)
							$files.append($file_name)
							$files.append($file_icon)
							$files.append($file_size)
							$files.append($updateTime)
							$("#displayFiles .yunpan_content").append($files);
							$files.click(function(){
								$("#displayFiles input[type='checkbox']").prop("checked",false)
								$(this).find("input[type='checkbox']").prop("checked",true)
								
							})
							$files.hover(function(){
								$(this).find(".file_icon.toolbar em").show()
							},function(){
								$(this).find(".file_icon.toolbar em").hide()
							})
							$file_icon.find("em").click(function(){
										 var cls= $(this).attr("class")
										 if(cls=="glyphicon glyphicon-download-alt"){
											 var $list= getSelected()
									        	if($list.size()==1){
									        		download($($list[0]).val()	)
										        }
										 }else if(cls=="glyphicon glyphicon-share"){
											    var $list= getSelected()
									        	if($list.size()==1){
									        		var type= $($list[0]).attr("_typ")
													share(type,0,$($list[0]).val())
											     }
										 }else if(cls=="glyphicon glyphicon-remove"){
												var type="file"
												var $list= getSelected()
										        if($list.size()==1){
										        		deleted(type,$($list[0]).val())
											     }
		
										 }
									})
							
							}
							}
						},"json");
						
											
					}
					



					
					var uploader = WebUploader.create({

					
					    // 文件接收服务端。
					    server: '${pageContext.request.contextPath}/files/fileupload',
					
					    // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
					    resize: false,

					    auto:true,
					    formData: {  
					    	current_path_id: "${current_path_id}" ,
					    	current_path:"${current_path}" 
					    }  
					});
				
					//当开始上传文件时
					 uploader.on( 'uploadStart', function( file ) {
						 var obj=[{
								files_id:file.id,
								files_name:file.name,
								files_size:file.size,
								files_direcory:"${current_path}",
								files_status:"0%",
								files_operation:"查看",
							}]
							messagTip.addMessage(obj)
							messagTip.openMessage()
		              });
						//上传过程中
					 uploader.on( 'uploadProgress', function( file, percentage ) {
						 var i=percentage* 100	
						 if(i<=100){
								var temp=""+i;
								messagTip.changeColor(file.id,"files_status","green")
								messagTip.updateMessage(file.id,"files_status",temp.substring(0,temp.indexOf('.')+4)+"%")
							}else{
								messagTip.changeColor(file.id,"files_status","black")
								messagTip.updateMessage(file.id,"files_status","完成")
							}

			            });	
					
					uploader.addButton({
					    id: '#upload',
					});
	
				 uploader.on( 'uploadSuccess', function( file ) {
				        alert(file.name +'上传成功！')
				        window.location.reload()
				  });
				
				function getSelected(){
					return $("#displayFiles .content input[type='checkbox']:checked");
				}
				
				function share(type,privaliege,fileid){

					$.post("${pageContext.request.contextPath}/files/gennerateShared",{resourceId:fileid,type:type,privaliege:privaliege},function(data){
						alert("分享地址：${pageContext.request.contextPath}"+data.share_url+"    分享码："+data.share_code)
						console.log(data)
					})
					
				}
				function renmae(fileid,list,type){
					var	newElement=$("<div class='rename'><input type='text'/> <i class='ok'>√</i><i class='cancle'>x</i> </div>")
					var	oldElement=list.parents(".files").find(".file_name label")[0];
					var oldText=$(oldElement).find("a").text()
					var l= oldText.split(".")
					var suffix= l[l.length-1]
					list.parents(".files").find(".file_name")[0].replaceChild(newElement[0],oldElement)
					newElement.find("input").focus()
					list.unbind("click");
					newElement.find("i.ok").click(function(e){
						var newName=newElement.find("input").val()
						if(newName.split(".")[newName.split(".").length-1] != suffix){
							newName=newName+"."+suffix;	
						}
						$.post("${pageContext.request.contextPath}/files/rename",{resource_id:fileid,newName:newName,type:type,current_path:"${current_path}",current_path_id:"${current_path_id}",parent_path_id:"${parent_path_id}"},function(data){
							if(data){
								$(oldElement).find("a").text(newName);
								list.parents(".files").find(".file_name")[0].replaceChild(oldElement,newElement[0])
							}else{
								alert("修改失败！")
							}

						})
						
						
						
						return false;
						
					})
					newElement.find("i.cancle").click(function(e){
						list.parents(".files").find(".file_name")[0].replaceChild(oldElement,newElement[0])
						return false
					})
											
					
				}
				function download(fileid){
					window.location.href="${pageContext.request.contextPath}/files/download?fileid="+fileid	
				}
				function moveTo(fileid){
					$("#saveMyYunPan").modal('show')
				}
				
				function deleted(type,fileid){
					//删除操作，将文件的标志位修改一下即可
					var url="";
					//分为删除文件和删除目录两种情况
					if(type == "floder"){
					//需要删除文件夹本身和其下的所有文件
					 url="${pageContext.request.contextPath}/files/deleteFloders"
						
					}else if(type == "file"){

						 url="${pageContext.request.contextPath}/files/deleteFiles"	
						//只删除本文件
					}

					$.post(url,{fileId:fileid},function(data){
						if(data){
							window.location.reload()
						}

					})
					
				}
				
				var MenuData = [
				    [{
				        text: "分享",
				        data: [[{
				            text: "公开分享",
				            func: function() {
				            	var $list= getSelected()
					        	if($list.size()==1){
					        		var type= $($list[0]).attr("_typ")
									share(type,1,$($list[0]).val())
							     }
					        }
				        }, {
				            text: "私密分享",
				            func: function() {
				            	var $list= getSelected()
					        	if($list.size()==1){
					        		var type= $($list[0]).attr("_typ")
									share(type,0,$($list[0]).val())
							     }
						     }
				        }]]
				    }, {
				        text: "重命名",
				        func: function() {
				        	var $list= getSelected()
				        	if($list.size()==1){
				        		var type= $($list[0]).attr("_typ")
				        		 renmae($($list[0]).val(),$list,type)
					        }
				        }
				    },
				     {
				        text: "下载",
				        func: function() {
				        	var $list= getSelected()
				        	if($list.size()==1){
				        		download($($list[0]).val()	)
					        }
				        }
				    },
				    {
				        text: "移动到",
				        func: function() {
				        	var $list= getSelected()
				        	if($list.size()==1){
				        		var type= $($list[0]).attr("_typ")
				        		if(type != "floder"){
				        			moveTo($($list[0]).val())
					        	}else{
									alert("文件夹暂时还不支持移动！")
						        }
					        }
				      		
				        }
				    }
				    ],
				    //分栏
				    [{
				        text: "删除",
				        func: function() {
					        
				        	var $list= getSelected()
				        	if($list.size()==1){
					        	var type= $($list[0]).attr("_typ")
				        		deleted(type,$($list[0]).val())
					        }
				        	
				        }
				    }]
				];
				$("#displayFiles").smartMenu(MenuData, {
	   				name: "displayFiles"    
				});

				
				//右键点击事件，将选项框选中
				document.getElementById("displayFiles").onmousedown = function(e){  
			       var e = e || window.event
			        if(e.button == "2"){  
			        	$("#displayFiles input[type='checkbox']").prop("checked",false)
			        	if($(e.target).attr("class")=="files"){
			        		$(e.target).find("span.check_box input[type='checkbox']").prop("checked",true)
			        	}else{
			        		var target= $(e.target).parents(".files").find("span.check_box input[type='checkbox']")[0]
				        	if(target!=undefined){
				        		$(target).prop("checked",true);
				        	}
			        	}
			        }  
	   			 }  
				
				var flag=false;
				//全选和反选
				$("#displayFiles .yunpan_title input[type='checkbox']").click(function(){
						if(!flag){
							$("#displayFiles input[type='checkbox']").prop("checked",true)
							flag=true
						}else{
							$("#displayFiles input[type='checkbox']").prop("checked",false)
							flag=false
						}
				})

				//ajax获取当前文件的列表
				
				var parent_id="${current_path_id}"||"1"

				window.parent_id=parent_id

				loadPage("0", parent_id, null)


				//创建文件夹
				$("#createFloder").click(function(){
					var $files=$("<div class='files'></div>")	
					var $check_box=$("<span class='check_box'><input type='checkbox'/></span>");
					var $file_name=$("<span class='file_name'><input type='text'/> <i class='ok'>√</i><i class='cancle'>x</i></span>")
					var $file_icon=$("<span class='file_icon toolbar'><em class='glyphicon glyphicon-download-alt'></em><em class='glyphicon glyphicon-share'></em><em class='glyphicon glyphicon-remove'></em></span>")
					var $file_size=$("<span class='file_size'></span>")
					var $updateTime=$("<span class='updateTime'></span>")
					$files.append($check_box)
					$files.append($file_name)
					$files.append($file_icon)
					$files.append($file_size)
					$files.append($updateTime)
					$("#displayFiles .yunpan_content").prepend($files);
					$file_name.find(".ok").click(function(){
						var file_name= $file_name.find("input").val()
						var parent_path="${current_path_id}"||"1";
						//接下来，将文件的name存储到数据库中，参数，文件name,当前路径id,name
						window.location.href="${pageContext.request.contextPath}/files/createFloder?fileName="+file_name+"&current_path=${current_path}&current_path_id=${current_path_id}&parent_path_id="+parent_path
					})
					$file_name.find(".cancle").click(function(){
						$files.detach()
					})	
					
				})


				//点击切换目录选项按钮

			/* 	$("#menu_list ul li").click(function(){
					$("#menu_list ul li").removeClass("active")
					$(this).addClass("active")
						
					loadPage($(this).attr("r"), parent_id, null)
				}); */


				$("#search button").click(function(){
					 var searchField= $("#search input").val()
					 loadPage("7", parent_id, searchField)
				})

				$("#nav li").click(function(){
					$("#nav li").removeClass("active")	
					$(this).addClass("active")
					loadPage($(this).attr("r"), parent_id, null)
				})

				$.post("${pageContext.request.contextPath}/info",null,function(data){
					if(data){
						$("#m_us_email").val(data.user_email)
						$("#m_us_nick").val(data.user_name)
						$("#m_us_phone").val(data.user_phone)
						$("#m_us_other").val(data.other||"无")
					}
					},"json")
				 
				})
				function information(){
					//无论消息是否有都会弹出，只查前两条消息
					
					$.post("${pageContext.request.contextPath}/message/all",null,function(data){
						$("#message_info").empty()
						if(data && data.length>0){
								for(var i=0;i<data.length;i++){
										var $node=$( "<div class='mes'>"+(i+1)+".  "+data[i].mes_content+"</div>")
										$("#message_info").append($node);

								}

							}else{
								$("#message_info").append("暂无消息！")
							}

						},"json")
					
					$('#message').modal('show')
				}

				$("#Filesave").click(function(){
					var nodes=treeObj.getCheckedNodes(true);
					var ids=[];
					for(var i=0;i<nodes.length;i++){
							ids.push(nodes[i].id)
						}
						if(ids.length==1){
							var $list= $("#displayFiles .content input[type='checkbox']:checked")
							var resourceId=$($list[0]).val()
							$.post("${pageContext.request.contextPath}/files/moveTo",{resourceId:resourceId,parentId:ids.pop()},function(data){
									if(data.status=="200"){
										alert("保存成功！")
										$("#saveMyYunPan").modal('hide')
										window.location.reload()
									}else{
										alert(data.message)
									}			
								
								})

						}else{
							alert("只能选一个目录")	
						} 
					})
					
					$.post("${pageContext.request.contextPath}/content/main/menu/left",null,function(data){

						var $ul=$(" <ul class='nav nav-pills nav-stacked' style='background: #f8f8f8;'></ul>")
						if (data !=null && data.length >0){
							for (var i=0;i<data.length;i++){
									var $node;
									if(i==0){
										$node=$('<li role="presentation" class="active" r="'+data[i].content+'"><a href="#">'+data[i].title+'</a></li>')
									}else if(data[i].title=="welcomeTip"){
										$node=$('<li role="presentation" id="welcomeTip">'+data[i].content+'</li>')
									}else{
										$node=$('<li role="presentation"  r="'+data[i].content+'"><a href="#">'+data[i].title+'</a></li>')
									}
									$ul.append($node)
								}
							}
						$("#menu_list").append($ul)

						$("#menu_list ul li").click(function(){
							$("#menu_list ul li").removeClass("active")
							$(this).addClass("active")
								
							loadPage($(this).attr("r"), parent_id, null)
						});
								
						
						})
						
						$.post("${pageContext.request.contextPath}/content/main/menu/topleft",null,function(data){

						if (data !=null && data.length >0){
							for (var i=0;i<data.length;i++){
									var $node;
									if(i==0){
										$node=$('<li class="active" r="'+data[i].content+'"><a href="#">'+data[i].title+' <span class="sr-only">(current)</span></a></li>')
									}else{
										$node=$('<li r="'+data[i].content+'"><a href="#">'+data[i].title+'</a></li>')
									}
									$("#nav").append($node)
								}
							}

							$("#nav li").click(function(){
								$("#nav li").removeClass("active")	
								$(this).addClass("active")
								loadPage($(this).attr("r"), parent_id, null)
							})
						})
						
						$.post("${pageContext.request.contextPath}/content/main/menu/topright",null,function(data){

						if (data !=null && data.length >0){
							for (var i=0;i<data.length;i++){
									var $node=$("<li>"+decodeURI(data[i].content)+"</li>");
									$("#nav_right").append($node)
								}
							}

						})
						
			  
		</script>	
		
		<!-- 
		
		 -->
		
	
	</body>
</html>
