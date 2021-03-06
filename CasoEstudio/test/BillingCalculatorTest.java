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
    private int total;
    private int ending;
    private int factor;
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
        customer = new Customer("xx1","xxName" );
       
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
        total=BillingCalculator.calculateTotalPurchase(customer,"EL-001,FU-006",iva).getTotalPriceTask().intValue();
        //ending=BillingCalculator.calculateTotalPurchase(customer,"EL-001,FU-006",950).getTotalFinal().intValue();

        //factor=ending/1000;
        
       // Assert.assertEquals(date, customer.getPoints());

        Assert.assertEquals(result,compare.intValue());//compare discounts
        
       Assert.assertEquals(compare.add(compare.multiply(new BigDecimal(0.03))).intValue(), total); //compare using iva
        
    }

}