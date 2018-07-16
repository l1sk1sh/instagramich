package com.multiheaded.webapp.service;

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
import com.multiheaded.webapp.util.ModelMapper;
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

@Service
public class SignedInstagramUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    InstagramUserRepository iRepository;

    @Autowired
    SignedInstagramUserRepository sRepository;

    private static final Logger logger = LoggerFactory.getLogger(SignedInstagramUserService.class);

    public PagedResponse<SignedInstagramUserResponse> getSignedInstagramUsersCreatedBy(
            String username, UserPrincipal currentUser, int page, int size) {
        validatePageNumberAndSize(page, size);

        // TODO ADD SECURITY using currentUser

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        // Retrieve all signed instagram accounts created by the given username
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        Page<SignedInstagramUser> sUsers = sRepository.findByCreatedBy(user.getId(), pageable);

        if (sUsers.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), sUsers.getNumber(),
                    sUsers.getSize(), sUsers.getTotalElements(), sUsers.getTotalPages(), sUsers.isLast());
        }

        // Mapping to SignedInstagramUserResponse
        List<SignedInstagramUserResponse> sUserResponses =
                sUsers.map(ModelMapper::mapSignedInstagramUserToResponse).getContent();

        return new PagedResponse<>(sUserResponses, sUsers.getNumber(),
                sUsers.getSize(), sUsers.getTotalElements(), sUsers.getTotalPages(), sUsers.isLast());
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
