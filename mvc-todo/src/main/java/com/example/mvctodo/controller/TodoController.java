package com.example.mvctodo.controller;


import com.example.mvctodo.model.Todo;
import com.example.mvctodo.services.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TodoController {
    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping()
    public String getTodos(Model model){
        List<Todo> todos = service.getAllTodos();
        model.addAttribute("todos", todos);
        return "todos";
    }

    @GetMapping("add")
    public String getAddTodoPage(Model model){
        model.addAttribute("todo", new Todo());
        return "add";
    }

    @PostMapping("add")
    public String addTodo(@ModelAttribute Todo todo){
        service.createTodo(todo);
        return "redirect:/";
    }

    @GetMapping("/complete/{id}")
    public String completeTdo(@PathVariable Long id){
        service.markTodoAsCompleted(id);
        return "redirect:/";
    }
}
