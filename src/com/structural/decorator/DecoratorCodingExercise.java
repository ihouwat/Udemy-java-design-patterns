/*
Exercise for decorator section
Complete the Dragon class, which is BOTH a Lizard and a Bird
 */

package structural.decorator;

class Bird
{
    public int age;

    public String fly()
    {
        return age < 10 ? "flying" : "too old";
    }
}

class Lizard
{
    public int age;

    public String crawl()
    {
        return (age > 1) ? "crawling" : "too young";
    }
}

class Dragon {
    private int age;
    // Solution: instead of inheriting or creating a common interface, you instantiate two classes in the Dragon class
    private Bird bird = new Bird();
    private Lizard lizard = new Lizard();

    // Need empty constructor
    public Dragon() {}

    public void setAge(int age)
    {
        // Set age of all animals to be the same
        this.age = bird.age = lizard.age = age;
    }
    public String fly()
    {
        return bird.fly();
    }
    public String crawl()
    {
        return lizard.crawl();
    }
}

class DecoratorExerciseDemo
{
    public static void main(String[] args) {
        Dragon dragon = new Dragon();
        dragon.setAge(0);
        System.out.println(dragon.crawl());
        System.out.println(dragon.fly());
    }
}