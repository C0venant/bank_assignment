package com.assignment.bank.controller;

import com.assignment.bank.exception.CustomerNotFoundException;
import com.assignment.bank.response.CustomerDto;
import com.assignment.bank.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String> handleRuntimeException(CustomerNotFoundException ex) {
        log.error("Customer is not present", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerDataById(@PathVariable long id) {
        return ResponseEntity.ok(customerService.getCustomerDataById(id));
    }
}
