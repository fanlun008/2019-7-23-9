package com.thoughtworks.parking_lot.controller;


import com.thoughtworks.parking_lot.entity.Todo;
import com.thoughtworks.parking_lot.repo.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@CrossOrigin(value = "*")
@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;


    @GetMapping()
    public List<Todo> loadAllTodos() {
        return todoRepository.findAll();
    }

    @PostMapping()
    public String addTodo(@RequestBody Todo todo) {
        Todo findTodo = todoRepository.findByTitle(todo.getTitle());
        if (findTodo != null) {
            return "repeat";
        }
        System.out.println(todo);
        Todo save = todoRepository.save(todo);
        if (save.getId() !=null) {
            return "OK";
        }
        return "fall";
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable(value = "id") String id) {
        todoRepository.deleteById(id);
        if (!todoRepository.findById(id).isPresent()){
            return "OK";
        }
        return "fall";
    }

    @Transactional
    @PutMapping("/{id}")
    public Todo update(@PathVariable("id") String id, @RequestBody Todo todo) {
        todoRepository.updateTodoById(id, todo.getTitle(), todo.isComplete());
        return todoRepository.findById(id).get();
    }



}
