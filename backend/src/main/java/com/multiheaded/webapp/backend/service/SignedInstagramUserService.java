package com.multiheaded.webapp.backend.service;

import com.multiheaded.webapp.backend.domain.InstagramUser;
import com.multiheaded.webapp.backend.domain.SignedInstagramUser;
import com.multiheaded.webapp.backend.domain.User;
import com.multiheaded.webapp.backend.exception.BadRequestException;
import com.multiheaded.webapp.backend.exception.ResourceNotFoundException;
import com.multiheaded.webapp.backend.payload.InstagramUserResponse;
import com.multiheaded.webapp.backend.payload.PagedResponse;
import com.multiheaded.webapp.backend.payload.SignedInstagramUserAuthRequest;
import com.multiheaded.webapp.backend.payload.SignedInstagramUserResponse;
import com.multiheaded.webapp.backend.repo.InstagramUserRepository;
import com.multiheaded.webapp.backend.repo.SignedInstagramUserRepository;
import com.multiheaded.webapp.backend.repo.UserRepository;
import com.multiheaded.webapp.backend.util.AppConstants;
import com.multiheaded.webapp.backend.util.MockModel;
import com.multiheaded.webapp.backend.util.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public SignedInstagramUserResponse getSignedInstagramUserBySUsername(String username, String sUsername) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        SignedInstagramUser sUser = sRepository.findBySUsernameAndUserId(user.getId(), sUsername)
                .orElseThrow(() -> new ResourceNotFoundException("sUser", "sUsername", sUsername));

        return ModelMapper.mapSignedInstagramUserToResponse(sUser);
    }

    public PagedResponse<SignedInstagramUserResponse> getSignedInstagramUsersCreatedBy(
            String username, int page, int size
    ) {
        validatePageNumberAndSize(page, size);

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

    public PagedResponse<InstagramUserResponse> getFollowersOfSignedIntagramUser(
            String username, String sUsername, int page, int size
    ) {
        validatePageNumberAndSize(page, size);

        // TODO Maybe it is easier to use principal then to fetch user from DB

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        // Retrieve signed instagram account by sUsername
        SignedInstagramUser sUser = sRepository.findBySUsernameAndUserId(user.getId(), sUsername)
                .orElseThrow(() -> new ResourceNotFoundException("SignedInstagramUser", "sUsername", sUsername));

        // Retrieve all followers for this signed instagram user
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        List<InstagramUser> listOfFollowers = new ArrayList<>(sUser.getFollowers());
        Page<InstagramUser> followers = new PageImpl<>(listOfFollowers, pageable, listOfFollowers.size());

        if (followers.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), followers.getNumber(),
                    followers.getSize(), followers.getTotalElements(), followers.getTotalPages(), followers.isLast());
        }

        List<InstagramUserResponse> followerResponses =
                followers.map(ModelMapper::mapInstagramUsertoResponse).getContent();

        return new PagedResponse<>(followerResponses, followers.getNumber(),
                followers.getSize(), followers.getTotalElements(), followers.getTotalPages(), followers.isLast());

    }

    public SignedInstagramUser createSignedInstagramUser(SignedInstagramUserAuthRequest request) {
        // Here we login to Instagram API to check all the shit, and if everything is fine return InstagramUser
        // TODO Create REST Consumer for Instagram4j API
        // THIS IS MOCK
        InstagramUser iUser = MockModel.mockInstagramUser(request.getUsername());
        try {
            iUser = iRepository.save(iUser);
        } catch (DataIntegrityViolationException ex) {
            logger.warn("Current iUser {} was found in DB. Using existing iUser", iUser.getUsername());
            iUser = iRepository.findByUsername(request.getUsername()).orElseThrow(() ->
                new ResourceNotFoundException("iUser", "iUsername", request.getUsername()));
        }

        SignedInstagramUser sUser = MockModel.mockSignedInstagramUser(iUser, request.getPassword());
        try {
            sUser = sRepository.save(sUser);
        } catch (DataIntegrityViolationException e) {
            logger.warn("Provided sUser {} is already being used", iUser.getUsername());
            throw new BadRequestException("Provided user is already being used. " +
                    "System doesn't support Instagram accounts re-usage");
        }

        return sUser;
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
