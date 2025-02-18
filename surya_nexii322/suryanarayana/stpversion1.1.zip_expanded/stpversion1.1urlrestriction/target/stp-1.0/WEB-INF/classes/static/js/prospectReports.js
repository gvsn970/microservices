
/***getting  all  Counts function *********/
function getContactCounts(){
	var appBasePath = getAppBasePath();
	var userId=$('#propsectUser').val();
	//alert(userId);
	$.ajax({
		type : "GET",
		dataType : "json",
		url : appBasePath + "/contact/getContactCount?userId="+userId,
		success : function(data) {
			if (data!=null) {
				$('.todayContactCount').text(data.todayCount);
				$('.monthlyContactCount').text(data.monthlyCount);
				$('.weeklyContactCount').text(data.weeklyCount);
				$('.customContactCount').text(data.dateFilterCount);
				$("#startDate").val(data.startDate);
				$("#endDate").val(data.endDate);
			}
		},
		error : function(req, status, msg) {
			alert("in error" + req + status + msg);
		}
	});
}
/****getting count for custom Dates*********/
function getFilterDateContactCount(){
	var appBasePath = getAppBasePath();
	var userId=$('#propsectUser').val();
	var startDate=$("#startDate").val();
	var endDate=$("#endDate").val();
	$("#distab").css("display","none");
	var params = {
			userId:userId,
			startDate:startDate,
			endDate:endDate
	};
	var queryParam = jQuery.param(params);
	$.ajax({
		type : "GET",
		dataType : "json",
		data:queryParam,
		url : appBasePath + "/contact/getFilterdDateContactCount",
		success : function(data) {
			//alert(data);
			if (data!=null) {
				$('.customContactCount').text(data);
			}
		},
		error : function(req, status, msg) {
			alert("in error" + req + status + msg);
		}
	});
}
/***List For Today's Contact List***/
function getTodayContactList(){
	var appBasePath = getAppBasePath();
	$('#datatable-responsive').dataTable().fnDestroy();
	var userId=$('#propsectUser').val();
	$.ajax({
		type : "GET",
		dataType : "json",
		url : appBasePath + "/contact/getTodayContactList?userId="+userId,
		success : function(data) {
			//alert(data);
			if (data.length > 0) {
				$('#prospectsList').html("");
				var createdOn=null;
				$.each(data,function(index, item) {
					createdOn=item.createdOn.split(" ");
					var eachrow = '<tr class="gradeX">'
						+ "<td>"+ item.prospectName+ "</td>"
						+ "<td>"+ item.companyName+ "</td>"
						+ "<td>"+ item.email+ "</td>"
						+ "<td>"+ item.phoneNumber+ "</td>"
						+ "<td>"+ createdOn[0]+ "</td>"
						+"<td>"+item.createdBy+ "</td>"
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
/******List For weekly Contact List**************/
function getWeeklyContactList()
{
	var appBasePath = getAppBasePath();
	$('#datatable-responsive').dataTable().fnDestroy();
	var userId=$('#propsectUser').val();
	$.ajax({
		type : "GET",
		dataType : "json",
		url : appBasePath + "/contact/getWeeklyContactList?userId="+userId,
		success : function(data) {
			//alert(data);
			if (data.length > 0) {
				var createdOn=null;
				$('#prospectsList').html("");
				$.each(data,function(index, item) {
					createdOn=item.createdOn.split(" ");
					var eachrow = '<tr class="gradeX">'
						+ "<td>"+ item.prospectName+ "</td>"
						+ "<td>"+ item.companyName+ "</td>"
						+ "<td>"+ item.email+ "</td>"
						+ "<td>"+ item.phoneNumber+ "</td>"
						+ "<td>"+ createdOn[0]+ "</td>"
						+"<td>"+item.createdBy+ "</td>"
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
/******List For Monthly Contact List *******/
function getMonthlyContactList(){
	var appBasePath = getAppBasePath();
	$('#datatable-responsive').dataTable().fnDestroy();
	var userId=$('#propsectUser').val();
	$.ajax({
		type : "GET",
		dataType : "json",
		url : appBasePath + "/contact/getMonthlyContactList?userId="+userId,
		success : function(data) {
			//alert(data);
			var createdOn=null;
			if (data.length > 0) {
				$('#prospectsList').html("");
				$.each(data,function(index, item) {
					createdOn=item.createdOn.split(" ");
					var eachrow = '<tr class="gradeX">'
						+ "<td>"+ item.prospectName+ "</td>"
						+ "<td>"+ item.companyName+ "</td>"
						+ "<td>"+ item.email+ "</td>"
						+ "<td>"+ item.phoneNumber+ "</td>"
						+ "<td>"+ createdOn[0]+ "</td>"
						+"<td>"+item.createdBy+ "</td>"
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
/****List For custom Date Contact********/
function getFilteredDateContactList(){
	var appBasePath = getAppBasePath();
	$('#datatable-responsive').dataTable().fnDestroy();
	var userId=$('#propsectUser').val();
	var startDate=$("#startDate").val();
	var endDate=$("#endDate").val();
	var createdOn=null;
	var params = {
			userId:userId,
			startDate:startDate,
			endDate:endDate
	};
	var queryParam = jQuery.param(params);
	$.ajax({
		type : "GET",
		dataType : "json",
		data:queryParam,
		url : appBasePath + "/contact/getFilteredDateContactList",
		success : function(data) {
			//alert(data);
			if (data.length > 0) {
				$('#prospectsList').html("");
				$.each(data,function(index, item) {
					createdOn=item.createdOn.split(" ");
					var eachrow = '<tr class="gradeX">'
						+ "<td>"+ item.prospectName+ "</td>"
						+ "<td>"+ item.companyName+ "</td>"
						+ "<td>"+ item.email+ "</td>"
						+ "<td>"+ item.phoneNumber+ "</td>"
						+ "<td>"+ createdOn[0]+ "</td>"
						+"<td>"+item.createdBy+ "</td>"
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
$( "#getTodayContactList" ).click(function() {
	showDiv('distab'); 
	$("#todayCount").css("border-bottom" ,"3px solid #06F");
	$("#weeklyCount").css("border-bottom" ,"");
	$("#monthlyCount").css("border-bottom" ,"");
	$("#customCount").css("border-bottom" ,"");
	getTodayContactList();
	});
$( "#getWeeklyContactList" ).click(function() {
	showDiv('distab'); 
	$("#weeklyCount").css("border-bottom" ,"3px solid #06F");
	$("#todayCount").css("border-bottom" ,"");
	$("#monthlyCount").css("border-bottom" ,"");
	$("#customCount").css("border-bottom" ,"");
	getWeeklyContactList();
	});
$( "#getMonthlyContactList" ).click(function() {
	showDiv('distab'); 
	$("#monthlyCount").css("border-bottom" ,"3px solid #06F");
	$("#todayCount").css("border-bottom" ,"");
	$("#weeklyCount").css("border-bottom" ,"");
	$("#customCount").css("border-bottom" ,"");
	getMonthlyContactList();
	});
$( "#getFilteredDateContactList" ).click(function() {
	showDiv('distab'); 
	$("#customCount").css("border-bottom" ,"3px solid #06F");
	$("#todayCount").css("border-bottom" ,"");
	$("#weeklyCount").css("border-bottom" ,"");
	$("#monthlyCount").css("border-bottom" ,"");
	getFilteredDateContactList();
	});