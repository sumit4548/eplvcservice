package com.eplvc.eplvcservice.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.eplvc.eplvcservice.entity.EplvcCapturedPhotos;


@Repository
public interface EplvcImageRepo extends CrudRepository<EplvcCapturedPhotos, Long>{

	@Transactional
	Long deleteByLeadId(String leadid);
	
	List<EplvcCapturedPhotos> findByLeadId(String leadid);
	
}
