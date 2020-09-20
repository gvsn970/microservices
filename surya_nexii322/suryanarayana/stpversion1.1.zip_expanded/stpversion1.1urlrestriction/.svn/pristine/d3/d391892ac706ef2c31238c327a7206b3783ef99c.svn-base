/* function for add bankAccount*/
function addBankAccount() {
	var l = $('#createaccount').ladda();
    l.ladda('start');
	//logoutcheck();
	var appBasePath = getAppBasePath();
	var bankName = $("#bankName").val().trim();
	var accountHolderName = $("#accountHolderName").val().trim();
	var accountNumber = $("#accountNumber").val().trim();
	var ifscCode = $("#ifscCode").val().trim();
	var branchName = $("#branchName").val().trim();
	//var accountName = $("#accountName").val().trim();
	var employeeId = 0;
	var color = 'red';
	$('#bankErrorMessage').css('color', color);
	if (bankName.length == 0 || bankName==null) {
		$('#bankErrorMessage').html(VALIDATION_BANKDETAILS_BANK_NAME_EMPTY_ERROR);
		$('#bankName').focus();
		l.ladda('stop');
		return false;
	} 
	else if(bankName.length<3)
	{
		$('#bankErrorMessage').html(VALIDATION_BANKDETAILS_BANK_NAME_MIN_LENGTH_ERROR);
		$('#bankName').focus();
		l.ladda('stop');
		return false;
	}
	else if(bankName.length>25)
	{
		$('#bankErrorMessage').html(VALIDATION_BANKDETAILS_BANK_NAME_MAX_LENGTH_ERROR);
		$('#bankName').focus();
		l.ladda('stop');
		return false;
	}
	else {
		$('#bankErrorMessage').html("");
	}
	if (accountHolderName.length == 0 || accountHolderName==null) {
		$('#bankErrorMessage').html(VALIDATION_BANKDETAILS_ACCOUNT_HOLDER_NAME_EMPTY_ERROR);
		$('#accountHolderName').focus();
		l.ladda('stop');
		return false;
	} 
	else if(accountHolderName.length<3)
	{
		$('#bankErrorMessage').html(VALIDATION_BANKDETAILS_ACCOUNT_HOLDER_NAME_MIN_LENGTH_ERROR);
		$('#accountHolderName').focus();
		l.ladda('stop');
		return false;
	}
	else if(accountHolderName.length>25)
	{
		$('#bankErrorMessage').html(VALIDATION_BANKDETAILS_ACCOUNT_HOLDER_NAME_MAX_LENGTH_ERROR);
		$('#accountHolderName').focus();
		l.ladda('stop');
		return false;
	}
	else {
		$('#bankErrorMessage').html("");
	}
	if (accountNumber.length == 0 || accountNumber==null) {
		$('#bankErrorMessage').html(VALIDATION_BANKDETAILS_ACCOUNT_NUMBER_EMPTY_ERROR);
		$('#accountNumber').focus();
		l.ladda('stop');
		return false;
	} 
	else if(accountNumber.length<5)
	{
		$('#bankErrorMessage').html(VALIDATION_BANKDETAILS_ACCOUNT_NUMBER_MIN_LENGTH_ERROR);
		$('#accountNumber').focus();
		l.ladda('stop');
		return false;
	}
	else if (isNaN(accountNumber)) {
		$('#bankErrorMessage').html(VALIDATION_BANK_ACCOUNT_NUMBER_SPECIALS_ERROR);
		$('#accountNumber').focus();
		l.ladda('stop');
		return false;
	}
	else if(accountNumber.length>25)
	{
		$('#bankErrorMessage').html(VALIDATION_BANKDETAILS_ACCOUNT_NUMBER_MAX_LENGTH_ERROR);
		$('#accountNumber').focus();
		l.ladda('stop');
		return false;
	}
	else {
		$('#bankErrorMessage').html("");
	}
	if (branchName.length == 0 || branchName==null) {
		$('#bankErrorMessage').html(VALIDATION_BANKDETAILS_BRANCH_NAME_EMPTY_ERROR);
		$('#branchName').focus();
		l.ladda('stop');
		return false;
	}
	else if(branchName.length<3)
	{
		$('#bankErrorMessage').html(VALIDATION_BANKDETAILS_BRANCH_MIN_LENGTH_ERROR);
		$('#branchName').focus();
		l.ladda('stop');
		return false;
	}
	else if(branchName.length>25)
	{
		$('#bankErrorMessage').html(VALIDATION_BANKDETAILS_BRANCH_MAX_LENGTH_ERROR);
		$('#branchName').focus();
		l.ladda('stop');
		return false;
	}
	else {
		$('#bankErrorMessage').html("");
	}
	if (ifscCode.length == 0 || ifscCode==null) {
		$('#bankErrorMessage').html(VALIDATION_BANKDETAILS_IFSC_EMPTY_ERROR);
		$('#ifscCode').focus();
		l.ladda('stop');
		return false;
	}
	else if(ifscCode.length<3)
	{
		$('#bankErrorMessage').html(VALIDATION_BANKDETAILS_IFSC_MIN_LENGTH_ERROR);
		$('#ifscCode').focus();
		l.ladda('stop');
		return false;
	}
	else if(ifscCode.length>20)
	{
		$('#bankErrorMessage').html(VALIDATION_BANKDETAILS_IFSC_MAX_LENGTH_ERROR);
		$('#ifscCode').focus();
		l.ladda('stop');
		return false;
	}
	else {
		$('#bankErrorMessage').html("");
	}
	var params = {
			accountHolderName:accountHolderName,
			accountNumber : accountNumber ,
			accountIFSC : ifscCode,
			branchName : branchName ,
			bankName : bankName 
	};
	var queryParam = jQuery.param(params);
	$.ajax({
		type : "POST",
		data:queryParam,
		dataType : "json",
		url : appBasePath + "/bankaccount/addBankAccount",
		success : function(data) {
			// alert(data);
			if (data.statusCode == 1) {
				l.ladda('stop');
				$('#bankErrorMessage').css('color', 'green');
				$('#bankErrorMessage').html(data.message);
				getBankAcountList();
				
			} else {
				l.ladda('stop');
				$('#bankErrorMessage').css('color', color);
				$('#bankErrorMessage').html(data.message);
			}
			setTimeout(function (){
	       		 $('#bankErrorMessage').html("");
	       		 $('#ifscCode').val("");
	       		 $('#accountNumber').val("");
	       		 $('#accountHolderName').val("");
	       		 $('#bankName').val("");
	       		$('#branchName').val("");
	       	   },5000);
		},
		error : function(req, status, msg) {
			l.ladda('stop');
			alert("in error" + req + status + msg);
		}
	});
}
/**function for update bank account******/
function updateBankAccount(accountId) 
{
	//logoutcheck();
	var appBasePath = getAppBasePath();
	var bankName = $("#updatebankName").val().trim();
	var accountHolderName = $("#updateaccountHolderName").val().trim();
	var accountNumber = $("#updateaccountNumber").val().trim();
	var ifscCode = $("#updateifscCode").val().trim();
	var branchName = $("#updatebranchName").val().trim();
	var color = 'red';
	$('#updatebankErrorMessage').css('color', color);
	if (bankName.length == 0 || bankName==null) {
		$('#updatebankErrorMessage').html(VALIDATION_BANKDETAILS_BANK_NAME_EMPTY_ERROR);
		$('#updatebankName').focus();
		return false;
	} 
	else if(bankName.length<3)
	{
		$('#updatebankErrorMessage').html(VALIDATION_BANKDETAILS_BANK_NAME_MIN_LENGTH_ERROR);
		$('#updatebankName').focus();
		return false;
	}
	else if(bankName.length>25)
	{
		$('#updatebankErrorMessage').html(VALIDATION_BANKDETAILS_BANK_NAME_MAX_LENGTH_ERROR);
		$('#updatebankName').focus();
		return false;
	}
	else {
		$('#updatebankErrorMessage').html("");
	}
	if (accountHolderName.length == 0 || accountHolderName==null) {
		$('#updatebankErrorMessage').html(VALIDATION_BANKDETAILS_ACCOUNT_HOLDER_NAME_EMPTY_ERROR);
		$('#updateaccountHolderName').focus();
		return false;
	} 
	else if(accountHolderName.length<3)
	{
		$('#updatebankErrorMessage').html(VALIDATION_BANKDETAILS_ACCOUNT_HOLDER_NAME_MIN_LENGTH_ERROR);
		$('#updateaccountHolderName').focus();
		return false;
	}
	else if(accountHolderName.length>25)
	{
		$('#updatebankErrorMessage').html(VALIDATION_BANKDETAILS_ACCOUNT_HOLDER_NAME_MAX_LENGTH_ERROR);
		$('#updateaccountHolderName').focus();
		return false;
	}
	else {
		$('#updatebankErrorMessage').html("");
	}
	if (accountNumber.length == 0 || accountNumber==null) {
		$('#updatebankErrorMessage').html(VALIDATION_BANKDETAILS_ACCOUNT_NUMBER_EMPTY_ERROR);
		$('#updateaccountNumber').focus();
		return false;
	} 
	else if (isNaN(accountNumber)) {
		$('#updatebankErrorMessage').html(VALIDATION_BANK_ACCOUNT_NUMBER_SPECIALS_ERROR);
		$('#updateaccountNumber').focus();
		return false;
	}
	else if(accountNumber.length<5)
	{
		$('#updatebankErrorMessage').html(VALIDATION_BANKDETAILS_ACCOUNT_NUMBER_MIN_LENGTH_ERROR);
		$('#updateaccountNumber').focus();
		return false;
	}
	else if(accountNumber.length>25)
	{
		$('#updatebankErrorMessage').html(VALIDATION_BANKDETAILS_ACCOUNT_NUMBER_MAX_LENGTH_ERROR);
		$('#updateaccountNumber').focus();
		return false;
	}
	else {
		$('#updatebankErrorMessage').html("");
	}
	if (branchName.length == 0 || branchName==null) {
		$('#updatebankErrorMessage').html(VALIDATION_BANKDETAILS_BRANCH_NAME_EMPTY_ERROR);
		$('#updatebranchName').focus();
		return false;
	} else {
		$('#updatebankErrorMessage').html("");
	}
	if (ifscCode.length == 0 || ifscCode==null) {
		$('#updatebankErrorMessage').html(VALIDATION_BANKDETAILS_IFSC_EMPTY_ERROR);
		$('#updateifscCode').focus();
		return false;
	} 
	else if(ifscCode.length<3)
	{
		$('#updatebankErrorMessage').html(VALIDATION_BANKDETAILS_IFSC_MIN_LENGTH_ERROR);
		$('#updateifscCode').focus();
		return false;
	}
	else if(ifscCode.length>20)
	{
		$('#updatebankErrorMessage').html(VALIDATION_BANKDETAILS_IFSC_MAX_LENGTH_ERROR);
		$('#updateifscCode').focus();
		return false;
	}
	else {
		$('#updatebankErrorMessage').html("");
	}
   var params = {
		    accountId:accountId,
			accountHolderName:accountHolderName,
			accountNumber : accountNumber ,
			accountIFSC : ifscCode,
			branchName : branchName ,
			bankName : bankName 
	};
	var queryParam = jQuery.param(params);
	$.ajax({
		type : "POST",
		data:queryParam,
		dataType : "json",
		url : appBasePath + "/bankaccount/updateBankAccount",
		success : function(data) {
			// alert(data.message);
			if (data.statusCode == 1) {
				$('#updatebankErrorMessage').css('color', 'green');
				$('#updatebankErrorMessage').html(data.message);
				getBankAcountList();
			} else {
				$('#updatebankErrorMessage').css('color', color);
				$('#updatebankErrorMessage').html(data.message);
			}
			setTimeout(function (){
       		 $('#updatebankErrorMessage').html("");
       	   },5000);
		},
		error : function(req, status, msg) {
			alert("in error" + req + status + msg);
		}
	});
}
/****Bank account edit view***************/
function bankAccountEditView(accountId) {
	//logoutcheck();
	//alert("customerId::"+customerId);
	Custombox.open({
		target : "#custom-modal-edit",
		effect : "fadein"
	});
	$("button#updateAccount").attr('onclick','updateBankAccount('+accountId+')');
	getAccountDetails(accountId);

}
/**********Bank Account List out***********/
function getBankAcountList() {
	//logoutcheck();
	var appBasePath = getAppBasePath();
	$.ajax({
				type : "Get",
				dataType : "json",
				url : appBasePath + "/bankaccount/bankAccountList",
				success : function(data) {
					//alert(data);
					if (data.length > 0) {
						$('#banklist').html("");
						$.each(data,function(index, item) {
						var row='<div class="form-group clearfix col-sm-6" style="text-align:left !important; border:1px solid #eaf1f6;">'
		                       +'<div class="radio radio-info">'
		                       +'<input type="radio" name="bankradio" id="'+item.bankName+'" onchange="activeBankAccount('+item.accountId+');">'
		                       +'<label><span style="font-size:14px; color:#000; font-weight:600;">'+item.bankName+'</span>'
		                       +'<a href="#custom-modal-edit" class="on-default text-info m-r-10 pull-right" onclick="bankAccountEditView('+item.accountId+');" data-animation="fadein" data-plugin="custommodal" data-overlaySpeed="200" data-overlayColor="#36404a">'
		                       +'<i class="fa fa-pencil"></i></a><br>'
		                       +'Ac.No:' +item.accountNumber+'<br>Ac.Holder Name:'+ item.accountHolderName+'<br>IFSC:'+item.accountIFSC+'<br>Branch:'+item.accountBranchName+'</label>'
		                       + '</div>'
		                       +'</div>'
		                       $('#banklist').append(row);
						      if(item.isActive==1)
						      {
						    	  $("[id='"+item.bankName+"']").prop("checked", true);
						      }
						});
					} else {
						$('#banklist').html("");
						
					}

				},
				error : function(req, status, msg) {
					alert("in error" + req + status + msg);
				}
			});
}
/********function for delete bankaccount******/
function deleteAccount(accountId)
{
	//logoutcheck();
	var appBasePath = getAppBasePath();
	var employeeId;
	$.ajax({
		type : "POST",
		dataType : "json",
		url : appBasePath + "/bankaccount/deleteBankAccount/"+accountId,
		success : function(data) {
			alert(data.message);
			//Custombox.close();
		},
		error : function(req, status, msg) {
			alert("in error" + req + status + msg);
		}
	});
}
/******function for get account details*/
function getAccountDetails(accountId)
{
	//logoutcheck();
	var appBasePath = getAppBasePath();
	var employeeId;
	$.ajax({
		type : "GET",
		dataType : "json",
		url : appBasePath + "/bankaccount/getBankAccountDetails/"+accountId,
		success : function(data) {
			if(data!=null || data!="")
			{
				$("#updatebankName").val(data.bankName);
				$("#updateaccountHolderName").val(data.accountHolderName);
			    $("#updateaccountNumber").val(data.accountNumber);
				$("#updateifscCode").val(data.accountIFSC);
			    $("#updatebranchName").val(data.accountBranchName);
				
			}
		},
		error : function(req, status, msg) {
			alert("in error" + req + status + msg);
		}
	});
}
/***function for add tax details*******/

