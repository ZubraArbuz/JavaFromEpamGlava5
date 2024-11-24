package org.example;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account();
    }

    @Test
    void testDepositSuccess() {
        assertTrue(account.deposit(1000), "Депозит не выполнен, хотя сумма положительна.");
        assertEquals(1000, account.getBalance(), "Баланс после депозита должен быть равен 1000.");
    }

    @Test
    void testDepositFailure() {
        assertFalse(account.deposit(-500), "Депозит с отрицательной суммой не должен быть выполнен.");
        assertEquals(0, account.getBalance(), "Баланс должен остаться равным 0 после неудачного депозита.");
    }

    @Test
    void testWithdrawSuccess() {
        account.deposit(1000); // Пополнение счета перед снятием
        assertTrue(account.withdraw(500), "Снятие не выполнено, хотя средств достаточно.");
        assertEquals(500, account.getBalance(), "Баланс после снятия должен быть равен 500.");
    }

    @Test
    void testWithdrawFailureDueToInsufficientFunds() {
        account.deposit(300);
        assertFalse(account.withdraw(500), "Снятие должно быть отклонено из-за недостатка средств.");
        assertEquals(300, account.getBalance(), "Баланс не должен измениться при неудачном снятии.");
    }

    @Test
    void testPaymentSuccess() {
        account.deposit(1000);
        assertTrue(account.payment("Оплата интернета", 200), "Платеж не выполнен, хотя средств достаточно.");
        assertEquals(800, account.getBalance(), "Баланс после платежа должен быть равен 800.");
    }

    @Test
    void testPaymentFailureDueToInsufficientFunds() {
        account.deposit(100);
        assertFalse(account.payment("Оплата интернета", 200), "Платеж должен быть отклонен из-за недостатка средств.");
        assertEquals(100, account.getBalance(), "Баланс не должен измениться при неудачном платеже.");
    }

    @Test
    void testTransactionHistory() {
        account.deposit(1000);
        account.withdraw(200);
        account.payment("Оплата интернета", 100);

        assertEquals(3, account.getTransactionHistory().size(), "История операций должна содержать 3 записи.");
    }
}

