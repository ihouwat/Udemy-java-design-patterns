// Property proxy - a bit unusual

package structural.proxy;

import java.util.Objects;

// Property class set up to fully represent a property
class Property<T>
{
    private T value;

    public Property(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        // logging
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Property<?> property = (Property<?>) o;
        return value.equals(property.value);
    }

    @Override
    public int hashCode() {
        return value!= null ? value.hashCode() : 0;
    }
}

//class Creature
//{
//    private int agility;
//
//    public Creature()
//    {
//
//    }
//
//    public Creature(int agility) {
////        agility = 123; // not recorded anywhere; agility itself has no getter/setter
//    }
//
//    public int getAgility() {
//        return agility;
//    }
//
//    public void setAgility(int agility) {
//        this.agility = agility;
//    }
//}

class Creature
{
    private Property<Integer> agility = new Property<>(0);

    public void setAgility(int value)
    {
        agility.setValue(value);
    }

    public int getAgility()
    {
        return agility.getValue();
    }
}

class PropertyProxyDemo
{
    public static void main(String[] args)
    {
        Creature creature = new Creature();
        creature.setAgility(3);
        System.out.println(creature.getAgility());
    }
}
