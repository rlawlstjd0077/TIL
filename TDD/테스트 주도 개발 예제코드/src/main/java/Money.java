/**
 * Created by kimjs on 2020-02-04.
 */
public abstract class Money {
  protected int amount;



  abstract Money times(int multiplier);

  public static Money dollar(int amount) {
    return new Dollar(amount);
  }

  public static Money Franc(int amount) {
    return new Franc(amount);
  }

  public boolean equals(Object object) {
    Money money = (Money) object;
    return amount == money.amount && getClass().equals(money.getClass());
  }
}
