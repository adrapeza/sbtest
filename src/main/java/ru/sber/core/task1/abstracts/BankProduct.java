package ru.sber.core.task1.abstracts;

public abstract class BankProduct {

    protected String currency;
    protected double balance;
    protected String name;

    public BankProduct(String currency, double balance, String name) {
        this.currency = currency;
        this.balance = balance;
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public String getName() {
        return name;
    }

    public abstract void deposit(double amount);

    public abstract void withdraw(double amount);

    public abstract double getBalance();

}
