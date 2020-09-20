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
			boolean status= utilitiesController.authorizationsCheck("User Management",menu);
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
<!-- Ladda buttons css -->
<link href="assets/plugins/ladda-buttons/css/ladda-themeless.min.css" rel="stylesheet" type="text/css" />

<!-- Plugin Css-->
<link rel="stylesheet" href="assets/plugins/magnific-popup/css/magnific-popup.css" />
<link rel="stylesheet" href="assets/plugins/jquery-datatables-editable/datatables.css" />
<link href="assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="assets/css/core.css" rel="stylesheet" type="text/css" />
<link href="assets/css/components.css" rel="stylesheet" type="text/css" />
<link href="assets/css/icons.css" rel="stylesheet" type="text/css" />
<link href="assets/css/pages.css" rel="stylesheet" type="text/css" />
<link href="assets/css/responsive.css" rel="stylesheet" type="text/css" />
<link href="assets/plugins/custombox/css/custombox.css" rel="stylesheet">
<link href="assets/plugins/bootstrap-select/css/bootstrap-select.min.css" rel="stylesheet" />
<!-- HTML5 Shiv and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
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
                        <a  class="logo">STP</a>
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
                        <%=utilitiesController.getMenuNavigationForActivePage("roles",menu) %>
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
                                <div class="btn-group pull-right m-t-15">
                                    <a href="#custom-modal-create" class="btn btn-default waves-effect waves-light text-info m-r-10" data-animation="fadein" data-plugin="custommodal" data-overlaySpeed="200" data-overlayColor="#36404a">Create Role<span class="m-l-5"><i class="ion-checkmark-circled"></i></span></a>
                                </div>
								<h4 class="page-title">Roles</h4>
                                <ol class="breadcrumb">
                                  <li> <a href="#">User Management</a> </li>
                                  <li class="active"> Roles </li>
                                </ol>
                                <span style="color: red;" id="roleError"></span>
							</div>
						</div>


                        

                  <div class="row">
                    <div class="col-sm-12">
                        <div class="card-box table-responsive">
                             <table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
	                                    <thead>
	                                        <tr>
	                                            <th>Role Name</th>
	                                            <th>Menu Permissions</th>
	                                           <!--  <th>Role Band</th> -->
	                                            <th>Actions</th>
	                                        </tr>
	                                    </thead>
	                                    <tbody id="roleList">
	                                     
	                                    </tbody>
	                                </table>
                                </div>
                            </div>
                            <!-- end: page -->

                        </div> <!-- end Panel -->
                        
                        
                        


                    </div> <!-- container -->
                               
                </div> <!-- content -->

                <footer class="footer">
                    Â© 2017. All rights reserved.
                </footer>

            </div>
            <!-- ============================================================== -->
            <!-- End Right content here -->
            <!-- ============================================================== -->
            
            <!-- Delete MODAL -->
            <div id="custom-modal" class="modal-demo">
			    <button type="button" class="close" onclick="Custombox.close();">
			        <span>&times;</span><span class="sr-only">Close</span>
			    </button>
			    <h4 class="custom-modal-title">Delete Row</h4>
			    <div class="custom-modal-text">
			        <div class="panel-body">
                        <div class="modal-wrapper">
                            <div class="modal-text">
                                <p>Are you sure that you want to delete this row?</p>
                            </div>
                        </div>

                        <div class="row m-t-20">
                            <div class="col-md-12 text-right delete">
                                <button class="btn btn-primary waves-effect" onclick="deleteRole(this);">Confirm</button>
                                <button id="roleDeleteCancel" class="btn btn-default waves-effect" onclick="Custombox.close();">Cancel</button>
                            </div>
                        </div>
                    </div>
			    </div>
			</div>
            <!-- end Modal -->
            
            
          <!--   <div id="updateRole"></div> -->
            <!-- Edit Role MODAL -->
            <div id="custom-modal-edit" class="modal-demo">
			    <button type="button" class="close" onclick="Custombox.close();">
			        <span>&times;</span><span class="sr-only">Close</span>
			    </button>
			    <h4 class="custom-modal-title">Edit Details</h4>
			    <div class="custom-modal-text">
			        <div class="panel-body">
                        <div class="row"> 
                        <div class="col-md-6"> 
                        <div class="form-group">
                        <label class="control-label pull-left">Role Name</label> 
                        <input type="text" class="form-control" placeholder="Role Name" id="editrolename"> 
                        </div> 
                        </div> 
                        <div class="col-md-6"> 
                        <div class="form-group"> 
                        <label class="control-label pull-left">Select Permissions</label> 
                        <select class="selectpicker"  multiple data-selected-text-format="count > 3" data-style="btn-white" id="editpermissions">
                        </select> 
                        </div> 
                        </div>
                        </div> 
 						
                       <!--  <div class="row"> 
                        <div class="col-md-6"> 
                        <div class="form-group">
                        <label class="control-label pull-left">Role Band</label> 
                        <input type="text" class="form-control" placeholder="Role Band"> 
                        </div> 
                        </div> 
                        </div> -->
 						<div class="row m-t-20">
 						<div class="col-md-6 text-left">
                            <span style="color: red;" id="editRoleError"></span>
                            </div>
                            <div class="col-md-6 text-right edit">
                                <button class="btn btn-primary waves-effect" onclick="updateRole(this);">Save Changes</button>
                                <button class="btn btn-default waves-effect" onclick="Custombox.close();" id="editRoleCancel">Cancel</button>
                            </div>
                        </div>

                    </div>
			    </div>
			</div>
            <!-- end Modal -->
            
            <!-- Create User MODAL -->
            <div id="custom-modal-create" class="modal-demo">
			    <button type="button" class="close" onclick="Custombox.close();">
			        <span>&times;</span><span class="sr-only">Close</span>
			    </button>
			    <h4 class="custom-modal-title">Create Role</h4>
			   <!--  <form id="createRoleForm"> -->
			    <div class="custom-modal-text">
			        <div class="panel-body">
			        <form id="role_creation">
                        <div class="row"> 
                        <div class="col-md-6"> 
                        <div class="form-group">
                        <label class="control-label pull-left">Role Name</label> 
                        <input type="text" class="form-control" placeholder="Role Name" id="role_name" name="role_name"> 
                        </div> 
                        </div> 
                        <div class="col-md-6"> 
                        <div class="form-group"> 
                        <label class="control-label pull-left">Select Permissions</label> 
                        <select class="selectpicker" multiple data-selected-text-format="count > 3" data-style="btn-white" id="permissions" name="fk_permission_id">
                        </select> 
                        </div> 
                        </div>
                        </div> 
 						
                       <!--  <div class="row"> 
                        <div class="col-md-6"> 
                        <div class="form-group">
                        <label class="control-label pull-left">Role Band</label> 
                        <input type="text" class="form-control" placeholder="Role Band"> 
                        </div> 
                        </div> 
                        </div> -->
 						<div class="row m-t-20">
 						  <div class="col-md-6 text-left">
                          		<span style="color: red;" id="createRoleError"></span>
                          	</div>
                            <div class="col-md-6 text-right">
                          <!--   <div class="col-md-12 text-right"> -->
                                <button type="button"  class="ladda-button  btn btn-info" data-style="slide-left" onclick="createRole();" id="createRoleSave">Create</button>
                                <button type="button" class="btn btn-default waves-effect" onclick="Custombox.close();" id="createRoleCancel">Cancel</button>
                            </div>
                        </div>
						</form>
                    </div>
			    </div>
			   <!--  </form> -->
			</div>
            <!-- end Modal -->
            
            

            
            

        </div>
        <!-- END wrapper -->
    
        <script>
            var resizefunc = [];
        </script>

        <!-- jQuery  -->
        <script src="assets/js/jquery.min.js"></script>
        
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

	    <!-- Examples -->
	    <script src="assets/plugins/magnific-popup/js/jquery.magnific-popup.min.js"></script>
	    <script src="assets/plugins/jquery-datatables-editable/jquery.dataTables.js"></script> 
	    <script src="assets/plugins/datatables/dataTables.bootstrap.js"></script>
	    <!-- <script src="assets/plugins/tiny-editable/mindmup-editabletable.js"></script>
	    <script src="assets/plugins/tiny-editable/numeric-input-example.js"></script>
	    
	    
	    <script src="assets/pages/datatables.editable.init.js"></script> -->
	    
	    <script>
			//$('#mainTable').editableTableWidget().numericInputExample().find('td:first').focus();
			
		</script>
		<!-- 	Modal-Effect  -->
        <script src="assets/plugins/custombox/js/custombox.min.js"></script>
        <script src="assets/plugins/custombox/js/legacy.min.js"></script>
         <!----Ladda button----->
        <script src="assets/plugins/ladda-buttons/js/spin.min.js"></script>
        <script src="assets/plugins/ladda-buttons/js/ladda.min.js"></script>
        <script src="assets/plugins/ladda-buttons/js/ladda.jquery.min.js"></script>
        
         <!-- Multi-Select -->
        <link href="assets/plugins/multiselect/css/multi-select.css"  rel="stylesheet" type="text/css" />
        <link href="assets/plugins/select2/css/select2.min.css" rel="stylesheet" type="text/css" />
        
		<!-- <script type="text/javascript" src="assets/plugins/multiselect/js/jquery.multi-select.js"></script>
		 <script src="assets/plugins/select2/js/select2.min.js" type="text/javascript"></script> -->
		 <script type="text/javascript" src="assets/plugins/jquery-quicksearch/jquery.quicksearch.js"></script>
       <!-- <script type="text/javascript" src="https://code.jquery.com/jquery-2.1.4.min.js"></script> -->
        
       <!--  <script type="text/javascript" src="assets/pages/jquery.form-advanced.init.js"></script> -->
       <!-- <script src="assets/js/jquery.min.js"></script>  -->
        
        <script type="text/javascript" src="js/role.js"></script>
        <script type="text/javascript" src="js/errorCodeConstants.js"></script>
        <script type="text/javascript" src="js/urlgetter.js"></script>
        <script type="text/javascript" src="js/changePassword.js"></script>
        <script type="text/javascript" src="js/login.js"></script>
        <script type="text/javascript" src="js/notifications.js"></script>
         <script type="text/javascript">
         $(document).ready(function () {
        	/*  $("#role_name").focus(); */
        	//logoutcheck();
        	getNotificationPanel();
        	//getMenuBarNavigation();
         	roleLoader();
         	permissionsLoader();
	     });
	</script>
	
       <script src="assets/plugins/bootstrap-select/js/bootstrap-select.min.js" type="text/javascript"></script> 
       
		<!-- <script type="text/javascript" src="assets/plugins/multiselect/js/jquery.multi-select.js"></script> -->
		<!-- <script src="assets/plugins/select2/js/select2.min.js" type="text/javascript"></script> -->
        
       <!--  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>  -->
        

        <!--  -->
        
         
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