import java.time.Month;

/**
 * Created by kimjs on 2020-02-04.
 */
public class Franc extends Money {

  public Franc(int amount, String currency) {
    super(amount, currency);
  }

  public Money times(int multiplier) {
    return Money.franc(amount * multiplier);
  }
}
