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
import com.multiheaded.webapp.util.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    SignedInstagramUserRepository sRepository;

    /*
    @Autowired
    // TODO Instagram service
    */
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
        return userSummary;
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
    public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        UserProfile userProfile = new UserProfile(user.getId(), user.getUsername(), user.getName(), user.getCreatedAt());

        return userProfile;
    }

    @GetMapping("/users/{username}/signedInstagramUsers")
    public PagedResponse<SignedInstagramUserResponse> getPollsCreatedBy(@PathVariable(value = "username") String username,
                                                                        @CurrentUser UserPrincipal currentUser,
                                                                        @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                                        @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return null; // TODO Service that returns list of signedinstagram
    }

}
