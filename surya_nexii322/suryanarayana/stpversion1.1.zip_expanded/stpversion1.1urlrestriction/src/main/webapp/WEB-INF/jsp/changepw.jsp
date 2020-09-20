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
	List<String> menu=(List<String>)userSession.getAttribute("menuPermissions");
	if(menu!=null){
	System.out.print("menu-------"+menu);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>STP</title>

<!-- Plugin Css-->
<link rel="stylesheet" href="assets/plugins/magnific-popup/css/magnific-popup.css" />
<link rel="stylesheet" href="assets/plugins/jquery-datatables-editable/datatables.css" />
<link href="assets/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<link href="assets/css/core.css" rel="stylesheet" type="text/css" />
<link href="assets/css/components.css" rel="stylesheet" type="text/css" />
<link href="assets/css/icons.css" rel="stylesheet" type="text/css" />
<link href="assets/css/pages.css" rel="stylesheet" type="text/css" />
<link href="assets/css/responsive.css" rel="stylesheet" type="text/css" />

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
                        <%=utilitiesController.getMenuNavigationForActivePage("change_password",menu) %>
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
                                <!--<div class="btn-group pull-right m-t-15">
                                    <a href="#custom-modal-create" class="btn btn-default waves-effect waves-light text-info m-r-10" data-animation="fadein" data-plugin="custommodal" data-overlaySpeed="200" data-overlayColor="#36404a">Create User<span class="m-l-5"><i class="fa fa-user-plus"></i></span></a>
                                </div>-->
								<h4 class="page-title">Change Password</h4>
                                <ol class="breadcrumb">
                                  <!--<li> <a href="#">User Management</a> </li>-->
                                  <li class="active"> New Password </li>
                                </ol>
							</div>
						</div>


                        

                  <div class="row">
                             <div class="col-md-6">
								<div class="panel panel-color panel-custom">
									<div class="panel-heading">
										<h3 class="panel-title">Change Password</h3>
									</div>
									<div class="panel-body">
									
									<p>
										<div class="form-group p-b-10">
                                            <label class="col-md-4 control-label">Current Password</label>
                                            <div class="col-md-8">
                                                <input type="password" id="currentpwd" class="form-control" value="">
                                            </div>
                                        </div><br>
                                        <div class="form-group p-b-10">
                                            <label class="col-md-4 control-label">New Password</label>
                                            <div class="col-md-8">
                                                <input type="password" id="newpwd" class="form-control" value="">
                                            </div>
                                        </div><br>
                                        <div class="form-group p-b-10">
                                            <label class="col-md-4 control-label">Confirm Password</label>
                                            <div class="col-md-8">
                                                <input type="password" id="confirmpwd" class="form-control" value="">
                                            </div>
                                        </div><br>
                                        <div class="form-group p-b-10">
                                        <div class="col-md-4 col-md-offset-4">
                                            <button class="btn btn-purple text-uppercase waves-effect waves-light" onclick="changepassword();" type="submit">Save</button>
                                            </div>
                                        </div>
                                        <span style="float:left; color:red;" id="errormsg"></span>
										</p>
									</div>
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
                            <div class="col-md-12 text-right">
                                <button class="btn btn-primary waves-effect">Confirm</button>
                                <button class="btn btn-default waves-effect" onclick="Custombox.close();">Cancel</button>
                            </div>
                        </div>
                    </div>
			    </div>
			</div>
            <!-- end Modal -->
            
            
            
            <!-- Edit User MODAL -->
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
                        <label class="control-label pull-left">First Name</label> 
                        <input type="text" class="form-control" placeholder="First Name"> 
                        </div> 
                        </div> 
                        <div class="col-md-6"> 
                        <div class="form-group"> 
                        <label class="control-label pull-left">Last Name</label> 
                        <input type="text" class="form-control" placeholder="Last Name"> 
                        </div> 
                        </div> 
                        </div> 
 						<div class="row"> 
                        <div class="col-md-6"> 
                        <div class="form-group">
                        <label class="control-label pull-left">E-mail</label> 
                        <input type="text" class="form-control" placeholder="E-mail"> 
                        </div> 
                        </div> 
                        <div class="col-md-6"> 
                        <div class="form-group">
                        <label class="control-label pull-left">Emp No.</label>  
                        <input type="text" class="form-control" placeholder="Emp No."> 
                        </div> 
                        </div> 
                        </div>
                        <div class="row"> 
                        <div class="col-md-6"> 
                        <div class="form-group"> 
                        <label class="control-label pull-left">Select Role</label> 
                        <select class="selectpicker" multiple data-selected-text-format="count > 3" data-style="btn-white">
                            <option>Role One</option>
                            <option>Role Two</option>
                            <option>Role Three</option>
                            <option>Role Four</option>
                        </select> 
                        </div> 
                        </div> 
                        <div class="col-md-6"> 
                        <div class="form-group">
                        <label class="control-label pull-left">Reporting To</label>  
                        <select class="selectpicker" data-style="btn-white">
                            <option>R One</option>
                            <option>R Two</option>
                            <option>R Three</option>
                            <option>R Four</option>
                        </select>
                        </div> 
                        </div> 
                        </div>
                        <div class="row"> 
                        <div class="col-md-6"> 
                        <div class="form-group">
                        <label class="control-label pull-left">Location</label> 
                        <input type="text" class="form-control" placeholder="Location"> 
                        </div> 
                        </div> 
                        </div>
 						<div class="row m-t-20">
                            <div class="col-md-12 text-right">
                                <button class="btn btn-primary waves-effect">Save Changes</button>
                                <button class="btn btn-default waves-effect" onclick="Custombox.close();">Cancel</button>
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
			    <h4 class="custom-modal-title">Create User</h4>
			    <div class="custom-modal-text">
			        <div class="panel-body">
                        <div class="row"> 
                        <div class="col-md-6"> 
                        <div class="form-group">
                        <label class="control-label pull-left">First Name</label> 
                        <input type="text" class="form-control" placeholder="First Name"> 
                        </div> 
                        </div> 
                        <div class="col-md-6"> 
                        <div class="form-group"> 
                        <label class="control-label pull-left">Last Name</label> 
                        <input type="text" class="form-control" placeholder="Last Name"> 
                        </div> 
                        </div> 
                        </div> 
 						<div class="row"> 
                        <div class="col-md-6"> 
                        <div class="form-group">
                        <label class="control-label pull-left">E-mail</label> 
                        <input type="text" class="form-control" placeholder="E-mail"> 
                        </div> 
                        </div> 
                        <div class="col-md-6"> 
                        <div class="form-group">
                        <label class="control-label pull-left">Emp No.</label>  
                        <input type="text" class="form-control" placeholder="Emp No."> 
                        </div> 
                        </div> 
                        </div>
                        <div class="row"> 
                        <div class="col-md-6"> 
                        <div class="form-group"> 
                        <label class="control-label pull-left">Select Role</label> 
                        <select class="selectpicker" multiple data-selected-text-format="count > 3" data-style="btn-white">
                            <option>Role One</option>
                            <option>Role Two</option>
                            <option>Role Three</option>
                            <option>Role Four</option>
                        </select> 
                        </div> 
                        </div> 
                        <div class="col-md-6"> 
                        <div class="form-group">
                        <label class="control-label pull-left">Reporting To</label>  
                        <select class="selectpicker" data-style="btn-white">
                            <option>R One</option>
                            <option>R Two</option>
                            <option>R Three</option>
                            <option>R Four</option>
                        </select>
                        </div> 
                        </div> 
                        </div>
                        <div class="row"> 
                        <div class="col-md-6"> 
                        <div class="form-group">
                        <label class="control-label pull-left">Location</label> 
                        <input type="text" class="form-control" placeholder="Location"> 
                        </div> 
                        </div> 
                        </div>
 						<div class="row m-t-20">
                            <div class="col-md-12 text-right">
                                <button class="btn btn-primary waves-effect">Create</button>
                                <button class="btn btn-default waves-effect" onclick="Custombox.close();">Cancel</button>
                            </div>
                        </div>

                    </div>
			    </div>
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
	    <script src="assets/plugins/tiny-editable/mindmup-editabletable.js"></script>
	    <script src="assets/plugins/tiny-editable/numeric-input-example.js"></script>
	    
	    
	    <script src="assets/pages/datatables.editable.init.js"></script>
	    
	    <script>
			$('#mainTable').editableTableWidget().numericInputExample().find('td:first').focus();
			
		</script>
        
        <!-- Modal-Effect -->
        <script src="assets/plugins/custombox/js/custombox.min.js"></script>
        <script src="assets/plugins/custombox/js/legacy.min.js"></script>
        <link href="assets/plugins/custombox/css/custombox.css" rel="stylesheet">
        
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
        <script type="text/javascript" src="js/notifications.js"></script>
        <script type="text/javascript">
        //logoutcheck();
        getNotificationPanel();
       // getMenuBarNavigation();
        </script>
	</body>
</html>
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


