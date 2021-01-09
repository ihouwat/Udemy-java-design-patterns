/*
Exercise for adapter section: given a Rectangle interface and an extension method on it,
define a SquareToRectangleAdapter that adopts Square to the Rectangle interface
 */

package structural.adapter;

class Square
{
    public int side;

    public Square(int side)
    {
        this.side = side;
    }
}

interface Rectangle
{
    int getWidth();
    int getHeight();

    default int getArea()
    {
        return getWidth() * getHeight();
    }
}

class SquareToRectangleAdapter implements Rectangle
{
    Square square;
    public SquareToRectangleAdapter(Square square) // reference to square we are adapting
    {
        this.square =  square;
    }

    // Adapt square to rectangle interface
    @Override
    public int getWidth() {
        return square.side;
    }

    @Override
    public int getHeight() {
        return square.side;
    }

    @Override
    public int getArea() {
        return square.side * square.side;
    }
}