package cz.fit.cvut.mdw;

import java.math.BigDecimal;

/**
 * Created by David on 22. 11. 2014.
 */
public class BankAccount {
    String number;
    BigDecimal balance;

    public BankAccount(String number, BigDecimal balance) {
        this.number = number;
        this.balance = balance;
    }

    public BigDecimal changeBalance(BigDecimal change)
    {
        this.balance = this.balance.add(change);
        return this.balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getNumber() {
        return number;
    }
}
