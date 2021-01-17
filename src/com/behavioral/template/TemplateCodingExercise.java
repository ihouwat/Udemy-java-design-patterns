/*
Exercise for template pattern section:
Imagine a typical collectible card game which has cards representing creatures.
Each creature has two values: Attack and Health. Creatures can fight each other, dealing their Attack damage,
thereby reducing their opponent's health.

The class CardGame implements the logic for two creatures fighting one another. However, the exact mechanics of
how damage is dealt is different:

• TemporaryCardDamageGame: In some games (e.g., Magic:the Gathering), unless the creature has been killed,
its health returns to the original value at the end of combat.
• PermanentCardDamageGame: In other games (e.g.,Hearthstone), health damage persists.

You are asked to implement classes TemporaryCardDamageGame and PermanentCardDamageGame that would
allow us to simulate combat between creatures.

Some examples:

• With temporary damage, creatures 1/2 and 1/3 can never kill one another.
With permanent damage, second creature will win after 2 rounds of combat.
• With either temporary or permanent damage, two 2/2 creatures kill one another.
 */

package behavioral.template;

class Creature
{
    public int attack, health;

    public Creature(int attack, int health)
    {
        this.attack = attack;
        this.health = health;
    }
}

abstract class CardGame
{
    public Creature [] creatures;

    public CardGame(Creature[] creatures)
    {
        this.creatures = creatures;
    }

    // returns -1 if no clear winner (both alive or both dead)
    public int combat(int creature1, int creature2)
    {
        Creature first = creatures[creature1];
        Creature second = creatures[creature2];
        hit(first, second);
        hit(second, first);
        // Determine who won
        if(first.health == 0 && second.health > 0) return creature2;
        else if (second.health == 0 && first.health > 0) return creature1;
        else return -1;
    }

    // attacker hits other creature
    protected abstract void hit(Creature attacker, Creature other);
}

class TemporaryCardDamageGame extends CardGame
{
    public TemporaryCardDamageGame(Creature[] creatures)
    {
        super(creatures);
    }

    @Override
    protected void hit(Creature attacker, Creature other)
    {
        int damage = other.health - attacker.attack;
        if (damage <= 0) other.health = 0;
    }
}

class PermanentCardDamageGame extends CardGame
{
    public PermanentCardDamageGame(Creature[] creatures)
    {
        super(creatures);
    }

    @Override
    protected void hit(Creature attacker, Creature other)
    {
        other.health -= attacker.attack;
    }
}

class TemplateExerciseDemo
{
    public static void main(String[] args)
    {
        // Temp damage = Impasse
//        Creature c1 = new Creature(1, 2);
//        Creature c2 = new Creature(1, 2);
//        TemporaryCardDamageGame game = new TemporaryCardDamageGame(new Creature[]{c1, c2});
//        System.out.println(game.combat(0, 1)); // -1
//
        // Temporary damage - creature 2 wins
//        Creature c1 = new Creature(1, 1);
//        Creature c2 = new Creature(2, 2);
//        TemporaryCardDamageGame game = new TemporaryCardDamageGame(new Creature[]{c1, c2});
//        System.out.println(game.combat(0,1)); // 1

        // Permanent damage - both die
//        Creature c1 = new Creature(2, 2);
//        Creature c2 = new Creature(2, 2);
//        TemporaryCardDamageGame game = new TemporaryCardDamageGame(new Creature[]{c1, c2});
//        System.out.println(game.combat(0,1)); // -1
//
//        // Permanent damage - creature two wins
        Creature c1 = new Creature(1, 2);
        Creature c2 = new Creature(1, 3);
        CardGame game = new PermanentCardDamageGame(new Creature[]{c1, c2});

        System.out.println(game.combat(0,1));
        System.out.println(game.combat(0,1));

    }
}