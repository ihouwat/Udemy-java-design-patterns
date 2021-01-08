/*
Testability issues of using singletons in 'real' situations
Example looks at loading a single instance of a database at any one time
 */

package creational.singleton;

import com.google.common.collect.Iterables;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

interface Database
{
    int getPopulation(String name);
}

class SingletonDatabase implements Database // here, we have an idea of an abstraction
{
    private Dictionary<String, Integer> capitals
        = new Hashtable<>();
    private static int instanceCount = 0;
    public static int getCount() { return instanceCount; }

    private SingletonDatabase()
    {
        instanceCount++;
        System.out.println("Initializing database");

        try
        {
            File file = new File(
                SingletonDatabase.class.getProtectionDomain()
                    .getCodeSource().getLocation().getPath()
            );

            Path fullPath = Paths.get(f.getPath(), "capitals.txt");
            List<String> lines = Files.readAllLines(fullPath);

            Iterables.partition(lines, 2)
                    .forEach(kv -> capitals.put(
                        kv.get(0).trim(),
                        Integer.parseInt(kv.get(1))
                    ));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static final SingletonDatabase INSTANCE
        = new SingletonDatabase();

    public static SingletonDatabase getInstance()
    {
        return INSTANCE;
    }

    public int getPopulation(String name)
    {
        return capitals.get(name);
    }
}

class SingletonRecordFinder
{
    public int getTotalPopulation(List<String> names)
    {
        int result = 0;
        for (String name : names)
        {
            /*
            PROBLEM here is testability
            The only way to test this method is with the live database.
            If you remember dependency inversion principle, you should depend
            on abstractions. However, this is a concrete class.

            Ideally it would be with a dummy database.
             */
            result += SingletonDatabase.getInstance().getPopulation(name);
        }
        return result;
    }
}

class ConfigurableRecordFinder
{
    // Introduce an abstraction, to respect dependency inversion principle
    private Database database;

    // Database injected into the constructor
    public ConfigurableRecordFinder(Database database) {
        this.database = database;
    }

    public int getTotalPopulation(List<String> names)
    {
        int result = 0;
        for (String name : names)
            result += database.getPopulation(name); // dependency injection; dummy database here
        return result;
    }
}

class DummyDatabase implements Database
{
    private Dictionary<String, Integer>
        data = new Hashtable<>();

    public DummyDatabase() { // dummy data added here
        data.put("alpha", 1);
        data.put("beta", 2);
        data.put("gamma", 3);
    }

    @Override
    public int getPopulation(String name) {
        return data.get(name);
    }
}

class Tests
{
    @Test // not a unit test, but an integration test
    public void singletonTotalPopulationTest()
    {
        SingletonRecordFinder rf = new SingletonRecordFinder();
        List<String> names = List.of("Seoul", "Mexico City");
        int tp = rf.getTotalPopulation(names);
        assertEquals(1750000+1740000, tp); // this test will work
    }

    @Test // unit test
    public void dependentPopulationTest()
    {
        DummyDatabase db = new DummyDatabase();
        final ConfigurableRecordFinder rf = new ConfigurableRecordFinder(db);
        assertEquals(4, rf.getTotalPopulation(
            List.of("alpha", "gamma")
        ));
    }
}