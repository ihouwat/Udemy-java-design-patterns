// Memento pattern

package behavioral.memento;

// bank account token here
class Memento
{
    private int balance;

    public int getBalance() {
        return balance;
    }

    public Memento(int balance) {
        this.balance = balance;
    }
}

class BankAccount
{
    private int balance;

    public BankAccount(int balance) {
        this.balance = balance;
    }

    // instead of operations be void, they return a memento
    public Memento deposit(int amount)
    {
        balance += amount;
        return new Memento(balance);
    }

    public void restore(Memento m)
    {
        balance = m.getBalance();
    }

    @Override
    public String toString() {
        return "BankAccount{" +
            "balance=" + balance +
            '}';
    }
}

class MementoDemo
{
    public static void main(String[] args) {
        BankAccount ba = new BankAccount(100);
        Memento memento1 = ba.deposit(50); // 150
        Memento memento2 = ba.deposit(25); // 175
        System.out.println(ba);

        // restore to m1
        ba.restore(memento1);
        System.out.println(ba);

        // restore to m2
        ba.restore(memento2);
        System.out.println(ba);
    }
}
