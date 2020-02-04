/**
 * Created by kimjs on 2020-02-04.
 */
public class Dollar {

  public int amount;

  public Dollar(int amount) {
    this.amount = amount;
  }

  public void times(int multiplier) {
    amount *= multiplier;
  }
}
