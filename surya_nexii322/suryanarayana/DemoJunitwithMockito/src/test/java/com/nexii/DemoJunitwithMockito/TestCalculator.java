package com.nexii.DemoJunitwithMockito;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestCalculator {

	Calculator c=new Calculator();
	@Test
	public void testAdd() {
		
		assertEquals(10, c.perfome(2, 3));
	}
}
