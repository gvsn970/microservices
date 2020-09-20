function createRequirement() {
	//logoutcheck();
	var appBasePath = getAppBasePath();
	var requirement_name = $("#requirement_name").val();
	var requirement_job_desc = $("#requirement_job_desc").val();
	var requirement_hm_name = $("#requirement_hm_name").val();
	var requirement_hm_email = $("#requirement_hm_email").val();
	var requirement_hm_contact = $("#requirement_hm_contact").val();
	var requirement_pm_name = $("#requirement_pm_name").val();
	var requirement_pm_email = $("#requirement_pm_email").val();
	var requirement_pm_contact = $("#requirement_pm_contact").val();
	var requirement_duration = $("#requirement_duration").val();
	var requirement_experience_level = $("#requirement_experience_level").val();
	var requirement_location = $("#requirement_location").val();
	var requirement_skill_set = $("#requirement_skill_set").val();
	var requirement_rate_card = $("#requirement_rate_card").val();
	var requirement_customer_id = $("#requirement_customer_id").val();
	
	var color = 'red';
	$('#requirement_name_error').css('color', color);
	$('#requirement_job_desc_error').css('color', color);
	$('#requirement_hm_name_error').css('color', color);
	$('#requirement_hm_email_error').css('color', color);
	$('#requirement_hm_contact_error').css('color', color);
	$('#requirement_pm_name_error').css('color', color);
	$('#requirement_pm_email_error').css('color', color);
	$('#requirement_pm_contact_error').css('color', color);
	$('#requirement_duration_error').css('color', color);
	$('#requirement_experience_level_error').css('color', color);
	$('#requirement_location_error').css('color', color);
	$('#requirement_skill_set_error').css('color', color);
	$('#requirement_rate_card_error').css('color', color);
	$('#requirement_customer_id_error').css('color', color);
	
	var params = {
			requirementName : requirement_name ,
			jd : requirement_job_desc ,
			hmName : requirement_hm_name ,
			hmEmail : requirement_hm_email ,
			hmContact : requirement_hm_contact ,
			pmName : requirement_pm_name ,
			pmEmail : requirement_pm_email ,
			pmContact : requirement_pm_contact ,
			duration : requirement_duration ,
			experienceLevel : requirement_experience_level ,
			location : requirement_location ,
			skillSet : requirement_skill_set ,
			rateCard : requirement_rate_card ,
			createdBy : 1,
			fkCustomerId : requirement_customer_id
	};
	var queryParam = jQuery.param( params );
	alert(queryParam);
	$.ajax({
		type : "GET",
		dataType : "json",
		url : appBasePath + "/requirements/saverequirements" ,
		data : queryParam,
		success : function(data) {
			if (data.statusCode == 1) {
				$('#createusermsg').html(data.message)
				$('#createusermsg').css('color', 'green');
			} else {
				$('#createusermsg').html(data.message)
				$('#createusermsg').css('color', color);
			}
			Custombox.close();
			getRequirementsList();
			alert(data.message);

		},
		error : function(xhr, status, error) {
			alert("in error" + xhr +">>>"+status+">>>"+error);
		}
	});

}

function getRequirementsList() {
	//logoutcheck();
	var appBasePath = getAppBasePath();
	$.ajax({
				type : "Get",
				dataType : "json",
				url : appBasePath + "/requirements/getrequirements",
				success : function(data) {
					//alert(data);
					if (data.length > 0) {
						$('#requirements_table').html("");
						var count = 0;
						$.each(data,function(index, item) {
							var eachrow = '<tr class="gradeX">'
							+ "<td>"+ item.requirementName+ "</td>"
							+ "<td>"+ item.jd+ "</td>"
							+ "<td>"+ item.hmName+ "</td>"
							+ "<td>"+ item.pmName+ "</td>"
							+ "<td>"+ item.duration+ "</td>"
							+ "<td>"+ item.fkCustomerId+ "</td>"
							+ "<td class=\"actions\"><a href=\"#\" class=\"on-default text-info m-r-10\" onclick=\"requirementEditView('"+ item.requirementId+ "','"+ item.requirementName
							+ "','"+ item.jd+ "','"+ item.hmName +"','"+ item.hmEmail+"','"+ item.hmContact+ "','"+ item.pmName
							+ "','"+ item.pmEmail+ "','"+ item.pmContact +"','"+ item.duration+"','"+ item.experienceLevel
							+ "','"+ item.location+ "','"+ item.skillSet +"','"+ item.rateCard+"','"+ item.createdBy
							+ "','"+item.fkCustomerId+"');\" data-animation=\"fadein\" data-plugin=\"custommodal\" data-overlaySpeed=\"200\" data-overlayColor=\"#36404a\"><i class=\"fa fa-pencil\"></i></a><a href=\"#\" class=\"on-danger text-danger\" onclick=\"requirementDeleteView('"+ item.requirementId+ "');\" data-animation=\"fadein\" data-plugin=\"custommodal\" data-overlaySpeed=\"200\" data-overlayColor=\"#36404a\"><i class=\"fa fa-trash-o\"></i></a></td>"
						    + "</tr>"
							$('#requirements_table').append(eachrow);
						});
					} else {
						var eachrow = "<tr>"
								+ "<td colspan='7'>No Requirements were found</td>"
								+ "</tr>";
						$('#requirements_table').html("");
						$('#requirements_table').html(eachrow);
					}

				},
				error : function(req, status, msg) {
					alert("in error")
				}
			});

}

