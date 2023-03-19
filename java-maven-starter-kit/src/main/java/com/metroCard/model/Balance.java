package com.metroCard.model;

public class Balance {
	
	private String metroCardNo;
	private Integer cardBalance;
	private Integer count;
	
	
	public String getMetroCardNo() {
		return metroCardNo;
	}

	public void setMetroCardNo(String metroCardNo) {
		this.metroCardNo = metroCardNo;
	}

	public Integer getCardBalance() {
		return cardBalance;
	}

	public void setCardBalance(Integer cardBalance) {
		this.cardBalance = cardBalance;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Balance() {
		
	}

	public Balance(String metroCard, Integer cardBalance, Integer count) {
		super();
		this.metroCardNo = metroCard;
		this.cardBalance = cardBalance;
		this.count = count;
	}
	
	public Balance(String metroCard, Integer cardBalance) {
		super();
		this.metroCardNo = metroCard;
		this.cardBalance = cardBalance;
		this.count =0 ;
	}

	@Override
	public String toString() {
		return "Balance [metroCardNo=" + metroCardNo + ", cardBalance=" + cardBalance + ", count=" + count + "]";
	}
	
	

}
