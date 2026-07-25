package com.company.dto;


public class CompanyCardInfoDto {

    private String companyName;
    private String location;
    private Double ratings;
    private Integer totalClients;
	public String getCompanyName() {
		return companyName;
	}
	public String getLocation() {
		return location;
	}
	public Double getRatings() {
		return ratings;
	}
	public Integer getTotalClients() {
		return totalClients;
	}
	public CompanyCardInfoDto(String companyName, String location, Double ratings, Integer totalClients) {
		super();
		this.companyName = companyName;
		this.location = location;
		this.ratings = ratings;
		this.totalClients = totalClients;
	}
    
    
}