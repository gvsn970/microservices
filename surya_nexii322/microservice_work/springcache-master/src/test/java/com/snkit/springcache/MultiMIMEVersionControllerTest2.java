package com.snkit.springcache;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class	)
public class MultiMIMEVersionControllerTest2 {
	
	private MockMvc mockMvc;

	
	@InjectMocks
    private MultiMIMEVersionController multiMIMEVersionController;
	
	@Mock
	UserService userService;
	
	@Mock
	ObjectMapper objectMapper;
	
	JacksonTester<User> jsonTester;
	
	List<User> users = null;
	
	
	
	  User cacheUser ;
	
	@Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(multiMIMEVersionController)               
                .build();
        
        User u1 = new User("Sree","SoftwareEnginner");
		u1.setCompName("Hcl");	
		
		User u2 = new User("kollu","TraineeSoftwareEnginner");
		u2.setCompName("Tcs");
		
		users = Arrays.asList(u1 ,u2);
		objectMapper = new ObjectMapper();
		JacksonTester.initFields(this, objectMapper);
		cacheUser= new User("Sree","SoftwareEnginner");
		cacheUser.setCompName("Hcl");	
		
    }
	
	@Test
	public void testGetUsers() throws Exception {	
		
	    when(userService.getAllUser()).thenReturn(users);
	    
	    mockMvc.perform(get("/getUsers")
	    		.contentType("application/json")
	    		.accept("application/json"))
	    		.andExpect(status().isOk())
	    		.andExpect(jsonPath("$[0].name", is("Sree")))
	    		.andExpect(jsonPath("$[0].compName", is("Hcl")));
		
	}

	@Test
	public void testCacheableUser() throws  Exception {
		User u  =  new User("Sree","SoftwareEngineer");
		
		
		
		 when(userService.getByUser(u)).thenReturn(cacheUser); 
		 
		  mockMvc.perform(post("/cacheableUser")
				 .contentType("application/json")
		    		.accept("application/json")
				 .content(jsonTester.write(u).getJson()))
		 		.andExpect(status().isOk());
		 
		// 
		
		
		
	
		
		
		
	}

}
