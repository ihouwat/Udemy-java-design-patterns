// Factory Method

package creational.factories;

enum CoordinateSystem {
    CARTESIAN,
    POLAR
}

class Point {
    private double x, y;

    // Simple constructor
    private Point(double x, double y) { // making a constructor private forces user to use factory methods
        this.x = x;
        this.y = y;
    }

//    // Illegal constructor - already have a constructor with two double values
//    public Point (double rho, double theta) {
//        x = rho * Math.cos(theta);
//        y = rho * Math.sin(theta);
//    }

    /* Alternative constructor, using the enum above, to manage various cases
    Constructor is ugly. You need documentation, as below.
     */
    /**
     *
     * @param a is x if cartesian or rho if polar.
     * @param b
     * @param cs
     */
    private Point(double a, double b, CoordinateSystem cs) {
        switch (cs){
            case CARTESIAN:
                this.x = x;
                this.y = y;
                break;
            case POLAR:
                x = a * Math.cos(b);
                y = a * Math.sin(b);
                break;
        }
    }

    // Factory methods
    public static Point newCartesianPoint(double x, double y) {
        return new Point(x,y);
    }

    public static Point newPolarPoint(double rho, double theta) {
        return new Point(rho*Math.cos(theta),
            rho*Math.sin(theta));
    }
}

class FactoryMethodDemo {
    public static void main(String[] args) {
        Point point = Point.newPolarPoint(2, 3); // using a factory method
    }
}
