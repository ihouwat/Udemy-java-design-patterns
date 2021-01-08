// Lazy singleton and thread safety, inner static singleton

package creational.singleton;

class LazySingleton
{
    private static LazySingleton instance;

    private LazySingleton()
    {
        System.out.println("initializing a lazy singleton");
    }

    // 1st approach: synchronized for thread-safety - performance is an issue
//    public static synchronized LazySingleton getInstance()
//    {
//        // Checks if it has been created on lazy loading
//        if (instance == null)
//        {
//            instance = new LazySingleton();
//        }
//        return instance;
//    }

    // 2nd approach: double-checked locking: check the instance loading twice
//    public static LazySingleton getInstance()
//    {
//        if (instance == null)
//        {
//            synchronized (LazySingleton.class)
//            {
//                if (instance == null)
//                {
//                    instance = new LazySingleton();
//                }
//            }
//        }
//        return instance;
//    }
}

//    3rd approach: inner static class

    class InnerStaticSingleton
    {
        private InnerStaticSingleton(){}
        // inner class here
        private static class Impl
        {
            private static final InnerStaticSingleton
                INSTANCE = new InnerStaticSingleton(); // inner classes can access private members of outer classes
        }

        //Expose instance - this approach avoids the problem of synchronization
        public InnerStaticSingleton getInstance()
        {
            return Impl.INSTANCE;
        }
    }

