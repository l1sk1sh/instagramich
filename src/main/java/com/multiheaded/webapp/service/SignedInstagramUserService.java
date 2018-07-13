package com.multiheaded.webapp.service;

import com.multiheaded.webapp.domain.InstagramUser;
import com.multiheaded.webapp.domain.SignedInstagramUser;
import com.multiheaded.webapp.domain.User;
import com.multiheaded.webapp.exception.BadRequestException;
import com.multiheaded.webapp.exception.ResourceNotFoundException;
import com.multiheaded.webapp.payload.PagedResponse;
import com.multiheaded.webapp.payload.SignedInstagramUserResponse;
import com.multiheaded.webapp.repo.InstagramUserRepository;
import com.multiheaded.webapp.repo.SignedInstagramUserRepository;
import com.multiheaded.webapp.repo.UserRepository;
import com.multiheaded.webapp.security.UserPrincipal;
import com.multiheaded.webapp.util.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SignedInstagramUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    InstagramUserRepository iRepository;

    @Autowired
    SignedInstagramUserRepository sRepository;

    private static final Logger logger = LoggerFactory.getLogger(SignedInstagramUserService.class);

    public PagedResponse<SignedInstagramUserResponse> getSignedInstagramUsersLinkedBy(
            String username, UserPrincipal currentUser, int page, int size) {
        validatePageNumberAndSize(page, size);

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        // Retrieve all polls created by the given username
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        Page<SignedInstagramUser> sUsers = sRepository.findByCreatedBy(user.getId(), pageable);

        if (sUsers.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), sUsers.getNumber(),
                    sUsers.getSize(), sUsers.getTotalElements(), sUsers.getTotalPages(), sUsers.isLast());
        }

        // Map Polls to PollResponses containing vote counts and poll creator details
        List<Long> signedUsersIds = sUsers.map(SignedInstagramUser::getId).getContent();

        // TODO Create mapping

        return null;

    }

    private void validatePageNumberAndSize(int page, int size) {
        if(page < 0) {
            throw new BadRequestException("Page number cannot be less than zero.");
        }

        if(size > AppConstants.MAX_PAGE_SIZE) {
            throw new BadRequestException("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
        }
    }

}
