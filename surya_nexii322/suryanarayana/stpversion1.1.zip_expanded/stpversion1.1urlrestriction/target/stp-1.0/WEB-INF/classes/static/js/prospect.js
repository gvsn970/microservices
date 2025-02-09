function addProspect() {
	var appBasePath = getAppBasePath();
	
	var prospectfName = $("#createprospectfName").val().trim();
	var prospectlName = $("#createprospectlName").val().trim();
	var fkCustomerId = $("#createprospectcustomer").val();
	var email=$("#createprospectemail").val().trim();
	var phNumber = $("#phnumber").val().trim();
	var alterNumber = $("#alternumber").val();
	var designation=$("#designation").val().trim();
	var addLane1 = $("#addlane1").val().trim();
	var addLane2 = $("#addlane2").val();
	var information=$("#information").val().trim();
	
	$('#prospectErrorMessage').css('color', 'red');
	if(prospectfName.trim().length==0){
		$('#prospectErrorMessage').html(VALIDATION_PROSPECT_FIRST_NAME_EMPTY_ERROR);
		$('#createprospectfName').focus();
		setTimeout(function() {
			$("#prospectErrorMessage").html("");
		}, 5000);
		return false;
	}
	if(prospectlName.trim().length==0){
		$('#prospectErrorMessage').html(VALIDATION_PROSPECT_LAST_NAME_EMPTY_ERROR);
		$('#createprospectlName').focus();
		setTimeout(function() {
			$("#prospectErrorMessage").html("");
		}, 5000);
		return false;
	}
	// EMAIL VALIDATION
	if (email.trim().length == 0) {
		$('#prospectErrorMessage').html(VALIDATION_PROSPECT_EMAIL_EMPTY_ERROR);
		$('#email').focus();
		setTimeout(function() {
			$("#prospectErrorMessage").html("");
		}, 5000);
		return false;
	} else if (!ValidateEmail(email)) {
		$('#prospectErrorMessage').html(VALIDATION_PROSPECT_EMAIL_EMPTY_ERROR);
		$('#email').focus();
		setTimeout(function() {
			$("#prospectErrorMessage").html("");
		}, 5000);
		return false;
	} else {
		$('#prospectErrorMessage').html("");
	}
	if(phNumber.trim().length==0){
		$('#prospectErrorMessage').html(VALIDATION_PROSPECT_PHONE_NUMBER_EMPTY_ERROR);
		$('#phnumber').focus();
		setTimeout(function() {
			$("#prospectErrorMessage").html("");
		}, 5000);
		return false;
	}
	if(isNaN(phNumber.trim())){
		$('#prospectErrorMessage').html(VALIDATION_PROSPECT_PHONE_NUMBER_VALID_ERROR);
		$('#phnumber').focus();
		setTimeout(function() {
			$("#prospectErrorMessage").html("");
		}, 5000);
		return false; 
	 }
	if(designation.trim().length==0){
		$('#prospectErrorMessage').html(VALIDATION_PROSPECT_DESIGNATION_EMPTY_ERROR);
		$('#designation').focus();
		setTimeout(function() {
			$("#prospectErrorMessage").html("");
		}, 5000);
		return false;
	}
	if(addLane1.trim().length==0){
		$('#prospectErrorMessage').html(VALIDATION_PROSPECT_ADDRESS1_EMPTY_ERROR);
		$('#addlane1').focus();
		setTimeout(function() {
			$("#prospectErrorMessage").html("");
		}, 5000);
		return false;
	}
	/*if(addLane2.trim().length==0){
		$('#prospectErrorMessage').html(VALIDATION_PROSPECT_ADDRESS2_EMPTY_ERROR);
		$('#addlane2').focus();
		setTimeout(function() {
			$("#prospectErrorMessage").html("");
		}, 5000);
		return false;
	}*/
	if(fkCustomerId==0|| fkCustomerId=="Select"){
		$('#prospectErrorMessage').html(VALIDATION_PROSPECT_COMPANY);
		$('#customer').focus();
		setTimeout(function() {
			$("#prospectErrorMessage").html("");
		}, 5000);
		return false;
	}
	if(information.trim().length==0){
		$('#prospectErrorMessage').html(VALIDATION_PROSPECT_INFORAMATION_EMPTY_ERROR);
		$('#information').focus();
		setTimeout(function() {
			$("#prospectErrorMessage").html("");
		}, 5000);
		return false;
	}
	if(information.trim().length>1000){
		$('#prospectErrorMessage').html(VALIDATION_PROSPECT_INFORAMATION_MAXLENGTH_ERROR);
		$('#information').focus();
		setTimeout(function() {
			$("#prospectErrorMessage").html("");
		}, 5000);
		return false;
	}
	
	
	var params = {
		firstName : prospectfName,
		lastName: prospectlName,
		phoneNumber:phNumber,
		alternateNumber:alterNumber,
		email:email,
		designation:designation,
		address1:addLane1,
		address2:addLane2,
		additionalInfo:information,
		fkCompanyId:fkCustomerId
	};
	var queryParam = jQuery.param(params);
	$.ajax({
		type : "POST",
		data : queryParam,
		dataType : "json",
		url : appBasePath + "/prospect/addContact",
		success : function(data) {
			// alert(data);
			if (data.statusCode == 1) {
				$('#prospectErrorMessage').css('color', 'green');
				$('#prospectErrorMessage').html(data.message);
			} else {
				$('#prospectErrorMessage').css('color', 'red');
				$('#prospectErrorMessage').html(data.message);
			}
			setTimeout(function() {
				$('#prospectErrorMessage').html("");
				$("#createprospectfName").val("");
				$("#createprospectlName").val("");
				$("#createprospectcustomer").val("");
				$("#createprospectemail").val("");
				$("#phnumber").val("");
				$("#alternumber").val("");
				$("#designation").val("");
				$("#designation").val("");
				$("#addlane1").val("");
				$("#addlane2").val("");
				$("#information").val("");
				prospectsList();
				customersList();
			}, 3000);

		},
		error : function(req, status, msg) {
			alert("in error" + req + status + msg);
		}
	});
}
function stageOnchange() {
	var stageId = $("#stageName").val();
	if(stageId==7){
     $( "#datetimepicker1" ).hide();
     $("#nextFollowupLabel").html("");
	}else{
		 $( "#datetimepicker1" ).show();
		 $("#nextFollowupLabel").html("Next Followup On");
	}
}
function updateProspect(prospectId,fkCompanyId) {
	//alert(prospectId);
	var appBasePath = getAppBasePath();
	var firstName = $("#updateprospectfName").val();
	var lastName = $("#updateprospectlName").val();
	var email = $("#email").val();
	var updatephnumber = $("#updatephnumber").val();
	var updatealternumber = $("#updatealternumber").val();
	var updatedesignation = $("#updatedesignation").val();
	var updateinformation = $("#updateinformation").val();
	var updateaddlane1=$("#updateaddlane1").val();
	var updateaddlane2=$("#updateaddlane2").val();
	//var fkCompanyId=$("#fkCompanyId").val();
	var meetingTypeId = $("#meetingtypeName").val();
	var stageId = $("#stageName").val();
	var nextFollowup=$("#datetimepicker1").val();
	var comments=$("#comments").val();
	var contactedOn=$("#datetimepicker").val();
	$('#prospectUpdateErrorMessage').css('color', 'red');
	if(firstName.trim().length==0){
		$('#prospectUpdateErrorMessage').html(VALIDATION_PROSPECT_FIRST_NAME_EMPTY_ERROR);
		$('#updateprospectfName').focus();
		setTimeout(function() {
			$("#prospectUpdateErrorMessage").html("");
		}, 5000);
		return false;
	}
	if(lastName.trim().length==0){
		$('#prospectUpdateErrorMessage').html(VALIDATION_PROSPECT_LAST_NAME_EMPTY_ERROR);
		$('#updateprospectlName').focus();
		setTimeout(function() {
			$("#prospectUpdateErrorMessage").html("");
		}, 5000);
		return false;
	}
	if(updatephnumber.trim().length==0){
		$('#prospectUpdateErrorMessage').html(VALIDATION_PROSPECT_PHONE_NUMBER_EMPTY_ERROR);
		$('#updatephnumber').focus();
		setTimeout(function() {
			$("#prospectUpdateErrorMessage").html("");
		}, 5000);
		return false;
	}
	if (isNaN(updatephnumber.trim())) {
		$('#prospectUpdateErrorMessage').html(VALIDATION_PROSPECT_PHONE_NUMBER_VALID_ERROR);
		$('#updatephnumber').focus();
		setTimeout(function() {
			$("#prospectUpdateErrorMessage").html("");
		}, 5000);
		return false;
	}
	if(updatedesignation.trim().length==0){
		$('#prospectUpdateErrorMessage').html(VALIDATION_PROSPECT_DESIGNATION_EMPTY_ERROR);
		$('#updatedesignation').focus();
		setTimeout(function() {
			$("#prospectUpdateErrorMessage").html("");
		}, 5000);
		return false;
	}
	if(updateaddlane1.trim().length==0){
		$('#prospectUpdateErrorMessage').html(VALIDATION_PROSPECT_ADDRESS1_EMPTY_ERROR);
		$('#updateaddlane1').focus();
		setTimeout(function() {
			$("#prospectUpdateErrorMessage").html("");
		}, 5000);
		return false;
	}
	/*if(updateaddlane2.trim().length==0){
		$('#prospectUpdateErrorMessage').html(VALIDATION_PROSPECT_ADDRESS2_EMPTY_ERROR);
		$('#updateaddlane2').focus();
		setTimeout(function() {
			$("#prospectUpdateErrorMessage").html("");
		}, 5000);
		return false;
	}*/
	if(updateinformation.trim().length==0){
		$('#prospectUpdateErrorMessage').html(VALIDATION_PROSPECT_INFORAMATION_EMPTY_ERROR);
		$('#updateinformation').focus();
		setTimeout(function() {
			$("#prospectUpdateErrorMessage").html("");
		}, 5000);
		return false;
	}
	if(meetingTypeId==0 || meetingTypeId=="Select Type" ||meetingTypeId==null){
		$('#prospectUpdateErrorMessage').html(VALIDATION_PROSPECT_MEETING_EMPTY_ERROR);
		setTimeout(function() {
			$("#prospectUpdateErrorMessage").html("");
		}, 5000);
		return false;
	}
	if(stageId==0 || stageId=="Select Stage" || stageId==null){
		$('#prospectUpdateErrorMessage').html(VALIDATION_PROSPECT_SELECT_STAGE_EMPTY_ERROR);
		setTimeout(function() {
			$("#prospectUpdateErrorMessage").html("");
		}, 5000);
		return false;
	}
	if(contactedOn.trim().length==0){
		$('#prospectUpdateErrorMessage').html(VALIDATION_PROSPECT_CONTRACTEDON_EMPTY_ERROR);
		setTimeout(function() {
			$("#prospectUpdateErrorMessage").html("");
		}, 5000);
		return false;
	}
	if(stageId==7){
		nextFollowup="";
	}else{
		if(nextFollowup.trim().length==0){
			$('#prospectUpdateErrorMessage').html(VALIDATION_PROSPECT_NEXTFOLLOWUP_EMPTY_ERROR);
			setTimeout(function() {
				$("#prospectUpdateErrorMessage").html("");
			}, 5000);
			return false;
		}
	}
	if(comments.trim().length==0){
		$('#prospectUpdateErrorMessage').html(VALIDATION_PROSPECT_COMMENTS_EMPTY_ERROR);
		$('#comments').focus();
		setTimeout(function() {
			$("#prospectUpdateErrorMessage").html("");
		}, 5000);
		return false;
	}
	if(comments.trim().length>1000){
		$('#prospectUpdateErrorMessage').html(VALIDATION_PROSPECT_COMMENTS_MAXLENGTH_ERROR);
		$('#comments').focus();
		setTimeout(function() {
			$("#prospectUpdateErrorMessage").html("");
		}, 5000);
		return false;
	}
	var params = {
			firstName:firstName,
			lastName:lastName,
			phoneNumber:updatephnumber,
			alternateNumber:updatealternumber,
			email : email,
			designation:updatedesignation,
			address1:updateaddlane1,
			address2:updateaddlane2,
			additionalInfo:updateinformation,
			fkCompanyId:fkCompanyId,
			prospectId:prospectId,
			meetingTypeId:meetingTypeId,
			stageId : stageId,
			nextFollowup:nextFollowup,
			comments:comments,
			contactedOn:contactedOn
	};
	//alert(params);
	var queryParam = jQuery.param(params);
	//alert(queryParam);
	$.ajax({
		type : "POST",
		data : queryParam,
		dataType : "json",
		url : appBasePath + "/prospect/updateContact",
		success : function(data) {
			// alert(data.message);
			if (data.statusCode == 1) {
				$('#prospectUpdateErrorMessage').css('color', 'green');
				$('#prospectUpdateErrorMessage').html(data.message);
				getNotificationPanel();
				Custombox.close();
			} else {
				$('#prospectUpdateErrorMessage').css('color', 'red');
				$('#prospectUpdateErrorMessage').html(data.message);
			}
			setTimeout(function() {
				$('#prospectUpdateErrorMessage').html("");
				$('#stageName').val("0");
				$('#meetingtypeName').val("0");
				$('#datetimepicker').val("");
				$('#datetimepicker1').val("");
				$('#comments').val('');
				todayFollowupList();
				myfunnelProspectsList();
				myFunnelAllFollowupList();
				myfunnelClosedProspectsList();
			}, 3000);

		},
		error : function(req, status, msg) {
			alert("in error" + req + status + msg);
		}
	});
}

