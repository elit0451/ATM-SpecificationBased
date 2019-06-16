import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Account implements AccountInterface {

    private int _id;
    private double _balance;
    private ArrayList<CreditCardInterface> _cards;
    private boolean _hasLoyaltyCard;

    public Account(boolean createCard) {
        _id = 0;
        _balance = 0;
        _cards = new ArrayList<>();
        _hasLoyaltyCard = false;

        if (createCard)
            _cards.add(new CreditCard(this, false));
    }

    public void setId(int id) {
        _id = id;
    }

    public int getId() {
        return _id;
    }

    public void setBalance(double balance) {
        _balance = balance;
    }

    public double getBalance() {
        return _balance;
    }

    public ArrayList<CreditCardInterface> getCards() {
        return _cards;
    }

    public void addCard(CreditCardInterface card) {
        _cards.add(card);
    }

    public boolean hasLoyaltyCard() {
        return _hasLoyaltyCard;
    }

    public void setLoyaltyCard(boolean loyaltyCard) {
        _hasLoyaltyCard = loyaltyCard;
    }

    public void deposit(double amount) {
        _balance += amount;
    }

    public void withdraw(double amount) {
        if(_balance - amount < 0)
            throw new ArithmeticException();

        _balance -= amount;
    }

    public double getMonthlyInterest()
    {
        if(_balance > 0 && _balance <=100)
        {
            return round(_balance * 0.03,3);
        }
        else if(_balance > 100 && _balance <= 1000)
        {
            return round(_balance * 0.05,3);
        }
        else if(_balance > 1000)
        {
            return round(_balance * 0.07,3);
        }

        return 0;
    }

    private double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
