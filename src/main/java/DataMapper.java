import java.util.HashMap;

public class DataMapper implements DataMapperInterface {
    HashMap<Integer, AccountInterface> accounts;
    HashMap<Integer, CreditCardInterface> cards;

    public DataMapper() {
        accounts = new HashMap<Integer, AccountInterface>();
        cards = new HashMap<Integer, CreditCardInterface>();
    }

    public void setCreditCard(CreditCardInterface creditCard) {
        if (!cards.containsKey(creditCard))
            cards.put(creditCard.getId(), creditCard);
        else
            cards.replace(creditCard.getId(), creditCard);
    }

    public CreditCardInterface getCreditCard(int id) {
        if (!cards.containsKey(id))
            return null;
        else
            return cards.get(id);
    }

    public void setAccount(AccountInterface account) {
        if (!accounts.containsKey(account))
            accounts.put(account.getId(), account);
        else
            accounts.replace(account.getId(), account);
    }

    public AccountInterface getAccount(int id) {
        if (!accounts.containsKey(id))
            return null;
        else
            return accounts.get(id);
    }
}
