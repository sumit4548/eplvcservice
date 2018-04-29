package com.eplvc.eplvcservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eplvc.eplvcservice.entity.EplvcCapturedPhotos;
import com.eplvc.eplvcservice.entity.EplvcLead;
import com.eplvc.eplvcservice.service.EplvcImageService;
import com.eplvc.eplvcservice.service.EplvcLeadService;

@RestController
public class EplvcFetchInfoController {
	
	@Autowired
	EplvcLeadService service;
	
	
	@Autowired
	EplvcImageService imageService;

	@RequestMapping(method=RequestMethod.GET , value = "/getAllLeads")
	public List<EplvcLead> returnAllLeads() {
		return service.getAllLeads();	
	}
	
	
	@RequestMapping(method=RequestMethod.GET , value = "/getAllPhotoes")
	public List<EplvcCapturedPhotos> returnAllPhotoes() {
		return imageService.getAllPhotoes();
	}
	
}
