package com.project.ifood.domain.controller.mapper.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor @AllArgsConstructor @Data
public class CityResume {

	@NotNull
	private Long id;

	private String name;
	private String state;
}