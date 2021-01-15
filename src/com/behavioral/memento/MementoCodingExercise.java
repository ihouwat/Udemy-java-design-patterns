/*
Exercise for memento section
A TokenMachine is in charge of keeping tokens. Each Token is a reference type with a single numerical value.
The machine supports adding tokens and, when it does, it returns a memento representing the state of that
system at that given time.

You are asked to fill in the gaps and implement the Memento design pattern for this scenario.
 Pay close attention to the situation where a token is fed in as a reference and its value is subsequently
 changed on that reference - you still need to return the correct system snapshot!
 */

package behavioral.memento;

import java.util.ArrayList;
import java.util.List;

class Token
{
    public int value;

    public Token(int value)
    {
        this.value = value;
    }
}

class MementoClass
{
    public List<Token> tokens = new ArrayList<>();

    @Override
    public String toString() {
        String result = "Memento list of tokens includes: ";
        for (Token token : tokens) {
            result += token.value + ", ";
        }
        return result;
    }
}

class TokenMachine
{
    public List<Token> tokens = new ArrayList<>();

    public MementoClass addToken(int value)
    {
        Token t = new Token(value);
        return addToken(t);
    }

    public MementoClass addToken(Token token)
    {
        tokens.add(token); // add tokens to the token machine
        MementoClass memento = new MementoClass();  // create a new store on each method call (memory intensive)
        deepCopy(memento); // make a deep copy (Prototype pattern!)
        return memento;
    }

    void deepCopy(MementoClass memento)
    {
        tokens.forEach(token -> memento.tokens.add(new Token(token.value)));
    }

    public void revert(MementoClass m)
    {
        tokens = m.tokens;
    }

    @Override
    public String toString() {
        String result = "Token machine  list includes: ";
        for (Token token : tokens) {
            result += token.value + ", ";
        }
        return result;
    }
}

class MementoExerciseDemo
{
    public static void main(String[] args) {
        // One token test
//        TokenMachine tm = new TokenMachine();
//        MementoClass m = tm.addToken(123);
//        tm.addToken(456);
//        tm.revert(m);


        // Two token test
//        TokenMachine tm = new TokenMachine();
//        tm.addToken(1);
//        MementoClass m = tm.addToken(2);
//        tm.addToken(3);
//        tm.revert(m);

        TokenMachine tm = new TokenMachine();
        System.out.println("Made a token with value 111 and kept a reference");
        Token token = new Token(111);
        System.out.println("Added this token to the list");
        tm.addToken(token);
        MementoClass m = tm.addToken(222);
        System.out.println("Changed this token's value to 333 :)");
        token.value = 333;
        tm.revert(m);
        System.out.println(m);
        System.out.println(tm);
    }
}