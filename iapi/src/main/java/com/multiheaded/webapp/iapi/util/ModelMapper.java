package com.multiheaded.webapp.iapi.util;

import com.multiheaded.webapp.iapi.payload.InstagramUserResponse;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUser;

public class ModelMapper {

    public static InstagramUserResponse map4jUserToInstagramUserResponse(InstagramUser iUser) {
        InstagramUserResponse iResponse = new InstagramUserResponse();
        iResponse.setPrivate(iUser.is_private);
        iResponse.setUsername(iUser.getUsername());
        iResponse.setFullName(iUser.getFull_name());
        iResponse.setPk(iUser.getPk());
        iResponse.setProfilePicUrl(iUser.getHd_profile_pic_url_info().url);
        iResponse.setFollowerCount(iUser.getFollower_count());

        return iResponse;
    }
}
