/*
Singleton section exercise: write a method called isSingleton(). It takes a factory method that returns
an object, and it is up to you to determine whether or not that object is a sinleton instance
 */

package creational.singleton;

import java.util.function.Supplier;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

class SingletonTester
{
    public static boolean isSingleton(Supplier<Object> func)
    {
        return func.get() == func.get();
    }

}

//In another file you'd have this test
// import org.junit.Test;
// import static org.junit.Assert.assertTrue;
// import static org.junit.Assert.assertFalse;
//
//public class Evaluate
//{
//    @Test
//    public void test()
//    {
//        Object obj = new Object();
//        assertTrue(SingletonTester.isSingleton(() -> obj));
//        assertFalse(SingletonTester.isSingleton(Object::new));
//    }
//}
