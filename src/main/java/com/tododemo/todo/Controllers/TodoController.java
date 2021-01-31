package com.tododemo.todo.Controllers;

import com.tododemo.todo.DTOs.TodoDTO;
import com.tododemo.todo.Services.TodoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class TodoController {

    @Autowired
    private TodoService todoService;


    @PostMapping("todo")
    @ApiOperation(value="Add new todo to system", response= TodoDTO.class)
    public TodoDTO addTodo(@RequestBody TodoDTO todo){
        return todoService.addTodos(todo);
    }

    @ApiOperation(value="Get todo list by name from system", response= List.class)
    @RequestMapping(value = "/todo", method = RequestMethod.GET)
    public List<TodoDTO> getByName(@RequestParam String name) {
        return todoService.findByName(name);
    }

    @ApiOperation(value="Get all todo list from system", response= List.class)
    @GetMapping("todos")
    public List<TodoDTO> getAllTodos(){
        return todoService.getTodos();
    }

    @ApiOperation(value="Edit todo in system by id", response= TodoDTO.class)
    @PutMapping("todo/{id}")
    public TodoDTO editTodo(@PathVariable UUID id, @RequestBody TodoDTO todoDTO){
        return todoService.editTodo(id, todoDTO);
    }

    @ApiOperation(value="Delete todo by id from system", response= Boolean.class)
    @DeleteMapping("todo/{id}")
    public boolean deleteTodo(@PathVariable UUID id){
        return todoService.deleteTodo(id);
    }

}
