package com.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.model.Test;
import com.sample.repository.TestRepository;


@Service
@Transactional
public class TestService {
	
	@Autowired
	private TestRepository repo;
	
	public List<Test> listALlTest() {
		return repo.findAll();
	}
	
	public void save(Test task) {
		repo.save(task);
	}
	
	public Test get(Integer id) {
		return repo.findById(id).get();
	}
	
	public void delete(Integer id) {
		repo.deleteById(id);;
	}
}
