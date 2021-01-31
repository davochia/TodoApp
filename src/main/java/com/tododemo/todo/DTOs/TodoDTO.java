package com.tododemo.todo.DTOs;

import com.tododemo.todo.model.Todo;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TodoDTO {
    private UUID id;
    private String TodoName;
    private String description;
    private LocalDateTime endDate;
    private Boolean complete;

    public static TodoDTO getTodoDTO(Todo todo){
        TodoDTO todoDTO = new TodoDTO();

        todoDTO.setId(todo.getId());
        todoDTO.setTodoName(todo.getTodoName());
        todoDTO.setDescription(todo.getDescription());
        todoDTO.setEndDate(todo.getEndDate());
        todoDTO.setComplete(todo.getComplete());

        return todoDTO;
    }

    public static Todo getTodo(TodoDTO todoDTO){
        Todo todo = new Todo();

        todo.setId(todoDTO.getId());
        todo.setTodoName(todoDTO.getTodoName());
        todo.setDescription(todoDTO.getDescription());
        todo.setEndDate(todoDTO.getEndDate());
        todo.setComplete(todoDTO.getComplete());

        return todo;
    }
}
