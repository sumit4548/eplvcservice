package com.eplvc.eplvcservice.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eplvc.eplvcservice.entity.EplvcSession;
import com.eplvc.eplvcservice.repositories.EplvcSessionRepo;

@Service
public class EplvcSessionService {
	
	@Autowired
	EplvcSessionRepo sessionRepo;
	
	public boolean isSessionExpired(String LeadId , String sessionId) {
		
		Optional<EplvcSession> session = sessionRepo.findById(LeadId);
		
		EplvcSession s=null;
		
		if(session == null)
			return true;
		
		 s = session.get();
		 
			

		if(!s.getSessionID().equalsIgnoreCase(sessionId))
			return true;

		Calendar cal = Calendar.getInstance();
		cal.setTime(s.getLastUpdatedAt());
		cal.add(Calendar.MINUTE, 30);
		System.out.println(cal.getTime().toString());
		if(cal.getTime().before(new Date()))
			return true; // session expired.
		 
		return false;
		
	}
	
	public void addSession(String LeadId , String SessionID ) {
		
		EplvcSession session = new EplvcSession();
		session.setLastUpdatedAt(new Date());
		session.setLeadId(LeadId);
		session.setSessionID(SessionID);
		
		sessionRepo.save(session);
	}
	
	public List<EplvcSession> getAllSessions(){
		
		List<EplvcSession> sList = new ArrayList<EplvcSession>();
		
		for(EplvcSession session : sessionRepo.findAll()) {
			sList.add(session);
		}
		return sList;
		
	}

}
