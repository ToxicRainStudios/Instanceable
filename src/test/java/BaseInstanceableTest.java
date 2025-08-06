import com.toxicrain.instanceable.BaseInstanceable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseInstanceableTest {

    // Sample subclass for testing
    public static class MySingleton extends BaseInstanceable<MySingleton> {
        private MySingleton() {}

        public static MySingleton getInstance() {
            return BaseInstanceable.getInstance(MySingleton.class);
        }
    }

    // Static inner classes for subclass isolation test
    public static class A extends BaseInstanceable<A> {
        public A() {} // public no-arg constructor
    }

    public static class B extends BaseInstanceable<B> {
        public B() {} // public no-arg constructor
    }

    @Test
    void testGetInstanceReturnsSameObject() {
        MySingleton instance1 = MySingleton.getInstance();
        MySingleton instance2 = MySingleton.getInstance();

        assertNotNull(instance1);
        assertSame(instance1, instance2, "Expected the same instance on repeated calls");
    }

    @Test
    void testDifferentSubclassesGetDifferentInstances() {
        A instanceA = BaseInstanceable.getInstance(A.class);
        B instanceB = BaseInstanceable.getInstance(B.class);

        assertNotNull(instanceA);
        assertNotNull(instanceB);
        assertNotSame(instanceA, instanceB, "Instances of different subclasses should not be the same");
    }

    @Test
    void testExceptionWhenNoConstructor() {
        class NoConstructor extends BaseInstanceable<NoConstructor> {
            private NoConstructor(String arg) {} // Only arg constructor
        }

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            BaseInstanceable.getInstance(NoConstructor.class);
        });

        assertTrue(ex.getMessage().contains("Failed to create instance for class"));
    }
}
