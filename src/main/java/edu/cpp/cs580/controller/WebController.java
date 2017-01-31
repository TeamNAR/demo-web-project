package edu.cpp.cs580.controller;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.io.IOUtils;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import edu.cpp.cs580.App;
import edu.cpp.cs580.data.User;
import edu.cpp.cs580.data.provider.UserManager;


import java.util.Arrays;
//import java.util.ArrayList;


//package com.javarticles.guava;

import java.util.ArrayList;
//import java.util.Arrays;
import java.util.Collection;
//import java.util.List;
import com.google.common.base.Function;
import com.google.common.collect.Collections2;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * This is the controller used by Spring framework.
 * <p>
 * The basic function of this controller is to map
 * each HTTP API Path to the correspondent method.
 *
 */

@RestController
public class WebController {

	/**
	 * When the class instance is annotated with
	 * {@link Autowired}, it will be looking for the actual
	 * instance from the defined beans.
	 * <p>
	 * In our project, all the beans are defined in
	 * the {@link App} class.
	 */
	@Autowired
	private UserManager userManager;

	/**
	 * This is a simple example of how the HTTP API works.
	 * It returns a String "OK" in the HTTP response.
	 * To try it, run the web application locally,
	 * in your web browser, type the link:
	 * 	http://localhost:8080/cs580/ping
	 */
	@RequestMapping(value = "/cs580/ping", method = RequestMethod.GET)
	String healthCheck() {
		// You can replace this with other string,
		// and run the application locally to check your changes
		// with the URL: http://localhost:8080/
		return "OK-CS580";
	}

	/**
	 *  simple GET method with static parameters 
	 * 	http://localhost:8080/cs580/streak
	 */
	@RequestMapping(value = "/cs580/streak", method = RequestMethod.GET)
	String streakCheack() {
		int goal = 0;
		int streak = 0;

		String message;
		if(streak >= goal){
			message = "Congartulations!! YOU DID IT!! :D";
		}
		else{
			message = "Not yet! Sorry :(";
		}
		return message;
	}


	/**
	 *  same as previous method but with HTTP POST 
	 * 	http://localhost:8080/cs580/streakpost
	 */
	@RequestMapping(value = "/cs580/streakpost", method = RequestMethod.POST)
	String streakCheackwithPOST(@RequestParam("goal") int goal,
			@RequestParam("streak") int streak) {

		String message;
		if(streak >= goal){
			message = "Congartulations!! YOU DID IT!! :D";
		}
		else{
			message = "Not yet! Sorry :(";
		}
		return message;
	}

	/**
	 *  A4 -- HTTP API method using the library Common Math 
	 * 	http://localhost:8080/commonMath
	 */
	@RequestMapping(value = "/commonMath", method = RequestMethod.GET)
	String commonsMathLibraryExample() {
		//from http://stackoverflow.com/questions/15306207/standard-deviation-with-apache-commons-math
		
	    double[] v = {1.0, 2.0, 3.0, 4.0, 5.0};
	    StandardDeviation sd = new StandardDeviation(false);
	    sd.evaluate(v);

	    StandardDeviation sd2 = new StandardDeviation();
	    sd2.evaluate(v);
	    
		String message = "Using Apache Commons Math Liabray calculations on the array {1.0, 2.0, 3.0, 4.0, 5.0}"
				+ "were done; such as, The Population Standard deviation = " + sd.evaluate(v) + 
				", and The Standard Deviation = " +  sd2.evaluate(v);

		return message;
	}
	

	/**
	 *  GET method 
	 * 	http://localhost:8080/cs580/commongoal
	 */

	@RequestMapping(value = "/cs580/commongoal", method = RequestMethod.GET)
	String commonGoal() {
		List<String> commongoals = Arrays.asList("Reading", "work out", "volunteer", "increase income");

		boolean contains = commongoals.contains("Reading");

		String message;
		if(contains){
			message = "Yes, this goal is common";
		}
		else{
			message = "No, this is not a common goal";
		}
		return message;
	}
	
