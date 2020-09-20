function IsName(name) {
	var regex = /^([A-Za-z ]{3,20})+([A-Za-z0-9 ])$/;
	return regex.test(name);
}

function roleDelete(roleId){
	//logoutcheck();
	Custombox.open({
		target : "#custom-modal",
		effect : "fadein"
	});
	$(".delete").attr("id",roleId);
}

function deleteRole(roleId) {
	//logoutcheck();
	var roleid = $(roleId).parent().attr('id');
	var appBasePath = getAppBasePath();
	$.ajax({
		type : "DELETE",
		url : appBasePath + "/crud/deleterole/" + roleid,
		data : roleid,
		success : function(data) {
			//alert(data.statusCode);
			//alert("in delete"+data.message);
			if (data.message == "Role can not be deleted an user is existed with this role.") {
				$("#roleError").html(data.message);
				setTimeout(function() {$("#roleError").html("");}, 5000);
			} else {
				// alert(data.message);
				roleLoader();
			}
			$("#roleDeleteCancel").click();
		},
		error : function(req, status, msg) {
			alert("in error");
		}
	});
}
 function roleLoader(){
	//logoutcheck();
	var appBasePath=getAppBasePath();
	$.ajax({
		type: "GET",
	    dataType:"json",
	    url: appBasePath+"/crud/getallroles",
	    success : function(data) {
	    	$('#roleList').html("");
	        if(data.length>0){
	        	$.each(data, function (index, item) {
	        	var eachrow = "<tr>"
	        		+ "<td>" + item.role_name + "</td>"
	        		+ "<td>" + item.fk_permission_id + "</td>"
		            +'<td class="actions"><a href="#" class="on-default text-info m-r-10" onclick=\"roleEdit(\''+item.role_id+'\',\''+item.role_name+'\',\''+item.fk_permission_id+'\',\''+item.permission_ids+'\');\" data-animation="fadein" data-plugin="custommodal" data-overlaySpeed="200" data-overlayColor="#36404a"><i class="fa fa-pencil"></i></a><a href="#" onclick=\"roleDelete(\''+item.role_id+'\');\" class="on-danger text-danger" data-animation="fadein" data-plugin="custommodal" data-overlaySpeed="200" data-overlayColor="#36404a"><i class="fa fa-trash-o"></i></a></td>'
		            + "</tr>"
	        		$('#roleList').append(eachrow);
	        	});
	        	$('#datatable-responsive').DataTable();
       		}else{
       			$('#roleList').append("");
       			$('#datatable-responsive').DataTable({
					"language": {
						"emptyTable": "Roles not exist for listout"
					}
				});
       		}
		},
		error:function(req,status,msg){	
			alert("Error While Processing");
		}
	});
 }

function updateRole(roleId){
	//logoutcheck();
	var roleid=$(roleId).parent().attr('id');
	var role_name=$("#editrolename").val();
	var permissions=$("#editpermissions").val();
	//alert(role_name+" and "+permissions+" and "+roleid);
	if(role_name.trim().length==0){
		$("#editRoleError").html(VALIDATION_ROLE_NAME_EMPTY_ERROR);
		$("#editrolename").focus();
		setTimeout(function (){$("#editRoleError").html("");},5000);
		return false;
    }
	if(role_name.trim().length<3){
		$("#editRoleError").html(VALIDATION_ROLE_NAME_MIN_LENGTH_ERROR);
		$("#editrolename").focus();
		setTimeout(function (){$("#editRoleError").html("");},5000);return false;
	}
	if(role_name.trim().length>25  ){
		$("#editRoleError").html(VALIDATION_ROLE_NAME_MAX_LENGTH_ERROR);
		$("#editrolename").focus();
		setTimeout(function (){ $("#editRoleError").html("");},5000);
		return false;
	}
	if(!(IsName(role_name))){
		$("#editRoleError").html(VALIDATION_ROLE_NAME_NUMBER_ERROR);
		$("#editrolename").focus();
		setTimeout(function (){ $("#editRoleError").html("");},5000);
		return false;
	}
	if(permissions==null){
		$("#editRoleError").html(VALIDATION_PERMISSION_EMPTY_ERROR);
		$("#editpermissions").focus();
		setTimeout(function (){ $("#editRoleError").html("");},5000);
		return false;
	}
	var params = {
			id:roleid,
 			roleName : role_name ,
 			permissions : ""+permissions
	};
	var queryParam = jQuery.param(params);
	var appBasePath=getAppBasePath();
	$.ajax({
		async : true,
		type : "PUT",
		data:queryParam,
		dataType : "json",
		url : appBasePath + "/crud/updaterole",
		success : function(data) {
			//alert(data.statusCode + " >>>> " + data.message);
			if (data.statusCode !== 1) {
				$("#editRoleError").html(data.message);
				$("#role_name").focus();
				setTimeout(function() {$("#editRoleError").html("");}, 5000);
			} else {
				getMenuBarNavigation();
				roleLoader();
				$("#editRoleCancel").click();
				$("#role_name").val('');
			}
		},
		error : function(req, status, msg) {
			alert("error")
		}
	});
}
	
