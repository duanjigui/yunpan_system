<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台系统登录</title>
<link href="css/login.css" rel="stylesheet" rev="stylesheet" type="text/css" media="all" />
<link href="css/demo.css" rel="stylesheet" rev="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="js/jquery1.42.min.js"></script>
<script type="text/javascript" src="js/jquery.SuperSlide.js"></script>
<script type="text/javascript" src="js/codeUtil.js"></script>

<script>
$(function(){

$(".i-text").focus(function(){
$(this).addClass('h-light');
});

$(".i-text").focusout(function(){
$(this).removeClass('h-light');
});

$("#username").focus(function(){
 var username = $(this).val();
 if(username=='输入账号'){
 $(this).val('');
 }
});

$("#username").focusout(function(){
 var username = $(this).val();
 if(username==''){
 $(this).val('输入账号');
 }
});


$("#password").focus(function(){
 var username = $(this).val();
 if(username=='输入密码'){
 $(this).val('');
 }
});


$("#yzm").focus(function(){
 var username = $(this).val();
 if(username=='输入验证码'){
 $(this).val('');
 }
});

$("#yzm").focusout(function(){
 var username = $(this).val();
 if(username==''){
 $(this).val('输入验证码');
 }
});

});
$(function(){
	var check={
		mesg:"",//启始标志位
		_ck_em:/^\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/,
		_ck_pw:/^[\u4E00-\u9FA5\uf900-\ufa2d\w\.\s]{6,18}$/,
		_ck_en_code:/^[\u4E00-\u9FA5\uf900-\ufa2d\w\.\s]{4}$/,
		_ck_list:[], //数组，检查元素的列表
		checkOne:function(target,error_box,check_type){
			var value= target.val(); //获取输入的结果
			var regx;
			var tip;
			var flag=false;
			if(check_type == "email"){
				regx=check._ck_em;
				tip="请输入正确的邮箱格式！";
			}else if(check_type == "pwd"){
				regx=check._ck_pw;
				tip="密码的长度应该在6到18位之间！";
			}else if(check_type == "enter_code"){
				regx=check._ck_en_code;
				tip="验证码的长度需要4位！";
			}
			if(check.mesg == ""){ //当提示框中没有消息时，则可以进行校验，否则不能进行校验，仍然提示原来的消息
				if(!value.match(regx)){  //当不匹配之时
						check.mesg = tip;
				}else{
					check.mesg="";//当匹配成功时，提醒内容可以为“”	
					flag=true;
				}
			}
			error_box.text(check.mesg)
			return flag;
		},
		chck_focusin:function(target,error_box,check_type){
			check.mesg="";
		},
		autoCheck:function(){ //进行绑定focusOut事件
			if(check._ck_list.length > 0){
				for (var i=0;i<check._ck_list.length;i++) {
					var _target=check._ck_list[i].target;
					var _error_box=check._ck_list[i].error_box;
					$(_target).focusout(function(){
						var _check_type;
						switch ($(this).attr("id")){
							case "username":
							_check_type="email";
								break;
							case "password":
							_check_type="pwd";
								break;
							case "yzm":
							_check_type="enter_code";
								break;	
							default:
								break;
						}
						check.checkOne($(this),$(_error_box),_check_type);
					})
					$(_target).focusin(function(){
						check.chck_focusin();
					});
				}
			}
		},
		form_submit:function(target_form){ //主要用来表单提交检查
			var size=check._ck_list.length;
			target_form.submit(function(){//当对list中的待校验元素进行判定，只有全为true，才进行提交，否则拒绝
				var chck_flag=new Array(size);
				if(size > 0){
					for (var i=0;i<size;i++) {
						var _target=check._ck_list[i].target;
						var _check_type=check._ck_list[i].check_type;
						var _error_box=check._ck_list[i].error_box;
						
						chck_flag[i]=check.checkOne($(_target),$(_error_box),_check_type);
					}
				}
				for (var i=0;i<size;i++) {
					if(chck_flag[i]==false){  //只要有一个标志位不满足，则表单不会提交
							return false;
					}
				}
				return true;
			})
			
		},
		init_list:function(list){
			check._ck_list=list;
		},
		init:function(list){
			check.init_list(list);
			check.autoCheck();
		}
		
	}

	var code= new codeUtil()
	code.draw(document.getElementById("code"))

	var list = [  
                { target: "#password", check_type: "pwd", error_box: ".error-box"},  
				{ target: "#yzm", check_type: "enter_code", error_box: ".error-box"}, 
				{ target: "#username", check_type: "email", error_box: ".error-box"}
               ];  
 
	check.init(list);
	$(".registerform input[type='submit']").click(function(){
		if($("#yzm").val()==code.getCode()){
			return check.form_submit($(".registerform"));
		}else{
			$(".error-box").text("验证码不正确！")
			return false;
		}
		
	})

	
})



</script>


</head>

<body>

	<div class="header">
  <h1 class="headerLogo"><a title="yunpan-system" target="_blank" ><img alt="logo" src="images/logo.jpg" width="60px" height="60px" style="vertical-align: middle;"/> 云盘-system</a></h1>
	<div class="headerNav">
		<a href="#" style="color: red;">严打违规文件和盗版侵权传播</a>
		<a target="_blank" href="#">团队介绍</a>
		<a target="_blank" href="#">帮助</a>	
	</div>
</div>	
	


<div class="banner">

<div class="login-aside">
  <div id="o-box-up"></div>
  <div id="o-box-down"  style="table-layout:fixed;">
   <div class="error-box"> ${tip} </div>
   
   <form class="registerform"action="./manager" method="post">
   <div class="fm-item">
	   <label for="logonId" class="form-label">云盘系统后台管理登陆：</label>
	   <input type="text" name="user_email" placeholder="输入账号" maxlength="100" value="${user_email}" id="username" class="i-text"  datatype="s6-18" errormsg="用户名至少6个字符,最多18个字符！" />    
       <div class="ui-form-explain"></div>
  </div>
  
  <div class="fm-item">
	   <label for="logonId" class="form-label">登陆密码：</label>
	   <input type="password" name="user_pwd" placeholder="输入密码"  maxlength="100" id="password" class="i-text" datatype="*6-16" nullmsg="请设置密码！" errormsg="密码范围在6~16位之间！" />    
       <div class="ui-form-explain"></div>
  </div>
  
  <div class="fm-item pos-r">
	   <label for="logonId" class="form-label">验证码</label>
	   <input type="text" value="输入验证码" maxlength="100" id="yzm" class="i-text yzm" nullmsg="请输入验证码！" style="vertical-align: top;" >    
       <div class="ui-form-explain" style="display: inline-block;"><canvas width="100px" height="41px" id="code"></canvas></div>
  </div>
  
  <div class="fm-item">
	   <label for="logonId" class="form-label"></label>
	   <input type="submit" value="" tabindex="4" id="send-btn" class="btn-login"> 
       <div class="ui-form-explain"></div>
  </div>
  
  </form>
  
  </div>

</div>

	<div class="bd">
		<ul>
			<li style="background:url(themes/bg1.jpg) #4f9dd5 180px 0 no-repeat;"></li>
		</ul>
	</div>

	<div class="hd"><ul></ul></div>
</div>
<script type="text/javascript">jQuery(".banner").slide({ titCell:".hd ul", mainCell:".bd ul", effect:"fold",  autoPlay:true, autoPage:true, trigger:"click" });</script>


<div class="banner-shadow"></div>

<div class="footer">
   <p>Copyright &copy; yunpan-system All rights reserved.</p>
</div>
<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>
