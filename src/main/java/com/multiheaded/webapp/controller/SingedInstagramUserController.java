package com.multiheaded.webapp.controller;

import com.multiheaded.webapp.domain.SignedInstagramUser;
import com.multiheaded.webapp.payload.*;
import com.multiheaded.webapp.repo.SignedInstagramUserRepository;
import com.multiheaded.webapp.security.CurrentUser;
import com.multiheaded.webapp.security.UserPrincipal;
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
    SignedInstagramUserRepository sRepository;


    @Autowired
    SignedInstagramUserService sService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/authorization")
    public ResponseEntity<?> authorizeSignedInstagramUser(@Valid @RequestBody SignedInstagramUserAuthRequest sUserRequest) {
        SignedInstagramUser sUser = sService.createSignedInstagramUser(sUserRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{sUsername}")
                .buildAndExpand(sUser.getInstagramUser().getUsername()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Signed Instagram User Created Successfully"));
    }

    @GetMapping("")
    public PagedResponse<SignedInstagramUserResponse> getSignedInstagramUsersCreatedBy(@CurrentUser UserPrincipal currentUser,
                                                                                       @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                                                       @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return sService.getSignedInstagramUsersCreatedBy(currentUser.getUsername(), page, size);
    }

    @GetMapping("/{sUsername}")
    public SignedInstagramUserResponse getSignedInstagramUserBySUsername(@CurrentUser UserPrincipal currentUser,
                                                                         @PathVariable(value = "sUsername") String sUsername) {
        return sService.getSignedInstagramUserBySUsername(currentUser.getUsername(), sUsername);
    }

    @GetMapping("/followers/{sUsername}")
    public PagedResponse<InstagramUserResponse> getFollowersOfSignedIntagramUser(@CurrentUser UserPrincipal currentUser,
                                                                                 @PathVariable(value = "sUsername") String sUsername,
                                                                                 @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                                                 @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return sService.getFollowersOfSignedIntagramUser(currentUser.getUsername(), sUsername, page, size);
    }
}
