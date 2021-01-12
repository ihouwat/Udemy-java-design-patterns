// Command pattern

package behavioral.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class BankAccount
{
    private int balance;
    private int overdraftLimit = -500;

    public void deposit(int amount)
    {
        balance += amount;
        System.out.println("Deposited " + amount
        + ", balance is now " + balance);
    }

    public boolean withdraw(int amount)
    {
        if (balance - amount >= overdraftLimit)
        {
            balance -= amount;
            System.out.println("Withdrew " + amount
                    + ", balance is now " + balance);
            return true;
        }
        else System.out.println("Cannot withdraw " + amount);
        return false;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "balance=" + balance +
                '}';
    }
}

interface Command
{
    void call(); // what you need to call to apply the command
    void undo();
}

//Command design pattern here
class BankAccountCommand implements Command
{
    private BankAccount account;
    private boolean succeeded; //flag

    @Override
    public void call()
    {
        switch (action) {
            case DEPOSIT:
                succeeded = true;
                account.deposit(amount);
                break;
            case WITHDRAW:
                succeeded = account.withdraw(amount); // way to figure out if an operation succeeded
                break;
        }
    }

    @Override
    public void undo()
    {
        if(!succeeded) return;
        switch (action) {
            case DEPOSIT:
                account.withdraw(amount);
                break;
            case WITHDRAW:
                account.deposit(amount);
                break;
        }
    }

    public enum Action
    {
        DEPOSIT, WITHDRAW // only two possible operations
    }

    private Action action;
    private int amount;

    public BankAccountCommand(BankAccount account, Action action, int amount) {
        this.account = account;
        this.action = action;
        this.amount = amount;
    }
}

class CommandDemo
{
    public static void main(String[] args) {
        BankAccount ba = new BankAccount();
        System.out.println(ba);

        List<BankAccountCommand> commands = new ArrayList<>(List.of(
                new BankAccountCommand(ba, BankAccountCommand.Action.DEPOSIT, 100),
                new BankAccountCommand(ba, BankAccountCommand.Action.WITHDRAW, 1000)
        ));

        for (Command c : commands)
        {
            c.call();
            System.out.println(ba);
        }

        Collections.reverse(commands);
        for (Command c : commands)
        {
            c.undo();
            System.out.println(ba);
        }
    }
}