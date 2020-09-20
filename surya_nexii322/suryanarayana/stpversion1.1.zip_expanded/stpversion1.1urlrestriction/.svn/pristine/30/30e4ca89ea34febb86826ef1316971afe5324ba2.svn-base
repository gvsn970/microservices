package com.nexiilabs.stp.account;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nexiilabs.stp.user.CreateUserModel;
import com.nexiilabs.stp.user.UserResponseDTO;

@RestController
@RequestMapping("/account")
public class CustomerController {
	private static final Logger log = LogManager.getLogger(CustomerController.class);
	@Autowired
	CustomerService customerService;
	@Autowired
	private Environment environment;

	@RequestMapping("/Welcome")
	public String getMsg() {
		log.info("in user controller!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		return "hello !";
	}

	// @CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders =
	// "*")
	@RequestMapping(value = "/createAccount", method = RequestMethod.POST)
	public UserResponseDTO createAccount(@RequestParam("companyName") String companyName,
			@RequestParam("location") String location, @RequestParam("contactNum") String contactNum,
			@RequestParam("contactPerson") String contactPerson, @RequestParam("pan") String pan,
			@RequestParam("gstin") String gstin, @RequestParam("state") String state,
			@RequestParam("stateCode") String stateCode,
			@RequestParam("billingAddressLane1") String billingAddressLane1,
			@RequestParam("billingAddressLane2") String billingAddressLane2,
			@RequestParam("billingAddressPincode") String billingAddressPincode,
			@RequestParam("billingAddressState") String billingAddressState,
			@RequestParam("vendorAddressLane1") String vendorAddressLane1,
			@RequestParam("vendorAddressLane2") String vendorAddressLane2,
			@RequestParam("vendorAddressPincode") String vendorAddressPincode,
			@RequestParam("vendorAddressState") String vendorAddressState,
			@RequestParam("shippingAddressLane1") String shippingAddressLane1,
			@RequestParam("shippingAddressLane2") String shippingAddressLane2,
			@RequestParam("shippingAddressPinCode") String shippingAddressPinCode,
			@RequestParam("shippingAddressState") String shippingAddressState,
			@RequestParam("files") MultipartFile[] uploadfiles, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		CreateCustomerModel createCustomerModel = null;
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		CustomerFileUpload customerFileUpload = null;
		try {
			String UPLOADED_FOLDER = null;
			String agreementsFolder = environment.getProperty("app.agreementsuploaddir");
			;
			String fileName = null;
			String filePath = null;
			createCustomerModel = new CreateCustomerModel();
			int userId = 0;
			List<String> menus = null;
			// check session is exist or not if does not exist give error
			// message
			// check all mandatory fields filled or not if not filled then give
			// error message
			// else check user exist or not if exist give error message
			// else save user data if save operation is success then go for
			// agreement file upload
			// if file upload success then give success message of account
			// creation
			// else delete inserted account details record from database and
			// give failure of account creation
			if (userSession != null) {
				CreateUserModel userModel = (CreateUserModel) userSession.getAttribute("userModel");
				userId = userModel.getUserId();
				menus = (List<String>) userSession.getAttribute("menuPermissions");
				if (menus.contains("Accounts")) {
					if (companyName != null && location != null && contactNum != null && contactPerson != null
							&& billingAddressLane1 != null && billingAddressLane2 != null && vendorAddressLane1 != null
							&& vendorAddressLane2 != null && billingAddressPincode != null
							&& billingAddressState != null && vendorAddressPincode != null && vendorAddressState != null
							&& shippingAddressLane1 != null && shippingAddressLane2 != null
							&& shippingAddressPinCode != null && shippingAddressState != null && pan != null
							&& gstin != null && state != null && stateCode != null) {
						// else get all input fields info and check file input
						// empty
						// or not if empty give error message
						createCustomerModel.setCustomerName(companyName);
						createCustomerModel.setContactNumber(contactNum);
						createCustomerModel.setContactPerson(contactPerson);
						createCustomerModel.setCustomerLocation(location);
						createCustomerModel.setBillingAddressLane1(billingAddressLane1);
						createCustomerModel.setBillingAddressLane2(billingAddressLane2);
						createCustomerModel.setBillingAddressPinCode(billingAddressPincode);
						createCustomerModel.setBillingAddressState(billingAddressState);
						createCustomerModel.setShippingAddressLane1(shippingAddressLane1);
						createCustomerModel.setShippingAddressLane2(shippingAddressLane2);
						createCustomerModel.setShippingAddressPinCode(shippingAddressPinCode);
						createCustomerModel.setShippingAddressState(shippingAddressState);
						createCustomerModel.setVendorAddressLane1(vendorAddressLane1);
						createCustomerModel.setVendorAddressLane2(vendorAddressLane2);
						createCustomerModel.setVendorAddressPinCode(vendorAddressPincode);
						createCustomerModel.setVendorAddressState(vendorAddressState);
						createCustomerModel.setPanNumber(pan);
						createCustomerModel.setGstIn(gstin);
						createCustomerModel.setState(state);
						createCustomerModel.setStateCode(stateCode);
						createCustomerModel.setCreatedBy(userId);
						System.out.println("Account Model Before Insertion : " + createCustomerModel.toString());
						System.out.println("file array length " + uploadfiles.length);
						userResponseDTO = customerService.createAccount(createCustomerModel);
						int customerId = userResponseDTO.getCustomerId();
						createCustomerModel.setCustomerId(customerId);
						System.out.println("Account Model After Insertion : " + createCustomerModel.toString());
						if (userResponseDTO.getStatusCode() != 2) {
							if (userResponseDTO.getStatusCode() == 1) {
								userResponseDTO = customerService.createDirectories(companyName, location,
										agreementsFolder);
								if (userResponseDTO.getStatusCode() != 0 && userResponseDTO.getUploadPath() != null) {
									UPLOADED_FOLDER = userResponseDTO.getUploadPath();
									if (uploadfiles.length > 1) {
										boolean allUploadStatus = true;
										for (MultipartFile file_object : Arrays.asList(uploadfiles)) {
											fileName = file_object.getOriginalFilename();
											filePath = Paths.get(UPLOADED_FOLDER, fileName).toString();
											customerFileUpload = new CustomerFileUpload();
											customerFileUpload.setFileName(fileName);
											customerFileUpload.setUploadPath(filePath);
											customerFileUpload.setCustomerId(customerId);
											customerFileUpload.setCreatedBy(userId);
											if (customerService.saveFileToDisk(file_object, UPLOADED_FOLDER, fileName,
													filePath)) {
												userResponseDTO = customerService.saveUploadedFiles(customerFileUpload);
											} else {
												allUploadStatus = false;
											}
										}
										if (allUploadStatus) {
											userResponseDTO.setStatusCode(1);
											userResponseDTO.setMessage("Account Created Successfully");
										} else {
											customerService.deleteAccount(createCustomerModel);
											userResponseDTO.setStatusCode(0);
											userResponseDTO
													.setMessage("Account Creation Failed due to File Upload Failure");
										}
									} else if (uploadfiles.length == 1) {
										MultipartFile file_object = uploadfiles[0];
										if (!file_object.isEmpty()) {
											fileName = file_object.getOriginalFilename();
											filePath = Paths.get(UPLOADED_FOLDER, fileName).toString();
											customerFileUpload = new CustomerFileUpload();
											customerFileUpload.setFileName(fileName);
											customerFileUpload.setUploadPath(filePath);
											customerFileUpload.setCustomerId(customerId);
											customerFileUpload.setCreatedBy(userId);
											if (customerService.saveFileToDisk(file_object, UPLOADED_FOLDER, fileName,
													filePath)) {
												userResponseDTO = customerService.saveUploadedFiles(customerFileUpload);
											} else {
												customerService.deleteAccount(createCustomerModel);
												userResponseDTO.setStatusCode(0);
												userResponseDTO.setMessage(
														"Account Creation Failed due to File Upload Failure");
											}
										} else {
											customerService.deleteAccount(createCustomerModel);
											userResponseDTO.setStatusCode(0);
											userResponseDTO.setMessage("Please upload Agreement");
										}
									} else {
										customerService.deleteAccount(createCustomerModel);
										userResponseDTO.setStatusCode(0);
										userResponseDTO.setMessage("Please upload Agreement");
									}
								} else {
									userResponseDTO.setStatusCode(0);
									userResponseDTO.setMessage("Failed to create Directory");
								}
							} else {
								userResponseDTO.setStatusCode(0);
								userResponseDTO.setMessage("Account Creation Failed");
							}

						} else {
							userResponseDTO.setStatusCode(0);
							userResponseDTO.setMessage("Account Already Exist");
						}
					} else {
						userResponseDTO.setStatusCode(0);
						userResponseDTO.setMessage("All input fields are Mandatory");
					}
				} else {
					userResponseDTO.setStatusCode(0);
					userResponseDTO.setMessage("Not permitted to access the service");
				}
			} else {
				userResponseDTO.setStatusCode(0);
				userResponseDTO.setMessage("Login Required to Access this service");
			}
		} catch (Exception e) {
			e.printStackTrace();
			userResponseDTO.setMessage(e.getMessage());
			userResponseDTO.setStatusCode(0);
		}
		return userResponseDTO;
	}

	@RequestMapping(value = "/updateAccount", method = RequestMethod.POST)
	public UserResponseDTO updateCustomer(@RequestParam("customerId") Integer customerId,
			@RequestParam("companyName") String companyName, @RequestParam("location") String location,
			@RequestParam("contactNum") String contactNum, @RequestParam("contactPerson") String contactPerson,
			@RequestParam("pan") String pan, @RequestParam("gstin") String gstin, @RequestParam("state") String state,
			@RequestParam("stateCode") String stateCode,
			@RequestParam("billingAddressLane1") String billingAddressLane1,
			@RequestParam("billingAddressLane2") String billingAddressLane2,
			@RequestParam("billingAddressPincode") String billingAddressPincode,
			@RequestParam("billingAddressState") String billingAddressState,
			@RequestParam("vendorAddressLane1") String vendorAddressLane1,
			@RequestParam("vendorAddressLane2") String vendorAddressLane2,
			@RequestParam("vendorAddressPincode") String vendorAddressPincode,
			@RequestParam("vendorAddressState") String vendorAddressState,
			@RequestParam("shippingAddressLane1") String shippingAddressLane1,
			@RequestParam("shippingAddressLane2") String shippingAddressLane2,
			@RequestParam("shippingAddressPinCode") String shippingAddressPinCode,
			@RequestParam("shippingAddressState") String shippingAddressState,
			@RequestParam("files") MultipartFile[] uploadfiles, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		CustomerFileUpload customerFileUpload = null;
		CreateCustomerModel createCustomerModel = null;
		String agreementsFolder = environment.getProperty("app.agreementsuploaddir");
		String UPLOADED_FOLDER = null;
		String fileName = null;
		String filePath = null;
		int userId = 0;
		List<String> menus = null;
		try {
			if (userSession != null) {
				CreateUserModel userModel = (CreateUserModel) userSession.getAttribute("userModel");
				userId = userModel.getUserId();
				menus = (List<String>) userSession.getAttribute("menuPermissions");
				if (menus.contains("Accounts")) {
					if (companyName != null && location != null && contactNum != null && contactPerson != null
							&& billingAddressLane1 != null && billingAddressLane2 != null && vendorAddressLane1 != null
							&& vendorAddressLane2 != null && billingAddressPincode != null
							&& billingAddressState != null && vendorAddressPincode != null && vendorAddressState != null
							&& shippingAddressLane1 != null && shippingAddressLane2 != null
							&& shippingAddressPinCode != null && pan != null && gstin != null && state != null
							&& stateCode != null) {
						createCustomerModel = new CreateCustomerModel();
						createCustomerModel.setCustomerId(customerId);
						createCustomerModel.setCustomerName(companyName);
						createCustomerModel.setContactNumber(contactNum);
						createCustomerModel.setCustomerLocation(location);
						createCustomerModel.setContactPerson(contactPerson);
						createCustomerModel.setBillingAddressLane1(billingAddressLane1);
						createCustomerModel.setBillingAddressLane2(billingAddressLane2);
						createCustomerModel.setBillingAddressPinCode(billingAddressPincode);
						createCustomerModel.setBillingAddressState(billingAddressState);
						createCustomerModel.setShippingAddressLane1(shippingAddressLane1);
						createCustomerModel.setShippingAddressLane2(shippingAddressLane2);
						createCustomerModel.setShippingAddressPinCode(shippingAddressPinCode);
						createCustomerModel.setShippingAddressState(shippingAddressState);
						createCustomerModel.setVendorAddressLane1(vendorAddressLane1);
						createCustomerModel.setVendorAddressLane2(vendorAddressLane2);
						createCustomerModel.setVendorAddressPinCode(vendorAddressPincode);
						createCustomerModel.setVendorAddressState(vendorAddressState);
						createCustomerModel.setPanNumber(pan);
						createCustomerModel.setGstIn(gstin);
						createCustomerModel.setState(state);
						createCustomerModel.setStateCode(stateCode);
						createCustomerModel.setUpdatedBy(userId);
						userResponseDTO = customerService.createDirectories(companyName, location, agreementsFolder);
						if (userResponseDTO.getStatusCode() != 0 && userResponseDTO.getUploadPath() != null) {
							UPLOADED_FOLDER = userResponseDTO.getUploadPath();
							// System.err.println("UPLOADED_FOLDER::"+UPLOADED_FOLDER);
							userResponseDTO = customerService.updateAccount(createCustomerModel);
							if (userResponseDTO.getStatusCode() == 1) {
								// update of account info success
								// System.out.println("Upload files array : " +
								// uploadfiles.length);
								// userResponseDTO =
								// customerService.getFilesofCustomer(createCustomerModel);
								if (uploadfiles.length > 0) {
									boolean allUploadStatus = true;
									for (MultipartFile file : Arrays.asList(uploadfiles)) {
										fileName = file.getOriginalFilename();
										filePath = Paths.get(UPLOADED_FOLDER, fileName).toString();
										userResponseDTO = new UserResponseDTO();
										customerFileUpload = new CustomerFileUpload();
										customerFileUpload.setFileName(fileName);
										customerFileUpload.setUploadPath(filePath);
										customerFileUpload.setCustomerId(customerId);
										customerFileUpload.setCreatedBy(userId);
										if (!file.isEmpty()) {
											byte[] bytes = file.getBytes();
											Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
											try {
												Files.write(path, bytes);
												if (!customerService.checkFileExistancyForCustomer(customerFileUpload,
														createCustomerModel)) {
													userResponseDTO = customerService
															.updateUploadedFiles(customerFileUpload);
												}
											} catch (Exception e) {
												allUploadStatus = false;
												userResponseDTO.setStatusCode(0);
												userResponseDTO.setMessage("Account update File Upload Failure");
											}
										}
									}
									if (allUploadStatus) {
										userResponseDTO.setStatusCode(1);
										userResponseDTO.setMessage("Account updated Successfully");
									}
								}
							} else if (userResponseDTO.getStatusCode() == 2) {
								if (uploadfiles.length > 0) {
									if (uploadfiles[0].isEmpty()) {
										userResponseDTO.setStatusCode(0);
										userResponseDTO.setMessage("No Changes found");
									} else {
										boolean allUploadStatus = true;
										boolean fileexist = false;
										for (MultipartFile file : Arrays.asList(uploadfiles)) {
											fileName = file.getOriginalFilename();
											filePath = Paths.get(UPLOADED_FOLDER, fileName).toString();
											userResponseDTO = new UserResponseDTO();
											customerFileUpload = new CustomerFileUpload();
											customerFileUpload.setFileName(fileName);
											customerFileUpload.setUploadPath(filePath);
											customerFileUpload.setCustomerId(customerId);
											customerFileUpload.setCreatedBy(userId);
											if (!file.isEmpty()) {
												byte[] bytes = file.getBytes();
												Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
												try {
													if (!customerService.checkFileExistancyForCustomer(
															customerFileUpload, createCustomerModel)) {
														Files.write(path, bytes);
														userResponseDTO = customerService.updateUploadedFiles(customerFileUpload);
													} else {
														fileexist = true;
													}
												} catch (Exception e) {
													allUploadStatus = false;
													userResponseDTO.setStatusCode(0);
													userResponseDTO.setMessage("Account update File Upload Failure");
												}
											}
										}
										if (allUploadStatus && fileexist) {
											userResponseDTO.setStatusCode(0);
											userResponseDTO.setMessage("No changes found");
										} else {
											userResponseDTO.setStatusCode(1);
											userResponseDTO.setMessage("Account updated successfully");
										}
									}
								}
							} else if (userResponseDTO.getStatusCode() == 3) {
								userResponseDTO.setStatusCode(0);
								userResponseDTO.setMessage("Company Name already exist Please choose different name");
							} else {
								userResponseDTO.setStatusCode(0);
								userResponseDTO.setMessage("All fields are mandatory");
							}
						} else {
							userResponseDTO.setStatusCode(0);
							userResponseDTO.setMessage("Failed to create Directory");
						}
					} else {
						userResponseDTO.setStatusCode(0);
						userResponseDTO.setMessage("All fields are mandatory");
					}
				} else {
					userResponseDTO.setStatusCode(0);
					userResponseDTO.setMessage("Not permitted to access the service");
				}
			} else {
				userResponseDTO.setStatusCode(0);
				userResponseDTO.setMessage("Login Required to Access this service");
			}
		} catch (Exception e) {
			e.printStackTrace();
			userResponseDTO.setMessage(e.getMessage());
			userResponseDTO.setStatusCode(0);
		}
		return userResponseDTO;
	}

	@RequestMapping(value = "/deleteAccount/{customerId}", method = RequestMethod.POST)
	public UserResponseDTO deleteCustomer(@PathVariable("customerId") Integer customerId, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		CreateCustomerModel createCustomerModel = null;
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		int userId = 0;
		List<String> menus = null;
		try {
			if (userSession != null) {
				CreateUserModel userModel = (CreateUserModel) userSession.getAttribute("userModel");
				userId = userModel.getUserId();
				menus = (List<String>) userSession.getAttribute("menuPermissions");
				if (menus.contains("Accounts")) {
					createCustomerModel = new CreateCustomerModel();
					createCustomerModel.setCustomerId(customerId);
					createCustomerModel.setDeletedBy(userId);
					if (createCustomerModel != null) {
						userResponseDTO = customerService.deleteAccount(createCustomerModel);
					}
				} else {
					userResponseDTO.setStatusCode(0);
					userResponseDTO.setMessage("Not permitted to access the service");
				}
			} else {
				userResponseDTO.setStatusCode(0);
				userResponseDTO.setMessage("Login Required to Access this service");
			}
		} catch (Exception e) {
			e.printStackTrace();
			userResponseDTO.setMessage(e.getMessage());
			userResponseDTO.setStatusCode(0);
		}
		return userResponseDTO;
	}

	@RequestMapping(value = "/getAgreementsList/{companyAccountId}", method = RequestMethod.GET)
	public List<CustomerFileUpload> getAgreementsList(@PathVariable("companyAccountId") Integer companyAccountId,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		List<CustomerFileUpload> agreementListResponseDTO = null;
		int userId = 0;
		List<String> menus = null;
		try {
			if (userSession != null) {
				CreateUserModel userModel = (CreateUserModel) userSession.getAttribute("userModel");
				userId = userModel.getUserId();
				menus = (List<String>) userSession.getAttribute("menuPermissions");
				if (menus.contains("Accounts")) {
					agreementListResponseDTO = customerService.getAgreementsList(companyAccountId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return agreementListResponseDTO;
	}

	@RequestMapping(value = "/getAccountsList", method = RequestMethod.GET)
	public List<CustomersListResponseDTO> getCustomersList(HttpServletRequest request, HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		int userId = 0;
		List<CustomersListResponseDTO> customersListResponseDTO = null;
		List<String> menus = null;
		try {
			if (userSession != null) {
				CreateUserModel userModel = (CreateUserModel) userSession.getAttribute("userModel");
				userId = userModel.getUserId();
				menus = (List<String>) userSession.getAttribute("menuPermissions");
				if (menus.contains("Accounts")) {
					customersListResponseDTO = customerService.getAccountsList(userId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customersListResponseDTO;
	}

	@RequestMapping(value = "/deleteAgreementFiles/{fileIds}/{customerId}", method = RequestMethod.POST)
	public UserResponseDTO deleteFiles(@PathVariable("customerId") Integer customerId,
			@PathVariable("fileIds") String fileIds, HttpServletRequest request, HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		CreateCustomerModel createCustomerModel = null;
		List<String> menus = null;
		try {
			if (userSession != null) {
				menus = (List<String>) userSession.getAttribute("menuPermissions");
				if (menus.contains("Accounts")) {
					createCustomerModel = new CreateCustomerModel();
					createCustomerModel.setCustomerId(customerId);
					userResponseDTO = customerService.getFilesofCustomer(createCustomerModel);
					System.out.println("userResponseDTO:::::::::::" + userResponseDTO.toString());
					if (userResponseDTO.getFilesUploadedforCustomer() > 1) {
						userResponseDTO = customerService.deleteFiles(fileIds, customerId);
					} else {
						userResponseDTO.setMessage(
								"Atleast One file should be there,If you want to delete first upload a file and delete existing one");
						userResponseDTO.setStatusCode(0);
					}
				} else {
					userResponseDTO.setStatusCode(0);
					userResponseDTO.setMessage("Not permitted to access the service");
				}
			} else {
				userResponseDTO.setStatusCode(0);
				userResponseDTO.setMessage("Login Required to Access this service");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return userResponseDTO;

	}
}
