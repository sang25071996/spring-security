package sang.uaa.com.vn.design.pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sang.uaa.com.vn.service.Tea;

public class ToppingDecorator extends TeaDecorator {
	private static final Logger LOGGER = LoggerFactory.getLogger(ToppingDecorator.class);
	private double topping;
	
	public ToppingDecorator(Tea tea) {
		super(tea);
	}
	
	@Override
	public double getCost() {
		double cost = tea.getCost();
		return cost + addTopping();
	}
	
	public double addTopping() {
		return topping;
	}
	
	public void setTopping(double topping) {
		LOGGER.info("set price topping: {}", topping);
		this.topping = topping;
	}
}
