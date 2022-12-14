package com.project.ifood.domain.service;

import java.util.List;

import com.project.ifood.domain.model.Kitchen;

public interface KitchenService {

	Kitchen save(Kitchen kitchen);
	Kitchen update(Long id, Kitchen kitchen);
	List<Kitchen> findAll();
	Kitchen findById(Long id);
	void deleteById(Long id);
	Kitchen checkIfKitchenExists(Long id);
}
