package com.assignment.bank.util;

import com.assignment.bank.entity.Account;
import com.assignment.bank.entity.Card;
import com.assignment.bank.response.IdValueWrapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DataValueMapper {

    private static final String ACCOUNT_VALUE_TEMPLATE = "%s - %s %s";

    private static final String CARD_VALUE_TEMPLATE = "%s - %s";

    private static final String MASKED_CARD_NUMBER_TEMPLATE = "xxxx xxxx xxxx %s";

    public static IdValueWrapper cardToValue(Card card) {
        String value = String.format(CARD_VALUE_TEMPLATE, getMaskedCardNumber(card.getCardNumber()), card.getType());
        return new IdValueWrapper(card.getId(), value);
    }

    public static IdValueWrapper accountToValue(Account account) {
        String value = String.format(ACCOUNT_VALUE_TEMPLATE, account.getIban(),
                account.getBalance(), account.getCurrency());
        return new IdValueWrapper(account.getId(), value);
    }

    private static String getMaskedCardNumber(String cardNumber) {
        return String.format(MASKED_CARD_NUMBER_TEMPLATE, cardNumber.split(" ")[3]);
    }
}
