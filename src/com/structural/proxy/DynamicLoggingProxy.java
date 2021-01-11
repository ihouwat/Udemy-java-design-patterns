/*
 Dynamic Proxy for logging
 Example: build a dynamic proxy which takes an existing object of type Person
 and counts the number inside of Person that have been called
 */

package structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

interface Human
{
    void walk();
    void talk();
}

class Person implements Human
{

    @Override
    public void walk() {
        System.out.println("I am walking");
    }

    @Override
    public void talk() {
        System.out.println("I am talking");
    }
}

class LoggingHandler implements InvocationHandler
{
    private final Object target; // Need reference to an object
    private Map<String, Integer> calls = new HashMap<>(); // map names of methods to how many times they've been called

    public LoggingHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String name = method.getName();

        // Here we intercept the toString function and change its output
        if (name.contains("toString"))
        {
            return calls.toString();
        }

        calls.merge(name, 1, Integer::sum);  // merge explanation: https://www.geeksforgeeks.org/hashmap-mergekey-value-bifunction-method-in-java-with-examples/
        return method.invoke(target, args); // reflection API
    }
}

class DynamicProxyDemo
{
    // Utility function
    @SuppressWarnings("unchecked")
    public static <T> T withLogging(T target, Class<T> itf)
    {
        return (T) Proxy.newProxyInstance(
                itf.getClassLoader(),
                new Class<?>[] { itf },
                new LoggingHandler(target)
        );
    }

    public static void main(String[] args)
    {
        Person person = new Person();
        Human logged = withLogging(person, Human.class);
        logged.talk();
        logged.walk();
        logged.walk();
        System.out.println(logged);
    }
}