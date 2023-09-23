package com.assignment.bank.service;

import com.assignment.bank.entity.Account;
import com.assignment.bank.entity.Card;
import com.assignment.bank.entity.Customer;
import com.assignment.bank.entity.model.CardType;
import com.assignment.bank.entity.model.CustomerType;
import com.assignment.bank.exception.CustomerNotFoundException;
import com.assignment.bank.repository.AccountRepository;
import com.assignment.bank.repository.CardRepository;
import com.assignment.bank.repository.CustomerRepository;
import com.assignment.bank.service.impl.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private CardRepository cardRepository;

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    void testGetCustomerDataByInvalidId() {
        Mockito.when(customerRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomerDataById(1));
    }

    @Test
    void testGetPersonalCustomerDataById() {
        Customer customer = getPersonalCustomer();

        Mockito.when(customerRepository.findById(1L))
                .thenReturn(Optional.of(customer));
        Mockito.when(accountRepository.findAccountsByCustomerId(1L))
                .thenReturn(List.of(getAccount(), getAccount(), getAccount()));
        Mockito.when(cardRepository.findCardsByCustomerId(1L))
                .thenReturn(List.of(getCreditCard(), getDebitCard()));


        var result = customerService.getCustomerDataById(1L);

        assertEquals(customer.getId(), result.id());
        assertEquals(customer.getFullName(), result.fullName());
        assertEquals(customer.getType(), result.type());
        assertEquals(3, result.accounts().size());
        assertEquals(2, result.cards().size());
    }

    @Test
    void testGetBusinessCustomerDataById() {
        Customer customer = getBusinessCustomer();

        Mockito.when(customerRepository.findById(1L))
                .thenReturn(Optional.of(customer));
        Mockito.when(accountRepository.findAccountsByCustomerId(1L))
                .thenReturn(List.of(getAccount(), getAccount()));
        Mockito.when(cardRepository.findCardsByCustomerIdAndTypeIsNot(1L, CardType.CREDIT.getName()))
                .thenReturn(List.of(getDebitCard()));


        var result = customerService.getCustomerDataById(1L);

        assertEquals(customer.getId(), result.id());
        assertEquals(customer.getFullName(), result.fullName());
        assertEquals(customer.getType(), result.type());
        assertEquals(2, result.accounts().size());
        assertEquals(1, result.cards().size());
    }

    private Customer getPersonalCustomer() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setType(CustomerType.PERSONAL.getName());
        customer.setFullName("personal customer");
        return customer;
    }

    private Customer getBusinessCustomer() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setType(CustomerType.BUSINESS.getName());
        customer.setFullName("Business customer");
        return customer;
    }

    private Account getAccount() {
        Account account = new Account();
        account.setBalance(100.2f);
        account.setIban("LT601010012345678901");
        account.setCurrency("EUR");
        return account;
    }

    private Card getCreditCard() {
        Card card = getDebitCard();
        card.setType(CardType.CREDIT.getName());
        return card;
    }

    private Card getDebitCard() {
        Card card = new Card();
        card.setCardNumber("5236 5484 2365 4125");
        card.setType(CardType.DEBIT.getName());
        card.setExpiry(Timestamp.valueOf("2020-10-05 14:01:1"));
        return card;
    }
}