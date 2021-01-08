// Multiton

package creational.singleton;

import java.util.HashMap;

enum Subsystem
{
    PRIMARY,
    AUXILIARY,
    FALLBACK
}

// Multiton: an object that exists in a finite set of subsystems
class Printer
{
    private static int instanceCount = 0;
    private Printer(){
        instanceCount++;
        System.out.println("A total of " +
            instanceCount + " instances created so far.");
    }

    private static HashMap<Subsystem, Printer>
            instances = new HashMap<>();

    // Provide a subsystem, you get a singleton for that subsystem
    public static Printer get(Subsystem subsystem)
    {
        if (instances.containsKey(subsystem))
            return instances.get(subsystem);

        Printer instance = new Printer(); // construction/lazy loading is put in the hash map!
        instances.put(subsystem, instance);
        return instance;
    }
}

class MultitonDemo {
    public static void main(String[] args) {
        Printer main = Printer.get(Subsystem.PRIMARY); // constructor is in the hashmap
        Printer aux = Printer.get(Subsystem.AUXILIARY);
        Printer aux2 = Printer.get(Subsystem.AUXILIARY); // not duplicated
    }
}