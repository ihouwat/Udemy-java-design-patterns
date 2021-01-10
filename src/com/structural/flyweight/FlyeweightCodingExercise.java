/*
Exercise for flyweight section
Given a class Sentence which can take a string such as "hello world"
provide a method getWord() that returns a word token which is used to capitalize a word in a sentence
 */

package structural.flyweight;

import java.util.*;

class Sentence
{
    private String plainText;
    private String [] words;
    private Map<Integer, WordToken> tokens = new HashMap<>();

    public Sentence(String plainText)
    {
        // Create a list of words
        words = plainText.split(" ");
    }

    public WordToken getWord(int index)
    {
        WordToken wt = new WordToken(); // contains one variable, called capitalize
        tokens.put(index, wt);
        return tokens.get(index); // return token at that index
    }

    @Override
    public String toString()
    {
       ArrayList<String> ws = new ArrayList<>();
       for(int i = 0; i < words.length; i++)
       {
           String word = words[i];
           // a bit overkill here. we are getting the index in the hash map and checking if it contains the variable capitalize
           if(tokens.containsKey(i) && tokens.get(i).capitalize)
           {
               word = word.toUpperCase();
           }
           ws.add(word);
       }
        return String.join(" ", ws);
    }

    class WordToken
    {
        public boolean capitalize;
    }
}

class FlyweightExerciseDemo
{
    public static void main(String[] args)
    {
        Sentence sentence = new Sentence("hello world");
        sentence.getWord(1).capitalize = true;
        System.out.println(sentence);
    }
}