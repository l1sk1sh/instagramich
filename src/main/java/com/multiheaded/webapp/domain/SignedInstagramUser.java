package com.multiheaded.webapp.domain;

import com.multiheaded.webapp.domain.audit.DateAudit;
import com.multiheaded.webapp.domain.audit.UserDateAudit;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "signed_instagram_users")
public class SignedInstagramUser extends UserDateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String password;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "username", nullable = false)
    private InstagramUser instagramUser;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "signed_user_followers",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "username"))
    private Set<InstagramUser> followers = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "signed_user_followings",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "username"))
    private Set<InstagramUser> followings = new HashSet<>();

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
        return instagramUser;
    }

    public void setInstagramUser(InstagramUser instagramUser) {
        this.instagramUser = instagramUser;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
