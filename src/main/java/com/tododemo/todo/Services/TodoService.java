package com.tododemo.todo.Services;

import com.tododemo.todo.DTOs.TodoDTO;

import java.util.List;
import java.util.UUID;

public interface TodoService {

    TodoDTO addTodos(TodoDTO todoDTO);
    List<TodoDTO> getTodos();
    List<TodoDTO> findByName(String name);
    TodoDTO editTodo(UUID id, TodoDTO todoDTO);
    boolean deleteTodo(UUID id);
    //List<TodoDTO> dateTodoPass();

}
