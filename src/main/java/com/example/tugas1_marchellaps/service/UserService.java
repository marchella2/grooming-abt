package com.example.tugas1_marchellaps.service;

import com.example.tugas1_marchellaps.DTO.LoginRequest;
import com.example.tugas1_marchellaps.DTO.LoginResponse;
import com.example.tugas1_marchellaps.DTO.Response;
import com.example.tugas1_marchellaps.entity.User;
import com.example.tugas1_marchellaps.repository.UserRepository;
import com.example.tugas1_marchellaps.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<Response<LoginResponse>> registerUser(User user){
        Response<LoginResponse> responseData = new Response<>();
        LoginResponse loginResponse = new LoginResponse();

        Optional<User> findUser = userRepo.findByUsername(user.getUsername());

        if (!findUser.isPresent()){
            String encodedPass = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPass);
            user = userRepo.save(user);
            String token = jwtUtil.generateToken(user.getUsername());
            loginResponse.setUsername(user.getUsername());
            loginResponse.setToken(token);

            responseData.setStatus(HttpStatus.OK.value());
            responseData.setMessage("User berhasil register");
            responseData.setPayload(loginResponse);

            return ResponseEntity.ok(responseData);
        } else {
            responseData.setStatus(HttpStatus.OK.value());
            responseData.setMessage("User dengan username = " + user.getUsername() + " telah terdaftar");
            responseData.setPayload(null);

            return ResponseEntity.ok(responseData);
        }
    }

    public ResponseEntity<Response<LoginResponse>> loginUser(LoginRequest user){
        Response<LoginResponse> responseData = new Response<>();
        LoginResponse loginResponse = new LoginResponse();

        try {
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

            authManager.authenticate(authInputToken);

            String token = jwtUtil.generateToken(user.getUsername());

            loginResponse.setUsername(user.getUsername());
            loginResponse.setToken(token);

            responseData.setStatus(HttpStatus.OK.value());
            responseData.setMessage("Login sukses");
            responseData.setPayload(loginResponse);

            return ResponseEntity.ok(responseData);
        }catch (AuthenticationException authExc){
            responseData.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            responseData.setMessage("Invalid Login Credentials");
            responseData.setPayload(null);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
//            throw new RuntimeException("Invalid Login Credentials");
        }
    }

    public ResponseEntity<Response<User>> getDetailsUser(){
        Response<User> responseData = new Response<>();
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User findUser =  userRepo.findByUsername(username).get();

        responseData.setStatus(HttpStatus.OK.value());
        responseData.setMessage("User found");
        responseData.setPayload(findUser);

        return ResponseEntity.ok(responseData);
    }

}
