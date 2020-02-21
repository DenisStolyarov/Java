package control.system.DAL.Entities;

import java.math.BigDecimal;

public abstract class Account
{
    private int id;
    private int bonusCoint;
    private String firstName;
    private String lastName;
    private BigDecimal moneyAmount;
    private Status status;

    public Account(int id, String firstName, String lastName)
    {

    }

    protected abstract void GetBonus(BigDecimal payment, boolean deposit);

    public void Deposit(BigDecimal payment)
    {
        GetBonus(payment, true);
    }

    public void Withdraw(BigDecimal payment)
    {
        GetBonus(payment, false);
    }

    public void ChangeStatus(Status status)
    {
        this.status = status;
    }

    public class AccountDTO
    {

    }
}
