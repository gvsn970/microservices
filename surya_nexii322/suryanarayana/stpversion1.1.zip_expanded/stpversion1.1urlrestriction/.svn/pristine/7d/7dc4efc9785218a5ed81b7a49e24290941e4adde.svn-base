package com.nexiilabs.stp.bankaccount;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexiilabs.stp.user.UserResponseDTO;

@Service
public class BankAccountServiceImpl implements BankAccountService {
	@Autowired
	BankAccoutRepository bankAccoutRepository;

	@Override
	public UserResponseDTO addBankAccount(BankAccountModel bankAccountModel) {
		UserResponseDTO userResponseDTO = bankAccoutRepository.addBankAccount(bankAccountModel);
		return userResponseDTO;
	}

	@Override
	public UserResponseDTO updateBankAccount(BankAccountModel bankAccountModel) {
		UserResponseDTO userResponseDTO = bankAccoutRepository.updateBankAccount(bankAccountModel);
		return userResponseDTO;
	}

	@Override
	public UserResponseDTO deleteBankAccount(BankAccountModel bankAccountModel) {
		UserResponseDTO userResponseDTO = bankAccoutRepository.deleteBankAccount(bankAccountModel);
		return userResponseDTO;
	}

	@Override
	public List<BankAccountListResponseDTO> getBankAccountsList() {
		return bankAccoutRepository.getBankAccountsList();
	}

	@Override
	public UserResponseDTO activeBankAccount(BankAccountModel bankAccountModel) {
		UserResponseDTO userResponseDTO = bankAccoutRepository.activeBankAccount(bankAccountModel);
		return userResponseDTO;
	}

	@Override
	public BankAccountListResponseDTO getBankAccountDetails(BankAccountModel bankAccountModel) {
		BankAccountListResponseDTO bankAccountListResponseDTO = bankAccoutRepository.getBankAccountDetails(bankAccountModel);
		return bankAccountListResponseDTO;
	}

}
