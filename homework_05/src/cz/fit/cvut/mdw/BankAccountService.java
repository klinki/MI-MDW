package cz.fit.cvut.mdw;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static cz.fit.cvut.mdw.BankAccount.BankAccountNumber;

/**
 * Created by David on 22. 11. 2014.
 */
@WebService()
public class BankAccountService {

    BankAccountDb accountDb;

    public BankAccountService()
    {
        this.accountDb = BankAccountDb.getInstance();
    }

    @WebMethod
    public boolean accountExists(BankAccountNumber accountNumber)
    {
        return this.accountDb.accountExists(accountNumber);
    }

    @WebMethod
    public boolean validateBalance(BankAccountNumber accountNumber, BigDecimal balance)
    {
        BankAccount account = this.accountDb.getAccount(accountNumber);
        return account.balance.compareTo(balance) >= 0;
    }

    @WebMethod
    public BigDecimal changeBalance(BankAccountNumber accountNumber, BigDecimal change)
    {
        BankAccount account = this.accountDb.getAccount(accountNumber);
        return account.changeBalance(change);
    }

    /*

    Add operation to validate existence of Bank account by account number (returns true/false)
    Add operation to validate Account Balance by account number (returns true/false)
    Add operation to change Account Balance (+/-)

     */

  public static void main(String[] argv) {
    Object implementor = new BankAccountService ();
    String address = "http://localhost:9000/BankAccountService";
    Endpoint.publish(address, implementor);
  }
}
