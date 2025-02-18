package com.nexiilabs.stp.invoice;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.nexiilabs.stp.bankaccount.BankAccountModel;
import com.nexiilabs.stp.bankaccount.TaxDetailsModel;
import com.nexiilabs.stp.resource.POModel;
import com.nexiilabs.stp.resource.RequirementsModel;
import com.nexiilabs.stp.user.UserResponseDTO;

@Repository
public interface InvoiceGenerationDao {

	List<POModel> getPOList(String date);

	BankAccountModel getBankDetails();

	InvoiceRequestModel getInvoiceRequestDetails(InvoiceRequestModel invoiceRequestModel, POModel poModel);

	List<InvoiceRequestModel> getCountOfInvoice(POModel poModel);

	InvoiceRequestModel generteInvoiceRequest(InvoiceRequestModel invoiceRequestModel, POModel poModel);

	InvoiceModel saveInvoiceDetails(InvoiceModel invoiceModel);

	InvoiceModel getLastInvoiceDetails(InvoiceRequestModel invoiceRequestModel);

	InvoiceModel updateInvoiceDetails(InvoiceModel invoiceModel, POModel poModel);

	int updateInvoiceRequest(InvoiceModel invoiceModel);

	//AddressModel getCommAddress();

	List<POModel> getExipredPOList(String format);

	int updatePomodelExpiryDate(POModel poModel);

	//EditInvoiceDetailsResponseModel getInvoiceeditList(InvoiceModel invoiceModel);

	String generteInvoiceAndSend(InvoiceModel invoiceModel,InvoiceRequestModel invoiceRequestModel,RequirementsModel requirementsModel, int userId);

	List<EditInvoiceDetailsResponseModel> getMyInvoiceList(int id);

	RequirementsModel getProjectRequirementName(InvoiceModel invoiceModel);

	RequirementsModel getRequirementProjectId(int employeeId);

	HashMap<String, BankAccountModel> getBankData(String term);

	TaxDetailsModel getNexiiTaxDetails();

	HashMap<String, TaxDetailsModel> searchTaxDEtails(String term);

	int getInvoiceRequestCount();

	List<EditInvoiceDetailsResponseModel> getInvoiceRequestList(int userId);

	InvoiceRequestModel downloadInvoice(InvoiceModel invoiceModel, InvoiceRequestModel invoiceRequestModel,
			RequirementsModel requirementsModel, int userId);

	InvoiceRequestModel downloadMyInvoice(int requestId);

	List<EditInvoiceDetailsResponseModel> getMyinvoceFilterList(int userId, String monthandyear);

	UserResponseDTO freezeInvoice(String fkRequestId, String invoiceId, int userId);
}