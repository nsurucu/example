package com.todo.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.todo.entity.Todo;
import com.todo.service.TodoService;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true")

public class TodoController {
	@Autowired
	private TodoService todoService;
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@RequestMapping(value = "/api/todo", method = RequestMethod.POST)
	public ResponseEntity<Todo> create(@Valid @RequestBody CreateReq createReq, Principal principal) {
		return new ResponseEntity<>(todoService.create(createReq, principal.getName()), HttpStatus.CREATED);
	}
	
	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(value = "/api/todo", method = RequestMethod.GET)
	public ResponseEntity<List<Todo>> readAll(Principal principal, @RequestParam(required = false) String isCompleted)throws Exception {
		if(isCompleted != null) {
			return new ResponseEntity<>(todoService.readAllByIsCompleted(principal.getName(), isCompleted), HttpStatus.OK);
		}
		return new ResponseEntity<>(todoService.readAll(principal.getName()), HttpStatus.OK);
	}
	


	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(value = "/api/todo/{pageNumber}/{pageSize}", method = RequestMethod.GET)
	public ResponseEntity<List<Todo>> readAllPageable(Principal principal, @PathVariable String pageNumber, @PathVariable String pageSize, @RequestParam(required = false) String isCompleted)throws Exception {
		if(isCompleted != null) {
			return new ResponseEntity<>(todoService.readAllByIsCompletedPageable(principal.getName(), isCompleted, pageNumber, pageSize), HttpStatus.OK);
		}
		return new ResponseEntity<>(todoService.readAllPageable(principal.getName(), pageNumber, pageSize), HttpStatus.OK);
	}

	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(value = "/api/todo/{id}", method = RequestMethod.GET)
	public ResponseEntity<Todo> read(@PathVariable long id, Principal principal) {
		return new ResponseEntity<>(todoService.readById(id, principal.getName()), HttpStatus.OK);
	}
	

	
	@ResponseStatus(code = HttpStatus.OK)
	@RequestMapping(value = "/api/todo/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Todo> update(@PathVariable long id, @Valid @RequestBody UpdateReq updateReq, Principal principal) {
		return new ResponseEntity<>(todoService.updateById(id, updateReq, principal.getName()), HttpStatus.OK);
	}
	
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@RequestMapping(value = "/api/todo/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable long id, Principal principal) {
		todoService.deleteById(id, principal.getName());
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}
}
