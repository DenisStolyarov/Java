package DAL.Entities;

import java.math.BigDecimal;

public class GoldAccount extends Account
{
    public GoldAccount(int id, String firstName, String lastName) {
        super(id, firstName, lastName);
    }

    @Override
    protected void GetBonus(BigDecimal payment, boolean deposit) {
        int bonus;

        if(deposit)
        {
            bonus = payment.intValue()/10;
        }
        else
        {
            bonus = -1 * payment.intValue()/3000;
        }

        this.bonusCoin += bonus;
    }
}
