package com.example.todolist.service;

import com.example.todolist.model.dto.TodoItemDto;
import com.example.todolist.model.dto.TodoListDto;
import com.example.todolist.model.entity.TodoItem;
import com.example.todolist.model.entity.TodoList;
import com.example.todolist.repository.TodoItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class TodoItemService {

    private TodoItemRepository todoItemRepository;
    private ModelMapper modelMapper;

    public TodoItemService(TodoItemRepository todoItemRepository, ModelMapper modelMapper) {
        this.todoItemRepository = todoItemRepository;
        this.modelMapper = modelMapper;
    }

    public TodoItemDto convertToDto(TodoItem todoItem) {
        return modelMapper.map(todoItem, TodoItemDto.class);
    }

    public TodoItem convertToEntity(TodoItemDto todoItemDto) {
        return modelMapper.map(todoItemDto, TodoItem.class);
    }

    public void createOrUpdateTodoItem(TodoList todoList, TodoItemDto todoItemDto) {
        TodoItem todoItem = convertToEntity(todoItemDto);
        todoItem.setTodoList(todoList);
        todoItemRepository.save(todoItem);
    }
}
