package com.example.ToDoApp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ToDoApp.model.ToDo;
import com.example.ToDoApp.repo.IToDoRepo;

@Service
public class ToDoService {
	
	@Autowired
	IToDoRepo repo;
	
	public List<ToDo> getAllToDoitems(){
		ArrayList<ToDo> todolist=new ArrayList<>();
		repo.findAll().forEach(todo -> todolist.add(todo));
		
		return todolist;
	}
	
	public ToDo getToDoItemsById(Long id) {
		return repo.findById(id).get();
		
	}
	
	public boolean updateStatus(Long id) {
		ToDo todo=getToDoItemsById(id);
		todo.setStatus("Completed");
		
		return saveOrupdateToDoItem(todo);
		
	}
	
	public boolean saveOrupdateToDoItem(ToDo todo) {
		ToDo updateObj = repo.save(todo);
		
		if(getToDoItemsById(updateObj.getId()) != null) {
			return true;
		}
		return false;
		
	}
	
	public boolean deleteToDoItem(Long id) {
		repo.deleteById(id);
		
		if(repo.findById(id).isEmpty()) {
			return true;
		}
		return false;
		
	}

}
