package e2eTestingPWC_AID.reference;

import java.io.*;
import java.util.Scanner;

public class readCSVviaScanner {
	public static StringBuffer getTestIDs = null;
	public static void main(String[] args) throws Exception {
		//parsing a CSV file into Scanner class constructor  
		Scanner sc = new Scanner(new File("/Users/preetheldhose/eclipse-workspace/e2eTestingPWC_AID/csvDirectory/TestID.csv"));
		sc.useDelimiter(","); // sets the delimiter pattern
		while (sc.hasNext()) // returns a boolean value
		{
			System.out.print(sc.next()); // find and returns the next complete token from this scanner
			//getTestIDs.append(sc.next().toString());
		}
		sc.close(); // closes the scanner
		
		//System.out.println("TestID values : " + getTestIDs);
		
	}
}
