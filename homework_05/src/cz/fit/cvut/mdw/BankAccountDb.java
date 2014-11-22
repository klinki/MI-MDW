package cz.fit.cvut.mdw;

import java.util.HashMap;
import java.util.Map;
import static cz.fit.cvut.mdw.BankAccount.BankAccountNumber;
/**
 * Created by David on 22. 11. 2014.
 */
public class BankAccountDb {
    public static class BankAccountNotFoundException extends RuntimeException {}

    public static BankAccountDb instance;

    private Map<BankAccountNumber, BankAccount> accountMap = new HashMap<BankAccountNumber, BankAccount>();

    public static BankAccountDb getInstance() {
        if (instance == null) {
            instance = new BankAccountDb();
        }

        return instance;
    }

    public BankAccount getAccount(BankAccountNumber number)
    {
        BankAccount account = this.accountMap.get(number);

        if (account == null) {
            throw new BankAccountNotFoundException();
        }

        return account;
    }


    public boolean accountExists(BankAccountNumber accountNumber)
    {
        return this.accountMap.containsKey(accountNumber);
    }

}
