package com.metroCard.model;

public class StationDetails {
	
	private String stationName;
	private Integer totalCollection;
	private Integer totalDiscount;
	private Integer adultCount;
	private Integer seniorCount;
	private Integer kidCount;
	
	
	
	public StationDetails(String stationName) {
		super();
		this.stationName = stationName;
		this.adultCount =0 ;
		this.kidCount = 0;
		this.seniorCount = 0;
		this.totalCollection = 0;
		this.totalDiscount = 0;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public Integer getTotalCollection() {
		return totalCollection;
	}
	public void setTotalCollection(Integer totalCollection) {
		this.totalCollection = totalCollection;
	}
	public Integer getTotalDiscount() {
		return totalDiscount;
	}
	public void setTotalDiscount(Integer totalDiscount) {
		this.totalDiscount = totalDiscount;
	}
	public Integer getAdultCount() {
		return adultCount;
	}
	public void setAdultCount(Integer adultCount) {
		this.adultCount = adultCount;
	}
	public Integer getSeniorCount() {
		return seniorCount;
	}
	public void setSeniorCount(Integer seniorCount) {
		this.seniorCount = seniorCount;
	}
	public Integer getKidCount() {
		return kidCount;
	}
	public void setKidCount(Integer kidCount) {
		this.kidCount = kidCount;
	}
	public StationDetails(String stationName, Integer totalCollection, Integer totalDiscount, Integer adultCount,
			Integer seniorCount, Integer kidCount) {
		super();
		this.stationName = stationName;
		this.totalCollection = totalCollection;
		this.totalDiscount = totalDiscount;
		this.adultCount = adultCount;
		this.seniorCount = seniorCount;
		this.kidCount = kidCount;
	}
	public StationDetails() {
		
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "StationDetails [stationName=" + stationName + ", totalCollection=" + totalCollection
				+ ", totalDiscount=" + totalDiscount + ", adultCount=" + adultCount + ", seniorCount=" + seniorCount
				+ ", kidCount=" + kidCount + "]";
	}
	
	
	
	

}
