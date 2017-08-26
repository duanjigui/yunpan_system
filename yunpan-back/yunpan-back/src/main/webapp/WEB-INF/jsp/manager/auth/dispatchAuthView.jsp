<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <style type="text/css">
    #container{
    	overflow:scroll
    }
    </style>
    
    <script src="${pageContext.request.contextPath}/js/jquery/jquery-1.9.0.min.js" type="text/javascript"></script>
    <link href="${pageContext.request.contextPath}/css/zTreeStyle/zTreeStyle.css" rel="stylesheet" type="text/css" />
    <script src="${pageContext.request.contextPath}/js/jquery.ztree.all.min.js" type="text/javascript"></script>
   <script type="text/javascript">
   		var treeObj =null
		var setting = {
			async: {
				enable: true,
				url:"${pageContext.request.contextPath}/manager/auth/dispatch/role/list",
				autoParam:["id"],
				otherParam: { "pid":"${pid}"},
				dataFilter: filter
			},
			view: {expandSpeed:"",
				selectedMulti: true
			}	,
			data: {
				simpleData: {
					enable: true
				}
			}
		};

		function filter(treeId, parentNode, childNodes) {
			if (!childNodes) return null;
			console.log(childNodes)
			for (var i=0, l=childNodes.length; i<l; i++) {
				childNodes[i].name = childNodes[i].name;
			}
			return childNodes;
		}


		$(document).ready(function(){
		   treeObj =$.fn.zTree.init($("#tree1"), setting);
		});
	</script>
</head>
<body style="padding:10px">  
    <div id="container" style="width:97%; height:500px; margin:10px; float:left; clear:both; border:1px solid #ccc; overflow:auto;  ">
    <ul id="tree1" class="ztree"></ul>
    </div> 
 	<div>
 		<button class='close'>关闭</button>
 	</div>
 	
  <script type="text/javascript">
		$(function(){
			var dialog = frameElement.dialog;
			$('button.close').click(function(){
				dialog.close()
			})
		})
  </script>	
</body>
</html>
