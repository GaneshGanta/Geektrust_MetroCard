package com.metroCard.model;

public class CheckIn {
	private String metroCard;
	private String passengerType;
	private String fromStation;
	
	
	public CheckIn() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "CheckIn [metroCard=" + metroCard + ", passengerType=" + passengerType + ", fromStation=" + fromStation
				+ "]";
	}
	public CheckIn(String metroCard, String passengerType, String fromStation) {
		super();
		this.metroCard = metroCard;
		this.passengerType = passengerType;
		this.fromStation = fromStation;
	}
	public String getMetroCard() {
		return metroCard;
	}
	public void setMetroCard(String metroCard) {
		this.metroCard = metroCard;
	}
	public String getPassengerType() {
		return passengerType;
	}
	public void setPassengerType(String passengerType) {
		this.passengerType = passengerType;
	}
	public String getFromStation() {
		return fromStation;
	}
	public void setFromStation(String fromStation) {
		this.fromStation = fromStation;
	}
	
	
	
	
	
	
}
