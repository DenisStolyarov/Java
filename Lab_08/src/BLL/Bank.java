package BLL;

import DAL.Entities.*;
import DAL.Interfaces.IRepository;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Bank
{
    private IRepository<Account> repository = new AccountRepository();
    private static int id = 0;

    public void OpenAccount(String fullName, AccountType type)
    {
        var firstNameIndex = 0;
        var lastNameIndex = 1;
        var names = fullName.split(" ");
        this.id++;

        if (names.length < 2)
        {
            throw new IllegalArgumentException("Wrong name format!");
        }

        if (names[firstNameIndex].isBlank())
        {
            throw new NullPointerException();
        }

        if (names[lastNameIndex].isBlank())
        {
            throw new NullPointerException();
        }

        Account account;

        switch (type)
        {
            case Base:
                account = new BaseAccount(id, names[firstNameIndex], names[lastNameIndex]);
                break;
            case Gold:
                account = new GoldAccount(id, names[firstNameIndex], names[lastNameIndex]);
                break;
            case Silver:
                account = new SilverAccount(id, names[firstNameIndex], names[lastNameIndex]);
                break;
            default:
                throw new IllegalArgumentException();
        }

        this.repository.Create(account);
    }

    public Iterable<Account.AccountDTO> GetAllAccounts()
    {
        var accountsDTO= new ArrayList<Account.AccountDTO>();

        for (var account : repository.GetAll())
        {
            accountsDTO.add(account.getDTO());
        }

        return accountsDTO;
    }

    public void WithdrawAccount(int id, BigDecimal payment)
    {
        var account = repository.Get(id);
        if (account.getStatus() == Status.Close)
        {
            throw new IllegalArgumentException("Account closed.");
        }
        if (payment.compareTo(new BigDecimal(0)) < 0)
        {
            throw new IllegalArgumentException("Payment must be positive.");
        }
        if (payment.compareTo(account.getMoneyAmount()) > 0)
        {
            throw new IllegalArgumentException("Insufficient funds.");
        }

        account.Withdraw(payment);
        //repository.Update(account);
    }

    public void DepositAccount(int id, BigDecimal payment)
    {
        var account = repository.Get(id);
        if (account.getStatus() == Status.Close)
        {
            throw new IllegalArgumentException("Account closed.");
        }
        if (payment.compareTo(new BigDecimal(0)) < 0)
        {
            throw new IllegalArgumentException("Payment must be positive.");
        }

        account.Deposit(payment);
        //repository.Update(account);
    }

    public void CloseAccount(int id)
    {
        var account = repository.Get(id);
        account.setStatus(Status.Close);
        //repository.Update(account);
    }
}
