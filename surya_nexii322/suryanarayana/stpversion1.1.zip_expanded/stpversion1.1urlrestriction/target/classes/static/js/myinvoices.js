/*******************************************************************************
 * @Author Trineesha.Mandapati
 * @Version 1.0
 * 
 * 
 * /************* GET MY INVOICE FUNCTION
 ******************************************************************************/

function myinvoices() {
	// alert("get");
	//logoutcheck();
	var appBasePath = getAppBasePath();
	$.ajax({
				type : "GET",
				url : appBasePath + "/invoice/myinvoices",
				beforeSend : function() {
					// alert("before getting list");
				},
				success : function(data) {
					$('#myinvoicelist').html("");
					if (data.length > 0) {
						var count=1;
						var invoiceDate=null;
						var poDate=null;
						$.each(data,function(index, item) {
								//alert(item.currencyType);			
							invoiceDate=item.invoicePdfDate.split(" ");
							poDate=item.pODate.split(" ");
											var eachrow = "<tr class=\"gradeX\">"
													+ "<td>"+count+ "</td>"
													+ "<td>"+ item.invoiceNumber+ "</td>"
													+ "<td>"+ item.vendorAddLane1+ ","+ item.vendorAddLane2+"<br/>"+item.vendorAddState+ ","+ item.vendorAddPincode+"</td>"
													+ "<td>"+ invoiceDate[0]+ "</td>"
													+ "<td>"+ item.pONumber+ "</td>"
													+ "<td>"+ poDate[0]+ "</td>"
													+ '<td class="actions"><a href="#" onclick=\"myInvoiceView(\''+ item.invoiceRequestId
													+ '\',\''+ item.invoiceId+ '\',\''+ item.invoiceNumber
													+ '\',\''+ invoiceDate[0]+ '\',\''+ item.pONumber
													+ '\',\''+ poDate[0]+ '\',\''+ item.supplierReferanceNumber
													+ '\',\''+ item.shipAddLane1+ '\',\''+ item.shipAddLane2
													+ '\',\''+ item.shipAddState+ '\',\''+ item.shipAddPincode
													+ '\',\''+ item.billingAddLane1+ '\',\''+ item.billingAddLane2
													+ '\',\''+ item.billingAddState+ '\',\''+ item.billingAddPincode
													+ '\',\''+ item.vendorAddLane1+ '\',\''+ item.vendorAddLane2
													+ '\',\''+ item.vendorAddState+ '\',\''+ item.vendorAddPincode
													+ '\',\''+ item.project_Name
													+ '\',\''+ item.period+ '\',\''+ item.employeeName
													+ '\',\''+ item.hSNSAC+ '\',\''+ item.quantity
													+ '\',\''+ item.billingDays+ '\',\''+ item.totalWorkingDays
													+ '\',\''+ item.rate+ '\',\''+ item.taxableValue
													+ '\',\''+ item.cGSTRate+ '\',\''+ item.cGSTAmount
													+ '\',\''+ item.sGSTRate+ '\',\''+ item.sGSTAmount
													+ '\',\''+ item.iGSTRate+ '\',\''+ item.iGSTAmount
													+ '\',\''+ item.igstApplicable+ '\',\''+ item.totalAmount
													+ '\',\''+ item.accountHolderName+ '\',\''+ item.bankName
													+ '\',\''+ item.bankAcNo+ '\',\''+ item.iFSCCode
													+ '\',\''+ item.customerPan
													+ '\',\''+ item.customerGstin+ '\',\''+ item.customerState
													+ '\',\''+ item.customerStateCode+ '\',\''+ item.ourPan
													+ '\',\''+ item.ourGstin+ '\',\''+ item.ourState
													+ '\',\''+ item.ourStateCode+ '\',\''+ item.currencyType+ '\',\''+ item.currencyType+ '\',\''+ item.invoiceDate
													+'\');\" class="btn btn-success m-l-15" data-animation="fadein" data-plugin="custommodal" data-overlaySpeed="200" data-overlayColor="#36404a">View</a></td>'
													+ "</tr>";

											$('#myinvoicelist').append(eachrow);
											count=count+1;
										});
						$('#datatable-responsive').DataTable();
					} else {
						$('#myinvoicelist').append("");
						$('#datatable-responsive').DataTable({
							"language": {
								"emptyTable": "Invoices not exist for listout"
							}
						});
					}

				},
				error : function(req, status, msg) {

				}
			});
}
function myInvoiceView(fkInvoiceRequestId, invoiceId, invoiceNumber, invoicePdfDate, poNumber,poDate,
		supplierRefNumber,shipAddLane1,shipAddLane2,shipAddState,shipAddPincode,
		billingAddLane1,billingAddLane2,billingAddState,billingAddPincode,vendorAddLane1,
		vendorAddLane2,vendorAddState,vendorAddPincode,project_Name,period,employeeName,
		hsnSac,quantity,billingDays,totalWorkDays,rate,taxableAmount,cgst_rate,cgstAmount,sgstRate,sgstAmount,
		igstRate,igstAmount,igstApplicable,totalAmount,accountHolderName,bankName,accountNumber,ifscCode,
		customerPan,customerGstin,customerState,customerStateCode,our_pan,ourGstin,ourState,ourStateCode,currencyType,invoiceDate) {
		//logoutcheck();
					$("#htmlview").html("");
					var words=number2text(totalAmount);
					var totalAmount1=""+totalAmount;
					var rate1=""+rate;
					var taxableAmount1=""+taxableAmount;
					if(currencyType=="USD"){
						words=words.replace("RUPEES","DOLLAR");
						totalAmount1="&#036;"+totalAmount;
						rate1=""+rate;
						taxableAmount1=""+taxableAmount;
					}else if(currencyType=="EUR"){
						words=words.replace("RUPEES","EURO");
						totalAmount1="&#8364;"+totalAmount;
						rate1=""+rate;
						taxableAmount1=""+taxableAmount;
					}else{
						words=""+words;
						totalAmount1="Rs."+totalAmount;
						rate1=""+rate;
						taxableAmount1=""+taxableAmount;
					}
					//alert(words+"....in words");
					
					var View=" <button type='button' class='close' onclick='Custombox.close();'> " +
					"<span>&times;</span><span class='sr-only'>Close</span> </button>" +
					"<h4 class='custom-modal-title'>Invoice</h4><div class='custom-modal-text'>" +
					"<div class='panel-body'><div class='clearfix'> <div class='pull-right'>" +
					"<h4 class='text-right'><img src='assets/images/Invoice-logo.png' alt='Nexiilabs'>" +
					"</h4> </div></div><hr><div class='row'><div class='col-md-12'>" +
					"<div class='pull-left m-t-10 text-left'><address>" +
					"<strong>"+vendorAddLane1+"</strong><br>"+vendorAddLane2+"," +
					"<br>"+vendorAddState+",<br>"+vendorAddPincode+"." +
					"<p class='m-t-10'>PAN No: <strong>"+our_pan+"</strong><br>" +
					"GSTIN: <strong>"+ourGstin+"</strong><br>" +
					"State: <strong>"+ourState+"</strong><br>" +
					" State Code: <strong>"+ourStateCode+"</strong></p></address></div>" +
					"<div class='pull-right m-t-10 text-right'>" +
					"<p>Invoice No: <strong id='strong'>"+invoiceNumber+"</strong><br>Dated: <strong>"+invoicePdfDate+"</strong></p>" +
					" <p>Buyer's Order No: <strong>"+poNumber+"</strong><br>Dated: <strong>"+invoicePdfDate+"</strong></p>" +
					"<p>Supplier's Ref. No: <strong>"+supplierRefNumber+"</strong></p></div></div> </div>" +
					"<div class='m-h-20'></div> <div class='row'><div class='col-md-12'>" +
					"<div class='table-responsive table-bordered table-striped text-left'>" +
					"<table class='table'>" +
					"<thead>" +
					"<tr> " +
					"<td class='col-sm-6 info'>Bill To</td>" +
					"<td class='col-sm-6 info'>Ship To</td>" +
					"</tr>" +
					"</thead>" +
					"<tbody>" +
					"<tr>" +
					"<td class='col-sm-6'><strong>"+billingAddLane1+"</strong>" +
					"<br>"+billingAddLane2+"," +
					"<br>"+billingAddState+"," +
					"<br>"+billingAddPincode+"." +
					"<p class='m-t-10'>PAN No: <strong>"+customerPan+"</strong>" +
					"<br>GSTIN: <strong>"+customerGstin+"</strong>" +
					"<br>State: <strong>"+customerState+"</strong>" +
					"<br>State Code: <strong>"+customerStateCode+"</strong></p>" +
					"</td>" +
					"<td class='col-sm-6'><strong>"+shipAddLane1+"</strong>" +
					"<br>"+shipAddLane2+"," +
					"<br>"+shipAddState+"," +
					"<br>"+shipAddPincode+"." +
					"<p class='m-t-10'>PAN No: <strong>"+customerPan+"</strong>" +
					"<br>GSTIN: <strong>"+customerGstin+"</strong>" +
					"<br>State: <strong>"+customerState+"</strong>" +
					"<br>State Code: <strong>"+customerStateCode+"</strong>" +
					"</p>" +
					"</td>" +
					"</tr>" +
					"</tbody> " +
					"</table>" +
					"</div>" +
					"</div> " +
					"</div>" +
					"<div class='m-h-40'></div>" +
					"<div class='row'>" +
					"<div class='col-md-12'>" +
					"<div class='table-responsive table-bordered text-left'>" +
					"<table class='table'>" +
					"<thead>" +
					"<tr>" +
					"<td class='info'>S.No.</td> " +
					"<td class='info col-md-2'>Description of services</td>" +
					"<td class='info'>HSN SAC</td>" +
					"<td class='info'>Qty</td>" +
					"<td class='info'>Rate</td> " +
					"<td class='info'>Taxable value</td> " +
					"<td class='info'>CGST Rate</td> " +
					"<td class='info'>CGST Amount</td>" +
					"<td class='info'>SGST Rate</td>" +
					"<td class='info'>SGST Amount</td> " +
					"<td class='info'>IGST Rate</td> " +
					"<td class='info'>IGST Amount</td>" +
					"<td class='info'>Total Amount.</td> " +
					"</tr>" +
					"</thead> " +
					"<tbody> " +
					/*"<tr> " +
					"<td class='brt'>01</td>" +
					"<td class='brt'>Services for Project<br></td>" +
					"<td class='brt'>-</td>" +
					"<td class='brt'>-</td>" +
					"<td class='brt'>-</td>" +
					"<td class='brt'>-</td>" +
					"<td class='brt'>-</td>" +
					"<td class='brt'>-</td>" +
					"<td class='brt'>-</td>" +
					"<td class='brt'>-</td>" +
					"<td class='brt'>-</td>" +
					"<td class='brt'>-</td>" +
					"<td>-</td>" +
					"</tr>" +*/
					"<tr>" +
					"<td class='brt'>02</td>" +
					"<td class='brt'>Services for<br/> Project Name:"+project_Name+"<br>Resource Employee:"+employeeName+"<br>Billing Period:"+period+"<br>Working Days:"+totalWorkDays+"<br>Billing Days:"+billingDays+"</td>" +
					"<td class='brt'>"+hsnSac+"</td> " +
					"<td class='brt'>"+quantity+"</td> " +
					"<td class='brt'>"+rate1+"</td>" +
					"<td class='brt'>"+taxableAmount1+"</td>" +
					"<td class='brt'>"+cgst_rate+"%</td>" +
					"<td class='brt'>"+cgstAmount+"</td>" +
					"<td class='brt'>"+sgstRate+"%</td>" +
					"<td class='brt'>"+sgstAmount+"</td>" +
					"<td class='brt'>"+igstRate+"%</td>" +
					"<td class='brt'>"+igstAmount+"</td>" +
					"<td>"+totalAmount+"</td>" +
					"</tr>" +
					"<tr>" +
					"<td colspan='3' class='brt text-right'>Total</td>" +
					"<td class='brt'>1</td>" +
					"<td class='brt'></td>" +
					"<td class='brt'></td>" +
					"<td class='brt'></td> " +
					"<td class='brt'></td> " +
					"<td class='brt'></td>" +
					"<td class='brt'></td>" +
					"<td class='brt'></td>" +
					"<td class='brt'></td>" +
					"<td>"+totalAmount1+"</td> " +
					"</tr>" +
					"</tbody> " +
					"</table>" +
					"</div>" +
					"</div>" +
					"</div>" +
					"<div class='m-h-40'></div>" +
					"<div class='row'>" +
					"<div class='col-md-12'>" +
					"<div class='col-md-6'>" +
					"<p class='text-left'><strong>Total Invoice Amount in Words</strong>" +
					"<br>"+words+"</p>" +
					"<p class='text-left'><strong>Bank Details</strong>" +
					"<br>Account Holder Name: <strong>"+accountHolderName+"</strong>" +
					"<br>Bank Name: <strong>"+bankName+"</strong>" +
					"<br>Bank Account Number: <strong>"+accountNumber+"</strong>" +
					"<br>IFSC: <strong>"+ifscCode+"</strong>" +
					"<br>" +
					"</p>" +
					"<p class='text-left'><strong>Declaraion</strong>" +
					"<br>We declare that this invoice shows the actual price of the services described and that all particulars are true and correct</p>" +
					"</div>" +
					"<div class='col-md-6'><div class='table-responsive table-bordered text-left'>" +
					"<table class='table m-b-0'>" +
					"<tr>" +
					"<td class='bg-purple text-white'>Total Amount before Tax</td>" +
					"<td>"+taxableAmount1+"</td>" +
					"</tr>" +
					"<tr>" +
					"<td class='bg-inverse text-white'>Add CGST</td>" +
					"<td>"+cgstAmount+"</td>" +
					"</tr>" +
					"<tr>" +
					"<td class='bg-inverse text-white'>Add SGST</td>" +
					"<td>"+sgstAmount+"</td>" +
					"</tr> " +
					"<tr>" +
					"<td class='bg-inverse text-white'>Add IGST</td>" +
					"<td>"+igstAmount+"</td>" +
					"</tr>" +
					"<tr>" +
					"<td class='bg-inverse text-white'>Total GST</td>" +
					"<td>"+((+cgstAmount)+(+sgstAmount)+(+igstAmount))+"</td>" +
					"</tr>" +
					"<tr>" +
					"<td class='bg-purple text-white'>Total Amount After Tax</td>" +
					"<td>"+totalAmount1+"</td>" +
					"</tr>" +
					"<tr>" +
					"<td colspan='2' class='bg-success text-white text-center'>" +
					"<strong>For Nexii IT Labs Private Limited</strong>" +
					"</td>" +
					"</tr>" +
					"</table>" +
					"</div>" +
					"</div>" +
					"</div>" +
					"</div>" +
					"<div class='m-h-40'></div>" +
					"<div class='hidden-print'>" +
					"<div class='pull-right'> " +
					'<button class="btn btn-success btn-primary waves-effect" onclick=\"downloadMyInvoice(\''+ fkInvoiceRequestId
					+ '\');\">Download Invoice</button>&nbsp;' +
					"<button class='btn btn-default waves-effect' onclick='Custombox.close();'>Close</button>" +
					" </div>" +
					"</div><span id='send_msg'></span>" +
					"</div>" +
					"            <div style=\" vertical-align:top; text-align:center; height:59px; padding:5px;font-size:15px;\">" + 
					"           <i>Computer Generated Invoice, No Signature required</i> " + 
					"            </div>" + 
					"</div>";
					
					$("#htmlview").html(View);
					
					Custombox.open({
						target : "#custom-modal-generate",
						effect : "fadein"
					});
	
}

