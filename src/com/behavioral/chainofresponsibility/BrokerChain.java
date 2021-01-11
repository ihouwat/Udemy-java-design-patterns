/*
Broker Chain - patterns used:
chain of responsibility + observer + mediator + mementos
 */

package behavioral.chainofresponsibility;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

// Command Query Separation (observer pattern used here)
class Event<Args>
{
    private int index = 0; // key into the map to find a consumer
    private Map<Integer, Consumer<Args>>
        handlers = new HashMap<>();

    public int subscribe(Consumer<Args> handler)
    {
        int i = index;
        handlers.put(index++, handler);
        return i;
    }

    public void unsubscribe(int key)
    {
        handlers.remove(key);
    }

    public void fire(Args args)
    {
        for (Consumer<Args> handler : handlers.values())
        {
            handler.accept(args); // fire the event on every single consumer
        }
    }
}

class Query
{
    public String creatureName;
    enum Argument
    {
        ATTACK, DEFENSE
    }
    public Argument argument;
    public int result; // value of the handlers

    public Query(String creatureName,
                 Argument argument, int result) {
        this.creatureName = creatureName;
        this.argument = argument;
        this.result = result;
    }
}

class Game
{
    public Event<Query> queries = new Event<>(); // central location where any modifier can subscribe itself to queries on the creature and modify its attributes
}

class Character
{
    private Game game;
    public String name;
    public int baseAttack, baseDefense;

    public Character(Game game, String name,
                    int baseAttack, int baseDefense) {
        this.game = game;
        this.name = name;
        this.baseAttack = baseAttack;
        this.baseDefense = baseDefense;
    }

    int getAttack()
    {
        Query q = new Query(name, Query.Argument.ATTACK, baseAttack);
        game.queries.fire(q);
        return q.result;
    }

    int getDefense()
    {
        Query q = new Query(name, Query.Argument.DEFENSE, baseDefense);
        game.queries.fire(q);
        return q.result;
    }

    @Override
    public String toString() {
        return "Creature{" +
                "game=" + game +
                ", name='" + name + '\'' +
                ", attack=" + getAttack() +
                ", defense=" + getDefense() +
                '}';
    }
}

class CharacterModifier
{
    protected Game game;
    protected Character character;

    public CharacterModifier(Game game, Character character) {
        this.game = game;
        this.character = character;
    }
}

class IncreaseDefenseMod
    extends CharacterModifier
{
    public IncreaseDefenseMod(Game game, Character character) {
        super(game, character);

        game.queries.subscribe(q -> {
            if (q.creatureName.equals(character.name)
                    && q.argument == Query.Argument.DEFENSE)
            {
                q.result += 3;
            }
        });
    }
}

class DoubleAttackMod
    extends CharacterModifier
    implements AutoCloseable
{
    private final int token;

    public DoubleAttackMod(Game game, Character character) {
        super(game, character);

        // subscribe mediator (game) to all queries
        token = game.queries.subscribe(q -> {
            if (q.creatureName.equals(character.name)
                && q.argument == Query.Argument.ATTACK)
            {
                q.result *= 2;
            }
        });
    }

    @Override
    public void close() /*throws Exception */
    {
        game.queries.unsubscribe(token); // unsubscribe from handling any changes to the underlying character
    }
}

class BrokerChainDemo
{
    public static void main(String[] args) {
        Game game = new Game();
        Character goblin = new Character(game, "Strong Goblin", 2, 2);
        System.out.println(goblin);

        IncreaseDefenseMod icm = new IncreaseDefenseMod(game, goblin);

        DoubleAttackMod dam = new DoubleAttackMod(game, goblin);

        try(dam)
        {
            System.out.println("inside try block: " + goblin);
        }
        System.out.println("double attack mod goes back: " + goblin);
    }
}