package sang.uaa.com.vn.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sang.uaa.com.vn.service.Tea;

public class OlongTea implements Tea{

	private static final Logger LOGGER = LoggerFactory.getLogger(OlongTea.class);
	private double price;
	private int size;
	
	public void setPrice(double price) {
		LOGGER.info("OlongTea set price: {}", price);
		this.price = price;
	}
	
	@Override
	public double getCost() {
		
		return price;
	}

	public int getSize() {
		return this.size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
}
