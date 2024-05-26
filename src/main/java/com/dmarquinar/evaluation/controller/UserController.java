package com.dmarquinar.evaluation.controller;

import com.dmarquinar.evaluation.dto.UserRequest;
import com.dmarquinar.evaluation.dto.UserResponse;
import com.dmarquinar.evaluation.model.User;
import com.dmarquinar.evaluation.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserRequest request) {
        try {
            User registeredUser = userService.registerUser(request);

            UserResponse response = new UserResponse();
            response.setId(registeredUser.getId());
            response.setCreated(registeredUser.getCreated());
            response.setModified(registeredUser.getModified());
            response.setLastLogin(registeredUser.getLastLogin());
            response.setToken(registeredUser.getToken());
            response.setIsActive(registeredUser.isActive());
            return ResponseEntity.status(201).body(response);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(409).body("{\"mensaje\": \"" + e.getMessage() + "\"}");
        }
    }
}