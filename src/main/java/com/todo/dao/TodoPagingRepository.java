package com.todo.dao;

import java.util.List;

import com.todo.entity.Todo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoPagingRepository extends PagingAndSortingRepository<Todo, Long> {
	List<Todo> findAllByUsername(String username, Pageable pageable);
	List<Todo> findAllByUsernameAndIsCompleted(String username, boolean isCompleted, Pageable pageable);
}