// State pattern: handmade implementation using example of traditional phone

package behavioral.state.handmade;

import org.javatuples.Pair;

import java.io.*;
import java.text.*;
import java.util.*;

enum State
{
    OFF_HOOK, // starting state
    ON_HOOK, // terminal state
    CONNECTING,
    CONNECTED,
    ON_HOLD
}

enum Trigger
{
    CALL_DIALED,
    HUNG_UP,
    CALL_CONNECTED,
    PLACED_ON_HOLD,
    TAKEN_OFF_HOLD,
    LEFT_MESSAGE,
    STOP_USING_PHONE
}

class HandmadeStateDemo
{
    // State machine here
    private static Map<State, List<Pair<Trigger, State>>>
        rules = new HashMap<>();

    static
    {
        rules.put(State.OFF_HOOK, List.of (
            new Pair<>(Trigger.CALL_DIALED, State.CONNECTED),
            new Pair<>(Trigger.STOP_USING_PHONE, State.ON_HOOK)
        ));
        rules.put(State.CONNECTING, List.of (
            new Pair<>(Trigger.HUNG_UP, State.OFF_HOOK),
            new Pair<>(Trigger.CALL_CONNECTED, State.CONNECTED)
        ));
        rules.put(State.CONNECTED, List.of (
            new Pair<>(Trigger.LEFT_MESSAGE, State.OFF_HOOK),
            new Pair<>(Trigger.HUNG_UP, State.OFF_HOOK),
            new Pair<>(Trigger.PLACED_ON_HOLD, State.ON_HOLD)
        ));
        rules.put(State.ON_HOLD, List.of(
            new Pair<>(Trigger.TAKEN_OFF_HOLD, State.CONNECTED),
            new Pair<>(Trigger.HUNG_UP, State.OFF_HOOK)
        ));
    }

    private static State currentState = State.OFF_HOOK; // initial state
    private static State exitState = State.ON_HOOK; // final state

    public static void main(String[] args)
    {
        BufferedReader console = new BufferedReader(
                new InputStreamReader(System.in)
        );
        while(true)
        {
            System.out.println("The phone is currently " + currentState);
            System.out.println("Select a trigger:");
            for (var i = 0; i < rules.get(currentState).size(); ++i)
            {
                Trigger trigger = rules.get(currentState).get(i).getValue0();
                System.out.println("" + i + ". " + trigger);
            }
            boolean parseOk;
            int choice = 0;
            do{
                try {
                    System.out.println("Please enter your choice:");

                    choice = Integer.parseInt(console.readLine());
                    parseOk = choice >=0 &&
                        choice < rules.get(currentState).size();
                } catch (Exception e)
                {
                    parseOk = false;
                }
            } while (!parseOk);

            currentState = rules.get(currentState).get(choice).getValue1();

            if (currentState == exitState) break;
        }
        System.out.println("And we are done!");
    }
}
