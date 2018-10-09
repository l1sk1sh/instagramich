package com.multiheaded.webapp.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

// TODO Finish RestTemplate consumer with getting key

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name="signed_instagram_user_cookie_stores")
public class SignedInstagramUserCookieStore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "id")
    private SignedInstagramUser sUser;

    @NotBlank
    private String key;

    SignedInstagramUserCookieStore() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public SignedInstagramUser getsUser() {
        return sUser;
    }

    public void setsUser(SignedInstagramUser sUser) {
        this.sUser = sUser;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

