package com.donnu.todo.service;

import com.donnu.todo.entity.UserEntity;
import com.donnu.todo.exceptions.UserAlreadyExistsException;
import com.donnu.todo.exceptions.UserNotFoundException;
import com.donnu.todo.model.User;
import com.donnu.todo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserEntity registration(UserEntity user) throws UserAlreadyExistsException {
        if (userRepo.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistsException("Пользователь с таким именем уже существует");
        }
        return userRepo.save(user);
    }

    public User getOne(Long id) throws UserNotFoundException {
        return User.toModel(userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("Пользователь не найден")));
    }

    public Long delete(Long id) throws UserNotFoundException {
        userRepo.delete(userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("Пользователь не найден")));
        return id;
    }
}
