<%@page import="com.nexiilabs.stp.authentication.UtilitiesController"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html;charset=UTF-8" session="false"%>
<%! 
UtilitiesController utilitiesController=null;
List<String> menu=null;
%>
<%
	HttpSession userSession = request.getSession(false);
	System.out.print("before session check");
	if(userSession != null ){
		System.out.print("session is not null");
		utilitiesController=new UtilitiesController();
		menu=(List<String>)userSession.getAttribute("menuPermissions");
		if(menu!=null){
			System.out.print("menu-------"+menu);
			boolean status= utilitiesController.authorizationsCheck("My Profile",menu);
			System.out.print(status+"..................status");
			if(status==true){
		    	System.out.print("page allowed");
		
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>STP</title>


<link href="assets/plugins/dropzone/dropzone.css" rel="stylesheet" type="text/css" />
<!--Form Wizard-->
<link rel="stylesheet" type="text/css" href="assets/plugins/jquery.steps/css/jquery.steps.css" />

<link href="assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="assets/css/core.css" rel="stylesheet" type="text/css" />
<link href="assets/css/components.css" rel="stylesheet" type="text/css" />
<link href="assets/css/icons.css" rel="stylesheet" type="text/css" />
<link href="assets/css/pages.css" rel="stylesheet" type="text/css" />
<link href="assets/css/responsive.css" rel="stylesheet" type="text/css" />
<link href="assets/plugins/bootstrap-datepicker/css/bootstrap-datepicker.min.css" rel="stylesheet">

<!-- HTML5 Shiv and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/reskpond.js/1.3.0/respond.min.js"></script>
        <![endif]-->

<script src="assets/js/modernizr.min.js"></script>
</head>

<body class="fixed-left">

		<!-- Begin page -->
		<div id="wrapper">

            <!-- Top Bar Start -->
            <div class="topbar">

                <!-- LOGO -->
                <div class="topbar-left">
                    <div class="text-center">
                        <a class="logo">STP</a>
                    </div>
                </div>

                <!-- Button mobile view to collapse sidebar menu -->
                <div class="navbar navbar-default" role="navigation">
                    <div class="container">
                        <div class="">
                            <div class="pull-left">
                                <button class="button-menu-mobile open-left waves-effect waves-light">
                                    <i class="md md-menu"></i>
                                </button>
                                <span class="clearfix"></span>
                            </div>
                            <ul class="nav navbar-nav navbar-right pull-right" id="notification">
                                <li id="logout" class="dropdown top-menu-item-xs">
                                    <a href="#" class="dropdown-toggle profile waves-effect waves-light" data-toggle="dropdown" aria-expanded="true"><img src="assets/images/users/avatar-1.jpg" alt="user-img" class="img-circle"> </a>
                                    <ul class="dropdown-menu">
                                        <li><a href="changepw"><i class="ti-key m-r-10 text-custom"></i> Change Password</a></li>
                                        <li class="divider"></li>
                                        <li><a href="#" onclick="logout();"><i class="ti-power-off m-r-10 text-danger"></i> Logout</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                        <!--/.nav-collapse -->
                    </div>
                </div>
            </div>
            <!-- Top Bar End -->


            <!-- ========== Left Sidebar Start ========== -->

            <div class="left side-menu">
                <div class="sidebar-inner slimscrollleft">
                    <!--- Divider -->
                    <div id="sidebar-menu">
                      <%=utilitiesController.getMenuNavigationForActivePage("my_profile",menu) %>
                        <div class="clearfix"></div>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
			<!-- Left Sidebar End -->

			<!-- ============================================================== -->
			<!-- Start right Content here -->
			<!-- ============================================================== -->
			<div class="content-page">
				<!-- Start content -->
				<div class="content">
					<div class="container">

						<!-- Page-Title -->
						<div class="row">
							<div class="col-sm-12">
								<h4 class="page-title">My Profile</h4>
                                <ol class="breadcrumb">
                                  <li> <a href="#">STP</a> </li>
                                  <li class="active"> My Profile </li>
                                </ol>
							</div>
						</div>


                        

                  <div class="row">
                            <div class="col-md-12">
                            	<div class="card-box table-responsive">
                                    <div class="col-md-4"> 
                                   	 	<span id="empId" style="display:none;"></span>
                                            <div class="form-group">
                                            <label class="control-label pull-left">Employ Name</label> 
                                            <input type="text" id="empName" class="form-control" placeholder="Name Of Employ" required> 
                                            </div> 
                                            </div> 
                                            <div class="col-md-4"> 
                                            <div class="form-group"> 
                                            <label class="control-label pull-left">Email ID</label> 
                                            <input type="text" id="empMailId" class="form-control" placeholder="Ex: appaji.godugula@nexiilabs.com"> 
                                            </div> 
                                            </div>
                                            <div class="col-md-4"> 
                                            <div class="form-group">
                                            <label class="control-label pull-left">Contact Number</label> 
                                            <input type="text" id="empContact" class="form-control" placeholder="Ex: 8096237037"> 
                                            </div> 
                                            </div>
                                            <div class="col-md-4"> 
                                            <div class="form-group">
                                            <label class="control-label pull-left">Skill Set</label> 
                                            <input type="text" id="empSkill" class="form-control" placeholder="Ex: java"> 
                                            </div> 
                                            </div>
                                            <div class="col-md-4"> 
                                            <div class="form-group">
                                            <label class="control-label pull-left">Experience</label> 
                                            <input type="text" id="empExp" class="form-control" placeholder="Ex: 12+ Years"> 
                                            </div> 
                                            </div> 
                                            <div class="col-md-4"> 
                                            <div class="form-group m-t-15 p-t-10">
                                                <button class="btn btn-default waves-effect" onclick="updateProfileDetails();">Save Changes</button>
                                            </div> 
                                            </div> 
                                            <span id="error_msg"></span>
                            	</div>
                        	</div>
                    	</div> <!-- end Panel -->
                        
                        
                        


                    </div> <!-- container -->
                               
                </div> <!-- content -->

                <footer class="footer">
                    Â© 2017. All rights reserved.
                </footer>

            </div>
            <!-- ============================================================== -->
            <!-- End content here -->
            <!-- ============================================================== -->
            
            

        </div>
        <!-- END wrapper -->
    
        <script>
            var resizefunc = [];
        </script>

        <!-- jQuery  -->
        <!-- <script src="assets/js/jquery.min.js"></script> -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/detect.js"></script>
        <script src="assets/js/fastclick.js"></script>
        <script src="assets/js/jquery.slimscroll.js"></script>
        <script src="assets/js/jquery.blockUI.js"></script>
        <script src="assets/js/waves.js"></script>
        <script src="assets/js/wow.min.js"></script>
        <script src="assets/js/jquery.nicescroll.js"></script>
        <script src="assets/js/jquery.scrollTo.min.js"></script>


        <script src="assets/js/jquery.core.js"></script>
        <script src="assets/js/jquery.app.js"></script>

        <!-- Page Specific JS Libraries -->
        <script src="assets/plugins/dropzone/dropzone.js"></script>
        
        <!--Form Wizard-->
        <script src="assets/plugins/jquery.steps/js/jquery.steps.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="assets/plugins/jquery-validation/js/jquery.validate.min.js"></script>

        <!--wizard initialization-->
        <script src="assets/pages/jquery.wizard-init.js" type="text/javascript"></script>
        
        <!-- Date-Picker --> 
		<script src="assets/plugins/timepicker/bootstrap-timepicker.js"></script> 
        <script src="assets/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.min.js"></script> 
        <script src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script> 
        <script src="assets/plugins/clockpicker/js/bootstrap-clockpicker.min.js"></script> 
        <script src="assets/plugins/bootstrap-daterangepicker/daterangepicker.js"></script> 
        <script src="assets/pages/jquery.form-pickers.init.js"></script>
        
        <!-- Multi-Select -->
        <link href="assets/plugins/multiselect/css/multi-select.css"  rel="stylesheet" type="text/css" />
        <link href="assets/plugins/select2/css/select2.min.css" rel="stylesheet" type="text/css" />
        <link href="assets/plugins/bootstrap-select/css/bootstrap-select.min.css" rel="stylesheet" />
		<script type="text/javascript" src="assets/plugins/multiselect/js/jquery.multi-select.js"></script>
        <script type="text/javascript" src="assets/plugins/jquery-quicksearch/jquery.quicksearch.js"></script>
        <script src="assets/plugins/select2/js/select2.min.js" type="text/javascript"></script>
        <script src="assets/plugins/bootstrap-select/js/bootstrap-select.min.js" type="text/javascript"></script>
        <script type="text/javascript" src="assets/pages/jquery.form-advanced.init.js"></script>
         
        <script type="text/javascript" src="js/errorCodeConstants.js"></script>
        <script type="text/javascript" src="js/urlgetter.js"></script>
        <script type="text/javascript" src="js/changePassword.js"></script>
        <script type="text/javascript" src="js/login.js"></script>
        <script type="text/javascript" src="js/myprofile.js"></script>
        <script type="text/javascript" src="js/notifications.js"></script>
         <script type="text/javascript">
         $(document).ready(function () {
        	// alert("on load function");
        	//logoutcheck();
        	getNotificationPanel();
        	//getMenuBarNavigation();
        	getMyProfile();
	     });
	</script>
	</body>
</html>
<%
	}else {
		System.out.print("permission denied");
%>
<jsp:forward page="403.jsp"></jsp:forward>   
<%
	} 
%>
<%
}else {
	System.out.print("session is null");
%>
<jsp:forward page="login.jsp"></jsp:forward>   
<%
} 
%>
<%
}else {
	System.out.print("session is null");
%>
<jsp:forward page="login.jsp"></jsp:forward>   
<%
} 
%>