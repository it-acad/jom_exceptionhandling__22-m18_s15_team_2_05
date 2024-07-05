package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.exception.EntityNotFoundException;
import com.softserve.itacademy.exception.NullEntityReferenceException;
import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.repository.ToDoRepository;
import com.softserve.itacademy.service.ToDoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ToDoServiceImpl implements ToDoService {

    private ToDoRepository todoRepository;

    public ToDoServiceImpl(ToDoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public ToDo create(ToDo todo) {
        if (todo == null) {
            throw new NullEntityReferenceException("ToDo cannot be null");
        }
        return todoRepository.save(todo);
    }

    @Override
    public ToDo readById(long id) {
        return todoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ToDo not found"));
    }

    @Override
    public ToDo update(ToDo todo) {
        if (todo == null) {
            throw new EntityNotFoundException("ToDo cannot be null");
        }
        ToDo oldTodo = readById(todo.getId());
        if (oldTodo.getId() != todo.getId()) {
            throw new EntityNotFoundException("ToDo not found");
        }
        return todoRepository.save(oldTodo);
    }

    @Override
    public void delete(long id) {
        if (id < 0) {
            throw new NullEntityReferenceException("Wrong id");
        }
        ToDo todo = readById(id);
        todoRepository.delete(todo);
    }

    @Override
    public List<ToDo> getAll() {
        List<ToDo> todos = todoRepository.findAll();
        return todos.isEmpty() ? new ArrayList<>() : todos;
    }

    @Override
    public List<ToDo> getByUserId(long userId) {
        List<ToDo> todos = todoRepository.getByUserId(userId);
        return todos.isEmpty() ? new ArrayList<>() : todos;
    }
}
