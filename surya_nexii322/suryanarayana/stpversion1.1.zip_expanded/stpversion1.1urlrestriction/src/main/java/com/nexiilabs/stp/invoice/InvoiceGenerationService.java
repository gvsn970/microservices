package com.nexiilabs.stp.invoice;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.nexiilabs.stp.bankaccount.BankAccountModel;
import com.nexiilabs.stp.bankaccount.TaxDetailsModel;
import com.nexiilabs.stp.resource.POModel;
import com.nexiilabs.stp.resource.RequirementsModel;
import com.nexiilabs.stp.user.UserResponseDTO;

@Service
public interface InvoiceGenerationService {

	List<POModel> getPOList(String date);

	BankAccountModel getBankDetails();

	InvoiceRequestModel getInvoiceRequestDetails(InvoiceRequestModel invoiceRequestModel, POModel poModel);

	List<InvoiceRequestModel> getCountOfInvoice(POModel poModel);

	InvoiceRequestModel generteInvoiceRequest(InvoiceRequestModel invoiceRequestModel, POModel poModel);

	InvoiceModel saveInvoiceDetails(InvoiceModel invoiceModel);

	InvoiceModel getLastInvoiceDetails(InvoiceRequestModel invoiceRequestModel);

	InvoiceModel updateInvoiceDetails(InvoiceModel invoiceModel, POModel poModel);

	int updateInvoiceRequest(InvoiceModel invoiceModel);

	String genertePdfInvoice(InvoiceModel invoiceModel,String empName,String projectName,String currencyType,String clientName,int userId,String imagePath,String DEST,int printStatus);

	List<POModel> getExipredPOList(String format);

	int updatePomodelExpiryDate(POModel poModel);

	//EditInvoiceDetailsResponseModel getInvoiceeditList(InvoiceModel invoiceModel);

	List<EditInvoiceDetailsResponseModel> getMyInvoiceList(int id);

	RequirementsModel getRequirementProjectId(int employeeId);

	HashMap<String, BankAccountModel> getBankData(String term);

	TaxDetailsModel getNexiiTaxDetails();

	HashMap<String, TaxDetailsModel> searchTaxDEtails(String term);

	List<EditInvoiceDetailsResponseModel> getInvoiceRequestList(int userId);

	InvoiceRequestModel DownloadPdfInvoice(InvoiceModel invoiceModel, String empName, String projectName,String currencyType, String clientName, int userId,
			String imagePath, String dEST, int printStatus);

	InvoiceRequestModel downloadMyInvoice(int requestId);

	List<EditInvoiceDetailsResponseModel> getMyInvoiceListFilter(int userId, String monthandyear);

	UserResponseDTO freezeInvoice(String fkRequestId, String invoiceId, int userId);

}