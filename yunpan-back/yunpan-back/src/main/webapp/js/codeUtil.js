	var codeUtil=(function(){
					function genenratorCode(){
						var codes=['0','1','2','3','4','5','6','7','8','9','a','b','c',
					           'd','e','f','g','h','i','j','k','l','m','n','o','p',
					           'q','r','s','t','u','v','w','x','y','z',
								'A','B','C','D','E','F','G','H','I','J','K','L','M',
								'N','O','P','Q','R','S','T','U','V','W','X','Y','Z'
							  ]
						var s="";
						var i=0
						while(true){
							var tmp= codes[Math.round(Math.random()*codes.length)-1] 
							if(tmp==undefined){
								continue
							}
							s+=tmp
							i++
							if(i==4){
								return s
							}
							
						}
					}
				
					function drawText(element,text,width,height){
						var context = element.getContext('2d');
						context.font = 'bold 25px consolas';
				        context.textAlign = 'left';
				        context.textBaseline = 'top';
				        context.strokeStyle = '#DF5326';
				        context.strokeText(text, width, height);
					}
					
					
					function CodeUtil(){
						this.code=genenratorCode() //获取验证码
						this.drawText=drawText
						this.draw=function(element){
							this.drawText(element,this.code,10,5)
						}
						this.getCode=function(){
							return this.code
						}
					}
					
					return CodeUtil
			})()
			
	
