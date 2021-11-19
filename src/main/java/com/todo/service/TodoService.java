package com.todo.service;

import java.util.List;

import com.todo.controller.CreateReq;
import com.todo.controller.UpdateReq;
import com.todo.dao.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.todo.entity.Todo;
import com.todo.dao.TodoPagingRepository;

@Service
public class TodoService {
	@Autowired
	private TodoRepository todoRepository;

	@Autowired
	private TodoPagingRepository todoPagingRepository;

	public Todo create(CreateReq createReq, String username) {
		Todo todo = new Todo(createReq.getTitle(), createReq.getTargetDate(), username);
		return todoRepository.save(todo);
	}

	public Todo readById(long id, String username) {
		Todo todo = todoRepository.findByUsernameAndId(username, id);

		return todo;
	}

	public List<Todo> readAll(String username) {
		return todoRepository.findAllByUsername(username);
	}

	public List<Todo> readAllPageable(String username, String pageNumber, String pageSize) throws Exception {
		int _pageNumber = pageNumberStringToInteger(pageNumber);
		int _pageSize = pageSizeStringToInteger(pageSize);

		Pageable pageable = PageRequest.of(_pageNumber, _pageSize, Sort.by(Sort.Direction.ASC, "targetDate"));
		return todoPagingRepository.findAllByUsername(username, pageable);
	}

	public List<Todo> readAllByIsCompleted(String username, String isCompleted) throws Exception {
		boolean _isCompleted = isCompletedStringToBoolean(isCompleted);
		return todoRepository.findAllByUsernameAndIsCompleted(username, _isCompleted);
	}

	public List<Todo> readAllByIsCompletedPageable(String username, String isCompleted, String pageNumber, String pageSize) throws Exception {
		boolean _isCompleted = isCompletedStringToBoolean(isCompleted);
		int _pageNumber = pageNumberStringToInteger(pageNumber);
		int _pageSize = pageSizeStringToInteger(pageSize);

		Pageable pageable = PageRequest.of(_pageNumber, _pageSize, Sort.by(Sort.Direction.ASC, "targetDate"));
		return todoPagingRepository.findAllByUsernameAndIsCompleted(username, _isCompleted, pageable);
	}

	public void deleteById(long id, String username) {
		Todo todo = todoRepository.findByUsernameAndId(username, id);

		todoRepository.deleteById(id);
	}

	public Todo updateById(long id, UpdateReq updateReq, String username) {
		Todo todo = todoRepository.findByUsernameAndId(username, id);


		todo.setTitle(updateReq.getTitle());
		todo.setTargetDate(updateReq.getTargetDate());
		return todoRepository.save(todo);
	}




	private boolean isCompletedStringToBoolean(String isCompleted) throws Exception {
		try {
			return Boolean.parseBoolean(isCompleted);
		} catch (Exception e) {
			throw new Exception("Invalid isCompleted");
		}
	}

	private int pageNumberStringToInteger(String pageNumber) throws Exception {
		int _pageNumber;

		try {
			_pageNumber = Integer.parseInt(pageNumber);
		} catch(Exception e) {
			throw new Exception("Invalid Page Number");
		}



		return _pageNumber;
	}

	private int pageSizeStringToInteger(String pageSize) throws Exception {
		int _pageSize;

		try {
			_pageSize = Integer.parseInt(pageSize);
		} catch(Exception e) {
			throw new Exception("Invalid Page Size");
		}


		return _pageSize;
	}
}