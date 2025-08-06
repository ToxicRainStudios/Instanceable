# Instanceable
A lightweight utility for providing singleton-style instance management using Java reflection. Automatically handles instance creation and ensures only one instance per subclass without needing to declare static fields manually.

## Installing
Add Jitpack to your build.gradle file:

```groovy
maven { url 'https://jitpack.io' }
```
Then add the dependency to your project:

```groovy
implementation 'com.github.ToxicRainStudios:Instanceable:VERSION'
```
Replace VERSION with the desired release tag (e.g. 1.0.0).

## Usage
### BaseInstanceable
A base class that uses reflection to keep track of one instance per subclass. Makes it easy to use the singleton pattern across different class types.
### How It Works
Use `BaseInstanceable.getInstance(MyClass.class)` to retrieve the singleton instance.

The subclass must have a no-arg constructor (can be private or protected).

### Example
```java

public class MyService extends BaseInstanceable<MyService> {
    private MyService() {
    // Initialization logic
    }
    }

    public void doSomething() {
        System.out.println("Singleton logic executed.");
    }
}
```
```
// Usage
MyService service = MyService.getInstance();
service.doSomething();
```