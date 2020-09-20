function getCompanyAccountsList() {
	//logoutcheck();
	var appBasePath = getAppBasePath();
	$.ajax({
		type : "Get",
		dataType : "json",
		url : appBasePath + "/account/getAccountsList",
		success : function(data) {
			//alert(data);
		if (data.length > 0) {
			$('#company_accounts_table').html("");
			var count = 0;
			$.each(data,function(index, item) {
				var eachrow = '<tr class="gradeX">'
				+ "<td>"+ item.customerName+ "</td>"
				+ "<td>"+ item.location+ "</td>"
				+ "<td>"+ item.contactNumber+ "</td>"
				+ "<td>"+ item.contactPerson+ "</td>"
				+ "<td class=\"actions\"><a href=\"#\" class=\"on-default text-info m-r-10\" onclick=\"companyAccountEditView('"+ item.customerId+ "','"+ item.customerName
				+ "','"+ item.location+ "','"+ item.contactNumber+ "','"+ item.contactPerson
				+"','"+ item.billingAddressLane1 +"','"+ item.billingAddressLane2 +"','"+ item.billingAddressPinCode
				+"','"  + item.billingAddressState +"','"+ item.shippingAddressLane1 +"','"+ item.shippingAddressLane2
				+"','" + item.shippingAddressState +"','"+ item.shippingAddressPinCode +"','"+ item.vendorAddressLane1
				+"','" + item.vendorAddressLane2 +"','"+ item.vendorAddressState +"','"+ item.vendorAddressPinCode
				+"','" +item.panNumber+"','"+ item.gstIn +"','"+ item.state +"','"+ item.stateCode+"');\""
				+ "data-animation=\"fadein\" data-plugin=\"custommodal\" data-overlaySpeed=\"200\" data-overlayColor=\"#36404a\">" 
				+ "<i class=\"fa fa-pencil\"></i></a><a href=\"#\" class=\"on-danger text-danger m-r-10\" "
				+ "onclick=\"companyAccountDeleteView('"+ item.customerId+ "');\" data-animation=\"fadein\" data-plugin=\"custommodal\" " 
				+ "data-overlaySpeed=\"200\" data-overlayColor=\"#36404a\"><i class=\"fa fa-trash-o\"></i></a>" 
				+"<a href=\"#\" class=\"on-default text-success\" onclick=\"accountPreView('"+ item.customerId+ "','"+ item.customerName
				+ "','"+ item.location+ "','"+ item.contactNumber+ "','"+ item.contactPerson
				+ "','"+ item.billingAddressLane1 +"','"+ item.billingAddressLane2 +"','"+ item.billingAddressPinCode
				+"','" + item.billingAddressState +"','"+ item.shippingAddressLane1 +"','"+ item.shippingAddressLane2
				+"','" + item.shippingAddressState +"','"+ item.shippingAddressPinCode +"','"+ item.vendorAddressLane1
				+"','" + item.vendorAddressLane2 +"','"+ item.vendorAddressState +"','"+ item.vendorAddressPinCode
				+"','" +item.panNumber+"','"+ item.gstIn +"','"+ item.state +"','"+ item.stateCode+"');\"data-animation=\"fadein\" data-plugin=\"custommodal\" data-overlaySpeed=\"200\" data-overlayColor=\"#36404a\"><i class=\"fa fa-eye-slash\"></i></a>"
				+"</td>"
			    + "</tr>";
				$('#company_accounts_table').append(eachrow);
			});
			$('#datatable-responsive').DataTable();
		} else {
			$('#datatable-responsive').dataTable().fnDestroy();
			$('#company_accounts_table').html("");
			$('#datatable-responsive').DataTable({
				"language": {
					"emptyTable": "Company Accounts not exist for listout"
				}
			});
		}
		
		},
		error : function(req, status, msg) {
			alert("in error")
		}
	});
}

function getAgreementFilesList(companyAccountId) {
	//logoutcheck();
	var appBasePath = getAppBasePath();
	$.ajax({
		type : "Get",
		dataType : "json",
		url : appBasePath + "/account/getAgreementsList/"+companyAccountId,
		success : function(data) {
			//alert(data);
		if (data.length > 0) {
			$('#agreement_files_list').html("");
			var count = 0;
			$.each(data,function(index, item) {
               // <td class="text-left">File Name.docs</td>
               // <td class="text-left"><a href="" class="on-danger text-danger"><i class="fa fa-trash-o"></i></a></td>
             // </tr>
				var eachrow = '<tr class="gradeX">'
				+ "<td>"+ item.fileName+ "</td>"
				+ "<td class=\"actions\"><a href=\"#\" class=\"on-danger text-danger\" "
				+ "onclick=\"companyFileDelete('"+ item.customerUploadId+ "','"+item.customerId+"');\" data-animation=\"fadein\" data-plugin=\"custommodal\" " 
				+ "data-overlaySpeed=\"200\" data-overlayColor=\"#36404a\"><i class=\"fa fa-trash-o\"></i></a></td>"
			    + "</tr>";
				$('#agreement_files_list').append(eachrow);
			});
		} else {
			var eachrow = "<tr>"
					+ "<td colspan='2'>No Company Agreements were found</td>"
					+ "</tr>";
			$('#agreement_files_list').html("");
			$('#agreement_files_list').html(eachrow);
		}
		
		},
		error : function(req, status, msg) {
			alert("in error")
		}
	});
}
$("#create_company_account").click(function (event) {

    //stop submit the form, we will post it manually.
    event.preventDefault();

    createCompanyAccount();

});

