package com.multiheaded.webapp.iapi.controller;

import com.multiheaded.webapp.iapi.payload.*;
import com.multiheaded.webapp.iapi.service.IApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.io.IOException;
import java.net.URI;

// TODO Create exception handling and custom exceptions

@RestController
@RequestMapping("/api")
public class IApiController {

    @Autowired
    IApiService service;

    @PostMapping("/login")
    public SignedInstagramUserResponse signinInstagramAccount(@Valid @RequestBody InstagramUserAuthRequest sUserRequest) {
        return service.signinInstagramUser(sUserRequest);
    }

    @PostMapping("/user")
    public InstagramUserResponse getInstagramAccount(@Valid @RequestBody InstagramUserRequest userRequest) {
        return service.getInstagramUser(userRequest);
    }
}
