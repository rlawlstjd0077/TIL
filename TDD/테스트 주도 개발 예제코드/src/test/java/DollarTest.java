import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.Month;
import org.junit.Test;

/**
 * Created by kimjs on 2020-02-04.
 */
public class DollarTest {


  @Test
  public void testMultiplication() {
    Money five = Money.dollar(5);
    assertEquals(new Dollar(10), five.times(2));
    assertEquals(new Dollar(15), five.times(3));
  }


  @Test
  public void testEquality() {
    assertTrue(new Dollar(5).equals(new Dollar(5)));
    assertFalse(new Dollar(5).equals(new Dollar(6)));
    assertTrue(new Franc(5).equals(new Franc(5)));
    assertFalse(new Franc(5).equals(new Franc(6)));
    assertFalse(new Dollar(5).equals(new Franc(5)));
  }

  @Test
  public void testFrancMultiplication() {
    Money five = Money.Franc(5);
    assertEquals(Money.Franc(10), five.times(2));
    assertEquals(Money.Franc(15), five.times(3));
  }
}
