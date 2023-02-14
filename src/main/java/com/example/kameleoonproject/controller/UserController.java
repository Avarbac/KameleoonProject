package com.example.kameleoonproject.controller;

import com.example.kameleoonproject.model.User;
import com.example.kameleoonproject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
@Slf4j
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/createAccount")
    public ResponseEntity<User> createAccount(@RequestParam String name,
                                             @RequestParam String email,
                                             @RequestParam String password) {

        User user = userService.createAccount(name, email, password);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

}
