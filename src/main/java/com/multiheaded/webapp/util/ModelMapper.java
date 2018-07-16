package com.multiheaded.webapp.util;

import com.multiheaded.webapp.domain.InstagramUser;
import com.multiheaded.webapp.domain.SignedInstagramUser;
import com.multiheaded.webapp.payload.SignedInstagramUserResponse;
import com.multiheaded.webapp.repo.SignedInstagramUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ModelMapper {

    @Autowired
    static SignedInstagramUserRepository sRepo;

    public static SignedInstagramUserResponse mapSignedInstagramUserToResponse(SignedInstagramUser sUser) {
        SignedInstagramUserResponse sResponse = new SignedInstagramUserResponse();
        sResponse.setId(sUser.getId());
        sResponse.setInstagramUser(sUser.getInstagramUser());

        String username = sUser.getInstagramUser().getUsername();
        Set<InstagramUser> followers = sRepo.findFollowersBySignedUsername(username);

        sResponse.setFollowers(followers);
        sResponse.setFollowings(sRepo.findFollowingsBySignedUsername(sUser.getInstagramUser().getUsername()));

        return sResponse;
    }

}
