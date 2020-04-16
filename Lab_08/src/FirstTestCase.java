import BLL.Bank;
import DAL.Entities.Account;
import DAL.Entities.AccountType;
import DAL.Entities.Status;
import org.testng.Assert;
import org.testng.annotations.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

public class FirstTestCase {

    private Bank bank;
    private Iterable<Account.AccountDTO> accounts;

    @BeforeSuite
    void beforeSuite(){
        System.out.println("Suite start!");
    }

    @BeforeTest
    void beforeTest(){
        System.out.println("Test start!");
    }

    @BeforeClass
    void setup(){
        bank = new Bank();
        bank.OpenAccount("Denis Stolyarov", AccountType.Base);
        bank.OpenAccount("Mark Tven", AccountType.Silver);
        bank.OpenAccount("Ivan Grozny", AccountType.Gold);
        accounts = bank.GetAllAccounts();
    }

    @BeforeMethod
    void beforeMethod(){
        System.out.println("Method start!");
    }

    @AfterMethod
    void afterMethod(){
        System.out.println("Method finish!");
    }

    @AfterClass
    void afterClass(){
        System.out.println("Class finish!");
    }

    @AfterTest
    void afterTest(){
        System.out.println("Test finish!");
    }

    @BeforeSuite
    void afterSuite(){
        System.out.println("Suite finish!");
    }

    @Test
    void CheckCount(){
        var wrapper = new ArrayList<Account.AccountDTO>((Collection<? extends Account.AccountDTO>) accounts);
        Assert.assertEquals(wrapper.size(), 3);
    }

    @Parameters("amountPlus")
    @Test(priority = 1)
    void TestDeposit(int Param){
        var amount = new BigDecimal(Param);
        for (var item : accounts)
        {
            bank.DepositAccount(item.idDTO,  amount);
        }

        accounts = bank.GetAllAccounts();

        for (var item : accounts)
        {
            Assert.assertEquals( item.moneyAmountDTO, amount, "Must be open!");
        }
    }

    @Parameters("amountMinus")
    @Test(dependsOnMethods = "TestDeposit", priority = 1)
    void TestWithdraw(int Param){
        var amount = new BigDecimal(Param);

        for (var item : accounts)
        {
            bank.WithdrawAccount(item.idDTO,  amount);
        }

        accounts = bank.GetAllAccounts();

        for (var item : accounts)
        {
            Assert.assertEquals(item.moneyAmountDTO, amount);
        }
    }


    @Test(timeOut = 2000, priority = 2)
    void TestCloseAccounts(){

        for (var item : accounts)
        {
            bank.CloseAccount(item.idDTO);
        }

        accounts = bank.GetAllAccounts();

        for (var item : accounts)
        {
            Assert.assertEquals(item.statusDTO, Status.Close);
        }
    }

    @Ignore
    void Ignored(){
        System.out.println("Ignoring!");
        Assert.fail();
    }
}
