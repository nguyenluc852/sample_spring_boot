package com.sample;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	private TaskService service;
	
	@GetMapping
	public String index(Model model) {
	    List<Task> listTasks = service.listALlTask();
	    model.addAttribute("listTasks", listTasks);
	     
	    return "tasks/index";
	}
	
	@GetMapping("/task/{id}")
	public ResponseEntity<Task> get(@PathVariable Integer id) {
	    try {
	        Task task = service.get(id);
	        return new ResponseEntity<Task>(task, HttpStatus.OK);
	    } catch (NoSuchElementException e) {
	        return new ResponseEntity<Task>(HttpStatus.NOT_FOUND);
	    }      
	}
}
