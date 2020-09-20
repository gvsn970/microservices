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
		boolean status= utilitiesController.authorizationsCheck("Accounts",menu);
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
<link href="assets/plugins/dropzone/dropzone.css" rel="stylesheet" type="text/css" />
<!--Form Wizard-->
<link rel="stylesheet" type="text/css" href="assets/plugins/jquery.steps/css/jquery.steps.css" />
<!-- Plugin Css-->
<link rel="stylesheet" href="assets/plugins/magnific-popup/css/magnific-popup.css" />
<link rel="stylesheet" href="assets/plugins/jquery-datatables-editable/datatables.css" />
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
        <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->

<script src="assets/js/modernizr.min.js"></script>
<style>
.custombox-modal-container.custombox-modal-container-fadein {
	width: 980px !important;
}
.wizard > .content > .body label{
	margin-top:0px;
	}
</style>
</head>

<body class="fixed-left">

<!-- Begin page -->
<div id="wrapper"> 
  
  <!-- Top Bar Start -->
  <div class="topbar"> 
    
    <!-- LOGO -->
    <div class="topbar-left">
      <div class="text-center"> <a class="logo">STP</a> </div>
    </div>
    
    <!-- Button mobile view to collapse sidebar menu -->
    <div class="navbar navbar-default" role="navigation">
      <div class="container">
        <div class="">
          <div class="pull-left">
            <button class="button-menu-mobile open-left waves-effect waves-light"> <i class="md md-menu"></i> </button>
            <span class="clearfix"></span> </div>
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
        <%=utilitiesController.getMenuNavigationForActivePage("accounts",menu)
                     %>
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
            <div class="btn-group pull-right m-t-15"> <a href="#custom-modal-create" class="btn btn-default waves-effect waves-light text-info m-r-10" data-animation="fadein" data-plugin="custommodal" data-overlaySpeed="200" data-overlayColor="#36404a">Create Account<span class="m-l-5"><i class="fa fa-user-plus"></i></span></a> </div>
            <h4 class="page-title">Accounts</h4>
            <ol class="breadcrumb">
              <li class="active">Account Details </li>
              <li class="active"> Create Account </li>
            </ol>
          </div>
        </div>
        <div class="row">
          <div class="col-sm-12">
            <div class="card-box table-responsive">
              <table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
                <thead>
                  <tr>
                    <th>Company Name</th>
                    <th>Company Location</th>
                    <th>Contact Number</th>
                    <th>Contact Person Name</th>
                    <th>Actions</th>
                  </tr>
                </thead>
                <tbody id="company_accounts_table">
                 
                </tbody>
              </table>
            </div>
          </div>
          <!-- end: page --> 
          
        </div>
        <!-- end Panel --> 
        
      </div>
      <!-- container --> 
      
    </div>
    <!-- content -->
    
    <footer class="footer"> © 2017. All rights reserved. </footer>
  </div>
  <!-- ============================================================== --> 
  <!-- End Right content here --> 
  <!-- ============================================================== --> 
  
  <!-- Delete MODAL -->
  <div id="custom-modal" class="modal-demo">
    <button type="button" class="close" onclick="Custombox.close();"> <span>&times;</span><span class="sr-only">Close</span> </button>
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
            <button class="btn btn-primary waves-effect" id="confirm">Confirm</button>
            <button class="btn btn-default waves-effect" onclick="Custombox.close();">Cancel</button>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- end Modal --> 
  
  <!-- Edit Customer MODAL -->
  <div id="custom-modal-edit" class="modal-demo">
    <button type="button" class="close" onclick="Custombox.close();"> <span>&times;</span><span class="sr-only">Close</span> </button>
    <h4 class="custom-modal-title">Edit Details</h4>
    <div class="custom-modal-text">
      <div class="panel-body">
        <div class="col-md-12">
          <div class="card-box">
            <form id="update-form" enctype="multipart/form-data">
            <div id="basic-form-add-res_update">
            <h3>Basic Info</h3>
            <section>
              <div class="row">
                <div class="col-md-6">
                  <div class="form-group">
                    <label class="control-label pull-left">Company Name</label>
                    <input type="text" class="form-control" placeholder="Ex: TCS" id="company_account_name_u">
                    <input type="hidden" id="companyId">
                  </div>
                </div>
                <div class="col-md-6">
                  <div class="form-group">
                    <label class="control-label pull-left">Company Location</label>
                    <input type="text" class="form-control" placeholder="Ex: Hyderabad" id="company_account_location_u">
                  </div>
                </div>
                <div class="col-md-6">
                  <div class="form-group">
                    <label class="control-label pull-left">Contact Number</label>
                    <input type="text" class="form-control" placeholder="Ex: 8096237037" id="company_account_contact_no_u">
                  </div>
                </div>
                <div class="col-md-6">
                  <div class="form-group">
                    <label class="control-label pull-left">Contact Person</label>
                    <input type="text" class="form-control" placeholder="Ex: Mahesh Dutt" id="company_account_contact_person_u">
                  </div>
                </div>
              </div>
            </section>
            <!--Teb Completed-->
            <h3>Agreement Docs</h3>
            <section>
              <div class="row">
                <div class="col-md-12">
                      <div class="col-md-6">
                        <div class="form-group">
                          <input type="file" name="files" class="filestyle" data-classButton="btn btn-primary" data-input="false" data-classIcon="icon-plus" data-buttonTextt="Upload Files" multiple  accept=".doc,.pdf,.docx">
                        </div>
                      </div>
                      <div class="col-md-6">
                        <table class="tablesaw table m-b-0 table-bordered">
                          <thead>
                            <tr>
                              <th>File Name</th>
                              <th>Delete</th>
                            </tr>
                          </thead>
                          <tbody id="agreement_files_list">
                            
                          </tbody>
                        </table>
                      </div>
                    </div>
              </div>
            </section>
            <!--Teb Completed-->
            <h3>Address</h3>
            <section>
              <div class="row">
               <div class="col-md-6">
                      <div class="form-group">
                        <label class="control-label pull-left">Shipping Address</label>
                        <input type="text" class="form-control" style="border-left: none;border-right: none;border-top: none;" placeholder="Address Lane1" maxlength="75" id="company_account_shippingAddressLane1_u">
                      	<input type="text" class="form-control" style="border-left: none;border-right: none;border-top: none;" placeholder="Address Lane2" maxlength="75" id="company_account_shippingAddressLane2_u">
                        <input type="text" class="form-control" style="border-left: none;border-right: none;border-top: none;" placeholder="State" maxlength="20" id="company_account_shippingAddressState_u">
                        <input type="text" class="form-control" style="border-left: none;border-right: none;border-top: none;" placeholder="Pin Code" maxlength="10" id="company_account_shippingAddressPinCode_u">
                        </div>
                </div> 
                <div class="col-md-6">
                      <div class="form-group">
                        <label class="control-label pull-left">Vendor Address</label>
                        <input type="text" class="form-control" style="border-left: none;border-right: none;border-top: none;" placeholder="Address Lane1" maxlength="75" id="company_account_vendorAddressLane1_u">
                      	<input type="text" class="form-control" style="border-left: none;border-right: none;border-top: none;" placeholder="Address Lane2" maxlength="75" id="company_account_vendorAddressLane2_u">
                        <input type="text" class="form-control" style="border-left: none;border-right: none;border-top: none;" placeholder="State" maxlength="20" id="company_account_vendorAddressState_u">
                        <input type="text" class="form-control" style="border-left: none;border-right: none;border-top: none;" placeholder="Pin Code" maxlength="10" id="company_account_vendorAddressPinCode_u">
                        </div>
                </div>
                <div class="col-md-6">
                      <div class="form-group">
                        <label class="control-label pull-left">Billing Address</label>
                        <input type="text" class="form-control" style="border-left: none;border-right: none;border-top: none;" placeholder="Address Lane1" maxlength="75" id="company_account_billingAddressLane1_u">
                      	<input type="text" class="form-control" style="border-left: none;border-right: none;border-top: none;" placeholder="Address Lane2" maxlength="75" id="company_account_billingAddressLane2_u">
                        <input type="text" class="form-control" style="border-left: none;border-right: none;border-top: none;" placeholder="State" maxlength="20" id="company_account_billingAddressState_u">
                        <input type="text" class="form-control" style="border-left: none;border-right: none;border-top: none;" placeholder="Pin Code" maxlength="10" id="company_account_billingAddressPinCode_u">
                        </div>
                </div>
              </div>
            </section>
            <!--Teb Completed-->
            <h3>TAX Info</h3>
           <section>
              <div class="row">
                <div class="col-md-6">
                  <div class="form-group">
                    <label class="control-label pull-left">PAN</label>
                    <input type="text" class="form-control" placeholder="Ex: CWTX9807C" id="company_account_pan_u">
                  </div>
                </div>
                <div class="col-md-6">
                  <div class="form-group">
                    <label class="control-label pull-left">GSTIN</label>
                    <input type="text" class="form-control" placeholder="Ex: TS87562017" id="company_account_gstin_u">
                  </div>
                </div>
                <div class="col-md-6">
                  <div class="form-group">
                    <label class="control-label pull-left">State</label>
                    <input type="text" class="form-control" placeholder="Ex: Telangana" id="company_account_state_u">
                  </div>
                </div>
                <div class="col-md-6">
                  <div class="form-group">
                    <label class="control-label pull-left">State Code</label>
                    <input type="text" class="form-control" placeholder="Ex: 36" id="company_account_state_code_u">
                  </div>
                </div>
              </div>
            </section>
          </div>
          </form>
        </div>
        <div class="row m-t-20">
          <div class="col-md-6 text-left">
            <!-- <button class="ladda-button btn btn-info" data-style="slide-left" id="save">Save Changes</button> -->
            <button class="btn btn-default " onclick="Custombox.close();">Cancel</button>
            <span id="updateCompanyStatusMsg"></span>
          </div>
          <div class="col-md-6 text-right">
          
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<!-- end Modal --> 

