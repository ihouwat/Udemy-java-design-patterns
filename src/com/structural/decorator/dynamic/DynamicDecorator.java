// Dynamic decorator

package structural.decorator.dynamic;

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

// Build decorators to extend Shapes above

class ColoredShape implements Shape
{
    private Shape shape; // which shape we are decorating
    private String color;

    public ColoredShape(Shape shape, String color) {
        this.shape = shape;
        this.color = color;
    }

    @Override
    public String info() {
        return shape.info() + " has the color " + color;
    }
}

class TransparentShape implements Shape
{
    private Shape shape; // shape we are decorating
    private int transparency;

    public TransparentShape(Shape shape, int transparency) {
        this.shape = shape;
        this.transparency = transparency;
    }

    @Override
    public String info() {
        return shape.info()+ " has "
            + transparency + "% transparency";
    }
}

class DynamicDecoratorDemo
{
    public static void main(String[] args)
    {
        // A regular circle
        Circle circle = new Circle(10);
        System.out.println(circle.info());

        // A colored square
        ColoredShape blueSquare = new ColoredShape(
                new Square(20), "blue"
        );
        System.out.println(blueSquare.info());

        // A transparent, colored circle
        TransparentShape myCircle = new TransparentShape(
                new ColoredShape(
                        new Circle(5), "green"
                ), 50
        );
//        myCircle.resize() - does not work as TransparentShape does not inherit
        System.out.println(myCircle.info());
    }
}