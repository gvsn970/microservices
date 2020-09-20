package com.nexiilabs.stp.bankaccount;

import java.util.List;

import com.nexiilabs.stp.user.UserResponseDTO;

public interface BankAccountService {

	UserResponseDTO addBankAccount(BankAccountModel bankAccountModel);

	UserResponseDTO updateBankAccount(BankAccountModel bankAccountModel);

	UserResponseDTO deleteBankAccount(BankAccountModel bankAccountModel);

	List<BankAccountListResponseDTO> getBankAccountsList();

	UserResponseDTO activeBankAccount(BankAccountModel bankAccountModel);

	BankAccountListResponseDTO getBankAccountDetails(BankAccountModel bankAccountModel);

}