function myFunnelAllFollowupList(){
	var appBasePath = getAppBasePath();
	var userId=$('#propsectUser').val();
	$.ajax({
		type : "GET",
		dataType : "json",
		url : appBasePath + "/prospect/allFollowupList?userTypeId="+userId,
		success : function(data) {
			//alert(data);
			if (data.length > 0) {
				//var createdon="";
				$('#active-prospects').html("");
				$.each(data,function(index, item) {
					//createdon=item.createdOn.split(" ");
					var eachrow = '<p><div class="col-lg-4"><div class="widget-profile-one">'+
					'<div class="card-box m-b-0 b-0 bg-success p-lg text-left"> '+
					'<h4 class="text-white m-b-5 m-t-5 font-600"> <i class="fa fa-user"></i> '+item.prospectName+'</h4></div>'+
					'<div class="card-box">'+
					'<p class="text-muted font-13 m-t-5"> <i class="fa fa-building"></i> '+item.companyName+'</p>'+
					'<p style="line-height:1;" class="text-muted font-13"> <i class="fa fa-envelope"></i> '+item.email+'</p>'+
					'<p style="line-height:1;" class="text-muted font-13"><i class="fa fa-phone"></i> '+item.phoneNumber+'</p>'+
					'<p style="line-height:1;" class="text-muted font-13"><i class="fa fa-calendar-check-o"></i>: '+item.nextFollowup+'</p>'+
					'<p style="line-height:1;" class="text-muted font-13"><i class="fa fa-users"></i> '+item.meetingType+'</p>'+
					'<p style="line-height:1;" class="text-muted font-13"><i class="fa fa-angle-double-right"></i> '+item.comments+'</p>'+
					'<div class="text-right">'+
					'<a href="#custom-modal-update" class="btn btn-xs btn-success waves-effect waves-light"'+
					'data-animation="fadein" data-plugin="custommodal" data-overlaySpeed="200" onclick="prospectEditView('+item.fkProspectId+','+item.fkCompanyId+')" data-overlayColor="#36404a">Update</a>'+
					'<a href="#custom-modal-history" class="btn btn-xs btn-inverse waves-effect waves-light m-l-10" data-animation="fadein" onclick="prospectHistoryView('+ item.fkProspectId+');" data-plugin="custommodal" data-overlaySpeed="200" data-overlayColor="#36404a">History</a>'+
					'</div></div></div></div></p>';
					
					$('#active-prospects').append(eachrow);
				});
				
			}else {
				$('#active-prospects').html("No active Prospects Found");
				
			}

		},
		error : function(req, status, msg) {
			alert("in error" + req + status + msg);
		}
	});
	
}
function todayFollowupList(){
	var appBasePath = getAppBasePath();
	var userId=$('#propsectUser').val();
	$.ajax({
		type : "GET",
		dataType : "json",
		url : appBasePath + "/prospect/todayFollowupList?userTypeId="+userId,
		success : function(data) {
			//alert(data);
			if (data.length > 0) {
				$('#todayfollowupList').html("");
				$.each(data,function(index, item) {
					var eachrow = '<tr class="gradeX">'
						+ "<td>"+ item.prospectName+ "</td>"
						+ "<td>"+ item.companyName+ "</td>"
						+ "<td>"+ item.email+ "</td>"
						+ "<td>"+ item.phoneNumber+ "</td>"
						+ "<td style=\"color:blue;\">"+ item.nextFollowup+ "</td>"
						+ "<td><a href=\"#custom-modal-update\" class=\"btn btn-xs btn-success waves-effect waves-light m-r-10\" onclick=\"prospectEditView('"+ item.fkProspectId+"','"+item.fkCompanyId+"');\" data-animation=\"fadein\" data-plugin=\"custommodal\" data-overlaySpeed=\"200\" data-overlayColor=\"#36404a\">Update</a>"
						+ '<a href="#custom-modal-history" class="btn btn-xs btn-inverse waves-effect waves-light" onclick="prospectHistoryView('+ item.fkProspectId+');" data-animation="fadein" data-plugin="custommodal" data-overlaySpeed="200" data-overlayColor="#36404a">History</a>'
						+"</td>"
						+ "</tr>";
					
					$('#todayfollowupList').append(eachrow);
				});
				$('#datatable-responsive').DataTable();
				
			}else {
				$('#datatable-responsive').dataTable().fnDestroy();
				$('#todayfollowupList').html("");
				$('#datatable-responsive').DataTable({
					"language": {
						"emptyTable": "Today there is no followups"
					}
				});
				
			}

		},
		error : function(req, status, msg) {
			alert("in error" + req + status + msg);
		}
	});
	
}

