package com.eplvc.eplvcservice.repositories;


import org.springframework.data.repository.CrudRepository;

import com.eplvc.eplvcservice.entity.EplvcSession;

import java.lang.String;
import java.util.Date;
import java.util.List;

public interface EplvcSessionRepo extends CrudRepository<EplvcSession, String>  {
	
	
	
}
