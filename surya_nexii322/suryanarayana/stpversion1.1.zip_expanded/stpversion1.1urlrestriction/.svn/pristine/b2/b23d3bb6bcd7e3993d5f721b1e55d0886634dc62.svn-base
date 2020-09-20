<%@ page language="java" contentType="text/html;charset=UTF-8" session="false"%>
<%
HttpSession userSession = request.getSession(false);
System.out.print("before session check");
if(userSession != null ){
	System.out.print("session is not null");
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>INVOICE</title>
</head>

<body style="color:#555; font-size:15px; margin:0; padding:0;">
<div style="background-clip: padding-box; background-color: #fff; width: 980px; font-family:Arial, Helvetica, sans-serif; padding: 20px !important;">
  <div style="padding-top: 20px; padding-bottom:20px;"> <img src="assets/images/Invoice-logo.png" alt="Nexiilabs" style="text-align:left;"> </div>
  <h4 style="color: #333; font-size: 18px; line-height: 18px; margin: 0; padding: 10px 25px; text-align: center; font-family:Arial, Helvetica, sans-serif; font-weight:500;">Invoice</h4>
  <table  width="940" cellspacing="0" cellpadding="0" style="border:1px solid #999999;">
    <tr>
      <td style="border-right: 1px solid #999999; width:470px;"><table cellspacing="0" cellpadding="0" style="width:470px;">
          <tr>
            <td style="vertical-align:bottom; padding:5px; padding-right:0px !important;"><strong>Nexii IT Labs Pvt. Ltd</strong><br>
              01-08, 1st Floor,<br>
              Block-B, Cyber Pearl, Hitechcity,<br>
              Madhapur,<br>
              Hyderabad-500081<br>
              Telangana, INDIA.<br>
              <p><strong>PAN No:</strong> AAECN0261B<br>
                <strong>GSTIN:</strong> 36AAECN0261B1Z1<br>
                <strong>State:</strong> Telangana</p></td>
            <td style="vertical-align:bottom; padding:5px; padding-right:0px !important;"><p style="border:1px solid #999999; border-right:0px !important;"><strong style="border-right:1px solid #999999;">State Code:</strong> <i>36</i></p></td>
          </tr>
        </table></td>
      <td style="width:470px; vertical-align:top;"><table cellspacing="0" cellpadding="0" style="width:470px;">
          <tr>
            <td style="border-bottom:1px solid #999999; border-right:1px solid #999999; vertical-align:top; height:60px; padding:5px;"><strong>Invoice No:</strong> NEXII/17-18/11/0000</td>
            <td style="border-bottom:1px solid #999999;vertical-align:top; height:60px; padding:5px;"><strong>Dated:</strong> 20-Dec-2017</td>
          </tr>
          <tr>
            <td style="border-bottom:1px solid #999999; border-right:1px solid #999999; vertical-align:top; height:60px; padding:5px;"><strong>Buyers Order No:</strong> xxxxxxxxxxx</td>
            <td style="border-bottom:1px solid #999999;vertical-align:top; height:60px; padding:5px;"><strong>Dated:</strong> 11-Dec-2017</td>
          </tr>
          <tr>
            <td style="border-right:1px solid #999999; vertical-align:top; height:60px; padding:5px;"><strong>Supplier's Ref No:</strong> xxxxxxxxxxxxx</td>
            <td style="vertical-align:top; height:60px; padding:5px;">&nbsp;</td>
          </tr>
        </table></td>
    </tr>
  </table>
  <!--Address And Invoice Row Completed-->
  
  <table  width="940" cellspacing="0" cellpadding="0" style="border:1px solid #999999;">
    <tr>
      <td style="border-right: 1px solid #999999; width:470px;"><table cellspacing="0" cellpadding="0" style="width:470px;">
          <tr>
            <td style="vertical-align:bottom; padding:5px; padding-right:0px !important;"><strong>Bill To:</strong>
              <p>xxxxxxxxxxxxxxxxxxxxxxx,<br>
                xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx<br>
                xxxxxxxxxxxxxxxxxxxx<br>
                xxxxxxxxxxxxxxx<br>
              xxxxxxxxxxxxxxxxxxxx</p>
              <p><strong>PAN No:</strong> xxxxxxxxxxxx<br>
                <strong>GSTIN:</strong> xxxxxxxxxxxxxxxx<br>
              <strong>State:</strong> xxxxxxxxxxxxx</p></td>
            <td style="vertical-align:bottom; padding:5px; padding-right:0px !important;"><p style="border:1px solid #999999; border-right:0px !important;"><strong style="border-right:1px solid #999999;">State Code:</strong> <i>xx</i></p></td>
          </tr>
        </table></td>
      <td style="width:470px;"><table cellspacing="0" cellpadding="0" style="width:470px;">
          <tr>
            <td style="vertical-align:bottom; padding:5px; padding-right:0px !important;"><strong>Ship To:</strong>
              <p>xxxxxxxxxxxxxxxxxxxxxxx,<br>
                xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx<br>
                xxxxxxxxxxxxxxxxxxxx<br>
                xxxxxxxxxxxxxxx<br>
              xxxxxxxxxxxxxxxxxxxx</p>
              <p><strong>PAN No:</strong> xxxxxxxxxxxx<br>
                <strong>GSTIN:</strong> xxxxxxxxxxxxxxxx<br>
              <strong>State:</strong> xxxxxxxxxxxxx</p></td>
            <td style="vertical-align:bottom; padding:5px; padding-right:0px !important;"><p style="border:1px solid #999999; border-right:0px !important;"><strong style="border-right:1px solid #999999;">State Code:</strong> <i>xx</i></p></td>
          </tr>
        </table></td>
    </tr>
  </table>
  <!--Billing And Shipping Row Completed--> 
  
  <table  width="943" cellspacing="0" cellpadding="0" style="border:1px solid #999999;">
      <thead>
        <tr style="background-color: #d9edf7;">
          <td style="border-right:1px solid #999; border-bottom:1px solid #999; width:50px; padding-left:5px; padding-right:5px;">S.No.</td>
          <td style=" width:100px; padding-left:5px; padding-right:5px; border-right:1px solid #999; border-bottom:1px solid #999;">Description of services</td>
          <td style=" width:90px; padding-left:5px; padding-right:5px; border-right:1px solid #999; border-bottom:1px solid #999;">HSN SAC</td>
          <td style=" width:40px; padding-left:5px; padding-right:5px; border-right:1px solid #999; border-bottom:1px solid #999;">Qty</td>
          <td style=" width:90px; padding-left:5px; padding-right:5px; border-right:1px solid #999; border-bottom:1px solid #999;">Rate</td>
          <td style=" width:60px; padding-left:5px; padding-right:5px; border-right:1px solid #999; border-bottom:1px solid #999;">Taxable value</td>
          <td colspan="2" style="border-right:1px solid #999; border-bottom:1px solid #999; width:120px;">
          <table cellspacing="0" cellpadding="0" width="120">
              <tr>
                <td colspan="2" style=" width:110px; padding-left:5px; padding-right:5px; border-bottom:1px solid #999; text-align:center;">CGST</td>
              </tr>
              <tr>
                <td style=" width:50px; padding-left:5px; padding-right:5px; border-right:1px solid #999;">Rate</td>
                <td style=" width:50px; padding-left:5px; padding-right:5px;">Amount</td>
              </tr>
            </table>
          </td>
          <td colspan="2" style="border-right:1px solid #999; border-bottom:1px solid #999; width:120px;">
          <table cellspacing="0" cellpadding="0" width="120">
              <tr>
                <td colspan="2" style=" width:110px; padding-left:5px; padding-right:5px; border-bottom:1px solid #999; text-align:center;">SGST</td>
              </tr>
              <tr>
                <td style=" width:50px; padding-left:5px; padding-right:5px; border-right:1px solid #999;">Rate</td>
                <td style=" width:50px; padding-left:5px; padding-right:5px;">Amount</td>
              </tr>
            </table>
          </td>
          <td colspan="2" style="border-right:1px solid #999; border-bottom:1px solid #999; width:120px;">
          <table cellspacing="0" cellpadding="0" width="120">
              <tr>
                <td colspan="2" style=" width:110px; padding-left:5px; padding-right:5px; border-bottom:1px solid #999; text-align:center;">IGST</td>
              </tr>
              <tr>
                <td style=" width:50px; padding-left:5px; padding-right:5px; border-right:1px solid #999;">Rate</td>
                <td style=" width:50px; padding-left:5px; padding-right:5px;">Amount</td>
              </tr>
            </table>
          </td>
          <td style=" width:83px; padding-left:5px; padding-right:5px; border-bottom:1px solid #999;">Total Rs.</td>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td style=" width:50px; padding-left:5px; padding-right:5px; border-right:1px solid #999; border-bottom:1px solid #999;">01</td>
          <td style=" width:100px; padding-left:5px; padding-right:5px; border-right:1px solid #999; border-bottom:1px solid #999;">Services for Dummy text dont read this.</td>
          <td style=" width:90px; padding-left:5px; padding-right:5px; border-right:1px solid #999; border-bottom:1px solid #999;">998313</td>
          <td style=" width:40px; padding-left:5px; padding-right:5px; border-right:1px solid #999; border-bottom:1px solid #999;">1</td>
          <td style=" width:90px; padding-left:5px; padding-right:5px; border-right:1px solid #999; border-bottom:1px solid #999;">-</td>
          <td style=" width:60px; padding-left:5px; padding-right:5px; border-right:1px solid #999; border-bottom:1px solid #999;">-</td>
          <td style=" width:45px; padding-left:5px; padding-right:5px; border-right:1px solid #999; border-bottom:1px solid #999;">0.00%</td>
          <td style=" width:45px; padding-left:5px; padding-right:5px; border-right:1px solid #999; border-bottom:1px solid #999;">-</td>
          <td style=" width:45px; padding-left:5px; padding-right:5px; border-right:1px solid #999; border-bottom:1px solid #999;">0.00%</td>
          <td style=" width:45px; padding-left:5px; padding-right:5px; border-right:1px solid #999; border-bottom:1px solid #999;">-</td>
          <td style=" width:45px; padding-left:5px; padding-right:5px; border-right:1px solid #999; border-bottom:1px solid #999;">0.00%</td>
          <td style=" width:45px; padding-left:5px; padding-right:5px; border-right:1px solid #999; border-bottom:1px solid #999;">-</td>
          <td style=" width:83px; padding-left:5px; padding-right:5px; border-bottom:1px solid #999;">-</td>
        </tr>
        <tr>
          <td style=" width:50px; padding-left:5px; padding-right:5px; border-right:1px solid #999; border-bottom:1px solid #999;">02</td>
          <td style=" width:100px; padding-left:5px; padding-right:5px; border-right:1px solid #999; border-bottom:1px solid #999;">Resources for Dummy text dont read this.</td>
          <td style=" width:90px; padding-left:5px; padding-right:5px; border-right:1px solid #999; border-bottom:1px solid #999;">998313</td>
          <td style=" width:40px; padding-left:5px; padding-right:5px; border-right:1px solid #999; border-bottom:1px solid #999;">1</td>
          <td style=" width:90px; padding-left:5px; padding-right:5px; border-right:1px solid #999; border-bottom:1px solid #999;">-</td>
          <td style=" width:60px; padding-left:5px; padding-right:5px; border-right:1px solid #999; border-bottom:1px solid #999;">-</td>
          <td style=" width:45px; padding-left:5px; padding-right:5px; border-right:1px solid #999; border-bottom:1px solid #999;">0.00%</td>
          <td style=" width:45px; padding-left:5px; padding-right:5px; border-right:1px solid #999; border-bottom:1px solid #999;">-</td>
          <td style=" width:45px; padding-left:5px; padding-right:5px; border-right:1px solid #999; border-bottom:1px solid #999;">0.00%</td>
          <td style=" width:45px; padding-left:5px; padding-right:5px; border-right:1px solid #999; border-bottom:1px solid #999;">-</td>
          <td style=" width:45px; padding-left:5px; padding-right:5px; border-right:1px solid #999; border-bottom:1px solid #999;">0.00%</td>
          <td style=" width:45px; padding-left:5px; padding-right:5px; border-right:1px solid #999; border-bottom:1px solid #999;">-</td>
          <td style=" width:83px; padding-left:5px; padding-right:5px; border-bottom:1px solid #999;">-</td>
        </tr>
        
        <tr style="background-color: #d9edf7;">
          <td colspan="3" style="text-align:right; padding:20px 10px; border-right:1px solid #999;">Total</td>
          <td style="padding:20px 10px; border-right:1px solid #999;">2</td>
          <td style="padding:20px 10px; border-right:1px solid #999;"></td>
          <td style="padding:20px 10px; border-right:1px solid #999;"></td>
          <td style="padding:20px 10px; border-right:1px solid #999;"></td>
          <td style="padding:20px 10px; border-right:1px solid #999;"></td>
          <td style="padding:20px 10px; border-right:1px solid #999;"></td>
          <td style="padding:20px 10px; border-right:1px solid #999;"></td>
          <td style="padding:20px 10px; border-right:1px solid #999;"></td>
          <td style="padding:20px 10px; border-right:1px solid #999;"></td>
          <td style="padding:20px 10px;"></td>
        </tr>
      </tbody>
    </table>
  <!--Description Row Completed--> 
  <table  width="940" cellspacing="0" cellpadding="0" style="border:1px solid #999999;">
    <tr>
    
    <td style="width:548px; vertical-align:top;">
    <table cellspacing="0" cellpadding="0" style="width:548px;">
          <tr>
            <td style="border-bottom:1px solid #999999; border-right:1px solid #999999; vertical-align:top; height:60px; padding:5px; text-align:center;">
            <strong>Total Invoice Amount in Words</strong><br>
        	xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
            </td>
          </tr>
          <tr>
            <td style="border-bottom:1px solid #999999; border-right:1px solid #999999; vertical-align:top; height:120px; padding:5px;">
            <strong>Bank Details</strong><br>
                Account Holder Name: <strong>00001</strong><br>
                Bank Name: <strong>00001</strong><br>
                Bank Account Number: <strong>00001</strong><br>
                IFSC: <strong>00001</strong>
            </td>
          </tr>
          <tr>
            <td style="border-right:1px solid #999999; vertical-align:top; height:80px; padding:5px;">
            <strong>Declaraion</strong><br>We declare that this invoice shows the actual price of the services described and that all particulars are true and correct 
            </td>
          </tr>
        </table>
        </td>
    
    
      <td style="border-right: 1px solid #999999; width:392px; vertical-align:top;">
      <table cellspacing="0" cellpadding="0" style="width:392px;">
          <tr>
          <td style="background-color:#0f60ab; color:#ffffff; padding:7px 10px; width:250px; border-bottom:1px solid #999999;">Total Amount before Tax</td>
          <td style="padding-left:10px; border-bottom:1px solid #999999;">xxxxxx</td>
        </tr>
        <tr>
          <td style="background-color:#4c5667; color:#ffffff; padding:7px 10px; width:250px; border-bottom:1px solid #999999;">Add CGST</td>
          <td style="padding-left:10px; border-bottom:1px solid #999999;">xxxxxx</td>
        </tr>
        <tr>
          <td style="background-color:#4c5667; color:#ffffff; padding:7px 10px; width:250px; border-bottom:1px solid #999999;">Add SGST</td>
          <td style="padding-left:10px; border-bottom:1px solid #999999;">xxxxxx</td>
        </tr>
        <tr>
          <td style="background-color:#4c5667; color:#ffffff; padding:7px 10px; width:250px; border-bottom:1px solid #999999;">Add IGST</td>
          <td style="padding-left:10px; border-bottom:1px solid #999999;">xxxxxx</td>
        </tr>
        <tr>
          <td style="background-color:#4c5667; color:#ffffff; padding:7px 10px; width:250px; border-bottom:1px solid #999999;">Total GST</td>
          <td style="padding-left:10px; border-bottom:1px solid #999999;">xxxxxx</td>
        </tr>
        <tr>
          <td style="background-color:#0f60ab; color:#ffffff; padding:7px 10px; width:250px;">Total Amount After Tax</td>
          <td style="padding-left:10px;">xxxxxx</td>
        </tr>
        <tr>
          <td colspan="2" style="background-color:#81c868; color:#ffffff; padding:7px 10px; text-align:center; width:392px;"><strong>For Nexii IT Labs Private Limited</strong></td>
        </tr>
        </table>
        </td>
      
    </tr>
  </table>
  <!--<div style="padding: 20px; width:980px;"> <a href="#" style="background-color: #81c868; border: 1px solid #81c868; border-radius: 3px; padding:10px 20px; color:#ffffff; text-decoration:none;"><strong>Generate</strong></a> </div>--> 
</div>
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