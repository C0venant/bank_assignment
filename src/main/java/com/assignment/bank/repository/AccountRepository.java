package com.assignment.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.bank.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAccountsByCustomerId(long customerId);
}
