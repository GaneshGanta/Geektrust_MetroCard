package com.metroCard.model;

public class Check {
	
	
	private String metroCardNo;
	private String typeOfPassenger;
	private String stationName;
	
	
	public String getMetroCardNo() {
		return metroCardNo;
	}

	public void setMetroCardNo(String metroCardNo) {
		this.metroCardNo = metroCardNo;
	}

	public String getTypeOfPassenger() {
		return typeOfPassenger;
	}

	public void setTypeOfPassenger(String typeOfPassenger) {
		this.typeOfPassenger = typeOfPassenger;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	
	public Check() {
		
	}
	
	public Check(String metroCard, String passengerType, String fromStation) {
		super();
		this.metroCardNo = metroCard;
		this.typeOfPassenger = passengerType;
		this.stationName = fromStation;
	}

	@Override
	public String toString() {
		return "CheckIn [metroCardNo=" + metroCardNo + ", typeOfPassenger=" + typeOfPassenger + ", stationName="
				+ stationName + "]";
	}
	
	
	
	
}
