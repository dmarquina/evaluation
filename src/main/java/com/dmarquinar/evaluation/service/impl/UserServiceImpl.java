package com.dmarquinar.evaluation.service.impl;

import com.dmarquinar.evaluation.dto.UserRequest;
import com.dmarquinar.evaluation.model.Phone;
import com.dmarquinar.evaluation.model.User;
import com.dmarquinar.evaluation.repository.UserRepository;
import com.dmarquinar.evaluation.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private final String jwtSecret = "secret";

    @Override
    public User registerUser(UserRequest userRequest) {
        Optional<User> userOp = userRepository.findByEmail(userRequest.getEmail());
        if (userOp.isPresent()) {
            throw new IllegalArgumentException("El correo ya registrado");
        } else {
            User user = new User();
            user.setId(UUID.randomUUID());
            user.setCreated(new Date());
            user.setModified(new Date());
            user.setLastLogin(new Date());
            user.setEmail(userRequest.getEmail());
            user.setToken(generateToken(userRequest.getEmail()));
            user.setPhones(userRequest.getPhones());
            user.setActive(true);

            return userRepository.save(user);
        }
    }

    private String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day expiration
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
}
