function createUser() {
	// alert("in function");
	var l = $('#createuser').ladda();
    l.ladda('start');
	//logoutcheck();
	var appBasePath = getAppBasePath();
	var firstname = $("#firstname").val();
	var lastname = $("#lastname").val();
	var email = $("#email").val();
	var empno = $("#empno").val();
	var roleId = $("#roleId").val();
	var reportingId = $("#reportingId").val();
	var location = $("#location").val();
	
	var color = 'red';
	$('#fnameError').css('color', color);
	$('#emailError').css('color', color);
	$('#lnameError').css('color', color);
	$('#locationError').css('color', color);
	$('#reportError').css('color', color);
	$('#roleError').css('color', color);
	$('#empNoError').css('color', color);
	
	//validating first name 
	if (firstname.trim().length == 0) {
		// alert(firstname.trim().length)
		$('#fnameError').html(VALIDATION_CREATEUSER_FIRST_NAME_EMPTY_ERROR);
		l.ladda('stop');
		return false;
	} else if (firstname.trim().length < 3) {
		// alert("in length" + firstname.trim().length)
		$('#fnameError').html(VALIDATION_CREATEUSER_FIRST_NAME_LENGTH_ERROR);
		l.ladda('stop');
		return false;
	} else if (!isNaN(firstname)) {
		$('#fnameError').html(VALIDATION_CREATEUSER_FIRST_NAME_SPECIALS_ERROR);
		l.ladda('stop');
		return false;
	} else {
		$('#fnameError').html("");
	}
	if(firstname.trim().length>25  )
 	{
 		 $("#fnameError").html(VALIDATION_CREATEUSER_FIRST_NAME_MAX_LENGTH_ERROR);
 		 $("#fnameError").focus();
 		l.ladda('stop');
 		 setTimeout(function (){
        		 $("#fnameError").html("");
        	 },5000);
 		return false;
 	}
	else {
		$('#fnameError').html("");
	}
	
	//validating last name
	if(lastname.trim().length>25  )
 	{
 		 $("#lnameError").html(VALIDATION_CREATEUSER_LAST_NAME_MAX_LENGTH_ERROR);
 		 $("#lnameError").focus();
 		 setTimeout(function (){
        		 $("#lnameError").html("");
        	 },5000);
 		l.ladda('stop');
 		return false;
 	}
	else {
		$('#lnameError').html("");
	}
	if (lastname.trim().length == 0) {
		$('#lnameError').html(VALIDATION_CREATEUSER_LAST_NAME_EMPTY_ERROR);
		l.ladda('stop');
		return false;
	} else if (lastname.trim().length < 3) {
		$('#lnameError').html(VALIDATION_CREATEUSER_LAST_NAME_LENGTH_ERROR);
		l.ladda('stop');
		return false;
	} else if (!isNaN(lastname)) {
		$('#lnameError').html(VALIDATION_CREATEUSER_LAST_NAME_SPECIALS_ERROR);
		l.ladda('stop');
		return false;
	} else {
		$('#lnameError').html("");
	}
	
	//email validation
	if (email.trim().length == 0) {
		$('#emailError').html(VALIDATION_CREATEUSER_EMAIL_EMPTY_ERROR);
		l.ladda('stop');
		return false;
	} else if (!ValidateEmail(email)) {
		// $('#emaildiv').show();
		$('#emailError').html(VALIDATION_CREATEUSER_EMAIL_VALID_ERROR);
		$('#email').focus();
		setTimeout(function() {
			$("#emailError").html("");
		}, 5000);
		l.ladda('stop');
		return false;
	} else {
		$('#emailError').html("");
	}
	
	//emp number validation
	if (empno == 0) {
		$('#empNoError').html(VALIDATION_CREATEUSER_EMPLOYEENUM_EMPTY_ERROR);
		l.ladda('stop');
		return false;
	} else {
		$('#empNoError').html('');
	}
	
	//role validation
	if (roleId == null || roleId == 0) {
		//alert("in if role");
		$('#roleError').html(VALIDATION_CREATEUSER_DESIGNATION_EMPTY_ERROR);
		l.ladda('stop');
		return false;
	} else {
		$('#roleError').html('');
	}
	
	//reporting validation
	if (reportingId == null || reportingId == -1) {
		//alert("in if reports");
		$('#reportError').html(VALIDATION_CREATEUSER_REPORTING_EMPTY_ERROR);
		l.ladda('stop');
		return false;
	} else {
		$('#reportError').html('');
	}
	
	
	//location validation
	if (location.trim().length == 0) {
		$('#locationError').html(VALIDATION_CREATEUSER_LOCATION_EMPTY_ERROR);
		l.ladda('stop');
		return false;
	} else {
		$('#locationError').html('');
	}
	var params = {
			firstName : firstname ,
			lastName : lastname,
			email : email ,
			roleId : roleId ,
			reportTo : reportingId ,
			empNum : empno ,
			location : location 
	};
	var queryParam = jQuery.param(params);
	$.ajax({
		type : "POST",
		data:queryParam,
		dataType : "json",
		url : appBasePath + "/user/createUser",
		success : function(data) {
			if (data.statusCode == 1) {
				l.ladda('stop');
				$('#createusermsg').html(data.message)
				$('#createusermsg').css('color', 'green');
				$("form#user_creation")[0].reset();
				$('.selectpicker').last().selectpicker('refresh');
			} else {
				l.ladda('stop');
				$('#createusermsg').html(data.message)
				$('#createusermsg').css('color', color);
			}
			setTimeout(function() {
				$("#createusermsg").html("");
			}, 5000);
			//Custombox.close();
			usersloader();
			getUsersList();
			//alert(data.message);
		},
		error : function(req, status, msg) {
			alert("in error" + msg);
			l.ladda('stop');
		}
	});

}
function ValidateEmail(email) {
	var expr = /^(([a-zA-Z_.-]+)([\w-\.]+))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	return expr.test(email);
};

