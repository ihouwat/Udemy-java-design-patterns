// Visitor pattern - reflective visitor; example will PRINT a tree of expressions (ie: 1 + 2)


package behavioral.visitor.reflective;

abstract class Expression
{
}

class DoubleExpression extends Expression
{
    public double value;

    public DoubleExpression(double value) {
        this.value = value;
    }

}

class AdditionExpression extends Expression
{
    public Expression left, right;

    public AdditionExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
}

class ExpressionPrinter
{
    public static void print(Expression e, StringBuilder sb)
    {
        // Main issue is that code is slow due to checking type
        if (e.getClass() == DoubleExpression.class)
        {
            sb.append(((DoubleExpression)e).value);
        } else if(e.getClass() == AdditionExpression.class)
        {
            AdditionExpression ae = (AdditionExpression) e;
            sb.append("(");
            print(ae.left, sb);
            sb.append("+");
            print(ae.right, sb);
            sb.append(")");
        }
    }
}

class ReflectiveDemo
{
    public static void main(String[] args)
    {
        // 1 + (2+3)
        AdditionExpression e = new AdditionExpression(
            new DoubleExpression(1),
            new AdditionExpression(
                new DoubleExpression(2),
                    new DoubleExpression(3)
            )
        );
        StringBuilder sb = new StringBuilder();
        ExpressionPrinter.print(e, sb);
        System.out.println(sb);
    }
}

