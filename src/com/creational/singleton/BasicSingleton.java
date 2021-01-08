// Basic Singleton

package creational.singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class BasicSingleton implements Serializable
{
    private BasicSingleton()
    { }

    // Private instance
    private static final BasicSingleton INSTANCE
        = new BasicSingleton();

    // Expose the singleton
    public static BasicSingleton getInstance()
    {
        return INSTANCE;
    }

    private int value = 0;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    // Gives JVM hint that when serialization happens, has to happen into this instance
    protected Object readResolve()
    {
        return INSTANCE;
    }
}

class BasicSingletonDemo
{
    // Serialization code
    static void saveToFile(BasicSingleton singleton,
                           String filename)
        throws Exception
    {
        try (FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut))
        {
            out.writeObject(singleton);
        }
    }

    // Deserialization code
    static BasicSingleton readFromFile(String filename)
        throws Exception
    {
        try (FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn))
        {
            return (BasicSingleton) in.readObject();
        }
    }

    public static void main(String[] args)
        throws Exception
    {
//        new BasicSingleton(); // cannot call with private constructor
        BasicSingleton singleton = BasicSingleton.getInstance();
        singleton.setValue(111);

//        // Show how serialization is problematic with singletons
        String filename = "singleton.bin";
        saveToFile(singleton, filename);

        singleton.setValue(222);

        BasicSingleton singleton2 = readFromFile(filename);

        System.out.println(singleton == singleton2); // returns false without readResolve() method above

        System.out.println(singleton.getValue()); // returns 222
        System.out.println(singleton2.getValue()); // returns 111
    }
}