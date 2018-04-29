package com.eplvc.eplvcservice.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.eplvc.eplvcservice.entity.EplvcCapturedPhotos;
import com.eplvc.eplvcservice.model.EplvcImage;
import com.eplvc.eplvcservice.repositories.EplvcImageRepo;

import sun.misc.BASE64Decoder;

@Service
public class EplvcImageService {
	
	@Autowired
	EplvcImageRepo imageRepo;
	
	 @Value("${eplvc.image.base.path}") String basePath;

	public List<EplvcCapturedPhotos> getAllPhotoes(){
		
		List<EplvcCapturedPhotos> lstImageList = new ArrayList<EplvcCapturedPhotos>();
		
		for(EplvcCapturedPhotos photo: imageRepo.findAll()) {
			lstImageList.add(photo);
		}
		
		return lstImageList;
	}
	
	public void saveImages(EplvcImage eplvcImage) throws IOException {
		
		@SuppressWarnings("restriction")
		BASE64Decoder decoder = new BASE64Decoder();
		@SuppressWarnings("restriction")
		byte[] imageByte = decoder.decodeBuffer(eplvcImage.getBase64Image());
		ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
		BufferedImage image = ImageIO.read(bis);
		bis.close();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_hhmmss");
		
		File f = new File(basePath+eplvcImage.getLeadId()+"/");
		f.mkdirs();
		File outputfile = new File(basePath+eplvcImage.getLeadId()+"/"+eplvcImage.getLeadId()+"_"+sdf.format(new Date())+".png");
		
		ImageIO.write(image, "png", outputfile);
		
		EplvcCapturedPhotos ecp = new EplvcCapturedPhotos();
		
		ecp.setLeadId(eplvcImage.getLeadId());
		ecp.setBase64ImagePath(outputfile.getAbsolutePath());
		ecp.setFaceDetected(eplvcImage.isFaceDetected());
		ecp.setCapturedStage(eplvcImage.getCapturedStage());

		imageRepo.save(ecp);
		
	}

}
