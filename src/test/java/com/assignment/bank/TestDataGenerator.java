package com.assignment.bank;

import java.sql.Timestamp;

import com.assignment.bank.entity.Account;
import com.assignment.bank.entity.Card;
import com.assignment.bank.entity.Customer;
import com.assignment.bank.entity.model.CardType;
import com.assignment.bank.entity.model.CustomerType;

public final class TestDataGenerator {

    private TestDataGenerator() {
    }

    public static Customer getPersonalCustomer() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setType(CustomerType.PERSONAL.getName());
        customer.setFullName("personal customer");
        return customer;
    }

    public static Customer getBusinessCustomer() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setType(CustomerType.BUSINESS.getName());
        customer.setFullName("Business customer");
        return customer;
    }

    public static Account getAccount() {
        Account account = new Account();
        account.setBalance(100.2f);
        account.setIban("LT601010012345678901");
        account.setCurrency("EUR");
        return account;
    }

    public static Card getCreditCard() {
        Card card = getDebitCard();
        card.setType(CardType.CREDIT.getName());
        return card;
    }

    public static Card getDebitCard() {
        Card card = new Card();
        card.setCardNumber("5236 5484 2365 4125");
        card.setType(CardType.DEBIT.getName());
        card.setExpiry(Timestamp.valueOf("2020-10-05 14:01:1"));
        return card;
    }
}
