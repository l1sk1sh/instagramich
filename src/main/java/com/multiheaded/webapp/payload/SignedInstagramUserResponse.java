package com.multiheaded.webapp.payload;

import com.multiheaded.webapp.domain.InstagramUser;

import java.util.Set;

public class SignedInstagramUserResponse {
    private Long id;

    private InstagramUser iUser;

    private int followersCount;

    private int followingsCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InstagramUser getiUser() {
        return iUser;
    }

    public void setiUser(InstagramUser iUser) {
        this.iUser = iUser;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    public int getFollowingsCount() {
        return followingsCount;
    }

    public void setFollowingsCount(int followingsCount) {
        this.followingsCount = followingsCount;
    }
}