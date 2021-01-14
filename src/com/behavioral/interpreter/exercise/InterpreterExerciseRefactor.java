// This version of the exercise only works for single-digit numbers

package behavioral.interpreter.exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


// Object to sift integers from operators
class Store
{
    protected boolean firstPass;
    protected Integer integer;
    protected String operator;
    Type type = Type.NOTHING;

    enum Type {
        ADDITION,
        SUBTRACTION,
        NOTHING
    }

}

class ExpressionProcessorRefactored {
    public Map<Character, Integer> variables = new HashMap<>();

    // Parse integer or variable stored in the Hash map
    public Integer tryParse(String text)
    {
        try{
            if(!Character.isDigit(text.charAt(0))
                && variables.containsKey(text.charAt(0)))
                return variables.get(text.charAt(0));
            else return Integer.parseInt(text);
        }
        catch (NumberFormatException e) {return null;}
    }

    public int calculate(String expression)
    {
        String [] tokens = expression.split(""); // collect expressions in array
        Store store = new Store();
        int result = 0; // Final result

        // Check for consecutive single-letter variables
        Pattern p = Pattern.compile("([a-z])([a-z])");
        Matcher m = p.matcher(expression);
        if(m.find()) return 0;

        for (int i = 0; i < tokens.length; ++i)
        {
            if (tokens[i].matches("[+-]")) // If an operator
            {
                store.operator = tokens[i];
                store.type = tokens[i].equals("+")
                        ? Store.Type.ADDITION
                        : Store.Type.SUBTRACTION;
            }
            else if(tokens[i].matches("[0-9a-z]")) // If an alphanumeric character
            {
                Integer num = tryParse(tokens[i]);
                if(num == null) return 0; // if single-letter variable not in hash map, end method
                // First case
                if(store.firstPass == false)
                {
                    result += num;
                    store.firstPass = true;
                    continue;
                }
                else if(store.integer == null) store.integer = num;
            }

            if(store.integer != null && store.type != Store.Type.NOTHING ) // Make calculation
            {
                switch (store.type)
                {
                    case ADDITION:
                        result += store.integer;
                        break;
                    case SUBTRACTION:
                        result -= store.integer;
                        break;
                }
                store.integer = null; // reset node number
                store.type = Store.Type.NOTHING; // reset node type
            }
        }
        return result;
    }
}

class ExerciseDemo
{
    public static void main(String[] args) {
        ExpressionProcessorRefactored ep = new ExpressionProcessorRefactored();
        ep.variables.put('x', 5);

        System.out.println(ep.calculate("5+3+xx"));
        System.out.println(ep.calculate("+1"));
        System.out.println(ep.calculate("1+2"));
        System.out.println(ep.calculate("1+x"));
        System.out.println(ep.calculate("1+xy"));
        System.out.println(ep.calculate("y"));
    }
}
