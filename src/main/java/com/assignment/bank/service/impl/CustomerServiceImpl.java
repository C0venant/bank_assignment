package com.assignment.bank.service.impl;

import com.assignment.bank.entity.Card;
import com.assignment.bank.entity.Customer;
import com.assignment.bank.entity.model.CardType;
import com.assignment.bank.entity.model.CustomerType;
import com.assignment.bank.exception.CustomerNotFoundException;
import com.assignment.bank.repository.AccountRepository;
import com.assignment.bank.repository.CardRepository;
import com.assignment.bank.repository.CustomerRepository;
import com.assignment.bank.response.IdValueWrapper;
import com.assignment.bank.response.CustomerDto;
import com.assignment.bank.service.CustomerService;
import com.assignment.bank.util.DataValueMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final AccountRepository accountRepository;

    private final CardRepository cardRepository;

    @Override
    @Transactional(readOnly = true)
    public CustomerDto getCustomerDataById(long id) {
        Customer customer = customerRepository
                .findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with id: " + id + " is not present"));
        List<IdValueWrapper> accounts = getCustomerAccountDetails(id);
        List<IdValueWrapper> cards = getCustomerCardDetails(id, customer.getType());
        return CustomerDto.of(customer, cards, accounts);
    }

    private List<IdValueWrapper> getCustomerAccountDetails(long customerId) {
        return accountRepository
                .findAccountsByCustomerId(customerId)
                .stream()
                .map(DataValueMapper::accountToValue)
                .collect(Collectors.toList());
    }

    private List<IdValueWrapper> getCustomerCardDetails(long customerId, String customerType) {
        return getCustomerCards(customerId, customerType)
                .stream()
                .map(DataValueMapper::cardToValue)
                .collect(Collectors.toList());
    }

    private List<Card> getCustomerCards(long customerId, String customerType) {
        if (CustomerType.BUSINESS.getName().equals(customerType)) {
            return cardRepository.findCardsByCustomerIdAndTypeIsNot(customerId, CardType.CREDIT.getName());
        }
        return cardRepository.findCardsByCustomerId(customerId);
    }
}
