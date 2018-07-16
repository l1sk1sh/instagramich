package com.multiheaded.webapp.payload;

import com.multiheaded.webapp.domain.InstagramUser;

import java.util.Set;

public class FollowingsResponse {
    private Set<InstagramUser> followings;

    public Set<InstagramUser> getFollowers() {
        return followings;
    }

    public void setFollowers(Set<InstagramUser> followings) {
        this.followings = followings;
    }
}
