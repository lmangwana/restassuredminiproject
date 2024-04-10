package testCases;

import java.io.IOException;

import base.BasePage;

public class MainTest extends BasePage{

	public MainTest() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}


	public static void main(String[] args) throws IOException 
	{
		// TODO Auto-generated method stub
		
		MainTest obj = new MainTest();
		String number = obj.getRandomPhoneNumber();
		
		
		
		String name = "hamburger";
		int newNumber = name.length();
		
		String newString = name.substring(3,7);
		
		
		
		/*
		 * System.out.println(name.indexOf('m')); System.out.println(newNumber);
		 * System.out.println(newString);
		 */
		
		//randomNumber check
		
		 
		 for(int i=0; i<5; i++)
		 {
			 int randomGender = (int)((Math.random())*2);
			 System.out.println(randomGender);
		 }
		 

	}

}
