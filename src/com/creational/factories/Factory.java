// Factory Class

package creational.factories;

// called PointClass to distinguish from Point class in package
class PointClass {
    private double x, y;

    // Simple constructor
    private PointClass(double x, double y) { // making a constructor private forces user to use factory methods
        this.x = x;
        this.y = y;
    }

    public static class Factory {
        public static PointClass newCartesianPoint(double x, double y) {
            return new PointClass(x,y);
        }

        public static PointClass newPolarPoint(double rho, double theta) {
            return new PointClass(rho*Math.cos(theta),
                    rho*Math.sin(theta));
        }
    }
}

class FactoryDemo {
    public static void main(String[] args) {
        PointClass point = PointClass.Factory.newPolarPoint(2, 3); // using a factory method
    }
}