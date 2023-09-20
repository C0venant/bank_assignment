package com.assignment.bank.entity.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CardType {
    DEBIT("Debit"),
    CREDIT("Credit");

    private final String name;
}
