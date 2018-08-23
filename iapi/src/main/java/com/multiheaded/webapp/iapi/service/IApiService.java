package com.multiheaded.webapp.iapi.service;

import com.multiheaded.webapp.iapi.model.InstagramWrapper;
import com.multiheaded.webapp.iapi.payload.InstagramUserAuthRequest;
import com.multiheaded.webapp.iapi.payload.InstagramUserResponse;
import com.multiheaded.webapp.iapi.util.ModelMapper;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramSearchUsernameRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramSearchUsernameResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class IApiService {

    private static final Logger logger = LoggerFactory.getLogger(IApiService.class);

    public InstagramUserResponse signinInstagramUser(InstagramUserAuthRequest request) throws IOException {

        Instagram4j wrapper = new Instagram4j(request.getUsername(), request.getPassword());

        wrapper.setup();
        wrapper.login();

        return getInstagramUser(request.getUsername());
    }


    public InstagramUserResponse getInstagramUser(String handle) throws IOException {
        // TODO Fix new InstagramWrapper() shit
        InstagramSearchUsernameResult userResult = new InstagramWrapper().sendRequest(new InstagramSearchUsernameRequest(handle));

        return ModelMapper.map4jUserToInstagramUserResponse(userResult.getUser());
    }
}
