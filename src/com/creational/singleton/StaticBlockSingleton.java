// Static Block Singleton

package creational.singleton;

import java.io.File;
import java.io.IOException;

class StaticBlockSingleton
{
    private StaticBlockSingleton() throws IOException
    {
        System.out.println("Singleton is initializing");
        File.createTempFile(".", "."); // invalid statement
    }

    private static StaticBlockSingleton instance;

    static
    {
        try
        {
            instance = new StaticBlockSingleton();
        }
        catch (Exception e)
        {
            System.out.println("failed to create singleton");
        }
    }

    public static StaticBlockSingleton getInstance()
    {
        return instance;
    }
}
