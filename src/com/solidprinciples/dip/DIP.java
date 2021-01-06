// Dependency Inversion Principle - family tree application as the example

// A. High-level modules should not depend on low-level modules.
// Both should depend on abstractions.

// B. Abstractions should not depend on details.
// Details should depend on abstractions.

package solidprinciples.dip;

import java.lang.reflect.Array;
import java.util.ArrayList;

import java.util.*;
import java.util.stream.Collectors;

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

// abstraction here to implement on low-level module
interface RelationshipBrowser {
    List<Person> findAllChildrenOf(String name);
}

class Relationships implements RelationshipBrowser // low-level module, it's related to data storage/keeping a list. No business logic.
{
    private List<Triplet<Person, Relationship, Person>> relations = new ArrayList<>();

    /* Problem: we are exposing an internal storage implementation of relations
    (the private variable above) as a public getter for everyone to access */
    public List<Triplet<Person, Relationship, Person>> getRelations() {
        return relations;
    }

    public void addParentAndChild(Person parent, Person child) {
        relations.add(new Triplet<>(parent, Relationship.PARENT, child));
        relations.add(new Triplet<>(child, Relationship.CHILD, parent));
    }

    @Override
    public List<Person> findAllChildrenOf(String name) {
        return relations.stream()
            .filter(x -> Objects.equals(x.getValue0().name, name)
                && x.getValue1() == Relationship.PARENT)
            .map(Triplet::getValue2)
            .collect(Collectors.toList());
    }
}

class Research // high-level module, allows us to perform operations on lower-level constructs
{
    /* Violating DIP: constructor of high-level module taking low-level
    module as a dependency */
//    public Research(Relationships relationships) {
//        List<Triplet<Person, Relationship, Person>> relations = relationships.getRelations();
//        relations.stream()
//            .filter(x -> x.getValue0().name.equals("John")
//                && x.getValue1() == Relationship.PARENT)
//            .forEach(ch -> System.out.println (
//                "John has a child called " + ch.getValue2().name
//            ));
//    }

    //Correct implementation uses abstract interface
    public Research(RelationshipBrowser browser) {
        List<Person> children = browser.findAllChildrenOf("John");
        for (Person child : children)
            System.out.println(("John has a child called " + child.name));
    }
}

class DIPDemo {
    public static void main(String[] args) {
        Person parent = new Person("John");
        Person child1 = new Person("Chris");
        Person child2 = new Person("Matt");

        Relationships relationships = new Relationships();
        relationships.addParentAndChild(parent, child1);
        relationships.addParentAndChild(parent, child2);

        new Research(relationships);
    }
}
