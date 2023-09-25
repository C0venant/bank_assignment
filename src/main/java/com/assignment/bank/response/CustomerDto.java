package com.assignment.bank.response;

import java.util.List;

import com.assignment.bank.entity.Customer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public record CustomerDto(
        long id,
        @JsonProperty(value = "full_name") String fullName,
        String type,
        @JsonInclude(JsonInclude.Include.NON_EMPTY) List<IdValueWrapper> cards,
        @JsonInclude(JsonInclude.Include.NON_EMPTY) List<IdValueWrapper> accounts) {

    public static CustomerDto of(Customer customer,
                                 List<IdValueWrapper> cards,
                                 List<IdValueWrapper> accounts) {
        return new CustomerDto(customer.getId(),
                customer.getFullName(),
                customer.getType(),
                cards,
                accounts);
    }
}
