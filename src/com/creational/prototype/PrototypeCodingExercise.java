/*
Prototype section exercise: Given the class definitions, implement Line.deepCopy() to perform a deep
copy of the current Line object

 */

package creational.prototype;

class Point
{
    public int x, y;

    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

class Line
{
    public Point start, end;

    public Line(Point start, Point end)
    {
        this.start = start;
        this.end = end;
    }

    // copy method: create a new line with new points and return the new line
    public Line deepCopy ()
    {
        // First construct the new Line object and then clone it
        Line other = new Line(new Point (start.x, start.y), new Point(end.x, end.y));
        return other;
    }

    @Override
    public String toString() {
        return "Line{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}

class ExerciseDemo {
    public static void main(String[] args) {
        Line l1 =
            new Line (
                new Point(100,100),
                new Point(200, 200));
        Line l2 = l1.deepCopy();
        System.out.println(l1);

        System.out.println("l2 is a deep copy of l1:");
        System.out.println(l2);

        System.out.println("Change x coordinate on l2:");
        l2.start.x = 1;
        System.out.println(l1);
        System.out.println(l2);
    }
}
