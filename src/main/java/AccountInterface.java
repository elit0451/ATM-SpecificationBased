import java.util.ArrayList;

public interface AccountInterface {
    public void setId(int id);
    public int getId();
    public void setBalance(double balance);
    public double getBalance();
    public ArrayList<CreditCardInterface> getCards();
    public void addCard(CreditCardInterface card);
    public boolean hasLoyaltyCard();
    public void setLoyaltyCard(boolean loyaltyCard);
    public void deposit(double amount);
    public void withdraw(double amount);
    public double getMonthlyInterest();
}