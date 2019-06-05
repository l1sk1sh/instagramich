package com.multiheaded.webapp.backend.payload;

import com.multiheaded.webapp.backend.domain.InstagramUser;

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