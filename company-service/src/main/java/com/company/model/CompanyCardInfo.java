package com.company.model;



public class CompanyCardInfo {

	private Integer companyId;
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

	public CompanyCardInfo( Integer companyId,String companyName, String location, Double ratings, Integer totalClients) {
		super();
		this.companyId=companyId;
		this.companyName = companyName;
		this.location = location;
		this.ratings = ratings;
		this.totalClients = totalClients;
	}
	
	public CompanyCardInfo()
	{
		
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
    
    
}