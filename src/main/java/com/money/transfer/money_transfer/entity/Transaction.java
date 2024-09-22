package com.money.transfer.money_transfer.entity;

import com.money.transfer.money_transfer.common.TransactionStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class Transaction {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long fromAccountId;
  private Long toAccountId;
  private BigDecimal amount;
  private LocalDateTime timestamp;

  @Enumerated(EnumType.STRING)
  private TransactionStatus status;
  private LocalDateTime scheduleTime;
}
