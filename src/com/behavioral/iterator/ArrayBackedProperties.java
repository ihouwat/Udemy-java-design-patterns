// Array-backed properties

package behavioral.iterator;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

class SimpleCreature
{
    private int strength, agility, intelligence;

    public int max()
    {
        return Math.max(strength,
            Math.max(agility, intelligence));
    }

    public int sum()
    {
        return strength+agility+intelligence;
    }

    public double average()
    {
        return sum() / 3.0;
    }

    // Getters and setters
    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }
}

// class masquerading as an array
// you can take various elements, group them in one object, and perform stream operations on them
class Creature implements Iterable<Integer>
{
    private int [] stats = new int[3]; // array of statistics

    private final int STR = 0;


    public int getStrength() { return  stats[STR]; }
    public void setStrength(int value)
    {
        stats[STR] = value;
    }

    public int getAgility() { return stats[1]; }
    public void setAgility(int value)
    {
        stats[1] = value;
    }

    public int getIntelligence() { return stats[2]; }
    public void setIntelligence(int value)
    {
        stats[2] = value;
    }
    public int sum()
    {
        return IntStream.of(stats).sum();
    }

    public int max()
    {
        return IntStream.of(stats).max().getAsInt();
    }

    public double average()
    {
        return IntStream.of(stats).average().getAsDouble();
    }

    @Override
    public Iterator<Integer> iterator() {
        return IntStream.of(stats).iterator();
    }

    @Override
    public void forEach(Consumer<? super Integer> action) {
        for (int x : stats)
            action.accept(x);
    }

    @Override
    public Spliterator<Integer> spliterator() {
        return IntStream.of(stats).spliterator();
    }
}

class ArrayBackedPropertiesDemo
{
    public static void main(String[] args) {
        Creature creature = new Creature();
        creature.setAgility(12);
        creature.setIntelligence(13);
        creature.setStrength(17);

        System.out.println(
            "Creature has a max stat of " + creature.max()
            + ", total stats = " + creature.sum()
            + " average stat = " + creature.average()
        );
    }
}