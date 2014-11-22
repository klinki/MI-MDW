package cz.fit.cvut.mdw;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.math.BigDecimal;
import javax.jws.WebParam;

/**
 * Created by David on 22. 11. 2014.
 */
@WebService(serviceName = "BankAccountService")
public class BankAccountService implements IBankAccountService {

    BankAccountDb accountDb;

    public BankAccountService()
    {
        this.accountDb = BankAccountDb.getInstance();
    }

    @WebMethod
    public boolean accountExists(@WebParam(name = "bankAccountNumber") String accountNumber)
    {
        return this.accountDb.accountExists(accountNumber);
    }

    @WebMethod
    public boolean validateBalance(@WebParam(name = "bankAccountNumber") String accountNumber, 
            @WebParam(name = "amount") double amount) throws BankAccountDb.BankAccountNotFoundException 
    {
        BankAccount account = this.accountDb.getAccount(accountNumber);
        return account.balance.compareTo(new BigDecimal(amount)) >= 0;
    }

    @WebMethod
    public BigDecimal changeBalance(@WebParam(name = "bankAccountNumber") String accountNumber, 
            @WebParam(name = "amount") double amount) throws BankAccountDb.BankAccountNotFoundException 
    {
        BankAccount account = this.accountDb.getAccount(accountNumber);
        return account.changeBalance(new BigDecimal(amount));
    }
    
    @WebMethod
    @Override
    public BigDecimal getBalance(@WebParam(name = "bankAccountNumber") String accountNumber)
    {
        BankAccount account = this.accountDb.getAccount(accountNumber);
        return account.getBalance();
    }
}
