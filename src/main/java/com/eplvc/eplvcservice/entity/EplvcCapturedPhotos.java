package com.eplvc.eplvcservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.transaction.annotation.Transactional;

import com.eplvc.eplvcservice.enums.CapturedStage;

@Entity
public class EplvcCapturedPhotos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String leadId;
	private String base64ImagePath;
	private CapturedStage capturedStage;
	private boolean faceDetected;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
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
