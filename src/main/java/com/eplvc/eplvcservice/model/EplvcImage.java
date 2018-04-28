package com.eplvc.eplvcservice.model;


import com.eplvc.eplvcservice.enums.CapturedStage;


public class EplvcImage {

	private String leadId;
	private String base64Image;
	private CapturedStage capturedStage;
	private boolean faceDetected;
	
	public String getLeadId() {
		return leadId;
	}
	public void setLeadId(String leadId) {
		this.leadId = leadId;
	}
	
	public String getBase64Image() {
		return base64Image;
	}
	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
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
