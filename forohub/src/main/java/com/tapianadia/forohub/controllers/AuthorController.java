package com.tapianadia.forohub.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tapianadia.forohub.configuration.UserDetailsServices;

@RestController
@RequestMapping("/auth")
public class AuthorController  {

    @Autowired
    private UserDetailsServices userDetailsServices;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO login){
        return new ResponseEntity<>(userDetailsServices.login(login), HttpStatus.OK);
    }

}

