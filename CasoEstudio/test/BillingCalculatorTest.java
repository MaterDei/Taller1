/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.math.BigDecimal;
import org.junit.Assert;
import co.edu.eafit.carritocompras.data.products.ElectronicProduct;
import co.edu.eafit.carritocompras.data.Product;
import co.edu.eafit.carritocompras.service.BillingCalculator;
import co.edu.eafit.carritocompras.data.Customer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author JAVIER
 */
public class BillingCalculatorTest {

    private Customer customer;
    private BillingCalculator b;
    private Product p;
    private ElectronicProduct ep;

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
        
        Assert.assertEquals(p.getProductPricesBD().get("EL-001").multiply(new BigDecimal(0.95)).add(p.getProductPricesBD().get("FU-006").multiply(new BigDecimal(0.9))).intValue(),b.calculateTotalPurchase(customer,"EL-001,FU-006").getTotalPrice().intValue());

        //p.getProductPricesBD().get("EL-001").multiply(new BigDecimal(0.2))
    }

}