/**
 * Created by kimjs on 2020-02-04.
 */
public class Money {
  protected int amount;

  public boolean equals(Object object) {
    Money money = (Money) object;
    return amount == money.amount && getClass().equals(money.getClass());
  }
}
