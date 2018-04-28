package com.eplvc.eplvcservice.model;

import com.eplvc.eplvcservice.enums.Status;

public class LeadStatus {

	private String leadId;
	private Status status;
	public String getLeadId() {
		return leadId;
	}
	public void setLeadId(String leadId) {
		this.leadId = leadId;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
}
