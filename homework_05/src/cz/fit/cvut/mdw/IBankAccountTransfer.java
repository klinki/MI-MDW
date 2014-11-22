/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fit.cvut.mdw;

import java.math.BigDecimal;

/**
 *
 * @author david
 */
public interface IBankAccountTransfer {
    boolean transfer(String from, String to, double amount);
}
