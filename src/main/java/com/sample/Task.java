package com.sample;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Task {
	int id;
	String title;
	String scheduled_date;
	String complete_date;
	int complete_flg;
	int priority;
	int delete_flg;
	String created_at;
	String updated_at;
	
	
	public Task() {
		super();
	}


	public Task(int id, String title, String scheduled_date, String compleate_date, int complete_flg, int priority,
			int delete_flg, String created_at, String updated_at) {
		super();
		this.id = id;
		this.title = title;
		this.scheduled_date = scheduled_date;
		this.complete_date = compleate_date;
		this.priority = priority;
		this.complete_flg = complete_flg;
		this.delete_flg = delete_flg;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getScheduled_date() {
		return scheduled_date;
	}
	public void setScheduled_date(String scheduled_date) {
		this.scheduled_date = scheduled_date;
	}
	public String getComplete_date() {
		return complete_date;
	}
	public void setComplete_date(String compleate_date) {
		this.complete_date = compleate_date;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	


	public int getComplete_flg() {
		return complete_flg;
	}


	public void setComplete_flg(int complete_flg) {
		this.complete_flg = complete_flg;
	}


	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getUpdated_at() {
		return updated_at;
	}
	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
	public int getDelete_flg() {
		return delete_flg;
	}
	public void setDelete_flg(int delete_flg) {
		this.delete_flg = delete_flg;
	}
	
}

