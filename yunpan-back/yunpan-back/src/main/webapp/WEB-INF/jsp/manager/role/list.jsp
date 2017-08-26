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
    
        function itemclick(item)
        {
            if(item.text == "修改"){
            	 var seleceted= getSelected();
				if(seleceted.length > 1){
					alert("你所选的行数大于1!")
				}else if(seleceted.length < 1){
					alert("请选择要修改的行！")
				}else{//执行修改操作逻辑
					$.ligerDialog.open({
		                   height:600,
		                   width: 800,
		                   title : '修改角色',
		                   url: 'toUpdate?id='+seleceted[0], 
		                   showMax: false,
		                   showToggle: true,
		                   showMin: false,
		                   isResize: true,
		                   slide: false,
		                   isHidden:false,
		                   allowClose:true
		               }) ;
					//window.location.reload(true)
				}
             }else if(item.text == "删除"){
                //获取要删除数据的id
				var ids=getSelected();
				if(ids.length == 0){
					alert("请选择要删除的行！");
					return ;
				}
				//向服务端发送要删除的数据
				var contents="<form action='${pageContext.request.contextPath}/manager/role/delete' method='post'>";
				for(var i=0;i<ids.length;i++){
					 contents+=	"<input type='hidden' name='ids' value='"+ids[i]+"'/>"
					}
				contents+="</form>";
				var $content=$(contents);
				$("body").append($content);
				//提交表单数据
				$("form:last").submit();
             }else{
           		  dialog= $.ligerDialog.open({
                   height:600,
                   width: 800,
                   title : '增加角色',
                   url: 'toAdd', 
                   showMax: false,
                   showToggle: true,
                   showMin: false,
                   isResize: true,
                   slide: false,
                   isHidden:false,
                   allowClose:true
               }) ;

           }
            
        	
    }
        $(function ()
        {
            var data=${roles};

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
                { display: '角色id', name: 'role_id', align: 'center', width: 60, minWidth: 60 },
                { display: '角色名称', name: 'role_name', minWidth: 50 },
                { display: '角色描述', name: 'role_desc', minWidth: 50 },
                { display: '创建者', name: 'creater_name', minWidth: 50 },
                { display: '创建时间', name: 'create_time', minWidth: 50 , type: 'date', format: 'yyyy年MM月dd'},
                { display: '更新时间', name: 'update_time', minWidth: 50 },
                ], data: data, rownumbers: true,
                toolbar: {
                    items: [
                    { text: '增加', click: itemclick, icon: 'add' },
                    { line: true },
                    { text: '修改', click: itemclick, icon: 'modify' },
                    { line: true },
                    { text: '删除', click: itemclick, img: '${pageContext.request.contextPath}/js/ligerUI/skins/icons/delete.gif' }
                    ]
                },
                autoFilter: true,
                checkbox :true,
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
