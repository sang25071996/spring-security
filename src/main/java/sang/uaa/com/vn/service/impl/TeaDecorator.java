package sang.uaa.com.vn.service.impl;

import sang.uaa.com.vn.service.Tea;

public abstract class TeaDecorator implements Tea {
	
	protected Tea tea;
	
	public TeaDecorator(Tea tea) {
		this.tea = tea;
	}
	
	public Tea getTea() {
		return tea;
	}
	
	public void setTea(Tea tea) {
		this.tea = tea;
	}
	
}
