package com.nexiilabs.stp.bankaccount;

import java.util.List;

import com.nexiilabs.stp.user.UserResponseDTO;

public interface TaxDetailsService {

	UserResponseDTO addtaxDetails(TaxDetailsModel taxDetailsModel);

	UserResponseDTO updateTaxDetails(TaxDetailsModel taxDetailsModel);

	UserResponseDTO deleteTaxDetails(TaxDetailsModel taxDetailsModel);

	UserResponseDTO activeTaxDetails(TaxDetailsModel taxDetailsModel);

	List<TaxDetailsListResponseDTO> getTaxDetailsList();

	TaxDetailsListResponseDTO getTaxDetails(TaxDetailsModel taxDetailsModel);

}
