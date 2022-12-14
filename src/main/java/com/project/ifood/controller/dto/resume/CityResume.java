package com.project.ifood.controller.dto.resume;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor @AllArgsConstructor @Data
public class CityResume {

	 @NotNull(message = "A inserção da Cidade(object='city') é obrigatório!")
	private Long id;

	private String name;
	private String state;
}