function createCompanyAccount() {
	//logoutcheck();
	/*var l = $('#create_company_account').ladda();
    l.ladda('start');*/
	var appBasePath = getAppBasePath();
	var flag=false;
	var company_account_name = $("#company_account_name").val();
	var company_account_location = $("#company_account_location").val();
	var company_account_contact_no = $("#company_account_contact_no").val();
	var company_account_contact_person = $("#company_account_contact_person").val();
	var company_account_billingAddressLane1 = $("#company_account_billingAddressLane1").val();
	var company_account_billingAddressLane2 = $("#company_account_billingAddressLane2").val();
	var company_account_billingAddressPinCode = $("#company_account_billingAddressPinCode").val();
	var company_account_billingAddressState = $("#company_account_billingAddressState").val();
	var company_account_shippingAddressLane1 = $("#company_account_shippingAddressLane1").val();
	var company_account_shippingAddressLane2 = $("#company_account_shippingAddressLane2").val();
	var company_account_shippingAddressPinCode = $("#company_account_shippingAddressPinCode").val();
	var company_account_shippingAddressState = $("#company_account_shippingAddressState").val();
	var company_account_vendorAddressLane1 = $("#company_account_vendorAddressLane1").val();
	var company_account_vendorAddressLane2 = $("#company_account_vendorAddressLane2").val();
	var company_account_vendorAddressPinCode = $("#company_account_vendorAddressPinCode").val();
	var company_account_vendorAddressState = $("#company_account_vendorAddressState").val();
	var company_account_pan = $("#company_account_pan").val();
	var company_account_gstin = $("#company_account_gstin").val();
	var company_account_state = $("#company_account_state").val();
	var company_account_state_code = $("#company_account_state_code").val();
	var file=$("#agreementFile").val();
	var form = $('form#form1')[0];
	var data = new FormData(form);
	var color = 'red';
	$('#createCompanyStatusMsg').css('color', color);
	
	//Account name validation
	if (company_account_name.trim().length == 0) {
		$('#createCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_NAME_EMPTY_ERROR);
		$('#company_account_name').focus();
		return false;
	} else if (company_account_name.trim().length < 3) {
		// alert("in length" + firstname.trim().length)
		$('#createCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_NAME_LENGTH_ERROR);
		$('#company_account_name').focus();
		return false;
	} else if (!isNaN(company_account_name)) {
		$('#createCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_NAME_SPECIALS_ERROR);
		$('#company_account_name').focus();
		return false;
	} else {
		$('#createCompanyStatusMsg').html("");
	}
	if (company_account_name.trim().length > 25) {
		$("#createCompanyStatusMsg").html(VALIDATION_CREATE_ACCOUNT_NAME_MAX_LENGTH_ERROR);
		$("#company_account_name").focus();
		setTimeout(function() {
			$("#createCompanyStatusMsg").html("");
		}, 5000);
		return false;
	} else {
		$('#createCompanyStatusMsg').html("");
	}
	//Account Location validation
	if (company_account_location.trim().length == 0) {
		$('#createCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_LOCATION_EMPTY_ERROR);
		$('#company_account_location').focus();
		return false;
	} else if (company_account_location.trim().length < 3) {
		// alert("in length" + firstname.trim().length)
		$('#createCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_LOCATION_MIN_LENGTH_ERROR);
		$('#company_account_location').focus();
		return false;
	} else if (!isNaN(company_account_location)) {
		$('#createCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_LOCATION_SPECIALS_ERROR);
		$('#company_account_location').focus();
		return false;
	} else {
		$('#createCompanyStatusMsg').html("");
	}
	if (company_account_name.trim().length > 25) {
		$("#createCompanyStatusMsg").html(VALIDATION_CREATE_ACCOUNT_LOCATION_MAX_LENGTH_ERROR);
		$("#company_account_name").focus();
		setTimeout(function() {
			$("#createCompanyStatusMsg").html("");
		}, 5000);
		return false;
	} else {
		$('#createCompanyStatusMsg').html("");
	}
	//Account contact number validation
	if (company_account_contact_no.trim().length == 0) {
		$('#createCompanyStatusMsg').html(VALIDATION_CREATE_CUSTOMER_CONTACT_NUMBER_EMPTY_ERROR);
		$('#company_account_contact_no').focus();
		return false;
	} else if (company_account_contact_no.trim().length < 8) {
		$('#createCompanyStatusMsg').html(VALIDATION_CONTACT_LENGTH_ERROR);
		$('#company_account_contact_no').focus();
		return false;
	} else if (isNaN(company_account_contact_no)) {
		$('#createCompanyStatusMsg').html(VALIDATION_CONTACT_SPECIALS_ERROR);
		$('#company_account_contact_no').focus();
		return false;
	} else {
		$('#createCompanyStatusMsg').html("");
	}
	//contact person validation
	if (company_account_contact_person.trim().length == 0) {
		$('#createCompanyStatusMsg').html(VALIDATION_CREATE_CUSTOMER_CONTACT_NAME_EMPTY_ERROR);
		$('#company_account_contact_person').focus();
		return false;
	} else if (company_account_contact_person.trim().length < 3) {
		// alert("in length" + firstname.trim().length)
		$('#createCompanyStatusMsg').html(VALIDATION_CREATE_CUSTOMER_CONTACT_NAME_LENGTH_ERROR);
		$('#company_account_contact_person').focus();
		return false;
	} else if (!isNaN(company_account_contact_person)) {
		$('#createCompanyStatusMsg').html(VALIDATION_CREATE_CUSTOMER_CONTACT_NAME_SPECIALS_ERROR);
		$('#company_account_contact_person').focus();
		return false;
	} else {
		$('#createCompanyStatusMsg').html("");
	}
	if (company_account_contact_person.trim().length > 25) {
		$("#createCompanyStatusMsg").html(VALIDATION_CREATE_CUSTOMER_CONTACT_NAME_MAX_LENGTH_ERROR);
		$("#company_account_contact_person").focus();
		setTimeout(function() {
			$("#createCompanyStatusMsg").html("");
		}, 5000);
		return false;
	} else {
		$('#createCompanyStatusMsg').html("");
	}
	if(file.trim().length==0){
		$('#createCompanyStatusMsg').html("Please upload file");
		return false;	
	}
	else
	{
		$('#createCompanyStatusMsg').html("");
	}
	//Shipping address validation
	if (company_account_shippingAddressLane1.trim().length == 0) {
		$('#createCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_SHIPPING_ADDRESS_LANE1_EMPTY_ERROR);
		$('#company_account_shippingAddressLane1').focus();
		return false;
	}  else {
		$('#createCompanyStatusMsg').html("");
	}
	if (company_account_shippingAddressLane2.trim().length == 0) {
		$('#createCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_SHIPPING_ADDRESS_LANE2_EMPTY_ERROR);
		$('#company_account_shippingAddressLane2').focus();
		return false;
	}  else {
		$('#createCompanyStatusMsg').html("");
	}
	if (company_account_shippingAddressState.trim().length == 0) {
		$('#createCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_SHIPPING_ADDRESS_STATE_EMPTY_ERROR);
		$('#company_account_shippingAddressState').focus();
		return false;
	}  else {
		$('#createCompanyStatusMsg').html("");
	}
	if (company_account_shippingAddressPinCode.trim().length == 0) {
		$('#createCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_SHIPPING_ADDRESS_PIN_CODE_ERROR);
		$('#company_account_shippingAddressPinCode').focus();
		return false;
	}  else {
		$('#createCompanyStatusMsg').html("");
	}
	//billing Address validation
	if (company_account_billingAddressLane1.trim().length == 0) {
		$('#createCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_BILLING_ADDRESS_LANE1_EMPTY_ERROR);
		$('#company_account_billingAddressLane1').focus();
		return false;
	} else {
		$('#createCompanyStatusMsg').html("");
	}
	if (company_account_billingAddressLane2.trim().length == 0) {
		$('#createCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_BILLING_ADDRESS_LANE2_EMPTY_ERROR);
		$('#company_account_billingAddressLane2').focus();
		return false;
	}  else {
		$('#createCompanyStatusMsg').html("");
	}
	if (company_account_billingAddressState.trim().length == 0) {
		$('#createCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_BILLING_ADDRESS_STATE_EMPTY_ERROR);
		$('#company_account_billingAddressState').focus();
		return false;
	}  else {
		$('#createCompanyStatusMsg').html("");
	}
	if (company_account_billingAddressPinCode.trim().length == 0) {
		$('#createCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_BILLING_ADDRESS_PIN_CODE_ERROR);
		$('#company_account_billingAddressPinCode').focus();
		return false;
	}  else {
		$('#createCompanyStatusMsg').html("");
	}
	//Vendor Address validation
	if (company_account_vendorAddressLane1.trim().length == 0) {
		$('#createCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_VENDOR_ADDRESS_LANE1_EMPTY_ERROR);
		$('#company_account_vendorAddressLane1').focus();
		return false;
	} else {
		$('#createCompanyStatusMsg').html("");
	}
	if (company_account_vendorAddressLane2.trim().length == 0) {
		$('#createCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_VENDOR_ADDRESS_LANE2_EMPTY_ERROR);
		$('#company_account_vendorAddressLane2').focus();
		return false;
	} else {
		$('#createCompanyStatusMsg').html("");
	}
	if (company_account_vendorAddressState.trim().length == 0) {
		$('#createCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_VENDOR_ADDRESS_STATE_EMPTY_ERROR);
		$('#company_account_vendorAddressState').focus();
		return false;
	}  else {
		$('#createCompanyStatusMsg').html("");
	}
	if (company_account_vendorAddressPinCode.trim().length == 0) {
		$('#createCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_VENDOR_ADDRESS_PIN_CODE_ERROR);
		$('#company_account_vendorAddressPinCode').focus();
		return false;
	}  else {
		$('#createCompanyStatusMsg').html("");
	}
	
	if (company_account_pan.length == 0 || company_account_pan==null) {
		$('#createCompanyStatusMsg').html(VALIDATION_TAXDETAILS_PAN_EMPTY_ERROR);
		$('#company_account_pan').focus();
		return false;
	}else if(company_account_pan.length<10){
		$('#createCompanyStatusMsg').html(VALIDATION_TAXDETAILS_PAN_MIN_LENGTH_ERROR);
		$('#company_account_pan').focus();
		return false;
	}else if(company_account_pan.length>20){
		$('#createCompanyStatusMsg').html(VALIDATION_TAXDETAILS_PAN_MAX_LENGTH_ERROR);
		$('#company_account_pan').focus();
		return false;
	}else {
		$('#createCompanyStatusMsg').html("");
	}
	
	if (company_account_gstin.length == 0 || company_account_gstin==null) {
		$('#createCompanyStatusMsg').html(VALIDATION_TAXDETAILS_GST_EMPTY_ERROR);
		$('#company_account_gstin').focus();
		return false;
	}else if(/^[a-zA-Z0-9- ]*$/.test(company_account_gstin) == false) {
	    $('#updatetaxErrorMessage').html(VALIDATION_TAXDETAILS_GST_SPECIAL_ERROR);
		$('#company_account_gstin').focus();
		return false;
	}
	else {
		$('#updatetaxErrorMessage').html("");
	}
	
	if (company_account_state.length == 0 || company_account_state==null) {
		$('#createCompanyStatusMsg').html(VALIDATION_TAXDETAILS_STATE_NAME_EMPTY_ERROR);
		$('#company_account_state').focus();
		return false;
	}else if(company_account_state.length<3){
		$('#createCompanyStatusMsg').html(VALIDATION_TAXDETAILS_STATE_NAME_MIN_LENGTH_ERROR);
		$('#company_account_state').focus();
		return false;
	}else if(company_account_state.length>25){
		$('#createCompanyStatusMsg').html(VALIDATION_TAXDETAILS_STATE_NAME_MAX_LENGTH_ERROR);
		$('#company_account_state').focus();
		return false;
	}else {
		$('#createCompanyStatusMsg').html("");
	}
	
	if (company_account_state_code.length == 0 || company_account_state_code==null) {
		$('#createCompanyStatusMsg').html(VALIDATION_TAXDETAILS_STATE_CODE_EMPTY_ERROR);
		$('#company_account_state_code').focus();
		return false;
	}else if(company_account_state_code.length<2){
		$('#createCompanyStatusMsg').html(VALIDATION_TAXDETAILS_STATE_CODE_MIN_LENGTH_ERROR);
		$('#company_account_state_code').focus();
		return false;
	}else if(company_account_state_code.length>10){
		$('#createCompanyStatusMsg').html(VALIDATION_TAXDETAILS_STATE_CODE_MAX_LENGTH_ERROR);
		$('#company_account_state_code').focus();
		return false;
	}else {
		$('#createCompanyStatusMsg').html("");
	}
	data.append("companyName",company_account_name);
	data.append("location",company_account_location);
	data.append("contactNum",company_account_contact_no);
	data.append("contactPerson",company_account_contact_person);
	data.append("pan",company_account_pan);
	data.append("gstin",company_account_gstin);
	data.append("state",company_account_state);
	data.append("stateCode",company_account_state_code);
	data.append("billingAddressLane1",company_account_billingAddressLane1);
	data.append("billingAddressLane2",company_account_billingAddressLane2);
	data.append("billingAddressPincode",company_account_billingAddressPinCode);
	data.append("billingAddressState",company_account_billingAddressState);
	data.append("vendorAddressLane1",company_account_vendorAddressLane1);
	data.append("vendorAddressLane2",company_account_vendorAddressLane2);
	data.append("vendorAddressPincode",company_account_vendorAddressPinCode);
	data.append("vendorAddressState",company_account_vendorAddressState);
	data.append("shippingAddressLane1",company_account_shippingAddressLane1);
	data.append("shippingAddressLane2",company_account_shippingAddressLane2);
	data.append("shippingAddressPinCode",company_account_shippingAddressPinCode);
	data.append("shippingAddressState",company_account_shippingAddressState);
	console.log(data);
	$.ajax({
		type : "POST",
		dataType : "json",
		enctype: 'multipart/form-data',
		url : appBasePath + "/account/createAccount",
		data:data,
	    processData: false, //prevent jQuery from automatically transforming the data into a query string
	    contentType: false,
	    cache: false,
	    beforeSend : function() {
			$.blockUI({
			message: 'Loading please wait....'
			});
			},
		success : function(data) {
			console.log(data);
			if (data.statusCode == 1) {
				$('#createCompanyStatusMsg').html(data.message)
				$('#createCompanyStatusMsg').css('color', 'green');
				flag=true;
			} else {
				$('#createCompanyStatusMsg').html(data.message)
				$('#createCompanyStatusMsg').css('color', color);
				flag=false;
			}
			$.unblockUI(); 
			setTimeout(function() {
				$("#createCompanyStatusMsg").html("");
				$("#company_account_name").val("");
				$("#company_account_location").val("");
				$("#company_account_contact_no").val("");
				$("#company_account_contact_person").val("");
				$("#company_account_billingAddressLane1").val("");
				$("#company_account_billingAddressLane2").val("");
				$("#company_account_billingAddressPinCode").val("");
				$("#company_account_billingAddressState").val("");
				$("#company_account_shippingAddressLane1").val("");
				$("#company_account_shippingAddressLane2").val("");
				$("#company_account_shippingAddressPinCode").val("");
				$("#company_account_shippingAddressState").val("");
				$("#company_account_vendorAddressLane1").val("");
				$("#company_account_vendorAddressLane2").val("");
				$("#company_account_vendorAddressPinCode").val("");
				$("#company_account_vendorAddressState").val("");
				$("#company_account_pan").val("");
				$("#company_account_gstin").val("");
				$("#company_account_state").val("");
				$("#company_account_state_code").val("");
				$('form#form1')[0].reset();
				//$('input[type=file]').val('');
				//$("#agreementFile").val("");
			}, 3000);
			//Custombox.close();
			getCompanyAccountsList();
			// alert(data.message);
		},
		error : function(req, status, msg) {
			alert("Error While Processing Request" + msg);
			flag=false;
		}
	});
	return flag;
}

function updateCompanyAccount(companyAccountId) {
	var flag=false;
	//logoutcheck();
	/*var l = $('#save').ladda();
    l.ladda('start');*/
	var appBasePath = getAppBasePath();
	var companyAccountId= $("#companyId").val();
	var company_account_name_u = $("#company_account_name_u").val();
	var company_account_location_u = $("#company_account_location_u").val();
	var company_account_contact_no_u = $("#company_account_contact_no_u").val();
	var company_account_contact_person_u = $("#company_account_contact_person_u").val();
	var company_account_billingAddressLane1_u = $("#company_account_billingAddressLane1_u").val();
	var company_account_billingAddressLane2_u = $("#company_account_billingAddressLane2_u").val();
	var company_account_billingAddressPinCode_u = $("#company_account_billingAddressPinCode_u").val();
	var company_account_billingAddressState_u = $("#company_account_billingAddressState_u").val();
	var company_account_shippingAddressLane1_u = $("#company_account_shippingAddressLane1_u").val();
	var company_account_shippingAddressLane2_u = $("#company_account_shippingAddressLane2_u").val();
	var company_account_shippingAddressPinCode_u = $("#company_account_shippingAddressPinCode_u").val();
	var company_account_shippingAddressState_u = $("#company_account_shippingAddressState_u").val();
	var company_account_vendorAddressLane1_u = $("#company_account_vendorAddressLane1_u").val();
	var company_account_vendorAddressLane2_u= $("#company_account_vendorAddressLane2_u").val();
	var company_account_vendorAddressPinCode_u = $("#company_account_vendorAddressPinCode_u").val();
	var company_account_vendorAddressState_u = $("#company_account_vendorAddressState_u").val();
	var company_account_pan_u = $("#company_account_pan_u").val();
	var company_account_gstin_u = $("#company_account_gstin_u").val();
	var company_account_state_u = $("#company_account_state_u").val();
	var company_account_state_code_u = $("#company_account_state_code_u").val();
	var color = 'red';
	$('#updateCompanyStatusMsg').css('color', color);
	
	//Account name validation
	if (company_account_name_u.trim().length == 0) {
		$('#updateCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_NAME_EMPTY_ERROR);
		$('#company_account_name_u').focus();
		return false;
	} else if (company_account_name_u.trim().length < 3) {
		// alert("in length" + firstname.trim().length)
		$('#updateCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_NAME_LENGTH_ERROR);
		$('#company_account_name_u').focus();
		return false;
	} else if (!isNaN(company_account_name_u)) {
		$('#createCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_NAME_SPECIALS_ERROR);
		$('#company_account_name_u').focus();
		return false;
	} else {
		$('#updateCompanyStatusMsg').html("");
	}
	if (company_account_name_u.trim().length > 25) {
		$("#updateCompanyStatusMsg").html(VALIDATION_CREATE_ACCOUNT_NAME_MAX_LENGTH_ERROR);
		$("#company_account_name_u").focus();
		setTimeout(function() {
			$("#updateCompanyStatusMsg").html("");
		}, 5000);
		return false;
	} else {
		$('#updateCompanyStatusMsg').html("");
	}
	//Account Location validation
	if (company_account_location_u.trim().length == 0) {
		$('#updateCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_LOCATION_EMPTY_ERROR);
		$('#company_account_location_u').focus();
		return false;
	} else if (company_account_location_u.trim().length < 3) {
		// alert("in length" + firstname.trim().length)
		$('#updateCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_LOCATION_MIN_LENGTH_ERROR);
		$('#company_account_location_u').focus();
		return false;
	} else if (!isNaN(company_account_location_u)) {
		$('#updateCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_LOCATION_SPECIALS_ERROR);
		$('#company_account_location_u').focus();
		return false;
	} else {
		$('#updateCompanyStatusMsg').html("");
	}
	if (company_account_location_u.trim().length > 25) {
		$("#updateCompanyStatusMsg").html(VALIDATION_CREATE_ACCOUNT_LOCATION_MAX_LENGTH_ERROR);
		$("#company_account_location_u").focus();
		setTimeout(function() {
			$("#updateCompanyStatusMsg").html("");
		}, 5000);
		return false;
	} else {
		$('#updateCompanyStatusMsg').html("");
	}
	//Account contact number validation
	if (company_account_contact_no_u.trim().length == 0) {
		$('#updateCompanyStatusMsg').html(VALIDATION_CREATE_CUSTOMER_CONTACT_NUMBER_EMPTY_ERROR);
		$('#company_account_contact_no_u').focus();
		return false;
	} else if (company_account_contact_no_u.trim().length < 8) {
		$('#updateCompanyStatusMsg').html(VALIDATION_CONTACT_LENGTH_ERROR);
		$('#company_account_contact_no_u').focus();
		return false;
	} else if (isNaN(company_account_contact_no_u)) {
		$('#updateCompanyStatusMsg').html(VALIDATION_CONTACT_SPECIALS_ERROR);
		$('#company_account_contact_no_u').focus();
		return false;
	} else {
		$('#updateCompanyStatusMsg').html("");
	}
	//contact person validation
	if (company_account_contact_person_u.trim().length == 0) {
		$('#updateCompanyStatusMsg').html(VALIDATION_CREATE_CUSTOMER_CONTACT_NAME_EMPTY_ERROR);
		$('#company_account_contact_person_u').focus();
		return false;
	} else if (company_account_contact_person_u.trim().length < 3) {
		// alert("in length" + firstname.trim().length)
		$('#updateCompanyStatusMsg').html(VALIDATION_CREATE_CUSTOMER_CONTACT_NAME_LENGTH_ERROR);
		$('#company_account_contact_person_u').focus();
		return false;
	} else if (!isNaN(company_account_contact_person_u)) {
		$('#updateCompanyStatusMsg').html(VALIDATION_CREATE_CUSTOMER_CONTACT_NAME_SPECIALS_ERROR);
		$('#company_account_contact_person_u').focus();
		return false;
	} else {
		$('#updateCompanyStatusMsg').html("");
	}
	if (company_account_contact_person_u.trim().length > 25) {
		$("#updateCompanyStatusMsg").html(VALIDATION_CREATE_CUSTOMER_CONTACT_NAME_MAX_LENGTH_ERROR);
		$("#company_account_contact_person_u").focus();
		setTimeout(function() {
			$("#updateCompanyStatusMsg").html("");
		}, 5000);
		return false;
	} else {
		$('#updateCompanyStatusMsg').html("");
	}
	//Shipping address validation
	if (company_account_shippingAddressLane1_u.trim().length == 0) {
		$('#updateCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_SHIPPING_ADDRESS_LANE1_EMPTY_ERROR);
		$('#company_account_shippingAddressLane1_u').focus();
		return false;
	}  else {
		$('#updateCompanyStatusMsg').html("");
	}
	if (company_account_shippingAddressLane2_u.trim().length == 0) {
		$('#updateCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_SHIPPING_ADDRESS_LANE2_EMPTY_ERROR);
		$('#company_account_shippingAddressLane2_u').focus();
		return false;
	}  else {
		$('#updateCompanyStatusMsg').html("");
	}
	if (company_account_shippingAddressState_u.trim().length == 0) {
		$('#updateCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_SHIPPING_ADDRESS_STATE_EMPTY_ERROR);
		$('#company_account_shippingAddressState_u').focus();
		return false;
	}  else {
		$('#updateCompanyStatusMsg').html("");
	}
	if (company_account_shippingAddressPinCode_u.trim().length == 0) {
		$('#updateCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_SHIPPING_ADDRESS_PIN_CODE_ERROR);
		$('#company_account_shippingAddressPinCode_u').focus();
		return false;
	}  else {
		$('#updateCompanyStatusMsg').html("");
	}
	//billing Address validation
	if (company_account_billingAddressLane1_u.trim().length == 0) {
		$('#updateCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_BILLING_ADDRESS_LANE1_EMPTY_ERROR);
		$('#company_account_billingAddressLane1_u').focus();
		return false;
	} else {
		$('#updateCompanyStatusMsg').html("");
	}
	if (company_account_billingAddressLane2_u.trim().length == 0) {
		$('#updateCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_BILLING_ADDRESS_LANE2_EMPTY_ERROR);
		$('#company_account_billingAddressLane2_u').focus();
		return false;
	}  else {
		$('#updateCompanyStatusMsg').html("");
	}
	if (company_account_billingAddressState_u.trim().length == 0) {
		$('#updateCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_BILLING_ADDRESS_STATE_EMPTY_ERROR);
		$('#company_account_biullingAddressState_u').focus();
		return false;
	}  else {
		$('#updateCompanyStatusMsg').html("");
	}
	if (company_account_billingAddressPinCode_u.trim().length == 0) {
		$('#updateCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_BILLING_ADDRESS_PIN_CODE_ERROR);
		$('#company_account_billingAddressPinCode_u').focus();
		return false;
	}  else {
		$('#updateCompanyStatusMsg').html("");
	}
	//Vendor Address validation
	if (company_account_vendorAddressLane1_u.trim().length == 0) {
		$('#updateCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_VENDOR_ADDRESS_LANE1_EMPTY_ERROR);
		$('#company_account_vendorAddressLane1_u').focus();
		return false;
	} else {
		$('#updateCompanyStatusMsg').html("");
	}
	if (company_account_vendorAddressLane2_u.trim().length == 0) {
		$('#updateCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_VENDOR_ADDRESS_LANE2_EMPTY_ERROR);
		$('#company_account_vendorAddressLane2_u').focus();
		return false;
	} else {
		$('#updateCompanyStatusMsg').html("");
	}
	if (company_account_vendorAddressState_u.trim().length == 0) {
		$('#updateCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_VENDOR_ADDRESS_STATE_EMPTY_ERROR);
		$('#company_account_vendorAddressState_u').focus();
		return false;
	}  else {
		$('#updateCompanyStatusMsg').html("");
	}
	if (company_account_vendorAddressPinCode_u.trim().length == 0) {
		$('#updateCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_VENDOR_ADDRESS_PIN_CODE_ERROR);
		$('#company_account_vendorAddressPinCode_u').focus();
		return false;
	}  else {
		$('#updateCompanyStatusMsg').html("");
	}	
	if (company_account_pan_u.length == 0 || company_account_pan_u==null) {
		$('#updateCompanyStatusMsg').html(VALIDATION_TAXDETAILS_PAN_EMPTY_ERROR);
		$('#company_account_pan_u').focus();
		return false;
	}else if(company_account_pan_u.length<10){
		$('#updateCompanyStatusMsg').html(VALIDATION_TAXDETAILS_PAN_MIN_LENGTH_ERROR);
		$('#company_account_pan_u').focus();
		return false;
	}else if(company_account_pan_u.length>20){
		$('#updateCompanyStatusMsg').html(VALIDATION_TAXDETAILS_PAN_MAX_LENGTH_ERROR);
		$('#company_account_pan_u').focus();
		return false;
	}else {
		$('#updateCompanyStatusMsg').html("");
	}
	
	if (company_account_gstin_u.length == 0 || company_account_gstin_u==null) {
		$('#updateCompanyStatusMsg').html(VALIDATION_TAXDETAILS_GST_EMPTY_ERROR);
		$('#company_account_gstin_u').focus();
		return false;
	}else if(/^[a-zA-Z0-9- ]*$/.test(company_account_gstin_u) == false) {
	    $('#updateCompanyStatusMsg').html(VALIDATION_TAXDETAILS_GST_SPECIAL_ERROR);
		$('#company_account_gstin_u').focus();
		return false;
	}
	else {
		$('#updateCompanyStatusMsg').html("");
	}
	
	if (company_account_state_u.length == 0 || company_account_state_u==null) {
		$('#updateCompanyStatusMsg').html(VALIDATION_TAXDETAILS_STATE_NAME_EMPTY_ERROR);
		$('#company_account_state_u').focus();
		return false;
	}else if(company_account_state_u.length<3){
		$('#updateCompanyStatusMsg').html(VALIDATION_TAXDETAILS_STATE_NAME_MIN_LENGTH_ERROR);
		$('#company_account_state_u').focus();
		return false;
	}else if(company_account_state_u.length>25){
		$('#updateCompanyStatusMsg').html(VALIDATION_TAXDETAILS_STATE_NAME_MAX_LENGTH_ERROR);
		$('#company_account_state_u').focus();
		return false;
	}else {
		$('#updateCompanyStatusMsg').html("");
	}
	
	if (company_account_state_code_u.length == 0 || company_account_state_code_u==null) {
		$('#updateCompanyStatusMsg').html(VALIDATION_TAXDETAILS_STATE_CODE_EMPTY_ERROR);
		$('#company_account_state_code_u').focus();
		return false;
	}else if(company_account_state_code_u.length<2){
		$('#updateCompanyStatusMsg').html(VALIDATION_TAXDETAILS_STATE_CODE_MIN_LENGTH_ERROR);
		$('#company_account_state_code_u').focus();
		return false;
	}else if(company_account_state_code_u.length>10){
		$('#updateCompanyStatusMsg').html(VALIDATION_TAXDETAILS_STATE_CODE_MAX_LENGTH_ERROR);
		$('#company_account_state_code_u').focus();
		return false;
	}else {
		$('#updateCompanyStatusMsg').html("");
	}
	var form = $('#update-form')[0];
	var data = new FormData(form);
	data.append("customerId",companyAccountId)
	data.append("companyName",company_account_name_u);
	data.append("location",company_account_location_u);
	data.append("contactNum",company_account_contact_no_u);
	data.append("contactPerson",company_account_contact_person_u);
	data.append("pan",company_account_pan_u);
	data.append("gstin",company_account_gstin_u);
	data.append("state",company_account_state_u);
	data.append("stateCode",company_account_state_code_u);
	data.append("billingAddressLane1",company_account_billingAddressLane1_u);
	data.append("billingAddressLane2",company_account_billingAddressLane2_u);
	data.append("billingAddressPincode",company_account_billingAddressPinCode_u);
	data.append("billingAddressState",company_account_billingAddressState_u);
	data.append("vendorAddressLane1",company_account_vendorAddressLane1_u);
	data.append("vendorAddressLane2",company_account_vendorAddressLane2_u);
	data.append("vendorAddressPincode",company_account_vendorAddressPinCode_u);
	data.append("vendorAddressState",company_account_vendorAddressState_u);
	data.append("shippingAddressLane1",company_account_shippingAddressLane1_u);
	data.append("shippingAddressLane2",company_account_shippingAddressLane2_u);
	data.append("shippingAddressPinCode",company_account_shippingAddressPinCode_u);
	data.append("shippingAddressState",company_account_shippingAddressState_u);
	//console.log(data);
	$.ajax({
		type : "POST",
		dataType : "json",
		enctype: 'multipart/form-data',
		url : appBasePath + "/account/updateAccount",
	    data: data,
	    processData: false, //prevent jQuery from automatically transforming the data into a query string
	    contentType: false,
	    cache: false,
	    beforeSend : function() {
			$.blockUI({
			message: 'Loading please wait....'
			});
			},			
		success : function(data) {
			console.log(data);
			if (data.statusCode == 1) {
				$('#updateCompanyStatusMsg').html(data.message)
				$('#updateCompanyStatusMsg').css('color', 'green');
				flag=true;
			} else {
				$('#updateCompanyStatusMsg').html(data.message)
				$('#updateCompanyStatusMsg').css('color', color);
				flag=true;
			}
			$.unblockUI(); 
			//Custombox.close();
			getCompanyAccountsList();
			getAgreementFilesList(companyAccountId);
			//alert(data.message);
		},
		error : function(req, status, msg) {
			alert("in error" + req + status + msg);
			flag=false;
		}
	});
 return flag;
}



function companyAccountEditView(companyAccountId, customer_name, customer_location, customer_contact_no, 
		customer_contact_person,billingAddressLane1,billingAddressLane2,billingAddressPinCode,
		billingAddressState,shippingAddressLane1,shippingAddressLane2,shippingAddressState,shippingAddressPinCode,
		vendorAddressLane1,vendorAddressLane2,vendorAddressState,vendorAddressPinCode
		,pan,gstin,state,stateCode) {
	//logoutcheck();
	$("#company_account_name_u").val(customer_name);
	$("#company_account_location_u").val(customer_location);
	$("#company_account_contact_no_u").val(customer_contact_no);
	$("#company_account_contact_person_u").val(customer_contact_person);
	$("#company_account_billingAddressLane1_u").val(billingAddressLane1);
	$("#company_account_billingAddressLane2_u").val(billingAddressLane2);
	$("#company_account_billingAddressPinCode_u").val(billingAddressPinCode);
	$("#company_account_billingAddressState_u").val(billingAddressState);
	$("#company_account_shippingAddressLane1_u").val(shippingAddressLane1);
	$("#company_account_shippingAddressLane2_u").val(shippingAddressLane2);
	$("#company_account_shippingAddressPinCode_u").val(shippingAddressPinCode);
	$("#company_account_shippingAddressState_u").val(shippingAddressState);
	$("#company_account_vendorAddressLane1_u").val(vendorAddressLane1);
	$("#company_account_vendorAddressLane2_u").val(vendorAddressLane2);
    $("#company_account_vendorAddressPinCode_u").val(vendorAddressPinCode);
	$("#company_account_vendorAddressState_u").val(vendorAddressState);
	$("#company_account_pan_u").val(pan);
	$("#company_account_gstin_u").val(gstin);
	$("#company_account_state_u").val(state);
	$("#company_account_state_code_u").val(stateCode);
	getAgreementFilesList(companyAccountId);
	Custombox.open({
		target : "#custom-modal-edit",
		effect : "fadein"
	});
	$("#companyId").val(companyAccountId);
	//$("button#save").attr('onclick','updateCompanyAccount('+companyAccountId+')');
}
/********function for preview**********/
function accountPreView(companyAccountId, customer_name, customer_location, customer_contact_no, 
		customer_contact_person,billingAddressLane1,billingAddressLane2,billingAddressPinCode,
		billingAddressState,shippingAddressLane1,shippingAddressLane2,shippingAddressState,shippingAddressPinCode,
		vendorAddressLane1,vendorAddressLane2,vendorAddressState,vendorAddressPinCode
		,pan,gstin,state,stateCode) {
	//logoutcheck();
	 $('#basicInfoPreview').html("");
	 $('#addressInfoPreview').html("");
	 $('#taxForPreview').html("");
	Custombox.open({
		target : "#custom-modal-preview",
		effect : "fadein"
	});
	var basicInfo='<p><strong>Customer Name:</strong>'+customer_name+'<br>'+
	'<strong>Location:</strong>'+customer_location+'<br>'+
    '<strong>Contact Number:</strong>'+ customer_contact_no+'<br>'+
    '<strong>Contact Person:</strong>'+customer_contact_person+'</p>'
	var addressInfo='<p><strong>Shipping Address Lane1:</strong>'+shippingAddressLane1+'<br>'+
	'<strong>Shipping Address Lane2:</strong>'+shippingAddressLane2+'<br>'+
	'<strong>Shipping Address Pincode:</strong>'+shippingAddressPinCode+'<br>'+
	'<strong>Shipping Address State:</strong>'+shippingAddressState+'</p><br>'+
	'<p><strong>vendorAddressLane1:</strong>'+vendorAddressLane1+'<br>'+
	'<strong>vendor Address Lane2:</strong>'+vendorAddressLane2+'<br>'+
	'<strong>vendor Address Pincode:</strong>'+vendorAddressPinCode+'<br>'+
	'<strong>Vendor Address State:</strong>'+vendorAddressState+'</p><br>'+
	'<p><strong>Billing Address Lane1:</strong>'+billingAddressLane1+'<br>'+
	'<strong>Billing Address Lane2:</strong>'+billingAddressLane2+'<br>'+
	'<strong>Billing Address Pincode:</strong>'+billingAddressPinCode+'<br>'+
	'<strong>Billing Address State:</strong>'+billingAddressState+'</p><br>'
	var taxInfo='<p><strong>PAN Number:</strong>'+pan+'<br>'+
	'<strong>GSTIN:</strong>'+gstin+'<br>'+
    '<strong>State Name:</strong>'+ state+'<br>'+
    '<strong>State Code:</strong>'+stateCode+'</p>'
    $('#basicInfoPreview').append(basicInfo);
	$('#addressInfoPreview').append(addressInfo);
    $('#taxForPreview').append(taxInfo);
   getAgreementFilesListForPreview(companyAccountId);
}
/*****function for getting agreement files for preview*********/
function getAgreementFilesListForPreview(companyAccountId) {
	//logoutcheck();
	var appBasePath = getAppBasePath();
	$.ajax({
		type : "Get",
		dataType : "json",
		url : appBasePath + "/account/getAgreementsList/"+companyAccountId,
		success : function(data) {
			//alert(data);
		if (data.length > 0) {
			$('#agreementFilesList').html("");
			$.each(data,function(index, item) {
				$('#agreementFilesList').append(item.fileName+'<br>');
			    $('#agreementFilesList').wrapInner('<a href="'+appBasePath +"/fileViewer/download/"+"agreement"+"/"+item.customerUploadId+ '", target="_blank"/>');
			
			});
		} else {
			$('#agreementFilesList').html("No Sow files were found");
		}
		
		
		},
		error : function(req, status, msg) {
			alert("in error")
		}
	});
}
function companyAccountDeleteView(companyAccountId) {
	//logoutcheck();
	Custombox.open({
		target : "#custom-modal",
		effect : "fadein"
	});
	$("button#confirm").attr('onclick','deleteCompanyAccount('+companyAccountId+')');
}

function deleteCompanyAccount(companyAccountId){
	var appBasePath = getAppBasePath();
	$.ajax({
		type : "POST",
		dataType : "json",
		url : appBasePath + "/account/deleteAccount/" + companyAccountId,
		success : function(data) {
			Custombox.close();
			alert(data.message);
			getCompanyAccountsList();

		},
		error : function(req, status, msg) {
			alert("in error" + req);
		}
	});
}

function companyFileDelete(agreementId,companyAccountId){
	var appBasePath = getAppBasePath();
	$.ajax({
		type : "POST",
		dataType : "json",
		url : appBasePath + "/account/deleteAgreementFiles/" + agreementId + "/" + companyAccountId,
		success : function(data) {
			console.log(data);
			alert(data.message);
			getAgreementFilesList(companyAccountId);
		},
		error : function(req, status, msg) {
			alert("in error" + req);
		}
	});
}
///validation functions for account creation and updation
function basicFormValidations(){
	var company_account_name = $("#company_account_name").val();
	var company_account_location = $("#company_account_location").val();
	var company_account_contact_no = $("#company_account_contact_no").val();
	var company_account_contact_person = $("#company_account_contact_person").val();
	$('#createCompanyStatusMsg').css('color', 'red');
	//alert("company_account_name::"+company_account_name+"company_account_location::"+company_account_location+"company_account_contact_no::"+company_account_contact_no+"company_account_contact_person::"+company_account_contact_person);
	//Account name validation
	if (company_account_name.trim().length == 0) {
		$('#createCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_NAME_EMPTY_ERROR);
		$('#company_account_name').focus();
		return false;
	} else if (company_account_name.trim().length < 3) {
		// alert("in length" + firstname.trim().length)
		$('#createCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_NAME_LENGTH_ERROR);
		$('#company_account_name').focus();
		return false;
	} else if (!isNaN(company_account_name)) {
		$('#createCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_NAME_SPECIALS_ERROR);
		$('#company_account_name').focus();
		return false;
	} else {
		$('#createCompanyStatusMsg').html("");
	}
	if (company_account_name.trim().length > 25) {
		$("#createCompanyStatusMsg").html(VALIDATION_CREATE_ACCOUNT_NAME_MAX_LENGTH_ERROR);
		$("#company_account_name").focus();
		setTimeout(function() {
			$("#createCompanyStatusMsg").html("");
		}, 5000);
		return false;
	} else {
		$('#createCompanyStatusMsg').html("");
	}
	//Account Location validation
	if (company_account_location.trim().length == 0) {
		$('#createCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_LOCATION_EMPTY_ERROR);
		$('#company_account_location').focus();
		return false;
	} else if (company_account_location.trim().length < 3) {
		// alert("in length" + firstname.trim().length)
		$('#createCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_LOCATION_MIN_LENGTH_ERROR);
		$('#company_account_location').focus();
		return false;
	} else if (!isNaN(company_account_location)) {
		$('#createCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_LOCATION_SPECIALS_ERROR);
		$('#company_account_location').focus();
		return false;
	} else {
		$('#createCompanyStatusMsg').html("");
	}
	if (company_account_name.trim().length > 25) {
		$("#createCompanyStatusMsg").html(VALIDATION_CREATE_ACCOUNT_LOCATION_MAX_LENGTH_ERROR);
		$("#company_account_name").focus();
		setTimeout(function() {
			$("#createCompanyStatusMsg").html("");
		}, 5000);
		return false;
	} else {
		$('#createCompanyStatusMsg').html("");
	}
	//Account contact number validation
	if (company_account_contact_no.trim().length == 0) {
		$('#createCompanyStatusMsg').html(VALIDATION_CREATE_CUSTOMER_CONTACT_NUMBER_EMPTY_ERROR);
		$('#company_account_contact_no').focus();
		return false;
	} else if (company_account_contact_no.trim().length < 8) {
		$('#createCompanyStatusMsg').html(VALIDATION_CONTACT_LENGTH_ERROR);
		$('#company_account_contact_no').focus();
		return false;
	} else if (isNaN(company_account_contact_no)) {
		$('#createCompanyStatusMsg').html(VALIDATION_CONTACT_SPECIALS_ERROR);
		$('#company_account_contact_no').focus();
		return false;
	} else {
		$('#createCompanyStatusMsg').html("");
	}
	//contact person validation
	if (company_account_contact_person.trim().length == 0) {
		$('#createCompanyStatusMsg').html(VALIDATION_CREATE_CUSTOMER_CONTACT_NAME_EMPTY_ERROR);
		$('#company_account_contact_person').focus();
		return false;
	} else if (company_account_contact_person.trim().length < 3) {
		// alert("in length" + firstname.trim().length)
		$('#createCompanyStatusMsg').html(VALIDATION_CREATE_CUSTOMER_CONTACT_NAME_LENGTH_ERROR);
		$('#company_account_contact_person').focus();
		return false;
	} else if (!isNaN(company_account_contact_person)) {
		$('#createCompanyStatusMsg').html(VALIDATION_CREATE_CUSTOMER_CONTACT_NAME_SPECIALS_ERROR);
		$('#company_account_contact_person').focus();
		return false;
	} else {
		$('#createCompanyStatusMsg').html("");
	}
	if (company_account_contact_person.trim().length > 25) {
		$("#createCompanyStatusMsg").html(VALIDATION_CREATE_CUSTOMER_CONTACT_NAME_MAX_LENGTH_ERROR);
		$("#company_account_contact_person").focus();
		setTimeout(function() {
			$("#createCompanyStatusMsg").html("");
		}, 5000);
		return false;
	} else {
		$('#createCompanyStatusMsg').html("");
	}
	return true;
}
function fileValidation(){
	var file=$("#agreementFile").val();
	if(file.trim().length==0){
		$('#createCompanyStatusMsg').html("Please upload file");
		return false;	
	}
	else
	{
		$('#createCompanyStatusMsg').html("");
		return true;
	}
}
function addressValidation(){
	var company_account_billingAddressLane1 = $("#company_account_billingAddressLane1").val();
	var company_account_billingAddressLane2 = $("#company_account_billingAddressLane2").val();
	var company_account_billingAddressPinCode = $("#company_account_billingAddressPinCode").val();
	var company_account_billingAddressState = $("#company_account_billingAddressState").val();
	var company_account_shippingAddressLane1 = $("#company_account_shippingAddressLane1").val();
	var company_account_shippingAddressLane2 = $("#company_account_shippingAddressLane2").val();
	var company_account_shippingAddressPinCode = $("#company_account_shippingAddressPinCode").val();
	var company_account_shippingAddressState = $("#company_account_shippingAddressState").val();
	var company_account_vendorAddressLane1 = $("#company_account_vendorAddressLane1").val();
	var company_account_vendorAddressLane2 = $("#company_account_vendorAddressLane2").val();
	var company_account_vendorAddressPinCode = $("#company_account_vendorAddressPinCode").val();
	var company_account_vendorAddressState = $("#company_account_vendorAddressState").val();
	if (company_account_shippingAddressLane1.trim().length == 0) {
		$('#createCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_SHIPPING_ADDRESS_LANE1_EMPTY_ERROR);
		$('#company_account_shippingAddressLane1').focus();
		return false;
	}  else {
		$('#createCompanyStatusMsg').html("");
	}
	if (company_account_shippingAddressLane2.trim().length == 0) {
		$('#createCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_SHIPPING_ADDRESS_LANE2_EMPTY_ERROR);
		$('#company_account_shippingAddressLane2').focus();
		return false;
	}  else {
		$('#createCompanyStatusMsg').html("");
	}
	if (company_account_shippingAddressState.trim().length == 0) {
		$('#createCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_SHIPPING_ADDRESS_STATE_EMPTY_ERROR);
		$('#company_account_shippingAddressState').focus();
		return false;
	}  else {
		$('#createCompanyStatusMsg').html("");
	}
	if (company_account_shippingAddressPinCode.trim().length == 0) {
		$('#createCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_SHIPPING_ADDRESS_PIN_CODE_ERROR);
		$('#company_account_shippingAddressPinCode').focus();
		return false;
	}  else {
		$('#createCompanyStatusMsg').html("");
	}
	//billing Address validation
	if (company_account_billingAddressLane1.trim().length == 0) {
		$('#createCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_BILLING_ADDRESS_LANE1_EMPTY_ERROR);
		$('#company_account_billingAddressLane1').focus();
		return false;
	} else {
		$('#createCompanyStatusMsg').html("");
	}
	if (company_account_billingAddressLane2.trim().length == 0) {
		$('#createCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_BILLING_ADDRESS_LANE2_EMPTY_ERROR);
		$('#company_account_billingAddressLane2').focus();
		return false;
	}  else {
		$('#createCompanyStatusMsg').html("");
	}
	if (company_account_billingAddressState.trim().length == 0) {
		$('#createCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_BILLING_ADDRESS_STATE_EMPTY_ERROR);
		$('#company_account_billingAddressState').focus();
		return false;
	}  else {
		$('#createCompanyStatusMsg').html("");
	}
	if (company_account_billingAddressPinCode.trim().length == 0) {
		$('#createCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_BILLING_ADDRESS_PIN_CODE_ERROR);
		$('#company_account_billingAddressPinCode').focus();
		return false;
	}  else {
		$('#createCompanyStatusMsg').html("");
	}
	//Vendor Address validation
	if (company_account_vendorAddressLane1.trim().length == 0) {
		$('#createCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_VENDOR_ADDRESS_LANE1_EMPTY_ERROR);
		$('#company_account_vendorAddressLane1').focus();
		return false;
	} else {
		$('#createCompanyStatusMsg').html("");
	}
	if (company_account_vendorAddressLane2.trim().length == 0) {
		$('#createCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_VENDOR_ADDRESS_LANE2_EMPTY_ERROR);
		$('#company_account_vendorAddressLane2').focus();
		return false;
	} else {
		$('#createCompanyStatusMsg').html("");
	}
	if (company_account_vendorAddressState.trim().length == 0) {
		$('#createCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_VENDOR_ADDRESS_STATE_EMPTY_ERROR);
		$('#company_account_vendorAddressState').focus();
		return false;
	}  else {
		$('#createCompanyStatusMsg').html("");
	}
	if (company_account_vendorAddressPinCode.trim().length == 0) {
		$('#createCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_VENDOR_ADDRESS_PIN_CODE_ERROR);
		$('#company_account_vendorAddressPinCode').focus();
		return false;
	}  else {
		$('#createCompanyStatusMsg').html("");
	}
	return true;
}
function updateBasicValidations(){
	$('#updateCompanyStatusMsg').css('color', 'red');
	var company_account_name_u = $("#company_account_name_u").val();
	var company_account_location_u = $("#company_account_location_u").val();
	var company_account_contact_no_u = $("#company_account_contact_no_u").val();
	var company_account_contact_person_u = $("#company_account_contact_person_u").val();
	if (company_account_name_u.trim().length == 0) {
		$('#updateCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_NAME_EMPTY_ERROR);
		$('#company_account_name_u').focus();
		return false;
	} else if (company_account_name_u.trim().length < 3) {
		// alert("in length" + firstname.trim().length)
		$('#updateCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_NAME_LENGTH_ERROR);
		$('#company_account_name_u').focus();
		return false;
	} else if (!isNaN(company_account_name_u)) {
		$('#createCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_NAME_SPECIALS_ERROR);
		$('#company_account_name_u').focus();
		return false;
	} else {
		$('#updateCompanyStatusMsg').html("");
	}
	if (company_account_name_u.trim().length > 25) {
		$("#updateCompanyStatusMsg").html(VALIDATION_CREATE_ACCOUNT_NAME_MAX_LENGTH_ERROR);
		$("#company_account_name_u").focus();
		setTimeout(function() {
			$("#updateCompanyStatusMsg").html("");
		}, 5000);
		return false;
	} else {
		$('#updateCompanyStatusMsg').html("");
	}
	//Account Location validation
	if (company_account_location_u.trim().length == 0) {
		$('#updateCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_LOCATION_EMPTY_ERROR);
		$('#company_account_location_u').focus();
		return false;
	} else if (company_account_location_u.trim().length < 3) {
		// alert("in length" + firstname.trim().length)
		$('#updateCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_LOCATION_MIN_LENGTH_ERROR);
		$('#company_account_location_u').focus();
		return false;
	} else if (!isNaN(company_account_location_u)) {
		$('#updateCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_LOCATION_SPECIALS_ERROR);
		$('#company_account_location_u').focus();
		return false;
	} else {
		$('#updateCompanyStatusMsg').html("");
	}
	if (company_account_location_u.trim().length > 25) {
		$("#updateCompanyStatusMsg").html(VALIDATION_CREATE_ACCOUNT_LOCATION_MAX_LENGTH_ERROR);
		$("#company_account_location_u").focus();
		setTimeout(function() {
			$("#updateCompanyStatusMsg").html("");
		}, 5000);
		return false;
	} else {
		$('#updateCompanyStatusMsg').html("");
	}
	//Account contact number validation
	if (company_account_contact_no_u.trim().length == 0) {
		$('#updateCompanyStatusMsg').html(VALIDATION_CREATE_CUSTOMER_CONTACT_NUMBER_EMPTY_ERROR);
		$('#company_account_contact_no_u').focus();
		return false;
	} else if (company_account_contact_no_u.trim().length < 8) {
		$('#updateCompanyStatusMsg').html(VALIDATION_CONTACT_LENGTH_ERROR);
		$('#company_account_contact_no_u').focus();
		return false;
	} else if (isNaN(company_account_contact_no_u)) {
		$('#updateCompanyStatusMsg').html(VALIDATION_CONTACT_SPECIALS_ERROR);
		$('#company_account_contact_no_u').focus();
		return false;
	} else {
		$('#updateCompanyStatusMsg').html("");
	}
	//contact person validation
	if (company_account_contact_person_u.trim().length == 0) {
		$('#updateCompanyStatusMsg').html(VALIDATION_CREATE_CUSTOMER_CONTACT_NAME_EMPTY_ERROR);
		$('#company_account_contact_person_u').focus();
		return false;
	} else if (company_account_contact_person_u.trim().length < 3) {
		// alert("in length" + firstname.trim().length)
		$('#updateCompanyStatusMsg').html(VALIDATION_CREATE_CUSTOMER_CONTACT_NAME_LENGTH_ERROR);
		$('#company_account_contact_person_u').focus();
		return false;
	} else if (!isNaN(company_account_contact_person_u)) {
		$('#updateCompanyStatusMsg').html(VALIDATION_CREATE_CUSTOMER_CONTACT_NAME_SPECIALS_ERROR);
		$('#company_account_contact_person_u').focus();
		return false;
	} else {
		$('#updateCompanyStatusMsg').html("");
	}
	if (company_account_contact_person_u.trim().length > 25) {
		$("#updateCompanyStatusMsg").html(VALIDATION_CREATE_CUSTOMER_CONTACT_NAME_MAX_LENGTH_ERROR);
		$("#company_account_contact_person_u").focus();
		setTimeout(function() {
			$("#updateCompanyStatusMsg").html("");
		}, 5000);
		return false;
	} else {
		$('#updateCompanyStatusMsg').html("");
	}
	return true;
}
function updateAddressValidations(){
	$('#updateCompanyStatusMsg').css('color', 'red');
	var company_account_billingAddressLane1_u = $("#company_account_billingAddressLane1_u").val();
	var company_account_billingAddressLane2_u = $("#company_account_billingAddressLane2_u").val();
	var company_account_billingAddressPinCode_u = $("#company_account_billingAddressPinCode_u").val();
	var company_account_billingAddressState_u = $("#company_account_billingAddressState_u").val();
	var company_account_shippingAddressLane1_u = $("#company_account_shippingAddressLane1_u").val();
	var company_account_shippingAddressLane2_u = $("#company_account_shippingAddressLane2_u").val();
	var company_account_shippingAddressPinCode_u = $("#company_account_shippingAddressPinCode_u").val();
	var company_account_shippingAddressState_u = $("#company_account_shippingAddressState_u").val();
	var company_account_vendorAddressLane1_u = $("#company_account_vendorAddressLane1_u").val();
	var company_account_vendorAddressLane2_u= $("#company_account_vendorAddressLane2_u").val();
	var company_account_vendorAddressPinCode_u = $("#company_account_vendorAddressPinCode_u").val();
	var company_account_vendorAddressState_u = $("#company_account_vendorAddressState_u").val();
	//Shipping address validation
	if (company_account_shippingAddressLane1_u.trim().length == 0) {
		$('#updateCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_SHIPPING_ADDRESS_LANE1_EMPTY_ERROR);
		$('#company_account_shippingAddressLane1_u').focus();
		return false;
	}  else {
		$('#updateCompanyStatusMsg').html("");
	}
	if (company_account_shippingAddressLane2_u.trim().length == 0) {
		$('#updateCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_SHIPPING_ADDRESS_LANE2_EMPTY_ERROR);
		$('#company_account_shippingAddressLane2_u').focus();
		return false;
	}  else {
		$('#updateCompanyStatusMsg').html("");
	}
	if (company_account_shippingAddressState_u.trim().length == 0) {
		$('#updateCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_SHIPPING_ADDRESS_STATE_EMPTY_ERROR);
		$('#company_account_shippingAddressState_u').focus();
		return false;
	}  else {
		$('#updateCompanyStatusMsg').html("");
	}
	if (company_account_shippingAddressPinCode_u.trim().length == 0) {
		$('#updateCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_SHIPPING_ADDRESS_PIN_CODE_ERROR);
		$('#company_account_shippingAddressPinCode_u').focus();
		return false;
	}  else {
		$('#updateCompanyStatusMsg').html("");
	}
	//billing Address validation
	if (company_account_billingAddressLane1_u.trim().length == 0) {
		$('#updateCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_BILLING_ADDRESS_LANE1_EMPTY_ERROR);
		$('#company_account_billingAddressLane1_u').focus();
		return false;
	} else {
		$('#updateCompanyStatusMsg').html("");
	}
	if (company_account_billingAddressLane2_u.trim().length == 0) {
		$('#updateCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_BILLING_ADDRESS_LANE2_EMPTY_ERROR);
		$('#company_account_billingAddressLane2_u').focus();
		return false;
	}  else {
		$('#updateCompanyStatusMsg').html("");
	}
	if (company_account_billingAddressState_u.trim().length == 0) {
		$('#updateCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_BILLING_ADDRESS_STATE_EMPTY_ERROR);
		$('#company_account_biullingAddressState_u').focus();
		return false;
	}  else {
		$('#updateCompanyStatusMsg').html("");
	}
	if (company_account_billingAddressPinCode_u.trim().length == 0) {
		$('#updateCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_BILLING_ADDRESS_PIN_CODE_ERROR);
		$('#company_account_billingAddressPinCode_u').focus();
		return false;
	}  else {
		$('#updateCompanyStatusMsg').html("");
	}
	//Vendor Address validation
	if (company_account_vendorAddressLane1_u.trim().length == 0) {
		$('#updateCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_VENDOR_ADDRESS_LANE1_EMPTY_ERROR);
		$('#company_account_vendorAddressLane1_u').focus();
		return false;
	} else {
		$('#updateCompanyStatusMsg').html("");
	}
	if (company_account_vendorAddressLane2_u.trim().length == 0) {
		$('#updateCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_VENDOR_ADDRESS_LANE2_EMPTY_ERROR);
		$('#company_account_vendorAddressLane2_u').focus();
		return false;
	} else {
		$('#updateCompanyStatusMsg').html("");
	}
	if (company_account_vendorAddressState_u.trim().length == 0) {
		$('#updateCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_VENDOR_ADDRESS_STATE_EMPTY_ERROR);
		$('#company_account_vendorAddressState_u').focus();
		return false;
	}  else {
		$('#updateCompanyStatusMsg').html("");
	}
	if (company_account_vendorAddressPinCode_u.trim().length == 0) {
		$('#updateCompanyStatusMsg').html(VALIDATION_CREATE_ACCOUNT_VENDOR_ADDRESS_PIN_CODE_ERROR);
		$('#company_account_vendorAddressPinCode_u').focus();
		return false;
	}  else {
		$('#updateCompanyStatusMsg').html("");
	}	
	return true;
	
}