function addTaxDetails() {
	var l = $('#savetax').ladda();
    l.ladda('start');
	//logoutcheck();
	var appBasePath = getAppBasePath();
	var panNumber = $("#panNumber").val().trim();
	var stateName = $("#stateName").val().trim();
	var stateCode = $("#stateCode").val().trim();
	var gstIn = $("#gstIn").val().trim();
	var color = 'red';
	$('#taxErrorMessage').css('color', color);
	
	if (panNumber.length == 0 || panNumber==null) {
		$('#taxErrorMessage').html(VALIDATION_TAXDETAILS_PAN_EMPTY_ERROR);
		$('#panNumber').focus();
		l.ladda('stop');
		return false;
	}else if(panNumber.length<10){
		$('#taxErrorMessage').html(VALIDATION_TAXDETAILS_PAN_MIN_LENGTH_ERROR);
		$('#panNumber').focus();
		l.ladda('stop');
		return false;
	}else if(panNumber.length>20){
		$('#taxErrorMessage').html(VALIDATION_TAXDETAILS_PAN_MAX_LENGTH_ERROR);
		$('#panNumber').focus();
		l.ladda('stop');
		return false;
	}else {
		$('#taxErrorMessage').html("");
	}
	if (gstIn.length == 0 || gstIn==null) {
		$('#taxErrorMessage').html(VALIDATION_TAXDETAILS_GST_EMPTY_ERROR);
		$('#gstIn').focus();
		l.ladda('stop');
		return false;
	}else if(/^[a-zA-Z0-9- ]*$/.test(gstIn) == false) {
	    $('#taxErrorMessage').html(VALIDATION_TAXDETAILS_GST_SPECIAL_ERROR);
		$('#gstIn').focus();
		l.ladda('stop');
		return false;
	}else {
		$('#taxErrorMessage').html("");
	}
	if (stateName.length == 0 || stateName==null) {
		$('#taxErrorMessage').html(VALIDATION_TAXDETAILS_STATE_NAME_EMPTY_ERROR);
		$('#stateName').focus();
		l.ladda('stop');
		return false;
	}else if(stateName.length<3){
		$('#taxErrorMessage').html(VALIDATION_TAXDETAILS_STATE_NAME_MIN_LENGTH_ERROR);
		$('#stateName').focus();
		l.ladda('stop');
		return false;
	}else if(stateName.length>25){
		$('#taxErrorMessage').html(VALIDATION_TAXDETAILS_STATE_NAME_MAX_LENGTH_ERROR);
		$('#stateName').focus();
		l.ladda('stop');
		return false;
	}else {
		$('#taxErrorMessage').html("");
	}
	
	if (stateCode.length == 0 || stateCode==null) {
		$('#taxErrorMessage').html(VALIDATION_TAXDETAILS_STATE_CODE_EMPTY_ERROR);
		$('#stateCode').focus();
		l.ladda('stop');
		return false;
	}else if(stateCode.length<2){
		$('#taxErrorMessage').html(VALIDATION_TAXDETAILS_STATE_CODE_MIN_LENGTH_ERROR);
		$('#stateCode').focus();
		l.ladda('stop');
		return false;
	}else if(stateCode.length>10){
		$('#taxErrorMessage').html(VALIDATION_TAXDETAILS_STATE_CODE_MAX_LENGTH_ERROR);
		$('#stateCode').focus();
		l.ladda('stop');
		return false;
	}else {
		$('#taxErrorMessage').html("");
	}
	
	var params = {
			panNumber : panNumber ,
			stateName : stateName,
			gstIn : gstIn ,
			stateCode : stateCode 
	};
	var queryParam = jQuery.param(params);
	$.ajax({
		type : "POST",
		data:queryParam,
		dataType : "json",
		url : appBasePath + "/taxDetails/addTaxDetails",
		success : function(data) {
			// alert(data.message);
			if (data.statusCode == 1) {
				l.ladda('stop');
				$('#taxErrorMessage').css('color', 'green');
				$('#taxErrorMessage').html(data.message);
				getTaxDetailsList();
			} else {
				l.ladda('stop');
				$('#taxErrorMessage').css('color', color);
				$('#taxErrorMessage').html(data.message);
			}
			 setTimeout(function (){
        		 $('#taxErrorMessage').html("");
        		 $('#stateName').val(" ");
     			$('#stateCode').val(" ");
     			$('#gstIn').val(" ");
        	 },5000);
			
		},
		error : function(req, status, msg) {
			alert("in error" + req + status + msg);
		}
	});
}

