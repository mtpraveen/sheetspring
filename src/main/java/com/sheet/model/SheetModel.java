package com.sheet.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "details")
public class SheetModel {
	
	
	@Id
	@GenericGenerator(name = "gene", strategy = "increment")
	@GeneratedValue(generator = "gene")
	private int enggId;
	@Column(name = "timestamp")
	private String timestamp;
	@Column(name = "name")
	private String name;
	@Column(name = "email")
	private String email;
	@Column(name = "company")
	private String company;
	@Column(name = "profilepic")
	private String profilePicture;
	@Column(name = "domain")
	private String domain;
	@Column(name = "hiringCity")
	private String hiringCity;
	@Column(name = "companyLocation")
	private String companyLocation;
	@Column(name = "enggStatus")
	private String enggStatus;
	
	public int getEnggId() {
		return enggId;
	}
	public void setEnggId(int enggId) {
		this.enggId = enggId;
	}
	/**
     * @return the name
     */
	public String getName() {
		return name;
	}
	/**
     * @param apiKey the name to set
     */
	public void setName(String name) {
		this.name = name;
	}
	/**
     * @return the email
     */
	public String getEmail() {
		return email;
	}
	/**
     * @param apiKemailey the name to set
     */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
     * @return the company
     */
	public String getCompany() {
		return company;
	}
	/**
     * @param company the name to set
     */
	public void setCompany(String company) {
		this.company = company;
	}
	
	/**
     * @return the timestamp
     */
	public String getTimestamp() {
		return timestamp;
	}
	/**
     * @param apiKey the timestamp to set
     */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	/**
     * @return the ProfilePicture
     */
	public String getProfilePicture() {
		return profilePicture;
	}
	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
	/**
     * @return domain the name
     */
	public String getDomain() {
		return domain;
	}
	/**
     * @param domain the name to set
     */
	
	public void setDomain(String domain) {
		this.domain = domain;
	}
	/**
     * @return city the name
     */
	public String getHiringCity() {
		return hiringCity;
	}
	/**
     * @param city the name to set
     */
	public void setHiringCity(String hiringCity) {
		this.hiringCity = hiringCity;
	}
	/**
     * @return company location the name
     */
	public String getCompanyLocation() {
		return companyLocation;
	}
	/**
     * @param companyLocation the name to set
     */
	public void setCompanyLocation(String companyLocation) {
		this.companyLocation = companyLocation;
	}
	/**
     * @return enggStatus
     */
	public String getEnggStatus() {
		return enggStatus;
	}
	/**
     * @param enggStatus the name to set
     */
	public void setEnggStatus(String enggStatus) {
		this.enggStatus = enggStatus;
	}
	
	
	
	
}
