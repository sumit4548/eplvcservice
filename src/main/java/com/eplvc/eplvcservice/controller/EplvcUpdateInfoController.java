package com.eplvc.eplvcservice.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eplvc.eplvcservice.customexceptions.ServiceException;
import com.eplvc.eplvcservice.customexceptions.SessionExpiredException;
import com.eplvc.eplvcservice.entity.EplvcLead;
import com.eplvc.eplvcservice.model.EplvcImage;
import com.eplvc.eplvcservice.model.LeadStatus;
import com.eplvc.eplvcservice.service.EplvcImageService;
import com.eplvc.eplvcservice.service.EplvcLeadService;

@RestController
public class EplvcUpdateInfoController {
	
	@Autowired
	EplvcLeadService service;

	@Autowired
	EplvcImageService imageService;
	
	@RequestMapping(method=RequestMethod.POST ,value ="/updateLeadStatus")
	public void updateStatus(@RequestBody LeadStatus leadStatus,
			HttpServletRequest request) throws ServiceException, SessionExpiredException {
		
		if(request.getSession().isNew() || request.getSession().getAttribute("LeadID").toString().equalsIgnoreCase(leadStatus.getLeadId()))
			throw new SessionExpiredException("Session is not valid");
		
		EplvcLead lead = service.getLeadById(leadStatus.getLeadId());
		lead.setStatus(leadStatus.getStatus());
		lead.setLastUpdatedAt(new Date());
		
		service.updateLead(lead);	
	
	}
	
	@RequestMapping(method=RequestMethod.POST ,value ="/updateCapturedPhotoStatus")
	public void updateCapturedPhotoStatus(@RequestBody EplvcImage eplvcImage,
			HttpServletRequest request) throws IOException, SessionExpiredException {
		
		if(request.getSession().isNew() || request.getSession().getAttribute("LeadID").toString().equalsIgnoreCase(eplvcImage.getLeadId()))
			throw new SessionExpiredException("Session is not valid");
		
		imageService.saveImages(eplvcImage);
	
	}

}
