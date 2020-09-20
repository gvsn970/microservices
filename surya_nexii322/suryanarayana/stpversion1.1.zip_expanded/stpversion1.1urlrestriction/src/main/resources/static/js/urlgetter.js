function getAppBasePath(){
	var protocol=window.location.protocol;
	var host=window.location.host;
	var appRoot="";
	var baseURL=protocol+"//"+host+appRoot;
	//var protocol="http:";
	//var host="localhost:8080";
	//var appRoot="/";
	//var baseURL=protocol+"//"+host;
	//alert(baseURL);
	return baseURL;
}


