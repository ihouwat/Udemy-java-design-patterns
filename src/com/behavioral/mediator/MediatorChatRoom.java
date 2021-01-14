/*
Mediator pattern - classic illustration of this pattern
Key idea is that every person has a reference to the chat room (the mediator),
but at no point does one person have a reference to another person.
 */

package behavioral.mediator;

import java.util.ArrayList;
import java.util.List;

// participant in chat room
class Person
{
    public String name;
    public ChatRoom room; // reference to the room
    private List<String> chatLog = new ArrayList<>();

    public Person(String name) {
        this.name = name;
    }

    public void receive(String sender, String message)
    {
        String s = sender + ": '" + message + "'";
        System.out.println("[" + name + "'s chat session] " + s);
        chatLog.add(s);
    }

    public void send(String message)
    {
        room.broadcast(name, message);
    }

    public void privateMessage(String who, String message)
    {
        room.message(name, who, message);
    }
}

// Mediator
class ChatRoom {
    private List<Person> people = new ArrayList<>();

    public void join(Person p)
    {
        String joinMsg = p.name + " joins the room";
        broadcast("room", joinMsg);

        p.room = this; // adds reference to the person
        people.add(p);
    }

    public void broadcast(String source, String message)
    {
        for(Person person : people)
            if(!person.name.equals(source)) // ensures you don't send message to sender
                person.receive(source, message);
    }

    public void message(String source, String destination,
                        String message)
    {
        people.stream()
            .filter(p -> p.name.equals(destination))
            .findFirst()
            .ifPresent(person -> person.receive(source, message));
    }
}

class ChatRoomDemo
{
    public static void main(String[] args) {
        final ChatRoom room = new ChatRoom();

        Person john = new Person("John");
        Person jane = new Person("Jane");

        room.join(john);
        room.join(jane);

        john.send("hi room");
        jane.send("oh, hey john");

        Person simon = new Person("Simon");
        room.join(simon);
        simon.send("hi everyone!");

        jane.privateMessage("Simon",
            "glad you could join us!");
    }
}