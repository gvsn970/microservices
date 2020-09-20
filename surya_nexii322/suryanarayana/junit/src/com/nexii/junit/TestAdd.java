package com.nexii.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestAdd {

	@Test
	void add() {
		JunitDemo test=new JunitDemo();
	
	assertEquals(7, test.add(3, 4));
	}
}
