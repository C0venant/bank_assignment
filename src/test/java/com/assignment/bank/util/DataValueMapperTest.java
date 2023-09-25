package com.assignment.bank.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.assignment.bank.TestDataGenerator;

class DataValueMapperTest {

    @Test
    void testCardToValueMapping() {
        var account = TestDataGenerator.getAccount();
        var result = DataValueMapper.accountToValue(account);
        assertEquals(account.getId(), result.id());
        assertEquals(String.format("%s - %s %s", account.getIban(), account.getBalance(), account.getCurrency()),
                result.value());
    }

    @Test
    void accountToValueMapping() {
        var card = TestDataGenerator.getCreditCard();
        var result = DataValueMapper.cardToValue(card);
        assertEquals(card.getId(), result.id());
        assertEquals("xxxx xxxx xxxx 4125 - Credit", result.value());
    }
}