function updateUser(userId) {
	//alert("in function >>> "+userId);
	//logoutcheck();
	var appBasePath = getAppBasePath();
	var color = 'red';
	var firstname = $("#firstname1").val();
	var lastname = $("#lastname1").val();
	var email = $("#email1").val();
	var empno = $("#empno1").val();
	var roleId = $("#roleId1").val();
	var reportingId = $("#reportingId1").val();
	var location = $("#location1").val();
	// alert(roleId);
	// alert(reportingId);
	$('#fnameError1').css('color', color);
	$('#emailError1').css('color', color);
	$('#lnameError1').css('color', color);
	$('#locationError1').css('color', color);
	$('#reportError1').css('color', color);
	$('#roleError1').css('color', color);
	$('#empNoError1').css('color', color);
	
	//fname validation
	if (firstname.trim().length == 0) {
		// alert(firstname.trim().length)
		$('#fnameError1').html(VALIDATION_EDITUSER_FIRST_NAME_EMPTY_ERROR);
		return false;
	} else if (firstname.trim().length < 3) {
		// alert("in length" + firstname.trim().length)
		$('#fnameError1').html(VALIDATION_EDITUSER_FIRST_NAME_LENGTH_ERROR);
		return false;
	} else if (!isNaN(firstname)) {
		$('#fnameError1').html(VALIDATION_EDITUSER_FIRST_NAME_SPECIALS_ERROR);
		return false;
	} else {
		$('#fnameError1').html("");
	}
	
	//lastname validation
	if (lastname.trim().length == 0) {
		$('#lnameError1').html(VALIDATION_EDITUSER_LAST_NAME_EMPTY_ERROR);
		return false;
	} else if (lastname.trim().length < 3) {
		$('#lnameError1').html(VALIDATION_EDITUSER_LAST_NAME_LENGTH_ERROR);
		return false;
	} else if (!isNaN(lastname)) {
		$('#lnameError1').html(VALIDATION_EDITUSER_LAST_NAME_SPECIALS_ERROR);
		return false;
	} else {
		$('#lnameError1').html("");
	}
	
	//role validation
	if (roleId == null) {
		$('#roleError1').html(VALIDATION_EDITUSER_DESIGNATION_EMPTY_ERROR);
		return false;
	} else {
		$('#roleError1').html('');
	}
	
	//report validation
	if (reportingId == null || reportingId == -1) {
		$('#reportError1').html(VALIDATION_EDITUSER_REPORTING_EMPTY_ERROR);
		return false;
	} else {
		$('#reportError1').html('');
	}
	
	//location validation
	if (location.trim().length == 0) {
		$('#locationError1').html(VALIDATION_EDITUSER_LOCATION_EMPTY_ERROR);
		return false;
	} else {
		$('#locationError1').html('');
	}
	var params = {
			userId:userId,
			firstName : firstname ,
			lastName : lastname,
			roleId : roleId ,
			reportTo : reportingId ,
			location : location 
	};
	var queryParam = jQuery.param(params);
	$.ajax({
		type : "POST",
		data:queryParam,
		dataType : "json",
		url : appBasePath + "/user/updateUser",
		success : function(data) {
			//alert(data.message);
			if (data.statusCode == 1) {
				$('#updateusermsg').html(data.message)
				$('#updateusermsg').css('color', 'green');
				$('.selectpicker').last().selectpicker('refresh');
				Custombox.close();
			} else {
				$('#updateusermsg').html(data.message)
				$('#updateusermsg').css('color', color);
			}
			setTimeout(function() {
				$("#updateusermsg").html("");
			}, 5000);
			usersloader();
			getUsersList();
			getMenuBarNavigation();
		},
		error : function(req, status, msg) {
			alert("in error" + req);
		}
	});

}

