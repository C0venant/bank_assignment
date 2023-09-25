package com.assignment.bank.service;

import com.assignment.bank.response.CustomerDto;

public interface CustomerService {

    CustomerDto getCustomerDataById(long id);
}
