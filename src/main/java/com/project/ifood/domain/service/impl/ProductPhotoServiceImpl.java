package com.project.ifood.domain.service.impl;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.ifood.domain.model.ProductPhoto;
import com.project.ifood.domain.repositoy.ProductPhotoRepository;
import com.project.ifood.domain.service.ProductPhotoService;

@Service
public class ProductPhotoServiceImpl implements ProductPhotoService{

	@Autowired private ProductPhotoRepository productPhotoRepository;
	
	@Transactional
	@Override
	public void save(ProductPhoto productPhoto) {
		Long productId = productPhoto.getProduct().getId();
		Long restaurantId = productPhoto.getRestaurantId();
		
		Optional<ProductPhoto> productExist = productPhotoRepository.findByProductAndRestaurant(productId, restaurantId);
		if(productExist.isPresent()) {
			productPhotoRepository.deleteById(productExist.get().getId());
		}
		productPhotoRepository.flush();
		productPhotoRepository.save(productPhoto);
	}

}
