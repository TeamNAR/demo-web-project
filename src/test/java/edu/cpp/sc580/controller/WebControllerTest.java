package edu.cpp.sc580.controller;

//import junit.framework.Test;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import edu.cpp.cs580.controller.WebController;
import edu.cpp.cs580.data.User;

import org.junit.Assert;
import org.junit.Before;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class WebControllerTest {

	@Test
	public void testStreakCheck() {
		WebController myWebController = new WebController();
		String methodOutput = myWebController.streakCheackwithPOST(0, 0);
		Assert.assertEquals("Congartulations!! YOU DID IT!! :D",methodOutput);
	}

	@Test
	public void testCommonsMathLibraryExample(){
		WebController myWebController = new WebController();
		String methodOutput = myWebController.commonsMathLibraryExample();
		Assert.assertNotNull(methodOutput);
	}

/*	MockMvc not working properly. coming back to this later on 
  	private MockMvc mockMvc;
    @Autowired WebApplicationContext wac;
    
    
    @Before
    public void setup() {
        //this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }
    
	@Test
	public void testUpdateUser() throws Exception {
		//WebController myWebController = new WebController();
		//mockMvc = MockMvcBuilders.standaloneSetup(new DigipostSpringConnector()).build();
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		  this.mockMvc.perform(get("/cs580/ping").header("host", "localhost:8080"))
	       .andExpect(status().isOk());
	}*/

	// Amani
	@Test
	public void testTest() {

		WebController Message = new WebController();
		String result = Message.test();
		Assert.assertNotNull(result);

	}


	//Ahlam
	@Test
	public void testcommonGoal() {

		WebController msg = new WebController();
		String result = msg.commonGoal();
		Assert.assertEquals("Yes, this goal is common",result);

	}

}
