/* function for create resource*/
var returnVal=false;
function createEmployee()
{
	//$("a#next").addClass("ladda-button");
	//alert("innnnn");
	/*var l = $('#next').ladda();
    l.ladda('start');*/
	//logoutcheck();
	var appBasePath = getAppBasePath();
	var firstName=$("#firstName").val().trim();
	var lastName=$("#lastName").val().trim();
	var email=$("#email").val().trim();
	var contact=$("#contact").val().trim();
	var joinDate=$("#datepicker-autoclose1").val().trim();
	var skillSet=$("#skillSet").val().trim();
	var employeeId=0;
	var experienceLevel=$("#experienceLevel").val().trim();
	var color = 'red';
	$('#empErrorMessage').css('color', color);
	// First name validation
	if (firstName.trim().length == 0) {
		// alert(firstname.trim().length)
		$('#empErrorMessage').html(VALIDATION_CREATEUSER_FIRST_NAME_EMPTY_ERROR);
		$('#firstName').focus();
		//l.ladda('stop')
		return false;
	} else if (firstName.trim().length < 3) {
		// alert("in length" + firstname.trim().length)
		$('#empErrorMessage').html(VALIDATION_CREATEUSER_FIRST_NAME_LENGTH_ERROR);
		$('#firstName').focus();
		return false;
	} else if (!isNaN(firstName)) {
		$('#empErrorMessage').html(VALIDATION_CREATEUSER_FIRST_NAME_SPECIALS_ERROR);
		$('#firstName').focus();
		return false;
	} else {
		$('#empErrorMessage').html("");
	}
	if (firstName.trim().length > 25) {
		$("#empErrorMessage").html(VALIDATION_CREATEUSER_FIRST_NAME_MAX_LENGTH_ERROR);
		$("#firstName").focus();
		setTimeout(function() {
			$("#empErrorMessage").html("");
		}, 5000);
		return false;
	} else {
		$('#empErrorMessage').html("");
	}
	// LAST NAME VALIDATION
	
	if (lastName.trim().length == 0) {
		$('#empErrorMessage').html(VALIDATION_CREATEUSER_LAST_NAME_EMPTY_ERROR);
		$('#lastName').focus();
		return false;
	} else if (lastName.trim().length < 3) {
		$('#empErrorMessage').html(VALIDATION_CREATEUSER_LAST_NAME_LENGTH_ERROR);
		$('#lastName').focus();
		return false;
	} else if (!isNaN(lastName)) {
		$('#empErrorMessage').html(VALIDATION_CREATEUSER_LAST_NAME_SPECIALS_ERROR);
		$('#lastName').focus();
		return false;
	} else {
		$('#empErrorMessage').html("");
	}
	if(lastName.trim().length>25  )
 	{
 		 $("#empErrorMessage").html(VALIDATION_CREATEUSER_LAST_NAME_MAX_LENGTH_ERROR);
 		 $("#lastName").focus();
 		 setTimeout(function (){
        		 $("#empErrorMessage").html("");
        	 },5000);
 		return false;
 	}
	else {
		$('#empErrorMessage').html("");
	}
	// EMAIL VALIDATION
	if (email.trim().length == 0) {
		$('#empErrorMessage').html(VALIDATION_CREATEUSER_EMAIL_EMPTY_ERROR);
		$('#email').focus();
		return false;
	} else if (!ValidateEmail(email)) {
		$('#empErrorMessage').html(VALIDATION_CREATEUSER_EMAIL_VALID_ERROR);
		$('#email').focus();
		setTimeout(function() {
			$("#empErrorMessage").html("");
		}, 5000);
		return false;
	} else {
		$('#empErrorMessage').html("");
	}
	// Contact validation
	if (contact.trim().length == 0) {
		$('#empErrorMessage').html(VALIDATION_CONTACT_EMPTY_ERROR);
		$('#contact').focus();
		return false;
	} else if (contact.trim().length < 8) {
		$('#empErrorMessage').html(VALIDATION_CONTACT_LENGTH_ERROR);
		$('#contact').focus();
		return false;
	} else if (isNaN(contact)) {
		$('#empErrorMessage').html(VALIDATION_CONTACT_SPECIALS_ERROR);
		$('#contact').focus();
		return false;
	} else {
		$('#empErrorMessage').html("");
	}
	// SKILL SET VALIDATION
	if (skillSet.trim().length == 0) {
		$('#empErrorMessage').html(VALIDATION_SKILLSET_EMPTY_ERROR);
		$('#skillSet').focus();
		return false;
	} else {
		$('#empErrorMessage').html("");
	}
	// Experience LEVEL VALIDATION
	if (experienceLevel.trim().length == 0) {
		$('#empErrorMessage').html(VALIDATION_EXPERIENCE_EMPTY_ERROR);
		$('#experienceLevel').focus();
		return false;
	} else {
		$('#empErrorMessage').html("");
	}
	// JOINDATE VALIDATION
	if (joinDate.trim().length == 0) {
		$('#empErrorMessage').html(VALIDATION_DATE_EMPTY_ERROR);
		$('#datepicker-autoclose1').focus();
		return false;
	} else {
		$('#empErrorMessage').html("");
	}
	var params = {
			firstName : firstName ,
			lastName : lastName ,
			empEmailID : email ,
			empContact : contact ,
			empJoiningDate : joinDate ,
			skillSet : skillSet ,
			experienceLevel : experienceLevel
	};
	var queryParam = jQuery.param( params );
	$.ajax({
		type : "POST",
		dataType : "json",
		async: false,
		url : appBasePath + "/resource/createResource",
		data : queryParam,
		beforeSend : function() {
			$.blockUI({
			message: 'Loading please wait....'
			});
			},
		success : function(data) {
			if(data.statusCode==1)
			{
				$('#empErrorMessage').css('color', 'green');
				$('#empErrorMessage').html(data.message);
				$("#employee_Id").val(data.employeeId);
				returnVal=true;
				
			}
			else
			{
				$('#empErrorMessage').css('color', color);
				$('#empErrorMessage').html(data.message);
				returnVal=false;
				
			}
			getInActiveResourcesList();
			getActiveResourcesList();
			$.unblockUI();
			setTimeout(function() {
				$("#empErrorMessage").html("");
			}, 5000);
		},
		error : function(req, status, msg) {
			alert("in error" + req + status + msg);
			returnVal=false;
			
		}
	});
	return returnVal;
}
/*
 * function createResource() { createEmployee();
 * addProjectDetails($("#employee_Id").val()); //alert("empId::"+emp);
 * //alert("customerId:::"+customer); uploadSowfile($("#employee_Id").val());
 * addPODetails($("#employee_Id").val(),$('#customer_Id').val()); }
 */
