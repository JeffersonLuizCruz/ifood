package com.project.ifood.controller.mapper;

import org.mapstruct.Mapper;

import com.project.ifood.controller.dto.request.StateDTO;
import com.project.ifood.controller.dto.response.StateResponseDTO;
import com.project.ifood.domain.model.State;

@Mapper(componentModel = "spring")
public interface StateMapper {

    State toModel(StateDTO tdo);
    StateResponseDTO toDTO(State state);
}
