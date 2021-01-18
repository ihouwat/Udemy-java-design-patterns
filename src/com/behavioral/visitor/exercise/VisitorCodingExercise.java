/*
Exercise for visitor section:
You are asked to implement a double-dispatch visitor called ExpressionPrinter for printing different mathematical
expressions. The range of expressions covers addition and multiplication - please put round brackets after
around addition but not around multiplication! Also, please avoid any blank spaces in output. Example:
• Input:AdditionExpression(Value(2), Value(3))
• Output:(2+3)
 */

package behavioral.visitor.exercise;

abstract class ExpressionVisitor
{
    abstract void visit(Value value);
    abstract void visit(AdditionExpression ae);
    abstract void visit(MultiplicationExpression me);
}

abstract class Expression
{
    abstract void accept(ExpressionVisitor ev);
}

class Value extends Expression
{
    public int value;

    public Value(int value)
    {
        this.value = value;
    }

    @Override
    void accept(ExpressionVisitor ev)
    {
        ev.visit(this);
    }
}

class AdditionExpression extends Expression
{
    public Expression lhs, rhs;

    public AdditionExpression(Expression lhs, Expression rhs)
    {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    void accept(ExpressionVisitor ev)
    {
        ev.visit(this);
    }
}

class MultiplicationExpression extends Expression
{
    public Expression lhs, rhs;

    public MultiplicationExpression(Expression lhs, Expression rhs)
    {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    void accept(ExpressionVisitor ev)
    {
        ev.visit(this);
    }
}

class ExpressionPrinter extends ExpressionVisitor
{
    private StringBuilder sb = new StringBuilder();

    @Override
    void visit(Value value)
    {
        sb.append(value.value);
    }

    @Override
    void visit(AdditionExpression ae)
    {
        sb.append("(");
        ae.lhs.accept(this);
        sb.append("+");
        ae.rhs.accept(this);
        sb.append(")");
    }

    @Override
    void visit(MultiplicationExpression me)
    {
        me.lhs.accept(this);
        sb.append("*");
        me.rhs.accept(this);
    }

    @Override
    public String toString()
    {
        return sb.toString();
    }
}

class VisitorExerciseDemo
{
    public static void main(String[] args)
    {
        AdditionExpression simple = new AdditionExpression(new Value(2), new Value(3));
        ExpressionPrinter ep = new ExpressionPrinter();
        ep.visit(simple);
        System.out.println(ep); // (2+3)

        MultiplicationExpression expr = new MultiplicationExpression(
            new AdditionExpression(new Value(2), new Value(3)),
            new Value(4)
        );
        ExpressionPrinter ep2 = new ExpressionPrinter();

        ep2.visit(expr);
        System.out.println(ep2); // (2+3)*4
    }
}
