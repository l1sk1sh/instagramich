package com.multiheaded.webapp.backend.domain;

import com.multiheaded.webapp.backend.domain.audit.UserDateAudit;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "signed_instagram_users")
public class SignedInstagramUser extends UserDateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String password;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "instagram_user_id", nullable = false)
    private InstagramUser iUser;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "signed_user_followers",
            joinColumns = @JoinColumn(name = "signed_instagram_user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "instagram_user_id", referencedColumnName = "id"))
    private Set<InstagramUser> followers;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "signed_user_followings",
            joinColumns = @JoinColumn(name = "signed_instagram_user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "instagram_user_id", referencedColumnName = "id"))
    private Set<InstagramUser> followings;

    @OneToOne
    private SignedInstagramUserCookieStore cookieStore;

    public SignedInstagramUser() {}

    public SignedInstagramUser(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public InstagramUser getInstagramUser() {
        return iUser;
    }

    public void setInstagramUser(InstagramUser iUser) {
        this.iUser = iUser;
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

    public SignedInstagramUserCookieStore getCookieStore() {
        return cookieStore;
    }

    public void setCookieStore(SignedInstagramUserCookieStore cookieStore) {
        this.cookieStore = cookieStore;
    }
}