function addProjectDetails(fkEmployeeId) {
	//logoutcheck();
	/*var l = $('#saveproject').ladda();
    l.ladda('start');*/
	var empId=$("#employee_Id").val();
	var appBasePath = getAppBasePath();
	var project_name = $("#project_name").val().trim();
	var project_desc = $("#project_desc").val().trim();
	var requirement_hm_name = $("#hm_name").val().trim();
	var requirement_hm_email = $("#hm_email").val().trim();
	var requirement_hm_contact = $("#hm_contact").val().trim();
	var requirement_pm_name = $("#pm_name").val().trim();
	var requirement_pm_email = $("#pm_email").val().trim();
	var requirement_pm_contact = $("#pm_contact").val().trim();
	var requirement_location = $("#location").val().trim();
	var requirement_rate_card = $("#rate_card").val().trim();
	var requirement_customer_id = $("#customer_id").val();
	var color = 'red';
	$('#projectErrorMessage').css('color', color)
	// PROJECT NAME VALIDATION
	if (project_name.trim().length == 0) {
		$('#projectErrorMessage').html(VALIDATION_PROJECT_NAME_EMPTY_ERROR);
		$('#project_name').focus();
		return false;
	} else if (project_name.trim().length < 3) {
		$('#projectErrorMessage').html(VALIDATION_PROJECT_NAME_LENGTH_ERROR);
		$('#project_name').focus();
		return false;
	} else if (!isNaN(project_name)) {
		$('#projectErrorMessage').html(VALIDATIONPROJECT_NAME_SPECIALS_ERROR);
		$('#project_name').focus();
		return false;
	} else {
		$('#projectErrorMessage').html("");
	}
	if(project_name.trim().length>25  )
 	{
 		 $("#projectErrorMessage").html(VALIDATION_PROJECT_NAME_MAX_LENGTH_ERROR);
 		 $("#project_name").focus();
 		 setTimeout(function (){
        		 $("#projectErrorMessage").html("");
        	 },5000);
 		return false;
 	}
	else {
		$('#projectErrorMessage').html("");
	}
	// PROJECT DESCRIPTION VALIDATION
	if (project_desc.trim().length == 0) {
		$('#projectErrorMessage').html(VALIDATION_PROJECT_DESC_EMPTY_ERROR);
		 $("#project_desc").focus();
		return false;
	} else if (project_desc.trim().length < 3) {
		$('#projectErrorMessage').html(VALIDATION_PROJECT_DESC_LENGTH_ERROR);
		 $("#project_desc").focus();
		return false;
	} else {
		$('#projectErrorMessage').html("");
	}
	// hm name validation
	if (requirement_hm_name.trim().length == 0) {
		$('#projectErrorMessage').html(VALIDATION_hmName_EMPTY_ERROR);
		 $("#hm_name").focus();
		return false;
	} else if (requirement_hm_name.trim().length < 3) {
		$('#projectErrorMessage').html(VALIDATION_hmName_LENGTH_ERROR);
		$("#hm_name").focus();
		return false;
	} else {
		$('#projectErrorMessage').html("");
	}
	// hm email validation
	if (requirement_hm_email.trim().length == 0) {
		$('#projectErrorMessage').html(VALIDATION_HM_EMAIL_EMPTY_ERROR);
		$("#hm_email").focus();
		return false;
	} else if (!ValidateEmail(requirement_hm_email)) {
		$('#projectErrorMessage').html(VALIDATION_HM_EMAIL_VALID_ERROR);
		$('#hm_email').focus();
		setTimeout(function() {
			$("#projectErrorMessage").html("");
		}, 5000);
		return false;
	} else {
		$('#projectErrorMessage').html("");
	}
	// hm Contact Validation
	if (requirement_hm_contact.trim().length == 0) {
		$('#projectErrorMessage').html(VALIDATION_HM_CONTACT_EMPTY_ERROR);
		$('#hm_contact').focus();
		return false;
	} else if (requirement_hm_contact.trim().length < 8) {
		$('#projectErrorMessage').html(VALIDATION_HM_CONTACT_LENGTH_ERROR);
		$('#hm_contact').focus();
		return false;
	} else if (isNaN(requirement_hm_contact)) {
		$('#projectErrorMessage').html(VALIDATION_HM_CONTACT_SPECIALS_ERROR);
		$('#hm_contact').focus();
		return false;
	} else {
		$('#projectErrorMessage').html("");
	}
	// pm name validation
	if (requirement_pm_name.trim().length == 0) {
		$('#projectErrorMessage').html(VALIDATION_pmName_EMPTY_ERROR);
		$('#pm_name').focus();
		return false;
	} else if (requirement_pm_name.trim().length < 3) {
		$('#projectErrorMessage').html(VALIDATION_pmName_LENGTH_ERROR);
		$('#pm_name').focus();
		return false;
	} else {
		$('#projectErrorMessage').html("");
	}
	// pm email validation
	if (requirement_pm_email.trim().length == 0) {
		$('#projectErrorMessage').html(VALIDATION_PM_EMAIL_EMPTY_ERROR);
		$('#pm_email').focus();
		return false;
	} else if (!ValidateEmail(requirement_pm_email)) {
		// $('#emaildiv').show();
		$('#projectErrorMessage').html(VALIDATION_PM_EMAIL_VALID_ERROR);
		$('#pm_email').focus();
		setTimeout(function() {
			$("#projectErrorMessage").html("");
		}, 5000);
		return false;
	} else {
		$('#projectErrorMessage').html("");
	}
	// pm Contact Validation
	if (requirement_pm_contact.trim().length == 0) {
		$('#projectErrorMessage').html(VALIDATION_PM_CONTACT_EMPTY_ERROR);
		$('#pm_contact').focus();
		return false;
	} else if (requirement_pm_contact.trim().length < 8) {
		$('#projectErrorMessage').html(VALIDATION_PM_CONTACT_LENGTH_ERROR);
		$('#pm_contact').focus();
		return false;
	} else if (isNaN(requirement_pm_contact)) {
		$('#projectErrorMessage').html(VALIDATION_PM_CONTACT_SPECIALS_ERROR);
		$('#pm_contact').focus();
		return false;
	} else {
		$('#projectErrorMessage').html("");
	}
	// Customer validation
	if (requirement_customer_id=="Select Customer" || requirement_customer_id==0) {
		$('#projectErrorMessage').html(VALIDATION_CUSTOMER_EMPTY_ERROR);
		$('#customer_id').focus();
		return false;
	} else {
		$('#projectErrorMessage').html("");
	}
	// location Validation
	if (requirement_location.trim().length == 0) {
		$('#projectErrorMessage').html(VALIDATION_LOCATION_EMPTY_ERROR);
		$('#location').focus();
		return false;
	} else if (requirement_location.trim().length < 3) {
		$('#projectErrorMessage').html(VALIDATION_LOCATION_LENGTH_ERROR);
		$('#location').focus();
		return false;
	} else if (!isNaN(requirement_location)) {
		$('#projectErrorMessage').html(VALIDATION_LOCATION_SPECIALS_ERROR);
		$('#location').focus();
		return false;
	} else {
		$('#projectErrorMessage').html("");
	}
	// rateCard validaton
	if (requirement_rate_card.trim().length == 0) {
		$('#projectErrorMessage').html(VALIDATION_RATE_CARD_EMPTY_ERROR);
		$('#rate_card').focus();
		return false;
	}  else if (isNaN(requirement_rate_card)) {
		$('#projectErrorMessage').html(VALIDATION_RATE_CARD_SPECIALS_ERROR);
		$('#rate_card').focus();
		return false;
	} 
	else {
		$('#projectErrorMessage').html("");
	}
	 if(requirement_rate_card!=null || requirement_rate_card!=0)
	 {
		 $("#unitPrice").val(requirement_rate_card);
		 $("#unitPrice").prop("readonly", true);
	 }
	var params = {
			projectName : project_name ,
			projectDescription : project_desc,
			hmName : requirement_hm_name ,
			hmEmail : requirement_hm_email ,
			hmContact : requirement_hm_contact ,
			pmName : requirement_pm_name ,
			pmEmail : requirement_pm_email ,
			pmContact : requirement_pm_contact ,
			location : requirement_location ,
			rateCard : requirement_rate_card ,
			fkCustomerId : requirement_customer_id,
			fkEmployeeId:empId
	};
	var queryParam = jQuery.param( params );
	// alert(queryParam);
	$.ajax({
		type : "GET",
		dataType : "json",
		async: false,
		url : appBasePath + "/requirements/saverequirements" ,
		data : queryParam,
		beforeSend : function() {
			$.blockUI({
			message: 'Loading please wait....'
			});
			},
		success : function(data) {
			if (data.statusCode == 1) {
				//alert(data.message);
				$('#projectErrorMessage').html(data.message)
				$('#projectErrorMessage').css('color', 'green');
				$("#customer_id").val(data.customerId);
				setTimeout(function() {
					$("#projectErrorMessage").html("");
				}, 5000);
				returnVal=true;
				//alert($("#customer_id").val());
			} else {
				$('#projectErrorMessage').html(data.message)
				$('#createusermsg').css('color', color);
				setTimeout(function() {
					$("#projectErrorMessage").html("");
				}, 5000);
				returnVal=false;
			}
			getInActiveResourcesList();
			getActiveResourcesList();
			 $.unblockUI(); 
			
		},
		error : function(req, status, msg) {
			alert("Error while Processing the request");
			returnVal=false;
		}
	});
 return returnVal;
}

function uploadSowfile(fkEmployeeId)
{
	//logoutcheck();
	/*var l = $('#savesow').ladda();
    l.ladda('start');*/
	var fkEmployeeId=$("#employee_Id").val();
	var appBasePath = getAppBasePath();
	 var form = $('#dropzone1')[0];
	 var data = new FormData(form);
	$.ajax({
		type : "POST",
		dataType : "json",
		async: false,
		enctype: 'multipart/form-data',
		url : appBasePath + "/podetails/uploadSOWFile/" +fkEmployeeId ,
		data:data,
	    processData: false, // prevent jQuery from automatically transforming
							// the data into a query string
	    contentType: false,
	    cache: false,
	    beforeSend : function() {
			$.blockUI({
			message: 'Loading please wait....'
			});
			},
		success : function(data) {
			if (data.statusCode == 1) {
				//alert(data.message);
				$('#sowErrorMessage').css('color', 'green');
				$('#sowErrorMessage').html(data.message);
				console.log("SUCCESS : ", data);
				returnVal=true;
			} else {
				$('#sowErrorMessage').css('color', 'red');
				$('#sowErrorMessage').html(data.message);
				returnVal=false;
			}
			getInActiveResourcesList();
			getActiveResourcesList();
			 $.unblockUI(); 
			setTimeout(function() {
				$("#sowErrorMessage").html("");
			}, 5000);
			
		},
		error : function(req, status, msg) {
			alert("in error" + msg);
			returnVal=false;
		}
	});
	return returnVal;
}

