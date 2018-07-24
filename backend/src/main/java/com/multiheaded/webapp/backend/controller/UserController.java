package com.multiheaded.webapp.backend.controller;

/*
    Get the currently logged in user.
    Check if a username is available for registration.
    Check if an email is available for registration.
    Get the public profile of a user.
    Get a paginated list of users connected by a given user.
 */
import com.multiheaded.webapp.backend.domain.User;
import com.multiheaded.webapp.backend.exception.ResourceNotFoundException;
import com.multiheaded.webapp.backend.payload.UserIdentityAvailability;
import com.multiheaded.webapp.backend.payload.UserProfile;
import com.multiheaded.webapp.backend.payload.UserSummary;
import com.multiheaded.webapp.backend.repo.SignedInstagramUserRepository;
import com.multiheaded.webapp.backend.repo.UserRepository;
import com.multiheaded.webapp.backend.security.CurrentUser;
import com.multiheaded.webapp.backend.security.UserPrincipal;
import com.multiheaded.webapp.backend.service.SignedInstagramUserService;
import com.multiheaded.webapp.backend.util.AppConstants;
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

    @Deprecated
    @GetMapping("/users/{username}")
    @PostAuthorize("returnObject.username == principal.username")
    public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
        return new UserProfile(user.getId(), user.getUsername(), user.getName(), user.getCreatedAt());
    }

}
