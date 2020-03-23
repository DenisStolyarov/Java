package control.system.DAL.Entities;

import java.math.BigDecimal;

public class BaseAccount extends Account
{

    public BaseAccount(int id, String firstName, String lastName) {
        super(id, firstName, lastName);
    }

    @Override
    protected void GetBonus(BigDecimal payment, boolean deposit) {
        int bonus;

        if(deposit)
        {
            bonus = payment.intValue()/100;
        }
        else
        {
            bonus = -1 * payment.intValue()/1000;
        }

        this.bonusCoin += bonus;
    }
}
