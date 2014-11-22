/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fit.cvut.mdw.clients;

import cz.fit.cvut.mdw.IBankAccountService;
import java.math.BigDecimal;

/*
 * @author david
 */
public class BankAccountServiceClient implements IBankAccountService {
    generated.client.BankAccountService_Service service = new generated.client.BankAccountService_Service();
    generated.client.BankAccountService port = service.getBankAccountServicePort();
    
    @Override
    public boolean accountExists(java.lang.String bankAccountNumber) {
        return port.accountExists(bankAccountNumber);
    }

    @Override
    public BigDecimal changeBalance(java.lang.String bankAccountNumber, double amount) {        
        return port.changeBalance(bankAccountNumber, amount);
    }

    @Override
    public boolean validateBalance(java.lang.String bankAccountNumber, double amount) {
        return port.validateBalance(bankAccountNumber, amount);
    }

    @Override
    public BigDecimal getBalance(String accountNumber) {
        return port.getBalance(accountNumber);
    }
}
