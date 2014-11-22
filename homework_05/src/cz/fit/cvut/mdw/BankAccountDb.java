package cz.fit.cvut.mdw;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import javax.xml.ws.WebServiceException;
/**
 * Created by David on 22. 11. 2014.
 */
public class BankAccountDb {
    public static class BankAccountNotFoundException extends WebServiceException {
        public BankAccountNotFoundException(String message) {
            super(message);
        }
 }

    public static BankAccountDb instance;

    private final Map<String, BankAccount> accountMap = new HashMap<>();

    public BankAccountDb()
    {
        BankAccount firstAccount = new BankAccount("0000002", new BigDecimal(5000));
        BankAccount secondAccount = new BankAccount("0000001", new BigDecimal(0));

        this.accountMap.put(firstAccount.getNumber(), firstAccount);
        this.accountMap.put(secondAccount.getNumber(), secondAccount);
    }
    
    public static BankAccountDb getInstance() {
        if (instance == null) {
            instance = new BankAccountDb();
        }

        return instance;
    }

    public BankAccount getAccount(String number)
    {
        BankAccount account = this.accountMap.get(number);

        if (account == null) {
            throw new BankAccountNotFoundException("Account with id: " + number + " not found");
        }

        return account;
    }


    public boolean accountExists(String accountNumber)
    {
        return this.accountMap.containsKey(accountNumber);
    }

}
