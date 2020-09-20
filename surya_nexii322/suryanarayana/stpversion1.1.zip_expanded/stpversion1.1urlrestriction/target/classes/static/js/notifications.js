function getNotificationPanel(){
	var appBasePath=getAppBasePath();
	$.ajax({
		type: "GET",
		url: appBasePath+"/notification/getNotificationPanel",
		success : function(data) {
			$("ul#notification").html(data);
		},
		error:function(req,status,msg){
			alert(msg);
		}
	});
}