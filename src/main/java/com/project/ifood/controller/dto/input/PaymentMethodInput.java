package com.project.ifood.controller.dto.input;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor @Data
public class PaymentMethodInput {

	@NotNull(message = "Tipo de pagamento é obrigatório!")
	public Long id;
}
