package homework1;

public class QuadraticEquationCalculator {
  private final double ACCURACY = 0.00001;

  public double[] calculate(double a, double b, double c) {
    if (Math.abs(a) < ACCURACY
        || Double.isNaN(a) || Double.isNaN(b) || Double.isNaN(c)
        || Double.isInfinite(a) || Double.isInfinite(b) || Double.isInfinite(c)) {
      throw new IllegalArgumentException();
    }

    double d = b * b - 4 * a * c;
    if (d < -ACCURACY) {
      return new double[]{};
    } else if (d > ACCURACY) {
      double x1 = (-b + Math.sqrt(d)) / (2 * a);
      double x2 = (-b - Math.sqrt(d)) / (2 * a);
      return new double[]{x1, x2};
    } else {
      double x1 = (-b / (2 * a));
      return new double[]{x1};
    }
  }
}
