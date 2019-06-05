package com.multiheaded.webapp.iapi.payload;

import javax.validation.constraints.NotBlank;

public abstract class AbstractSignedRequest {
    @NotBlank
    private String serializedCookieStore;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String uuid;

    public String getSerializedCookieStore() {
        return serializedCookieStore;
    }

    public void setSerializedCookieStore(String serializedCookieStore) {
        this.serializedCookieStore = serializedCookieStore;
    }

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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
