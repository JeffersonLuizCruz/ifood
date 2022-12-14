package com.project.ifood.domain.service.impl;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.ifood.domain.model.City;
import com.project.ifood.domain.model.State;
import com.project.ifood.domain.repositoy.CityRepository;
import com.project.ifood.domain.service.CityService;
import com.project.ifood.domain.service.StateService;
import com.project.ifood.domain.service.exception.ConstraintViolationService;
import com.project.ifood.domain.service.exception.NotFoundExceptionService;

import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor
public class CityServiceImpl implements CityService{

	private final CityRepository cityRepository;
	private final StateService stateService;

	private final String CONSTRAINT_VALIDATION_MESSAGE = "Cidade de código '%d' não pode ser removida, pois está em uso";
	private final String NOT_FOUND_MESSAGE = "Cidade de código '%d' não encontrado.";

	@Transactional
	@Override
	public City save(City city) {
		State stateEntity = stateService.checkIfStateExists(city.getState().getId());
		city.setState(stateEntity);
		return cityRepository.save(city);
	}
	@Transactional
	@Override
	public City update(Long id, City city) {
		checkIfCityExists(id);
		State stateEntity = stateService.checkIfStateExists(city.getState().getId());
		city.setId(id);
		city.setState(stateEntity);

		return cityRepository.save(city);
	}

	@Override
	public List<City> findAll() {
		return cityRepository.findAll();
	}

	@Transactional
	@Override
	public City findById(Long id) {
		return checkIfCityExists(id);
	}

	@Transactional
	@Override
	public void deleteById(Long id) {

		try {
			City cityEntity = checkIfCityExists(id);
			cityRepository.delete(cityEntity);
			cityRepository.flush();
		}catch (DataIntegrityViolationException | ConstraintViolationException e){
			throw new ConstraintViolationService(String.format(CONSTRAINT_VALIDATION_MESSAGE, id));

		}
	}

	@Override
	public City checkIfCityExists(Long id) {
		return cityRepository.findById(id).orElseThrow(() ->
				new NotFoundExceptionService(String.format(NOT_FOUND_MESSAGE, id)));
	}
}
