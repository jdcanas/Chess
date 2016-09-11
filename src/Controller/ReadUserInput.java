package Controller;

import java.util.Scanner;

public class ReadUserInput {
	
		public static String getVisibleInput(String prompt){
			Scanner reader = new Scanner(System.in);
			String line;
			
			System.out.println(prompt);
			line = reader.nextLine();  
			
			return line;
		}
		
}
