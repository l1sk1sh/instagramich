package com.multiheaded.webapp.controller;

import com.multiheaded.webapp.domain.SignedInstagramUser;
import com.multiheaded.webapp.payload.*;
import com.multiheaded.webapp.repo.SignedInstagramUserRepository;
import com.multiheaded.webapp.repo.UserRepository;
import com.multiheaded.webapp.service.SignedInstagramUserService;
import com.multiheaded.webapp.util.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/susers")
@PreAuthorize("hasAnyRole('ROLE_USER_TRIAL', 'ROLE_USER_FULL')")
public class SingedInstagramUserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    SignedInstagramUserRepository sRepository;


    @Autowired
    SignedInstagramUserService sService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/authorization/{username}")
    @PreAuthorize("#username == principal.username")
    public ResponseEntity<?> authorizeSignedInstagramUser(@Valid @RequestBody SignedInstagramUserAuthRequest sUserRequest,
                                                            @PathVariable(value = "username") String username) {
        SignedInstagramUser sUser = sService.createSignedInstagramUser(sUserRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{username}/{sUsername}")
                .buildAndExpand(username, sUser.getInstagramUser().getUsername()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Signed Instagram User Created Successfully"));
    }

    @GetMapping("/{username}")
    @PreAuthorize("#username == principal.username")
    public PagedResponse<SignedInstagramUserResponse> getSignedInstagramUsersCreatedBy(@PathVariable(value = "username") String username,
                                                                                       @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                                                       @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return sService.getSignedInstagramUsersCreatedBy(username, page, size);
    }

    @GetMapping("/{username}/{sUsername}")
    @PreAuthorize("#username == principal.username")
    public SignedInstagramUserResponse getSignedInstagramUserBySUsername(@PathVariable(value = "username") String username,
                                                                         @PathVariable(value = "sUsername") String sUsername) {
        return sService.getSignedInstagramUserBySUsername(username, sUsername);
    }

    @GetMapping("/followers/{username}/{sUsername}")
    @PreAuthorize("#username == principal.username")
    public PagedResponse<InstagramUserResponse> getFollowersOfSignedIntagramUser(@PathVariable(value = "username") String username,
                                                                                 @PathVariable(value = "sUsername") String sUsername,
                                                                                 @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                                                 @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return sService.getFollowersOfSignedIntagramUser(username, sUsername, page, size);
    }
}
