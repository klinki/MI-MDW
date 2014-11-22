package cz.fit.cvut.mdw;

import java.math.BigDecimal;

/**
 * Created by David on 22. 11. 2014.
 */
public class BankAccount {
    public static class BankAccountNumber
    {
        private int number;

        public BankAccountNumber(int number)
        {
            this.number = number;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            BankAccountNumber that = (BankAccountNumber) o;

           return (this.number == that.number);
        }

        @Override
        public int hashCode() {
            return number;
        }
    }

    BankAccountNumber number;
    BigDecimal balance;

    public BankAccount(BankAccountNumber number, BigDecimal balance) {
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
}
