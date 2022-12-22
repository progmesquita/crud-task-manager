package com.example.taskmanager.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import com.example.taskmanager.entities.Task;
import com.example.taskmanager.tests.Factory;

import jakarta.persistence.EntityNotFoundException;

@DataJpaTest
public class TaskRepositoryTests {

	@Autowired
	private TaskRepository repository;
	
	private long lastTaskId;
	private long invalidTaskId;
	
	@BeforeEach
	void setUp() throws Exception {
		lastTaskId = 1L;
		invalidTaskId = 1000L;
	}

	@Test
	public void findByIdShouldReturnNoneExceptionWhenIdIsValid() {
		
		Optional<Task> task = repository.findById(lastTaskId);
		
		Assertions.assertTrue(task.isPresent());
	}
	
	@Test
	public void findByIdShouldReturnIllegalArgumentExceptionWhenIdIsInvalid() {
		
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			repository.findById(invalidTaskId);
		});
	}
	
	@Test
	public void insertShouldPersistWithAutoincrementWhenIdIsNull() {
		
		Task task = Factory.createTask();
		task.setId(null);
		
		task = repository.save(task);
		
		Assertions.assertEquals(lastTaskId + 1, task.getId());
	}
	
	@Test
	public void updateShouldReturnNoneExceptionWhenIdIsValid() {
		
		Task newTask = Factory.createTask();
		
		Task editedTask = repository.getReferenceById(lastTaskId);
		editedTask.setTitle(newTask.getTitle());
		editedTask.setDescription(newTask.getDescription());
		editedTask.setDueDate(newTask.getDueDate());
		
		editedTask = repository.save(editedTask);
		
		Assertions.assertNotNull(editedTask);
	}
	
	@Test
	public void updateShouldReturnEntityNotFoundExceptionWhenIdIsInvalid() {
		
		Assertions.assertThrows(EntityNotFoundException.class, () -> {
			Task newTask = Factory.createTask();
			
			Task editedTask = repository.getReferenceById(invalidTaskId);
			editedTask.setTitle(newTask.getTitle());
			editedTask.setDescription(newTask.getDescription());
			editedTask.setDueDate(newTask.getDueDate());
			
			repository.save(editedTask);
		});
	}
	
	@Test
	public void deleteNotShouldReturnNoneExceptionWhenIdIsValid() {
		
		repository.deleteById(lastTaskId);
		Optional<Task> taskById = repository.findById(lastTaskId);
		
		Assertions.assertTrue(taskById.isEmpty());
	}
	
	@Test
	public void deleteShouldReturnEmptyResultDataAccessExceptionWhenIdIsInvalid() {
		
		Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
			repository.deleteById(2L);
		});
	}
}
