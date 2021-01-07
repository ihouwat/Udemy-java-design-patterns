/* Factory section exercise: given a class Person with two fields, id and name,
    implement a non-static PersonFactory that has a createPerson() that takes a person's name
    and returns a new instance of Person. The id returned is set on a 0-based index

 */

package creational.factories;

class Person
{
    public int id;
    public String name;

    public Person(int id, String name)
    {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

class PersonFactory
{
    private static int idCount = 0; // index-based counter for people

    public static Person createPerson(String name)
    {
        Person p =  new Person(idCount, name);
        idCount++;
        return p;
    }
}

class FactoryCodingExercise {
    public static void main(String[] args) {
        Person first = PersonFactory.createPerson("Igor");
        Person second = PersonFactory.createPerson("Lauren");
        Person third = PersonFactory.createPerson("Opus");

        System.out.println(first);
        System.out.println(second);
        System.out.println(third);
    }
}