package com.multiheaded.webapp.backend.util;

import com.multiheaded.webapp.backend.domain.InstagramUser;
import com.multiheaded.webapp.backend.domain.SignedInstagramUser;
import com.multiheaded.webapp.backend.payload.InstagramUserResponse;
import com.multiheaded.webapp.backend.payload.SignedInstagramUserResponse;

public class ModelMapper {

    public static SignedInstagramUserResponse mapSignedInstagramUserToResponse(SignedInstagramUser sUser) {
        SignedInstagramUserResponse sResponse = new SignedInstagramUserResponse();
        sResponse.setId(sUser.getId());
        sResponse.setiUser(sUser.getInstagramUser());
        sResponse.setFollowersCount(sUser.getFollowers().size());
        sResponse.setFollowingsCount(sUser.getFollowings().size());

        return sResponse;
    }

    public static InstagramUserResponse mapInstagramUsertoResponse(InstagramUser iUser) {
        InstagramUserResponse iResponse = new InstagramUserResponse();
        iResponse.setId(iUser.getId());
        iResponse.setPrivate(iUser.getPrivate());
        iResponse.setUsername(iUser.getUsername());
        iResponse.setFullName(iUser.getFullName());
        iResponse.setPk(iUser.getPk());
        iResponse.setProfilePicUrl(iUser.getProfilePicUrl());
        iResponse.setFollowerCount(iUser.getFollowerCount());

        return iResponse;
    }

}
