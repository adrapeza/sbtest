package ru.sber.core.task1.products;

import ru.sber.core.task1.Card;
import ru.sber.core.task1.interfaces.CreditCardInterface;

public class CreditCard extends Card implements CreditCardInterface {

    private final double interestRate;

    public CreditCard(String currency, double balance, String name, double interestRate) {
        super(currency, balance, name);
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    @Override
    public double checkDebt() {
        return 1000.0;
    }
}
