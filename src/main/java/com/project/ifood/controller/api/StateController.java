package com.project.ifood.controller.api;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.ifood.controller.dto.request.StateDTO;
import com.project.ifood.controller.dto.response.StateResponseDTO;
import com.project.ifood.controller.mapper.StateMapper;
import com.project.ifood.domain.model.State;
import com.project.ifood.domain.service.StateService;
import com.project.ifood.infrastructure.util.http.ResponseUriHelper;
import org.springframework.hateoas.Link;
import lombok.RequiredArgsConstructor;

@RestController @RequiredArgsConstructor
@RequestMapping("/states")
public class StateController {

	private final StateService stateService;
	private final StateMapper stateMapper;


	@PostMapping
	public ResponseEntity<State> save(@RequestBody @Valid StateDTO dto){
		State stateModel = stateMapper.toModel(dto);
		State state = stateService.save(stateModel);
		ResponseUriHelper.addUriInResponseHader(state.getId());
		return ResponseEntity.status(HttpStatus.CREATED).body(stateService.save(state));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<State> update(@PathVariable Long id, @RequestBody @Valid StateDTO dto){
		State state = stateMapper.toModel(dto);
		return ResponseEntity.ok(stateService.update(id, state));
	}
	
	@GetMapping
	public ResponseEntity<List<StateResponseDTO>> findAll(){
		List<StateResponseDTO> listStateResponse = stateService.findAll()
		.stream()
		.map(stateMapper::toDTO)
		.map(state -> state.add(Link.of("http://localhost:8181/estados")))
		.collect(Collectors.toList());

		return ResponseEntity.ok(listStateResponse);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<StateResponseDTO> findById(@PathVariable Long id){
		State stateEntity = stateService.findById(id);
		StateResponseDTO dto = stateMapper.toDTO(stateEntity);
		dto.add(Link.of("http://localhost:8181/estados/1"));
		dto.add(Link.of("http://localhost:8181/estados", "estado"));
		return ResponseEntity.ok(dto);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		stateService.deleteById(id);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