<!-- Create Customer MODAL -->
<div id="custom-modal-create" class="modal-demo">
  <button type="button" class="close" onclick="Custombox.close();"> <span>&times;</span><span class="sr-only">Close</span> </button>
  <h4 class="custom-modal-title">Create Account</h4>
  <div class="custom-modal-text">
    <div class="panel-body">
      <div class="col-md-12">
          <div class="card-box">
           <form id="form1" enctype="multipart/form-data">
            <div id="basic-form-add-res">
            <h3>Basic Info</h3>
            <section>
              <div class="row">
                <div class="col-md-6">
                  <div class="form-group">
                    <label class="control-label pull-left">Company Name</label>
                    <input type="text" class="form-control" placeholder="Ex: TCS" id="company_account_name">
                  </div>
                </div>
                <div class="col-md-6">
                  <div class="form-group">
                    <label class="control-label pull-left">Company Location</label>
                    <input type="text" class="form-control" placeholder="Ex: Hyderabad" id="company_account_location">
                  </div>
                </div>
                <div class="col-md-6">
                  <div class="form-group">
                    <label class="control-label pull-left">Contact Number</label>
                    <input type="text" class="form-control" placeholder="Ex: 8096237037" id="company_account_contact_no">
                  </div>
                </div>
                <div class="col-md-6">
                  <div class="form-group">
                    <label class="control-label pull-left">Contact Person</label>
                    <input type="text" class="form-control" placeholder="Ex: Prasanth Rana" id="company_account_contact_person">
                  </div>
                </div>
              </div>
            </section>
            <!--Teb Completed-->
            <h3>Agreement Docs</h3>
            <section>
              <div class="row">
                <div class="col-md-12">
                  <div class="form-group">
                   	<label class="control-label pull-left">Upload Agreement  </label>
                    <input name="files" id="agreementFile" type="file"class="filestyle" data-classButton="btn btn-primary" data-input="false" data-classIcon="icon-plus" data-buttonTextt="Upload Files" multiple accept=".doc,.pdf,.docx" />
                  </div>
                </div>
              </div>
            </section>
            <!--Teb Completed-->
            <h3>Address</h3>
            <section>
              <div class="row">
               <div class="col-md-6">
                      <div class="form-group">
                        <label class="control-label pull-left">Shipping Address</label>
                        <input type="text" class="form-control" style="border-left: none;border-right: none;border-top: none;" placeholder="Address Lane1" maxlength="75" id="company_account_shippingAddressLane1">
                      	<input type="text" class="form-control" style="border-left: none;border-right: none;border-top: none;" placeholder="Address Lane2" maxlength="75" id="company_account_shippingAddressLane2">
                        <input type="text" class="form-control" style="border-left: none;border-right: none;border-top: none;" placeholder="State" maxlength="20" id="company_account_shippingAddressState">
                        <input type="text" class="form-control" style="border-left: none;border-right: none;border-top: none;" placeholder="Pin Code" maxlength="10" id="company_account_shippingAddressPinCode">
                        </div>
                </div> 
                <div class="col-md-6">
                      <div class="form-group">
                        <label class="control-label pull-left">Vendor Address</label>
                        <input type="text" class="form-control" style="border-left: none;border-right: none;border-top: none;" placeholder="Address Lane1" maxlength="75" id="company_account_vendorAddressLane1">
                      	<input type="text" class="form-control" style="border-left: none;border-right: none;border-top: none;" placeholder="Address Lane2" maxlength="75" id="company_account_vendorAddressLane2">
                        <input type="text" class="form-control" style="border-left: none;border-right: none;border-top: none;" placeholder="State" maxlength="20" id="company_account_vendorAddressState">
                        <input type="text" class="form-control" style="border-left: none;border-right: none;border-top: none;" placeholder="Pin Code" maxlength="10" id="company_account_vendorAddressPinCode">
                        </div>
                </div>
                <div class="col-md-6">
                      <div class="form-group">
                        <label class="control-label pull-left">Billing Address</label>
                        <input type="text" class="form-control" style="border-left: none;border-right: none;border-top: none;" placeholder="Address Lane1" maxlength="75" id="company_account_billingAddressLane1">
                      	<input type="text" class="form-control" style="border-left: none;border-right: none;border-top: none;" placeholder="Address Lane2" maxlength="75" id="company_account_billingAddressLane2">
                        <input type="text" class="form-control" style="border-left: none;border-right: none;border-top: none;" placeholder="State" maxlength="20" id="company_account_billingAddressState">
                        <input type="text" class="form-control" style="border-left: none;border-right: none;border-top: none;" placeholder="Pin Code" maxlength="10" id="company_account_billingAddressPinCode">
                        </div>
                </div>
              </div>
            </section>
            <!--Teb Completed-->
             <h3>TAX Info</h3>
            <section>
              <div class="row">
                <div class="col-md-6">
                  <div class="form-group">
                    <label class="control-label pull-left">PAN</label>
                    <input type="text" class="form-control" placeholder="Ex: CWTX9807C" id="company_account_pan">
                  </div>
                </div>
                <div class="col-md-6">
                  <div class="form-group">
                    <label class="control-label pull-left">GSTIN</label>
                    <input type="text" class="form-control" placeholder="Ex: TS87562017" id="company_account_gstin">
                  </div>
                </div>
                <div class="col-md-6">
                  <div class="form-group">
                    <label class="control-label pull-left">State</label>
                    <input type="text" class="form-control" placeholder="Ex: Telangana" id="company_account_state">
                  </div>
                </div>
                <div class="col-md-6">
                  <div class="form-group">
                    <label class="control-label pull-left">State Code</label>
                    <input type="text" class="form-control" placeholder="Ex: 36" id="company_account_state_code">
                  </div>
                </div>
              </div>
            </section>
          </div>
           </form>
        </div>
        <div class="row m-t-20">
          <div class="col-md-6 text-left">
          <!--   <button class="ladda-button btn btn-info" type="button" data-style="slide-left" id="create_company_account">Create Account</button> -->
            <button class="btn btn-default waves-effect" type="button" onclick="Custombox.close();">Cancel</button>
             <span id="createCompanyStatusMsg"></span>
          </div>
           <div class="col-md-6 text-right">
          
          </div>
        </div>
      </div>
  </div>
