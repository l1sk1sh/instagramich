package com.multiheaded.webapp.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class FollowingsRequest {
    @NotBlank
    @Size(max = 30)
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
