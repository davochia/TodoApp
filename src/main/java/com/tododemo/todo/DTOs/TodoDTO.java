package com.tododemo.todo.DTOs;

import com.tododemo.todo.model.Todo;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class TodoDTO {
    private UUID id;
    private String TodoName;
    private String description;
    private LocalDate endDate;
    private Boolean isComplete;

    public static TodoDTO getTodoDTO(Todo todo){
        TodoDTO todoDTO = new TodoDTO();

        todoDTO.setId(todo.getId());
        todoDTO.setTodoName(todo.getTodoName());
        todoDTO.setDescription(todo.getDescription());
        todoDTO.setEndDate(todo.getEndDate());
        todoDTO.setIsComplete(todo.getIsComplete());

        return todoDTO;
    }

    public static Todo getTodo(TodoDTO todoDTO){
        Todo todo = new Todo();

        todo.setId(todoDTO.getId());
        todo.setTodoName(todoDTO.getTodoName());
        todo.setDescription(todoDTO.getDescription());
        todo.setEndDate(todoDTO.getEndDate());
        todo.setIsComplete(todoDTO.getIsComplete());

        return todo;
    }
}