function updateRequirement(requirementId) {
	//logoutcheck();
	var appBasePath = getAppBasePath();
	
	var requirement_name = $("#requirement_name_u").val();
	var requirement_job_desc = $("#requirement_job_desc_u").val();
	var requirement_hm_name = $("#requirement_hm_name_u").val();
	var requirement_hm_email = $("#requirement_hm_email_u").val();
	var requirement_hm_contact = $("#requirement_hm_contact_u").val();
	var requirement_pm_name = $("#requirement_pm_name_u").val();
	var requirement_pm_email = $("#requirement_pm_email_u").val();
	var requirement_pm_contact = $("#requirement_pm_contact_u").val();
	var requirement_duration = $("#requirement_duration_u").val();
	var requirement_experience_level = $("#requirement_experience_level_u").val();
	var requirement_location = $("#requirement_location_u").val();
	var requirement_skill_set = $("#requirement_skill_set_u").val();
	var requirement_rate_card = $("#requirement_rate_card_u").val();
	var requirement_customer_id = $("#requirement_customer_id_u").val();
	
	var color = 'red';
	$('#requirement_name_error').css('color', color);
	$('#requirement_job_desc_error').css('color', color);
	$('#requirement_hm_name_error').css('color', color);
	$('#requirement_hm_email_error').css('color', color);
	$('#requirement_hm_contact_error').css('color', color);
	$('#requirement_pm_name_error').css('color', color);
	$('#requirement_pm_email_error').css('color', color);
	$('#requirement_pm_contact_error').css('color', color);
	$('#requirement_duration_error').css('color', color);
	$('#requirement_experience_level_error').css('color', color);
	$('#requirement_location_error').css('color', color);
	$('#requirement_skill_set_error').css('color', color);
	$('#requirement_rate_card_error').css('color', color);
	$('#requirement_customer_id_error').css('color', color);
	
	var params = {
			requirementId : requirementId,
			requirementName : requirement_name ,
			jd : requirement_job_desc ,
			hmName : requirement_hm_name ,
			hmEmail : requirement_hm_email ,
			hmContact : requirement_hm_contact ,
			pmName : requirement_pm_name ,
			pmEmail : requirement_pm_email ,
			pmContact : requirement_pm_contact ,
			duration : requirement_duration ,
			experienceLevel : requirement_experience_level ,
			location : requirement_location ,
			skillSet : requirement_skill_set ,
			rateCard : requirement_rate_card ,
			fkCustomerId : requirement_customer_id
	};
	var queryParam = jQuery.param( params );
	alert(queryParam);
	
	$.ajax({
		type : "GET",
		dataType : "json",
		url : appBasePath + "/requirements/updaterequirements" ,
		data : queryParam,
		success : function(data) {
			alert(data.message);
			Custombox.close();
			getRequirementsList();
			$('#bvcn').trigger("reset");
		},
		error : function(req, status, msg) {
			alert("in error" + req + status + msg);
		}
	});

}



function requirementEditView(requirementId, requirement_name, requirement_job_desc, requirement_hm_name, requirement_hm_email, requirement_hm_contact, 
		requirement_pm_name, requirement_pm_email, requirement_pm_contact, requirement_duration, requirement_experience_level, requirement_location,
		requirement_skill_set, requirement_rate_card, created_by, requirement_customer_id) {
	//logoutcheck();
	
	$("#requirement_name_u").val(requirement_name);
	$("#requirement_job_desc_u").val(requirement_job_desc);
	$("#requirement_hm_name_u").val(requirement_hm_name);
	$("#requirement_hm_email_u").val(requirement_hm_email);
	$("#requirement_hm_contact_u").val(requirement_hm_contact);
	$("#requirement_pm_name_u").val(requirement_pm_name);
	$("#requirement_pm_email_u").val(requirement_pm_email);
	$("#requirement_pm_contact_u").val(requirement_pm_contact);
	$("#requirement_duration_u").val(requirement_duration);
	$("#requirement_experience_level_u").val(requirement_experience_level);
	$("#requirement_location_u").val(requirement_location);
	$("#requirement_skill_set_u").val(requirement_skill_set);
	$("#requirement_rate_card_u").val(requirement_rate_card);
	//$("#requirement_customer_id_u").val(requirement_customer_id);
	
	Custombox.open({
		target : "#custom-modal-edit",
		effect : "fadein"
	});
	setTimeout(function(){
		$("#requirement_customer_id_u").val(requirement_customer_id);
		$('.selectpicker').selectpicker('refresh');
	},500);
	$("button#save").attr('onclick','updateRequirement('+requirementId+')');
}

function requirementDeleteView(requirementId) {
	//logoutcheck();
	Custombox.open({
		target : "#custom-modal",
		effect : "fadein"
	});
	$("button#confirm").attr('onclick','deleteRequirement('+requirementId+')');
}

function deleteRequirement(requirementId){
	var appBasePath = getAppBasePath();
	$.ajax({
		type : "GET",
		dataType : "json",
		url : appBasePath + "/requirements/deleterequirements?requirementId=" + requirementId,
		success : function(data) {
			Custombox.close();
			alert(data.message);
			getRequirementsList();
			$('#bvcn').trigger("reset");
		},
		error : function(req, status, msg) {
			alert("in error" + req);
		}
	});
}

function customersDropDownLoader() {
	// alert("hello");
	//logoutcheck();
	var appBasePath = getAppBasePath();
	$.ajax({
		type : "Get",
		dataType : "json",
		url : appBasePath + "/customer/getCustomersList",
		success : function(data) {
			var customerOption="";
			for (var i = 0; i < data.length; i++) {
				customerOption = customerOption+"<option value=\"" + data[i].customerId + "\">"+ data[i].customerName + "</option>";
			}
			$('#requirement_customer_id').html("");
			$('#requirement_customer_id').html(customerOption);
			$('#requirement_customer_id_u').html("");
			$('#requirement_customer_id_u').html(customerOption);
			$('.selectpicker').selectpicker('refresh');
		},
		error : function(req, status, msg) {
			alert("in error")
		}
	});
}