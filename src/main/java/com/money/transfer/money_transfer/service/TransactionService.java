package com.money.transfer.money_transfer.service;

import com.money.transfer.money_transfer.common.TransactionStatus;
import com.money.transfer.money_transfer.entity.Transaction;
import com.money.transfer.money_transfer.repository.TransactionRepository;
import com.money.transfer.money_transfer.repository.specification.TransactionSpecification;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransactionService {
  private final TransactionRepository transactionRepository;

  @Validated
  public Page<Transaction> getFilterTransaction(@NotNull LocalDateTime startDate, @NotNull LocalDateTime endDate, @NotNull TransactionStatus transactionStatus, int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    Specification<Transaction> specTransaction = Specification.where(null);
    specTransaction = specTransaction.and(TransactionSpecification.withinDateRange(startDate, endDate))
        .and(TransactionSpecification.hasStatus(transactionStatus));
    return transactionRepository.findAll(specTransaction, pageable);
  }
}
