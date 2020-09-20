package com.nexii.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestMultipication {

	@Test
	void testMultipication() {
		JunitDemo test=new JunitDemo();
	
	assertEquals(12, test.multipication(3, 4));
	}
	

}
