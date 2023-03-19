package com.example.geektrust;

import java.io.IOException;
import com.metroCard.exception.InSufficientBalanceException;
import com.metroCard.service.MetroCardServiceImpl;
import com.metroCard.utility.Input;

public class Main {
    public static void main(String[] args) throws InSufficientBalanceException {
        
        try {
            // the file to be opened for reading
        	if(args.length==0) {
        		
        		throw new IOException("File Path is not provided in the command line argument!");
        	}
        	String filePath = args[0];
        	
        	summary(filePath);
        	
        } catch (IOException e) {
        	
        	e.printStackTrace();
        	
        }
        
    }
    
    public static String summary(String filePath) throws InSufficientBalanceException {
    	
    	//sending the file path to Input class to read the input file...
    	Input ir = new Input(filePath);
    	ir.takeInput();
    	    	
    	MetroCardServiceImpl process = new MetroCardServiceImpl(ir.getBalanceList(), ir.getCheckInList());
    	
    	//printing the summary of the passengers...
    	String output = process.console();
    	
    	return output;
    	
    	
    }
    
    
    
}
