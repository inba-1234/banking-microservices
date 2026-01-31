package com.banking.account.service;

import com.banking.account.dto.AccountDTO;
import com.banking.account.dto.CreditRequestDTO;
import com.banking.account.dto.DebitRequestDTO;
import com.banking.account.dto.CreateAccountRequestDTO;

public interface AccountService {
    AccountDTO getAccountByAccountNumber(String accountNumber);
    void creditAccount(String accountNumber, CreditRequestDTO creditRequest);
    void debitAccount(String accountNumber, DebitRequestDTO debitRequest);
    AccountDTO createAccount(CreateAccountRequestDTO createRequest);
}