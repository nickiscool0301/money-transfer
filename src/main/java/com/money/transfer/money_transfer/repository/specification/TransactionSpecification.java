package com.money.transfer.money_transfer.repository.specification;

import com.money.transfer.money_transfer.common.TransactionStatus;
import com.money.transfer.money_transfer.entity.Transaction;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class TransactionSpecification {
  public static Specification<Transaction> withinDateRange(LocalDateTime startDate, LocalDateTime endDate) {
    return (root, query, criteriaBuilder) ->
        criteriaBuilder.between(root.get("timestamp"), startDate, endDate);
  }

  public static Specification<Transaction> hasStatus(TransactionStatus status) {
    return (root, query, criteriaBuilder) ->
        criteriaBuilder.equal(root.get("status"), status);
  }
}
