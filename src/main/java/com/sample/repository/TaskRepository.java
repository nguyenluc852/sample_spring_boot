package com.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.model.Task;


public interface TaskRepository extends JpaRepository<Task, Integer> {

}
