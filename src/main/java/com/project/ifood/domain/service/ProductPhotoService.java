package com.project.ifood.domain.service;

import com.project.ifood.domain.model.ProductPhoto;

public interface ProductPhotoService {

	ProductPhoto save(ProductPhoto productPhoto);
	ProductPhoto findByProductAndRestaurant(Long productId, Long restaurantId);
	void removerByProductAndRestaurant(Long productId, Long restaurantId);
}