function roleEdit(roleId,rolename,permissions,permissionids){
	//logoutcheck();
	//var roleid=roleId;
	//alert(roleid+"roleid");
	if(rolename == 'Admin' || rolename == 'Resource'){
		$("#roleError").html("Default role : "+rolename+ " updation not allowed");
		setTimeout(function() {$("#roleError").html("");}, 5000);
	}else{
		$("#editrolename").val(rolename);
		permissionsForEdit(permissionids);
		$(".edit").attr("id",roleId);
		Custombox.open({
			target : "#custom-modal-edit",
			effect : "fadein"
		});
	}
}
 
function permissionsForEdit(permissionids){
	 //logoutcheck();
	 var appBasePath=getAppBasePath();
	 var str_array = permissionids.split(',');
	 $.ajax({ 
	  	  async:true,	
 		  type: "GET",
 		  dataType:"json",
 		  url: appBasePath+"/crud/getallpermissions", 
 		  success: function (data) { 
 			  //alert("all permissions"+data.length);
 			 var permissionOption="";
 			 for (var i = 0; i < data.length; i++) {
 				 permissionOption=permissionOption+"<option value='"+data[i].permission_id+"'>"+data[i].permission_name+"</option>";
 			 }
 			 $("select#editpermissions").html("");
 			 $("select#editpermissions").append(permissionOption);
 			 $('.selectpicker').selectpicker('val', str_array);
 			 $('.selectpicker').selectpicker('refresh');
 		  },
 		  error:function(req,status,msg){
 				 alert("error");
 		  }
 	});
 }
 
function permissionsLoader(){
	//logoutcheck();
	var appBasePath=getAppBasePath();
	$.ajax({ 
		async:true,	
		type: "GET",
		dataType:"json",
		url: appBasePath+"/crud/getallpermissions", 
		success: function (data) { 
			for (var i = 0; i < data.length; i++) {
				var permissionOption="<option value='"+data[i].permission_id+"'>"+data[i].permission_name+"</option>";
				$("#permissions").append(permissionOption); 
	 		}
			//alert($('.selectpicker').size())
			$('.selectpicker').last().selectpicker('refresh');
			//alert("sample");
		} ,
		error:function(req,status,msg){
			alert("error");
		}
	});
}

function createRole(){
	//alert("create role click");
	var l = $('#createRoleSave').ladda();
    l.ladda('start');
	//logoutcheck();
	var role_name=$("#role_name").val();
	var permissions=$("#permissions").val();
	if(role_name.trim().length==0){
		$("#createRoleError").html(VALIDATION_ROLE_NAME_EMPTY_ERROR);
		$("#role_name").focus();
		setTimeout(function (){$("#createRoleError").html("");},5000);
		l.ladda('stop');
		return false;
    }
	if(role_name.trim().length<3){	
		$("#createRoleError").html(VALIDATION_ROLE_NAME_MIN_LENGTH_ERROR);
		$("#role_name").focus();
		setTimeout(function (){$("#createRoleError").html("");},5000);
		l.ladda('stop');
		return false;
	}	
	if(role_name.trim().length>25  ){
		$("#createRoleError").html(VALIDATION_ROLE_NAME_MAX_LENGTH_ERROR);
		$("#role_name").focus();
		setTimeout(function (){$("#createRoleError").html("");},5000);
		l.ladda('stop');
		return false;
	}
 	if(!(IsName(role_name))){
 		$("#createRoleError").html(VALIDATION_ROLE_NAME_NUMBER_ERROR);
 		$("#role_name").focus();
 		setTimeout(function (){$("#createRoleError").html("");},5000);
 		l.ladda('stop');
 		return false;
 	}
 	if(permissions==null || permissions == 0){
 		$("#createRoleError").html(VALIDATION_PERMISSION_EMPTY_ERROR);
    	$("#permissions").focus();
    	setTimeout(function (){$("#createRoleError").html("");},5000);
    	l.ladda('stop');
    	return false;
 	}
 	// var formData=$("#createRoleForm").serialize();
 	//alert("formData"+formData);
 	var params = {
 			roleName : role_name ,
 			permissions : ""+permissions
	};
	var queryParam = jQuery.param(params);
 	var appBasePath=getAppBasePath();
 	$.ajax({ 
 		async:true,
 		type: "GET",
 		data:queryParam,
 		dataType:"json",
 		url: appBasePath+"/crud/addrole",
 		//data:formData,
 		success: function (data) { 
 			console.log(data);
 			if (data.statusCode == 1) {
				$('#createRoleError').html(data.message)
				$('#createRoleError').css('color', 'green');
				setTimeout(function (){$("#createRoleError").html("");$('#createRoleError').css('color', 'red');},4000);
				$("form#role_creation")[0].reset();
				$('.selectpicker').last().selectpicker('refresh');
				l.ladda('stop');
			} else {
				$('#createRoleError').html(data.message)
				$('#createRoleError').css('color', 'red');
				setTimeout(function (){$("#createRoleError").html("");},5000);
				l.ladda('stop');
			}
 			roleLoader();
			//Custombox.close();
 		 },
 		 error:function(req,status,msg){
 			l.ladda('stop');
 			 alert("error")
 		 }
 	});
}
 