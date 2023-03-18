package com.metroCard.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.metroCard.exception.BalanceException;
import com.metroCard.model.Balance;
import com.metroCard.model.CheckIn;
import com.metroCard.model.StationDetails;

public class MetroSummaryServiceImpl implements MetroSummaryService {
	
	
	List<Balance> balList;
	List<CheckIn> checkList;
	List<StationDetails> stationList = new ArrayList<>();
	
	
	
	public MetroSummaryServiceImpl(List<Balance> balList, List<CheckIn> checkList) {
		super();
		this.balList = balList;
		this.checkList = checkList;
		this.stationList.add(new StationDetails("CENTRAL"));
		this.stationList.add(new StationDetails("AIRPORT"));
	}
	
	
	
	public List<StationDetails> method1() throws BalanceException {
		
		for(int i=0;i< this.checkList.size();i++) {
			
			CheckIn obj = checkList.get(i);
			
			String cardNum = obj.getMetroCard();
			
			
			Integer balanceIndex = getBalanceIndex(cardNum);
			
			if(balanceIndex==null) {
				throw new BalanceException("Card Not Found");
			}
			
			Integer currBal = balList.get(balanceIndex).getBalance();
			
			
			if(obj.getPassengerType().equals("ADULT")) {
				processTicket(balanceIndex, currBal, 200, obj.getFromStation(), "ADULT");
				
			}else if(obj.getPassengerType().equals("SENIOR_CITIZEN")) {
				processTicket(balanceIndex, currBal, 100, obj.getFromStation(),"SENIOR_CITIZEN");
				
			}else if(obj.getPassengerType().equals("KID")) {
				
				processTicket(balanceIndex, currBal, 50, obj.getFromStation(), "KID");
			}
			
			
			
			
		}
		return stationList;
		
	}
	
	public void processTicket(Integer indexNum, Integer currBal, Integer ticketPrice, String station, String type) {
		
		//increasing passenger count
		balList.get(indexNum).setCount(balList.get(indexNum).getCount()+1);
		int count = balList.get(indexNum).getCount();
		int discount = 0;
		
		if(currBal<ticketPrice) {
			
			if(count%2==0) {
				ticketPrice = ticketPrice/2;
				discount = ticketPrice;
			}
			balList.get(indexNum).setBalance(0);
			
			Integer requiredBal = ticketPrice - currBal;
			
			Integer charge = (requiredBal * 2)/100;
			
			updateStation(station, ticketPrice+charge , type, discount );
			
			
		}else {
			
			if(count%2==0) {
				ticketPrice = ticketPrice/2;
				discount = ticketPrice;
			}
			
			balList.get(indexNum).setBalance(currBal-ticketPrice);
			updateStation(station, ticketPrice , type ,discount);
		}
		
		
		
	}
	
	
	public void updateStation(String station, Integer amount, String type,Integer discount) {
		
		StationDetails obj;
		
		if(station.equals("CENTRAL")) {
			obj = stationList.get(0);
		}else {
			obj = stationList.get(1);
		}
		
		obj.setTotalCollection(obj.getTotalCollection()+amount);
		
		obj.setTotalDiscount(obj.getTotalDiscount()+discount);
		
		if(type.equals("ADULT")) {
			obj.setAdultCount(obj.getAdultCount()+1);
		}else if(type.equals("SENIOR_CITIZEN")) {
			obj.setSeniorCount(obj.getSeniorCount()+1);
		}else {
			obj.setKidCount(obj.getKidCount()+1);
		}
		
		
		if(station.equals("CENTRAL")) {
			stationList.get(0).setAdultCount(obj.getAdultCount());
			stationList.get(0).setKidCount(obj.getKidCount());
			stationList.get(0).setSeniorCount(obj.getSeniorCount());
			stationList.get(0).setTotalCollection(obj.getTotalCollection());
			stationList.get(0).setTotalDiscount(obj.getTotalDiscount());
		}else {
			stationList.get(1).setAdultCount(obj.getAdultCount());
			stationList.get(1).setKidCount(obj.getKidCount());
			stationList.get(1).setSeniorCount(obj.getSeniorCount());
			stationList.get(1).setTotalCollection(obj.getTotalCollection());
			stationList.get(1).setTotalDiscount(obj.getTotalDiscount());
		}
		
		
	}
	
	
	public  Integer getBalanceIndex(String card) {
		Integer index = null;
		for(int i=0;i<balList.size();i++) {
			if(balList.get(i).getMetroCard().equals(card)) {
				index=i;
				break;
			}
		}
		
		
		return index;
		
	}
	
	
	
	public Integer getBalance(Integer index) {
		return balList.get(index).getBalance();
	}
	
	//final summary of the metroCard
	public void console() throws BalanceException {
		
		List<StationDetails> output= method1();
		
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
