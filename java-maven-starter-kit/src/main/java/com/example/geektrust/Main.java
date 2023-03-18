package com.example.geektrust;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.metroCard.exception.BalanceException;
import com.metroCard.model.StationDetails;
import com.metroCard.service.MetroSummaryServiceImpl;
import com.metroCard.utility.InputReader;

public class Main {
    public static void main(String[] args) throws BalanceException {
        
        //Sample code to read from file passed as command line argument
        try {
            // the file to be opened for reading
        	if(args.length==0) {
        		throw new IOException("File Path is not Provided in the command line argument!!");
        	}
        	String filePath = args[0];
        	createSummary(filePath);
        	
        } catch (IOException e) {
        	e.printStackTrace();
        }
        
    }
    
    public static void createSummary(String filePath) throws BalanceException {
    	
    	InputReader ir = new InputReader(filePath);
    	ir.takeInput();
    	
    	MetroSummaryServiceImpl process = new MetroSummaryServiceImpl(ir.getBalanceList(), ir.getCheckInList());
    	process.console();
    	
    	
    	
    	
    }
    
    
    
}