	/**
	 *  A4 --  jsoup library 
	 * 	http://localhost:8080/jsoup
	 *Ahlam
	 */
	@RequestMapping(value = "/ jsoup", method = RequestMethod.GET)
	String  jsoupLibraryExample() {
		
		Document doc;
        try {

            //get all images
            doc = Jsoup.connect("http://yahoo.com").get();
            Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
            for (Element image : images) {

                System.out.println("\nsrc : " + image.attr("src"));
                System.out.println("height : " + image.attr("height"));
                System.out.println("width : " + image.attr("width"));
                System.out.println("alt : " + image.attr("alt"));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
	    
		String message = "Done";

		return message;
	}

	/**
	 * A4 -- Using Commons IO Library
	 * 
	 * @throws IOException 
	 * @throws MalformedURLException 
	 */
	@RequestMapping(value = "/commonsIO", method = RequestMethod.GET)
	String  commonsIOLibraryExample() throws MalformedURLException, IOException {
		String message = null;
		
		InputStream in = new URL( "http://commons.apache.org" ).openStream();
		 try {
		   System.out.println( in.toString() );
		   message = in.toString();
		 } finally {
		   IOUtils.closeQuietly(in);
		 }

		return message;	 
	}

	/**
	 *  
	 * 	http://localhost:8080/cs580/timer
	 */

	Timer t;
	String message;
	@RequestMapping(value = "/cs580/timer", method = RequestMethod.POST)
	String timerOut(@RequestParam("time") int seconds) {


		System.out.println("the task is scheduled for 10 seconds ");
		System.out.println(new Date());
		System.out.println("Starting now ");
		message = "the program has run for " + seconds + " seconds from " + new Date() ;

		t = new Timer();
		t.schedule(new RemindTask(), seconds*1000);

		return message;
	}
	
	class RemindTask extends TimerTask {
		public void run() {
			System.out.format("OPPS time's up ");
			t.cancel(); 
			System.out.format("%n the new time is ");
			System.out.println(new Date());
			
			//message = " until " + new Date();
			
		}
	}
	
	/**
	 *  
	 * 	http://localhost:8080/cs580/CheckToDoList
	 *  Amani
	 */ 
	@RequestMapping(value = "/cs580/CheckToDoList", method = RequestMethod.GET)
	String test() {
		int count = 0;
		String message;
		String toDoList = null;
		
		if (toDoList == "yes"){
			count++;
			message = "Congartulations .. You complete the list";
		}
		
		else
			message = "You did not complete the list";

		return message;
	}

	/**
	 *  A4 -- HTTP API method using the library Google Guava 
	 * 	http://localhost:8080/GoogleGuava
	 *  Amani
	 */
	@RequestMapping(value = "/GoogleGuava", method = RequestMethod.GET)
	String GoogleGuavaLibraryExample() {
		
		
		List inputList = Arrays.asList("Hi There", "I am", "testing functions");
        System.out.println(inputList);
        Function<String, String[]> splitByWords = new Function<String, String[]>(){

            public String[] apply(String input) {
                return input.split(" ");
            }};
            
        Collection<String[]> wordsList = Collections2.transform(inputList, splitByWords);
        List allWords = new ArrayList();
        for (String[] words : wordsList) {
            allWords.addAll(Arrays.asList(words));
        }
        System.out.println(allWords);
        
        String message = "Split By Words";

		return message;
	}
	
	/**
	 * This is a simple example of how to use a data manager
	 * to retrieve the data and return it as an HTTP response.
	 *
	 * <p>
	 * Note, when it returns from the Spring, it will be
	 * automatically converted to JSON format.
	 * <p>
	 * Try it in your web browser:
	 * 	http://localhost:8080/cs580/user/user101
	 */
	@RequestMapping(value = "/cs580/user/{userId}", method = RequestMethod.GET)
	User getUser(@PathVariable("userId") String userId) {
		User user = userManager.getUser(userId);
		return user;
	}

	/**
	 * This is an example of sending an HTTP POST request to
	 * update a user's information (or create the user if not
	 * exists before).
	 *
	 * You can test this with a HTTP client by sending
	 *  http://localhost:8080/cs580/user/user101
	 *  	name=John major=CS
	 *
	 * Note, the URL will not work directly in browser, because
	 * it is not a GET request. You need to use a tool such as
	 * curl.
	 *
	 * @param id
	 * @param name
	 * @param major
	 * @return
	 */
	@RequestMapping(value = "/cs580/user/{userId}", method = RequestMethod.POST)
	User updateUser(
			@PathVariable("userId") String id,
			@RequestParam("name") String name,
			@RequestParam(value = "major", required = false) String major) {
		User user = new User();
		user.setId(id);
		user.setMajor(major);
		user.setName(name);
		userManager.updateUser(user);
		return user;
	}

	/**
	 * This API deletes the user. It uses HTTP DELETE method.
	 *
	 * @param userId
	 */
	@RequestMapping(value = "/cs580/user/{userId}", method = RequestMethod.DELETE)
	void deleteUser(
			@PathVariable("userId") String userId) {
		userManager.deleteUser(userId);
	}

	/**
	 * This API lists all the users in the current database.
	 *
	 * @return
	 */
	@RequestMapping(value = "/cs580/users/list", method = RequestMethod.GET)
	List<User> listAllUsers() {
		return userManager.listAllUsers();
	}

	/*********** Web UI Test Utility **********/
	/**
	 * This method provide a simple web UI for you to test the different
	 * functionalities used in this web service.
	 */
	@RequestMapping(value = "/cs580/home", method = RequestMethod.GET)
	ModelAndView getUserHomepage() {
		ModelAndView modelAndView = new ModelAndView("home");
		modelAndView.addObject("users", listAllUsers());
		return modelAndView;
	}

}