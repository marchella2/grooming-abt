package com.example.tugas1_marchellaps.controller;

import com.example.tugas1_marchellaps.DTO.LoginRequest;
import com.example.tugas1_marchellaps.DTO.LoginResponse;
import com.example.tugas1_marchellaps.DTO.Response;
import com.example.tugas1_marchellaps.entity.User;
import com.example.tugas1_marchellaps.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Response<LoginResponse>> registerUser(@RequestBody User user){
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<Response<LoginResponse>> loginUser(@RequestBody LoginRequest body){
        return userService.loginUser(body);
    }


    @GetMapping("/info")
    public ResponseEntity<Response<User>> getUserDetails(){
        return userService.getDetailsUser();
    }

}
