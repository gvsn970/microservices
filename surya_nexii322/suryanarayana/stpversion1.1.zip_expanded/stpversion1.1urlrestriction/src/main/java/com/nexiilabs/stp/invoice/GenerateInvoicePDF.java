package com.nexiilabs.stp.invoice;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class GenerateInvoicePDF {

	
	public static InvoiceRequestModel generateEmpInvoicePdf(InvoiceModel invoiceModel,String empName,String projectName,String currencyType,String clientName,String imagePath,String DEST, int printStatus) {

		String	pdfHtml=null;
		InvoiceRequestModel invoiceRequestModel=new InvoiceRequestModel();
		
				//System.out.println(imagePath+"......image path");
				//NumberToWord numberToWord=new NumberToWord();
			     DecimalFormat df = new DecimalFormat("#.00");
				 double amountBeforeTax=invoiceModel.getTotalAmount()-invoiceModel.getCgstAmount()-invoiceModel.getSgstAmount()-invoiceModel.getIgstAmount();
				 amountBeforeTax = Math.round(Double.parseDouble(df.format(amountBeforeTax)));
				 double rate = Math.round(Double.parseDouble(df.format(invoiceModel.getRate())));
				 double taxvalue =Math.round(Double.parseDouble(df.format(invoiceModel.getTaxableAmount())));
				 double cgstRate =0;
				 double cgstAmount =0;
				 double sgstRate =0;
				 double sgstAmount =0;
				 double igstRate =0;
				 double igstAmount =0;
				 double totalGst=0;
				 if(currencyType.equals("INR")){
					  cgstRate = Double.parseDouble(df.format(invoiceModel.getCgst_rate()));
					  cgstAmount = Math.round(Double.parseDouble(df.format(invoiceModel.getCgstAmount())));
					  sgstRate = Double.parseDouble(df.format(invoiceModel.getSgstRate()));
					  sgstAmount =Math.round(Double.parseDouble(df.format(invoiceModel.getSgstAmount())));
					  igstRate = Double.parseDouble(df.format(invoiceModel.getIgstRate()));
					  igstAmount = Math.round(Double.parseDouble(df.format(invoiceModel.getIgstAmount())));
					  totalGst=invoiceModel.getCgstAmount()+invoiceModel.getSgstAmount()+invoiceModel.getIgstAmount();
					  totalGst=Math.round(totalGst);
				 }
				 double totalAmount = Math.round(Double.parseDouble(df.format(invoiceModel.getTotalAmount())));
				 int totalAmount1=(int) totalAmount;
				 if(invoiceModel.getVendorAddLane1()==null){
					 invoiceModel.setVendorAddLane1(" ");
				 }
				 if(invoiceModel.getVendorAddLane2()==null){
					 invoiceModel.setVendorAddLane1(" ");
				 }
				 if(invoiceModel.getVendorAddState()==null){
					 invoiceModel.setVendorAddState(" ");
				 }
				 if(invoiceModel.getVendorAddPincode()==null){
					 invoiceModel.setVendorAddPincode(" ");
				 }
				 if(invoiceModel.getBillingAddLane1()==null){
					 invoiceModel.setBillingAddLane1(" ");
				 }
				 if(invoiceModel.getBillingAddLane2()==null){
					 invoiceModel.setBillingAddLane2(" ");
				 }
				 if(invoiceModel.getBillingAddState()==null){
					 invoiceModel.setBillingAddState(" ");
				 }
				 if(invoiceModel.getBillingAddPincode()==null){
					 invoiceModel.setBillingAddPincode(" ");;
				 }
				 if(invoiceModel.getShipAddLane1()==null){
					 invoiceModel.setShipAddLane1(" ");
				 }
				 if(invoiceModel.getShipAddLane2()==null){
					 invoiceModel.setShipAddLane2(" ");
				 }
				 if(invoiceModel.getShipAddState()==null){
					 invoiceModel.setShipAddState(" ");
				 }
				 if(invoiceModel.getShipAddPincode()==null){
					 invoiceModel.setShipAddPincode(" ");
				 } 
				 String words=NumberToWord.numberToWords(totalAmount1+"");
				 
				 String totalAmount2=""+totalAmount1;
				 String totalSymbol="";
					if(currencyType.equals("USD")){
						words="Dollars "+words+" Only";
						totalAmount2=""+totalAmount1;
						totalSymbol="&#036;";
					}else if(currencyType.equals("EUR")){
						words="Euros "+words+" Only";
						totalAmount2=""+totalAmount1;
						totalSymbol="&#8364;";
					}else{
						words="Rupees "+words+" Only";
						totalAmount2=""+totalAmount1;
						totalSymbol="Rs.";
					}
					System.out.println("amount 1==="+totalAmount2);
					if(printStatus==1){
						imagePath="";
					}
					String rateString="-     ";
					String taxableValueString="-     ";
					String cgstRateString="-     ";
					String cgstAmountString="-     ";
					String sgstRateString="-     ";
					String sgstAmountString="-     ";
					String igstRateString="-     ";
					String igstAmountString="-     ";
					String totalGstString="-     ";
					
					String rateTextAlign="text-align:center";
					String taxTextAlign="text-align:center";
					String cgstRateTextAlign="text-align:center";
					String sgstRateTextAlign="text-align:center";
					String igstRateTextAlign="text-align:center";
					String cgstAmountTextAlign="text-align:center";
					String sgstAmountTextAlign="text-align:center";
					String igstAmountTextAlign="text-align:center";
					String totalGSTTextAlign="text-align:center";
					if(rate==0){
						rateString="-";
						rateTextAlign="text-align:center";
					}else{
						rateString=String.format("%.2f", rate);
						rateTextAlign="text-align:right";
					}
					if(taxvalue==0){
						taxableValueString="-";
						taxTextAlign="text-align:center";
					}else{
						taxableValueString=String.format("%.2f", taxvalue);
						taxTextAlign="text-align:right";
					}
					if(cgstRate==0){
						cgstRateString="-";
						cgstRateTextAlign="text-align:center";
					}else{
						cgstRateString=String.format("%.2f", cgstRate)+"%";
						cgstRateTextAlign="text-align:right";
					}
					if(sgstRate==0){
						sgstRateString="-";
						sgstRateTextAlign="text-align:center";
					}else{
						sgstRateString=String.format("%.2f", sgstRate)+"%";
						sgstRateTextAlign="text-align:right";
					}
					if(igstRate==0){
						igstRateString="-";
						igstRateTextAlign="text-align:center";
					}else{
						igstRateString=String.format("%.2f", igstRate)+"%";
						igstRateTextAlign="text-align:right";
					}
					
					if(cgstAmount==0){
						cgstAmountString="-";
						cgstAmountTextAlign="text-align:center";
					}else{
						cgstAmountString=String.format("%.2f", cgstAmount);
						cgstAmountTextAlign="text-align:right";
					}
					if(sgstAmount==0){
						sgstAmountString="-";
						sgstAmountTextAlign="text-align:center";
					}else{
						sgstAmountString=String.format("%.2f", sgstAmount);
						sgstAmountTextAlign="text-align:right";
					}
					if(igstAmount==0){
						igstAmountString="-";
						igstAmountTextAlign="text-align:center";
						
					}else{
						igstAmountString=String.format("%.2f", igstAmount);
						igstAmountTextAlign="text-align:right";
					}
					if(totalGst==0){
						totalGstString="-";
						totalGSTTextAlign="text-align:center";
					}else{
						totalGstString=String.format("%.2f", totalGst);
						totalGSTTextAlign="text-align:right";
					}
					
					pdfHtml="<!doctye html>" + 
							"<html>" + 
							"<head>" + 
							//"<meta charset=\"utf-8\"/>" + 
							"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>" + 
							"<title>INVOICE</title>" + 
							"</head>" + 
							"" + 
							"<body style=\"color:#191919; font-size:10px; margin:0; padding:0; border-color:#000;\">" + 
							"<div style=\"background-clip: padding-box; background-color: #fff; width: 600px; font-family:Arial, Helvetica, sans-serif; padding: 20px !important;\">" + 
							"  <div style=\"padding-top: 20px; padding-bottom:20px;\"> <img src='"+imagePath+"' alt=\"Nexiilabs\" width=\"250\" style=\"float:right;vertical-align:top;\"/><br/></div>" + 
							"  <table cellspacing=\"0\" cellpadding=\"0\" width=\"700\"><tbody style=\"text-align:center;\" ><tr><td style=\" font-family:Arial, Helvetica, sans-serif; !important;font-size:12px;\"><strong>INVOICE</strong></td></tr></tbody> </table>" + 
							"  <table  width=\"700\" cellspacing=\"0\" cellpadding=\"0\" style=\"font-color:#191919; border:1.5px solid #000; border-color:#000;\">" + 
							"<tr><td>"+
							"  <table width=\"700\"  cellspacing=\"0\" cellpadding=\"0\" style=\"border-bottom-width: 1.5px; border-bottom-style: solid; border-bottom-color:#000;\">" + 
							"    <tr>" + 
							"      <td style=\"border-right-width: 1px; border-right-style: solid; border-right-color: gray;\"><table cellspacing=\"0\" cellpadding=\"0\" width=\"298\">" + 
							"          <tr>" + 
							"            <td style=\"vertical-align:bottom; padding:5px; padding-right-width:1px !important; font-size:10px;\"><strong>"+invoiceModel.getVendorAddLane1()+"</strong><br/>" + 
							"              "+invoiceModel.getVendorAddLane2()+",<br/>" +
							"              "+invoiceModel.getVendorAddState()+",<br/>" +
							"              "+invoiceModel.getVendorAddPincode()+".<br/>" +
							"              <p><strong style=\"font-size:11px;\">PAN No:</strong> "+invoiceModel.getOur_pan()+"<br/>" + 
							"                <strong style=\"font-size:11px;\">GSTIN:</strong> "+invoiceModel.getOurGstin()+"<br/>" + 
							"                <strong style=\"font-size:11px;\">State:</strong> "+invoiceModel.getOurState()+"<br/>" + 
							"                <strong style=\"font-size:11px;\">State Code:</strong> "+invoiceModel.getOurStateCode()+"</p></td>" + 
							/*"            <td style=\"vertical-align:bottom; padding:5px; padding-right:1px !important;font-size:11px;\"><p style=\"border:1px solid #000; border-right:0px !important;font-size:11px;\"><strong style=\"border-right:1px solid #000;font-size:11px;\">State Code:</strong> <i>36</i></p></td>" + 
			*/				"          </tr>" + 
							"        </table></td>" + 
							"      <td><table cellspacing=\"0\" cellpadding=\"0\" width=\"298\" height=\"100\">" + 
							"          <tr>" + 
							"            <td style=\"border-right-width: 1px; border-right-style: solid; border-right-color: gray; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray; vertical-align:top; height:60px; padding:5px;font-size:10px;\"><strong>Invoice No: "+invoiceModel.getInvoiceNumber()+" </strong></td>" + 
							"            <td style=\"border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray; vertical-align:top; height:60px; padding:5px;font-size:10px;\"><strong>Dated: "+invoiceModel.getInvoicePdfDate()+" </strong></td>" + 
							"          </tr>" + 
							"          <tr>" + 
							"            <td style=\"border-right-width: 1px; border-right-style: solid; border-right-color: gray; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray; vertical-align:top; height:40px; padding:5px;font-size:10px;\"><strong>Buyers Order No: "+invoiceModel.getPoNumber()+" </strong></td>" + 
							"            <td style=\"border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray; vertical-align:top; height:40px; padding:5px;font-size:10px;\"><strong>Dated:"+invoiceModel.getPoDate()+"</strong> </td>" + 
							"          </tr>" + 
							"          <tr>" + 
							"            <td style=\"border-right-width: 1px; border-right-style: solid; border-right-color: gray; vertical-align:top; padding:5px;font-size:10px;\"><strong>Supplier's Ref No: "+invoiceModel.getSupplierRefNumber()+"</strong></td>" + 
							"            <td style=\"vertical-align:top; padding:5px;font-size:10px;\">&nbsp;</td>" + 
							"          </tr>" + 
							"        </table></td>" + 
							"    </tr>" + 
							"  </table>" + 
							"  </td> </tr>" +
							"<tr><td>"+
							"  <table  width=\"700px\" cellspacing=\"0\" cellpadding=\"0\">" + 
							"    <tr>" + 
							"      <td width=\"346px\" style=\"border-right-width: 1px; border-right-style: solid; border-right-color: #000;\"><table cellspacing=\"0\" cellpadding=\"0\">" + 
							"          <tr>" + 
							"            <td style=\"vertical-align:bottom; padding:5px; font-size:10px;\"><strong>Bill To:</strong>" + 
							"              <p>"+clientName+",<br/>" + 
							"                "+invoiceModel.getBillingAddLane1()+",<br/>" + 
							"                "+invoiceModel.getBillingAddLane2()+",<br/>" + 
							"                "+invoiceModel.getBillingAddState()+",<br/>" + 
							"               "+invoiceModel.getBillingAddPincode()+"</p>" +
							"              <p><strong>PAN No:</strong> "+invoiceModel.getCustomerPan()+"<br/>" + 
							"                <strong>GSTIN:</strong> "+invoiceModel.getCustomerGstin()+"<br/>" + 
							"              <strong>State:</strong> "+invoiceModel.getCustomerState()+"<br/>" + 
							"              <strong>State Code:</strong> "+invoiceModel.getCustomerStateCode()+"</p></td>" + 
							/*"            <td style=\"vertical-align:bottom; padding:5px; padding-right:0px !important;font-size:11px;\"><p style=\"border:1px solid #000; border-right:0px !important;\"><strong style=\"border-right:1px solid #000;\">State Code:</strong> <i>xx</i></p></td>" + */
							"          </tr>" + 
							"        </table></td>" + 
							"      <td><table cellspacing=\"0\" cellpadding=\"0\">" + 
							"          <tr>" + 
							"            <td style=\"vertical-align:bottom; padding:5px; padding-right:0px !important;font-size:10px;\"><strong>Ship To:</strong>" + 
							"              <p>"+clientName+",<br/>" + 
							"                "+invoiceModel.getShipAddLane1()+",<br/>" + 
							"                "+invoiceModel.getShipAddLane2()+",<br/>" + 
							"                "+invoiceModel.getShipAddState()+",<br/>" + 
							"               "+invoiceModel.getShipAddPincode()+"</p>" +
							"              <p><strong>PAN No:</strong> "+invoiceModel.getCustomerPan()+"<br/>" + 
							"                <strong>GSTIN:</strong> "+invoiceModel.getCustomerGstin()+"<br/>" + 
							"              <strong>State:</strong> "+invoiceModel.getCustomerState()+"<br/>" +
							"              <strong>State Code:</strong> "+invoiceModel.getCustomerStateCode()+"</p></td>" +
							/*"            <td style=\"vertical-align:bottom; padding:5px; padding-right:0px !important;font-size:11px;\"><p style=\"border:1px solid #000; border-right:0px !important;\"><strong style=\"border-right:1px solid #000;\">State Code:</strong> <i>xx</i></p></td>" + */
							"          </tr>" + 
							"        </table></td>" + 
							"    </tr>" + 
							"  </table>" + 
							"  <!--Billing And Shipping Row Completed--> " + 
							"</td></tr>"+
							"<tr><td>"+
							"  <table rules=\"none\"  width=\"700px\" cellspacing=\"0\" cellpadding=\"0\" style=\"border-top-width: 1.5px; border-top-style: solid; border-top-color: #000; border-bottom-width: 1.5px; border-bottom-style: solid; border-bottom-color: #000;\">" + 
							"        <tr>" + 
							"          <td style=\"width:25px; background-color: #D0D0D0; border-right-width: 1px; border-right-style: solid; border-right-color: #000; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000; text-align:center; font-size:10px;\"><strong>Sr No</strong></td>" + 
							"          <td style=\"width:250px; background-color: #D0D0D0;  border-right-width: 1px; border-right-style: solid; border-right-color: #000; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000; text-align:center; font-size:10px;\"><strong>Description of services</strong></td>" + 
							"          <td style=\"width:60px; background-color: #D0D0D0; border-right-width: 1px; border-right-style: solid; border-right-color: #000; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000; text-align:center; font-size:10px;\"><strong>HSN SAC</strong></td>" + 
							"          <td style=\"width:35px; background-color: #D0D0D0;  border-right-width: 1px; border-right-style: solid; border-right-color: #000; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000; text-align:center; font-size:10px;\"><strong>Qty</strong></td>" + 
							"          <td style=\"width:90px; background-color: #D0D0D0;   border-right-width: 1px; border-right-style: solid; border-right-color: #000; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000; text-align:center; font-size:10px;\"><strong>Rate</strong></td>" + 
							"          <td style=\"width:90px; background-color: #D0D0D0;  border-right-width: 1px; border-right-style: solid; border-right-color: #000; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000; text-align:center; font-size:10px;\"><strong>Taxable value</strong></td>" + 
							/*"          <td colspan=\"2\"  cellspacing=\"0\" cellpadding=\"0\" style=\" width:120px; border-right-width: 1px; border-right-style: solid; border-right-color: #000; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000;font-size:10px;\">" + 
							"          <table cellspacing=\"0\" cellpadding=\"0\">" + 
							"              <tr>" + 
							"                <td colspan=\"2\" style=\" text-align:center;font-size:10px;"+cssStyle+" \"><strong>CGST</strong></td>" + 
							"              </tr>" + 
							"              <tr>" + 
							"                <td style=\" font-size:8px;  "+rateStyle+"\"><strong>Rate</strong></td>" + 
							"                <td style=\" font-size:8px;  "+amountStyle+"\"><strong>Amount</strong></td>" + 
							"              </tr>" + 
							"            </table>" + 
							"          </td>" + 
							*/
							"          <td colspan=\"2\" style=\" width:120px; border-right-width: 1px; border-right-style: solid; border-right-color: #000; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000;font-size:10px;\">" + 
							"          <table cellspacing=\"0\" cellpadding=\"0\" style=\"width:100%\">" + 
							"              <tr>" + 
							"                <td colspan=\"2\" scope=\"col\" style=\" border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000; text-align:center; font-size:10px; background-color: #D0D0D0; padding:10px;\"><strong>CGST</strong></td>" + 
							"              </tr>" + 
							"              <tr>" + 
							"                <td style=\" border-right-width: 1px; border-right-style: solid; width:58%; text-align:left; border-right-color: #000; font-size:10px; background-color: #D0D0D0; \"><strong>Rate</strong></td>" + 
							"                <td style=\" font-size:10px; width:65%; text-align:right; background-color: #D0D0D0; \"><strong>Amount</strong></td>" + 
							"              </tr>" + 
							"            </table>" + 
							"          </td>" + 
														
							
							
							"          <td colspan=\"2\" style=\"width:120px; border-right-width: 1px; border-right-style: solid; border-right-color: #000; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000;font-size:10px;\">" + 
							"          <table cellspacing=\"0\" cellpadding=\"0\" style=\"width:100%\">" + 
							"              <tr>" + 
							"                <td colspan=\"2\" style=\"  border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000; text-align:center; font-size:10px; background-color: #D0D0D0; padding:10px;\"><strong>SGST</strong></td>" + 
							"              </tr>" + 
							"              <tr>" + 
							"                <td style=\"border-right-width: 1px; border-right-style: solid; width:58%; text-align:left; border-right-color: #000; font-size:10px; background-color: #BEBEBE\"><strong>Rate</strong></td>" + 
							"                <td style=\"font-size:10px; width:65%; text-align:right; background-color: #D0D0D0;\"><strong>Amount</strong></td>" + 
							"              </tr>" + 
							"            </table>" + 
							"          </td>" + 
							"          <td colspan=\"2\" style=\"width:120px; border-right-width: 1px; border-right-style: solid; border-right-color: #000; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000;font-size:10px;\">" + 
							"          <table cellspacing=\"0\" cellpadding=\"0\" style=\"width:100% \">" + 
							"              <tr>" + 
							"                <td colspan=\"2\" style=\"margin: 0px auto; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000; text-align:center;font-size:10px; background-color: #D0D0D0; padding:10px;\"><strong>IGST</strong></td>" + 
							"              </tr>" + 
							"              <tr>" + 
							"                <td style=\"margin: 0px auto; border-right-width: 1px; border-right-style: solid; width:58%; text-align:left; border-right-color: #000;font-size:10px; background-color: #D0D0D0;\"><strong>Rate</strong></td>" + 
							"                <td style=\"margin: 0px auto; font-size:10px; width:65%; text-align:right; background-color: #D0D0D0;\"><strong>Amount</strong></td>" + 
							"              </tr>" + 
							"            </table>" + 
							"          </td>" + 
							"          <td style=\"width:90px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000; text-align:center; font-size:10px; background-color: #D0D0D0;\"><strong>Total ("+totalSymbol+")</strong></td>" + 
							"        </tr>" + 
							"      <tbody>" + 
							
							"        <tr>" + 
							"          <td style=\"width:20px;  border-right-width: 1px; border-right-style: solid; border-right-color: #000; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000;font-size:11px; vertical-align:top;\">01</td>" +  
							"          <td style=\"width:250px;  border-right-width: 1px; border-right-style: solid; border-right-color: #000; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000;font-size:11px; vertical-align:top;\"><strong>Services for </strong><br/><br/>"+projectName+"<br/>"+invoiceModel.getPeriod()+"<br/><strong>Resource:</strong><br/><br/>"+empName+"<br/><strong>Working Days:"+invoiceModel.getTotalWorkDays()+"</strong><br/><strong>Billing Days:"+invoiceModel.getBillingDays()+"</strong><br/><br/><br/><br/><br/></td>" + 
							"          <td style=\"width:60px;  border-right-width: 1px; border-right-style: solid; border-right-color: #000; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000;font-size:11px; vertical-align:top; text-align:right;\">"+invoiceModel.getHsnSac()+"</td>" +     
							"          <td style=\"width:35px;  border-right-width: 1px; border-right-style: solid; border-right-color: #000; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000;font-size:11px; vertical-align:top; text-align:right;\">"+invoiceModel.getQuantity()+"</td>" + 
							"          <td style=\"width:90px;  border-right-width: 1px; border-right-style: solid; border-right-color: #000; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000;font-size:11px; vertical-align:top; "+rateTextAlign+"\">"+rateString+"</td>" + 
							"          <td style=\"width:90px;  border-right-width: 1px; border-right-style: solid; border-right-color: #000; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000;font-size:11px; vertical-align:top; background-color: #D0D0D0; "+taxTextAlign+"\">"+taxableValueString+"</td>" + 
							"          <td style=\"width:55px;  border-right-width: 1px; border-right-style: solid; border-right-color: #000; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000;font-size:11px; vertical-align:top; "+cgstRateTextAlign+"\">"+cgstRateString+"</td>" + 
							"          <td style=\"width:65px;  border-right-width: 1px; border-right-style: solid; border-right-color: #000; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000;font-size:11px; vertical-align:top; "+cgstAmountTextAlign+"\">"+cgstAmountString+"</td>" + 
							"          <td style=\" width:35px; border-right-width: 1px; border-right-style: solid; border-right-color: #000; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000;font-size:11px; vertical-align:top; "+sgstRateTextAlign+"\">"+sgstRateString+"</td>" + 
							"          <td style=\"width:65px;  border-right-width: 1px; border-right-style: solid; border-right-color: #000; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000;font-size:11px; vertical-align:top; "+sgstAmountTextAlign+"\">"+sgstAmountString+"</td>" + 
							"          <td style=\"width:35px;  border-right-width: 1px; border-right-style: solid; border-right-color: #000; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000;font-size:11px; vertical-align:top; "+igstRateTextAlign+"\">"+igstRateString+"</td>" + 
							"          <td style=\"width:65px;  border-right-width: 1px; border-right-style: solid; border-right-color: #000; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000;font-size:11px; vertical-align:top; "+igstAmountTextAlign+"\">"+igstAmountString+"</td>" + 
							"          <td style=\" width:90px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000;font-size:10px; vertical-align:top; text-align:right;\"><strong>"+totalAmount1+"</strong></td>" + 
							"        </tr>" + 
							
							"        <tr style=\"background-color: #D0D0D0;\">" + 
							"          <td colspan=\"3\" style=\"text-align:right; padding:10px; border-right-width: 1px; border-right-style: solid; border-right-color: #000; font-size:10px;\"><strong>Total</strong></td>" + 
							"          <td style=\"padding:10px; border-right-width: 1px; border-right-style: solid; border-right-color: #000;font-size:10px;\">1</td>" + 
							"          <td style=\"padding:10px; border-right-width: 1px; border-right-style: solid; border-right-color: #000;font-size:10px;\"></td>" + 
							"          <td style=\"padding:10px; border-right-width: 1px; border-right-style: solid; border-right-color: #000;font-size:8px; "+taxTextAlign+"\">"+taxableValueString+"</td>" + 
							"          <td style=\"padding:10px; font-size:10px;\"></td>" + 
							"          <td style=\"width:65px; border-right-width: 1px; border-right-style: solid; border-right-color: #000;font-size:8px;"+cgstAmountTextAlign+"\">"+cgstAmountString+"</td>" + 
							"          <td style=\"padding:10px; font-size:10px;\"></td>" + 
							"          <td style=\"width:65px; border-right-width: 1px; border-right-style: solid; border-right-color: #000;font-size:8px;"+sgstAmountTextAlign+"\">"+sgstAmountString+"</td>" + 
							"          <td style=\"padding:10px; font-size:10px;\"></td>" + 
							"          <td style=\"width:65px; border-right-width: 1px; border-right-style: solid; border-right-color: #000;font-size:8px;"+igstAmountTextAlign+"\">"+igstAmountString+"</td>" + 
							"          <td style=\"padding:10px; font-size:10px; text-align:right;\"><strong>"+totalAmount2+"</strong></td>" + 
							"        </tr>" + 
							"      </tbody>" + 
							"    </table>" + 
							"  <!--Description Row Completed--> " + 
							"</td></tr>"+
							"<tr><td>"+
							"  <table  width=\"700\" cellspacing=\"0\" cellpadding=\"0\">" + 
							"    <tr>" + 
							"    " + 
							"    <td style=\"width:348px; vertical-align:top;font-size:10px;\">" + 
							"    <table cellspacing=\"0\" cellpadding=\"0\" style=\"width:348px;\">" + 
							"          <tr>" + 
							"            <td style=\"height:40px; padding:5px; border-top-width: 1px; border-top-style: solid; border-top-color: #000; border-right-width: 1px; border-right-style: solid; border-right-color: #000; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000; text-align:center; vertical-align:top; font-size:10px;\">" + 
							"            <strong>Total Invoice Amount in Words:<br/><br/>"+words+
							"            </strong></td>" + 
							"          </tr>" + 
							"          <tr>" + 
							"            <td style=\"height:30px; padding:5px;line-height: 0.5; border-right-width: 1px; border-right-style: solid; border-right-color: gray;border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000;font-size:9px;\">" + 
							"            <strong>Bank Details:<br/><br/></strong>" + 
							"               <br/>" + 
							"                Account Holder Name: "+invoiceModel.getAccountHolderName()+"<br/><br/>" + 
							"                Bank Name:    "+invoiceModel.getBankName()+"<br/><br/>" + 
							"                Bank Account Number: "+invoiceModel.getAccountNumber()+"<br/><br/>" + 
							"                IFSC: "+invoiceModel.getIfscCode()+"<br/>" + 
							"            </td>" + 
							"          </tr>" + 
							"          <tr>" + 
							"            <td style=\"height:59px; border-right-width: 1px; border-right-style: solid; border-right-color: gray; padding:5px;font-size:9px;\">" + 
							"           <strong>Declaraion:<br/><br/></strong><i>We declare that this invoice shows the actual price of the services described and that all particulars are true and correct </i>" + 
							"            </td>" + 
							"          </tr>" +
							"        </table>" + 
							"        </td>" + 
							
							"      <td style=\"border-right-width: 1px; border-right-style: solid; border-right-color: #000; width:248px; vertical-align:top;font-size:10px; font-color:#000;\">" + 
							"      <table cellspacing=\"0\" cellpadding=\"0\" style=\"width:400px; \">" + 
							"          <tr>" + 
							"          <td style=\"color:#000; padding:3px 10px; width:148px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000;border-right-width: 1px; border-right-style: solid; border-right-color: #000; border-top-width: 1px; border-top-style: solid; border-top-color: #000; font-size:10px; background-color: #D0D0D0;  font-color:#000\"><strong>Total Amount before Tax</strong></td>" + 
							"          <td style=\"text-align:right; padding-left:10px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000;border-right-width: 1px; border-right-style: solid; border-right-color: #000; border-top-width: 1px; border-top-style: solid; border-top-color: #000; font-size:10px; background-color: #D0D0D0; padding-right:5px;\"><strong>"+taxableValueString+"</strong></td>" + 
							"        </tr>" + 
							"        <tr>" + 
							"          <td style=\"color:#000; padding:3px 10px; width:148px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000; border-right-width: 1px; border-right-style: solid; border-right-color: #000; font-size:10px; font-color:#000\">Add CGST</td>" + 
							"          <td style=\"text-align:right; padding-left:10px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000; border-right-width: 1px; border-right-style: solid; border-right-color: #000; font-size:10px; padding-right:5px;\">"+cgstAmountString+"</td>" + 
							"        </tr>" + 
							"        <tr>" + 
							"          <td style=\"color:#000; padding:3px 10px; width:148px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000; border-right-width: 1px; border-right-style: solid; border-right-color: #000; font-size:10px; font-color:#000\">Add SGST</td>" + 
							"          <td style=\"text-align:right; padding-left:10px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000; border-right-width: 1px; border-right-style: solid; border-right-color: #000; font-size:10px; padding-right:5px;\">"+sgstAmountString+"</td>" + 
							"        </tr>" + 
							"        <tr>" + 
							"          <td style=\"color:#000; padding:3px 10px; width:148px; bordborder-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000; border-right-width: 1px; border-right-style: solid; border-right-color: #000; font-size:10px; font-color:#000\">Add IGST</td>" + 
							"          <td style=\"text-align:right; padding-left:10px; bordborder-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000; border-right-width: 1px; border-right-style: solid; border-right-color: #000; font-size:10px; padding-right:5px;\">"+igstAmountString+"</td>" + 
							"        </tr>" + 
							"        <tr>" + 
							"          <td style=\"color:#000; padding:3px 10px; width:148px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000; border-right-width: 1px; border-right-style: solid; border-right-color: #000; font-size:10px; background-color: #D0D0D0;  font-color:#000\"><strong>Total GST</strong></td>" + 
							"          <td style=\"text-align:right; padding-left:10px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000; border-right-width: 1px; border-right-style: solid; border-right-color: #000; font-size:10px; background-color: #D0D0D0; padding-right:5px;\"><strong>"+totalGstString+"</strong></td>" + 
							"        </tr>" + 
							"        <tr>" + 
							"          <td style=\"color:#000; padding:3px 10px; width:148px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000; border-right-width: 1px; border-right-style: solid; border-right-color: #000; font-size:10px; background-color: #D0D0D0;  font-color:#000\"><strong>Total Amount After Tax</strong></td>" + 
							"          <td style=\"text-align:right; padding-left:10px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000; border-right-width: 1px; border-right-style: solid; border-right-color: #000;  font-size:10px; background-color: #D0D0D0; padding-right:5px;\"><strong>"+totalAmount2+"</strong></td>" + 
							"        </tr>" + 
							"        <tr>" + 
							"          <td colspan=\"2\" style=\"color:#000; padding:7px 10px; text-align:right; width:248px;font-size:10px;  font-color:#000 \"><strong><i>For Nexii IT Labs Private Limited</i></strong></td>" + 
							"        </tr>" + 
							"        </table>" + 
							"        </td>" + 
							"      " + 
							"    </tr>" + 
							"  </table>" + 
							"</td>"+
							"</tr>"+
							"</table>" + 
							"  <table cellspacing=\"0\" cellpadding=\"0\" width=\"650\"><tbody style=\"text-align:center;\" ><tr><td style=\"vertical-align:right; padding-right:0px text-align:right; !important;font-size:10px;\"><i>Computer Generated Invoice, No Signature required</i></td></tr></tbody> </table>" + 
							"</div>" + 
							"</body>" + 
							"</html>";
				
		//System.out.println(".,......before pdf........."+pdfHtml);
				BufferedWriter bw = null;
				String invoiceNumber=invoiceModel.getInvoiceNumber();
				invoiceNumber=invoiceNumber.replace('/', '_');
				
				System.out.println(imagePath+"......image path");
				String HTML= DEST+invoiceNumber+"_"+invoiceModel.getFkEmployeeId()+"-stp.html";
				DEST = DEST+invoiceNumber+"_"+invoiceModel.getFkEmployeeId()+"-stp.pdf";
				System.out.println(imagePath+"......image path");
				
				System.out.println("DEST................"+DEST+".............................HTML.........."+HTML);
				try{
					File file = new File(HTML);
					file.getParentFile().mkdirs();
					file = new File(DEST);
					file.getParentFile().mkdirs();
					Document document = new Document();
					bw = new BufferedWriter(new FileWriter(HTML, false));
					bw.write(pdfHtml);
					bw.newLine();
					bw.flush();
					// step 2
					System.out.println("step2................................................");
					PdfWriter writer = PdfWriter.getInstance(document,
							new FileOutputStream(file));
					// step 3
					System.out.println("step3................................................");
					document.open();
					//document.setPageSize(PageSize.A4);
					// step 4
					System.out.println("step4................................................");
					XMLWorkerHelper.getInstance().parseXHtml(writer, document,
							new FileInputStream(HTML));
					document.close();
					System.err.println("Generated successfully--");
					//logger.info("Generated successfully");
					} catch (DocumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
					System.err.println("invoiceModel.getInvoiceId()..........."+invoiceModel.getInvoiceId());
				    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Date date = new Date();
					invoiceRequestModel.setInvoiceFilePath(DEST);
					invoiceRequestModel.setInvoiceFileName(invoiceModel.getInvoiceNumber()+"-stp.pdf");
					invoiceRequestModel.setInvoiceStatus(1);
					invoiceRequestModel.setGeneratedBy(invoiceModel.getCreatedBy());
			        invoiceRequestModel.setGeneratedOn(dateFormat.format(date));
			        invoiceRequestModel.setFkInvoiceId(invoiceModel.getInvoiceId());
			        invoiceRequestModel.setInvoiceRequestId(invoiceModel.getFkInvoiceRequestId());
		
		return invoiceRequestModel;
	}
	
	
	
	
	
	public static boolean generateSampleInvoice() {
		
		boolean flag=false;
		
		String imagePath="/home/nexii/dumps/Invoice-logo.png";
		String pdfHtml="<!doctype html>" + 
				"<html>" + 
				"<head>" + 
				//"<meta charset=\"utf-8\"/>" + 
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>" + 
				"<title>INVOICE</title>" + 
				"</head>" + 
				"" + 
				"<body style=\"color:#000; font-size:11px; margin:0; padding:0;\">" + 
				"<div style=\"background-clip: padding-box; background-color: #fff; width: 600px; font-family:Arial, Helvetica, sans-serif; padding: 20px !important;\">" + 
				"  <div style=\"padding-top: 20px; padding-bottom:20px;\"> <img src='"+imagePath+"' alt=\"Nexiilabs\" style=\"float:right;vertical-align:top;\"/><br/></div>" + 
				"  <h4 style=\"color: #333; font-size: 14px; line-height: 18px; margin: 0; padding: 10px 25px; text-align: center; font-family:Arial, Helvetica, sans-serif; font-weight:500;\"></h4>" + 
				"  <table  width=\"600\" cellspacing=\"0\" cellpadding=\"0\" style=\"border:1px solid #000;\">" + 
				"    <tr>" + 
				"      <td style=\"border-right: 1px solid #000;\"><table cellspacing=\"0\" cellpadding=\"0\" width=\"298\">" + 
				"          <tr>" + 
				"            <td style=\"vertical-align:bottom; padding:5px; padding-right:1px !important; font-size:11px;\"><strong>Nexii IT Labs Pvt. Ltd</strong><br/>" + 
				"              01-08, 1st Floor,<br/>" + 
				"              Block-B, Cyber Pearl, Hitechcity,<br/>" + 
				"              Madhapur,<br/>" + 
				"              Hyderabad-500081<br/>" + 
				"              Telangana, INDIA.<br/>" + 
				"              <p><strong style=\"font-size:11px;\">PAN No:</strong> AAECN0261B<br/>" + 
				"                <strong style=\"font-size:11px;\">GSTIN:</strong> 36AAECN0261B1Z1<br/>" + 
				"                <strong style=\"font-size:11px;\">State:</strong> Telangana<br/>" + 
				"                <strong style=\"font-size:11px;\">State Code:</strong> Telangana</p></td>" + 
				/*"            <td style=\"vertical-align:bottom; padding:5px; padding-right:1px !important;font-size:11px;\"><p style=\"border:1px solid #000; border-right:0px !important;font-size:11px;\"><strong style=\"border-right:1px solid #000;font-size:11px;\">State Code:</strong> <i>36</i></p></td>" + 
*/				"          </tr>" + 
				"        </table></td>" + 
				"      <td style=\"vertical-align:top;\"><table cellspacing=\"0\" cellpadding=\"0\" width=\"298\">" + 
				"          <tr>" + 
				"            <td style=\"border:1px solid #000;vertical-align:top; height:60px; padding:5px;font-size:11px;\">Invoice No: NEXII/17-18/11/0000<br/></td>" + 
				"            <td style=\"border:1px solid #000;vertical-align:top; height:60px; padding:5px;font-size:11px;\">Dated: 20-Dec-2017</td>" + 
				"          </tr>" + 
				"          <tr>" + 
				"            <td style=\"border:1px solid #000; vertical-align:top; height:60px; padding:5px;font-size:11px;\">Buyers Order No: xxxxxxxxxxx</td>" + 
				"            <td style=\"border:1px solid #000; vertical-align:top; height:60px; padding:5px;font-size:11px;\">Dated: 11-Dec-2017</td>" + 
				"          </tr>" + 
				"          <tr>" + 
				"            <td style=\"border:1px solid #000; vertical-align:top; height:60px; padding:5px;font-size:11px;\">Supplier's Ref No: xxxxxxxxxxxxx</td>" + 
				"            <td style=\"vertical-align:top; height:60px; padding:5px;font-size:11px;\">&nbsp;</td>" + 
				"          </tr>" + 
				"        </table></td>" + 
				"    </tr>" + 
				"  </table>" + 
				"  <!--Address And Invoice Row Completed-->" + 
				"  " + 
				"  <table  width=\"600\" cellspacing=\"0\" cellpadding=\"0\" style=\"border:1px solid #000;\">" + 
				"    <tr>" + 
				"      <td style=\"border-right: 1px solid #000;\"><table cellspacing=\"0\" cellpadding=\"0\">" + 
				"          <tr>" + 
				"            <td style=\"vertical-align:bottom; padding:5px; padding-right:0px !important;font-size:11px;\"><strong>Bill To:</strong>" + 
				"              <p>xxxxxxxxxxxxxxxxxxxxxxx,<br/>" + 
				"                xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx<br/>" + 
				"                xxxxxxxxxxxxxxxxxxxx<br/>" + 
				"                xxxxxxxxxxxxxxx<br/>" + 
				"              xxxxxxxxxxxxxxxxxxxx</p>" + 
				"              <p><strong>PAN No:</strong> xxxxxxxxxxxx<br/>" + 
				"                <strong>GSTIN:</strong> xxxxxxxxxxxxxxxx<br/>" + 
				"              <strong>State:</strong> xxxxxxxxxxxxx<br/>" + 
				"              <strong>State Code:</strong> xxxxxxxxxxxxx</p></td>" + 
				/*"            <td style=\"vertical-align:bottom; padding:5px; padding-right:0px !important;font-size:11px;\"><p style=\"border:1px solid #000; border-right:0px !important;\"><strong style=\"border-right:1px solid #000;\">State Code:</strong> <i>xx</i></p></td>" + */
				"          </tr>" + 
				"        </table></td>" + 
				"      <td><table cellspacing=\"0\" cellpadding=\"0\">" + 
				"          <tr>" + 
				"            <td style=\"vertical-align:bottom; padding:5px; padding-right:0px !important;font-size:11px;\"><strong>Ship To:</strong>" + 
				"              <p>xxxxxxxxxxxxxxxxxxxxxxx,<br/>" + 
				"                xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx<br/>" + 
				"                xxxxxxxxxxxxxxxxxxxx<br/>" + 
				"                xxxxxxxxxxxxxxx<br/>" + 
				"              xxxxxxxxxxxxxxxxxxxx</p>" + 
				"              <p><strong>PAN No:</strong> xxxxxxxxxxxx<br/>" + 
				"                <strong>GSTIN:</strong> xxxxxxxxxxxxxxxx<br/>" + 
				"              <strong>State:</strong> xxxxxxxxxxxxx<br/>" +
				"              <strong>State Code:</strong> xxxxxxxxxxxxx</p></td>" +
				/*"            <td style=\"vertical-align:bottom; padding:5px; padding-right:0px !important;font-size:11px;\"><p style=\"border:1px solid #000; border-right:0px !important;\"><strong style=\"border-right:1px solid #000;\">State Code:</strong> <i>xx</i></p></td>" + */
				"          </tr>" + 
				"        </table></td>" + 
				"    </tr>" + 
				"  </table>" + 
				"  <!--Billing And Shipping Row Completed--> " + 
				"  <table  width=\"600\" cellspacing=\"0\" cellpadding=\"0\" style=\"border:1px solid #000;\">" + 
				"      <thead>" + 
				"        <tr style=\"background-color: #d9edf7;\">" + 
				"          <td style=\"border-right-width: 1px; border-right-style: solid; border-right-color: gray; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;  font-size:11px;\">S.No.</td>" + 
				"          <td style=\"   border-right-width: 1px; border-right-style: solid; border-right-color: gray; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;\">Description of services</td>" + 
				"          <td style=\"   border-right-width: 1px; border-right-style: solid; border-right-color: gray; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;\">HSN SAC</td>" + 
				"          <td style=\"   border-right-width: 1px; border-right-style: solid; border-right-color: gray; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;\">Qty</td>" + 
				"          <td style=\"   border-right-width: 1px; border-right-style: solid; border-right-color: gray; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;\">Rate</td>" + 
				"          <td style=\"   border-right-width: 1px; border-right-style: solid; border-right-color: gray; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;\">Taxable value</td>" + 
				"          <td colspan=\"2\" style=\"border-right-width: 1px; border-right-style: solid; border-right-color: gray; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;\">" + 
				"          <table cellspacing=\"0\" cellpadding=\"0\">" + 
				"              <tr>" + 
				"                <td colspan=\"2\" style=\"border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray; text-align:center;font-size:11px;\">CGST</td>" + 
				"              </tr>" + 
				"              <tr>" + 
				"                <td style=\"border-right-width: 1px; border-right-style: solid; border-right-color: gray;font-size:11px;\">Rate</td>" + 
				"                <td style=\"font-size:11px;\">Amount</td>" + 
				"              </tr>" + 
				"            </table>" + 
				"          </td>" + 
				"          <td colspan=\"2\" style=\"border-right-width: 1px; border-right-style: solid; border-right-color: gray; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;\">" + 
				"          <table cellspacing=\"0\" cellpadding=\"0\">" + 
				"              <tr>" + 
				"                <td colspan=\"2\" style=\"border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray; text-align:center;font-size:11px;\">SGST</td>" + 
				"              </tr>" + 
				"              <tr>" + 
				"                <td style=\"border-right-width: 1px; border-right-style: solid; border-right-color: gray;font-size:11px;\">Rate</td>" + 
				"                <td style=\"font-size:11px;\">Amount</td>" + 
				"              </tr>" + 
				"            </table>" + 
				"          </td>" + 
				"          <td colspan=\"2\" style=\"border-right-width: 1px; border-right-style: solid; border-right-color: gray; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;\">" + 
				"          <table cellspacing=\"0\" cellpadding=\"0\">" + 
				"              <tr>" + 
				"                <td colspan=\"2\" style=\"border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray; text-align:center;font-size:11px;\">IGST</td>" + 
				"              </tr>" + 
				"              <tr>" + 
				"                <td style=\"border-right-width: 1px; border-right-style: solid; border-right-color: gray;font-size:11px;\">Rate</td>" + 
				"                <td style=\"font-size:11px;\">Amount</td>" + 
				"              </tr>" + 
				"            </table>" + 
				"          </td>" + 
				"          <td style=\"border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;\">Total Rs.</td>" + 
				"        </tr>" + 
				"      </thead>" + 
				"      <tbody>" + 
				/*"        <tr>" + 
				"          <td style=\"  border-right:1px solid #999; border-bottom:1px solid #999;font-size:11px;\">01</td>" + 
				"          <td style=\"  border-right:1px solid #999; border-bottom:1px solid #999;font-size:11px;\">Services for Dummy text dont read this.</td>" + 
				"          <td style=\"  border-right:1px solid #999; border-bottom:1px solid #999;font-size:11px;\">998313</td>" + 
				"          <td style=\"  border-right:1px solid #999; border-bottom:1px solid #999;font-size:11px;\">1</td>" + 
				"          <td style=\"  border-right:1px solid #999; border-bottom:1px solid #999;font-size:11px;\">-</td>" + 
				"          <td style=\"  border-right:1px solid #999; border-bottom:1px solid #999;font-size:11px;\">-</td>" + 
				"          <td style=\"  border-right:1px solid #999; border-bottom:1px solid #999;font-size:11px;\">0.00%</td>" + 
				"          <td style=\"  border-right:1px solid #999; border-bottom:1px solid #999;font-size:11px;\">-</td>" + 
				"          <td style=\"  border-right:1px solid #999; border-bottom:1px solid #999;font-size:11px;\">0.00%</td>" + 
				"          <td style=\"  border-right:1px solid #999; border-bottom:1px solid #999;font-size:11px;\">-</td>" + 
				"          <td style=\"  border-right:1px solid #999; border-bottom:1px solid #999;font-size:11px;\">0.00%</td>" + 
				"          <td style=\"  border-right:1px solid #999; border-bottom:1px solid #999;font-size:11px;\">-</td>" + 
				"          <td style=\"  border-bottom:1px solid #999;\">-</td>" + 
				"        </tr>" + */
				"        <tr>" + 
				"          <td style=\"  border-right-width: 1px; border-right-style: solid; border-right-color: gray; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;\">01</td>" + 
				"          <td style=\"  border-right-width: 1px; border-right-style: solid; border-right-color: gray; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;\">Resources for Dummy text dont read this.</td>" + 
				"          <td style=\"  border-right-width: 1px; border-right-style: solid; border-right-color: gray; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;\">998313</td>" + 
				"          <td style=\"  border-right-width: 1px; border-right-style: solid; border-right-color: gray; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;\">1</td>" + 
				"          <td style=\"  border-right-width: 1px; border-right-style: solid; border-right-color: gray; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;\">-</td>" + 
				"          <td style=\"  border-right-width: 1px; border-right-style: solid; border-right-color: gray; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;\">-</td>" + 
				"          <td style=\"  border-right-width: 1px; border-right-style: solid; border-right-color: gray; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;\">0.00%</td>" + 
				"          <td style=\"  border-right-width: 1px; border-right-style: solid; border-right-color: gray; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;\">-</td>" + 
				"          <td style=\"  border-right-width: 1px; border-right-style: solid; border-right-color: gray; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;\">0.00%</td>" + 
				"          <td style=\"  border-right-width: 1px; border-right-style: solid; border-right-color: gray; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;\">-</td>" + 
				"          <td style=\"  border-right-width: 1px; border-right-style: solid; border-right-color: gray; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;\">0.00%</td>" + 
				"          <td style=\"  border-right-width: 1px; border-right-style: solid; border-right-color: gray; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;\">-</td>" + 
				"          <td style=\"  border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;\">-</td>" + 
				"        </tr>" + 
				"        <tr style=\"background-color: #d9edf7;\">" + 
				"          <td colspan=\"3\" style=\"text-align:right; padding:20px 10px; border-right:1px solid #999;font-size:11px;\">Total</td>" + 
				"          <td style=\"padding:20px 10px; border-right-width: 1px; border-right-style: solid; border-right-color: gray;font-size:11px;\">2</td>" + 
				"          <td style=\"padding:20px 10px; border-right-width: 1px; border-right-style: solid; border-right-color: gray;font-size:11px;\"></td>" + 
				"          <td style=\"padding:20px 10px; border-right-width: 1px; border-right-style: solid; border-right-color: gray;font-size:11px;\"></td>" + 
				"          <td style=\"padding:20px 10px; border-right-width: 1px; border-right-style: solid; border-right-color: gray;font-size:11px;\"></td>" + 
				"          <td style=\"padding:20px 10px; border-right-width: 1px; border-right-style: solid; border-right-color: gray;font-size:11px;\"></td>" + 
				"          <td style=\"padding:20px 10px; border-right-width: 1px; border-right-style: solid; border-right-color: gray;font-size:11px;\"></td>" + 
				"          <td style=\"padding:20px 10px; border-right-width: 1px; border-right-style: solid; border-right-color: gray;font-size:11px;\"></td>" + 
				"          <td style=\"padding:20px 10px; border-right-width: 1px; border-right-style: solid; border-right-color: gray;font-size:11px;\"></td>" + 
				"          <td style=\"padding:20px 10px; border-right-width: 1px; border-right-style: solid; border-right-color: gray;font-size:11px;\"></td>" + 
				"          <td style=\"padding:20px 10px;font-size:11px;\"></td>" + 
				"        </tr>" + 
				"      </tbody>" + 
				"    </table>" + 
				"  <!--Description Row Completed--> " + 
				"  " + 
				"  " + 
				"  " + 
				"  " + 
				"  <table  width=\"600\" cellspacing=\"0\" cellpadding=\"0\" style=\"border:1px solid #000;\">" + 
				"    <tr>" + 
				"    " + 
				"    <td style=\"width:348px; vertical-align:top;font-size:11px;\">" + 
				"    <table cellspacing=\"0\" cellpadding=\"0\" style=\"width:348px;\">" + 
				"          <tr>" + 
				"            <td style=\" border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000;border-right-width: 1px; border-right-style: solid; border-right-color: gray; vertical-align:top; height:40px; padding:5px; text-align:center;font-size:11px;\">" + 
				"            Total Invoice Amount in Words<br/><br/>"+NumberToWord.numberToWords("5432")+
				/*"        	xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" +*/ 
				"            </td>" + 
				"          </tr>" + 
				"          <tr>" + 
				"            <td style=\"border-right-width: 1px; border-right-style: solid; border-right-color: gray; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray; vertical-align:top; height:70px; padding:5px;font-size:11px;\">" + 
				"            Bank Details:<br/><br/>Bccount Holder Name: 00001<br/><br/>" + 
				"                Bank Name: 00001<br/><br/>" + 
				"                Bank Account Number: 00001<br/><br/>" + 
				"                IFSC: 00001<br/>" + 
				"            </td>" + 
				"          </tr>" + 
				"          <tr>" + 
				"            <td style=\"border-right-width: 1px; border-right-style: solid; border-right-color: gray; vertical-align:top; height:59px; padding:5px;font-size:11px;\">" + 
				"           Declaraion:<br/>We declare that this invoice shows the actual price of the services described and that all particulars are true and correct " + 
				"            </td>" + 
				"          </tr>" + 
				"        </table>" + 
				"        </td>" + 
				"    " + 
				"    " + 
				"      <td style=\"border-right-width: 1px; border-right-style: solid; border-right-color: gray; width:248px; vertical-align:top;font-size:11px;\">" + 
				"      <table cellspacing=\"0\" cellpadding=\"0\" style=\"width:248px;\">" + 
				"          <tr>" + 
				"          <td style=\"background-color:#0f60ab; color:#ffffff; padding:7px 10px; width:148px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;\">Total Amount before Tax</td>" + 
				"          <td style=\"padding-left:10px; border:1px solid #000;font-size:11px;\">xxxxxx</td>" + 
				"        </tr>" + 
				"        <tr>" + 
				"          <td style=\"background-color:#4c5667; color:#ffffff; padding:7px 10px; width:148px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;\">Add CGST</td>" + 
				"          <td style=\"padding-left:10px; border:1px solid #000;font-size:11px;\">xxxxxx</td>" + 
				"        </tr>" + 
				"        <tr>" + 
				"          <td style=\"background-color:#4c5667; color:#ffffff; padding:7px 10px; width:148px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;font-size:11px;\">Add SGST</td>" + 
				"          <td style=\"padding-left:10px; border:1px solid #000;font-size:11px;\">xxxxxx</td>" + 
				"        </tr>" + 
				"        <tr>" + 
				"          <td style=\"background-color:#4c5667; color:#ffffff; padding:7px 10px; width:148px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;font-size:11px;\">Add IGST</td>" + 
				"          <td style=\"padding-left:10px; border:1px solid #000;font-size:11px;\">xxxxxx</td>" + 
				"        </tr>" + 
				"        <tr>" + 
				"          <td style=\"background-color:#4c5667; color:#ffffff; padding:7px 10px; width:148px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;font-size:11px;\">Total GST</td>" + 
				"          <td style=\"padding-left:10px; border:1px solid #000;font-size:11px;\">xxxxxx</td>" + 
				"        </tr>" + 
				"        <tr>" + 
				"          <td style=\"background-color:#0f60ab; color:#ffffff; padding:7px 10px; width:148px;font-size:11px;\">Total Amount After Tax</td>" + 
				"          <td style=\"padding-left:10px;font-size:11px;\">xxxxxx</td>" + 
				"        </tr>" + 
				"        <tr>" + 
				"          <td colspan=\"2\" style=\"background-color:#81c868; color:#ffffff; padding:7px 10px; text-align:center; width:248px;font-size:11px;\"><strong>For Nexii IT Labs Private Limited</strong></td>" + 
				"        </tr>" + 
				"        </table>" + 
				"        </td>" + 
				"      " + 
				"    </tr>" + 
				"  </table>" + 
				"  <!--<div style=\"padding: 20px; width:980px;\"> <a href=\"#\" style=\"background-color: #81c868; border: 1px solid #81c868; border-radius: 3px; padding:10px 20px; color:#ffffff; text-decoration:none;\"><strong>Generate</strong></a> </div>--> " + 
				"</div>" + 
				"</body>" + 
				"</html>";
	/*	String pdfHtml="<!doctype html>" + 
				"<html>" + 
				"<head>" + 
				//"<meta charset=\"utf-8\"/>" + 
				"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>" + 
				"<title>INVOICE</title>" + 
				"</head>" + 
				"" + 
				"<body style=\"color:#000; font-size:11px; margin:0; padding:0;\">" + 
				"<div style=\"background-clip: padding-box; background-color: #fff; width: 600px; font-family:Arial, Helvetica, sans-serif; padding: 20px !important;\">" + 
				"  <div style=\"padding-top: 20px; padding-bottom:20px;\"> <img src='"+imagePath+"' alt=\"Nexiilabs\" style=\"float:right;vertical-align:top;\"/><br/></div>" + 
				"  <h4 style=\"color: #333; font-size: 14px; line-height: 18px; margin: 0; padding: 10px 25px; text-align: center; font-family:Arial, Helvetica, sans-serif; font-weight:500;\"></h4>" + 
				"  <table  width=\"600\" cellspacing=\"0\" cellpadding=\"0\" style=\"border:1px solid #000;\">" + 
				"    <tr>" + 
				"      <td style=\"border-right: 1px solid #000;\"><table cellspacing=\"0\" cellpadding=\"0\" width=\"298\">" + 
				"          <tr>" + 
				"            <td style=\"vertical-align:bottom; padding:5px; padding-right:1px !important; font-size:11px;\"><strong>Nexii IT Labs Pvt. Ltd</strong><br/>" + 
				"              01-08, 1st Floor,<br/>" + 
				"              Block-B, Cyber Pearl, Hitechcity,<br/>" + 
				"              Madhapur,<br/>" + 
				"              Hyderabad-500081<br/>" + 
				"              Telangana, INDIA.<br/>" + 
				"              <p><strong style=\"font-size:11px;\">PAN No:</strong> AAECN0261B<br/>" + 
				"                <strong style=\"font-size:11px;\">GSTIN:</strong> 36AAECN0261B1Z1<br/>" + 
				"                <strong style=\"font-size:11px;\">State:</strong> Telangana<br/>" + 
				"                <strong style=\"font-size:11px;\">State Code:</strong> Telangana</p></td>" + 
				"            <td style=\"vertical-align:bottom; padding:5px; padding-right:1px !important;font-size:11px;\"><p style=\"border:1px solid #000; border-right:0px !important;font-size:11px;\"><strong style=\"border-right:1px solid #000;font-size:11px;\">State Code:</strong> <i>36</i></p></td>" + 
				"          </tr>" + 
				"        </table></td>" + 
				"      <td style=\"vertical-align:top;\"><table cellspacing=\"0\" cellpadding=\"0\" width=\"298\">" + 
				"          <tr>" + 
				"            <td style=\"border:1px solid #000;vertical-align:top; height:60px; padding:5px;font-size:11px;\">Invoice No: NEXII/17-18/11/0000<br/></td>" + 
				"            <td style=\"border:1px solid #000;vertical-align:top; height:60px; padding:5px;font-size:11px;\">Dated: 20-Dec-2017</td>" + 
				"          </tr>" + 
				"          <tr>" + 
				"            <td style=\"border:1px solid #000; vertical-align:top; height:60px; padding:5px;font-size:11px;\">Buyers Order No: xxxxxxxxxxx</td>" + 
				"            <td style=\"border:1px solid #000; vertical-align:top; height:60px; padding:5px;font-size:11px;\">Dated: 11-Dec-2017</td>" + 
				"          </tr>" + 
				"          <tr>" + 
				"            <td style=\"border:1px solid #000; vertical-align:top; height:60px; padding:5px;font-size:11px;\">Supplier's Ref No: xxxxxxxxxxxxx</td>" + 
				"            <td style=\"vertical-align:top; height:60px; padding:5px;font-size:11px;\">&nbsp;</td>" + 
				"          </tr>" + 
				"        </table></td>" + 
				"    </tr>" + 
				"  </table>" + 
				"  <!--Address And Invoice Row Completed-->" + 
				"  " + 
				"  <table  width=\"600\" cellspacing=\"0\" cellpadding=\"0\" style=\"border:1px solid #000;\">" + 
				"    <tr>" + 
				"      <td style=\"border-right: 1px solid #000;\"><table cellspacing=\"0\" cellpadding=\"0\">" + 
				"          <tr>" + 
				"            <td style=\"vertical-align:bottom; padding:5px; padding-right:0px !important;font-size:11px;\"><strong>Bill To:</strong>" + 
				"              <p>xxxxxxxxxxxxxxxxxxxxxxx,<br/>" + 
				"                xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx<br/>" + 
				"                xxxxxxxxxxxxxxxxxxxx<br/>" + 
				"                xxxxxxxxxxxxxxx<br/>" + 
				"              xxxxxxxxxxxxxxxxxxxx</p>" + 
				"              <p><strong>PAN No:</strong> xxxxxxxxxxxx<br/>" + 
				"                <strong>GSTIN:</strong> xxxxxxxxxxxxxxxx<br/>" + 
				"              <strong>State:</strong> xxxxxxxxxxxxx<br/>" + 
				"              <strong>State Code:</strong> xxxxxxxxxxxxx</p></td>" + 
				"            <td style=\"vertical-align:bottom; padding:5px; padding-right:0px !important;font-size:11px;\"><p style=\"border:1px solid #000; border-right:0px !important;\"><strong style=\"border-right:1px solid #000;\">State Code:</strong> <i>xx</i></p></td>" + 
				"          </tr>" + 
				"        </table></td>" + 
				"      <td><table cellspacing=\"0\" cellpadding=\"0\">" + 
				"          <tr>" + 
				"            <td style=\"vertical-align:bottom; padding:5px; padding-right:0px !important;font-size:11px;\"><strong>Ship To:</strong>" + 
				"              <p>xxxxxxxxxxxxxxxxxxxxxxx,<br/>" + 
				"                xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx<br/>" + 
				"                xxxxxxxxxxxxxxxxxxxx<br/>" + 
				"                xxxxxxxxxxxxxxx<br/>" + 
				"              xxxxxxxxxxxxxxxxxxxx</p>" + 
				"              <p><strong>PAN No:</strong> xxxxxxxxxxxx<br/>" + 
				"                <strong>GSTIN:</strong> xxxxxxxxxxxxxxxx<br/>" + 
				"              <strong>State:</strong> xxxxxxxxxxxxx<br/>" +
				"              <strong>State Code:</strong> xxxxxxxxxxxxx</p></td>" +
				"            <td style=\"vertical-align:bottom; padding:5px; padding-right:0px !important;font-size:11px;\"><p style=\"border:1px solid #000; border-right:0px !important;\"><strong style=\"border-right:1px solid #000;\">State Code:</strong> <i>xx</i></p></td>" + 
				"          </tr>" + 
				"        </table></td>" + 
				"    </tr>" + 
				"  </table>" + 
				"  <!--Billing And Shipping Row Completed--> " + 
				"  <table  width=\"600\" cellspacing=\"0\" cellpadding=\"0\" style=\"border:1px solid #000;\">" + 
				"      <thead>" + 
				"        <tr style=\"background-color: #d9edf7;\">" + 
				"          <td style=\"border-right-width: 1px; border-right-style: solid; border-right-color: gray; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;  font-size:11px;\">S.No.</td>" + 
				"          <td style=\"   border-right-width: 1px; border-right-style: solid; border-right-color: gray; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;\">Description of services</td>" + 
				"          <td style=\"   border-right-width: 1px; border-right-style: solid; border-right-color: gray; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;\">HSN SAC</td>" + 
				"          <td style=\"   border-right-width: 1px; border-right-style: solid; border-right-color: gray; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;\">Qty</td>" + 
				"          <td style=\"   border-right-width: 1px; border-right-style: solid; border-right-color: gray; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;\">Rate</td>" + 
				"          <td style=\"   border-right-width: 1px; border-right-style: solid; border-right-color: gray; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;\">Taxable value</td>" + 
				"          <td colspan=\"2\" style=\"border-right:1px solid #999; border-bottom:1px solid #999;font-size:11px;\">" + 
				"          <table cellspacing=\"0\" cellpadding=\"0\">" + 
				"              <tr>" + 
				"                <td colspan=\"2\" style=\"border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray; text-align:center;font-size:11px;\">CGST</td>" + 
				"              </tr>" + 
				"              <tr>" + 
				"                <td style=\"border-right-width: 1px; border-right-style: solid; border-right-color: gray;font-size:11px;\">Rate</td>" + 
				"                <td style=\"font-size:11px;\">Amount</td>" + 
				"              </tr>" + 
				"            </table>" + 
				"          </td>" + 
				"          <td colspan=\"2\" style=\"border-right-width: 1px; border-right-style: solid; border-right-color: gray; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;\">" + 
				"          <table cellspacing=\"0\" cellpadding=\"0\">" + 
				"              <tr>" + 
				"                <td colspan=\"2\" style=\"border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray; text-align:center;font-size:11px;\">SGST</td>" + 
				"              </tr>" + 
				"              <tr>" + 
				"                <td style=\"border-right-width: 1px; border-right-style: solid; border-right-color: gray;font-size:11px;\">Rate</td>" + 
				"                <td style=\"font-size:11px;\">Amount</td>" + 
				"              </tr>" + 
				"            </table>" + 
				"          </td>" + 
				"          <td colspan=\"2\" style=\"border-right-width: 1px; border-right-style: solid; border-right-color: gray; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;\">" + 
				"          <table cellspacing=\"0\" cellpadding=\"0\">" + 
				"              <tr>" + 
				"                <td colspan=\"2\" style=\"border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray; text-align:center;font-size:11px;\">IGST</td>" + 
				"              </tr>" + 
				"              <tr>" + 
				"                <td style=\"border-right-width: 1px; border-right-style: solid; border-right-color: gray;font-size:11px;\">Rate</td>" + 
				"                <td style=\"font-size:11px;\">Amount</td>" + 
				"              </tr>" + 
				"            </table>" + 
				"          </td>" + 
				"          <td style=\"border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;\">Total Rs.</td>" + 
				"        </tr>" + 
				"      </thead>" + 
				"      <tbody>" + 
				"        <tr>" + 
				"          <td style=\"  border-right:1px solid #999; border-bottom:1px solid #999;font-size:11px;\">01</td>" + 
				"          <td style=\"  border-right:1px solid #999; border-bottom:1px solid #999;font-size:11px;\">Services for Dummy text dont read this.</td>" + 
				"          <td style=\"  border-right:1px solid #999; border-bottom:1px solid #999;font-size:11px;\">998313</td>" + 
				"          <td style=\"  border-right:1px solid #999; border-bottom:1px solid #999;font-size:11px;\">1</td>" + 
				"          <td style=\"  border-right:1px solid #999; border-bottom:1px solid #999;font-size:11px;\">-</td>" + 
				"          <td style=\"  border-right:1px solid #999; border-bottom:1px solid #999;font-size:11px;\">-</td>" + 
				"          <td style=\"  border-right:1px solid #999; border-bottom:1px solid #999;font-size:11px;\">0.00%</td>" + 
				"          <td style=\"  border-right:1px solid #999; border-bottom:1px solid #999;font-size:11px;\">-</td>" + 
				"          <td style=\"  border-right:1px solid #999; border-bottom:1px solid #999;font-size:11px;\">0.00%</td>" + 
				"          <td style=\"  border-right:1px solid #999; border-bottom:1px solid #999;font-size:11px;\">-</td>" + 
				"          <td style=\"  border-right:1px solid #999; border-bottom:1px solid #999;font-size:11px;\">0.00%</td>" + 
				"          <td style=\"  border-right:1px solid #999; border-bottom:1px solid #999;font-size:11px;\">-</td>" + 
				"          <td style=\"  border-bottom:1px solid #999;\">-</td>" + 
				"        </tr>" + 
				"        <tr>" + 
				"          <td style=\"  border-right-width: 1px; border-right-style: solid; border-right-color: gray; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;\">01</td>" + 
				"          <td style=\"  border-right-width: 1px; border-right-style: solid; border-right-color: gray; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;\">Resources for Dummy text dont read this.</td>" + 
				"          <td style=\"  border-right-width: 1px; border-right-style: solid; border-right-color: gray; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;\">998313</td>" + 
				"          <td style=\"  border-right-width: 1px; border-right-style: solid; border-right-color: gray; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;\">1</td>" + 
				"          <td style=\"  border-right-width: 1px; border-right-style: solid; border-right-color: gray; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;\">-</td>" + 
				"          <td style=\"  border-right-width: 1px; border-right-style: solid; border-right-color: gray; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;\">-</td>" + 
				"          <td style=\"  border-right-width: 1px; border-right-style: solid; border-right-color: gray; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;\">0.00%</td>" + 
				"          <td style=\"  border-right-width: 1px; border-right-style: solid; border-right-color: gray; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;\">-</td>" + 
				"          <td style=\"  border-right-width: 1px; border-right-style: solid; border-right-color: gray; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;\">0.00%</td>" + 
				"          <td style=\"  border-right-width: 1px; border-right-style: solid; border-right-color: gray; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;\">-</td>" + 
				"          <td style=\"  border-right-width: 1px; border-right-style: solid; border-right-color: gray; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;\">0.00%</td>" + 
				"          <td style=\"  border-right-width: 1px; border-right-style: solid; border-right-color: gray; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;\">-</td>" + 
				"          <td style=\"  border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;\">-</td>" + 
				"        </tr>" + 
				"        <tr style=\"background-color: #d9edf7;\">" + 
				"          <td colspan=\"3\" style=\"text-align:right; padding:20px 10px; border-right:1px solid #999;font-size:11px;\">Total</td>" + 
				"          <td style=\"padding:20px 10px; border-right-width: 1px; border-right-style: solid; border-right-color: gray;font-size:11px;\">2</td>" + 
				"          <td style=\"padding:20px 10px; border-right-width: 1px; border-right-style: solid; border-right-color: gray;font-size:11px;\"></td>" + 
				"          <td style=\"padding:20px 10px; border-right-width: 1px; border-right-style: solid; border-right-color: gray;font-size:11px;\"></td>" + 
				"          <td style=\"padding:20px 10px; border-right-width: 1px; border-right-style: solid; border-right-color: gray;font-size:11px;\"></td>" + 
				"          <td style=\"padding:20px 10px; border-right-width: 1px; border-right-style: solid; border-right-color: gray;font-size:11px;\"></td>" + 
				"          <td style=\"padding:20px 10px; border-right-width: 1px; border-right-style: solid; border-right-color: gray;font-size:11px;\"></td>" + 
				"          <td style=\"padding:20px 10px; border-right-width: 1px; border-right-style: solid; border-right-color: gray;font-size:11px;\"></td>" + 
				"          <td style=\"padding:20px 10px; border-right-width: 1px; border-right-style: solid; border-right-color: gray;font-size:11px;\"></td>" + 
				"          <td style=\"padding:20px 10px; border-right-width: 1px; border-right-style: solid; border-right-color: gray;font-size:11px;\"></td>" + 
				"          <td style=\"padding:20px 10px;font-size:11px;\"></td>" + 
				"        </tr>" + 
				"      </tbody>" + 
				"    </table>" + 
				"  <!--Description Row Completed--> " + 
				"  " + 
				"  " + 
				"  " + 
				"  " + 
				"  <table  width=\"600\" cellspacing=\"0\" cellpadding=\"0\" style=\"border:1px solid #000;\">" + 
				"    <tr>" + 
				"    " + 
				"    <td style=\"width:348px; vertical-align:top;font-size:11px;\">" + 
				"    <table cellspacing=\"0\" cellpadding=\"0\" style=\"width:348px;\">" + 
				"          <tr>" + 
				"            <td style=\" border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: #000;border-right-width: 1px; border-right-style: solid; border-right-color: gray; vertical-align:top; height:40px; padding:5px; text-align:center;font-size:11px;\">" + 
				"            Total Invoice Amount in Words<br/><br/>"+NumberToWord.convertToIndianCurrency("5432")+
				"        	xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx" + 
				"            </td>" + 
				"          </tr>" + 
				"          <tr>" + 
				"            <td style=\"border-right-width: 1px; border-right-style: solid; border-right-color: gray; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray; vertical-align:top; height:70px; padding:5px;font-size:11px;\">" + 
				"            Bank Details<br/>" + 
				"                Account Holder Name: 00001<br/><br/>" + 
				"                Bank Name: 00001<br/><br/>" + 
				"                Bank Account Number: 00001<br/><br/>" + 
				"                IFSC: 00001<br/>" + 
				"            </td>" + 
				"          </tr>" + 
				"          <tr>" + 
				"            <td style=\"border-right-width: 1px; border-right-style: solid; border-right-color: gray; vertical-align:top; height:59px; padding:5px;font-size:11px;\">" + 
				"           Declaraion:<br/>We declare that this invoice shows the actual price of the services described and that all particulars are true and correct " + 
				"            </td>" + 
				"          </tr>" + 
				"        </table>" + 
				"        </td>" + 
				"    " + 
				"    " + 
				"      <td style=\"border-right-width: 1px; border-right-style: solid; border-right-color: gray; width:248px; vertical-align:top;font-size:11px;\">" + 
				"      <table cellspacing=\"0\" cellpadding=\"0\" style=\"width:248px;\">" + 
				"          <tr>" + 
				"          <td style=\"background-color:#0f60ab; color:#ffffff; padding:7px 10px; width:148px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;\">Total Amount before Tax</td>" + 
				"          <td style=\"padding-left:10px; border:1px solid #000;font-size:11px;\">xxxxxx</td>" + 
				"        </tr>" + 
				"        <tr>" + 
				"          <td style=\"background-color:#4c5667; color:#ffffff; padding:7px 10px; width:148px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;\">Add CGST</td>" + 
				"          <td style=\"padding-left:10px; border:1px solid #000;font-size:11px;\">xxxxxx</td>" + 
				"        </tr>" + 
				"        <tr>" + 
				"          <td style=\"background-color:#4c5667; color:#ffffff; padding:7px 10px; width:148px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;font-size:11px;\">Add SGST</td>" + 
				"          <td style=\"padding-left:10px; border:1px solid #000;font-size:11px;\">xxxxxx</td>" + 
				"        </tr>" + 
				"        <tr>" + 
				"          <td style=\"background-color:#4c5667; color:#ffffff; padding:7px 10px; width:148px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;font-size:11px;\">Add IGST</td>" + 
				"          <td style=\"padding-left:10px; border:1px solid #000;font-size:11px;\">xxxxxx</td>" + 
				"        </tr>" + 
				"        <tr>" + 
				"          <td style=\"background-color:#4c5667; color:#ffffff; padding:7px 10px; width:148px; border-bottom-width: 1px; border-bottom-style: solid; border-bottom-color: gray;font-size:11px;font-size:11px;\">Total GST</td>" + 
				"          <td style=\"padding-left:10px; border:1px solid #000;font-size:11px;\">xxxxxx</td>" + 
				"        </tr>" + 
				"        <tr>" + 
				"          <td style=\"background-color:#0f60ab; color:#ffffff; padding:7px 10px; width:148px;font-size:11px;\">Total Amount After Tax</td>" + 
				"          <td style=\"padding-left:10px;font-size:11px;\">xxxxxx</td>" + 
				"        </tr>" + 
				"        <tr>" + 
				"          <td colspan=\"2\" style=\"background-color:#81c868; color:#ffffff; padding:7px 10px; text-align:center; width:248px;font-size:11px;\"><strong>For Nexii IT Labs Private Limited</strong></td>" + 
				"        </tr>" + 
				"        </table>" + 
				"        </td>" + 
				"      " + 
				"    </tr>" + 
				"  </table>" + 
				"  <!--<div style=\"padding: 20px; width:980px;\"> <a href=\"#\" style=\"background-color: #81c868; border: 1px solid #81c868; border-radius: 3px; padding:10px 20px; color:#ffffff; text-decoration:none;\"><strong>Generate</strong></a> </div>--> " + 
				"</div>" + 
				"</body>" + 
				"</html>";*/
		BufferedWriter bw = null;
		String DEST = "/home/nexii/dumps/sample-stp.pdf";
		String HTML = "/home/nexii/dumps/sample-stp.html";
		System.out.println("DEST................"+DEST+".............................HTML.........."+HTML);
		try{
		File file = new File(HTML);
		file.getParentFile().mkdirs();
		file = new File(DEST);
		file.getParentFile().mkdirs();
		Document document = new Document();
		bw = new BufferedWriter(new FileWriter(HTML, false));
		bw.write(pdfHtml);
		bw.newLine();
		bw.flush();
		// step 2
		System.out.println("step2................................................");
		PdfWriter writer = PdfWriter.getInstance(document,
				new FileOutputStream(file));
		// step 3
		System.out.println("step3................................................");
		document.open();
		//document.setPageSize(PageSize.A4);
		// step 4
		System.out.println("step2................................................");
		XMLWorkerHelper.getInstance().parseXHtml(writer, document,
				new FileInputStream(HTML));
		document.close();
		System.err.println("Generated successfully--");
		//logger.info("Generated successfully");
		flag=true;
	}catch(Exception e){
		e.printStackTrace();
		//return null;
		
	}
		
		return flag;

	}
	
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}

}