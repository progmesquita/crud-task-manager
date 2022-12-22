package com.example.taskmanager.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.taskmanager.dto.TaskDTO;
import com.example.taskmanager.entities.Task;
import com.example.taskmanager.repositories.TaskRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TaskService {

	@Autowired
	private TaskRepository repository;
	
	@Transactional(readOnly = true)
	public Page<TaskDTO> getAllPaged(Pageable pageable) {
		Page<Task> tasks = repository.findAll(pageable);
		return tasks.map(x -> new TaskDTO(x));
	}

	@Transactional(readOnly = true)
	public TaskDTO findById(Long id) {
		Optional<Task> obj = repository.findById(id);
		Task entity = obj.orElseThrow(() -> new IllegalArgumentException());
		return new TaskDTO(entity);
	}
	
	@Transactional
	public TaskDTO insert(TaskDTO dto) {
		Task entity = new Task();
		
		entity.setTitle(dto.getTitle());
		entity.setDescription(dto.getDescription());
		entity.setDueDate(dto.getDueDate());
		
		entity = repository.save(entity);
		
		return new TaskDTO(entity);
	}
	
	@Transactional
	public TaskDTO uptade(Long id, TaskDTO dto) {
		try {
			Task entity = repository.getReferenceById(id);
			entity.setTitle(dto.getTitle());
			entity.setDescription(dto.getDescription());
			entity.setDueDate(dto.getDueDate());
			entity = repository.save(entity);
			return new TaskDTO(entity);
			
		} catch(EntityNotFoundException e) {
			throw new EntityNotFoundException();
		} catch(IllegalArgumentException e) {
			throw new IllegalArgumentException();
		}
	}

	public void deleteById(Long id) {
		try {
			repository.deleteById(id);			
		} catch(IllegalArgumentException e) {
			throw new IllegalArgumentException();
		} catch(EmptyResultDataAccessException e) {
			e.getMessage();
		}
	}
	
}
