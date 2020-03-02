package control.system;

import control.system.BLL.Bank;
import control.system.DAL.Entities.*;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args)
    {
        var bank = new Bank();
        bank.OpenAccount("Denis Stolyarov", AccountType.Base);
        bank.OpenAccount("Mark Tven", AccountType.Silver);
        bank.OpenAccount("Ivan Grozny", AccountType.Gold);

        var accounts = bank.GetAllAccounts();

        Show(accounts);

        for (var item : accounts)
        {
            bank.DepositAccount(item.idDTO,  new BigDecimal(1_000_000));
        }

        Show(bank.GetAllAccounts());

        for (var item : accounts)
        {
            bank.WithdrawAccount(item.idDTO,  new BigDecimal(500_000));
        }

        Show(bank.GetAllAccounts());

        for (var item : accounts)
        {
            bank.CloseAccount(item.idDTO);
        }

        Show(bank.GetAllAccounts());
    }

    public static void Show(Iterable<Account.AccountDTO> list)
    {
        for (var item : list)
        {
            System.out.println(item);
        }

        System.out.println();
    }

}
