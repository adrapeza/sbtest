package ru.sber.core.task1;

import ru.sber.core.task1.abstracts.BankProduct;

public class Card extends BankProduct {

    public Card(String currency, double balance, String name) {
        super(currency, balance, name);
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    @Override
    public void withdraw(double amount) {
        balance -= amount;
    }

    @Override
    public double getBalance() {
        return balance;
    }
}
