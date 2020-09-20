
/**
 * @Author Trineesha.Mandapati
 * @Version 1.0
 * 
 * 
/************* CHANGE PASSWORD FUNCTION***************/

 function changepassword(){
        	//alert("hiiiiii");
        	var currentpwd=$("#currentpwd").val();
        	var newpwd=$("#newpwd").val();
        	var confirmpwd=$("#confirmpwd").val();
        	if(currentpwd.trim().length==0&&newpwd.trim().length==0&&confirmpwd.trim().length==0){
        		//alert(currentpwd);
        	    $('#errormsg').show();	
            	$('#errormsg').html(VALIDATION_PASSWORDS_EMPTY);
            	setTimeout(function(){
            		$('#errormsg').hide();
        			}, 10000);
        		return false; 
        	}
        	if(currentpwd.trim().length==0) { 
        		//alert("current password should not be empty");
        		$('#errormsg').show();	
            	$('#errormsg').html(VALIDATION_CURRENT_PASSWORD_EMPTY_ERROR);
            	setTimeout(function(){
            		$('#errormsg').hide();
        			}, 10000);
        		return false; 
        		}
        	if(newpwd.trim().length==0) { 
        		//alert("new password should not be empty");
        		$('#errormsg').show();	
            	$('#errormsg').html(VALIDATION_NEW_PASSWORD_EMPTY_ERROR);
            	setTimeout(function(){
            		$('#errormsg').hide();
        			}, 10000);
        		return false; 
        		}
        	if(confirmpwd.trim().length==0) { 
        		//alert("confirm password should not be empty");
        		$('#errormsg').show();	
            	$('#errormsg').html(VALIDATION_CONFIRM_PASSWORD_EMPTY_ERROR);
            	setTimeout(function(){
            		$('#errormsg').hide();
        			}, 10000);
        		return false; 
        		}
        	
        	if(newpwd.length<5){
        		$('#errormsg').show();	
            	$('#errormsg').html(VALIDATION_NEW_PASSWORD_MINLENGTH_ERROR);
            	setTimeout(function(){
            		$('#errormsg').hide();
        			}, 10000);
        		return false; 
        	}
        	if(newpwd.length>25){
        		$('#errormsg').show();	
            	$('#errormsg').html(VALIDATION_NEW_PASSWORD_MAXLENGTH_ERROR);
            	setTimeout(function(){
            		$('#errormsg').hide();
        			}, 10000);
        		return false; 
        	}
            
        	
        	if(newpwd==confirmpwd){
        		
        	}
        	else{
        		
        		$('#errormsg').show();	
            	$('#errormsg').html(VALIDATION_CONFIRM_PASSWORD_MATCH_ERROR);
            	setTimeout(function(){
            		$('#errormsg').hide();
        			}, 10000);
        		return false; 
        	}
        	//alert("hi");
        	if(newpwd==currentpwd){
        		//alert("same");
            	$('#errormsg').show();	
            	$('#errormsg').html(VALIDATION_NEW_PASSWORD_SAME_ERROR);
            	setTimeout(function(){
            		$('#errormsg').hide();
        			}, 10000);
        		return false; 
        	}
        	var params = {
        			currentpassword : currentpwd ,
        			newpassword : newpwd,
        			confirmpassword : confirmpwd ,
        	};
        	
        	var queryParam = jQuery.param( params );
        	
        	var appBasePath=getAppBasePath();
        	 $.ajax({
        		type: "GET",
        		url: appBasePath+"/changepassword?currentpassword",
        		data:queryParam,
         		success : function(data) 
        		{
        			$('#errormsg').show();
        			if(data=="Password changed successfully"){
        			$('#errormsg').css("color","green");
        			}
                	$('#errormsg').html(data);
                	setTimeout(function(){
                		$('#errormsg').hide();
            			}, 10000);
            		return false; 
        			
        		},
        		error:function(req,status,msg){
                    alert(msg);
        		}
        	}); 
        }
 