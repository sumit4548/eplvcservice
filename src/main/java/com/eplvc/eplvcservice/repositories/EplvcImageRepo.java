package com.eplvc.eplvcservice.repositories;

import org.springframework.data.repository.CrudRepository;
import com.eplvc.eplvcservice.entity.EplvcCapturedPhotos;

public interface EplvcImageRepo extends CrudRepository<EplvcCapturedPhotos, Long>{

}
