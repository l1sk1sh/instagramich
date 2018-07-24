package com.multiheaded.webapp.backend.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignedInstagramUserAuthRequest {
    @NotBlank
    @Size(max = 30)
    private String username;

    @NotBlank
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
