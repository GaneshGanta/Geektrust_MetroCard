package com.metroCard.model;

public class Balance {
	
	private String metroCard;
	private Integer cardBalance;
	private Integer count;
	
	
	
	public Integer getCount() {
		return count;
	}


	public void setCount(Integer count) {
		this.count = count;
	}


	public Balance(String metroCard, Integer cardBalance, Integer count) {
		super();
		this.metroCard = metroCard;
		this.cardBalance = cardBalance;
		this.count = count;
	}


	public Balance() {
		
		// TODO Auto-generated constructor stub
	}


	public Balance(String metroCard, Integer cardBalance) {
		super();
		this.metroCard = metroCard;
		this.cardBalance = cardBalance;
		this.count =0 ;
	}


	public String getMetroCard() {
		return metroCard;
	}


	public void setMetroCard(String metroCard) {
		this.metroCard = metroCard;
	}


	public Integer getBalance() {
		return cardBalance;
	}


	public void setBalance(Integer balance) {
		this.cardBalance = balance;
	}


	@Override
	public String toString() {
		return "Balance [metroCard=" + metroCard + ", cardBalance=" + cardBalance + ", count=" + count + "]";
	}


	
	

}