function prospectsList(){
	$('#datatable-responsive').dataTable().fnDestroy();
	var appBasePath = getAppBasePath();
	var userId=$('#propsectUser').val();
	//alert("userId::"+userId);
	//var status=1;
	$.ajax({
		type : "GET",
		dataType : "json",
		url : appBasePath + "/prospect/prospectList?userTypeId="+userId,
		success : function(data) {
			//alert(data);
			if (data.length > 0) {
				$('#prospectsList').html("");
				//var createdon="";
				$.each(data,function(index, item) {
					//createdon=item.createdOn.split(" ");
					var eachrow = '<tr class="gradeX">'
						+ "<td>"+ item.prospectName+ "</td>"
						+ "<td>"+ item.companyName+ "</td>"
						+ "<td>"+ item.email+ "</td>"
						+ "<td>"+ item.phoneNumber+ "</td>"
						+ "</tr>";
					
					$('#prospectsList').append(eachrow);
				});
				$('#datatable-responsive').DataTable();
				
			}else {
				$('#datatable-responsive').dataTable().fnDestroy();
				$('#prospectsList').html("");
				$('#datatable-responsive').DataTable({
					"language": {
						"emptyTable": "No Prospects to show in list"
					}
				});
				
			}

		},
		error : function(req, status, msg) {
			alert("in error" + req + status + msg);
		}
	});
	
}
//my funnal page all prospects
function myfunnelProspectsList(){
	var appBasePath = getAppBasePath();
	var userId=$('#propsectUser').val();
	//var status=1;
	$.ajax({
		type : "GET",
		dataType : "json",
		url : appBasePath + "/prospect/allprospectList?userTypeId="+userId,
		success : function(data) {
			//alert(data);
			if (data.length > 0) {
				$('#prospects').html("");
				//var createdon="";
				var colorclass="";
				var divcolorclass="";
				$.each(data,function(index, item) {
					//createdon=item.createdOn.split(" ");
					//alert(item.prospectName)
					if(item.prospectStatus==0){
						divcolorclass='class="card-box m-b-0 b-0 bg-inverse p-lg text-left"';
						colorclass='class="btn btn-xs btn-inverse waves-effect waves-light"';
						item.stage="Not Applicable";
						item.meetingType="Not Applicable";
						item.nextFollowup="Not Applicable";
						item.comments="Not Applicable";
					}else{
						divcolorclass='class="card-box m-b-0 b-0 bg-success p-lg text-left"';
						colorclass='class="btn btn-xs btn-success waves-effect waves-light"';
						item.prospectId=item.fkProspectId;
					}
					var eachrow = '<p><div class="col-lg-4"><div class="widget-profile-one">'+
						'<div '+divcolorclass+'> '+
						'<h4 class="text-white m-b-5 m-t-5 font-600" > <i class="fa fa-user"></i> '+item.prospectName+'</h4></div>'+
						'<div class="card-box">'+
						'<p class="text-muted font-13 m-t-5"><i class="fa fa-building"></i> '+item.companyName+'</p>'+
						'<p style="line-height:1;" class="text-muted font-13"><i class="fa fa-envelope"></i> '+item.email+'</p>'+
						'<p style="line-height:1;" class="text-muted font-13"><i class="fa fa-phone"></i> '+item.phoneNumber+'</p>'+
						'<p style="line-height:1;" class="text-muted font-13"><i class="fa fa-calendar-check-o"></i>: '+item.nextFollowup+'</p>'+
						'<p style="line-height:1;" class="text-muted font-13"><i class="fa fa-users"></i> '+item.meetingType+'</p>'+
						'<p style="line-height:1;" class="text-muted font-13"><i class="fa fa-angle-double-right"></i> '+item.comments+'</p>'+
						
						'<div class="text-right">'+
						'<a href="#custom-modal-update" '+colorclass+
						'data-animation="fadein" data-plugin="custommodal" data-overlaySpeed="200" onclick="prospectEditView('+item.prospectId+','+item.fkCompanyId+')" data-overlayColor="#36404a">Update</a>'+
						'<a href="#custom-modal-history" class="btn btn-xs btn-inverse waves-effect waves-light m-l-10" data-animation="fadein" onclick="prospectHistoryView('+ item.prospectId+');" data-plugin="custommodal" data-overlaySpeed="200" data-overlayColor="#36404a">History</a>'+
						'</div></div></div></div></p>';
					
					$('#prospects').append(eachrow);
				});
				
			}else {
				$('#prospects').html("No prospects found");
				
			}

		},
		error : function(req, status, msg) {
			alert("in error" + req + status + msg);
		}
	});
	
}
//myfunnal edit view for update
function prospectEditView(prospectId,fkCompanyId){
	//alert(prospectId);
	var data="";
	var appBasePath = getAppBasePath();
	$.ajax({
		type : "GET",
		url : appBasePath + "/prospect/getProspectDetails?prospectId="+prospectId,
		success : function(data) {
			// alert("data==="+data.prospectName);
			if (data!="") 
			{
				// alert(data.firstName);
				
				$('#updateprospectfName').val(data.firstName);
				$('#updateprospectlName').val(data.lastName);
				$('#updatephnumber').val(data.phoneNumber);
				$('#updatealternumber').val(data.alternateNumber);
				$('#updatedesignation').val(data.designation);
				$('#updateinformation').val(data.additionalInfo);
				$('#updateaddlane1').val(data.address1);
				$('#updateaddlane2').val(data.address2);
				$('#email').val(data.email);
				$("#email").prop("readonly", true);
				$('#companyName').val(data.companyName);
				$("#companyName").prop("readonly", true);
				/*$('#stageName').val(data.fkStageId);
				$('#meetingtypeName').val(data.fkmeetingId);
				$('#datetimepicker').val(data.contactedOn);
				$('#datetimepicker1').val(data.nextFollowup);
				$('#comments').val(data.comments);*/
				$('#stageName').val("0");
				$('#meetingtypeName').val("0");
				$('#datetimepicker').val("");
				$('#datetimepicker1').val("");
				$('#comments').val('');
				
				// alert("firstname"+firstname)
			} 
			else
			{
				// alert("in else"+data);
				$('#updateprospectfName').val("");
				$('#updateprospectlName').val("");
				$('#updatephnumber').val("");
				$('#updatealternumber').val("");
				$('#updatedesignation').val("");
				$('#updateinformation').val("");
				$('#updateaddlane1').val("");
				$('#updateaddlane2').val("");
				$('#email').val("");
				$('#companyName').val("");
				$('#stageName').val("0");
				$('#meetingtypeName').val("0");
				$('#datetimepicker').val("");
				$('#datetimepicker1').val("");
				$('#comments').val('');
			}
			
		},
		error : function(req, status, msg) {
			alert("error"+msg)
		}
	});
	Custombox.open({
		target : "#custom-modal-update",
		effect : "fadein"
	});
	$("button#updatebtn").attr('onclick','updateProspect('+prospectId+','+fkCompanyId+')');
}
function meetingTypesList()
{
	var appBasePath = getAppBasePath();
	$.ajax({
		type : "GET",
		dataType : "json",
		url : appBasePath + "/prospect/meetingTypesList",
		success : function(data) {
			// alert("list of roles: " + data);
			$('#meetingtypeName').html("");
			var meetingOption="";
			meetingOption ="<option value=0>Select Type</option>";
			$('#meetingtypeName').append(meetingOption);
			for (var i = 0; i < data.length; i++) {
				var meetingOption = "<option value=" +data[i].meetingTypeId+">"
						+ data[i].meetingType +"</option>";
				//$('#meetingtypeId').append(meetingOption);
				$('#meetingtypeName').append(meetingOption);
				$('#meetingtypeName').val("0");
			}
			return true;
		},
		error : function(req, status, msg) {
			alert("in error")
		}
	});
}

