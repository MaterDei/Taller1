package co.edu.eafit.carritocompras.data;

import java.util.List;

public class Customer {

	private String code;
	private String name;
	private List<Purchase> purchases;
        private int points;

	public Customer(String code, String name, int points) {
		super();
		this.code = code;
		this.name = name;
                this.points=points;
	}

	public String getCode() {
		return code;
	}

	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

        public void setPoint(int p)
        {
                this.points=p;
        }

        public int getPoints(){
            return points;
        }

}
