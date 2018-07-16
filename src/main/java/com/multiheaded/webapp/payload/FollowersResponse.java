package com.multiheaded.webapp.payload;

import com.multiheaded.webapp.domain.InstagramUser;

import java.util.Set;

public class FollowersResponse {
    private Set<InstagramUser> followers;

    public Set<InstagramUser> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<InstagramUser> followers) {
        this.followers = followers;
    }
}
