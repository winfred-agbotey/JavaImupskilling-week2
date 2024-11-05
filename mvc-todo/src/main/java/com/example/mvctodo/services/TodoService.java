package com.example.mvctodo.services;

import com.example.mvctodo.model.Todo;
import com.example.mvctodo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    public void createTodo(Todo todo) {
        repository.save(todo);
    }

    public Todo getTodoById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Todo> getAllTodos() {
        return repository.findAll();
    }

    public void markTodoAsCompleted(Long id) {
        Todo todo = repository.findById(id).orElseThrow(IllegalArgumentException::new);
        todo.setCompleted(true);
        repository.save(todo);
    }
}