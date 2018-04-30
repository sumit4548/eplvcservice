package com.eplvc.eplvcservice.controller;

import java.util.ArrayList;
import java.util.Date;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import sun.misc.BASE64Decoder;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eplvc.eplvcservice.entity.EplvcLead;
import com.eplvc.eplvcservice.service.EplvcLeadService;
import com.eplvc.eplvcservice.enums.Status;

@Controller
public class LeadController {
	
	@Autowired
	EplvcLeadService service;

	@RequestMapping("/")
	public String validateAndSaveLead (
			@RequestParam("leadID") String leadId,
			@RequestParam("leadName") String leadName,
			@RequestParam("productName") String productName,
			@RequestParam("productType") String productType,
			@RequestParam("sumAssured") Double sumAssured,
			@RequestParam("premiumAmount") Double premiumAmount,
			@RequestParam("premiumFrequency") String premiumFrequency,
			@RequestParam("premiumTerm") String premiumTerm,
			@RequestParam("policyTerm") String policyTerm,
			HttpServletRequest request
			) throws Exception{
		
		EplvcLead lead = new EplvcLead();
		
		lead.setLeadId(leadId);
		lead.setLeadName(leadName);
		lead.setProductName(productName);
		lead.setProductType(productType);
		lead.setSumAssured(sumAssured);
		lead.setPremiumAmount(premiumAmount);
		lead.setPremiumFrequency(premiumFrequency);
		lead.setPremiumTerm(premiumTerm);
		lead.setPolicyTerm(policyTerm);
		lead.setStatus(Status.LOADED);
		lead.setCreatedAt(new Date());
		lead.setLastUpdatedAt(new Date());
		
		if(service.validateEplvcLead(lead)==false) 
			return "ValidationFailed"; // retrun error page.
			
		if(service.isProcessCompleted(lead)==true) {
			System.out.println("User has alredy completed Process"); // return thank you page.
			return "ProcessAlreadyCompleted";
		}
		
		request.getSession().setAttribute("LeadID", lead.getLeadId());
		
		service.addLead(lead);
		
		return "WelcomePage";
		
	}
	
	
}
