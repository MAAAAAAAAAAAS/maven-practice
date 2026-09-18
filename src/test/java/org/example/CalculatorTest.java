package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @Test
    void add_shouldReturnSum() {
        assertEquals(5, calculator.add(2, 3));
    }

    @Test
    void subtract_shouldReturnDifference() {
        assertEquals(1, calculator.subtract(3, 2));
    }

    @Test
    void multiply_shouldReturnProduct() {
        assertEquals(6, calculator.multiply(2, 3));
    }

    @Test
    void divide_shouldReturnQuotient() {
        assertEquals(2, calculator.divide(6, 3));
    }

    @Test
    void divide_byZero_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> calculator.divide(10, 0));
    }

    @Test
    void subtract_shouldReturnNegative() {
        assertEquals(-2, calculator.subtract(4, 6));
    }
}