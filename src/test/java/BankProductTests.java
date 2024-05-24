import org.junit.Test;
import ru.sber.core.task1.products.CreditCard;
import ru.sber.core.task1.products.DepositAccount;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class BankProductTests {

    @Test()
    public void testCreditCardFunctionality() {
        CreditCard cardCredit = new CreditCard("rub", 130, "myCredit", 17);

        assertNotNull(cardCredit);

        assertEquals(130.0, cardCredit.getBalance(),0.01);
        cardCredit.withdraw(30);
        assertEquals(100.0, cardCredit.getBalance(),0.01);
        cardCredit.deposit(50);
        assertEquals(150.0, cardCredit.getBalance(),0.01);

        assertEquals(1000.0, cardCredit.checkDebt(),0.01);

        assertEquals(17.0, cardCredit.getInterestRate(),0.01);
        assertEquals("myCredit", cardCredit.getName());
        assertEquals("rub", cardCredit.getCurrency());
    }

    @Test()
    public void testCheckDepositFunctionality() {
        DepositAccount deposit = new DepositAccount("rub", 1000, "myDeposit");

        assertNotNull(deposit);

        assertEquals(deposit.getBalance(), 1000.0, 0.01);
        deposit.deposit(100);
        assertEquals(1100.0, deposit.getBalance(), 0.01);
        assertEquals("myDeposit", deposit.getName());
        assertEquals("rub", deposit.getCurrency());

        deposit.closeDeposit();
        assertEquals(0.0, deposit.getBalance(), 0.01);
    }

}