function addPODetails(fkEmployeeId,fkCustomerId)
{
	//logoutcheck();
	/*var l = $('#save').ladda();
    l.ladda('start');*/
	var fkEmployeeId=$("#employee_Id").val();
	var fkCustomerId=$("#customer_id").val();
	var appBasePath = getAppBasePath();
	var description=$("#description").val().trim();
	var poNumber=$("#poNumber").val().trim();
	var supplierRefNumber=$("#supplierRefNumber").val().trim();
	var currency=$("#currency").val();
	var durationMonths=$("#durationMonths").val();
	var unitPrice=$("#unitPrice").val();
	var startDate=$("#startDate").val().trim();
	var endDate=$("#endDate").val().trim();
	var raisedBy=$("#raisedBy").val().trim();
	var raisedOn=$("#datepicker-autoclose").val().trim();
	var form = $('#dropzone')[0];
	var data = new FormData(form);
	var color = 'red';
	 $('#poErrorMessage').css('color', color);
	 // validation for PO Number
	 if (poNumber.trim().length == 0) {
			$('#poErrorMessage').html(VALIDATION_PO_EMPTY_ERROR);
			return false;
		} else if (poNumber.trim().length < 3) {
			$('#poErrorMessage').html(VALIDATION_PO_LENGTH_ERROR);
			return false;
		} else {
			$('#poErrorMessage').html("");
		}
	 if(poNumber.trim().length>25  )
	 	{
	 		 $("#poErrorMessage").html(VALIDATION_PO_MAX_LENGTH_ERROR);
	 		 $("#poNumber").focus();
	 		 setTimeout(function (){
	        		 $("#poErrorMessage").html("");
	        	 },5000);
	 		return false;
	 	}
		else {
			$('#poErrorMessage').html("");
		}
	 // validation for description
	 if (description.trim().length == 0) {
			$('#poErrorMessage').html(VALIDATION_PROJECT_DESC_EMPTY_ERROR);
			return false;
		} else if (description.trim().length < 3) {
			$('#poErrorMessage').html(VALIDATION_PROJECT_DESC_LENGTH_ERROR);
			return false;
		} else {
			$('#poErrorMessage').html("");
		}
	 // validation for supplierRefNumber
	 if (supplierRefNumber.trim().length == 0) {
			$('#poErrorMessage').html(VALIDATION_SUPREF_EMPTY_ERROR);
			return false;
		} else if (supplierRefNumber.trim().length < 3) {
			$('#poErrorMessage').html(VALIDATION_SUPREF_LENGTH_ERROR);
			return false;
		} else {
			$('#poErrorMessage').html("");
		}
	 if(supplierRefNumber.trim().length>25  )
	 	{
	 		 $("#poErrorMessage").html(VALIDATION_SUPREF_MAX_LENGTH_ERROR);
	 		 $("#supplierRefNumber").focus();
	 		 setTimeout(function (){
	        		 $("#poErrorMessage").html("");
	        	 },5000);
	 		return false;
	 	}
		else {
			$('#poErrorMessage').html("");
		}
	 // Currency validation
	 if (currency=="Select Currency" || currency==0 || currency==null) {
			$('#poErrorMessage').html(VALIDATION_CURRENCY_EMPTY_ERROR);
			return false;
		} else {
			$('#poErrorMessage').html("");
		}
    // PO raised by validation
	 if (raisedOn.trim().length == 0) {
			$('#poErrorMessage').html(VALIDATION_PO_RAISED_ON_EMPTY_ERROR);
			return false;
		} else {
			$('#poErrorMessage').html("");
		}
	 // PO Raised By validation
	 if (raisedBy.trim().length == 0) {
			$('#poErrorMessage').html(VALIDATION_PO_RAISED_BY_EMPTY_ERROR);
			return false;
		} else if (raisedBy.trim().length < 3) {
			$('#poErrorMessage').html(VALIDATION_PO_RAISED_BY_LENGTH_ERROR);
			return false;
		} else {
			$('#poErrorMessage').html("");
		}
	 if(raisedBy.trim().length>25  )
	 	{
	 		 $("#poErrorMessage").html(VALIDATION_PO_RAISED_BY_MAX_LENGTH_ERROR);
	 		 $("#raisedBy").focus();
	 		 setTimeout(function (){
	        		 $("#poErrorMessage").html("");
	        	 },5000);
	 		return false;
	 	}
		else {
			$('#poErrorMessage').html("");
		}
	 // Duration validation
	 if (durationMonths.trim().length == 0 || durationMonths==0) {
			$('#poErrorMessage').html(VALIDATION_DURATION_EMPTY_ERROR);
			return false;
		}  else if (isNaN(durationMonths)) {
			$('#poErrorMessage').html(VALIDATION_DURATION_SPECIALS_ERROR);
			return false;
		} else if(durationMonths>24){
			$('#poErrorMessage').html(VALIDATION_DURATION_MAX_LIMIT);
			return false;
		}
		else {
			$('#poErrorMessage').html("");
		}
	 // Start date validation
	 if (startDate.trim().length == 0) {
			$('#poErrorMessage').html(VALIDATION_START_DATE_EMPTY_ERROR);
			return false;
		} else {
			$('#poErrorMessage').html("");
		}
	 // End date validation
	 if (endDate.trim().length == 0) {
			$('#poErrorMessage').html(VALIDATION_END_DATE_EMPTY_ERROR);
			return false;
		} else {
			$('#poErrorMessage').html("");
		}
	 // Unit Price validation
	 if (unitPrice.trim().length == 0) {
			$('#poErrorMessage').html(VALIDATION_UNIT_PRICE_EMPTY_ERROR);
			return false;
		}  else if (isNaN(unitPrice)) {
			$('#poErrorMessage').html(VALIDATION_UNIT_PRICE_SPECIALS_ERROR);
			return false;
		} 
		else if(unitPrice<0)
		{
			 $('#poErrorMessage').html(VALIDATION_UNIT_PRICE_SPECIALS_ERROR);
			 $('#unitPrice').focus();
			 return false;
		}
		else {
			$('#poErrorMessage').html("");
		}
	 
	 data.append("description",description);
	 data.append("poNumber",poNumber);
	 data.append("supplierRefNumber",supplierRefNumber);
	 data.append("currency",currency);
	 data.append("durationMonths",durationMonths);
	 data.append("unitPrice",unitPrice);
	 data.append("startDate",startDate);
	 data.append("endDate",endDate);
	 data.append("raisedBy",raisedBy);
	 data.append("raisedOn",raisedOn);
	 data.append("fkEmployeeId",fkEmployeeId);
	 data.append("fkCustomerId",fkCustomerId);
			 
	$.ajax({
		type : "POST",
		//dataType : "json",
		enctype: 'multipart/form-data',
		async: false,
		url : appBasePath + "/podetails/addPODetails",
		data:data,
	    processData: false, // prevent jQuery from automatically transforming
							// the data into a query string
	    contentType: false,
	    cache: false,
	    beforeSend : function() {
			$.blockUI({
			message: 'Loading please wait....'
			});
			},
		success : function(data) {
			// alert(data.message);
			if(data.statusCode==1)
			{
				$('#poErrorMessage').css('color', 'green');
				$('#poErrorMessage').html(data.message);
				returnVal=true;
			}
			else
			{
				$('#poErrorMessage').css('color', color);
				$('#poErrorMessage').html(data.message);
				returnVal=false;
			}
			getInActiveResourcesList();
			getActiveResourcesList();
			$.unblockUI(); 
			setTimeout(function() {
				$("#poErrorMessage").html("");
			}, 5000);

		},
		error : function(req, status, msg) {
			alert("in error" + req + status + msg);
			returnVal=false;
		}
	});
	return returnVal;
}
// Inactive resource Listout
function getInActiveResourcesList() {
	//logoutcheck();
	var appBasePath = getAppBasePath();
	$.ajax({
				type : "Get",
				dataType : "json",
				url : appBasePath + "/resource/inactiveResourceList",
				success : function(data) {
					// alert(data);
					if (data.length > 0) {
						$('#inactiveList').html("");
						var count = 0;
						$.each(data,function(index, item) {
							//alert("project::"+item.projectName);
						   if(item.projectName=="null")
							{
							   item.projectName="Not assigned";
							}
							var eachrow = '<tr class="gradeX">'
							+ "<td>"+ item.employeeName+ "</td>"
							+ "<td>"+ item.emailID+ "</td>"
							+ "<td>"+ item.contactNumber+ "</td>"
							+ "<td>"+ item.projectName+ "</td>"
							+ "<td>"+ item.joiningDate+ "</td>"
							+ "<td class=\"actions\"><a href=\"#\" class=\"on-default text-info m-r-10\" onclick=\"resourceEditView('"+ item.employeeId+ "');" +
						   "\" data-animation=\"fadein\" data-plugin=\"custommodal\" data-overlaySpeed=\"200\" data-overlayColor=\"#36404a\"><i class=\"fa fa-pencil\""
							+ " data-toggle=\"tooltip\" data-placement=\"top\" title=\"Edit resource.\""
							+"></i></a><a href=\"#\" class=\"on-danger text-danger m-r-10\"" +
						   " onclick=\"resourceDeleteView('"+ item.employeeId+ "','"+ item.emailID+ "');\" data-animation=\"fadein\" data-plugin=\"custommodal\" data-overlaySpeed=\"200\" data-overlayColor=\"#36404a\"><i class=\"fa fa-trash-o\"" 
						    + " data-toggle=\"tooltip\" data-placement=\"top\" title=\"Delete resource.\""
						    +"></i></a>" 
						    +"<a href=\"#\"class=\"on-danger text-danger m-r-10\" "
			                + "onclick=\"resourceReassignView('"+ item.employeeId+ "','"+ item.emailID+ "');\" data-animation=\"fadein\" data-plugin=\"custommodal\" data-overlaySpeed=\"200\" data-overlayColor=\"#36404a\"><i class=\"fa fa-sitemap text-success\"" 
			                + " data-toggle=\"tooltip\" data-placement=\"top\" title=\"Reassign resource.\""
			                +"></i></a>" 
						    +"</td>"
						    + "</tr>"
							$('#inactiveList').append(eachrow);
						});
						$('#datatable-responsive').DataTable();
					} else {
						$('#datatable-responsive').dataTable().fnDestroy();
						$('#inactiveList').html("");
						$('#datatable-responsive').DataTable({
							"language": {
								"emptyTable": "Non Active Resources not exist for listout"
							}
						});
					}

				},
				error : function(req, status, msg) {
					alert("in error")
				}
			});
}
// Active resoures listout
function getActiveResourcesList() {
	//logoutcheck();
	var appBasePath = getAppBasePath();
	$.ajax({
				type : "Get",
				//dataType : "json",
				url : appBasePath + "/resource/activeResourceList",
				success : function(data) {
					// alert(data);
					if (data.length > 0) {
						$('#activeList').html("");
						var count = 0;
						$.each(data,function(index, item) {
							var eachrow = '<tr class="gradeX">'
							+ "<td>"+ item.employeeName+ "</td>"
							+ "<td>"+ item.emailID+ "</td>"
							+ "<td>"+ item.contactNumber+ "</td>"
							+ "<td>"+ item.projectName+ "</td>"
							+ "<td>"+ item.joiningDate+ "</td>"
							+ "<td class=\"actions\"><a href=\"#\" class=\"on-default text-info m-r-10\" onclick=\"resourceEditView('"+ item.employeeId+ "');\" data-animation=\"fadein\" data-plugin=\"custommodal\" data-overlaySpeed=\"200\" data-overlayColor=\"#36404a\"><i class=\"fa fa-pencil\" " 
						    + " data-toggle=\"tooltip\" data-placement=\"top\" title=\"Edit resource.\""
							+ "></i></a><a href=\"#\" class=\"on-danger text-danger m-r-10\" "
			                + "onclick=\"resourceDeleteView('"+ item.employeeId+ "','"+ item.emailID+ "');\" data-animation=\"fadein\" data-plugin=\"custommodal\" data-overlaySpeed=\"200\" data-overlayColor=\"#36404a\"><i class=\"fa fa-trash-o\" "
			                + " data-toggle=\"tooltip\" data-placement=\"top\" title=\"Delete resource.\""
			                +"></i></a>" 
			               	+"<a href=\"#\" class=\"on-default text-success m-r-10\" onclick=\"resourcePreView('"+ item.employeeId+ "');\" data-animation=\"fadein\" data-plugin=\"custommodal\" data-overlaySpeed=\"200\" data-overlayColor=\"#36404a\"><i class=\"fa fa-eye-slash\" "
			                + " data-toggle=\"tooltip\" data-placement=\"top\" title=\"Resource Preview.\""
			               	+"></i></a>"
			                +"<a href=\"#\" class=\"on-danger text-danger m-r-10\" "
			                + "onclick=\"resourceReassignView('"+ item.employeeId+ "','"+ item.emailID+ "');\" data-animation=\"fadein\" data-plugin=\"custommodal\" data-overlaySpeed=\"200\" data-overlayColor=\"#36404a\"><i class=\"fa fa-sitemap text-success\"" 
			                + " data-toggle=\"tooltip\" data-placement=\"top\" title=\"Reassign resource.\""
			                +"></i></a>" 
			               	+"</td>"
						    + "</tr>"
							$('#activeList').append(eachrow);
						});
						$('#datatable-responsive').DataTable();
					} else {
						$('#datatable-responsive').dataTable().fnDestroy();
						$('#activeList').html("");
						$('#datatable-responsive').DataTable({
							"language": {
								"emptyTable": "Active Resources not exist for listout"
							}
						});
					}

				},
				error : function(req, status, msg) {
					alert("in error" + req + status + msg);
				}
			});
}
/* function for  check delete resource */
function checkDeleteResource(employeeId)
{
	var appBasePath = getAppBasePath();
	var employeeId;
	$.ajax({
		type : "POST",
		dataType : "json",
		url : appBasePath + "/resource/checkDeleteResource/"+employeeId,
		success : function(data) {
			if(data.expireIn>0)
			{
				$("p#deleteText").html("Are you sure that you want to delete this Resource still has contract for"+data.expireIn+"days?");
			}
			else{
				$("p#deleteText").html("Are you sure that you want to delete this row?");
			}
		},
		error : function(req, status, msg) {
			alert("in error" + req + status + msg);
		}
	});
}
/* function for delete resource */
function deleteResource(employeeId,emailID)
{
	//logoutcheck();
	//alert("emailID::"+emailID);
	var appBasePath = getAppBasePath();
	var employeeId;
	$.ajax({
		type : "POST",
		dataType : "json",
		url : appBasePath + "/resource/deleteResource/"+emailID+"/"+employeeId,
		success : function(data) {
			alert(data.message);
			getInActiveResourcesList();
			getActiveResourcesList();
			Custombox.close();
			getNotificationPanel();
		},
		error : function(req, status, msg) {
			alert("in error" + req + status + msg);
		}
	});
}
/* function for update resource */
function updateEmployee()
{
	//logoutcheck();
	/*var l = $('#updateemp').ladda();
    l.ladda('start');*/
	var appBasePath = getAppBasePath();
	var employeeId=$("#employee_Id_u").val();
	var firstName=$("#updatefirstName").val().trim();
	var lastName=$("#updatelastName").val().trim();
	var email=$("#updateemail").val().trim();
	var contact=$("#updatecontact").val().trim();
	var joinDate=$("#datepicker-autoclose1").val().trim();
	var skillSet=$("#updateskillSet").val().trim();
	var experienceLevel=$("#updateexperienceLevel").val().trim();
	var color = 'red';
	$('#empErrorMessage').css('color', color);
	if (firstName.trim().length == 0) {
		// alert(firstname.trim().length)
		$('#empErrorMessage').html(VALIDATION_CREATEUSER_FIRST_NAME_EMPTY_ERROR);
		$('#updatefirstName').focus();
		return false;
	} else if (firstName.trim().length < 3) {
		// alert("in length" + firstname.trim().length)
		$('#empErrorMessage').html(VALIDATION_CREATEUSER_FIRST_NAME_LENGTH_ERROR);
		$('#updatefirstName').focus();
		return false;
	} else if (!isNaN(firstName)) {
		$('#empErrorMessage').html(VALIDATION_CREATEUSER_FIRST_NAME_SPECIALS_ERROR);
		$('#updatefirstName').focus();
		return false;
	} else {
		$('#empErrorMessage').html("");
	}
	if (firstName.trim().length > 25) {
		$("#empErrorMessage").html(VALIDATION_CREATEUSER_FIRST_NAME_MAX_LENGTH_ERROR);
		$("#updatefirstName").focus();
		setTimeout(function() {
			$("#empErrorMessage").html("");
		}, 5000);
		return false;
	} else {
		$('#empErrorMessage').html("");
	}
	// LAST NAME VALIDATION
	
	if (lastName.trim().length == 0) {
		$('#empErrorMessage').html(VALIDATION_CREATEUSER_LAST_NAME_EMPTY_ERROR);
		$('#updatelastName').focus();
		return false;
	} else if (lastName.trim().length < 3) {
		$('#empErrorMessage').html(VALIDATION_CREATEUSER_LAST_NAME_LENGTH_ERROR);
		$('#updatelastName').focus();
		return false;
	} else if (!isNaN(lastName)) {
		$('#empErrorMessage').html(VALIDATION_CREATEUSER_LAST_NAME_SPECIALS_ERROR);
		$('#updatelastName').focus();
		return false;
	} else {
		$('#empErrorMessage').html("");
	}
	if(lastName.trim().length>25)
 	{
 		 $("#empErrorMessage").html(VALIDATION_CREATEUSER_LAST_NAME_MAX_LENGTH_ERROR);
 		 $("#updatelastName").focus();
 		 setTimeout(function (){
        		 $("#empErrorMessage").html("");
        	 },5000);
 		return false;
 	}
	else {
		$('#empErrorMessage').html("");
	}
	// EMAIL VALIDATION
	if (email.trim().length == 0) {
		$('#empErrorMessage').html(VALIDATION_CREATEUSER_EMAIL_EMPTY_ERROR);
		$('#updateemail').focus();
		return false;
	} else if (!ValidateEmail(email)) {
		$('#empErrorMessage').html(VALIDATION_CREATEUSER_EMAIL_VALID_ERROR);
		$('#updateemail').focus();
		setTimeout(function() {
			$("#empErrorMessage").html("");
		}, 5000);
		return false;
	} else {
		$('#empErrorMessage').html("");
	}
	// Contact validation
	if (contact.trim().length == 0) {
		$('#empErrorMessage').html(VALIDATION_CONTACT_EMPTY_ERROR);
		$('#updatecontact').focus();
		return false;
	} else if (contact.trim().length < 8) {
		$('#empErrorMessage').html(VALIDATION_CONTACT_LENGTH_ERROR);
		$('#updatecontact').focus();
		return false;
	} else if (isNaN(contact)) {
		$('#empErrorMessage').html(VALIDATION_CONTACT_SPECIALS_ERROR);
		$('#updatecontact').focus();
		return false;
	} else {
		$('#empErrorMessage').html("");
	}
	// SKILL SET VALIDATION
	if (skillSet.trim().length == 0) {
		$('#empErrorMessage').html(VALIDATION_SKILLSET_EMPTY_ERROR);
		$('#updateskillSet').focus();
		return false;
	} else {
		$('#empErrorMessage').html("");
	}
	// Experience LEVEL VALIDATION
	if (experienceLevel.trim().length == 0) {
		$('#empErrorMessage').html(VALIDATION_EXPERIENCE_EMPTY_ERROR);
		$('#updateexperienceLevel').focus();
		return false;
	} else {
		$('#empErrorMessage').html("");
	}
	// JOINDATE VALIDATION
	if (joinDate.trim().length == 0) {
		$('#empErrorMessage').html(VALIDATION_DATE_EMPTY_ERROR);
		$('#datepicker-autoclose1').focus();
		return false;
	} else {
		$('#empErrorMessage').html("");
	}
	var params = {
			employeeId : employeeId ,
			firstName : firstName ,
			lastName : lastName ,
			email : email ,
			contact : contact ,
			joinDate : joinDate ,
			skillSet : skillSet,
			experienceLevel:experienceLevel
	};
	var queryParam = jQuery.param( params );
	///{employeeId}/{firstName}/{lastName}/{email}/{contact}/{joinDate}/{skillSet}/{experienceLevel}
	$.ajax({
		type : "POST",
		dataType : "json",
		async: false,
		url : appBasePath + "/resource/updateResource",
		data : queryParam, 
		beforeSend : function() {
			$.blockUI({
			message: 'Loading please wait....'
			});
			},
		success : function(data) {
			// alert(data.message);
			if(data.statusCode==1)
			{
				$('#empErrorMessage').css('color', 'green');
				$('#empErrorMessage').html(data.message);
				$("#employee_Id").val(data.employeeId);
				getNotificationPanel();
				returnVal=true;
			}
			else
			{
				$('#empErrorMessage').css('color', color);
				$('#empErrorMessage').html(data.message);
				returnVal=true;
			}
			getInActiveResourcesList();
			getActiveResourcesList();
			$.unblockUI(); 
			setTimeout(function() {
				$("#empErrorMessage").html("");
			}, 5000);
		},
		error : function(req, status, msg) {
			alert("in error" + req + status + msg);
			returnVal=false;
		}
	});
	return returnVal;
}
function updateProjectDetails() {
	//logoutcheck();
	/*var l = $('#updateproject').ladda();
    l.ladda('start');*/
	var appBasePath = getAppBasePath();
	var fkEmployeeId=$("#employee_Id_u").val();
	var project_name = $("#project_name_u").val().trim();
	var project_desc = $("#project_desc_u").val().trim();
	var requirement_hm_name = $("#hm_name_u").val().trim();
	var requirement_hm_email = $("#hm_email_u").val().trim();
	var requirement_hm_contact = $("#hm_contact_u").val().trim();
	var requirement_pm_name = $("#pm_name_u").val().trim();
	var requirement_pm_email = $("#pm_email_u").val().trim();
	var requirement_pm_contact = $("#pm_contact_u").val().trim();
	var requirement_location = $("#location_u").val().trim();
	var requirement_rate_card = $("#rate_card_u").val().trim();
	var requirement_customer_id = $("#customer_id_u").val();
	var color = 'red';
	$('#projectErrorMessage').css('color', color);
	// PROJECT NAME VALIDATION
	if (project_name.trim().length == 0) {
		$('#projectErrorMessage').html(VALIDATION_PROJECT_NAME_EMPTY_ERROR);
		$('#project_name_u').focus();
		return false;
	} else if (project_name.trim().length < 3) {
		$('#projectErrorMessage').html(VALIDATION_PROJECT_NAME_LENGTH_ERROR);
		$('#project_name_u').focus();
		return false;
	} else if (!isNaN(project_name)) {
		$('#projectErrorMessage').html(VALIDATIONPROJECT_NAME_SPECIALS_ERROR);
		$('#project_name_u').focus();
		return false;
	} else {
		$('#projectErrorMessage').html("");
	}
	if(project_name.trim().length>25  )
 	{
 		 $("#projectErrorMessage").html(VALIDATION_PROJECT_NAME_MAX_LENGTH_ERROR);
 		$('#project_name_u').focus();
 		 setTimeout(function (){
        		 $("#projectErrorMessage").html("");
        	 },5000);
 		return false;
 	}
	else {
		$('#projectErrorMessage').html("");
	}
	// PROJECT DESCRIPTION VALIDATION
	if (project_desc.trim().length == 0) {
		$('#projectErrorMessage').html(VALIDATION_PROJECT_DESC_EMPTY_ERROR);
		 $("#project_desc_u").focus();
		return false;
	} else if (project_desc.trim().length < 3) {
		$('#projectErrorMessage').html(VALIDATION_PROJECT_DESC_LENGTH_ERROR);
		 $("#project_desc_u").focus();
		return false;
	} else {
		$('#projectErrorMessage').html("");
	}
	// hm name validation
	if (requirement_hm_name.trim().length == 0) {
		$('#projectErrorMessage').html(VALIDATION_hmName_EMPTY_ERROR);
		 $("#hm_name_u").focus();
		return false;
	} else if (requirement_hm_name.trim().length < 3) {
		$('#projectErrorMessage').html(VALIDATION_hmName_LENGTH_ERROR);
		 $("#hm_name_u").focus();
		return false;
	} else {
		$('#projectErrorMessage').html("");
	}
	// hm email validation
	if (requirement_hm_email.trim().length == 0) {
		$('#projectErrorMessage').html(VALIDATION_HM_EMAIL_EMPTY_ERROR);
		$("#hm_email_u").focus();
		return false;
	} else if (!ValidateEmail(requirement_hm_email)) {
		$('#projectErrorMessage').html(VALIDATION_HM_EMAIL_VALID_ERROR);
		$('#hm_email_u').focus();
		setTimeout(function() {
			$("#projectErrorMessage").html("");
		}, 5000);
		return false;
	} else {
		$('#projectErrorMessage').html("");
	}
	// hm Contact Validation
	if (requirement_hm_contact.trim().length == 0) {
		$('#projectErrorMessage').html(VALIDATION_HM_CONTACT_EMPTY_ERROR);
		$('#hm_contact_u').focus();
		return false;
	} else if (requirement_hm_contact.trim().length < 8) {
		$('#projectErrorMessage').html(VALIDATION_HM_CONTACT_LENGTH_ERROR);
		$('#hm_contact_u').focus();
		return false;
	} else if (isNaN(requirement_hm_contact)) {
		$('#projectErrorMessage').html(VALIDATION_HM_CONTACT_SPECIALS_ERROR);
		$('#hm_contact_u').focus();
		return false;
	} else {
		$('#projectErrorMessage').html("");
	}
	// pm name validation
	if (requirement_pm_name.trim().length == 0) {
		$('#projectErrorMessage').html(VALIDATION_pmName_EMPTY_ERROR);
		$('#pm_name_u').focus();
		return false;
	} else if (requirement_pm_name.trim().length < 3) {
		$('#projectErrorMessage').html(VALIDATION_pmName_LENGTH_ERROR);
		$('#pm_name_u').focus();
		return false;
	} else {
		$('#projectErrorMessage').html("");
	}
	// pm email validation
	if (requirement_pm_email.trim().length == 0) {
		$('#projectErrorMessage').html(VALIDATION_PM_EMAIL_EMPTY_ERROR);
		$('#pm_email_u').focus();
		return false;
	} else if (!ValidateEmail(requirement_pm_email)) {
		// $('#emaildiv').show();
		$('#projectErrorMessage').html(VALIDATION_PM_EMAIL_VALID_ERROR);
		$('#pm_email_u').focus();
		setTimeout(function() {
			$("#projectErrorMessage").html("");
		}, 5000);
		return false;
	} else {
		$('#projectErrorMessage').html("");
	}
	// pm Contact Validation
	if (requirement_pm_contact.trim().length == 0) {
		$('#projectErrorMessage').html(VALIDATION_PM_CONTACT_EMPTY_ERROR);
		$('#pm_contact_u').focus();
		return false;
	} else if (requirement_pm_contact.trim().length < 8) {
		$('#projectErrorMessage').html(VALIDATION_PM_CONTACT_LENGTH_ERROR);
		$('#pm_contact_u').focus();
		return false;
	} else if (isNaN(requirement_pm_contact)) {
		$('#projectErrorMessage').html(VALIDATION_PM_CONTACT_SPECIALS_ERROR);
		$('#pm_contact_u').focus();
		return false;
	} else {
		$('#projectErrorMessage').html("");
	}
	// location Validation
	if (requirement_location.trim().length == 0) {
		$('#projectErrorMessage').html(VALIDATION_LOCATION_EMPTY_ERROR);
		$('#location_u').focus();
		return false;
	} else if (requirement_location.trim().length < 3) {
		$('#projectErrorMessage').html(VALIDATION_LOCATION_LENGTH_ERROR);
		$('#location_u').focus();
		return false;
	} else if (!isNaN(requirement_location)) {
		$('#projectErrorMessage').html(VALIDATION_LOCATION_SPECIALS_ERROR);
		$('#location_u').focus();
		return false;
	} else {
		$('#projectErrorMessage').html("");
	}
	// rateCard validaton
	if (requirement_rate_card.trim().length == 0) {
		$('#projectErrorMessage').html(VALIDATION_RATE_CARD_EMPTY_ERROR);
		$('#rate_card_u').focus();
		return false;
	}  else if (isNaN(requirement_rate_card)) {
		$('#projectErrorMessage').html(VALIDATION_RATE_CARD_SPECIALS_ERROR);
		$('#rate_card_u').focus();
		return false;
	} else {
		$('#projectErrorMessage').html("");
	}
	// Customer validation
	if (requirement_customer_id=="Select Customer" || requirement_customer_id==0) {
		$('#projectErrorMessage').html(VALIDATION_CUSTOMER_EMPTY_ERROR);
		$('#customer_id_u').focus();
		return false;
	} else {
		$('#projectErrorMessage').html("");
	}
	 if(requirement_rate_card!=null || requirement_rate_card!=0)
	 {
		 $("#updateunitPrice").val(requirement_rate_card);
		 $("#updateunitPrice").prop("readonly", true);
	 }

	var params = {
			projectName : project_name ,
			projectDescription : project_desc,
			hmName : requirement_hm_name ,
			hmEmail : requirement_hm_email ,
			hmContact : requirement_hm_contact ,
			pmName : requirement_pm_name ,
			pmEmail : requirement_pm_email ,
			pmContact : requirement_pm_contact ,
			location : requirement_location ,
			rateCard : requirement_rate_card ,
			fkCustomerId : requirement_customer_id,
			fkEmployeeId:fkEmployeeId
	};
	
	var queryParam = jQuery.param( params );
	// alert(queryParam);
	$.ajax({
		type : "GET",
		dataType : "json",
	    async: false,
		url : appBasePath + "/requirements/updaterequirements" ,
		data : queryParam,
		beforeSend : function() {
			$.blockUI({
			message: 'Loading please wait....'
			});
			},
		success : function(data) {
			// alert(data.message);
			if(data.statusCode==1)
			{
				$('#projectErrorMessage').css('color', 'green');
				$('#projectErrorMessage').html(data.message);
				$("#customer_id").val(data.customerId);
				returnVal=true;
			}
			else
			{
				$('#projectErrorMessage').css('color', color);
				$('#projectErrorMessage').html(data.message);
				returnVal=true;
			}
			getInActiveResourcesList();
			getActiveResourcesList();
			$.unblockUI(); 
			setTimeout(function() {
				$("#projectErrorMessage").html("");
			}, 5000);
		},
		 error : function(req, status, msg) {
			alert("in error" + req + status + msg);
			returnVal=false;
		}
	});
	return returnVal;
}

