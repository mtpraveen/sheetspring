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
	@Column(name = "facebookurl")
	private String faceBookURL;
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
     * @return the faceBookURL
     */
	public String getFaceBookURL() {
		return faceBookURL;
	}
	/**
     * @param apiKey the faceBookURL to set
     */
	public void setFaceBookURL(String faceBookURL) {
		this.faceBookURL = faceBookURL;
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
	
	
	
}
