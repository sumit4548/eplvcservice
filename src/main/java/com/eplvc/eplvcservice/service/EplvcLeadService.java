package com.eplvc.eplvcservice.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eplvc.eplvcservice.customexceptions.ServiceException;
import com.eplvc.eplvcservice.entity.EplvcCapturedPhotos;
import com.eplvc.eplvcservice.entity.EplvcLead;
import com.eplvc.eplvcservice.repositories.EplvcImageRepo;
import com.eplvc.eplvcservice.repositories.EplvcLeadRepo;
import com.eplvc.eplvcservice.enums.Status;



@Service
public class EplvcLeadService {

	
	@Autowired
	EplvcLeadRepo leadRepo;
	
	@Autowired
	EplvcImageRepo imageRepo;
	
	Status status;

	public boolean validateEplvcLead(EplvcLead lead) throws Exception {

		if(lead.getLeadId().isEmpty() || !lead.getLeadId().matches("[A-Za-z0-9_]*") ) {
			throw new ServiceException("Lead id is not valid , Either it is empty or it contains special characters");
		}
		
		if(lead.getLeadName().isEmpty() || !lead.getLeadName().matches("[A-Za-z_\\s]*") ) {
			throw new ServiceException("Lead Name is not valid , Either it is empty or it contains special characters or numbers");
		}
		
		if(lead.getPolicyTerm().isEmpty() || !lead.getPolicyTerm().matches("[A-Za-z0-9_\\s]*") ) {
			throw new ServiceException("Policy Term is not valid , Either it is empty or it contains special characters");
		}
		
		if(lead.getPremiumAmount().isNaN() ) {
			throw new ServiceException("PremiumAmount Term is not valid ");
		}
		
		if(lead.getPremiumTerm().isEmpty() || !lead.getPremiumTerm().matches("[A-Za-z0-9_\\s]*") ) {
			throw new ServiceException("Premium Term is not valid , Either it is empty or it contains special characters ");
		}
		
		if(lead.getPremiumFrequency().isEmpty() || !lead.getPremiumFrequency().matches("[A-Za-z0-9_\\s]*") ) {
			throw new ServiceException("Premium Frequency is not valid , Either it is empty or it contains special characters ");
		}
		
		if(lead.getProductName().isEmpty() || !lead.getProductName().matches("[A-Za-z0-9_\\s]*") ) {
			throw new ServiceException("Product Name is not valid , Either it is empty or it contains special characters ");
		}
		
		if(lead.getProductType().isEmpty() || !lead.getProductType().matches("[A-Za-z0-9_\\s]*") ) {
			throw new ServiceException("Product Type is not valid , Either it is empty or it contains special characters");
		}
		
		if(lead.getSumAssured().isNaN() ) {
			throw new ServiceException("Sum Assured is not valid , Either it is empty or it contains special characters ");
		}
		
		return true;
	}
	
	public boolean isProcessCompleted(EplvcLead lead) {
	
		Optional<EplvcLead>  dblead = leadRepo.findById(lead.getLeadId());
				
		if(dblead.isPresent()) {
			if(dblead.get().getStatus() == Status.POLICY_DETAILS_VERIFIED 
				|| dblead.get().getStatus() == Status.POLICY_NAME_VERIFIED) {
				return true;
			}
		}
		
		return false;
	}
	
	public void addLead(EplvcLead lead) {
		
		if(leadRepo.existsById(lead.getLeadId())) {
			
			List<EplvcCapturedPhotos> listPhotoes= imageRepo.findByLeadId(lead.getLeadId());
			
			if(listPhotoes.size() >= 1) {
				
				for(EplvcCapturedPhotos image : listPhotoes) {
					File f = new File(image.getBase64ImagePath());
					if(f.exists()) {
						f.delete();
					}
				}
			}
			
			
			imageRepo.deleteByLeadId(lead.getLeadId());
		}
		leadRepo.save(lead);
	}
	
	public void updateLead(EplvcLead lead) throws ServiceException {
		
		if(!leadRepo.existsById(lead.getLeadId())) {
			throw new ServiceException("Invalid Request : Trying to update lead id , which does not exists");
		}
		
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
