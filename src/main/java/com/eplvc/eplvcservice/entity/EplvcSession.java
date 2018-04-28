package com.eplvc.eplvcservice.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EplvcSession {
	
	@Id
	String leadId;
	String sessionId;
	Date lastUpdatedAt;
	
	public String getLeadId() {
		return leadId;
	}
	public void setLeadId(String LeadId) {
		leadId = LeadId;
	}
	public String getSessionID() {
		return sessionId;
	}
	public void setSessionID(String SessionID) {
		sessionId = SessionID;
	}
	public Date getLastUpdatedAt() {
		return lastUpdatedAt;
	}
	public void setLastUpdatedAt(Date LastUpdatedAt) {
		lastUpdatedAt = LastUpdatedAt;
	}

}
