package com.assignment.bank.repository;

import com.assignment.bank.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAccountsByCustomerId(long customerId);
}
