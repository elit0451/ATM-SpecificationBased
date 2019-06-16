public interface DataMapperInterface {

    public void setCreditCard(CreditCardInterface creditCard);
    public CreditCardInterface getCreditCard(int id);
    public void setAccount(AccountInterface account);
    public AccountInterface getAccount(int id);
}
