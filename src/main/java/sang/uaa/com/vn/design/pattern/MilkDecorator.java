package sang.uaa.com.vn.design.pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sang.uaa.com.vn.service.Tea;

public class MilkDecorator extends TeaDecorator{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MilkDecorator.class);
	private double milk;
	
	public MilkDecorator(Tea tea) {
		super(tea);
	}

	@Override
	public double getCost() {
		double cost = tea.getCost();
		return cost + addMilk();
	}
	
	public double addMilk() {
		return milk;
	}
	
	public void setMilk(double milk) {
		LOGGER.info("set price milk: {}", milk);
		this.milk = milk;
	}
}
