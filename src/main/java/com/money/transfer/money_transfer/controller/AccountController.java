package com.money.transfer.money_transfer.controller;

import com.money.transfer.money_transfer.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {
  private final AccountService accountService;

  @PostMapping("/transfer")
  public ResponseEntity<String> transferMoney(
      @RequestParam Long fromAccountId,
      @RequestParam Long toAccountId,
      @RequestBody BigDecimal amount) {
    try {
      accountService.transfer(fromAccountId, toAccountId, amount);
      return ResponseEntity.ok("Transfer successful");
    } catch (Exception e) {
      return ResponseEntity.status(500).body("Transfer failed: " + e.getMessage());
    }
  }
}
