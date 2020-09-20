
/**
 * @Author Trineesha.Mandapati
 * @Version 1.0
 * 
 * 
/************* LOGIN FUNCTION***************/

function ValidateEmail(email) {
	var expr = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	return expr.test(email);
};

function logincheck(event){
	event.preventDefault();
	var email=$("#emailid").val();
	var pwd=$("#pwd").val();
	//alert("fields should not be empty");
	var email=$("#emailid").val();
	if(email.trim().length==0) { 
		//alert("email should not be empty");
		$('#loginmsg').show();	
     	$('#loginmsg').html("User Name should not be empty.");
     	setTimeout(function(){$('#loginmsg').hide();}, 10000);
		return false; 
	}
	if (!ValidateEmail(email)) {
		$('#loginmsg').show();
		$('#loginmsg').html(VALIDATION_FORGOT_PASSWORD_EMAIL_VALIDTE_ERROR);
		setTimeout(function(){$('#loginmsg').hide();}, 10000);
		return false;
	}
	if(pwd.trim().length==0) { 
		$('#loginmsg').show();	
		$('#loginmsg').html("Password should not be empty");
		setTimeout(function(){$('#loginmsg').hide();}, 10000);
     	return false; 
	}
	var appBasePath=getAppBasePath();
	$.ajax({
		type: "POST",
		url: appBasePath+"/userlogin?email="+email+"&password="+pwd,
		success : function(data) {
			if(data=="User not exist"){
				$('#loginmsg').show();	
				$('#loginmsg').html("User doesn't exist");
				setTimeout(function(){$('#loginmsg').hide();}, 10000);
			}else if(data=="login fail"){
				//alert("Invalid login details");
				$('#loginmsg').show();	
				$('#loginmsg').html("Invalid login details");
				setTimeout(function(){$('#loginmsg').hide();}, 10000);
			}else{
				window.location.href =appBasePath+"/"+data;
			}
					
		},
		error:function(req,status,msg){
         alert(msg);
		}
	});
}
 
/************* LOGOUT FUNCTION***************/
function logout(){
	//logoutcheck();
	var appBasePath=getAppBasePath();
	$.ajax({
		type: "POST",
		url: appBasePath+"/logout",
		success : function(data) {
			//alert("data:"+data);
			window.location.href =appBasePath+"/login";
		},
		error:function(req,status,msg){
			alert(msg);
		}
	});
}
 /************* LOGOUT CHECK FUNCTION***************/
function logoutcheck(){
	var appBasePath=getAppBasePath();
	// alert("logoutcheck");
	$.ajax({
		url:appBasePath+"/logoutcheck",
		type:"GET",
		success:function(data){
			//alert("logout check data:"+data);
			if(data=="sessionexpired"){
				window.location.href=appBasePath+"/login";
			}
		},
		error:function(req,status,msg){
			alert(msg);
		}
	});
}

function getMenuBarNavigation(){
	var appBasePath=getAppBasePath();
	$.ajax({
		type: "GET",
		url: appBasePath+"/menuNavigation",
		success : function(data) {
			var page=window.location.href;
			page=page.replace(appBasePath+"/","");
			//alert(page);
			page=page.replace("#","");
			//alert(page);
			$("div#sidebar-menu").html(data);
			if(page == 'bench' || page == 'accounts' || page == 'my_profile' || page == 'company_taxdetails' || page == 'company_bankdetails'){
				$("a[href='"+page+"']").parent().addClass("active");
				$("a[href='"+page+"']").addClass("active");
			}else{
				if($("a[href='"+page+"']").length ==2){
					$("a[href='"+page+"']").eq(0).addClass("active subdrop");
					$("a[href='"+page+"']").eq(1).parent().parent().attr("style","display: block;");
					$("a[href='"+page+"']").eq(1).parent().addClass("active");
					$("a[href='"+page+"']").eq(1).addClass("active");
				}else{
					$("a[href='"+page+"']").eq(0).parent().parent().prev().addClass("active subdrop");
					$("a[href='"+page+"']").eq(0).parent().parent().attr("style","display: block;");
					$("a[href='"+page+"']").eq(0).parent().addClass("active");
					$("a[href='"+page+"']").eq(0).addClass("active");
				}
			}
		},
		error:function(req,status,msg){
			alert(msg);
		}
	});
}