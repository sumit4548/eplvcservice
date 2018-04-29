package com.eplvc.eplvcservice.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.eplvc.eplvcservice.enums.Status;


@Entity
@Table(name="Eplvc_Call_Log")
public class EplvcLead {
	
	@Id	
	private String leadId;
	private String leadName;
	private String productName;
	private String productType;
	private Double sumAssured;
	private Double premiumAmount;
	private String premiumFrequency;
	private String premiumTerm;
	private String policyTerm;
	private Status status;
	private Date createdAt;
	private Date lastUpdatedAt;

	public EplvcLead() {
		
	}
	
	public String getLeadId() {
		return leadId;
	}


	public void setLeadId(String LeadId) {
		leadId = LeadId;
	}


	public String getLeadName() {
		return leadName;
	}


	public void setLeadName(String LeadName) {
		leadName = LeadName;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getProductType() {
		return productType;
	}


	public void setProductType(String productType) {
		this.productType = productType;
	}


	public Double getSumAssured() {
		return sumAssured;
	}


	public void setSumAssured(Double sumAssured) {
		this.sumAssured = sumAssured;
	}


	public Double getPremiumAmount() {
		return premiumAmount;
	}


	public void setPremiumAmount(Double premiumAmount) {
		this.premiumAmount = premiumAmount;
	}


	public String getPremiumFrequency() {
		return premiumFrequency;
	}


	public void setPremiumFrequency(String premiumFrequency) {
		this.premiumFrequency = premiumFrequency;
	}


	public String getPremiumTerm() {
		return premiumTerm;
	}


	public void setPremiumTerm(String premiumTerm) {
		this.premiumTerm = premiumTerm;
	}


	public String getPolicyTerm() {
		return policyTerm;
	}


	public void setPolicyTerm(String policyTerm) {
		this.policyTerm = policyTerm;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getLastUpdatedAt() {
		return lastUpdatedAt;
	}

	public void setLastUpdatedAt(Date lastUpdatedAt) {
		this.lastUpdatedAt = lastUpdatedAt;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
}