function stageList()
{
	var appBasePath = getAppBasePath();
	$.ajax({
		type : "GET",
		dataType : "json",
		url : appBasePath + "/prospect/stageList",
		success : function(data) {
			$('#stageName').html("");
			//var stageOption="";
			var stageOption="<option value=\"0\">Select Stage</option>";
			//stageOption ="<option value=0>Select Stage</option>";
			$('#stageName').append(stageOption);
			for (var i = 0; i < data.length; i++) {
				 stageOption = "<option value=" +data[i].stageId+">"
						+ data[i].stageName +"</option>";
				//$('#stageId').append(customerOption);
				$('#stageName').append(stageOption);
				$('#stageName').val("0");
			}
			return true;
		},
		error : function(req, status, msg) {
			alert("in error")
		}
	});
}
function prospectDeleteView(prospectId) {
	//logoutcheck();
	var appBasePath = getAppBasePath();
	//checkDeleteResource(employeeId);
	Custombox.open({
		target : "#custom-modal",
		effect : "fadein"
	});
	$("button#confirm").attr('onclick','deleteProspect('+prospectId+')');
}
function deleteProspect(prospectId)
{
	var appBasePath = getAppBasePath();
	var employeeId;
	$.ajax({
		type : "POST",
		dataType : "json",
		url : appBasePath + "/prospect/deleteProspect?prospectId="+prospectId,
		success : function(data) {
			alert(data.message);
			Custombox.close();
		},
		error : function(req, status, msg) {
			alert("in error" + req + status + msg);
		}
	});
}
function addCompany(){
	var companyName=$("#companyName1").val();
	var location=$("#location").val();
	$('#companyerrMessage').css('color', 'red');
	if(companyName.trim().length==0){
		$('#companyerrMessage').html(VALIDATION_CREATE_COMPANY_EMPTY_ERROR);
		$('#companyName1').focus();
		setTimeout(function() {
			$("#companyerrMessage").html("");
		}, 5000);
		return false;
	}
	if(location.trim().length==0){
		$('#companyerrMessage').html(VALIDATION_CREATE_COMPANY_LOCATION_EMPTY_ERROR);
		$('#location').focus();
		setTimeout(function() {
			$("#companyerrMessage").html("");
		}, 5000);
		return false;
	}
	var appBasePath = getAppBasePath();
	var params = {
			companyName:companyName,
			location:location
	};
	var queryParam = jQuery.param(params);
	$.ajax({
		type : "POST",
		dataType : "json",
		url : appBasePath + "/prospect/addCompany",
		data: queryParam,
		success : function(data) {
			if (data.statusCode == 1) {
				$('#companyerrMessage').css('color', 'green');
				$('#companyerrMessage').html(data.message);
			} else {
				$('#companyerrMessage').css('color', 'red');
				$('#companyerrMessage').html(data.message);
			}
			setTimeout(function() {
				$('#companyerrMessage').html("");
				$('#companyName1').val("");
				$('#location').val("");
				prospectsList();
				customersList();
			}, 3000);
			
			//alert(data.statusCode);
			
		},
		error : function(req, status, msg) {
			alert("in error" + req + status + msg);
		}
	});
	
}
function customersList()
{	
	
	//logoutcheck();
	var appBasePath = getAppBasePath();
	$.ajax({
		type : "GET",
		dataType : "json",
		url : appBasePath + "/prospect/companiesList",
		success : function(data) {
			$('#createprospectcustomer').html("");
			var option="";
			option ="<option value=0>Select</option>";
			$('#createprospectcustomer').append(option);
			 //alert("list of roles: " + data);
			for (var i = 0; i < data.length; i++) {
				option= "<option value=" +data[i].companyId+">"
						+ data[i].companyName +"-"+ data[i].companyLocation +"</option>";
				$('#createprospectcustomer').append(option);
				//$('#customer_id').append(customerOption);
			}
			
			return true;
		},
		error : function(req, status, msg) {
			alert("in error company");
		}
	});
}

