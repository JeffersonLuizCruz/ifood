package com.project.ifood.controller.mapper;

import org.mapstruct.Mapper;

import com.project.ifood.controller.dto.StateDTO;
import com.project.ifood.domain.model.State;

@Mapper(componentModel = "spring")
public interface StateMapper {

    State toModel(StateDTO tdo);
    StateDTO toDTO(State state);
}