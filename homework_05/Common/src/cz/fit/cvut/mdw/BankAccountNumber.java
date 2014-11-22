/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fit.cvut.mdw;

/**
 *
 * @author david
 */
public class BankAccountNumber
{
    private final int number;
   
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
