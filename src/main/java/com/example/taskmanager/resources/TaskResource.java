package com.example.taskmanager.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.taskmanager.dto.TaskDTO;
import com.example.taskmanager.services.TaskService;

@RestController
@RequestMapping(value = "/tasks")
public class TaskResource {

	@Autowired
	private TaskService service;
	
	@GetMapping
	public ResponseEntity<Page<TaskDTO>> findAll(Pageable pageable) {
		Page<TaskDTO> list = service.getAllPaged(pageable);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<TaskDTO> findById(@PathVariable Long id) {
		TaskDTO task = service.findById(id);
		return ResponseEntity.ok().body(task);
	}
	
	@PostMapping
	public ResponseEntity<TaskDTO> insert(@RequestBody TaskDTO dto) {
		TaskDTO task = service.insert(dto);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(dto.getId())
				.toUri();
		return ResponseEntity.created(uri).body(task);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<TaskDTO> uptadeById(@PathVariable Long id, @RequestBody TaskDTO dto) {
		TaskDTO task = service.uptade(id, dto);
		return ResponseEntity.ok().body(task);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
