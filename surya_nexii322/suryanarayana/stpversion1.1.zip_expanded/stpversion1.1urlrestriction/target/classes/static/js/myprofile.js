function getMyProfile() {
	//alert("in get profile");
	//logoutcheck();
	var appBasePath = getAppBasePath();
	$.ajax({
		type : "GET",
		//dataType : "json",
		url : appBasePath + "/profile/getmyprofile",
		success : function(data) {
				//data.empId
				$("#empId").val(data.empId);
				$("#empName").val(data.empName);
				$("#empMailId").val(data.empMailId);
				$("#empMailId").prop("readonly", true);
				$("#empContact").val(data.contactNumber);
				$("#empSkill").val(data.skillSet);
				$("#empExp").val(data.experience);
			
		},
		error : function(req, status, msg) {
			alert("in error")
		}
	});
}
function updateProfileDetails(){
	var empId=$("#empId").val();
	var empName=$("#empName").val();
	var empMailId=$("#empMailId").val();
	var empContact=$("#empContact").val();
	var empSkill=$("#empSkill").val();
	var empExp=$("#empExp").val();
	$("#error_msg").css("color","red");
	if(empName.trim().length==0){
    	$('#error_msg').html("");
    	$('#error_msg').html(VALIDATION_NAME_EMPTY_ERROR);
    	setTimeout(function(){
    		$('#error_msg').html("");
			}, 5000);
		return false; 
    }
	if(empMailId.trim().length==0){
    	$('#error_msg').html("");
    	$('#error_msg').html(VALIDATION_EMAIL_EMPTY_ERROR);
    	setTimeout(function(){
    		$('#error_msg').html("");
			}, 5000);
		return false; 
    }
	if(empContact.trim().length==0){
    	$('#error_msg').html("");
    	$('#error_msg').html(VALIDATION_CONTACT_EMPTY_ERROR);
    	setTimeout(function(){
    		$('#error_msg').html("");
			}, 5000);
		return false; 
    }
	if(empSkill.trim().length==0){
    	$('#error_msg').html("");
    	$('#error_msg').html(VALIDATION_SKILL_EMPTY_ERROR);
    	setTimeout(function(){
    		$('#error_msg').html("");
			}, 5000);
		return false; 
    }
	if(empExp.trim().length==0){
    	$('#error_msg').html("");
    	$('#error_msg').html(VALIDATION_EXP_EMPTY_ERROR);
    	setTimeout(function(){
    		$('#error_msg').html("");
			}, 5000);
		return false; 
    }
	
	// alert("in update profile");
		//logoutcheck();
		var appBasePath = getAppBasePath();
		var params = {
				empId : empId ,
				empName : empName ,
				empMailId : empMailId,
				empContact:empContact,
				empSkill:empSkill,
				empExp:empExp
		}
		var queryParam = jQuery.param( params );
		$.ajax({
			type : "POST",
			dataType : "json",
			data:queryParam,
			url : appBasePath + "/profile/updatemyprofile",
			success : function(data) {
				//alert(data);
				if(data.statusCode==1){
					$("#error_msg").css("color","green");
					$("#error_msg").html("Profile Updated Successfully");
					setTimeout(function(){
						getMyProfile();
					}, 10000);
				}else{
					$("#error_msg").css("color","red");
					$("#error_msg").html("Profile Updation Fail");
					setTimeout(function(){
						$('#error_msg').html("");
					}, 10000);
				}
				
			},
			error : function(req, status, msg) {
				alert("in error")
			}
		});
}
