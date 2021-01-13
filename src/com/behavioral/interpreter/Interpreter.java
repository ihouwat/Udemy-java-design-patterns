// Interpreter pattern

package behavioral.interpreter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

interface Element
{
    int eval();
}

class Integer implements Element
{
    private int value;

    public Integer(int value) {
        this.value = value;
    }

    @Override
    public int eval() {
        return value;
    }
}

// Will use binary tree approach
class BinaryOperation implements Element
{
    public enum Type
    {
        ADDITION,
        SUBTRACTION
    }

    public Type type;
    public Element left, right; // Binary tree approach

    @Override
    public int eval() {
        switch (type) {
            case ADDITION:
                return left.eval() + right.eval();
            case SUBTRACTION:
                return left.eval() - right.eval();
            default:
                return 0;
        }
    }
}


class Token
{
    public enum Type
    {
        INTEGER,
        PLUS,
        MINUS,
        LPAREN,
        RPAREN
    }

    public Type type; // the type of token we are evaluating
    public String text; // the textual value of the token

    public Token(Type type, String text) {
        this.type = type;
        this.text = text;
    }

    @Override
    public String toString() {
        return "`" + text + "`";
    }
}

class InterpreterDemo
{
    // Lexing operation
    static List<Token> lex(String input)
    {
        ArrayList<Token> result = new ArrayList<>();
        for (int i = 0; i < input.length(); ++i)
        {
            switch (input.charAt(i))
            {
                case '+':
                    result.add(new Token(Token.Type.PLUS, "+"));
                    break;
                case '-':
                    result.add(new Token(Token.Type.MINUS, "-"));
                    break;
                case '(':
                    result.add(new Token(Token.Type.LPAREN, "("));
                    break;
                case ')':
                    result.add(new Token(Token.Type.RPAREN, ")"));
                    break;
                default:
                    StringBuilder sb = new StringBuilder("" + input.charAt(i));
                    for (int j = i+1; j < input.length(); ++j)
                    {
                        if (Character.isDigit(input.charAt(j)))
                        {
                            sb.append(input.charAt(j));
                            i++;
                        }
                        else {
                            result.add(new Token(
                                Token.Type.INTEGER, sb.toString()
                            ));
                            break;
                        }
                    }
                break;
            }
        }
        return result;
    }

    // Parsing operation
    static Element parse(List<Token> tokens)
    {
        BinaryOperation result = new BinaryOperation();
        boolean haveLHS = false; // 'have left hand side'

        for (int i = 0; i < tokens.size(); ++i)
        {
            Token token = tokens.get(i);

            switch (token.type) {
                case INTEGER:
                    Integer integer = new Integer(java.lang.Integer.parseInt(token.text));
                    if(!haveLHS)
                    {
                        result.left = integer;
                        haveLHS = true;
                    } else result.right = integer;
                break;
                case PLUS:
                    result.type = BinaryOperation.Type.ADDITION;
                    break;
                case MINUS:
                    result.type = BinaryOperation.Type.SUBTRACTION;
                    break;
                case LPAREN:
                    int j = i; // points to location of rparen
                    for (; j < tokens.size(); ++j)
                        if(tokens.get(j).type == Token.Type.RPAREN)
                            break;
                    List<Token> subexpression = tokens.stream()
                            .skip(i + 1)
                            .limit(j - i - 1)
                            .collect(Collectors.toList());
                    Element element = parse(subexpression); // recursively parse elements
                    if (!haveLHS)
                    {
                        result.left = element;
                        haveLHS = true;
                    } else result.right = element;
                    i = j;
                    break;
            }
        }
        return result;
    }


    public static void main(String[] args) {
        String input = "(13+4)-(12+1)";
        List<Token> tokens = lex(input);

        // Testing out lexing
        System.out.println(tokens.stream()
        .map(t -> t.toString())
        .collect(Collectors.joining("\t")));

        Element parsed = parse(tokens);
        System.out.println(input + " = " + parsed.eval());
    }
}