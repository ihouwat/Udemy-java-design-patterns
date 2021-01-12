/*
Exercise for chain of responsibility section
Given a game scenario with Goblin and GoblinKing classes. Implement these rules:
- Goblin has base attack 1, base defense 1, Goblin King is 3/3
- When GoblinKing is in play, every other Goblin gets +1 Attack
- Goblins get +1 Defense for every other Goblin in play (a GoblinKing is Goblin!)
 */

package behavioral.chainofresponsibility.exercise;

import java.util.ArrayList;
import java.util.List;

abstract class Creature
{
    public abstract int getAttack();
    public abstract int getDefense();
    public abstract String getName();
    public String name;
}

class Goblin extends Creature
{
    protected int baseDefense, baseAttack;
    protected String name;
    private Game game;

    public Goblin(Game game)
    {
        this.game = game;
        this.name = "Goblin";
        this.baseDefense = 1;
        this.baseAttack = 1;
    }

    @Override
    public int getAttack() {
        int attack = baseAttack;
        for (Creature creature : game.creatures)
            if (creature.getName() == "GoblinKing") attack++;
        return attack;
    }

    @Override
    public int getDefense() {
        int defense = baseDefense;
        int numOfPlayers = game.creatures.size();
        if (numOfPlayers == 1) return defense;
        else return numOfPlayers;
    }

    @Override
    public String getName(){
        return this.name;
    }

}

class GoblinKing extends Goblin
{
    public GoblinKing(Game game) {
        super(game);
        this.name = "GoblinKing";
        this.baseDefense = this.baseAttack = 3;
    }
}

enum Statistic
{
    ATTACK, DEFENSE
}

class Game
{
    public List<Creature> creatures = new ArrayList<>();
}

class CORExerciseDemo
{
    public static void main(String[] args) {
        Game game = new Game();
        Goblin goblin = new Goblin(game);
        Goblin goblin2 = new Goblin(game);
        Goblin goblin3 = new Goblin(game);
        GoblinKing goblinKing = new GoblinKing(game);

        game.creatures.add(goblin);
        game.creatures.add(goblin2);
        game.creatures.add(goblin3);
        game.creatures.add(goblinKing);

        System.out.println(goblin.getDefense());
        System.out.println(goblin.getAttack());
    }
}

/* Instructor solution

abstract class Creature
{
  protected Game game;
  protected int baseAttack, baseDefense;

  public Creature(Game game, int baseAttack, int baseDefense)
  {
    this.game = game;
    this.baseAttack = baseAttack;
    this.baseDefense = baseDefense;
  }

  public abstract int getAttack();
  public abstract int getDefense();
  public abstract void query(Object source, StatQuery sq);
}

class Goblin extends Creature
{
  protected Goblin(Game game, int baseAttack, int baseDefense)
  {
    super(game, baseAttack, baseDefense);
  }

  public Goblin(Game game)
  {
    super(game, 1, 1);
  }

  @Override
  public int getAttack()
  {
    StatQuery q = new StatQuery(Statistic.ATTACK);
    for (Creature c : game.creatures)
      c.query(this, q);
    return q.result;
  }

  @Override
  public int getDefense()
  {
    StatQuery q = new StatQuery(Statistic.DEFENSE);
    for (Creature c : game.creatures)
      c.query(this, q);
    return q.result;
  }

  @Override
  public void query(Object source, StatQuery sq)
  {
    if (source == this)
    {
      switch (sq.statistic)
      {
        case ATTACK:
          sq.result += baseAttack;
          break;
        case DEFENSE:
          sq.result += baseDefense;
          break;
      }
    }
    else if (sq.statistic == Statistic.DEFENSE)
    {
      sq.result++;
    }
  }
}

class GoblinKing extends Goblin
{
  public GoblinKing(Game game)
  {
    super(game, 3, 3);
  }

  @Override
  public void query(Object source, StatQuery sq)
  {
    if (source != this && sq.statistic == Statistic.ATTACK)
    {
      sq.result++; // every goblin gets +1 attack
    }
    else super.query(source, sq);
  }
}

enum Statistic
{
  ATTACK, DEFENSE
}

class StatQuery
{
  public Statistic statistic;
  public int result;

  public StatQuery(Statistic statistic)
  {
    this.statistic = statistic;
  }
}

class Game
{
  public List<Creature> creatures = new ArrayList<>();
}
 */