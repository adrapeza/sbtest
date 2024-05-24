import org.junit.Test;
import ru.sber.core.task1.products.CreditCard;
import ru.sber.core.task1.products.DepositAccount;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class BankProductTests {

    @Test()
    public void testCreditCardDeposit() {
        CreditCard cardCredit = new CreditCard("rub", 130, "myCredit", 17);

        assertNotNull(cardCredit);

        assertEquals(130.0, cardCredit.getBalance());
        cardCredit.withdraw(30);
        assertEquals(100.0, cardCredit.getBalance());
        cardCredit.deposit(50);
        assertEquals(150.0, cardCredit.getBalance());

        assertEquals(1000.0, cardCredit.checkDebt());

        assertEquals(17.0, cardCredit.getInterestRate());
        assertEquals("myCredit", cardCredit.getName());
        assertEquals("rub", cardCredit.getCurrency());
    }

    @Test()
    public void testCheckDeposit() {
        DepositAccount deposit = new DepositAccount("rub", 1000, "myDeposit");

        assertNotNull(deposit);

        assertEquals(deposit.getBalance(), 1000.0);
        deposit.deposit(100);
        assertEquals(1100.0, deposit.getBalance());
        assertEquals("myDeposit", deposit.getName());
        assertEquals("rub", deposit.getCurrency());

        deposit.closeDeposit();
        assertEquals(0.0, deposit.getBalance());
    }

}
