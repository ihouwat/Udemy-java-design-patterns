// Observer pattern

package behavioral.observer;

import java.util.ArrayList;
import java.util.List;

class PropertyChangedEventsArgs<T>
{
    public T source;
    public String propertyName;
    public Object newValue;

    public PropertyChangedEventsArgs(T source,
                                     String propertyName, Object newValue)
    {
        this.source = source;
        this.propertyName = propertyName;
        this.newValue = newValue;
    }
}

interface Observer<T>
{
 /*
    If you want to monitor an object of type T,
    you implement this interface, define a handle method,
    and hope that the handle method executes when a change is made
  */
 void handle(PropertyChangedEventsArgs<T> args);
}

class Observable<T>
/*
    This class has a list of all subscribers watching a particular class.
 */
{
 private List<Observer<T>> observers = new ArrayList<>();

 // API for subscribing to this observable so as to monitor changes
    public void subscribe(Observer<T> observer)
    {
        observers.add(observer);
    }

    // Notify every subscriber when a property changes
    protected void propertyChanged(T source,
                                   String propertyName,
                                   Object newValue)
    {
        for (Observer<T> o : observers)
        {
            o.handle(new PropertyChangedEventsArgs<T>(
                source, propertyName, newValue
            ));
        }
    }
}

// Person is observable because it is generating an event
class Person extends Observable<Person>
{
    private int age; // property of 'age' includes field, getter, and setter

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (this.age == age) return; // age is the same, do nothing
        this.age = age;
        propertyChanged(this, "age", age); // implement observable of person
    }
}

class ObserverObservableDemo implements Observer<Person>
{
    public static void main(String[] args)
    {
        new ObserverObservableDemo(); // so we get out of static context
    }

    public ObserverObservableDemo()
    {
        Person person = new Person();
        person.subscribe(this); // the demo is the observer
        for (int i = 20; i < 24; ++i)
            person.setAge(i);
    }

    @Override
    public void handle(PropertyChangedEventsArgs<Person> args) {
        System.out.println("Person's " + args.propertyName
            + " has changed to " + args.newValue);
    }
}