//my funnel closed prospect list
function myfunnelClosedProspectsList(){
	var appBasePath = getAppBasePath();
	var userId=$('#propsectUser').val();
	//var status=1;
	$.ajax({
		type : "GET",
		dataType : "json",
		url : appBasePath + "/prospect/closedProspectList?userTypeId="+userId,
		success : function(data) {
			//alert(data);
			if (data.length > 0) {
				$('#closed-prospects').html("");
				//var createdon="";
				$.each(data,function(index, item) {
					//createdon=item.createdOn.split(" ");
					var eachrow = '<p><div class="col-lg-4"><div class="widget-profile-one">'+
						'<div class="card-box m-b-0 b-0 bg-inverse p-lg text-left"> '+
						'<h4 class="text-white m-b-5 m-t-5 font-600" > <i class="fa fa-user"></i> '+item.prospectName+'</h4></div>'+
						'<div class="card-box">'+
						'<p class="text-muted font-13 m-t-5"><i class="fa fa-building"></i> '+item.companyName+'</p>'+
						'<p style="line-height:1;" class="text-muted font-13"><i class="fa fa-envelope"></i> '+item.email+'</p>'+
						'<p style="line-height:1;" class="text-muted font-13"><i class="fa fa-phone"></i> '+item.phoneNumber+'</p>'+
						'<a href="#custom-modal-history" class="btn btn-xs btn-inverse waves-effect waves-light m-l-10" data-animation="fadein" onclick="prospectHistoryView('+ item.prospectId+');" data-plugin="custommodal" data-overlaySpeed="200" data-overlayColor="#36404a">History</a>'+
						'<div class="text-right"></div></div></div></div></p>';
					$('#closed-prospects').append(eachrow);
				});
				
			}else {
				$('#closed-prospects').html("No  closed prospects found");
				
			}

		},
		error : function(req, status, msg) {
			alert("in error" + req + status + msg);
		}
	});
	
}

