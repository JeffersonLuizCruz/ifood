package com.project.ifood.controller.api;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.ifood.domain.model.Group;
import com.project.ifood.domain.model.Role;
import com.project.ifood.domain.service.GroupByRoleService;
import com.project.ifood.domain.service.GroupService;

import lombok.RequiredArgsConstructor;

@RestController @RequiredArgsConstructor
@RequestMapping("/groups/{groupId}/roles")
public class GroupByRoleController {
	
	private final GroupByRoleService groupByRoleService;
	private final GroupService groupService;
	
	@GetMapping
	public ResponseEntity<Set<Role>> findAllByRole(@PathVariable Long groupId){
		Group group = groupService.checkIfGroupExists(groupId);
		return ResponseEntity.ok(group.getRoles());
	}
	
	@DeleteMapping("/{roleId}") @ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void disassociateRole(@PathVariable Long groupId, @PathVariable Long roleId){
		groupByRoleService.disassociate(groupId, roleId);
	}
	
	@PutMapping("/{roleId}") @ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void associateRole(@PathVariable Long groupId, @PathVariable Long roleId){
		groupByRoleService.associate(groupId, roleId);
	}

}
