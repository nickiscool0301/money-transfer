package com.money.transfer.money_transfer.repository;

import com.money.transfer.money_transfer.common.TransactionStatus;
import com.money.transfer.money_transfer.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction> {
  // JPA explain: 'ScheduleTime' is Transaction's field
  // 'Before': keyword to compare with specific date
  // 'AndStatus': and also filter base on status of transaction
  List<Transaction> findAllByScheduleTimeBeforeAndStatus(LocalDateTime scheduleTimeBefore, TransactionStatus status);
}
