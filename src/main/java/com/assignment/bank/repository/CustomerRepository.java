package com.assignment.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.bank.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
