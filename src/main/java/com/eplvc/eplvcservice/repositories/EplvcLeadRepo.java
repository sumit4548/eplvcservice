package com.eplvc.eplvcservice.repositories;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.eplvc.eplvcservice.entity.EplvcLead;

public interface EplvcLeadRepo extends CrudRepository<EplvcLead, String> {
	
	Optional<EplvcLead> findByLeadId(String leadid);

}
