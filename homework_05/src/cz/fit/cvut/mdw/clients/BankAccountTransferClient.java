/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fit.cvut.mdw.clients;
import cz.fit.cvut.mdw.IBankAccountTransfer;
/**
 *
 * @author david
 */
public class BankAccountTransferClient implements IBankAccountTransfer {
    generated.client.BankTransferService_Service service = new generated.client.BankTransferService_Service();
    generated.client.BankTransferService port = service.getBankTransferServicePort();
        
    @Override
    public boolean transfer(String sourceBankAccountNumber, String targetBankAccountNumber, double amount) {
        return port.transfer(sourceBankAccountNumber, targetBankAccountNumber, amount);
    }    
}
