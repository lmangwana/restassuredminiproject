package testCases;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainTest_ThreadLocal {
	
	
	/**
	 * This method is used to get the current date and time in a specific format.
	 *
	 * @return A string representing the current date and time in the "yyyy-MM-dd HH-mm-ss" format.
	 */
	public static String getFormat() {
	    // Create a Date object to get the current date and time
	    Date date = new Date();

	    // Create a SimpleDateFormat object with the desired date-time format
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");

	    // Format the current date and time and return the resulting string
	    return dateFormat.format(date);
	}

	public static void main(String[] args) {
			
		
		
		System.out.println();
	}

}
