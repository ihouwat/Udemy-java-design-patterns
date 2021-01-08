// Lazy singleton and thread safety

package creational.singleton;

class LazySingleton
{
    private static LazySingleton instance;

    private LazySingleton()
    {
        System.out.println("initializing a lazy singleton");
    }

    // synchronized for thread-safety - performance is an issue
//    public static synchronized LazySingleton getInstance()
//    {
//        // Checks if it has been created on lazy loading
//        if (instance == null)
//        {
//            instance = new LazySingleton();
//        }
//        return instance;
//    }

    // double-checked locking: check the instance loading twice
    public static LazySingleton getInstance()
    {
        if (instance == null)
        {
            synchronized (LazySingleton.class)
            {
                if (instance == null)
                {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }
}