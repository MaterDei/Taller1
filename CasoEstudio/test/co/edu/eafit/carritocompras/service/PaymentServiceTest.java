package co.edu.eafit.carritocompras.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import co.edu.eafit.carritocompras.data.Customer;
import co.edu.eafit.carritocompras.data.Purchase;
import java.math.BigDecimal;
import org.junit.Assert;

public class PaymentServiceTest {

	private Customer customer;
	private PaymentService paymentService;
	private BigDecimal ending;
        private BigDecimal total;
        private int factor;
        private int point= 1000;

	@Before
	public void setUp() {
		customer = new Customer("xx1", "xxName",1000);
		paymentService = new PaymentService();
	}
	
	@Test
	public void testPay() {
		IvaCalculator iva =Mockito.mock(IvaCalculator.class);
                Mockito.when(iva.ivaCalculate()).
                thenReturn(new BigDecimal(0.03));

                GenericCreditCardService creditCardService = Mockito.mock(GenericCreditCardService.class);
		Purchase p = BillingCalculator.calculateTotalPurchase(customer, "EL-001,FU-006",iva);
		
		//Mocking external service behavior
		Mockito.when(creditCardService.pay("xxxx111xxxx", p.getTotalPrice())).thenReturn(true);
		
		paymentService.pay(customer, p, "xxxx111xxxx", creditCardService);
                
                total=BillingCalculator.calculateTotalPurchase(customer,"EL-001,FU-006",iva).getTotalPriceTask();
                ending=BillingCalculator.calculateTotalPurchase(customer,"EL-001,FU-006",iva).getTotalFinal();

                factor=total.intValue()/1000;

                point=point+factor;

                Assert.assertEquals(point, customer.getPoints());
		
	}

}
