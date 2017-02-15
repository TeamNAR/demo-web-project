package edu.cpp.sc580.controller;

//import junit.framework.Test;
import org.junit.Test;

import edu.cpp.cs580.controller.WebController;

import org.junit.Assert;


public class WebControllerTest {

	@Test
	public void testStreakCheck() {
		
	}
	
	@Test
	public void testCommonsMathLibraryExample(){
		
	}
	
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
