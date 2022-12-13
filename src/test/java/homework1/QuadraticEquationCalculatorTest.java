package homework1;


import static java.lang.Double.NEGATIVE_INFINITY;
import static java.lang.Double.POSITIVE_INFINITY;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class QuadraticEquationCalculatorTest {
  private  final QuadraticEquationCalculator calc = new QuadraticEquationCalculator();

  @Test
  void calculate_noRoot() {
    double[] calculate = calc.calculate(1, 0, 1);
    Assertions.assertEquals(0, calculate.length);
  }

  @Test
  void calculate_twoRoot() {
    double[] calculate = calc.calculate(1, 0, -1);
    assertThat(calculate).containsExactlyInAnyOrder(1, -1);
  }

  @Test
  void calculate_oneRoot() {
    double[] calculate = calc.calculate(1, 2, 1.0000001);
    assertThat(calculate).containsExactlyInAnyOrder(-1);
  }

  @Test
  void calculate_throwIllegalArgumentExceptionIfAZero() {
    Assertions.assertThrows(RuntimeException.class, () -> calc.calculate(0.000001, 2, +1));
  }

  @Test
  void calculate_throwIllegalArgumentExceptionOnNaN() {
    Assertions.assertThrows(RuntimeException.class, () -> calc.calculate(Double.NaN, 2, 1));
    Assertions.assertThrows(RuntimeException.class, () -> calc.calculate(1, Double.NaN, 1));
    Assertions.assertThrows(RuntimeException.class, () -> calc.calculate(1, 2, Double.NaN));
  }

  @Test
  void calculate_throwIllegalArgumentExceptionOnInfinity() {
    Assertions.assertThrows(RuntimeException.class, () -> calc.calculate(NEGATIVE_INFINITY, 2, 1));
    Assertions.assertThrows(RuntimeException.class, () -> calc.calculate(1, POSITIVE_INFINITY, 1));
    Assertions.assertThrows(RuntimeException.class, () -> calc.calculate(1, 2, NEGATIVE_INFINITY));
  }

}











