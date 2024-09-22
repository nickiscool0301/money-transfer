package com.money.transfer.money_transfer.controller;

import com.money.transfer.money_transfer.common.TransactionStatus;
import com.money.transfer.money_transfer.entity.Transaction;
import com.money.transfer.money_transfer.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/transaction")
@RequiredArgsConstructor
public class TransactionController {
  private final TransactionService transactionService;

  @GetMapping("/history")
  public ResponseEntity<Page<Transaction>> getTransactionHistory(
      @RequestParam(required = false) LocalDateTime startDate,
      @RequestParam(required = false) LocalDateTime endDate,
      @RequestParam(required = false) TransactionStatus status,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
    Page<Transaction> res = transactionService.getFilterTransaction(startDate, endDate, status, page, size);
    return ResponseEntity.ok(res);
  }
}
