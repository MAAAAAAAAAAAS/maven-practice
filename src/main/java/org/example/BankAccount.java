package org.example;

public class BankAccount {
    private String owner;
    private double balance;

    public BankAccount(String owner, double balance) {
        this.owner = owner;
        this.balance = balance;
    }

    public String getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
        } else {
            throw new IllegalArgumentException("Сумма пополнения должна быть > 0");
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма снятия должна быть > 0");
        } else if (amount > balance) {
            throw new IllegalArgumentException("Недостаточно средств");
        } else {
            this.balance -= amount;
        }
    }
}