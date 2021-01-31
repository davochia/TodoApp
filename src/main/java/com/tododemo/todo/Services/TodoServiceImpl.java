package com.tododemo.todo.Services;

import com.tododemo.todo.DTOs.TodoDTO;
import com.tododemo.todo.Repository.TodoRepository;
import com.tododemo.todo.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TodoServiceImpl implements TodoService{
    @Autowired
    private TodoRepository todoRepository;

    @Override
    public TodoDTO addTodos(TodoDTO todoDTO) {
        LocalDate nowDate = LocalDate.now();
        if (todoDTO == null || todoDTO.getEndDate().isBefore(nowDate))return null;
        Todo todo = TodoDTO.getTodo(todoDTO);
        return TodoDTO.getTodoDTO(todoRepository.save(todo));
    }

    @CachePut(value = "todos")
    @Override
    public List<TodoDTO>  getTodos() {
        delayResponse();
        List<Todo> todos = todoRepository.findAll();
        if (todos.isEmpty())return null;

        List<TodoDTO> todosDTO = new ArrayList<>();
        todos.forEach(todo -> todosDTO.add(TodoDTO.getTodoDTO(todo)));
        return todosDTO;
    }

    @Cacheable(value = "todos")
    @Override
    public List<TodoDTO> findByName(String name) {
        delayResponse() ;
        if(name.isEmpty())return null;

        List<TodoDTO> todosDTO = new ArrayList<>();
        todoRepository.findAll().forEach(todo -> {
            if (todo.getTodoName().equals(name)){
                todosDTO.add(TodoDTO.getTodoDTO(todo));
            }
        });
        return todosDTO;
    }


    @Override
    public TodoDTO editTodo(UUID id, TodoDTO todoDTO) {
        LocalDate nowDate = LocalDate.now();
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        if(optionalTodo.isEmpty() || todoDTO.getEndDate().isBefore(nowDate))return null;

        Todo todo = optionalTodo.get();

        todo.setTodoName(todoDTO.getTodoName());
        todo.setDescription(todoDTO.getDescription());
        todo.setEndDate(todoDTO.getEndDate());
        todo.setIsComplete(todoDTO.getIsComplete());
        return TodoDTO.getTodoDTO(todoRepository.save(todo));
    }

    @Override
    public boolean deleteTodo(UUID id) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        if(optionalTodo.isEmpty())return false;
        todoRepository.delete(optionalTodo.get());
        return true;
    }

    @Override
    public void toggleIsComplete(){
        LocalDate nowDate = LocalDate.now();

        todoRepository.findAll().forEach(todo -> {
            if (todo.getEndDate().isBefore(nowDate)){
                todo.setIsComplete(true);
            }
        });
    }


    private void delayResponse(){
        try{
            long time = 3000L;
            Thread.sleep(time);
        }catch(InterruptedException e){
            throw new IllegalStateException(e);
        }
    }
}
