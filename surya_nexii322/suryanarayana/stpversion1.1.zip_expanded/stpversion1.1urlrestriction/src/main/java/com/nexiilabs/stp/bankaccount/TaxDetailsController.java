package com.nexiilabs.stp.bankaccount;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nexiilabs.stp.user.CreateUserModel;
import com.nexiilabs.stp.user.UserResponseDTO;

@RestController
@RequestMapping("/taxDetails")
public class TaxDetailsController {
	@Autowired
	TaxDetailsService taxDetailsService;
	private static final Logger log = LogManager.getLogger(TaxDetailsController.class);

	@RequestMapping(value = "/addTaxDetails", method = RequestMethod.POST)
	public UserResponseDTO addTaxDetails(@RequestParam("panNumber") String panNumber,
			@RequestParam("stateName") String stateName, @RequestParam("stateCode") String stateCode,
			@RequestParam("gstIn") String gstIn, HttpServletRequest request, HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		TaxDetailsModel taxDetailsModel = null;
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		int userId = 0;
		try {
			if (userSession != null) {
				CreateUserModel userModel = (CreateUserModel) userSession.getAttribute("userModel");
				List<String> menuList=(List<String>) userSession.getAttribute("menuPermissions");
				if(menuList.contains("Tax Setup")){
					userId = userModel.getUserId();
					if (panNumber != null && stateName != null && stateCode != null && gstIn != null) {
						taxDetailsModel = new TaxDetailsModel();
						taxDetailsModel.setPanNumber(panNumber);
						taxDetailsModel.setGstIn(gstIn);
						taxDetailsModel.setState(stateName);
						taxDetailsModel.setStateCode(stateCode);
						taxDetailsModel.setCreatedBy(userId);
						userResponseDTO = taxDetailsService.addtaxDetails(taxDetailsModel);
						int isActive=userResponseDTO.getTaxDetailsActive();
						if (userResponseDTO.getStatusCode() == 1) {
							userResponseDTO.setStatusCode(1);
							userResponseDTO.setTaxDetailsActive(isActive);
							userResponseDTO.setMessage("Tax Details added Successfully.");
						} else if (userResponseDTO.getStatusCode() == 2) {
							userResponseDTO.setStatusCode(2);
							userResponseDTO.setMessage("Tax Details Already exists");
						} else {
							userResponseDTO.setStatusCode(0);
							userResponseDTO.setMessage("Tax Details addition Failed.");
						}
					} else {
						userResponseDTO.setStatusCode(0);
						userResponseDTO.setMessage("All input fields are Mandatory");
					}
				}else {
					userResponseDTO.setStatusCode(0);
					userResponseDTO.setMessage("Authentication require to access this");
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

	@RequestMapping(value = "/updateTaxDetails", method = RequestMethod.POST)
	public UserResponseDTO updateTaxDetails(@RequestParam("taxId") Integer taxId,
			@RequestParam("panNumber") String panNumber, @RequestParam("stateName") String stateName,
			@RequestParam("stateCode") String stateCode, @RequestParam("gstIn") String gstIn,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		TaxDetailsModel taxDetailsModel = null;
		int userId = 0;
		try {
			if (userSession != null) {
				CreateUserModel userModel = (CreateUserModel) userSession.getAttribute("userModel");
				List<String> menuList=(List<String>) userSession.getAttribute("menuPermissions");
				if(menuList.contains("Tax Setup")){
					userId = userModel.getUserId();
					if (panNumber != null && stateName != null && stateCode != null && gstIn != null) {
						taxDetailsModel = new TaxDetailsModel();
						taxDetailsModel.setTaxId(taxId);
						taxDetailsModel.setPanNumber(panNumber);
						taxDetailsModel.setGstIn(gstIn);
						taxDetailsModel.setState(stateName);
						taxDetailsModel.setStateCode(stateCode);
						taxDetailsModel.setUpdatedBy(userId);
						userResponseDTO = taxDetailsService.updateTaxDetails(taxDetailsModel);
						if (userResponseDTO.getStatusCode() == 1) {
							userResponseDTO.setStatusCode(1);
							userResponseDTO.setMessage("Tax Details updated Successfully.");
						} else if (userResponseDTO.getStatusCode() == 2) {
							userResponseDTO.setStatusCode(2);
							userResponseDTO.setMessage("No changes found to update");
						} else {
							userResponseDTO.setStatusCode(0);
							userResponseDTO.setMessage("Tax Details updation Failed.");
						}
					} else {
						userResponseDTO.setStatusCode(0);
						userResponseDTO.setMessage("All input fields are Mandatory");
					}
				} else {
					userResponseDTO.setStatusCode(0);
					userResponseDTO.setMessage("Authentication require to access this");
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

	@RequestMapping(value = "/deleteTaxDetails/{taxId}", method = RequestMethod.POST)
	public UserResponseDTO deleteTaxDetails(@PathVariable("taxId") Integer taxId, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		TaxDetailsModel taxDetailsModel = null;
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		int userId = 0;
		try {
			if (userSession != null) {
				List<String> menuList=(List<String>) userSession.getAttribute("menuPermissions");
				if(menuList.contains("Tax Setup")){
					CreateUserModel userModel = (CreateUserModel) userSession.getAttribute("userModel");
					userId = userModel.getUserId();
					taxDetailsModel = new TaxDetailsModel();
					taxDetailsModel.setTaxId(taxId);
					taxDetailsModel.setDeletedBy(userId);
					userResponseDTO = taxDetailsService.deleteTaxDetails(taxDetailsModel);
					if (userResponseDTO.getStatusCode() == 1) {
						userResponseDTO.setStatusCode(1);
						userResponseDTO.setMessage("Tax Details Deleted Successfully");
					} else {
						userResponseDTO.setStatusCode(0);
						userResponseDTO.setMessage("Tax Details Deletion Failed");
					}
				} else {
					userResponseDTO.setStatusCode(0);
					userResponseDTO.setMessage("Authentication require to access this");
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

	@RequestMapping(value = "/activeTaxDetails/{taxId}", method = RequestMethod.POST)
	public UserResponseDTO activeTaxDetails(@PathVariable("taxId") Integer taxId, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		TaxDetailsModel taxDetailsModel = null;
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		int userId = 0;
		try {
			if (userSession != null) {
				CreateUserModel userModel = (CreateUserModel) userSession.getAttribute("userModel");
				List<String> menuList=(List<String>) userSession.getAttribute("menuPermissions");
				if(menuList.contains("Tax Setup")){
					userId = userModel.getUserId();
					taxDetailsModel = new TaxDetailsModel();
					taxDetailsModel.setTaxId(taxId);
					userResponseDTO = taxDetailsService.activeTaxDetails(taxDetailsModel);
					if (userResponseDTO.getStatusCode() == 1) {
						userResponseDTO.setStatusCode(1);
						userResponseDTO.setMessage("Tax Details activated Successfully");
					} else {
						userResponseDTO.setStatusCode(0);
						userResponseDTO.setMessage("Tax Details activation Failed");
					}
				} else {
					userResponseDTO.setStatusCode(0);
					userResponseDTO.setMessage("Authentication require to access this");
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

	@RequestMapping(value = "/taxDetailsList", method = RequestMethod.GET)
	public List<TaxDetailsListResponseDTO> taxDetailsList(HttpServletRequest request, HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		List<TaxDetailsListResponseDTO> taxDetailsListResponseDTO = null;
		try {
		    if(userSession!=null){
		    	List<String> menuList=(List<String>) userSession.getAttribute("menuPermissions");
		    	if(menuList.contains("Tax Setup")){
		    		taxDetailsListResponseDTO = taxDetailsService.getTaxDetailsList();
		    	}
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return taxDetailsListResponseDTO;
	}
	@RequestMapping(value = "/getTaxDetails/{taxId}", method = RequestMethod.GET)
	public TaxDetailsListResponseDTO getTaxDetails(@PathVariable("taxId") Integer taxId, HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession userSession = request.getSession(false);
		TaxDetailsModel taxDetailsModel = null;
		TaxDetailsListResponseDTO taxDetailsListResponseDTO = null;
		//int userId = 0;
		try {
			if (userSession != null) {
				//CreateUserModel userModel = (CreateUserModel) userSession.getAttribute("userModel");
				List<String> menuList=(List<String>) userSession.getAttribute("menuPermissions");
		    	if(menuList.contains("Tax Setup")){
					//userId = userModel.getUserId();
					taxDetailsModel = new TaxDetailsModel();
					taxDetailsModel.setTaxId(taxId);
					taxDetailsListResponseDTO = taxDetailsService.getTaxDetails(taxDetailsModel);
		    	}
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return taxDetailsListResponseDTO;
	}

}
