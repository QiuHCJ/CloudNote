
			//登录按钮
			function checkLogin() {
				//获取表单数据
           		var name = $("#count").val().trim();
           		var password = $("#password").val().trim();
           		
           		$("#name_msg").html("");
           		$("#password_msg").html("");
           		
           		
           		//验证数据合法性
           		var flag = true;
           		if(name==""){
           			$("#name_msg").html("用户名不能为空");
           			flag =  false;
           		}
           		if(password==""){
           			$("#password_msg").html("密码不能为空");
           			flag = false;
           		}
           		
           		//发送ajax请求
           		if(flag){
           			//采用HTTP Basic Authenticaction模式传输用
	           		var msg = name+":"+password;
	           		var base64_msg = Base64.encode(msg);
	           		
	            	$.ajax({
	            		url:"http://localhost:8080/cloud_note/user/login.do",
	            		type:"post",
	            		dataType:"json",
	            		beforeSend:function(xhr){
	            			xhr.setRequestHeader(
	            				"Authorization","basic "+base64_msg	
	            			);
	            		},
	            		success:function(result){
	            			if(result.status==0){//登录成功
	            				//将用户信息存入cookie
	            				var userId = result.data.userId;
	            				var userToken = result.data.userToken;
	            				addCookie("userId",userId,1);
	            				addCookie("userToken",userToken,1);
	            				
		            			window.location.href="edit.html";
	            			}else if(result.status==1){
	            				$("#name_msg").html("用户名不存在");
	            			}else{
	            				$("#password_msg").html("密码错误");
	            			}
	            		},
	            		error:function(){
	            			alert("登录异常");
	            		}
	            	});
            	}
           	}