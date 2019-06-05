package com.multiheaded.webapp.iapi.payload;

public class SignedInstagramUserResponse {
    private String encodedCookieStore;
    private String uuid;

    public SignedInstagramUserResponse(String encodedCookieStore, String uuid) {
        this.encodedCookieStore = encodedCookieStore;
        this.uuid = uuid;
    }

    public String getEncodedCookieStore() {
        return encodedCookieStore;
    }

    public void setEncodedCookieStore(String encodedCookieStore) {
        this.encodedCookieStore = encodedCookieStore;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
