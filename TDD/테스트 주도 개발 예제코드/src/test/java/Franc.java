import java.time.Month;

/**
 * Created by kimjs on 2020-02-04.
 */
public class Franc extends Money {

  public Franc(int amount) {
    this.amount = amount;
  }

  public Franc times(int multiplier) {
    return new Franc(amount * multiplier);
  }
}
