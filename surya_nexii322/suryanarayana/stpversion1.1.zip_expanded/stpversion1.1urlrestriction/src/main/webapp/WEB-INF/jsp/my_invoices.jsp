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
			boolean status= utilitiesController.authorizationsCheck("Invoices",menu);
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
        <script src="https://oss.maxcdn.com/libs/reskpond.js/1.3.0/respond.min.js"></script>
        <![endif]-->

<script src="assets/js/modernizr.min.js"></script>
<style>
.custombox-modal-container.custombox-modal-container-fadein {
    width: 980px !important;
}
.brt{
	border-right:1px solid #ebeff2;
}
</style>
</head>

<body class="fixed-left">

<!-- Begin page -->
<div class="wi-moon-alt-waning-gibbous-1" id="wrapper"> 
  
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
         <%=utilitiesController.getMenuNavigationForActivePage("my_invoices",menu) %>
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
            <h4 class="page-title">Request Invoices</h4>
            <ol class="breadcrumb">
              <li><a href="#">Invoices</a></li>
              <li class="active"> My Invoices</li>
            </ol>
          </div>
        </div>
        <div class="row">
          <div class="col-sm-12">
            <div class="card-box table-responsive">
              <table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
                <thead>
                  <tr>
                  	<th>S. No.</th>
                    <th>Invoice Number</th>
                    <th>Vendor Address</th>
                    <th>Invoice Date</th>
                    <th>PO Number</th>
                    <th>PO Date</th>
                    <th>Actions</th>
                  </tr>
                </thead>
                <tbody id="myinvoicelist">
                  <!-- <tr class="gradeX">
                  	<td>01</td>
                    <td>INV_001</td>
                    <td><h4 class="text-purple m-t-0">NexiiLabs Pvt. Ltd.</h4>
                      <div style="word-break:break-word; width:250px;">Unit No: 01-08,1st Floor, Block-B, Cyber Pearl, Madhapur, Hyderabad, AP, Pin: 500 081.India</div></td>
                    <td>03-01-2018</td>
                    <td>PO_003</td>
                    <td>03-01-2018</td>
                    <td><a href="#custom-modal-generate" class="btn btn-success m-l-15" data-animation="fadein" data-plugin="custommodal" data-overlaySpeed="200" data-overlayColor="#36404a">View</a></td>
                  </tr> -->
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
    
    <footer class="footer"> Â© 2017. All rights reserved. </footer>
  </div>
  <!-- ============================================================== --> 
  <!-- End Right content here --> 
  <!-- ============================================================== --> 
  
  <!-- Generate Invoice MODAL -->
  <div id="custom-modal-generate" class="modal-demo">
  <span id="htmlview"></span>
  </div>
 <!--  <div id="custom-modal-generate" class="modal-demo">
    <button type="button" class="close" onclick="Custombox.close();"> <span>&times;</span><span class="sr-only">Close</span> </button>
    <h4 class="custom-modal-title">Invoice</h4>
    <div class="custom-modal-text">
      <div class="panel-body">
        <div class="clearfix"> 
          <div class="pull-right">
            <h4 class="text-right"><img src="assets/images/Invoice-logo.png" alt="Nexiilabs"></h4>
          </div>
        </div>
        <hr>
        <div class="row">
          <div class="col-md-12">
            <div class="pull-left m-t-10 text-left">
              <address>
              <strong>Nexii IT Labs Pvt. Ltd</strong><br>
              01-08, 1st Floor, Block-B, Cyber Pearl,<br>Hitechcity, Madhapur, Hyderabad-500081,<br>Telangana, INDIA.
              <p class="m-t-10">PAN No: <strong>AAECN0261B</strong><br>
              GSTIN: <strong>36AAECN0261B1Z1</strong><br>
              State Code: <strong>36</strong></p>
              </address>
            </div>
            <div class="pull-right m-t-10 text-right">
              <p>Invoice No: <strong>NEXII/17-18/11/0000</strong><br>Dated: <strong>12/20/2017</strong></p>
              <p>Buyer's Order No: <strong>00001</strong><br>Dated: <strong>12/11/2017</strong></p>
              <p>Supplier's Ref. No: <strong>00001</strong></p>
            </div>
          </div>
        </div>
        <div class="m-h-20"></div>
        <div class="row">
          <div class="col-md-12">
            <div class="table-responsive table-bordered table-striped text-left">
              <table class="table">
                <thead>
                  <tr>
                    <td class="col-sm-6 info">Bill To</td>
                    <td class="col-sm-6 info">Ship To</td>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td class="col-sm-6">
                    <strong>Nexii IT Labs Pvt. Ltd</strong><br>
                      01-08, 1st Floor, Block-B, Cyber Pearl,<br>Hitechcity, Madhapur, Hyderabad-500081,<br>Telangana, INDIA.
                      <p class="m-t-10">PAN No: <strong>AAECN0261B</strong><br>
                      GSTIN: <strong>36AAECN0261B1Z1</strong><br>
                      State Code: <strong>36</strong></p>
					</td>
                    <td class="col-sm-6">
                    <strong>Nexii IT Labs Pvt. Ltd</strong><br>
                      01-08, 1st Floor, Block-B, Cyber Pearl,<br>Hitechcity, Madhapur, Hyderabad-500081,<br>Telangana, INDIA.
                      <p class="m-t-10">PAN No: <strong>AAECN0261B</strong><br>
                      GSTIN: <strong>36AAECN0261B1Z1</strong><br>
                      State Code: <strong>36</strong></p>
					</td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
		<div class="m-h-40"></div>
        <div class="row">
          <div class="col-md-12">
            <div class="table-responsive table-bordered text-left">
              <table class="table">
                <thead>
                  <tr>
                    <td class="info">S.No.</td>
                    <td class="info col-md-2">Description of services</td>
                    <td class="info">HSN SAC</td>
                    <td class="info">Qty</td>
                    <td class="info">Rate</td>
                    <td class="info">Taxable value</td>
                    <td class="info">CGST Rate</td>
                    <td class="info">CGST Amount</td>
                    <td class="info">SGST Rate</td>
                    <td class="info">SGST Amount</td>
                    <td class="info">IGST Rate</td>
                    <td class="info">IGST Amount</td>
                    <td class="info">Total Rs.</td>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td class="brt">01</td>
                    <td class="brt">Services for Dummy text dont read this.</td>
                    <td class="brt">998313</td>
                    <td class="brt">1</td>
                    <td class="brt">-</td>
                    <td class="brt">-</td>
                    <td class="brt">-</td>
                    <td class="brt">-</td>
                    <td class="brt">-</td>
                    <td class="brt">-</td>
                    <td class="brt">-</td>
                    <td class="brt">-</td>
                    <td>-</td>
                  </tr>
                  <tr>
                    <td class="brt">02</td>
                    <td class="brt">Resources for Dummy text dont read this.</td>
                    <td class="brt">998313</td>
                    <td class="brt">1</td>
                    <td class="brt">-</td>
                    <td class="brt">-</td>
                    <td class="brt">-</td>
                    <td class="brt">-</td>
                    <td class="brt">-</td>
                    <td class="brt">-</td>
                    <td class="brt">-</td>
                    <td class="brt">-</td>
                    <td>-</td>
                  </tr>
                  <tr>
                    <td colspan="3" class="brt text-right">Total</td>
                    <td class="brt">2</td>
                    <td class="brt"></td>
                    <td class="brt"></td>
                    <td class="brt"></td>
                    <td class="brt"></td>
                    <td class="brt"></td>
                    <td class="brt"></td>
                    <td class="brt"></td>
                    <td class="brt"></td>
                    <td></td>
                  </tr
                ></tbody>
              </table>
            </div>
          </div>
        </div>
        <div class="m-h-40"></div>
        <div class="row">
        <div class="col-md-12">
        <div class="col-md-6">
        <p class="text-left">
        <strong>Total Invoice Amount in Words</strong><br>
        xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
        </p>
        <p class="text-left">
        <strong>Bank Details</strong><br>
        Account Holder Name: <strong>00001</strong><br>
        Bank Name: <strong>00001</strong><br>
        Bank Account Number: <strong>00001</strong><br>
        IFSC: <strong>00001</strong><br>
        </p>
         <p class="text-left">
        <strong>Declaraion</strong><br>
        We declare that this invoice shows the actual price of the services described and that all particulars are true and correct
        </p>
        </div>
        <div class="col-md-6">
        <div class="table-responsive table-bordered text-left">
              <table class="table m-b-0">
                  <tr>
                    <td class="bg-purple text-white">Total Amount before Tax</td>
                    <td>xxxxxx</td>
                  </tr>
                  <tr>
                    <td class="bg-inverse text-white">Add CGST</td>
                    <td>xxxxxx</td>
                  </tr>
                  <tr>
                    <td class="bg-inverse text-white">Add SGST</td>
                    <td>xxxxxx</td>
                  </tr>
                  <tr>
                    <td class="bg-inverse text-white">Add IGST</td>
                    <td>xxxxxx</td>
                  </tr>
                  <tr>
                    <td class="bg-inverse text-white">Total GST</td>
                    <td>xxxxxx</td>
                  </tr>
                  <tr>
                    <td class="bg-purple text-white">Total Amount After Tax</td>
                    <td>xxxxxx</td>
                  </tr>
                  <tr>
                  <td colspan="2" class="bg-success text-white text-center"><strong>For Nexii IT Labs Private Limited</strong></td>
                  </tr>
              </table>
            </div>
        </div>
        </div>
        </div>
         <div class="m-h-40"></div>
        <div class="hidden-print">
          <div class="pull-right"> <button class="btn btn-primary waves-effect">Send Invoice</button>
            <button class="btn btn-default waves-effect" onclick="Custombox.close();">Cancel</button> </div>
        </div>
      </div>
    </div>
  </div> -->
  <!-- end Modal --> 
  
