package com.nexiilabs.emplyoeeproducer;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(MockitoJUnitRunner.class)
public class EmplyoeeControllerTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private EmplyeeController emplyeeController;
	
	@Mock
	EmployeeService employeeService;
	
	@Mock
	ObjectMapper objectMapper;
	
JacksonTester<Employee> jsonTester;
	
	List<Employee> users = null;
	
	Employee e1;
	
	@Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(emplyeeController)               
                .build();
        
        Employee u1 = new Employee("surya","SoftwareEnginner");
		u1.setName("surya");
		u1.setDesg("developer");
		
		Employee u2 = new Employee("kollu","TraineeSoftwareEnginner");
		u2.setName("venkat");
		u2.setDesg("developer-2");
		
		users = Arrays.asList(u1 ,u2);
		objectMapper = new ObjectMapper();
		JacksonTester.initFields(this, objectMapper);
	/*	u1= new Employee("Sree","SoftwareEnginner");
		u1.setName("surya");*/	
		
    }
	@Test
	public void testGetUsers() throws Exception {	
		
	    when(employeeService.listEmployee()).thenReturn(users);
	    
	    mockMvc.perform(get("/listEmp")
	    		.contentType("application/json")
	    		.accept("application/json"))
	    		.andExpect(status().isOk())
	    		.andExpect(jsonPath("$[0].name", is("surya")))
	    		.andExpect(jsonPath("$[0].desg", is("developer")));
		
	}

}
