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
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eplvc.eplvcservice.entity.EplvcCapturedPhotos;
import com.eplvc.eplvcservice.entity.EplvcLead;
import com.eplvc.eplvcservice.entity.EplvcSession;
import com.eplvc.eplvcservice.repositories.EplvcImageRepo;
import com.eplvc.eplvcservice.service.EplvcLeadService;
import com.eplvc.eplvcservice.enums.Status;
import com.eplvc.eplvcservice.model.EplvcImage;
import com.eplvc.eplvcservice.model.LeadStatus;

@RestController
public class LeadController {
	
	@Autowired
	EplvcLeadService service;

	@Autowired
	EplvcImageRepo imageRepo;
	
	private String basePath = "/Users/sumitagrawal/ICICI/Eplvc-Images/";
	
	@RequestMapping("/")
	public String validateAndSaveLead(
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
			){
		
		
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
		lead.setStatus(Status.SERVED);
		lead.setLastUpdatedAt(new Date());
		
		
		if(service.validateEplvcLead(lead)==false) 
			System.out.println("Validation Not SuccessFul"); // retrun error page.
			
		if(service.isProcessCompleted(lead)==true) {
			System.out.println("User has alredy completed Process"); // return thank you page.
		}
		
		service.addLead(lead);
		
		return "WelcomePage";
		
	}
	
	@RequestMapping("/getAllLeads")
	public List<EplvcLead> returnAllLeads() {
		return service.getAllLeads();	
	}
	
	
	@RequestMapping("/getAllPhotoes")
	public List<EplvcCapturedPhotos> returnAllPhotoes() {
		 
		List<EplvcCapturedPhotos> lstImageList = new ArrayList<EplvcCapturedPhotos>();
		
		for(EplvcCapturedPhotos photo: imageRepo.findAll()) {
			lstImageList.add(photo);
		}
		
		return lstImageList;
		
	}
	
	
	@RequestMapping(method=RequestMethod.POST ,value ="/updateLeadStatus")
	public void updateStatus(@RequestBody LeadStatus leadStatus) {
		
		EplvcLead lead = service.getLeadById(leadStatus.getLeadId());
		lead.setStatus(leadStatus.getStatus());
		lead.setLastUpdatedAt(new Date());
		
		service.addLead(lead);	
	}
	
	@RequestMapping(method=RequestMethod.POST ,value ="/updateCapturedPhotoStatus")
	public void updateCapturedPhotoStatus(@RequestBody EplvcImage eplvcImage) throws IOException {
		
		@SuppressWarnings("restriction")
		BASE64Decoder decoder = new BASE64Decoder();
		@SuppressWarnings("restriction")
		byte[] imageByte = decoder.decodeBuffer(eplvcImage.getBase64Image());
		ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
		BufferedImage image = ImageIO.read(bis);
		bis.close();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		
		
		File outputfile = new File(basePath+eplvcImage.getLeadId()+"_"+sdf.format(new Date())+".png");
		
		ImageIO.write(image, "png", outputfile);
		
		EplvcCapturedPhotos ecp = new EplvcCapturedPhotos();
		
		ecp.setId(1L);
		ecp.setLeadId(eplvcImage.getLeadId());
		ecp.setBase64ImagePath(outputfile.getAbsolutePath());
		ecp.setFaceDetected(eplvcImage.isFaceDetected());
		ecp.setCapturedStage(eplvcImage.getCapturedStage());
		
		imageRepo.save(ecp);
			
	}
	
	
}
