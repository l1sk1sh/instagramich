package com.multiheaded.webapp.controller;

/*
    Get the currently logged in user.
    Check if a username is available for registration.
    Check if an email is available for registration.
    Get the public profile of a user.
    Get a paginated list of polls created by a given user.
    Get a paginated list of polls in which a given user has voted.
 */
import com.multiheaded.webapp.domain.User;
import com.multiheaded.webapp.exception.ResourceNotFoundException;
import com.multiheaded.webapp.payload.*;
import com.multiheaded.webapp.repo.SignedInstagramUserRepository;
import com.multiheaded.webapp.repo.UserRepository;
import com.multiheaded.webapp.security.CurrentUser;
import com.multiheaded.webapp.security.UserPrincipal;
import com.multiheaded.webapp.service.SignedInstagramUserService;
import com.multiheaded.webapp.util.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    SignedInstagramUserRepository sRepository;


    @Autowired
    SignedInstagramUserService sService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/user/me")
    @PreAuthorize("hasAnyRole('ROLE_USER_TRIAL', 'ROLE_USER_FULL')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        return new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
    }

    @GetMapping("/user/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !userRepository.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/user/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/users/{username}")
    @PostAuthorize("returnObject.username == principal.username")
    public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
        return new UserProfile(user.getId(), user.getUsername(), user.getName(), user.getCreatedAt());
    }

    // TODO Fix it, it doesn't work
    @GetMapping("/users/{username}/signedInstagramUsers")
    @PreAuthorize("#username == principal.username")
    public PagedResponse<SignedInstagramUserResponse> getSignedInstagramUsersCreatedBy(@PathVariable(value = "username") String username,
                                                                        @CurrentUser UserPrincipal currentUser,
                                                                        @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                                        @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return sService.getSignedInstagramUsersCreatedBy(username, currentUser, page, size);
    }

}
