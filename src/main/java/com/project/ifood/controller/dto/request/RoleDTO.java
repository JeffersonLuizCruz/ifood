package com.project.ifood.controller.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor @Data
public class RoleDTO {

	@NotBlank(message = "Nome da permissão obrigatório!")
	private String name;
	private String description;
}
