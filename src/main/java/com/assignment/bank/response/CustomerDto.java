package com.assignment.bank.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public record CustomerDto(
        long id,
        String fullName,
        String type,
        @JsonInclude(JsonInclude.Include.NON_EMPTY) List<CardDto> cards,
        @JsonInclude(JsonInclude.Include.NON_EMPTY) List<AccountDto> accounts
) { }