</div>
<!-- END wrapper --> 

<script>
            var resizefunc = [];
        </script> 

<!-- jQuery  --> 
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> -->
<script src="assets/js/jquery.min1.js"></script>
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

<!-- Multi-Select -->
<link href="assets/plugins/multiselect/css/multi-select.css"  rel="stylesheet" type="text/css" />
<link href="assets/plugins/select2/css/select2.min.css" rel="stylesheet" type="text/css" />
<link href="assets/plugins/bootstrap-select/css/bootstrap-select.min.css" rel="stylesheet" />
<script type="text/javascript" src="assets/plugins/multiselect/js/jquery.multi-select.js"></script> 
<script type="text/javascript" src="assets/plugins/jquery-quicksearch/jquery.quicksearch.js"></script> 
<script src="assets/plugins/select2/js/select2.min.js" type="text/javascript"></script> 
<script src="assets/plugins/bootstrap-select/js/bootstrap-select.min.js" type="text/javascript"></script> 
<script type="text/javascript" src="assets/pages/jquery.form-advanced.init.js"></script>
<script type="text/javascript" src="js/myinvoices.js"></script>
        <script type="text/javascript" src="js/errorCodeConstants.js"></script>
        <script type="text/javascript" src="js/urlgetter.js"></script>
        <script type="text/javascript" src="js/changePassword.js"></script>
        <script type="text/javascript" src="js/login.js"></script>
        <script type="text/javascript" src="js/notifications.js"></script>
         <script type="text/javascript">
         $(document).ready(function () {
        	 //alert("on load function");
        	 //logoutcheck();
        	 getNotificationPanel();
        	 //getMenuBarNavigation();
        	 myinvoices();
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