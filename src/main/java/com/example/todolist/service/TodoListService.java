package com.example.todolist.service;

import com.example.todolist.model.dto.TodoItemDto;
import com.example.todolist.model.dto.TodoListDto;
import com.example.todolist.model.entity.TodoList;
import com.example.todolist.repository.TodoListRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoListService {

    private TodoListRepository todoListRepository;
    private TodoItemService todoItemService;
    private ModelMapper modelMapper;

    public TodoListService(TodoListRepository todoListRepository, TodoItemService todoItemService, ModelMapper modelMapper) {
        this.todoListRepository = todoListRepository;
        this.todoItemService = todoItemService;
        this.modelMapper = modelMapper;
    }

    private TodoListDto convertToDto(TodoList todoList) {
        return modelMapper.map(todoList, TodoListDto.class);
    }

    public void createTodoList(String name) {
        TodoList todoList = TodoList.builder()
                .name(name)
                .build();
        todoListRepository.save(todoList);
    }

    public List<TodoList> getAllTodoList() {
        return todoListRepository.findAll();
    }

    public TodoListDto getTodoListById(Long id) {
        return convertToDto(todoListRepository.getById(id));
    }

    public void updateTodoList(Long id, String name) {
        TodoList todoList = todoListRepository.getById(id);
        todoList.setName(name);
        todoListRepository.save(todoList);
    }

    public void createOrUpdateTodoItem(Long id, TodoItemDto todoItemDto) {
        TodoList todoList = todoListRepository.getById(id);
        todoItemService.createOrUpdateTodoItem(todoList, todoItemDto);
    }
}