function number2text(value) {
    var fraction = Math.round(frac(value)*100);
    var f_text  = "";

    if(fraction > 0) {
        f_text = "AND "+convert_number(fraction)+" PAISE";
    }

    return "RUPEES "+convert_number(value)+""+f_text+" ONLY";
}

function frac(f) {
    return f % 1;
}

function convert_number(number)
{
    if ((number < 0) || (number > 999999999)) 
    { 
        return "NUMBER OUT OF RANGE!";
    }
    var Gn = Math.floor(number / 10000000);  /* Crore */ 
    number -= Gn * 10000000; 
    var kn = Math.floor(number / 100000);     /* lakhs */ 
    number -= kn * 100000; 
    var Hn = Math.floor(number / 1000);      /* thousand */ 
    number -= Hn * 1000; 
    var Dn = Math.floor(number / 100);       /* Tens (deca) */ 
    number = number % 100;               /* Ones */ 
    var tn= Math.floor(number / 10); 
    var one=Math.floor(number % 10); 
    var res = ""; 

    if (Gn>0) 
    { 
        res += (convert_number(Gn) + " CRORE"); 
    } 
    if (kn>0) 
    { 
            res += (((res=="") ? "" : " ") + 
            convert_number(kn) + " LAKH"); 
    } 
    if (Hn>0) 
    { 
        res += (((res=="") ? "" : " ") +
            convert_number(Hn) + " THOUSAND"); 
    } 

    if (Dn) 
    { 
        res += (((res=="") ? "" : " ") + 
            convert_number(Dn) + " HUNDRED"); 
    } 


    var ones = Array("", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX","SEVEN", "EIGHT", "NINE", "TEN", "ELEVEN", "TWELVE", "THIRTEEN","FOURTEEN", "FIFTEEN", "SIXTEEN", "SEVENTEEN", "EIGHTEEN","NINETEEN"); 
var tens = Array("", "", "TWENTY", "THIRTY", "FOURTY", "FIFTY", "SIXTY","SEVENTY", "EIGHTY", "NINETY"); 

    if (tn>0 || one>0) 
    { 
        if (!(res=="")) 
        { 
            res += " AND "; 
        } 
        if (tn < 2) 
        { 
            res += ones[tn * 10 + one]; 
        } 
        else 
        { 

            res += tens[tn];
            if (one>0) 
            { 
                res += ("-" + ones[one]); 
            } 
        } 
    }

    if (res=="")
    { 
        res = "zero"; 
    } 
    return res;
}
function precisionRound(number, precision) {
	  var factor = Math.pow(10, precision);
	  return Math.round(number * factor) / factor;
	}

function downloadMyInvoice(fkInvoiceRequestId){
    
	var appBasePath=getAppBasePath();
    var url=appBasePath+"/invoice/downloadmyinvoice?requestId="+fkInvoiceRequestId;
    window.location.href = url;
	
}