package com.eplvc.eplvcservice.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.eplvc.eplvcservice.entity.EplvcCapturedPhotos;

public interface EplvcImageRepo extends CrudRepository<EplvcCapturedPhotos, Long>{

	@Transactional
	Long deleteByLeadId(String leadid);
	
	List<EplvcCapturedPhotos> findByLeadId(String leadid);
	
}
