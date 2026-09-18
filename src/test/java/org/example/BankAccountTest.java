package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class BankAccountTest {
    private BankAccount bankAccount;

    @BeforeEach
    void setUp() {
        bankAccount = new BankAccount("Саня", 1000);
    }

    @Test
    void deposit_shouldIncreaseBalance() {
        bankAccount.deposit(550);
        assertEquals(1550.0, bankAccount.getBalance(),0.001);
    }

    @Test
    void deposit_negativeAmount_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(-100));
    }

    @Test
    void withdraw_shouldDecreaseBalance() {
        bankAccount.withdraw(100);
        assertEquals(900, bankAccount.getBalance(), 0.001);
    }

    @Test
    void withdraw_negativeAmount_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(-100));
    }

    @Test
    void withdraw_moreThanBalance_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(2000));
    }

    @Test
    void getOwner_shouldReturnOwner() {
        assertEquals("Саня", bankAccount.getOwner());
    }

    @Test
    void getBalance_shouldReturnBalance() {
        assertEquals(1000, bankAccount.getBalance(), 0.001);
    }
}
