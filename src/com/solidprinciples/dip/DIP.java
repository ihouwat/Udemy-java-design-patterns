// DIP - family tree application as the example

// A. High-level modules should not depend on low-level modules.
// Both should depend on abstractions.

// B. Abstractions should not depend on details.
// Details should depend on abstractions.

package solidprinciples.dip;

import java.lang.reflect.Array;
import java.util.ArrayList;

import java.util.*;
import org.javatuples.Triplet;

enum Relationship {
    PARENT,
    CHILD,
    SIBLING
}

class Person {
    public String name;
    //dob, nationality, etc in a real app

    public Person(String name) {
        this.name = name;
    }
}

class Relationships {
    private List<Triplet<Person, Relationship, Person>> relations = new ArrayList<>();
}
