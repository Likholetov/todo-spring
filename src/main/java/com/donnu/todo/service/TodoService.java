package com.donnu.todo.service;

import com.donnu.todo.entity.TodoEntity;
import com.donnu.todo.entity.UserEntity;
import com.donnu.todo.exceptions.TodoNotFoundException;
import com.donnu.todo.exceptions.UserNotFoundException;
import com.donnu.todo.model.Todo;
import com.donnu.todo.repository.TodoRepo;
import com.donnu.todo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
    @Autowired
    private TodoRepo todoRepo;
    @Autowired
    private UserRepo userRepo;

    public Todo createTodo(TodoEntity todo, Long userId) throws UserNotFoundException {
        UserEntity user = userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));
        todo.setUser(user);
        return Todo.toModel(todoRepo.save(todo));
    }

    public Todo completeTodo(Long id) throws TodoNotFoundException {
        TodoEntity todo = todoRepo.findById(id).orElseThrow(() -> new TodoNotFoundException("Задание не найдено"));
        todo.setCompleted(!todo.getCompleted());
        return Todo.toModel(todoRepo.save(todo));
    }
}
