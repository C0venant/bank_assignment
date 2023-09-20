package com.assignment.bank.service.impl;

import com.assignment.bank.entity.Account;
import com.assignment.bank.entity.Card;
import com.assignment.bank.entity.Customer;
import com.assignment.bank.entity.model.CardType;
import com.assignment.bank.entity.model.CustomerType;
import com.assignment.bank.repository.AccountRepository;
import com.assignment.bank.repository.CardRepository;
import com.assignment.bank.repository.CustomerRepository;
import com.assignment.bank.response.CustomerDto;
import com.assignment.bank.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final AccountRepository accountRepository;

    private final CardRepository cardRepository;

    @Override
    public CustomerDto getCustomerDataById(long id) {
        Customer customer = customerRepository.findById(id).orElseThrow();
        List<Card> cards = cardRepository.findCardsByCustomerId(id);
        List<Account> accounts = accountRepository.findAccountsByCustomerId(id);
        System.out.println(accounts);
        return null;
    }

    private List<Card> getCustomerCards(Customer customer) {
        long customerId = customer.getId();
        if (CustomerType.BUSINESS.getName().equals(customer.getType())) {
            return cardRepository.findCardsByCustomerIdAndTypeIsNot(customerId, CardType.CREDIT.getName());
        }
        return cardRepository.findCardsByCustomerId(customerId);
    }
}
