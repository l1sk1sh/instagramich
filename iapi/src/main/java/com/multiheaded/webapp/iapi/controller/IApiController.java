package com.multiheaded.webapp.iapi.controller;

import com.multiheaded.webapp.iapi.payload.ApiResponse;
import com.multiheaded.webapp.iapi.payload.InstagramUserAuthRequest;
import com.multiheaded.webapp.iapi.payload.InstagramUserResponse;
import com.multiheaded.webapp.iapi.service.IApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;

// TODO Implement logging
// TODO Create exception handling and custom exceptions

@RestController
@RequestMapping("/api")
public class IApiController {

    @Autowired
    IApiService service;

    @PostMapping("/login")
    public ResponseEntity<?> signinInstagramAccount(@Valid @RequestBody InstagramUserAuthRequest sUserRequest) {
        try {
            InstagramUserResponse response = service.signinInstagramUser(sUserRequest);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/users/{username}")
                    .buildAndExpand(response.getUsername()).toUri();

            return ResponseEntity.created(location).body(response);

        } catch (IOException e) {
            return new ResponseEntity(new ApiResponse(false, "Unable to login!"
                    + " " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<?> getInstagramAccount(@PathVariable(value = "username") String username) {
        try {
            InstagramUserResponse response = service.getInstagramUser(username);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentContextPath().path("/users/{username}")
                    .buildAndExpand(response.getUsername()).toUri();

            return ResponseEntity.created(location).body(response);

        } catch (IOException e) {
            return new ResponseEntity(new ApiResponse(false, "Unable to get user info!"
                    + " " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
