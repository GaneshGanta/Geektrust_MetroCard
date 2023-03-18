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
    	List<StationDetails> output= process.method1();
    	
    	System.out.println("TOTAL_COLLECTION "+ "CENTRAL "+output.get(0).getTotalCollection()+" "+output.get(0).getTotalDiscount());
    	
    	
    	Map<String,Integer> centralMap = new LinkedHashMap();
    	centralMap.put("ADULT",output.get(0).getAdultCount());
    	centralMap.put("KID",output.get(0).getKidCount());
    	centralMap.put("SENIOR_CITIZEN",output.get(0).getSeniorCount());
    	
    	Set<Entry<String,Integer>> set = centralMap.entrySet();
    	
    	List<Entry<String,Integer>> list = new ArrayList<>(set);
    	
    	
    	list.sort((o1, o2) -> {
    		if(o1.getValue()>o2.getValue()) {
    			return -1;
    		}else if(o1.getValue()>o2.getValue()) {
    			return 1;
    		}else {
    			return o1.getKey().compareTo(o2.getKey());
    		}
    	});

    	
    	System.out.println("PASSENGER_TYPE_SUMMARY");
    	if(list.get(0).getValue()!=0) {
    		System.out.println(list.get(0).getKey()+" "+list.get(0).getValue());
    	}
    	if(list.get(1).getValue()!=0) {
    		System.out.println(list.get(1).getKey()+" "+list.get(1).getValue());
    	}
    	if(list.get(2).getValue()!=0) {
    		System.out.println(list.get(2).getKey()+" "+list.get(2).getValue());
    	}
    	
    	
    	System.out.println("TOTAL_COLLECTION "+ "AIRPORT "+output.get(1).getTotalCollection()+" "+output.get(1).getTotalDiscount());
    	
    	
    	Map<String,Integer> airportMap = new LinkedHashMap();
    	airportMap.put("ADULT",output.get(1).getAdultCount());
    	airportMap.put("KID",output.get(1).getKidCount());
    	airportMap.put("SENIOR_CITIZEN",output.get(1).getSeniorCount());
    	
    	Set<Entry<String,Integer>> set2 = airportMap.entrySet();
    	
    	List<Entry<String,Integer>> list2 = new ArrayList<>(set2);
    	
    	
    	list2.sort((o1, o2) -> {
    		if(o1.getValue()>o2.getValue()) {
    			return -1;
    		}else if(o1.getValue()>o2.getValue()) {
    			return 1;
    		}else {
    			return o1.getKey().compareTo(o2.getKey());
    		}
    	});

    	
    	System.out.println("PASSENGER_TYPE_SUMMARY");
    	if(list2.get(0).getValue()!=0) {
    		System.out.println(list2.get(0).getKey()+" "+list2.get(0).getValue());
    	}
    	if(list2.get(1).getValue()!=0) {
    		System.out.println(list2.get(1).getKey()+" "+list2.get(1).getValue());
    	}
    	if(list2.get(2).getValue()!=0) {
    		System.out.println(list2.get(2).getKey()+" "+list2.get(2).getValue());
    	}
    	
    	
    	
    	
    }
    
    
    
}