//history view
function prospectHistoryView(prospectId)
{
	var appBasePath = getAppBasePath();
	$.ajax({
		type : "GET",
		url : appBasePath + "/prospect/trackProspect?prospectId="+prospectId,
		success : function(data) {
			if (data.length>0) 
			{
				$('#history').html(" ");
				var count=data.length;
				$.each(data,function(index, item) {
					if(index==0){
					var basicInfo='<div class="col-md-12 text-left"><h4 class="text-success text-uppercase font-bold">Prospect Info</h4></div>'
                     +'<div class="col-md-12 text-left">'
                     +' <p>Prospect Name: <span class="text-primary">'+item.prospectName+'</span></p>'
                     +'<p>Prospect Email: <span class="text-primary">'+item.email+'</span></p>'
                     +' <p>Prospect Company: <span class="text-primary">'+item.companyName+'</span></p>'
                     +' <p>Prospect Phone Number: <span class="text-primary">'+item.phoneNumber+'</span></p>'
                     +'</div>'
                     $('#history').append(basicInfo);
					}
					var eachrow = '<div class="col-md-12 text-left"><h4 class="text-success text-uppercase font-bold">Meeting History #'+count+'</h4>'
	                		+'<p>Stage: <span class="text-primary">'+item.stage+'</span></p>'
	                		+'<p>Meeting Type: <span class="text-primary">'+item.meetingType+'</span></p>'
	                		+'<p>Contacted On: <span class="text-primary">'+item.contactedOn+'</span></p>'
	                		+'<p>Next Followup On: <span class="text-primary">'+item.nextFollowup+'</span></p>'
	                		+'<p>Comments: <span class="text-primary">'+item.comments+'</span></p>'
	                		+ '</div>';
					count=count-1;
                    $('#history').append(eachrow);
			  });
		  }else{
			  $('#history').html("No History Found");
		  }
			
		},
		error : function(req, status, msg) {
			alert("error"+msg)
		}
	});
	Custombox.open({
		target : "#custom-modal-history",
		effect : "fadein"
	});

}
function usersListForProspects() {
	// alert("hello");
	//logoutcheck();
	var appBasePath = getAppBasePath();
	$.ajax({
		type : "GET",
		dataType : "json",
		url : appBasePath + "/folloupreports/getUsersListForProspets",
		success : function(data) {
			//alert(data)
			$('#propsectUser').html("");
			var reportOption="<option value=\"-1\">Self</option><option value=\"-2\">All</option>";
			for (var i = 0; i < data.length; i++) {
				reportOption = reportOption+"<option value=" + data[i].userId + ">"
						+ data[i].firstName + '-' + data[i].email
						+ "</option>";
			}
			$('#propsectUser').html(reportOption);
			$("#propsectUser").val("-1");
		},
		error : function(req, status, msg) {
			alert("in error")
		}
	});

}