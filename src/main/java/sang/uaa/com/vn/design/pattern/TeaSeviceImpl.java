package sang.uaa.com.vn.design.pattern;

public class TeaSeviceImpl {
	
	private OlongTea olongTea;
	
	public TeaSeviceImpl() {
		this.olongTea = new OlongTea();
	}
	public double makeOlong() {
		
		this.olongTea.setPrice(15000);
		MilkDecorator milkDecorator = new MilkDecorator(olongTea);
		milkDecorator.setMilk(5000);
		ToppingDecorator toppingDecorator = new ToppingDecorator(milkDecorator);
		toppingDecorator.setTopping(10000);
		return toppingDecorator.getCost();
	}
}
