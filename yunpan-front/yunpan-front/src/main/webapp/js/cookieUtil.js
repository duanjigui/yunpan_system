$(function(){
	
	var cookieUtil=(function(){
          	  var cookies={
          	  	setCookie:function(key,value,path,expires){
          	  		var d = new Date();
					d.setTime(d.getTime()+(expires*1000));
					var expiresTime = "expires="+d.toGMTString();
					document.cookie=key+"="+escape(value)+";"+expiresTime+";"+"path="+path
          	  	},
          	  	getCookie:function(key){
          	  		var cookievalue= document.cookie
          	  		if(cookievalue.indexOf(";")>=0){
          	  			var array= cookievalue.split(";")
          	  			if(array.length>0){
          	  				for(var i=0;i<array.length;i++){
								if(array[i].indexOf(key)>=0){
									return array[i].substring(array[i].indexOf('=')+1,array[i].length)
		 						 }								
          	  				}
          	  			}else{
          	  				return null
          	  			}
          	  		}else{
          	  			return null;
          	  		}
          	  		
          	  	},
          	  	removeCookie:function(key){
          	  		document.cookie=key+"="+" "+";"+"path=/"
          	  	}
          	  }
          	return cookies;
          })()
          
	window.cookieUtil=cookieUtil
})
