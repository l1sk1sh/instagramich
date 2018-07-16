package com.multiheaded.webapp.payload;

import com.multiheaded.webapp.domain.InstagramUser;

import java.util.Set;

public class SignedInstagramUserResponse {
    private Long id;

    private InstagramUser instagramUser;

    private Set<InstagramUser> followers;

    private Set<InstagramUser> followings;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InstagramUser getInstagramUser() {
        return instagramUser;
    }

    public void setInstagramUser(InstagramUser instagramUser) {
        this.instagramUser = instagramUser;
    }

    public Set<InstagramUser> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<InstagramUser> followers) {
        this.followers = followers;
    }

    public Set<InstagramUser> getFollowings() {
        return followings;
    }

    public void setFollowings(Set<InstagramUser> followings) {
        this.followings = followings;
    }
}