package com.dmarquinar.evaluation.service;

import com.dmarquinar.evaluation.dto.UserRequest;
import com.dmarquinar.evaluation.model.User;
import org.springframework.stereotype.Service;

public interface UserService {

    User registerUser(UserRequest user);
}