function updateSowfile()
{
	
	//logoutcheck();
	/*var l = $('#updatesow').ladda();
    l.ladda('start');*/
	var fkEmployeeId=$("#employee_Id_u").val();
	var form = $('form#sowForm')[0];
	 var data = new FormData(form);
	var appBasePath = getAppBasePath();
	$.ajax({
		type : "POST",
		dataType : "json",
		enctype: 'multipart/form-data',
		async: false,
		url : appBasePath + "/podetails/updateSOWFile/" +fkEmployeeId ,
		data:data,
	    processData: false, // prevent jQuery from automatically transforming
	    contentType: false,
	    cache: false,
	    beforeSend : function() {
			$.blockUI({
			message: 'Loading please wait....'
			});
			},
		success : function(data) {
			if (data.statusCode == 1) 
			{
				$('#sowErrorMessage').css('color', 'green');
				$('#sowErrorMessage').html(data.message);
				getSowFilesList(fkEmployeeId);
				 returnVal=true;
			
			}
			else
			{
				if(data.noOfSowfiles==0){
					returnVal=false;
				}
				else{
					returnVal=true;
				}
				$('#sowErrorMessage').css('color', 'red');
				$('#sowErrorMessage').html(data.message);
			}
			getInActiveResourcesList();
			getActiveResourcesList();
			$.unblockUI(); 
			setTimeout(function() {
				$("#sowErrorMessage").html("");
			}, 5000);
			// alert(data.message);

		},
		error : function(req, status, msg) {
			alert("in error" + msg);
			 returnVal=false;
		}
	});
	return returnVal;
}
function endDateOnchange()
{
	var durationMonths = parseInt($("#durationMonths").val());
	var startDate = $("#startDate").val();
	// alert("startDate:"+startDate);
	var arr = startDate.split("-");
	var CurrentDate = new Date(arr[0], arr[1], arr[2]);
	var futuredate = new Date(CurrentDate.setMonth(CurrentDate
			.getMonth()
			+ (durationMonths - 1)));
	var endDatestr = futuredate.toISOString();
	var endDate = endDatestr.slice(0, 10);
	// alert("endDate::"+endDate);
	$('#endDate').val(endDate);
	$('#endDate').prop("readonly", true);
}
function allowOnlyNumbers(event)
{
	var duration=$("#durationMonths").val();
	var c= String.fromCharCode(event.keyCode);
	if(!($.isNumeric(c)) )
	{
		duration=duration.slice(0,-1);
		$("#durationMonths").val(duration);
	}
	endDateOnchange();

}
function allowOnlyNumbersforUpdateDuration(event)
{
	var duration=$("#updatedurationMonths").val();
	var c= String.fromCharCode(event.keyCode);
	//alert("c:::"+c);
	if(!($.isNumeric(c)) )
	{
		duration=duration.slice(0,-1);
		$("#updatedurationMonths").val(duration);
	}
	updateEndDateOnChange();

}
function updateEndDateOnChange()
{
	//alert("in bind");
	var durationMonths = parseInt($("#updatedurationMonths").val());
	var startDate = $("#updatestartDate").val();
	//alert("startDate:"+startDate);
	var arr = startDate.split("-");
	var CurrentDate = new Date(arr[0],arr[1],arr[2]);
	var futuredate= new Date(CurrentDate.setMonth(CurrentDate.getMonth() + (durationMonths-1)));
	var endDatestr=futuredate.toISOString();
	var endDate=endDatestr.slice(0, 10);
	//alert("endDate::"+endDate);
	$('#updateendDate').val(endDate);
	$('#updateendDate').prop("readonly", true);
	//$( "#updateendDate" ).datepicker( "option", "disabled", true );

}

