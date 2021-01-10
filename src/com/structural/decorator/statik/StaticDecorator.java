// Static decorator

package structural.decorator.statik;

import java.util.function.Supplier;

interface Shape
{
    String info();
}

class Circle implements Shape
{
    private float radius;

    public Circle() {}

    public Circle(float radius) {
        this.radius = radius;
    }

    void resize(float factor)
    {
        radius *= factor;
    }

    @Override
    public String info() {
        return "A circle of radius " + radius;
    }
}

class Square implements Shape
{
    private float side;

    public Square() {}

    public Square(float side) {
        this.side = side;
    }

    @Override
    public String info() {
        return "A square ";
    }
}

// Build static decorators to extend Shapes above
class ColoredShape<T extends Shape> implements Shape
{
    private Shape shape;
    private String color;

    // Instead of passing type, we pass constructor to that type
    public ColoredShape(Supplier<? extends T> constructor,
                        String color)
    {
        shape = constructor.get(); // we are constructing the shape from the supplier we are given
        this.color = color;
    }

    public ColoredShape(Shape shape, String color) {
        this.shape = shape;
        this.color = color;
    }

    @Override
    public String info() {
        return shape.info() + " has the color " + color;
    }
}

class TransparentShape<T extends Shape>
    implements Shape
{
    private Shape shape;
    private int transparency;

    public TransparentShape(Supplier<? extends T> constructor,
                            int transparency)
    {
        shape = constructor.get();
        this.transparency = transparency;
    }

    @Override
    public String info() {
        return shape.info()+ " has "
                + transparency + "% transparency";
    }
}

class StaticDecoratorDemo
{
    public static void main(String[] args)
    {
        ColoredShape<Square> blueSquare =
            new ColoredShape<>(() -> new Square(20),"blue");
        System.out.println(blueSquare.info());

        TransparentShape<ColoredShape<Circle>> myCircle =
            new TransparentShape<>(() ->
                new ColoredShape<>(() -> new Circle(5), "green"), 50);
        System.out.println(myCircle.info());
//        myCircle.resize() - not possible since TransparentShape does not inherit from circle
    }
}
