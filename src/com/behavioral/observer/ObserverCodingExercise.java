/*
Exercise for observable section:
Imagine a game where one or more rats can attack a player.
Each individual rat has an attack value of 1. However, rats attack as a swarm,
so each rat's attack value is equal to the total number of rats in play.

Given that a rat enters play through the constructor and leaves play (dies) via its close() method,
please implement the Game and Rat classes so that, at any point in the game,
the attack value of a rat is always consistent.
 */

package behavioral.observer;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


// Observer portion

// Will take in a 'property changed events'
class PropertyChangedEvents<Rat>
{
    public Rat source;
    public String propertyName;
    public Enum action;

    public PropertyChangedEvents(Rat source, String propertyName, Enum action) {
        this.source = source;
        this.propertyName = propertyName;
        this.action = action;
    }
}

interface ObserverItf<Rat>
{
    void handle(PropertyChangedEvents<Rat> args);
}

// Game is observer
class Game implements ObserverItf<Rat>
{
    private List<Rat> rats = new ArrayList<>(); // A mediator pattern here

    @Override
    public void handle(PropertyChangedEvents<Rat> args) {
        if (args.action == Rat.Action.JOINING)
        {
            rats.add(args.source); // add rat to game list
            System.out.println("A rat has JOINED the game");
        }
        else
        {
            rats.remove(args.source); // remove rat from game list
            System.out.println("A rat has LEFT the game");
        }
        for(Rat r : rats) // inform all rats of change
        {
            r.attack = rats.size();
        }
    }
}




// Observable portion


class ObservableClass<Rat>
{
    private List<ObserverItf<Rat>> observerList = new ArrayList<>();

    // API to subscribe observers to each observable, Rat, which generates the event
    // through the attack() below.
    public void subscribe(ObserverItf<Rat> observer)
    {
        observerList.add(observer);
    }

    public void unsubscribe(ObserverItf<Rat> observer)
    {
        observerList.remove(observer);
    }

    // Notify the game when a rat joins/leaves a game
    protected void propertyChanged(Rat source,
                                   String propertyName,
                                   Enum action)
    {
        for (ObserverItf<Rat> o : observerList)
        {
            o.handle(new PropertyChangedEvents<Rat>(
                source, propertyName, action
            ));
        }
    }

}


// Rat is observable
class Rat extends ObservableClass<Rat> implements Closeable
{
    private Game game;
    public int attack = 1;
    enum Action
    {
        JOINING, LEAVING
    }

    public Rat(Game game)
    {
        this.game = game;
        this.subscribe(game); // subscribe game observer to this Rat
        propertyChanged(this, "join", Action.JOINING);
    }

    @Override
    public void close() throws IOException
    {
        try{
            propertyChanged(this, "leave", Action.LEAVING);
            this.unsubscribe(game);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

class ObserverExerciseDemo
{
    public static void main(String[] args) throws IOException {
        Game game = new Game();
        Rat rat = new Rat(game);
        Rat rat2 = new Rat(game);
        Rat rat3 = new Rat(game);

        System.out.println(rat.attack); // 3
        rat3.close();
        System.out.println(rat.attack); // 2

    }
}
