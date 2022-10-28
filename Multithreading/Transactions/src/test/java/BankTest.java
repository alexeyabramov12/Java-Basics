import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(DataProviderRunner.class)
public class BankTest {
    @DataProvider
    public static Object[][] data() {
        return new Object[][] {
                // Correct translation
                {"1","2", 10100, 10200, 1000, 9100, 11200, false, false},
                // The amount of the transfer is greater than that of the sender
                {"3","4", 0, 10000, 1000, 0, 10000, false, false},
                // The amount of the transfer is greater than that of the sender
                {"5","6", 100, 0, 1000, 100, 0, false, false},
                // Correct translation
                {"7","8", 1000, 0, 1000, 0, 1000, false, false},
                //The transfer amount is more than 50000, the result is random due to a random variable
                {"9","10", 100100, 10200, 50010, 50090, 60210, false, false},
                //Account "from" blocked
                {"11","12", 1000, 2000, 1000, 1000, 2000, true, false},
                //Account "to" blocked
                {"13","14", 1000, 2000, 1000, 1000, 2000, false, true}
        };
    }
    @Test
    @UseDataProvider("data")
    public void transferTest(String fromAccountNum, String toAccountNum, long countMoneyFromAccount, long countMoneyToAccount,
                             long amount, long expected1, long expected2, boolean blockFromAcc, boolean blocToAcc) {
        Bank bank = new Bank();
        Account account1 = new Account(fromAccountNum, countMoneyFromAccount);
        Account account2 = new Account(toAccountNum, countMoneyToAccount);
        account1.setBlocking(blockFromAcc);
        account2.setBlocking(blocToAcc);
        bank.addAccount(fromAccountNum, account1);
        bank.addAccount(toAccountNum, account2);
        bank.transfer(fromAccountNum, toAccountNum, amount);
        Assert.assertEquals("fromAccount", expected1, bank.getBalance(fromAccountNum));
        Assert.assertEquals("toAccount", expected2, bank.getBalance(toAccountNum));
    }

}
