package ru.sber.core.task1;

import ru.sber.core.task1.abstracts.BankProduct;
import ru.sber.core.task1.interfaces.DepositInterface;

public class Deposit extends BankProduct implements DepositInterface {

    public Deposit(String currency, double balance, String name) {
        super(currency, balance, name);
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    @Override
    public void withdraw(double amount) {
        //nothing
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public void closeDeposit() {
        System.out.println("deposit has bee closed.");
        balance = 0;
    }
}
