package com.multiheaded.webapp.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "signed_instagram_users")
public class SignedInstagramUser extends InstagramUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private Boolean isPrivate;

    @NotBlank
    @Size(max = 30)
    private String username;

    @Size(max = 150)
    private String fullName;

    @NotBlank
    @Size(max = 11)
    private Long pk;

    @NotBlank
    private String profilePicUrl;

    @NotBlank
    private Integer followerCount;

    @NotBlank
    private String password;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public SignedInstagramUser() {}

    // TODO Fuck, it probably won't work for @Entity. Design it!
    public SignedInstagramUser(Boolean isPrivate, String username, String fullName,
                               Long pk, String profilePicUrl, Integer followerCount) {
        super(isPrivate, username, fullName, pk, profilePicUrl, followerCount);
        this.password = password; // TODO Do not forget to encrypt Controller's sent password
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
