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
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getFaceBookURL() {
		return faceBookURL;
	}
	public void setFaceBookURL(String faceBookURL) {
		this.faceBookURL = faceBookURL;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	
	
}
