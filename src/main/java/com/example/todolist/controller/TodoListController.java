package com.example.todolist.controller;

import com.example.todolist.model.dto.TodoItemDto;
import com.example.todolist.model.dto.TodoListDto;
import com.example.todolist.model.entity.TodoList;
import com.example.todolist.service.TodoListService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TodoListController {

    private TodoListService todoListService;

    public TodoListController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    @PostMapping(value = "/todolist")
    public void createTodoList(@RequestParam(name = "name") String name) {
        todoListService.createTodoList(name);
    }

    @GetMapping(value = "/todolist")
    public List<TodoList> getAllTodoList() {
        return todoListService.getAllTodoList();
    }

    @GetMapping(value = "/todolist/{id}")
    public TodoListDto getTodoList(@PathVariable Long id) {
        return todoListService.getTodoListById(id);
    }

    @PostMapping(value = "/todolist/{id}")
    public void updateTodoList(@PathVariable Long id, @RequestBody String name) {
        try {
            todoListService.updateTodoList(id, name);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo List with id " + id + " do not exist", e);
        }
    }

    @PostMapping(value = "/todolist/{id}/todoitem")
    public void createOrUpdateTodoItem(@PathVariable Long id, @RequestBody TodoItemDto todoItemDto) {
        if( todoItemDto == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "TodoItem should not be null");
        }
        todoListService.createOrUpdateTodoItem(id, todoItemDto);
    }

}
