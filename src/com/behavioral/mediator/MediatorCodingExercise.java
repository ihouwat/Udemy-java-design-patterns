/*
Mediator section exercise
Our system has any number of instances of Participant classes. Each Participant has a value integer, initially zero.

A participant can say() a particular value, which is broadcast to all other participants.
At this point in time, every other participant is obliged to increase their value by the value being broadcast.

Example:

• Two participants start with values 0 and 0 respectively
• Participant 1 broadcasts the value 3. We now have Participant 1 value = 0, Participant 2 value = 3
• Participant 2 broadcasts the value 2. We now have Participant 1 value = 2, Participant 2 value = 3
 */

package behavioral.mediator;

import java.util.ArrayList;
import java.util.List;

class Participant
{
    public int value;
    public Mediator mediator;

    // Injecting mediator in component
    public Participant(Mediator mediator)
    {
        this.mediator = mediator;
        mediator.join(this); // join room in this constructor
    }

    public void say(int n)
    {
        mediator.message(this, n);
    }
}

class Mediator
{
    private List<Participant> people = new ArrayList<>();


    public void message(Participant source, int n)
    {
        for(Participant person : people)
            if(!person.equals(source))
            {
                person.value = n;
            }
    }

    public void join(Participant p)
    {
        people.add(p);
        System.out.println(people.size() + " people in the list");
    }
}

class MediatorExerciseDemo
{
    public static void main(String[] args) {
        Mediator mediator = new Mediator();
        Participant p1 = new Participant(mediator);
        Participant p2 = new Participant(mediator);
        Participant p3 = new Participant(mediator);

        p1.say(2);
    }
}