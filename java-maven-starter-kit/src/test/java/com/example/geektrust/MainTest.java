package com.example.geektrust;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.metroCard.*;
import com.metroCard.exception.InSufficientBalanceException;

public class MainTest {
	
	public Main mainObj;
	@BeforeEach
	void setUp() {
		mainObj=new Main(); 
	}
	
	@Test
	public void input1() throws InSufficientBalanceException {
		String filePath="src/test/java/resources/input1.txt";
		
		String actualOutput = mainObj.summary(filePath);
		String expectedOutput="TOTAL_COLLECTION CENTRAL 300 0\n"
				+ "PASSENGER_TYPE_SUMMARY\n"
				+ "ADULT 1\n"
				+ "SENIOR_CITIZEN 1\n"
				+ "TOTAL_COLLECTION AIRPORT 403 100\n"
				+ "PASSENGER_TYPE_SUMMARY\n"
				+ "ADULT 2\n"
				+ "KID 2";
		
		assertEquals(actualOutput.trim(), expectedOutput);
	}
	
	
	
	@Test
	public void input3() throws InSufficientBalanceException {
		String filePath="sample_input/input1.txt";
		
		String actualOutput = mainObj.summary(filePath);
		String expectedOutput="TOTAL_COLLECTION CENTRAL 300 0\n"
				+ "PASSENGER_TYPE_SUMMARY\n"
				+ "ADULT 1\n"
				+ "SENIOR_CITIZEN 1\n"
				+ "TOTAL_COLLECTION AIRPORT 403 100\n"
				+ "PASSENGER_TYPE_SUMMARY\n"
				+ "ADULT 2\n"
				+ "KID 2";
		
		assertEquals(actualOutput.trim(), expectedOutput);
	}
	
	
	
}