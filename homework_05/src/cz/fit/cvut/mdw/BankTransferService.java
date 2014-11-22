package cz.fit.cvut.mdw;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.math.BigDecimal;
import static cz.fit.cvut.mdw.BankAccount.BankAccountNumber;

/**
 * Created by David on 22. 11. 2014.
 */
@WebService()
public class BankTransferService {

    @WebMethod
    public boolean transfer(BankAccountNumber from, BankAccountNumber to, BigDecimal amount)
    {
        BankAccountService service = new BankAccountService();

        if(service.accountExists(from) && service.accountExists(to)) {
            if (service.validateBalance(from, amount)) {
                service.changeBalance(from, amount.negate());
                service.changeBalance(to, amount);

                return true;
            }
        }

        return false;
    }

    /*
     Add operation transfer(from,to,amount)

    from - Account Number
    to - Account Number
    amount - Numeric value
    the logic of operation:
        check existence of from and to account and check the balance of from account (use SOAP communication with Bank Account Web Service)
        if ok –> change balance of both accounts and return true
        else –> return false

You can use automatic generation of client for Bank Account Service
     */
  public static void main(String[] argv) {
    Object implementor = new BankTransferService ();
    String address = "http://localhost:9000/BankTransferService";
    Endpoint.publish(address, implementor);
  }
}
