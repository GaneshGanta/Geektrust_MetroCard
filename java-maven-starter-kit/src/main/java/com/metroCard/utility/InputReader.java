package com.metroCard.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.metroCard.model.Balance;
import com.metroCard.model.CheckIn;

public class InputReader {
	private String filePath;
	
	List<Balance> balanceList = new ArrayList<>();
	List<CheckIn> checkInList = new ArrayList<>();
	
	public InputReader() {
		
	}

	public InputReader(String filePath) {
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
		return this.balanceList;
	}
	
	public List<CheckIn> getCheckInList(){
		return this.checkInList;
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
					balanceList.add(obj);
				}else {
					CheckIn obj = new CheckIn(inputArray[1], inputArray[2], inputArray[3]);
					checkInList.add(obj);
				}
				
				
			}
			
			sc.close();
			
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	
	
	
	
	
	
	
}
