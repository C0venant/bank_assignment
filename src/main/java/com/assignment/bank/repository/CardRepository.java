package com.assignment.bank.repository;

import com.assignment.bank.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {

    List<Card> findCardsByCustomerId(long customerId);

    List<Card> findCardsByCustomerIdAndTypeIsNot(long customerId, String type);
}
