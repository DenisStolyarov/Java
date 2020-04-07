package DAL.Entities;

import java.math.BigDecimal;

public abstract class Account implements Comparable<Account>
{
    protected int bonusCoin;
    protected int id;
    protected String firstName;
    protected String lastName;
    protected BigDecimal moneyAmount;
    protected Status status;

    public Account(int id, String firstName, String lastName)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bonusCoin = 0;
        this.moneyAmount = new BigDecimal(0);
        this.status = Status.Open;
    }

    protected abstract void GetBonus(BigDecimal payment, boolean deposit);

    public void Deposit(BigDecimal payment)
    {
        this.moneyAmount = this.moneyAmount.add(payment);
        GetBonus(payment, true);
    }

    public void Withdraw(BigDecimal payment)
    {
        this.moneyAmount = this.moneyAmount.subtract(payment);
        GetBonus(payment, false);
    }

    public int getId() {
        return id;
    }

    public int getBonusCoin()
    {
        return bonusCoin;
    }

    public BigDecimal getMoneyAmount()
    {
        return moneyAmount;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public Status getStatus()
    {
        return status;
    }

    public void setStatus(Status status)
    {
        this.status = status;
    }

    public AccountDTO getDTO()
    {
        return new AccountDTO();
    }

    @Override
    public int compareTo(Account item)
    {
        if (this.id > item.id)
        {
            return 1;
        }
        else if (this.id < item.id)
        {
            return -1;
        }
        else
        {
            return 0;
        }
    }

    public class AccountDTO
    {
        public int idDTO;
        public int bonusCoinDTO;
        public String firstNameDTO;
        public String lastNameDTO;
        public BigDecimal moneyAmountDTO;
        public Status statusDTO;

        public AccountDTO()
        {
            this.idDTO = id;
            this.firstNameDTO = firstName;
            this.lastNameDTO = lastName;
            this.bonusCoinDTO = bonusCoin;
            this.moneyAmountDTO = moneyAmount;
            this.statusDTO = status;
        }

        @Override
        public String toString() {
            var builder = new StringBuilder();
            builder.append("Id: ").append(this.idDTO).append("\n")
                    .append("First name: ").append(this.firstNameDTO).append("\n")
                    .append("Last name: ").append(this.lastNameDTO).append("\n")
                    .append("Money amount: ").append(this.moneyAmountDTO).append("\n")
                    .append("Bonus coin: ").append(this.bonusCoinDTO).append("\n")
                    .append("Status: ").append(this.statusDTO).append("\n");
            return builder.toString();
        }
    }
}
