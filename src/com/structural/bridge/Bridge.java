// Bridge

package structural.bridge;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

// Shape -> Circle, Square
// Rendering -> Vector, Raster
// Direct solution: VectorCircleRenderer, VectorSquareRenderer,RasterCircleRenderer....

interface Renderer
{
    void renderCircle(float radius);
}

class VectorRenderer implements Renderer
{
    @Override
    public void renderCircle(float radius) {
        System.out.println("Drawing a circle of radius "
        + radius);
    }
}

class RasterRenderer implements Renderer
{
    @Override
    public void renderCircle(float radius) {
        System.out.println("Drawing pixels for a circle "
        + "of radius " + radius);
    }
}

// Bridge here
abstract class Shape
{
    protected Renderer renderer;

    public Shape(Renderer renderer) {
        this.renderer = renderer;
    }

    public abstract void draw();
    public abstract void resize(float factor);
}

class Circle extends Shape
{
    public float radius;

    // Using a DI framework, you have a single point to determine what type
    // of renderer you want
    @Inject
    public Circle(Renderer renderer) {
        super(renderer);
    }

    // Circle is dependent upon the renderer
    public Circle(Renderer renderer, float radius) {
        super(renderer);
        this.radius = radius;
    }

    @Override
    public void draw()
    {
        renderer.renderCircle(radius);
    }

    @Override
    public void resize(float factor)
    {
        radius *= factor;
    }
}

class ShapeModule extends AbstractModule
{
    @Override
    protected void configure()
    {
        // When someone requests a Renderer to be injected
        // we make a new instance of VectorRenderer
        bind(Renderer.class).to(VectorRenderer.class);
    }
}

class BridgeDemo
{
    public static void main(String[] args) {
        // Injecting dependencies by hand
//        RasterRenderer raster = new RasterRenderer();
//        VectorRenderer vector = new VectorRenderer();
//        Circle circle = new Circle(vector, 5);
//        circle.draw();
//        circle.resize(2);
//        circle.draw();

        // Using google guice
        Injector injector = Guice.createInjector(new ShapeModule());
        Circle instance = injector.getInstance(Circle.class);
        instance.radius = 3;
        instance.draw();
        instance.resize(2);
        instance.draw();
    }
}