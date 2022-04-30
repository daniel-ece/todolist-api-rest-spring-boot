package com.example.todolist.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoListDto {
    private Long id;
    private String name;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
    private Set<TodoItemDto> todoItemList;
}