function updatePODetails()
{
	//logoutcheck();
	/*var l = $('#updatepo').ladda();
    l.ladda('start');*/
	var fkEmployeeId=$("#employee_Id_u").val();
	var fkCustomerId=$("#customer_id_u").val();
	//alert("fkCustomerId::"+fkCustomerId);
	var form = $('form#poForm')[0];
	 var data = new FormData(form);
	var appBasePath = getAppBasePath();
	var description=$("#updatedescription").val().trim();
	var poNumber=$("#updatepoNumber").val().trim();
	var supplierRefNumber=$("#updatesupplierRefNumber").val().trim();
	var currency=$("#updatecurrency").val();
	var durationMonths=$("#updatedurationMonths").val();
	var unitPrice=$("#updateunitPrice").val();
	var startDate=$("#updatestartDate").val().trim();
	var endDate=$("#updateendDate").val().trim();
	var raisedBy=$("#updateraisedBy").val().trim();
	var raisedOn=$("#datepicker-autoclose").val().trim();
	// var fkCustomerId=$("#customer_id_u").val();
	var color = 'red';
	 	$('#poErrorMessage').css('color', color);
		if(poNumber.trim().length==0 ||poNumber==null)
		{
			$('#poErrorMessage').html(VALIDATION_PO_EMPTY_ERROR);
			return false;
		}	
		else if (poNumber.trim().length < 3) {
			$('#poErrorMessage').html(VALIDATION_PO_LENGTH_ERROR);
			return false;
		} else {
			$('#poErrorMessage').html("");
		}
	 if (description.trim().length == 0) {
			$('#poErrorMessage').html(VALIDATION_PROJECT_DESC_EMPTY_ERROR);
			return false;
		} else if (description.trim().length < 3) {
			$('#poErrorMessage').html(VALIDATION_PROJECT_DESC_LENGTH_ERROR);
			return false;
		} else {
			$('#poErrorMessage').html("");
		}
	 // validation for supplierRefNumber
	 if (supplierRefNumber.trim().length == 0) {
			$('#poErrorMessage').html(VALIDATION_SUPREF_EMPTY_ERROR);
			return false;
		} else if (supplierRefNumber.trim().length < 3) {
			$('#poErrorMessage').html(VALIDATION_SUPREF_LENGTH_ERROR);
			return false;
		} else {
			$('#poErrorMessage').html("");
		}
	 if(supplierRefNumber.trim().length>25  )
	 	{
	 		 $("#poErrorMessage").html(VALIDATION_SUPREF_MAX_LENGTH_ERROR);
	 		 $("#updatesupplierRefNumber").focus();
	 		 setTimeout(function (){
	        		 $("#poErrorMessage").html("");
	        	 },5000);
	 		return false;
	 	}
		else {
			$('#poErrorMessage').html("");
		}
	 // Currency validation
	// alert("currency::"+currency)
	 if (currency=="Select Currency"||currency==0 || currency==null) 
	 {
			$('#poErrorMessage').html(VALIDATION_CURRENCY_EMPTY_ERROR);
			return false;
		} else {
			$('#poErrorMessage').html("");
		}
 // PO raised by validation
	 if (raisedOn.trim().length == 0) {
			$('#poErrorMessage').html(VALIDATION_PO_RAISED_ON_EMPTY_ERROR);
			return false;
		} else {
			$('#poErrorMessage').html("");
		}
	 // PO Raised By validation
	 if (raisedBy.trim().length == 0) {
			$('#poErrorMessage').html(VALIDATION_PO_RAISED_BY_EMPTY_ERROR);
			return false;
		} else if (raisedBy.trim().length < 3) {
			$('#poErrorMessage').html(VALIDATION_PO_RAISED_BY_LENGTH_ERROR);
			return false;
		} else {
			$('#poErrorMessage').html("");
		}
	 if(raisedBy.trim().length>25  )
	 	{
	 		 $("#poErrorMessage").html(VALIDATION_PO_RAISED_BY_MAX_LENGTH_ERROR);
	 		 $("#updateraisedBy").focus();
	 		 setTimeout(function (){
	        		 $("#poErrorMessage").html("");
	        	 },5000);
	 		return false;
	 	}
		else {
			$('#poErrorMessage').html("");
		}
	 // Duration validation
	 if (durationMonths.trim().length == 0 || durationMonths==0) {
			$('#poErrorMessage').html(VALIDATION_DURATION_EMPTY_ERROR);
			return false;
		}  else if (isNaN(durationMonths)) {
			$('#poErrorMessage').html(VALIDATION_DURATION_SPECIALS_ERROR);
			return false;
		} else if(durationMonths>24){
			$('#poErrorMessage').html(VALIDATION_DURATION_MAX_LIMIT);
			return false;
		}
		else {
			$('#poErrorMessage').html("");
		}
	 // Start date validation
	 if (startDate.trim().length == 0) {
			$('#poErrorMessage').html(VALIDATION_START_DATE_EMPTY_ERROR);
			return false;
		} else {
			$('#poErrorMessage').html("");
		}
	 // End date validation
	 if (endDate.trim().length == 0) {
			$('#poErrorMessage').html(VALIDATION_END_DATE_EMPTY_ERROR);
			return false;
		} else {
			$('#poErrorMessage').html("");
		}
	 // Unit Price validation
	 if (unitPrice.trim().length == 0) {
			$('#poErrorMessage').html(VALIDATION_UNIT_PRICE_EMPTY_ERROR);
			return false;
		}  else if (isNaN(unitPrice)) {
			$('#poErrorMessage').html(VALIDATION_UNIT_PRICE_SPECIALS_ERROR);
			return false;
		} 
		else if(unitPrice<0)
		{
			 $('#poErrorMessage').html(VALIDATION_UNIT_PRICE_SPECIALS_ERROR);
			 $('#updateunitPrice').focus();
				return false;
		}
		else {
			$('#poErrorMessage').html("");
		}
	 data.append("fkEmployeeId",fkEmployeeId);
	 data.append("fkCustomerId",fkCustomerId);
	 data.append("description",description);
	 data.append("poNumber",poNumber);
	 data.append("supplierRefNumber",supplierRefNumber);
	 data.append("currency",currency);
	 data.append("durationMonths",durationMonths);
	 data.append("unitPrice",unitPrice);
	 data.append("startDate",startDate);
	 data.append("endDate",endDate);
	 data.append("raisedBy",raisedBy);
	 data.append("raisedOn",raisedOn);
///{fkEmployeeId}/{fkCustomerId}/{description}/{poNumber}/{supplierRefNumber}/{currency}/{durationMonths}/{unitPrice}/{startDate}/{endDate}/{raisedBy}/{raisedOn}
	$.ajax({
		type : "POST",
		dataType : "json",
		enctype: 'multipart/form-data',
		async: false,
		url : appBasePath + "/podetails/updatePODetails",
		data:data,
	    processData: false, 
	    contentType: false,
	    cache: false,
	    beforeSend : function() {
			$.blockUI({
			message: 'Loading please wait....'
			});
			},
		success : function(data) {
			// alert(data.message);
			if(data.statusCode==1)
			{
				$('#poErrorMessage').css('color', 'green');
				$('#poErrorMessage').html(data.message);
				returnVal=true;
				getPoFilesList(fkEmployeeId);
				getNotificationPanel();
			}
			else
			{
				$('#poErrorMessage').css('color', color);
				$('#poErrorMessage').html(data.message);
				returnVal=true;
			}
			getInActiveResourcesList();
			getActiveResourcesList();
			$.unblockUI(); 
			setTimeout(function() {
				$("#poErrorMessage").html("");
			    $("#updatedescription").val("");
				$("#updatepoNumber").val("");
				$("#updatesupplierRefNumber").val("");
				$("#updatecurrency").val("");
				$("#updatedurationMonths").val("");
				$("#updateunitPrice").val("");
				$("#updatestartDate").val("");
				$("#updateendDate").val("");
				$("#updateraisedBy").val("");
				$("#datepicker-autoclose").val("");
				$('#project_name_u').html(" ");
				$('#project_desc_u').html(" ");
				$('#hm_name_u').html(" ");
				$('#hm_email_u').html(" ");
				$('#hm_contact_u').html(" ");
				$('#pm_name_u').html(" ");
				$('#pm_email_u').html(" ");
				$('#pm_contact_u').html(" ");
				$('#customer_id_u').val(0);
				$('#location_u').html(" ");
				$('#rate_card_u').html(" ");
				$('#sow_files_list').html("");
				
			}, 5000);
		},
		error : function(req, status, msg) {
			alert("in error" + req + status + msg);
			returnVal=false;
		}
	});
	return returnVal;
}
function customerList()
{
	//logoutcheck();
	var appBasePath = getAppBasePath();
	$.ajax({
		type : "Get",
		dataType : "json",
		url : appBasePath + "/account/getAccountsList",
		success : function(data) {
			// alert("list of roles: " + data);
			for (var i = 0; i < data.length; i++) {
				var customerOption = "<option value=" +data[i].customerId+">"
						+ data[i].customerName +"-"+ data[i].location +"</option>";
				$('#customer_id').append(customerOption);
				$('#customer_id_u').append(customerOption);
			}
			return true;
		},
		error : function(req, status, msg) {
			alert("in error")
		}
	});
}
function getSowFilesList(employeeId)
{
	//logoutcheck();
	var appBasePath = getAppBasePath();
	$.ajax({
		type : "Get",
		dataType : "json",
		url : appBasePath + "/resource/getSowDetails/"+employeeId,
		success : function(data) {
			// alert(data);
		if (data.length > 0) {
			$('#sow_files_list').html("");
			var count = 0;
			$.each(data,function(index, item) {
              
				var eachrow = '<tr class="gradeX">'
				+ "<td>"+ item.sowFileName+ "</td>"
				+ "<td class=\"actions\"><a href=\"#\" class=\"on-danger text-danger\" "
				+ "onclick=\"sowFileDelete('"+ item.sowFileId+ "','"+employeeId+"');\" data-animation=\"fadein\" data-plugin=\"custommodal\" " 
				+ "data-overlaySpeed=\"200\" data-overlayColor=\"#36404a\"><i class=\"fa fa-trash-o\"></i></a></td>"
			    + "</tr>";
				$('#sow_files_list').append(eachrow);
			});
		} else {
			var eachrow = "<tr>"
					+ "<td colspan='2'>No Sow files were found</td>"
					+ "</tr>";
			$('#sow_files_list').html("");
			$('#sow_files_list').html(eachrow);
		}
		
		},
		error : function(req, status, msg) {
			alert("in error")
		}
	});

}
function sowFileDelete(fileId,employeeId)
{
	//logoutcheck();
	var appBasePath = getAppBasePath();
	$.ajax({
		type : "POST",
		dataType : "json",
		url : appBasePath + "/podetails/deleteSOWFiles/"+fileId+ "/"+ employeeId,
		success : function(data) {
			console.log(data);
			// alert(data.message);
			if (data.statusCode == 1) 
			{
				$('#sowErrorMessage').css('color', 'green');
				$('#sowErrorMessage').html(data.message);
			}
			else
			{
				$('#sowErrorMessage').css('color', 'red');
				$('#sowErrorMessage').html(data.message);
			}
			setTimeout(function() {
				$("#sowErrorMessage").html("");
			}, 5000);
			getSowFilesList(employeeId);
		},
		error : function(req, status, msg) {
			alert("in sow error" + req);
		}
	});


}
function getPoFilesList(employeeId)
{
	//logoutcheck();
	var appBasePath = getAppBasePath();
	$.ajax({
		type : "Get",
		dataType : "json",
		url : appBasePath + "/resource/getPoUploadDetails/"+employeeId,
		success : function(data) {
			// alert(data);
		if (data.length > 0) {
			$('#po_files_list').html("");
			var count = 0;
			$.each(data,function(index, item) {
               // <td class="text-left">File Name.docs</td>
               // <td class="text-left"><a href="" class="on-danger
				// text-danger"><i class="fa fa-trash-o"></i></a></td>
             // </tr>
				var eachrow = '<tr class="gradeX">'
				+ "<td>"+ item.poFileName+ "</td>"
				+ "<td class=\"actions\"><a href=\"#\" class=\"on-danger text-danger\" "
				+ "onclick=\"poFileDelete('"+ item.poFileId+ "','"+employeeId+"');\" data-animation=\"fadein\" data-plugin=\"custommodal\" " 
				+ "data-overlaySpeed=\"200\" data-overlayColor=\"#36404a\"><i class=\"fa fa-trash-o\"></i></a></td>"
			    + "</tr>";
				$('#po_files_list').append(eachrow);
			});
		} else {
			var eachrow = "<tr>"
					+ "<td colspan='2'>No PO files were found</td>"
					+ "</tr>";
			$('#po_files_list').html("");
			$('#po_files_list').html(eachrow);
		}
		
		},
		error : function(req, status, msg) {
			alert(" po files in error")
		}
	});

}
function poFileDelete(fileId,employeeId)
{
	//logoutcheck();
	var appBasePath = getAppBasePath();
	$.ajax({
		type : "POST",
		dataType : "json",
		url : appBasePath + "/podetails/deletePOFiles/"+fileId+ "/"+ employeeId,
		success : function(data) {
			console.log(data);
			// alert(data.message);
			if(data.statusCode==1)
			{
				$('#poErrorMessage').css('color', 'green');
				$('#poErrorMessage').html(data.message);
			}
			else
			{
				$('#poErrorMessage').css('color', 'red');
				$('#poErrorMessage').html(data.message);
			}
			setTimeout(function() {
				$("#poErrorMessage").html("");
			}, 5000);
			getPoFilesList(employeeId);
		},
		error : function(req, status, msg) {
			alert("in error" + req);
		}
	});

}
function resourceEditView(employeeId) {
	//logoutcheck();
	var customerId=$("#customer_id_u").val();
	//alert("customerId::"+customerId);
	Custombox.open({
		target : "#custom-modal-edit",
		effect : "fadein"
	});
	$("#employee_Id_u").val(employeeId);
	getEmployeeDetails(employeeId);
	getProjectDetails(employeeId);
	getPODetails(employeeId);
	getSowFilesList(employeeId);
	getPoFilesList(employeeId);
	/*$("button#updateemp").attr('onclick','updateEmployee('+employeeId+')');
	$("button#updateproject").attr('onclick','updateProjectDetails('+employeeId+')');
	$("button#updatepo").attr('onclick','updatePODetails('+employeeId+','+customerId+')');
	$("button#updatesow").attr('onclick','updateSowfile('+employeeId+')');*/
	

}
function resourcePreView(employeeId)
{
	//logoutcheck();
	Custombox.open({
		target : "#custom-modal-preview",
		effect : "fadein"
	});
	getResourceDetailsforPreview(employeeId);
	getProjectDetailsForPreview(employeeId);
	getPODetailsForPreview(employeeId);
	getSowFilesListForPreview(employeeId);
	getPoFilesListForPreview(employeeId);
}
/*****GET EMPLOYEE DETAILS FOR PREVIEW**********/
function getResourceDetailsforPreview(employeeId)
{
	$('#basicInfoPreview').html("");
	//logoutcheck();
	var appBasePath = getAppBasePath();
	$.ajax({
		type : "Get",
		// dataType : "json",
		url : appBasePath + "/resource/getResourceDetails/"+employeeId,
		success : function(data) {
			// alert("emp_data"+data);
			if (data!=""  || data!=null) 
			{
				// alert(data);
				var name=data.employeeName;
				var row='<p><strong>Employee Name:</strong>'+data.employeeName+'<br>'+
				'<strong>Email ID:</strong>'+data.emailID+'<br>'+
                '<strong>Contact Number</strong>'+data.contactNumber+'<br>'+
                '<strong>Skill Set:</strong>'+data.skillSet+'<br>'+
                '<strong>Experience:</strong>'+data.experienceLevel+'<br>'+
               '<strong>Joining Date:</strong>'+data.joiningDate+'</p>'
			   $('#basicInfoPreview').append(row);
				
				// alert("firstname"+firstname)
			} 
			
		},
		error : function(req, status, msg) {
			alert("error"+msg)
		}
	});
}
/*********function for getting project details for preview********/
function getProjectDetailsForPreview(employeeId)
{
	$('#projectInfoPreview').html("");
	//logoutcheck();
	var appBasePath = getAppBasePath();
	$.ajax({
		type : "Get",
		url : appBasePath + "/resource/getProjectDetails/"+employeeId,
		success : function(data) {
			if (data!="" || data!=null) 
			{
				
				var row='<p><strong>Project Name:</strong>'+data.projectName+'<br>'+
                         '<strong>Project Description:</strong>'+data.projectDesc+'<br>'+
                         '<strong>Hiring Manager Name:</strong>'+data.hmName+'<br>'+
                         '<strong>Hiring Manager Email:</strong>'+data.hmEmail+'<br>'+
                         '<strong>Hiring Manager Contact:</strong>'+data.hmContact+'<br>'+
                         '<strong>Procurement Manager Name:</strong> '+data.pmName+'<br>'+
                         '<strong>Procurement Manager Email:</strong> '+data.pmEmail+'<br>'+
                         '<strong>Procurement Manager Contact:</strong>'+data.pmContact+'<br>'+
                         '<strong>Customer Name:</strong>'+data.customerName+' <br>'+
                         '<strong>Location:</strong> '+data.location+'<br>'+
                         '<strong>Rate Card:</strong>'+data.rateCard+'</br>'
                         $('#projectInfoPreview').append(row);
				// alert("firstname"+firstname)
			} 
		},
		error : function(req, status, msg) {
			alert("error"+msg);
		}
	});
	
}
/********get po details for preview*********/
function getPODetailsForPreview(employeeId)
{
	//alert("in po");
	 $('#poForPreview').html("");
	//logoutcheck();
	var appBasePath = getAppBasePath();
	$.ajax({
		type : "Get",
		url : appBasePath + "/resource/getPoDetails/"+employeeId,
		success : function(data) {
			if (data!="") 
			{
				var startDate=data.startDate.split(" ");
				var endDate=data.endDate.split(" ");
				var raisedOn=data.raisedOn.split(" ");
				var row='<p><strong>PO Number:</strong>'+data.poNumber+'<br>'+
                    '<strong>Description:</strong>'+data.description+'<br>'+
                    '<strong>Supplier Reference Number:</strong>'+data.supplierRefNum+'<br>'+
                    '<strong>PO Raised On:</strong>'+raisedOn[0]+'<br>'+
                   '<strong>Raised By:</strong>'+data.raisedBy+'<br>'+
                    '<strong>Duration In Months:</strong>'+data.duration+'<br>'+
                    '<strong>Start End Date</strong> '+startDate[0]+' to '+endDate[0]+'<br>'+
                    '<strong>Currency:</strong>'+data.currency+ '<br>'+
                    '<strong>Unit Price:</strong>'+data.unitPrice+'</p>'
                    $('#poForPreview').append(row);
			} 
			
		},
		error : function(req, status, msg) {
			alert("error"+msg)
		}
	});
	
}
/******get  sow files for preview***********/
function getSowFilesListForPreview(employeeId)
{
	//logoutcheck();
	var appBasePath = getAppBasePath();
	$.ajax({
		type : "Get",
		dataType : "json",
		url : appBasePath + "/resource/getSowDetails/"+employeeId,
		success : function(data) {
			// alert(data);
		if (data.length > 0) {
			$('#sowfilesForPreview1').html("");
			$('#sowfilesForPreview2').html("");
			$.each(data,function(index, item) {
			if(index/2!=0)
			{
				$('#sowfilesForPreview1').append(item.sowFileName+'<br>');
			    $('#sowfilesForPreview1').wrapInner('<a href="'+appBasePath +"/fileViewer/download/"+"sow"+"/"+item.sowFileId+ '", target="_blank"/>');
			}	
			else
			{
				$('#sowfilesForPreview2').append(item.sowFileName+'<br>');
			    $('#sowfilesForPreview2').wrapInner('<a href="'+appBasePath +"/fileViewer/download/"+"sow"+"/"+item.sowFileId+ '", target="_blank"/>');
			}
			});
		} 
		else {
			$('#sowfilesForPreview1').html("No Sow files were found");
		}
		
		},
		error : function(req, status, msg) {
			alert("in error")
		}
	});

}
/*********get po files for preview*******/
function getPoFilesListForPreview(employeeId)
{
	//logoutcheck();
	var appBasePath = getAppBasePath();
	$.ajax({
		type : "Get",
		dataType : "json",
		url : appBasePath + "/resource/getPoUploadDetails/"+employeeId,
		success : function(data) {
		if (data.length > 0) {
			$('#pofilesForPreview1').html("");
			$('#pofilesForPreview2').html("");
			$.each(data,function(index, item) {
				if(index/2!=0)
				{
					$('#pofilesForPreview1').append(item.poFileName+'<br>');
					$('#pofilesForPreview1').wrapInner('<a href="'+appBasePath +"/fileViewer/download/"+"po"+"/"+item.poFileId +'", target="_blank"/>');
				}
				else
				{
					$('#pofilesForPreview2').append(item.poFileName+'<br>');
					$('#pofilesForPreview2').wrapInner('<a href="'+appBasePath +"/fileViewer/download/"+"po"+"/"+item.poFileId +'" target="_blank"/>');
				}
				
				
			});
		} else {
			$('#pofilesForPreview1').html("No Po files were found");
		}
		
		},
		error : function(req, status, msg) {
			alert(" po files in error")
		}
	});

}
function getEmployeeDetails(employeeId)
{
	//logoutcheck();
	var appBasePath = getAppBasePath();
	$.ajax({
		type : "Get",
		// dataType : "json",
		url : appBasePath + "/resource/getResourceDetails/"+employeeId,
		success : function(data) {
			// alert("emp_data"+data);
			if (data!="") 
			{
				// alert(data);
				$('#updatefirstName').val(data.firstName);
				$('#updatelastName').val(data.lastName);
				$('#updateemail').val(data.emailID);
				$('#updatecontact').val(data.contactNumber);
				$('#updateskillSet').val(data.skillSet);
				$('#updateexperienceLevel').val(data.experienceLevel);
				$('#datepicker-autoclose1').val(data.joiningDate);
				$("#updateemail").prop("readonly", true);
				// alert("firstname"+firstname)
			} 
			else
			{
				// alert("in else"+data);
				$('#updatefirstName').html(" ");
				$('#updatelastName').html(" ");
				$('#updateemail').html(" ");
				$('#updatecontact').html(" ");
				$('#updateskillSet').html(" ");
				$('#updateexperienceLevel').html(" ");
				$('#datepicker-autoclose1').html(" ");
			}
			
		},
		error : function(req, status, msg) {
			alert("error"+msg)
		}
	});
}
function getProjectDetails(employeeId)
{
	//logoutcheck();
	var appBasePath = getAppBasePath();
	$.ajax({
		type : "Get",
		url : appBasePath + "/resource/getProjectDetails/"+employeeId,
		success : function(data) {
			if (data!="") 
			{
				$('#project_name_u').val(data.projectName);
				$('#project_desc_u').val(data.projectDesc);
				$('#hm_name_u').val(data.hmName);
				$('#hm_email_u').val(data.hmEmail);
				$('#hm_contact_u').val(data.hmContact);
				$('#pm_name_u').val(data.pmName);
				$('#pm_email_u').val(data.pmEmail);
				$('#pm_contact_u').val(data.pmContact);
				$('#customer_id_u').val(data.fkCustomerId);
				$('#location_u').val(data.location);
				$('#rate_card_u').val(data.rateCard);
				$('#updateunitPrice').val(data.rateCard);
				$("#updateunitPrice").prop("readonly", true);
				// alert("firstname"+firstname)
			} 
			else
			{
				$('#project_name_u').html(" ");
				$('#project_desc_u').html(" ");
				$('#hm_name_u').html(" ");
				$('#hm_email_u').html(" ");
				$('#hm_contact_u').html(" ");
				$('#pm_name_u').html(" ");
				$('#pm_email_u').html(" ");
				$('#pm_contact_u').html(" ");
				$('#customer_id_u').val(0);
				$('#location_u').html(" ");
				$('#rate_card_u').html(" ");
				
			}
		},
		error : function(req, status, msg) {
			alert("error"+msg);
		}
	});
	
}
function getPODetails(employeeId)
{
	//logoutcheck();
	var appBasePath = getAppBasePath();
	$.ajax({
		type : "Get",
		url : appBasePath + "/resource/getPoDetails/"+employeeId,
		success : function(data) {
			if (data!="") 
			{
				var startDate=data.startDate.split(" ");
				var endDate=data.endDate.split(" ");
				var raisedOn=data.raisedOn.split(" ");
				$('#updatepoNumber').val(data.poNumber);
				$('#updatedescription').val(data.description);
				$('#updatesupplierRefNumber').val(data.supplierRefNum);
				$('#updatecurrency').val(data.currency);
				$('#datepicker-autoclose').val(raisedOn[0]);
				$('#updateraisedBy').val(data.raisedBy);
				$('#updatedurationMonths').val(data.duration);
				$('#updatestartDate').val(startDate[0]);
				$('#updateendDate').val(endDate[0]);
				$('#updateunitPrice').val(data.unitPrice);
				//alert("data.currency"+data.currency)
				if(data.poNumber!=null)
				{
					$("#updatepoNumber").prop("readonly", true);
				}
				
			} 
			else
		    {
				$('#updatepoNumber').html(" ");
				$('#updatedescription').html(" ");
				$('#updatesupplierRefNumber').html(" ");
				$('#updatecurrency').val(0);
				$('#datepicker-autoclose').html(" ");
				$('#updateraisedBy').html(" ");
				$('#updatedurationMonths').html(" ");
				$('#updatestartDate').html(" ");
				$('#updateendDate').html(" ");
				$('#updateunitPrice').html(" ");
				
		    }
		},
		error : function(req, status, msg) {
			alert("error"+msg)
		}
	});
}
function resourceDeleteView(employeeId,emailId) {
	//logoutcheck();
	var appBasePath = getAppBasePath();
	var emailId=emailId;
	var employeeId=employeeId;
	checkDeleteResource(employeeId);
	Custombox.open({
		target : "#custom-modal",
		effect : "fadein"
	});
	$("button#confirm").attr('onclick','deleteResource('+employeeId+',\''+emailId+'\')');
	
}
function resourceReassignView(employeeId,emailId) {
	//logoutcheck();
	var appBasePath = getAppBasePath();
	var emailId=emailId;
	var employeeId=employeeId;
	Custombox.open({
		target : "#custom-modal-reassign",
		effect : "fadein"
	});
	$("button#confirm").attr('onclick','reAssignResource('+employeeId+',\''+emailId+'\')');
}
function reAssignUsersList()
{
	//logoutcheck();
	var appBasePath = getAppBasePath();
	$.ajax({
		type : "Get",
		dataType : "json",
		url : appBasePath + "/resource/getUserListForReassign",
		success : function(data) {
			// alert("list of roles: " + data);
			for (var i = 0; i < data.length; i++) {
				var userOption = "<option value=" +data[i].userId+">"
						+ data[i].firstName +" "+ data[i].lastName +"</option>";
				$('#userForreasssign').append(userOption);
			}
			return true;
		},
		error : function(req, status, msg) {
			alert("in error")
		}
	});
}

function reAssignResource(employeeId,emailId){
	var userId = $("#userForreasssign").val();
	if(userId==0||userId=="Select User"){
		$('#reassignerror').css('color', 'red');
		$('#reassignerror').html(VALIDATION_REASSIGN_EMPTY_ERROR);
		return false;
	}else{
		$('#reassignerror').html();
	}
	var appBasePath = getAppBasePath();
	var params = {
			employeeId : employeeId ,
			email : emailId ,
			newcreatedBy : userId 
	}
	var queryParam = jQuery.param( params );
	$.ajax({
		type : "POST",
		data:queryParam,
		dataType : "json",
		url : appBasePath + "/resource/reassignResource",
		beforeSend : function() {
			$.blockUI({
			message: 'Loading please wait....'
			});
			},
		success : function(data) {
			if(data.statusCode==1){
				$('#reassignerror').css('color', 'green');
				$('#reassignerror').html(data.message);
			}
			else{
				$('#reassignerror').css('color', 'red');
				$('#reassignerror').html(data.message);
			}
			$.unblockUI();
			setTimeout(function() {
				$("#reassignerror").html("");
				$("#userForreasssign").val("")
			}, 5000);
			
		},
		error : function(req, status, msg) {
			alert("in error")
		}
	});
}
