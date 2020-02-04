import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Created by kimjs on 2020-02-04.
 */
public class DollarTest {


  @Test
  public void testMultiplication() {
    Dollar five = new Dollar(5);
    five.times(2);
    assertEquals(10, five.amount);
  }
}
