package com.sample.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.comparator.Comparators;
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
	    List<Task> listUnComplete = new ArrayList<>();
	    
	    for (Task task : listTasks) {
	    	if (task.getDelete_flg() == 0) {
	    		if (task.getComplete_flg() == 1 ) {
					
					listComplete.add(task);
				} else {
					listUnComplete.add(task);
				}
	    	}
		}
	   
		Collections.sort(listComplete, new Comparator<Task>() {
            @Override
            public int compare(Task task, Task task2) {
                return task2.getComplete_date().compareTo(task.getComplete_date());
            }
        });
		
		Collections.sort(listUnComplete, new Comparator<Task>() {
            @Override
            public int compare(Task task, Task task2) {
            	Date currentDate = new Date();
            	Date date1 = null;
            	Date date2 = null;
            	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				try {
					date1 = sdf.parse(task.getScheduled_date());
					date2 = sdf.parse(task2.getScheduled_date());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	if ((date1.getTime() - currentDate.getTime()) > (date2.getTime() - currentDate.getTime())) {
            		return 1;
            	} else  if ((date1.getTime() - currentDate.getTime()) < (date2.getTime() - currentDate.getTime())) {
            		return -1;
            	} else {
            		return 0;
            	}
                
            }
        });
	
	    model.addAttribute("listTasksComplete", listComplete);
	    model.addAttribute("listTasksUnComplete", listUnComplete); 
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
    	Task taskUpdate = service.get(id); 
    	taskUpdate.setTitle(task.getTitle());
    	taskUpdate.setDescription(task.getDescription());
    	taskUpdate.setComplete_date(task.getComplete_date());
    	taskUpdate.setScheduled_date(task.getScheduled_date());
    	taskUpdate.setPriority(task.getPriority());
        task.setUpdated_at(sdf.format(new Date()));
        service.save(taskUpdate);
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

    @PostMapping("delete/{listId}")
    public String destroy(@PathVariable String listId) {
    	String[] parts = listId.split(",");
    	for (String id : parts) {
    		
    		Task task = service.get(Integer.decode(id));
           
            task.setUpdated_at(sdf.format(new Date()));
    		task.setDelete_flg(1);
            service.save(task);
		}
        
        return "redirect:/tasks";
    }
}
