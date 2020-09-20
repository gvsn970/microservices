package com.nexiilabs.emplyoeeproducer;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)

public class EmplyoeeServiceTest {
	

	EmployeeService employeeService;
	@Before
	public void init() {
		
	}

	@Test()
	public void testService() {

		assertEquals("onetwo", employeeService.listEmployee());
	} 

}
