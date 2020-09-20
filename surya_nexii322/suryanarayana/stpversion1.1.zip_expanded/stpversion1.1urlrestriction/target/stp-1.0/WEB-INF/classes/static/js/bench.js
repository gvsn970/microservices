function getBenchResourcesList(expireIn) {
	// alert("in Bench Resources");
	//logoutcheck();
	// alert("hello")
	var appBasePath = getAppBasePath();
	$.ajax({
		type : "Get",
		dataType : "json",
		url : appBasePath + "/bench/getBenchResourcesList/"+expireIn,
		success : function(data) {
			// alert(data);
			if (data.length > 0) {
				$('#bench_table').html("");
				var count = 0;
				$.each(data,function(index, item) {
					var eachrow = '<tr class="gradeX">'
					+ "<td>"+ item.resourceName+ "</td>"
					+ "<td>"+ item.resourceEmail+ "</td>"
					+ "<td>"+ item.resourceExperience+ "</td>"
					+ "<td>"+ item.resourceSkillSet+ "</td>"
					+ "<td>"+ item.resourceProjectName+ "</td>"
					+ "<td>"+ item.resourceExpiryStatus+ "</td>";
					if(item.resourceExpiryStatus=="expired"){
						//eachrow=eachrow+'<td class="actions"><a href="#" class="on-default text-info m-r-10"  onclick=\"resourceAssign(\''+ item.resourceId+ '\');\" data-animation="fadein" data-plugin="custommodal" data-overlaySpeed="200" data-overlayColor="#36404a"><i class="fas fa-undo"></a></td>';
					
						eachrow=eachrow + "<td class=\"actions\" align=\"middle\"><a href=\"#\" class=\"on-default text-info m-r-10\" onclick=\"resourceAssign('"+ item.resourceId+ "');\" data-animation=\"fadein\" data-plugin=\"custommodal\" data-overlaySpeed=\"200\" data-overlayColor=\"#36404a\"><i class=\"fa fa-undo fa-2x\" " 
			                + " data-toggle=\"tooltip\" data-placement=\"top\" title=\"Reassign resource.\""
			                +"></i></a>" +"</td>";
					
					}else{
						eachrow=eachrow + "<td class=\"actions\" align=\"middle\"><a href=\"#\" style=\"pointer-events: none; cursor: default; color:gray;\" disabled=\"true\" class=\"on-default text-info m-r-10\" onclick=\"resourceAssign('"+ item.resourceId+ "');\" data-animation=\"fadein\" data-plugin=\"custommodal\" data-overlaySpeed=\"200\" data-overlayColor=\"\"><i class=\"fa fa-undo fa-2x\" " 
		                + " data-toggle=\"tooltip\" data-placement=\"top\" title=\"Reassign resource.\""
		                +"></i></a>" +"</td>";
						}
					eachrow=eachrow+ '</tr>';
					$('#bench_table').append(eachrow);
				});
				$('#datatable-responsive').DataTable();
			} else {
				$('#bench_table').html("");
				$('#datatable-responsive').DataTable({
					"language": {
						"emptyTable": "Bench resources not exist for listout"
					}
				});
			}
		},
		error : function(req, status, msg) {
			alert("in error")
		}
	});
}


$("#expire_in_select").change(function(){
	$('#bench_table').html("");
	$("#datatable-responsive").dataTable().fnDestroy();
	var expireInSelect=$("#expire_in_select").val();
	getBenchResourcesList(expireInSelect);
});

function resourceAssign(resourceId){
	
	//alert(resourceId+"=resourceId");
	
	var appBasePath = getAppBasePath();
	$.ajax({
		type : "POST",
		dataType : "json",
		url : appBasePath + "/bench/expiredResourceReassign?resourceId="+ resourceId,
		success : function(data) {
			console.log(data);
			// alert(data.message);
			 $("#status_msg").show();
			if (data.statusCode == 1) 
			{
				$('#status_msg').css('color', 'green');
				$('#status_msg').html(data.message);
				getNotificationPanel();
			}
			else
			{
				$('#status_msg').css('color', 'red');
				$('#status_msg').html(data.message);
			}
			setTimeout(function() {
				$("#status_msg").html("");
			}, 5000);
			getBenchResourcesList(0);
			$("#datatable-responsive").dataTable().fnDestroy();
		},
		error : function(req, status, msg) {
			alert("in sow error" + req);
		}
	});
}


