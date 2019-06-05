package com.multiheaded.webapp.iapi.service;

import com.multiheaded.webapp.iapi.exception.AppException;
import com.multiheaded.webapp.iapi.exception.BadRequestException;
import com.multiheaded.webapp.iapi.payload.*;
import com.multiheaded.webapp.iapi.util.ModelMapper;
import org.apache.http.client.CookieStore;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramLoginRequest;
import org.brunocvcunha.instagram4j.requests.InstagramSearchUsernameRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramLoginResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramSearchUsernameResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static com.multiheaded.webapp.iapi.util.NetUtils.*;

// TODO FOR NEXT Challenge required

@Service
public class IApiService {

    private static final Logger logger = LoggerFactory.getLogger(IApiService.class);

    public SignedInstagramUserResponse signinInstagramUser(InstagramUserAuthRequest request) {
        String serializedCookieStore;
        InstagramLoginResult loginResult;
        Instagram4j instagram = new Instagram4j(request.getUsername(), request.getPassword());
        instagram.setup();

        try {
            loginResult = instagram.login();
        } catch (IOException e) {
            logger.warn("Login failed!", e);
            throw new BadRequestException("Login failed!", e);
        }

        if (loginResult.getStatus().equals("fail")) {
            // TODO Add exception handling logic
            throw new BadRequestException("Login failed! " + loginResult.getMessage());
        }

        try {
            serializedCookieStore = serializeToString(instagram.getCookieStore());
        } catch (IOException e) {
            logger.error("Serialization failed!", e);
            throw new AppException("Serialization failed!", e);
        }

        return new SignedInstagramUserResponse(serializedCookieStore, instagram.getUuid());
    }

    public InstagramUserResponse getInstagramUser(InstagramUserRequest request){
        Instagram4j instagram;
        InstagramSearchUsernameResult userResult;

        try {
            instagram = Instagram4j.builder()
                    .username(request.getUsername())
                    .password(request.getPassword())
                    .uuid(request.getUuid())
                    .cookieStore((CookieStore) deserializeFromString(request.getSerializedCookieStore()))
                    .build();
            instagram.setup();
        } catch (IOException|ClassNotFoundException e) {
            logger.error("Deserialization failed!", e);
            throw new AppException("Deserialization failed!", e);
        }

        try {
            userResult = instagram.sendRequest(new InstagramSearchUsernameRequest(request.getHandle()));
        } catch (IOException e) {
            logger.warn("Error while getting user!", e);
            throw new BadRequestException("Error while getting user!", e);
        }

        if (userResult.getStatus().equals("fail")) {
            // TODO Change into re-login logic
            throw new BadRequestException("Request failed: " + userResult.getMessage());
        }

        return ModelMapper.map4jUserToInstagramUserResponse(userResult.getUser());
    }
}
