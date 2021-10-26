package sang.uaa.com.vn.design.pattern;

import sang.uaa.com.vn.service.Tea;

public class BlackTea implements Tea{

	private double price;
	
	private String description;
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	@Override
	public double getCost() {
		
		return price;
	}
	
	public String getDescription() {
		return description;
	}
}