import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class ATMTest {

    @Test
    public void testNegativeBalance(){
        AccountInterface account = new Account(false);
        account.setBalance(-1);

        double interestRate = account.getMonthlyInterest();

        assertThat(interestRate, is(0.0));
    }

    @Test
    public void testFirstBoundary()
    {
        AccountInterface account = new Account(false);
        account.setBalance(100);

        double interestRate = account.getMonthlyInterest();

        assertThat(interestRate, is(3.0));
    }

    @Test
    public void testSecondBoundary()
    {
        AccountInterface account = new Account(false);
        account.setBalance(1000);

        double interestRate = account.getMonthlyInterest();

        assertThat(interestRate, is(50.0));
    }

    @Test
    public void testThirdBoundary()
    {
        AccountInterface account = new Account(false);
        account.setBalance(10000);

        double interestRate = account.getMonthlyInterest();

        assertThat(interestRate, is(700.0));
    }

    @RepeatedTest(5)
    public void repeatedTestWithDiscount() {

        AccountInterface account;
        CreditCardInterface creditCard10;
        CreditCardInterface creditCard15;
        CreditCardInterface creditCard20;

        account = new Account(false);
        account.setId(1);
        account.setBalance(100);

        creditCard15 = new CreditCard(account, false);

        account.setLoyaltyCard(true);
        creditCard10 = new CreditCard(account, false);

        account.setLoyaltyCard(false);
        creditCard20 = new CreditCard(account, true);

        assertThat(creditCard15.getDiscount(), equalTo(15));
        assertThat(creditCard10.getDiscount(), equalTo(10));
        assertThat(creditCard20.getDiscount(), equalTo(20));
    }

    @ParameterizedTest
    @ValueSource(strings = { "true", "false" })
    public void testDiscountsWithValueSource(Boolean coupon) {
        AccountInterface account = new Account(true);
        CreditCardInterface creditCard = new CreditCard(account, coupon);

        if (coupon)
            assertThat(creditCard.getDiscount(), is(20));
        else
            assertThat(creditCard.getDiscount(), is(0));
    }

    @ParameterizedTest
    @CsvSource({"true,20", "false,0"})
    public void testDiscountsWithCSVSource(Boolean coupon, int discount) {
        AccountInterface account = new Account(true);
        CreditCardInterface creditCard = new CreditCard(account, coupon);

        assertThat(creditCard.getDiscount(), is(discount));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    public void testDiscountsWithCSVSourceFile(Boolean coupon, int discount) {
        AccountInterface account = new Account(true);
        CreditCardInterface creditCard = new CreditCard(account, coupon);

        assertThat(creditCard.getDiscount(), is(discount));
    }

    @ParameterizedTest
    @MethodSource("provideTestData")
    void testDiscountWithMethodSource(Boolean coupon, int discount) {
        AccountInterface account = new Account(true);
        CreditCardInterface creditCard = new CreditCard(account, coupon);

        assertThat(creditCard.getDiscount(), is(discount));
    }

    private static Stream<Arguments> provideTestData() {
        return Stream.of(
                Arguments.of(true, 20),
                Arguments.of(false, 0)
        );
    }
}
