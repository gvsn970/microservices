package com.nexiilabs.stp.bankaccount;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexiilabs.stp.user.UserResponseDTO;

@Service
public class TaxDetailsServiceImpl implements TaxDetailsService {
	@Autowired
	TaxDetailsRepository taxDetailsRepository;

	@Override
	public UserResponseDTO addtaxDetails(TaxDetailsModel taxDetailsModel) {
		UserResponseDTO userResponseDTO = taxDetailsRepository.addtaxDetails(taxDetailsModel);
		return userResponseDTO;
	}

	@Override
	public UserResponseDTO updateTaxDetails(TaxDetailsModel taxDetailsModel) {
		UserResponseDTO userResponseDTO = taxDetailsRepository.updateTaxDetails(taxDetailsModel);
		return userResponseDTO;
	}

	@Override
	public UserResponseDTO deleteTaxDetails(TaxDetailsModel taxDetailsModel) {
		UserResponseDTO userResponseDTO = taxDetailsRepository.deleteTaxDetails(taxDetailsModel);
		return userResponseDTO;
	}

	@Override
	public UserResponseDTO activeTaxDetails(TaxDetailsModel taxDetailsModel) {
		UserResponseDTO userResponseDTO = taxDetailsRepository.activeTaxDetails(taxDetailsModel);
		return userResponseDTO;
	}

	@Override
	public List<TaxDetailsListResponseDTO> getTaxDetailsList() {
		return taxDetailsRepository.getTaxDetailsList();
	}

	@Override
	public TaxDetailsListResponseDTO getTaxDetails(TaxDetailsModel taxDetailsModel) {
		TaxDetailsListResponseDTO taxDetailsListResponseDTO = taxDetailsRepository.getTaxDetails(taxDetailsModel);
		return taxDetailsListResponseDTO;
	}

}
