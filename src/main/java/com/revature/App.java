package com.revature;

import org.apache.log4j.Logger;



public class App {
	private static Logger log = Logger.getLogger(App.class);

	public static void main(String[] args) {
		log.info("Begin the Application");
		printWelcomeMessage();
        Driver.start();

	}
	private static void printWelcomeMessage() {
		
		System.out.println("|--------------------------|");
		System.out.println("|**************************|");
		System.out.println("|*    Welcome To RAM      *|");
		System.out.println("|*      Banking App       *|");
		System.out.println("|**************************|");
		System.out.println("|--------------------------|");
	
	}

}
