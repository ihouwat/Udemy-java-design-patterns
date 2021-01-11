/*
Given a Person class, implement a ResponsiblePerson proxy that does the following:
- Allows person to drink unless they are younger than 18 ("too young")
- Allows person to drive unless they are younger than 16 ("too young")
- In case of driving while drinking, returns "dead"

 */

package structural.proxy.exercise;

class Person
{
    private int age;

    public Person(int age)
    {
        this.age = age;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String drink() { return "drinking"; }
    public String drive() { return "driving"; }
    public String drinkAndDrive() { return "driving while drunk"; }
}

class ResponsiblePerson
{
    private Person person;

    public ResponsiblePerson(Person person)
    {
        this.person = person;
    }
    public String drink()
    {
        if (person.getAge() <= 18) return "too young";
        else return person.drink();
    }

    public String drive()
    {
        if (person.getAge() <= 16) return "too young";
        else return person.drive();
    }

    public String drinkAndDrive()
    {
        return "dead";
    }

    public int getAge()
    {
        return person.getAge();
    }

    public void setAge(int age)
    {
        person.setAge(age);
    }
}

class ProxyExercise
{
    public static void main(String[] args) {
        Person p = new Person(10);
        ResponsiblePerson rp = new ResponsiblePerson(p);
        System.out.println(rp.drink());
        System.out.println(rp.drive());
        System.out.println(rp.drinkAndDrive());
        rp.setAge(20);
        System.out.println(rp.drink());
        System.out.println(rp.drive());
        System.out.println(rp.drinkAndDrive());
    }
}