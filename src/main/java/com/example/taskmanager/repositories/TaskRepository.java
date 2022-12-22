package com.example.taskmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.taskmanager.entities.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
