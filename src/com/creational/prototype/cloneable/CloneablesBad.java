// Cloneable interface - don't use!

package creational.prototype.cloneable;

import java.util.Arrays;

class Address implements Cloneable
{
    public String streetName;
    public int houseNumber;

    public Address(String streetName, int houseNumber) {
        this.streetName = streetName;
        this.houseNumber = houseNumber;
    }

    @Override
    public String toString() {
        return "Address{" +
                "streetName='" + streetName + '\'' +
                ", houseNumber=" + houseNumber +
                '}';
    }

    // deep copy
    @Override
    public Object clone() throws CloneNotSupportedException {
        // Naive implementation
        return new Address(streetName,houseNumber);
    }
}

class Person implements Cloneable
{
    public String [] names;
    public Address address;

    public Person(String[] names, Address address) {
        this.names = names;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "names=" + Arrays.toString(names) +
                ", address=" + address +
                '}';
    }

    // deep copy
    @Override
    public Object clone() throws CloneNotSupportedException {
        return new Person(
            names.clone(),
            (Address) address.clone()); // incorrect, since you are copying references
    }
}

class CloneablesBadDemo {
    public static void main(String[] args) throws Exception  {
        Person john =
                new Person(new String[]{"John", "Smith"},
                new Address("London Road", 123));

//        Person jane = john; // without Cloneable interface: problem line, we're copying the reference, so both variables share the same object and its data
        Person jane = (Person) john.clone(); // with Cloneable interface
        jane.names[0] = "Jane";
        jane.address.houseNumber = 124;

        System.out.println(john);
        System.out.println(jane);
    }
}