package com.financial.ifood.controller.mapper.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor @Data
public class InputStateId {

    @NotNull(message = "Id do Estado é obrigatório!")
    private Long id;
}
