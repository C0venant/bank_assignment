package com.assignment.bank.entity.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CustomerType {
    PERSONAL("Personal"),
    BUSINESS("Business");

    private final String name;
}
