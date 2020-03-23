package control.system.DAL.Entities;

import java.math.BigDecimal;

public class SilverAccount extends Account
{
    public SilverAccount(int id, String firstName, String lastName) {
        super(id, firstName, lastName);
    }

    @Override
    protected void GetBonus(BigDecimal payment, boolean deposit) {
        int bonus;

        if(deposit)
        {
            bonus = payment.intValue()/50;
        }
        else
        {
            bonus = -1 * payment.intValue()/2000;
        }

        this.bonusCoin += bonus;
    }
}
