// Dynamic null object pattern

package behavioral.nullobject.dynamicnullobject;


import java.lang.reflect.Proxy;

interface Log
{
    void info(String msg);
    void warn(String msg);
}

class ConsoleLog implements Log
{

    @Override
    public void info(String msg) {
        System.out.println(msg);
    }

    @Override
    public void warn(String msg) {
        System.out.println("WARNING: " + msg);
    }
}

class BankAccount
{
    private Log log;
    private int balance;

    public BankAccount(Log log) {
        this.log = log;
    }

    public void deposit(int amount)
    {
        balance += amount;

        log.info("Deposited " + amount); // hard dependency on 'Log'
    }
}

// Null object
final class NullLog implements Log
{
    // Leave methods empty and fields default value
    @Override
    public void info(String msg) {

    }

    @Override
    public void warn(String msg) {

    }
}

class DynamicNullObjectDemo
{
    // Dynamic null object construction here - utility static method
    @SuppressWarnings("unchecked")
    public static <T> T noOp(Class<T> itf)
    {
        return (T) Proxy.newProxyInstance(
            itf.getClassLoader(),
            new Class<?>[]{itf},
            (proxy, method, args) ->
            {
                if (method.getReturnType().equals(Void.TYPE))
                    return null;
                else
                    return method.getReturnType().getConstructor().newInstance();
            });
    }

    public static void main(String[] args) {
        Log log = noOp(Log.class); // at runtime, Java will construct a fake object conforming to the Log interface to check which interface to return
        BankAccount account = new BankAccount(log);

        account.deposit(100);
    }
}