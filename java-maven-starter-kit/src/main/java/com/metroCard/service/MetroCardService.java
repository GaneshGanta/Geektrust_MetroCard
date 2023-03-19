package com.metroCard.service;

import java.util.List;
import com.metroCard.exception.InSufficientBalanceException;
import com.metroCard.model.Stations;


public interface MetroCardService {
	
	public List<Stations> stats() throws InSufficientBalanceException;
	public void book(Integer indexNum, Integer currentBalance, Integer ticketPrice, String station, String type);
	public void update(String station, Integer amount, String type,Integer discount);
	public  Integer index(String card);
	public Integer getBalance(Integer index);
	public String console() throws InSufficientBalanceException;
	
}
