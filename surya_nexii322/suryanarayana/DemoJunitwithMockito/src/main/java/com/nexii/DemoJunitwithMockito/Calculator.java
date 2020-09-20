package com.nexii.DemoJunitwithMockito;

public class Calculator {

	CalculatorService service;

	public int perfome(int j, int i) {
		return service.add(i, j)*i;
	}
}