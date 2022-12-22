package com.example.taskmanager.tests;

import java.time.Instant;

import com.example.taskmanager.entities.Task;

public class Factory {

	public static Task createTask() {
		Task task = new Task(1L, "Lavar a louça", "Descrição....", Instant.parse("2020-07-13T20:50:07.12345Z"));
		return task;
	}
}
