/**
 * Created by kimjs on 2020-02-04.
 */
public class Dollar extends Money{
  public Dollar(int amount) {
    this.amount = amount;
  }

  public Dollar times(int multiplier) {
    return new Dollar(amount * multiplier);
  }
}
