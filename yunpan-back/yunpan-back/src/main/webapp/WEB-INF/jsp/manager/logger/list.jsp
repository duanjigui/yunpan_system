<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <link href="${pageContext.request.contextPath}/js/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/js/ligerUI/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
    <link href="${pageContext.request.contextPath}/js/ligerUI/skins/Gray/css/all.css" rel="stylesheet" type="text/css" />
    <script src="${pageContext.request.contextPath}/js/jquery/jquery-1.9.0.min.js" type="text/javascript"></script> 
    <script src="${pageContext.request.contextPath}/js/ligerUI/js/ligerui.all.js"></script>
    <script type="text/javascript">
	var dialog;
		//获取多选框所选中的行，并将其id值存放到数组中
		function getSelected(){
			var $selseted= $("table.l-grid-body-table tbody").find("tr.l-selected");
			var ids=new Array();
			for(var i=0;i<$selseted.length;i++){//获取选中的元素，由于复选框和表中的数据是两个不同的表格，故会出现2次
				var $element=  $($selseted[i]);
				if($element.attr("id").indexOf("|2|")>0){
					var user_id= $element.find("td:first div").text();//获取用户id
					ids.push(user_id);
				}
			}
			return ids;
		}
    
        $(function ()
        {
            var data=${logger};

            //时间格式化
			for(var i=0;i<data.Rows.length;i++){
			  var create_time=	data.Rows[i].create_time;
			  var last_login_time=	data.Rows[i].last_login_time;
			  var update_time=	data.Rows[i].update_time;
			  var date=new Date();
			  date.setTime(create_time);
			  var time= date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
			  data.Rows[i].create_time=time;
			  if(last_login_time != null){
				  date.setTime(last_login_time);
				  time= date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
				  data.Rows[i].last_login_time=time;
			  }else{
				  data.Rows[i].last_login_time="";
			 }
			  date.setTime(update_time);
			  time= date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate()+" "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
			  data.Rows[i].update_time=time;
			}
            
            window['g'] =
            $("#maingrid").ligerGrid({
                height: '100%',
                page :1,
                pageSize :10,
                columns: [
                { display: '日志id', name: 'log_id', align: 'center', width: 60, minWidth: 60 },
                { display: '日志级别', name: 'log_leavel', minWidth: 50 },
                { display: '日志内容', name: 'log_content', minWidth: 50 },
                { display: '所属组', name: 'group_name', minWidth: 50 },
                { display: '触发类', name: 'trigger_class_name', minWidth: 50 },
                { display: '触发方法', name: 'triger_method_name', minWidth: 50 },
                { display: '创建者', name: 'creater_name', minWidth: 50 },
                { display: '创建时间', name: 'create_time', minWidth: 50 , type: 'date', format: 'yyyy年MM月dd'},
                { display: '更新时间', name: 'update_time', minWidth: 50 },
                ], data: data, rownumbers: true,
                autoFilter: true,
                onReload :function(){
                	window.location.reload(true); //刷新事件
                }
                
            });
	             

            $("#pageloading").hide();
            
        });
		function page(element){
			console.log(element)
		}
        function deleteRow()
        {
            g.deleteSelectedRow();
        }

    </script>
</head>
<body style="overflow-x:hidden; padding:2px;">
<div class="l-loading" style="display:block" id="pageloading"></div>
 <a class="l-button" style="width:120px;float:left; margin-left:10px; display:none;" onclick="deleteRow()">删除选择的行</a>
 
 <div class="l-clear"></div>

    <div id="maingrid"></div>
   
  <div style="display:none;">
  
</div>
 
</body>
</html>
