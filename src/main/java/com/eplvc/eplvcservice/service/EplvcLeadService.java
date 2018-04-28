package com.eplvc.eplvcservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eplvc.eplvcservice.entity.EplvcLead;
import com.eplvc.eplvcservice.repositories.EplvcLeadRepo;



@Service
public class EplvcLeadService {

	
	@Autowired
	EplvcLeadRepo leadRepo;

	public boolean validateEplvcLead(EplvcLead lead) {
		
		//validation criterias
		return true;
	}
	
	public boolean isProcessCompleted(EplvcLead lead) {
		return false;
	}
	
	public void addLead(EplvcLead lead) {
		
		System.out.println(lead.getLeadId());
		
		if(leadRepo.existsById(lead.getLeadId())) {
			leadRepo.deleteById(lead.getLeadId());
			//TODO1.1 remove entry from rest of the places.
			
		}
		
		leadRepo.save(lead);
		
		
	}
	
	public EplvcLead getLeadById(String leadId) {
		
		Optional<EplvcLead> lead = leadRepo.findById(leadId);
		
		if(lead == null)
			return null;
		
		return lead.get();
	}
	
	
	
	public List<EplvcLead> getAllLeads(){
		
		List<EplvcLead> list = new ArrayList<EplvcLead>();
		
		for(EplvcLead eplvcCL: leadRepo.findAll()) {
			list.add(eplvcCL);
		}
		
		return list;
	}	

}