/***Function for update taxDetails****/
function updateTaxDetails(taxId) {
	//logoutcheck();
	var appBasePath = getAppBasePath();
	var panNumber = $("#updatepanNumber").val().trim();
	var stateName = $("#updatestateName").val().trim();
	var stateCode = $("#updatestateCode").val().trim();
	var gstIn = $("#updategstIn").val().trim();
	var color = 'red';
	$('#updatetaxErrorMessage').css('color', color);
	if (panNumber.length == 0 || panNumber==null) {
		$('#updatetaxErrorMessage').html(VALIDATION_TAXDETAILS_PAN_EMPTY_ERROR);
		$('#updatepanNumber').focus();
		return false;
	} 
	else if(panNumber.length<10)
	{
		$('#updatetaxErrorMessage').html(VALIDATION_TAXDETAILS_PAN_MIN_LENGTH_ERROR);
		$('#updatepanNumber').focus();
		return false;
	}
	else if(panNumber.length>20)
	{
		$('#updatetaxErrorMessage').html(VALIDATION_TAXDETAILS_PAN_MAX_LENGTH_ERROR);
		$('#updatepanNumber').focus();
		return false;
	}
	else {
		$('#updatetaxErrorMessage').html("");
	}
	if (stateName.length == 0 || stateName==null) {
		$('#updatetaxErrorMessage').html(VALIDATION_TAXDETAILS_STATE_NAME_EMPTY_ERROR);
		$('#updatestateName').focus();
		return false;
	} 
	else if(stateName.length<3)
	{
		$('#updatetaxErrorMessage').html(VALIDATION_TAXDETAILS_STATE_NAME_MIN_LENGTH_ERROR);
		$('#updatestateName').focus();
		return false;
	}
	else if(stateName.length>25)
	{
		$('#updatetaxErrorMessage').html(VALIDATION_TAXDETAILS_STATE_NAME_MAX_LENGTH_ERROR);
		$('#updatestateName').focus();
		return false;
	}
	else {
		$('#updatetaxErrorMessage').html("");
	}
	if (stateCode.length == 0 || stateCode==null) {
		$('#updatetaxErrorMessage').html(VALIDATION_TAXDETAILS_STATE_CODE_EMPTY_ERROR);
		$('#updatestateCode').focus();
		return false;
	} 
	else if(stateCode.length<2)
	{
		$('#updatetaxErrorMessage').html(VALIDATION_TAXDETAILS_STATE_CODE_MIN_LENGTH_ERROR);
		$('#updatestateCode').focus();
		return false;
	}
	else if(stateCode.length>10)
	{
		$('#updatetaxErrorMessage').html(VALIDATION_TAXDETAILS_STATE_CODE_MAX_LENGTH_ERROR);
		$('#updatestateCode').focus();
		return false;
	}
	else {
		$('#updatetaxErrorMessage').html("");
	}
	if (gstIn.length == 0 || gstIn==null) {
		$('#updatetaxErrorMessage').html(VALIDATION_TAXDETAILS_GST_EMPTY_ERROR);
		$('#updategstIn').focus();
		return false;
	} 
	else if(/^[a-zA-Z0-9- ]*$/.test(gstIn) == false) {
	    $('#updatetaxErrorMessage').html(VALIDATION_TAXDETAILS_GST_SPECIAL_ERROR);
		$('#updategstIn').focus();
		return false;
	}
	else {
		$('#updatetaxErrorMessage').html("");
	}
	var params = {
			taxId:taxId,
			panNumber : panNumber ,
			stateName : stateName,
			gstIn : gstIn ,
			stateCode : stateCode 
	};
	var queryParam = jQuery.param(params);

	$.ajax({
		type : "POST",
		data:queryParam,
		dataType : "json",
		url : appBasePath + "/taxDetails/updateTaxDetails",
		success : function(data) {
			// alert(data.message);
			if (data.statusCode == 1) {
				$('#updatetaxErrorMessage').css('color', 'green');
				$('#updatetaxErrorMessage').html(data.message);
				getTaxDetailsList();
			} else {
				$('#updatetaxErrorMessage').css('color', color);
				$('#updatetaxErrorMessage').html(data.message);
			}
			setTimeout(function (){
       		 $('#updatetaxErrorMessage').html("");
       	 },5000);
		},
		error : function(req, status, msg) {
			alert("in error" + req + status + msg);
		}
	});
}
/****function for delete tax details***********/
function deleteTaxDetails(taxId)
{
	//logoutcheck();
	var appBasePath = getAppBasePath();
	var employeeId;
	$.ajax({
		type : "POST",
		dataType : "json",
		url : appBasePath + "/taxDetails/deleteTaxDetails/"+taxId,
		success : function(data) {
			alert(data.message);
			//Custombox.close();
		},
		error : function(req, status, msg) {
			alert("in error" + req + status + msg);
		}
	});
}
/***Function for get tax details********/
function getTaxDetails(taxId)
{
	//logoutcheck();
	var appBasePath = getAppBasePath();
	var employeeId;
	$.ajax({
		type : "GET",
		dataType : "json",
		url : appBasePath + "/taxDetails/getTaxDetails/"+taxId,
		success : function(data) {
			//alert(data.message);
			if(data!=null || data!="")
			{
				$("#updatepanNumber").val(data.panNumber);
				$("#updatestateName").val(data.state);
				$("#updatestateCode").val(data.stateCode);
				$("#updategstIn").val(data.gstIn);
			}
		},
		error : function(req, status, msg) {
			alert("in error" + req + status + msg);
		}
	});
}
/*********Function for tax edit view**********/
function taxDetailsEditView(taxId) {
	//logoutcheck();
	Custombox.open({
		target : "#custom-modal-edit",
		effect : "fadein"
	});
	$("button#updateTax").attr('onclick','updateTaxDetails('+taxId+')');
	getTaxDetails(taxId);

}
/*********Function for Tax details List*********/
function getTaxDetailsList() {
	//logoutcheck();
	var appBasePath = getAppBasePath();
	$.ajax({
				type : "Get",
				dataType : "json",
				url : appBasePath + "/taxDetails/taxDetailsList",
				success : function(data) {
					//alert(data);
					if (data.length > 0) {
						$('#taxList').html("");
						$('#panNumber').val();
						$.each(data,function(index, item) {
							if(index==0)
							{
								$('#panNumber').val(item.panNumber);
								$("#panNumber").prop("readonly", true);
							}
						var row='<div class="form-group clearfix col-sm-6" style="text-align:left !important; border:1px solid #eaf1f6;">'
		                       +'<div class="radio radio-info">'
		                       +'<input type="radio" name="bankradio" id="'+item.gstIn+'" onchange="activeTaxDetails('+item.taxId+');">'
		                       +'<label><span style="font-size:14px; color:#000; font-weight:600;">'+item.panNumber+'</span>'
		                       +'<a href="#custom-modal-edit" class="on-default text-info m-r-10 pull-right" onclick="taxDetailsEditView('+item.taxId+');" data-animation="fadein" data-plugin="custommodal" data-overlaySpeed="200" data-overlayColor="#36404a">'
		                       +'<i class="fa fa-pencil"></i></a><br>'
		                       +  'GSTIN:' +item.gstIn+'<br>State:'+ item.state+'<br>State Code:'+item.stateCode+'</label>'
		                       + '</div>'
		                       +'</div>'
		                       $('#taxList').append(row);
							if(item.isActive==1)
							{
					    	  $("[id='"+item.gstIn+"']").prop("checked", true);
							}
						});
					} else {
						$('#taxList').html("");
						
					}

				},
				error : function(req, status, msg) {
					alert("in error" + req + status + msg);
				}
			});
}
/********function for active bank account***/
function activeBankAccount(accountId)
{
	//logoutcheck();
	var appBasePath = getAppBasePath();
	var employeeId;
	$.ajax({
		type : "POST",
		dataType : "json",
		url : appBasePath + "/bankaccount/activeBankAccount/"+accountId,
		success : function(data) {
			//alert(data.message);
		},
		error : function(req, status, msg) {
			alert("in error" + req + status + msg);
		}
	});

}
/********function active tax details*******/
function activeTaxDetails(taxId)
{
	//logoutcheck();
	var appBasePath = getAppBasePath();
	var employeeId;
	$.ajax({
		type : "POST",
		dataType : "json",
		url : appBasePath +"/taxDetails/activeTaxDetails/"+taxId,
		success : function(data) {
			//alert(data.message);
			//Custombox.close();
		},
		error : function(req, status, msg) {
			alert("in error" + req + status + msg);
		}
	});

}