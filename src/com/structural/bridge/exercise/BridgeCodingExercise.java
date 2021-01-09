/*
Exercise from Bridge section: given an example which results in Cartesian-product
duplication, refactor the hierarchy, giving the base class Shape a constructor that takes
an interface called Renderer defined as
interface Renderer
{
    public String whatToRenderAs();
}
 */

package structural.bridge.exercise;

interface Renderer
{
    public String whatToRenderAs(String name);
}

abstract class Shape implements Renderer
{
    protected Renderer renderer;
    public abstract String getName();

    public Shape(Renderer renderer) {
        this.renderer = renderer;
    }
}

class Triangle extends Shape
{
    public Triangle(Renderer renderer) {
        super(renderer);
    }

    @Override
    public String getName()
    {
        return "Triangle";
    }

    @Override
    public String whatToRenderAs(String name) {
        return renderer.whatToRenderAs(name);
    }

    @Override
    public String toString() {
        return whatToRenderAs(this.getName());
    }
}

class Square extends Shape
{
    public Square(Renderer renderer) {
        super(renderer);
    }

    @Override
    public String getName()
    {
        return "Square";
    }

    @Override
    public String whatToRenderAs(String name) {
        return renderer.whatToRenderAs(name);
    }

    @Override
    public String toString() {
        return whatToRenderAs(this.getName());
    }
}

class VectorRenderer implements Renderer
{
    @Override
    public String whatToRenderAs(String name) {
        return String.format("Drawing %s as lines", name);
    }
}

class RasterRenderer implements Renderer
{
    @Override
    public String whatToRenderAs(String name) {
        return String.format("Drawing %s as pixels", name);
    }
}

//class VectorSquare extends Square
//{
//    @Override
//    public String toString()
//    {
//        return String.format("Drawing %s as lines", getName());
//    }
//}
//
//class RasterSquare extends Square
//{
//    @Override
//    public String toString()
//    {
//        return String.format("Drawing %s as pixels", getName());
//    }
//}

// imagine VectorTriangle and RasterTriangle are here too

class BridgeExerciseDemo
{
    public static void main(String[] args) {
        Triangle triangle = new Triangle(new VectorRenderer());
        System.out.println(triangle);

    }
}