function usersloader() {
	// alert("hello");
	//logoutcheck();
	var appBasePath = getAppBasePath();
	$.ajax({
		type : "Get",
		dataType : "json",
		url : appBasePath + "/user/getUsersListForReporting",
		success : function(data) {
			//alert(data)
			var reportOption="<option value=\"-1\">select reporting person</option><option value=\"0\">Self</option>";
			for (var i = 0; i < data.length; i++) {
				reportOption = reportOption+"<option value=" + data[i].userId + ">"
						+ data[i].firstName + ' ' + data[i].lastName
						+ "</option>";
			}
			$('#reportingId').html("");
			$('#reportingId').html(reportOption);
			$('#reportingId1').html("");
			$('#reportingId1').html(reportOption);
			return true;
		},
		error : function(req, status, msg) {
			alert("in error")
		}
	});

}

function getUsersList() {
	// alert("in getUsers");
	//logoutcheck();
	// alert("hello")
	var appBasePath = getAppBasePath();
	$.ajax({
				type : "Get",
				dataType : "json",
				url : appBasePath + "/user/getUsersList",
				success : function(data) {
					// alert(data);
					if (data.length > 0) {
						$('#userstable').html("");
						var count = 0;
						$.each(data,function(index, item) {
							var eachrow = '<tr class="gradeX">'
							+ "<td>"+ item.firstName+ "</td>"
							+ "<td>"+ item.lastName+ "</td>"
							+ "<td>"+ item.email+ "</td>"
							+ "<td>"+ item.empNum+ "</td>"
							+ "<td>"+ item.roleName+ "</td>"
							+ "<td>"+ item.reportTo+ "</td>"
							+ "<td>"+ item.location+ "</td>"
							+ "<td class=\"actions\"><a href=\"#\" class=\"on-default text-info m-r-10\" onclick=\"editView('"+ item.userId+ "','"+ item.firstName
							+ "','"+ item.lastName+ "','"+ item.email+ "','"+ item.empNum+ "','"+ item.roleName+ "','"+ item.reportTo+ "','"+ item.location
							+ "','"+ item.reportingId+ "','"+ item.roleId+ "');\" data-animation=\"fadein\" data-plugin=\"custommodal\" data-overlaySpeed=\"200\" data-overlayColor=\"#36404a\"><i class=\"fa fa-pencil\"></i></a><a href=\"#\" class=\"on-danger text-danger\" onclick=\"deleteView('"+ item.userId+ "');\" data-animation=\"fadein\" data-plugin=\"custommodal\" data-overlaySpeed=\"200\" data-overlayColor=\"#36404a\"><i class=\"fa fa-trash-o\"></i></a></td>"
						    + "</tr>";
							$('#userstable').append(eachrow);
						});
						$('#datatable-responsive').DataTable();
					} else {
						$('#userstable').append("");
						$('#datatable-responsive').DataTable({
							"language": {
								"emptyTable": "Users not exist for listout"
							}
						});
					}
				},
				error : function(req, status, msg) {
					alert("in error")
				}
			});

}

function editView(userId, fName, lname, email, empNum, role, report, location,
		reportId, roleId) {
	//alert("before check>>> "+userId);
	//logoutcheck();
	$("#firstname1").val(fName);
	$("#lastname1").val(lname);
	$("#email1").val(email);
	$("#empno1").val(empNum);
	$("#location1").val(location);
	$("#email1").prop("readonly", true);
	$("#empno1").prop("readonly", true);
	$("#roleId1").val(roleId);
	// alert(reportId);
	// var arr = report.split('-');
	// var rep1=arr[0];
	// alert(reportId);
	// $("#reportingId1 option[value='"+rep1+"']").attr("selected", "selected");
	// $('#reportingId1 option[value="'+rep1+'"]').prop("selected", true);
	usersloader();
	Custombox.open({
		target : "#custom-modal-edit",
		effect : "fadein"
	});
	setTimeout(function(){$("#reportingId1").val(reportId);},500);
	$("button#save").attr('onclick','updateUser('+userId+')');
	/*$("button#save").on("click",function() {
		alert(userId);
		updateUser(userId);
	});*/
}
function deleteView(userId) {
	//logoutcheck();
	Custombox.open({
		target : "#custom-modal",
		effect : "fadein"
	});
	$("button#confirm").attr('onclick','deleteUser('+userId+')');
}
function deleteUser(userId){
	var appBasePath = getAppBasePath();
	$.ajax({
		type : "POST",
		dataType : "json",
		url : appBasePath + "/user/deleteUser/" + userId,
		success : function(data) {
			Custombox.close();
			alert(data.message);
			getUsersList();

		},
		error : function(req, status, msg) {
			alert("in error" + req);
		}
	});
}
function roleLoader() {
	//logoutcheck();
	var appBasePath = getAppBasePath();
	$.ajax({
		type : "GET",
		dataType : "json",
		url : appBasePath + "/crud/getallroles",
		success : function(data) {
			//alert("list of roles: " + data);
			for (var i = 0; i < data.length; i++) {
				var roleOption = "<option value=" +data[i].role_id+">"
						+ data[i].role_name +  "</option>";
				$('#roleId').append(roleOption);
				// alert("reportOption::"+reportOption);
				$('#roleId1').append(roleOption);
			}
			return true;
		},
		error : function(req, status, msg) {
			alert("in error")
		}
	});
}
