package com.money.transfer.money_transfer.service;

import com.money.transfer.money_transfer.repository.AccountRepository;
import com.money.transfer.money_transfer.repository.TransactionRepository;
import com.money.transfer.money_transfer.common.TransactionStatus;
import com.money.transfer.money_transfer.entity.Account;
import com.money.transfer.money_transfer.entity.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Setter
@RequiredArgsConstructor
@Service
public class AccountService {
  private final AccountRepository accountRepository;
  private final TransactionRepository transactionRepository;

  @Transactional
  public void transfer(Long fromAccountId, Long toAccountId, BigDecimal amount) {
    // get account first
    Optional<Account> fromAccountOptional = accountRepository.findById(fromAccountId);
    Optional<Account> toAccountOptional = accountRepository.findById(toAccountId);

    Account fromAccount = fromAccountOptional.orElseThrow(() -> new RuntimeException("Account with id " + fromAccountId + " not found"));
    Account toAccount = toAccountOptional.orElseThrow(() -> new RuntimeException("Account with id " + toAccountId + " not found"));

    //Check limit
    if (fromAccount.getBalance().compareTo(amount) < 0) {
      throw new RuntimeException("Account balance is less than transferring amount");
    }

    fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
    toAccount.setBalance(toAccount.getBalance().add(amount));

    accountRepository.save(fromAccount);
    accountRepository.save(toAccount);

    //Save a transaction
    Transaction transaction = Transaction.builder()
        .fromAccountId(fromAccountId)
        .toAccountId(toAccountId)
        .amount(amount)
        .timestamp(LocalDateTime.now())
        .status(TransactionStatus.COMPLETED)
        .build();
    transactionRepository.save(transaction);
  }
}
