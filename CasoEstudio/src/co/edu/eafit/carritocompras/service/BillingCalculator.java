package co.edu.eafit.carritocompras.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import co.edu.eafit.carritocompras.data.Customer;
import co.edu.eafit.carritocompras.data.Product;
import co.edu.eafit.carritocompras.data.Purchase;
import co.edu.eafit.carritocompras.data.PurchaseStatus;
import co.edu.eafit.carritocompras.data.util.ChangeStatusException;
import java.math.MathContext;
import java.util.Calendar;

public class BillingCalculator {

	/**
	 * @param productsFlatFile
	 *            Comma-separated codes of products (e.g. code1, code2, code3)
	 * 
	 *            Postcondition: totalPrice attribute is updated with the
	 *            totalAmount of prices of products included
	 */

    //static IvaCalculator iva = new IvaCalculator();
	public static Purchase calculateTotalPurchase(Customer customer, String productsFlatFile, IvaCalculator iva) {
		Purchase purchase = new Purchase(customer);
		List<Product>products = new ArrayList<Product>();
		BigDecimal total = BigDecimal.ZERO;
		for (String code : productsFlatFile.split(",")) {
			Product p = Product.buildProduct(code);
			total = total.add(p.getPrice());
			total = total.subtract(p.calculateDiscount());
                        
			products.add(p);
		}
		purchase.setTotalPrice(total);
                total= total.add(total.multiply(iva.ivaCalculate()));
		purchase.setTotalPriceAfterTask(total);
                purchase.setProducts(products);
                
                int puntos=0;
                puntos=purchase.getTotalPriceTask().intValue()/1000;
                puntos=puntos+customer.getPoints();

                if (puntos>1000)
                {
                total=total.add(total.multiply(new BigDecimal(0.02)));
                purchase.setTotalFinal(total);
                }
                else
                    purchase.setTotalFinal(total);
                
		//customer.setPoint(customer.getPoints()+puntos);
		try {
			purchase.setStatus(PurchaseStatus.PENDING);
		} catch (ChangeStatusException e) {
			throw new RuntimeException("Error setting pending status");
		}
		
		return purchase;
	}
}
