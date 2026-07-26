package com.company.model;



public class CompanyCardInfo {

    private String companyName;

    private String location;

    private Double ratings;

    private Integer totalClients;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Double getRatings() {
		return ratings;
	}

	public void setRatings(Double ratings) {
		this.ratings = ratings;
	}

	public Integer getTotalClients() {
		return totalClients;
	}

	public void setTotalClients(Integer totalClients) {
		this.totalClients = totalClients;
	}

	public CompanyCardInfo(String companyName, String location, Double ratings, Integer totalClients) {
		super();
		this.companyName = companyName;
		this.location = location;
		this.ratings = ratings;
		this.totalClients = totalClients;
	}
	
	public CompanyCardInfo()
	{
		
	}
    
    
}