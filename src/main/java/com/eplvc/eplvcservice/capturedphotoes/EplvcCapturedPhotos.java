package com.eplvc.eplvcservice.capturedphotoes;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class EplvcCapturedPhotos {
	
	@Id
	private Long id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	private String leadId;
	private String base64ImagePath;
	private CapturedStage capturedStage;
	private boolean faceDetected;
	
	public String getLeadId() {
		return leadId;
	}
	public void setLeadId(String leadId) {
		this.leadId = leadId;
	}
	public String getBase64ImagePath() {
		return base64ImagePath;
	}
	public void setBase64ImagePath(String base64ImagePath) {
		this.base64ImagePath = base64ImagePath;
	}
	public CapturedStage getCapturedStage() {
		return capturedStage;
	}
	public void setCapturedStage(CapturedStage capturedStage) {
		this.capturedStage = capturedStage;
	}
	public boolean isFaceDetected() {
		return faceDetected;
	}
	public void setFaceDetected(boolean faceDetected) {
		this.faceDetected = faceDetected;
	}

}
