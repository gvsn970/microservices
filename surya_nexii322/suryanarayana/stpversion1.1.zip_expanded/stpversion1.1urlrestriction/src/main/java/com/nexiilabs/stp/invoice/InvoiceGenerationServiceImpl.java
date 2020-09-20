package com.nexiilabs.stp.invoice;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nexiilabs.stp.bankaccount.BankAccountModel;
import com.nexiilabs.stp.bankaccount.TaxDetailsModel;
import com.nexiilabs.stp.resource.POModel;
import com.nexiilabs.stp.resource.RequirementsModel;
import com.nexiilabs.stp.user.UserResponseDTO;

@Service
public class InvoiceGenerationServiceImpl implements InvoiceGenerationService{

	@Autowired
	InvoiceGenerationDao invoiceGenerationDao;

	@Override
	public List<POModel> getPOList(String date) {
		return invoiceGenerationDao.getPOList(date);
	}

	@Override
	public BankAccountModel getBankDetails() {
		return invoiceGenerationDao.getBankDetails();
	}

	@Override
	public InvoiceRequestModel getInvoiceRequestDetails(InvoiceRequestModel invoiceRequestModel,POModel poModel) {
		return invoiceGenerationDao.getInvoiceRequestDetails(invoiceRequestModel,poModel);
	}

	@Override
	public List<InvoiceRequestModel> getCountOfInvoice(POModel poModel) {
		return invoiceGenerationDao.getCountOfInvoice(poModel);
	}

	@Override
	public InvoiceRequestModel generteInvoiceRequest(InvoiceRequestModel invoiceRequestModel, POModel poModel) {
		return invoiceGenerationDao.generteInvoiceRequest(invoiceRequestModel,poModel);
	}

	@Override
	public InvoiceModel saveInvoiceDetails(InvoiceModel invoiceModel) {
		return invoiceGenerationDao.saveInvoiceDetails(invoiceModel);
	}

	@Override
	public InvoiceModel getLastInvoiceDetails(InvoiceRequestModel invoiceRequestModel) {
		return invoiceGenerationDao.getLastInvoiceDetails(invoiceRequestModel);
	}

	@Override
	public InvoiceModel updateInvoiceDetails(InvoiceModel invoiceModel, POModel poModel) {
		return invoiceGenerationDao.updateInvoiceDetails(invoiceModel,poModel);
	}

	@Override
	public int updateInvoiceRequest(InvoiceModel invoiceModel) {
		return invoiceGenerationDao.updateInvoiceRequest(invoiceModel);
	}

	@Override
	public String genertePdfInvoice(InvoiceModel invoiceModel,String empName,String projectName,String currencyType,String clientName,int userId,String imagePath,String DEST,int printStatus) {
		String status=null;
		//AddressModel addressModel=new AddressModel();
		InvoiceRequestModel invoiceRequestModel=new InvoiceRequestModel();
					if(invoiceModel!=null){
						System.out.println("..........................in.."+invoiceModel.getInvoiceId());
						RequirementsModel requirementsModel=invoiceGenerationDao.getProjectRequirementName(invoiceModel);
						if(requirementsModel!=null){
							System.out.println("..........................in.."+invoiceModel.getInvoiceId());
							invoiceRequestModel=GenerateInvoicePDF.generateEmpInvoicePdf(invoiceModel,empName,projectName,currencyType,clientName,imagePath,DEST, printStatus);
							status=invoiceGenerationDao.generteInvoiceAndSend(invoiceModel,invoiceRequestModel,requirementsModel,userId);
							System.out.println("invoice status.............."+status);
							return status;
						}else{
							status="Poject not present with this requirement details";
						}
						
					}else{
						status="Employee not present with this PO details";
					}
						
		return status;
	}

	@Override
	public List<POModel> getExipredPOList(String format) {
		return invoiceGenerationDao.getExipredPOList(format);
	}

	@Override
	public int updatePomodelExpiryDate(POModel poModel) {
		return invoiceGenerationDao.updatePomodelExpiryDate(poModel);
	}

	/*@Override
	public EditInvoiceDetailsResponseModel getInvoiceeditList(InvoiceModel invoiceModel) {
		return invoiceGenerationDao.getInvoiceeditList(invoiceModel);
	}*/

	@Override
	public List<EditInvoiceDetailsResponseModel> getMyInvoiceList(int id) {
		return invoiceGenerationDao.getMyInvoiceList(id);
	}

	@Override
	public RequirementsModel getRequirementProjectId(int employeeId) {
		return invoiceGenerationDao.getRequirementProjectId(employeeId);
	}

	@Override
	public HashMap<String, BankAccountModel> getBankData(String term) {
		return invoiceGenerationDao.getBankData(term);
	}

	@Override
	public TaxDetailsModel getNexiiTaxDetails() {
		return invoiceGenerationDao.getNexiiTaxDetails();
	}

	@Override
	public HashMap<String, TaxDetailsModel> searchTaxDEtails(String term) {
		return invoiceGenerationDao.searchTaxDEtails(term);
	}

	@Override
	public List<EditInvoiceDetailsResponseModel> getInvoiceRequestList(int userId) {
		return invoiceGenerationDao.getInvoiceRequestList(userId);
	}

	@Override
	public InvoiceRequestModel DownloadPdfInvoice(InvoiceModel invoiceModel, String empName, String projectName,String currencyType,String clientName,
			int userId, String imagePath, String DEST,int printStatus) {
		ResponseEntity<?> status=null;
		//AddressModel addressModel=new AddressModel();
		InvoiceRequestModel invoiceRequestModel=new InvoiceRequestModel();
					if(invoiceModel!=null){
						System.out.println("..........................in.."+invoiceModel.getInvoiceId());
						RequirementsModel requirementsModel=invoiceGenerationDao.getProjectRequirementName(invoiceModel);
						if(requirementsModel!=null){
							System.out.println("..........................in.."+invoiceModel.getInvoiceId());
							invoiceRequestModel=GenerateInvoicePDF.generateEmpInvoicePdf(invoiceModel,empName,projectName,currencyType,clientName,imagePath,DEST, printStatus);
							invoiceRequestModel=invoiceGenerationDao.downloadInvoice(invoiceModel,invoiceRequestModel,requirementsModel,userId);
							System.out.println("invoice status.............."+invoiceRequestModel.getInvoiceFilePath());
							return invoiceRequestModel;
						}else{
							return null;
						}
						
					}else{
						return null;
					}
	}

	@Override
	public InvoiceRequestModel downloadMyInvoice(int requestId) {
		return invoiceGenerationDao.downloadMyInvoice(requestId);
	}

	@Override
	public List<EditInvoiceDetailsResponseModel> getMyInvoiceListFilter(int userId, String monthandyear) {
		return invoiceGenerationDao.getMyinvoceFilterList(userId,monthandyear);
	}

	@Override
	public UserResponseDTO freezeInvoice(String fkRequestId, String invoiceId, int userId) {
		return invoiceGenerationDao.freezeInvoice(fkRequestId,invoiceId,userId);
	}
	
}