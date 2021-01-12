/*
Exercise for Command patterns section
Implement Account.process() to process the different commands
 */

package behavioral.command;

class CommandImpl
{
    enum Action
    {
        DEPOSIT, WITHDRAW
    }

    public Action action;
    public int amount;
    public boolean success;

    public CommandImpl(Action action, int amount)
    {
        this.action = action;
        this.amount = amount;
    }
}

class Account
{
    public int balance;

    public Account() {}

    public void process(CommandImpl c)
    {
        switch (c.action)
        {
            case DEPOSIT -> {
                c.success = true;
                balance += c.amount;
            }
            case WITHDRAW -> {
                if (balance - c.amount < 0)
                    c.success = false;
                else {
                    c.success = true;
                    balance -= c.amount;
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                '}';
    }
}

class CommandExercise
{
    public static void main(String[] args)
    {
        Account a = new Account();
        CommandImpl command = new CommandImpl(CommandImpl.Action.DEPOSIT, 100);
        a.process(command);
        System.out.println(a);
        System.out.println(command.success);

        command = new CommandImpl(CommandImpl.Action.WITHDRAW, 50);
        a.process(command);
        System.out.println(a);
        System.out.println(command.success);

        command = new CommandImpl(CommandImpl.Action.WITHDRAW, 150);
        a.process(command);
        System.out.println(a);
        System.out.println(command.success);

    }
}