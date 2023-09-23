package com.assignment.bank.service;

import com.assignment.bank.TestDataGenerator;
import com.assignment.bank.entity.model.CardType;
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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        var customer = TestDataGenerator.getPersonalCustomer();

        Mockito.when(customerRepository.findById(1L))
                .thenReturn(Optional.of(customer));
        Mockito.when(accountRepository.findAccountsByCustomerId(1L))
                .thenReturn(List.of(TestDataGenerator.getAccount(),
                        TestDataGenerator.getAccount(),
                        TestDataGenerator.getAccount()));
        Mockito.when(cardRepository.findCardsByCustomerId(1L))
                .thenReturn(List.of(TestDataGenerator.getCreditCard(), TestDataGenerator.getDebitCard()));

        var result = customerService.getCustomerDataById(1L);

        assertEquals(customer.getId(), result.id());
        assertEquals(customer.getFullName(), result.fullName());
        assertEquals(customer.getType(), result.type());
        assertEquals(3, result.accounts().size());
        assertEquals(2, result.cards().size());
    }

    @Test
    void testGetBusinessCustomerDataById() {
        var customer = TestDataGenerator.getBusinessCustomer();

        Mockito.when(customerRepository.findById(1L))
                .thenReturn(Optional.of(customer));
        Mockito.when(accountRepository.findAccountsByCustomerId(1L))
                .thenReturn(List.of(TestDataGenerator.getAccount(), TestDataGenerator.getAccount()));
        Mockito.when(cardRepository.findCardsByCustomerIdAndTypeIsNot(1L, CardType.CREDIT.getName()))
                .thenReturn(List.of(TestDataGenerator.getDebitCard()));

        var result = customerService.getCustomerDataById(1L);

        assertEquals(customer.getId(), result.id());
        assertEquals(customer.getFullName(), result.fullName());
        assertEquals(customer.getType(), result.type());
        assertEquals(2, result.accounts().size());
        assertEquals(1, result.cards().size());
    }
}