// Method chain

package behavioral.chainofresponsibility;

class Creature
{
    public String name;
    public int attack, defense;

    public Creature(String name, int attack, int defense) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
    }

    @Override
    public String toString() {
        return "Creature{" +
                "name='" + name + '\'' +
                ", attack=" + attack +
                ", defense=" + defense +
                '}';
    }
}

// Modifier here - base constructor
class CreatureModifier
{
    protected Creature creature;
    protected CreatureModifier next; // linked list-like approach?

    public CreatureModifier(Creature creature) {
        this.creature = creature;
    }

    public void add(CreatureModifier cm)
    {
        if(next != null)
        {
            next.add(cm); //recursive call on the next object until next == null
        }
        else next = cm;
    }

    public void handle()
    {
        if (next != null) next.handle(); // call handle on the entire chain
    }
}

class DoubleAttackModifier extends CreatureModifier
{
    public DoubleAttackModifier(Creature creature) {
        super(creature);
    }

    @Override
    public void handle() {
        System.out.println("Doubling " + creature.name + "'s attack");
        creature.attack *= 2;
        super.handle(); // this is critical to handle the chain of handlers
    }
}

class IncreaseDefenseModifier extends CreatureModifier
{
    public IncreaseDefenseModifier(Creature creature) {
        super(creature);
    }

    @Override
    public void handle() {
        System.out.println("Increasing " + creature.name + "'s defense");
        creature.defense += 3;
        super.handle();
    }
}

// HOW TO INTERRUPT CHAIN OF RESPONSIBILITY
class NoBonusesModifier extends CreatureModifier
{
    public NoBonusesModifier(Creature creature) {
        super(creature);
    }

    @Override
    public void handle() {
        //nothing - don't call super.handle() to interrupt chain after this one is called
        System.out.println("No bonuses for you!");
    }
}

class MethodChainDemo
{
    public static void main(String[] args) {
        Creature goblin = new Creature("Goblin", 2, 2);
        System.out.println(goblin);

        // Instantiate root class of CreatureModifier as a starting point
        CreatureModifier root = new CreatureModifier(goblin);



        System.out.println("Let's double goblin's attack");
        root.add(new DoubleAttackModifier(goblin));

        root.add(new NoBonusesModifier(goblin)); // Subverts the chain of responsibility

        System.out.println("Let's increase goblin's defense");
        root.add(new IncreaseDefenseModifier(goblin));

        root.handle(); // THIS is what traverses chain of responsibility
        System.out.println(goblin);
    }
}