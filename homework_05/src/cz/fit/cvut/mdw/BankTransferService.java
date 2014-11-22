package cz.fit.cvut.mdw;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.WebParam;
import cz.fit.cvut.mdw.clients.BankAccountServiceClient;
/**
 * Created by David on 22. 11. 2014.
 */
@WebService(serviceName = "BankTransferService")
public class BankTransferService implements IBankAccountTransfer {

    IBankAccountService service;
    
    public BankTransferService()
    {
        this.service = new BankAccountServiceClient();
    }
   
    @WebMethod
    @Override
    public boolean transfer(@WebParam(name = "sourceBankAccountNumber") String from, 
            @WebParam(name = "targetBankAccountNumber") String to, 
            @WebParam(name = "amount") double amount)
    {   

        if(this.service.accountExists(from) && this.service.accountExists(to)) {
            if (this.service.validateBalance(from, amount)) {
                this.service.changeBalance(from, -amount);
                this.service.changeBalance(to, amount);

                return true;
            }
        }

        return false;
    }
}
