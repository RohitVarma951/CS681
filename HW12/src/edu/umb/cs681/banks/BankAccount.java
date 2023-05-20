package edu.umb.cs681.banks;

public interface BankAccount {
    public void deposit(double amount);
    public void withdraw(double amount);
    public double getBalance();
}
