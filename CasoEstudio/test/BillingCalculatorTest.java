/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Calendar;

import org.mockito.Mockito;

import java.math.BigDecimal;
import org.junit.Assert;

import co.edu.eafit.carritocompras.data.Product;
import co.edu.eafit.carritocompras.service.BillingCalculator;
import co.edu.eafit.carritocompras.data.Customer;
import co.edu.eafit.carritocompras.service.IvaCalculator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author JAVIER
 */
public class BillingCalculatorTest {

    private Customer customer;
    private BillingCalculator b;
    private Product p;
    private Calendar date;

    private int result;
    private BigDecimal compare;
    private BigDecimal total;
    private BigDecimal ending;
    private int factor;
    private int point=1000;
    public BillingCalculatorTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        customer = new Customer("xx1","xxName",point );
       
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void calculateTotalPurchase() {
        IvaCalculator iva =Mockito.mock(IvaCalculator.class);
        Mockito.when(iva.ivaCalculate()).
                thenReturn(new BigDecimal(0.03));

        result=Product.getProductPricesBD().get("EL-001").multiply(new BigDecimal(0.95)).add(Product.getProductPricesBD().get("FU-006").multiply(new BigDecimal(0.9))).intValue();
        compare=BillingCalculator.calculateTotalPurchase(customer,"EL-001,FU-006",iva).getTotalPrice();
        total=BillingCalculator.calculateTotalPurchase(customer,"EL-001,FU-006",iva).getTotalPriceTask();

        ending=BillingCalculator.calculateTotalPurchase(customer,"EL-001,FU-006",iva).getTotalFinal();

        factor=total.intValue()/1000;

        point=point+factor;

       //Assert.assertEquals(result,compare.intValue());//compare discounts

       //Assert.assertEquals(compare.add(compare.multiply(new BigDecimal(0.03))).intValue(), total.intValue()); //compare using iva

       //Assert.assertEquals(point, customer.getPoints());
       Assert.assertEquals(total.add(total.multiply(new BigDecimal(0.02))).intValue(),ending.intValue());

        
    }

}