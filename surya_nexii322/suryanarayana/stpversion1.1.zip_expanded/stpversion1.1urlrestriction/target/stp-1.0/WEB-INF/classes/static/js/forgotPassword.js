
/**
 * @Author Trineesha.Mandapati
 * @Version 1.0
 * 
 * 
 /************* Forgot PASSWORD FUNCTION***************/
 
 function forgotpassword(){

		 var email=$("#emailid").val();
			if(email.trim().length==0) { 
				//alert("email should not be empty");
				$('#forgotmsg').show();	
	        	$('#forgotmsg').html(VALIDATION_FORGOT_PASSWORD_EMAIL_EMPTY_ERROR);
				return false; 
				}
			 function ValidateEmail(email) {
			        var expr = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
			        return expr.test(email);
			    };

			    if (!ValidateEmail(email)) {
		            $('#forgotmsg').show();
	       		    $('#forgotmsg').html(VALIDATION_FORGOT_PASSWORD_EMAIL_VALIDTE_ERROR);
		            return false;
		        }
		
		var appBasePath=getAppBasePath();
		$.ajax({
			type: "GET",
			url: appBasePath+"/forgotpassword?email="+email,
			success : function(data) 
			{
				$('#forgotmsg').show();
				if(data=="New password sent to your mail"){
					$('#forgotmsg').css("color","green");
				}
    		    $('#forgotmsg').html(data);
		 
			},
			error:function(req,status,msg){
 	        alert(msg);
		}
	});
}
 
