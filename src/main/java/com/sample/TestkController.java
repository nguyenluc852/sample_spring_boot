package com.sample;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class TestkController {

	@Autowired
	private TestService service;
	
	@GetMapping("/tests")
	public List<Test> list () {
		return service.listALlTest();
	}
	
	@GetMapping("/test/{id}")
	public ResponseEntity<Test> get(@PathVariable Integer id) {
	    try {
	        Test task = service.get(id);
	        return new ResponseEntity<Test>(task, HttpStatus.OK);
	    } catch (NoSuchElementException e) {
	        return new ResponseEntity<Test>(HttpStatus.NOT_FOUND);
	    }      
	}
}
