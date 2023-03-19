package com.metroCard.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.metroCard.exception.InSufficientBalanceException;
import com.metroCard.model.Balance;
import com.metroCard.model.Check;
import com.metroCard.model.Stations;

public class MetroCardServiceImpl implements MetroCardService {
	
	private static Integer seniorCitizenCharge=100;
	private static Integer adultCharge=200;
	private static Integer kidCharge=50;
	private static Integer serviceCharge=2;

	
	private List<Balance> listOfBalance;
	private List<Check> listOfCheck;
	private List<Stations> stationList = new ArrayList<>();
	
	
	
	//transforming the raw data of passengers into stastistical data.
	public List<Stations> stats() throws InSufficientBalanceException {
		
		for(int i=0;i< this.listOfCheck.size();i++) {
			
			Check obj = listOfCheck.get(i);
			
			String cardNum = obj.getMetroCardNo();
			
			//getting index of card if present...
			Integer cardStatus = index(cardNum);
			
			if(cardStatus==null) {
				throw new InSufficientBalanceException("Card Not Found");
			}
			
			Integer currentBalance = listOfBalance.get(cardStatus).getCardBalance();
			
			
			if(obj.getTypeOfPassenger().equals("ADULT")) {
				
				book(cardStatus, currentBalance, adultCharge, obj.getStationName(), "ADULT");
				
			}else if(obj.getTypeOfPassenger().equals("SENIOR_CITIZEN")) {
				
				book(cardStatus, currentBalance, seniorCitizenCharge, obj.getStationName(),"SENIOR_CITIZEN");
				
			}else if(obj.getTypeOfPassenger().equals("KID")) {
				book(cardStatus, currentBalance, kidCharge, obj.getStationName(), "KID");
				
			}
						
			
		}
		
		return stationList;
		
	}
	
	
	//booking the ticket to travel
	public void book(Integer indexNum, Integer currentBalance, Integer ticketPrice, String station, String type) { 
		
		//increasing passenger count
		listOfBalance.get(indexNum).setCount(listOfBalance.get(indexNum).getCount()+1);
		
		int count = listOfBalance.get(indexNum).getCount();
		int discount = 0;
		
		if(currentBalance<ticketPrice) {
			
			if(count%serviceCharge==0) {
				ticketPrice = ticketPrice/2;
				discount = ticketPrice;
			}
			listOfBalance.get(indexNum).setCardBalance(0);
			
			Integer requiredBalance = ticketPrice - currentBalance;
			
			Integer charge = (requiredBalance * serviceCharge)/100;
			
			update(station, ticketPrice+charge , type, discount );
			
			
		}else {
			
			if(count%serviceCharge==0) {
				ticketPrice = ticketPrice/serviceCharge;
				discount = ticketPrice;
			}
			
			listOfBalance.get(indexNum).setCardBalance(currentBalance-ticketPrice);
			update(station, ticketPrice , type ,discount);
		}
		
		
		
	}
	
	
	public void update(String station, Integer amount, String type,Integer discount) {
		
		Stations obj;
		
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
		}else if(type.equals("KID")){
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
	
	
	//returning the index of the metroCard list if found...
	public  Integer index(String card) {
		
		Integer in = null;
		
		for(int i=0;i<listOfBalance.size();i++) {
			if(listOfBalance.get(i).getMetroCardNo().equals(card)) {
				in=i;
				break;
			}
		}
		
		
		return in;
		
	}
	
	
	//getting the balance in the metroCard of the passenger
	public Integer getBalance(Integer index) {
		return listOfBalance.get(index).getCardBalance();
	}
	
	
	
	//final summary of the metroCard project
	public String console() throws InSufficientBalanceException {
		
		List<Stations> output= stats();
		
		String ans =" ";
		
		System.out.println("TOTAL_COLLECTION "+ "CENTRAL "+output.get(0).getTotalCollection()+" "+output.get(0).getTotalDiscount());
		ans += "TOTAL_COLLECTION "+ "CENTRAL "+output.get(0).getTotalCollection()+" "+output.get(0).getTotalDiscount()+"\n";

    	
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
    	ans+="PASSENGER_TYPE_SUMMARY\n";
    	
    	if(list.get(0).getValue()!=0) {
    		System.out.println(list.get(0).getKey()+" "+list.get(0).getValue());
    		ans+=list.get(0).getKey()+" "+list.get(1).getValue()+"\n";

    	}
    	if(list.get(1).getValue()!=0) {
    		System.out.println(list.get(1).getKey()+" "+list.get(1).getValue());
    		ans+=list.get(1).getKey()+" "+list.get(1).getValue()+"\n";

    	}
    	if(list.get(2).getValue()!=0) {
    		System.out.println(list.get(2).getKey()+" "+list.get(2).getValue());
    		ans+=list.get(2).getKey()+" "+list.get(1).getValue()+"\n";

    	}
    	
    	
    	System.out.println("TOTAL_COLLECTION "+ "AIRPORT "+output.get(1).getTotalCollection()+" "+output.get(1).getTotalDiscount());
    	ans += "TOTAL_COLLECTION "+ "AIRPORT "+output.get(1).getTotalCollection()+" "+output.get(1).getTotalDiscount()+"\n";

    	
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
    	ans+="PASSENGER_TYPE_SUMMARY\n";
    	
    	if(list2.get(0).getValue()!=0) {
    		System.out.println(list2.get(0).getKey()+" "+list2.get(0).getValue());
    		ans+=list2.get(0).getKey()+" "+list2.get(0).getValue()+"\n";

    	}
    	if(list2.get(1).getValue()!=0) {
    		System.out.println(list2.get(1).getKey()+" "+list2.get(1).getValue());
    		ans+=list2.get(1).getKey()+" "+list2.get(0).getValue()+"\n";

    	}
    	if(list2.get(2).getValue()!=0) {
    		System.out.println(list2.get(2).getKey()+" "+list2.get(2).getValue());
    		ans+=list2.get(1).getKey()+" "+list2.get(0).getValue()+"\n";

    	}
				
		return ans;
	}
	
	
	public MetroCardServiceImpl(List<Balance> balList, List<Check> checkList) {
		super();
		this.listOfBalance = balList;
		this.listOfCheck = checkList;
		this.stationList.add(new Stations("CENTRAL"));
		this.stationList.add(new Stations("AIRPORT"));
	}

}