</div>
</div>
<!-- end Modal -->

</div>
<!-- END wrapper --> 
        <!--Preview Modal-->
        <div id="custom-modal-preview" class="modal-demo">
			    <button type="button" class="close" onclick="Custombox.close();">
			        <span>&times;</span><span class="sr-only">Close</span>
			    </button>
			    <h4 class="custom-modal-title">Preview</h4>
			    <div class="custom-modal-text">
			        <div class="panel-body">
                     <div class="row">
                     			<div class="col-lg-12">
								<div class="panel panel-color panel-custom table-bordered">
									<div class="panel-heading">
										<h3 class="panel-title text-left">Basic Info</h3>
									</div>
									<div class="panel-body text-left" id="basicInfoPreview">
									</div>
								</div>
							</div>
							<div class="col-lg-12">
								<div class="panel panel-color panel-custom table-bordered">
									<div class="panel-heading">
										<h3 class="panel-title text-left">Uploaded Agreement Files</h3>
									</div>
									<div class="panel-body text-left">
                                    	<div class="row">
									<div class="col-md-6" id="agreementFilesList"></div>
                                        </div>
									</div>
								</div>
							</div>

						</div>
							<div class="col-lg-12">
								<div class="panel panel-color panel-custom table-bordered">
									<div class="panel-heading">
										<h3 class="panel-title text-left">Address Info</h3>
									</div>
									<div class="panel-body text-left" id="addressInfoPreview">
										
									</div>
								</div>
							</div>
							
                     <div class="row">
							<div class="col-lg-12">
								<div class="panel panel-color panel-custom table-bordered">
									<div class="panel-heading">
										<h3 class="panel-title text-left">Tax Info</h3>
									</div>
									<div class="panel-body text-left" id="taxForPreview"></div>
								</div>
							</div>
						</div>    
                         
 						
			    </div>
			</div>
            <!-- end Modal -->

        </div>
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
<!-- Ladda buttons  -->
<script src="assets/plugins/ladda-buttons/js/spin.min.js"></script>
<script src="assets/plugins/ladda-buttons/js/ladda.min.js"></script>
<script src="assets/plugins/ladda-buttons/js/ladda.jquery.min.js"></script>
<!-- Multi-Select -->
<link href="assets/plugins/multiselect/css/multi-select.css"  rel="stylesheet" type="text/css" />
<link href="assets/plugins/select2/css/select2.min.css" rel="stylesheet" type="text/css" />
<link href="assets/plugins/bootstrap-select/css/bootstrap-select.min.css" rel="stylesheet" />
<script type="text/javascript" src="assets/plugins/multiselect/js/jquery.multi-select.js"></script> 
<script type="text/javascript" src="assets/plugins/jquery-quicksearch/jquery.quicksearch.js"></script> 
<script src="assets/plugins/select2/js/select2.min.js" type="text/javascript"></script> 
<script src="assets/plugins/bootstrap-select/js/bootstrap-select.min.js" type="text/javascript"></script> 
<script src="assets/plugins/bootstrap-filestyle/js/bootstrap-filestyle.min.js" type="text/javascript"></script> 
<script type="text/javascript" src="assets/pages/jquery.form-advanced.init.js"></script>
<script type="text/javascript" src="js/accounts.js"></script>
<script type="text/javascript" src="js/urlgetter.js"></script>
<script type="text/javascript" src="js/errorCodeConstants.js"></script>
<script type="text/javascript" src="js/login.js"></script>
<script type="text/javascript" src="js/notifications.js"></script>
<script>
$("#basic-form-add-res").steps({
    headerTag: "h3",
    bodyTag: "section",
    transitionEffect: "slideLeft",
    autoFocus: true,
	onStepChanging: function (event, currentIndex, newIndex) {
		//$form_container.validate().settings.ignore = ":disabled,:hidden";
		if(currentIndex > newIndex){
			return true;
		}else{
			if(currentIndex==0){
				return basicFormValidations();
			}else if(currentIndex==1){
				return fileValidation();
			}else if(currentIndex==2){
				return addressValidation();
			}
		}
    },
    onFinishing: function (event, currentIndex) {
		//$form_container.validate().settings.ignore = ":disabled";
		//alert("submit PO info");
		return createCompanyAccount();
    },
    onFinished: function (event, currentIndex) {
       // alert("Submitted!");
    }
});
</script>
<script>
$("#basic-form-add-res_update").steps({
    headerTag: "h3",
    bodyTag: "section",
    transitionEffect: "slideLeft",
    autoFocus: true,
	onStepChanging: function (event, currentIndex, newIndex) {
		//$form_container.validate().settings.ignore = ":disabled,:hidden";
		if(currentIndex > newIndex){
			return true;
		}else{
			if(currentIndex==0){
				return updateBasicValidations();
			}else if(currentIndex==1){
				return true;
			}else if(currentIndex==2){
				return updateAddressValidations();
			}
		}
    },
    onFinishing: function (event, currentIndex) {
		//$form_container.validate().settings.ignore = ":disabled";
		//alert("submit PO info");
		return updateCompanyAccount();
		//return true;
    },
    onFinished: function (event, currentIndex) {
        //alert("Submitted!");
    }
});
</script>
<script>
	$(":file").filestyle({buttonText: "Upload Agreements"}); 
</script>
<script type="text/javascript">
	//logoutcheck();
	getNotificationPanel();
	//getMenuBarNavigation();
	getCompanyAccountsList();
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