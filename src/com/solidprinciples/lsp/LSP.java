// LSP

package solidprinciples.lsp;

import org.w3c.dom.css.Rect;

class Rectangle {
    protected int width, height;

    public Rectangle() {
    }

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getArea() { return width*height; }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }

    // Detecting a square
    public boolean isSquare() {
        return width == height;
    }
}


class Square extends Rectangle {
    public Square() {
    }

    public Square(int size) {
        width = height = size;
    }

    // LSP violated
    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        super.setHeight(width);
    }

    @Override
    public void setHeight(int height) {
        super.setWidth(height);
        super.setHeight(height);
    }
}

// Factory design pattern
class RectangleFactory {
    public static Rectangle newRectangle(int width, int height) {
        return new Rectangle(width, height);
    }

    public static Rectangle newSquare(int side){
        return new Rectangle(side, side);
    }
}

class Demo {
    static void useIt(Rectangle r) {
        int width = r.getWidth();
        r.setHeight(10); // bad setter because it makes sense for a rect but not square
        // area = width * 10
        System.out.println(
            "Expected area of " + (width * 10) +
                ", got " + r.getArea()
        );
    }

    public static void main(String[] args) {
        Rectangle rc = new Rectangle(2,3);
        useIt(rc);

        Rectangle sq = new Square();
        sq.setWidth(5);
        useIt(sq); // Expected area of 50, got 100
    }
}