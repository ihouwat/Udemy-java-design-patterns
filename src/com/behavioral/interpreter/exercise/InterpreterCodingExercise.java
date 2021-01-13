package behavioral.interpreter.exercise;

// Exercise for interpreter pattern section

import java.util.HashMap;
import java.util.Map;

class Tree
{
    Tree left;
    Tree right;
    Object value;
    ExpressionProcessor.Type type;

    public Tree() {
        this.left = null;
        this.right = null;
        this.value = null;
        this.type = type;
    }
}

class ExpressionProcessor
{
    public Map<Character, Integer> variables = new HashMap<>();
    int result;

    public enum Type
    {
        ADDITION,
        SUBTRACTION,
    }

    // Parse integer or variable stored in the Hash map
    public Integer tryParse(String text)
    {
        try{
            if(!Character.isDigit(text.charAt(0))
                    && variables.containsKey(text.charAt(0)))
                return variables.get(text.charAt(0));
            else return Integer.parseInt(text);
        }
        catch (NumberFormatException e)
        {
            return null;
        }
    }

    public int calculate(String expression)
    {
        Tree root = new Tree();
        String [] tokens = expression.split(""); // collect expressions in array

        // LEXING STAGE
        for (int i = 0; i < tokens.length; ++i)
        {
            Tree current = new Tree();
            // If two consecutive variables, return 0.
            try{
                if(!Character.isDigit(tokens[i].charAt(0))
                    && !Character.isDigit(tokens[i+1].charAt(0))
                    && !tokens[i].equals("+")
                    && !tokens[i].equals("-")) return 0;
                }
            catch (Exception e){}

            // parse numbers
            Integer ch = tryParse(tokens[i]) ; // check if it is a number

            // Concatenate consecutive integers
            Integer temp = 0;
            while(ch != null & temp != null && i < tokens.length-1)
            {
                for (int j = i+1; j < tokens.length; ++j)
                {
                    temp = tryParse(tokens[j]);
                    if (temp == null) break;
                    String s1 = Integer.toString(ch);
                    String s2 = Integer.toString(temp);

                    // Concatenate both strings
                    String s = s1 + s2;

                    // Convert the concatenated string
                    // to integer
                    ch = Integer.parseInt(s);
                    i++;
                }
            }

            // Add value to tree
            if(root.value == null) {
                current = root; // Create tree node
                current.value = ch;
            }
            else if (ch == null)
            {
                current.value = tokens[i];
                current.left = root;
                current.type = tokens[i].equals("+")
                        ? Type.ADDITION
                        : Type.SUBTRACTION;
                current.right = new Tree();
                root = current;
            }

            else root.right.value = ch;
        }
        // PARSING STAGE - calculate result
        return result = traverse(root);
    }

    // Traverse tree recursively till you hit a leaf
    int traverse (Tree tree)
    {
        int result = 0;
        if (tree.type == null) result = (Integer) tree.value;
        else{
            switch (tree.type) {
                case ADDITION:
                    result = traverse(tree.left) + traverse(tree.right);
                    break;
                case SUBTRACTION:
                    result = traverse(tree.left) - traverse(tree.right);
                    break;
            }
        }
        return result;
    };
}


class InterpreterExerciseDemo
{
    public static void main(String[] args) {
        ExpressionProcessor ep = new ExpressionProcessor();
        ep.variables.put('x', 5);

        System.out.println(ep.calculate("5+3+xx"));
        System.out.println(ep.calculate("1"));
        System.out.println(ep.calculate("1+2"));
        System.out.println(ep.calculate("1+x"));
        System.out.println(ep.calculate("1+xy"));
    }
}
