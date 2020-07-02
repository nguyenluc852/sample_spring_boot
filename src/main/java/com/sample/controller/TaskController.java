package com.sample.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.sample.model.Task;
import com.sample.service.TaskService;


@Controller
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	private TaskService service;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@GetMapping
	public String index(Model model) {
	    List<Task> listTasks = service.listALlTask();
	    List<Task> listComplete = new ArrayList<>();
	    List<Task> listNotComplete = new ArrayList<>();
	    
	    for (Task task : listTasks) {
	    	
			if (task.getComplete_flg() == 1) {
				listComplete.add(task);
			} else {
				listNotComplete.add(task);
			}
		}
	    
	   
	    
	    model.addAttribute("listTasksComplete", listComplete);
	    model.addAttribute("listTasksNotComplete", listNotComplete); 
	    return "tasks/index";
	}
	
	@GetMapping("new")
    public String newTask(Model model) {
        return "tasks/new";
    }
	
	@GetMapping("{id}/edit")
    public String edit(@PathVariable int id, Model model) { // ⑤
        Task task = service.get(id);
        model.addAttribute("task", task);
        return "tasks/edit";
    }
	@GetMapping("{id}")
    public String show(@PathVariable int id, Model model) {
        Task task = service.get(id);
        model.addAttribute("task", task);
        return "tasks/show";
    }
	
	@GetMapping("/get/{id}")
    public ResponseEntity<Task> getTask(@PathVariable int id) {
        return new ResponseEntity<Task>(service.get(id), HttpStatus.OK);
    }

    @PostMapping
    public String create(@ModelAttribute Task task) { 
    	task.setCreated_at(sdf.format(new Date()));
    	task.setUpdated_at(sdf.format(new Date()));
        service.save(task);
        return "redirect:/tasks"; 
    }

    @PostMapping("update/{id}")
    public String update(@PathVariable int id, @ModelAttribute Task task) {
    	
        task.setId(id);
        task.setUpdated_at(sdf.format(new Date()));
        service.save(task);
        return "redirect:/tasks";
    }
    
    @PostMapping("complete_task/{id}")
    public String updateWithAjax(@PathVariable int id) {
        Task task = service.get(id);
        task.setComplete_flg(1);
        task.setUpdated_at(sdf.format(new Date()));
        service.save(task);
        return "redirect:/tasks";
    }

    @PostMapping("delete/{id}")
    public String destroy(@PathVariable int id) {
        Task task = service.get(id);
        task.setId(id);
        task.setUpdated_at(new Date().toString());
		task.setDelete_flg(1);
        service.save(task);
        return "redirect:/tasks";
    }
}
