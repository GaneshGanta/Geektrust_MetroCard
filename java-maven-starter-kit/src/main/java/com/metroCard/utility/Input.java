package com.metroCard.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.metroCard.model.Balance;
import com.metroCard.model.Check;

public class Input {
	
	private String filePath;
	
	List<Balance> listOfBalance = new ArrayList<>();
	List<Check> listOfCheckIn = new ArrayList<>();
	
	public Input() {
		
	}

	public Input(String filePath) {
		super();
		this.filePath = filePath;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public List<Balance> getBalanceList(){
		return this.listOfBalance;
	}
	
	public List<Check> getCheckInList(){
		return this.listOfCheckIn;
	}
	
	
	public  void takeInput() {
		
		
		
		try {
			FileInputStream fis = new FileInputStream(filePath);
			Scanner sc = new Scanner(fis);
			String inputLine;
			while(sc.hasNextLine()) {
				
				inputLine = sc.nextLine();
				
				//excluding commented 2 lines
				if(inputLine.contains("/*") || inputLine.contains("*/")) {
					continue;
				}
				
				//break the loop when Print_Summary found
				if(inputLine.equals("PRINT_SUMMARY")){
					break;
				}
				
				//Actual Input Taking
				String[] inputArray = inputLine.split(" ");
				
				if(inputArray[0].equals("BALANCE")) {
					Balance obj = new Balance(inputArray[1], Integer.parseInt(inputArray[2]));
					listOfBalance.add(obj);
				}else if(inputArray[0].equals("CHECK_IN")) {
					Check obj = new Check(inputArray[1], inputArray[2], inputArray[3]);
					listOfCheckIn.add(obj);
				}
				
				
			}
			
			sc.close();
			
			//throws an exception when file path is not found...
		} catch (FileNotFoundException e) {
			
			System.out.println(e.getMessage());

		}
		
		
	}
	
	
	
}
