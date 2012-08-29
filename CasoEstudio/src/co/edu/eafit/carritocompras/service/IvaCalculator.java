/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package co.edu.eafit.carritocompras.service;

import java.math.BigDecimal;

/**
 *
 * @author JAVIER
 */
public class IvaCalculator {

    public BigDecimal ivaCalculate  ()
    {
    //complex logic consuming remote web service
        return new BigDecimal (0.04);
    }

}
