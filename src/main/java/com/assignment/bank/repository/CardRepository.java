package com.assignment.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.bank.entity.Card;

public interface CardRepository extends JpaRepository<Card, Long> {

    List<Card> findCardsByCustomerId(long customerId);

    List<Card> findCardsByCustomerIdAndTypeIsNot(long customerId, String type);
}
