package com.codebind;

import org.junit.runner.RunWith;
import java.util.ArrayList;
import org.junit.runners.Parameterized;
import static org.junit.Assert.assertEquals;
import org.junit.*;

import java.util.Collection;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



@RunWith(Parameterized.class)
public class NBA_test {
	private String inputName;
	private String expectedResult;
	private NBA_JUnit junit_nba;
	
	
	@Before
	public void initialize() {
		this.junit_nba = new NBA_JUnit();
	}

	
	public NBA_test(String inputName, String expectedResult) {
		this.inputName = inputName;
		this.expectedResult = expectedResult;
	}
	
	
	@Parameterized.Parameters
	public static Collection<Object[]> players() {
		
		ArrayList<Object[]> parameters = new ArrayList<Object[]>();
		
		try {
			
			File expectedResults = new File("target/test-classes/expectedResults.txt");
		    File inputNames = new File("target/test-classes/inputNames.txt");
		      
		    
		      Scanner readNames = new Scanner(inputNames);
		      Scanner readResults = new Scanner(expectedResults);
		      
		      
		      while (readNames.hasNextLine()) {
		        String name = readNames.nextLine();
		        String season = readResults.nextLine();
		        
		        parameters.add(new Object[] {name, season});
		        
		      }
		      
		      readNames.close();
		      readResults.close();
		      
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		return parameters;
	}
	
	
	@Test
	public void test() {
		System.out.println("Player Name: "+inputName);
		assertEquals(expectedResult, junit_nba.get3P(inputName));
	}
}
