package com.sample;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class TaskService {
	
	@Autowired
	private TaskRepository repo;
	
	public List<Task> listALlTask() {
		return repo.findAll();
	}
	
	public void save(Task task) {
		repo.save(task);
	}
	
	public Task get(Integer id) {
		return repo.findById(id).get();
	}
	
	public void delete(Integer id) {
		repo.deleteById(id);;
	}
}
