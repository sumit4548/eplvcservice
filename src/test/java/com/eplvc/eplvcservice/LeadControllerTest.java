package com.eplvc.eplvcservice;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.eplvc.eplvcservice.controller.LeadController;
import com.eplvc.eplvcservice.entity.EplvcLead;
import com.eplvc.eplvcservice.enums.Status;
import com.eplvc.eplvcservice.repositories.EplvcImageRepo;
import com.eplvc.eplvcservice.repositories.EplvcLeadRepo;
import com.eplvc.eplvcservice.service.EplvcImageService;
import com.eplvc.eplvcservice.service.EplvcLeadService;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LeadControllerTest {

	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
	private EplvcLeadRepo leadRepo;
	
	@Autowired
	private EplvcLeadService service;
	
	
	 @Test
	 public void simpleTest() {
		 
		 EplvcLead lead = new EplvcLead();
		 lead.setLeadId("TEST123");
		 lead.setLeadName("TEST_NAME");
		 lead.setPolicyTerm("15 Years");
		 lead.setPremiumAmount((double) 10000);
		 lead.setPremiumFrequency("Yearly");
		 lead.setPremiumTerm("15 Years");
		 lead.setProductName("HDFC 3d Plus");
		 lead.setProductType("XYZ");
		 lead.setStatus(Status.LOADED);
		 lead.setLastUpdatedAt(new Date());
		 lead.setCreatedAt(new Date());
		 lead.setSumAssured((double) 1000000);
		 
		 service.addLead(lead);
		 
		 
	 }
	 
}
