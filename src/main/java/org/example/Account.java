package org.example;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private double balance;
    private List<Transaction> transactions;

    public Account() {
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }

    public boolean deposit(double amount) {
        if (amount <= 0) {
            return false;
        }
        balance += amount;
        transactions.add(new Transaction("Deposit", amount, balance));
        return true;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        }
        balance -= amount;
        transactions.add(new Transaction("Withdrawal", amount, balance));
        return true;
    }

    public boolean payment(String description, double amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        }
        balance -= amount;
        transactions.add(new Transaction("Payment: " + description, amount, balance));
        return true;
    }

    public List<Transaction> getTransactionHistory() {
        return transactions;
    }

    public double getBalance() {
        return balance;
    }

    // Внутренний класс
    private static class Transaction {
        private String type;
        private double amount;
        private double balanceAfter;
        private long timestamp;

        public Transaction(String type, double amount, double balanceAfter) {
            this.type = type;
            this.amount = amount;
            this.balanceAfter = balanceAfter;
            this.timestamp = System.currentTimeMillis();
        }

        public double getBalanceAfter() {
            return balanceAfter;
        }

        @Override
        public String toString() {
            return "Операция: " + type +
                    ", Сумма: " + amount +
                    ", Баланс после операции: " + balanceAfter +
                    ", Время: " + new java.util.Date(timestamp);
        }
    }

    public static void main(String[] args) {
        Account account = new Account();

        // Пополнение
        if (account.deposit(1000)) {
            System.out.println("Депозит успешно выполнен. Текущий баланс: " + account.getBalance());
        } else {
            System.out.println("Ошибка: Сумма депозита должна быть положительной.");
        }

        // Снятие
        if (account.withdraw(200)) {
            System.out.println("Снятие успешно выполнено. Текущий баланс: " + account.getBalance());
        } else {
            System.out.println("Ошибка: Недостаточно средств или сумма снятия неверна.");
        }

        // Платеж
        if (account.payment("Оплата интернета", 150)) {
            System.out.println("Платеж выполнен. Текущий баланс: " + account.getBalance());
        } else {
            System.out.println("Ошибка: Недостаточно средств для платежа.");
        }

        // Вывод истории операций
        System.out.println("История операций:");
        for (var transaction : account.getTransactionHistory()) {
            System.out.println(transaction);
        }
    }
}
