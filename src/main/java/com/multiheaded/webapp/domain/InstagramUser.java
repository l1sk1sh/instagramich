package com.multiheaded.webapp.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "instagram_users")
public class InstagramUser {
    /*InstagramUser(is_private=false,
    is_verified=false,
    username=oliver_johnson_work,
    has_chaining=false,
    is_business=false,
    media_count=0,
    profile_pic_id=null,
    external_url=,
    full_name=Oliver Johnson,
    has_biography_translation=false,
    has_anonymous_profile_picture=true,
    is_favorite=false,
    public_phone_country_code=null,
    public_phone_number=null,
    public_email=null,
    pk=8017197124,
    geo_media_count=0,
    usertags_count=0,
    profile_pic_url=https://instagram.fevn1-1.fna.fbcdn.net/vp/da767c5fe26fe6a419aad4f352e7f45a/5BE3737A/t51.2885-19/11906329_960233084022564_1448528159_a.jpg,
    address_street=null,
    city_name=null,
    zip=null,
    direct_messaging=null,
    business_contact_method=null,
    biography=,
    follower_count=0,
    hd_profile_pic_versions=
        [InstagramProfilePic(
            super=org.brunocvcunha.instagram4j.requests.payload.InstagramProfilePic@3514a4c0,
            url=https://instagram.fevn1-1.fna.fbcdn.net/vp/da767c5fe26fe6a419aad4f352e7f45a/5BE3737A/t51.2885-19/11906329_960233084022564_1448528159_a.jpg,
            width=320,
            height=320
            ),
        InstagramProfilePic(
            super=org.brunocvcunha.instagram4j.requests.payload.InstagramProfilePic@212b5695,
            url=https://instagram.fevn1-1.fna.fbcdn.net/vp/da767c5fe26fe6a419aad4f352e7f45a/5BE3737A/t51.2885-19/11906329_960233084022564_1448528159_a.jpg,
            width=640,
            height=640
            )
        ],
    hd_profile_pic_url_info=
        InstagramProfilePic(
            super=org.brunocvcunha.instagram4j.requests.payload.InstagramProfilePic@446293d,
            url=https://instagram.fevn1-1.fna.fbcdn.net/vp/da767c5fe26fe6a419aad4f352e7f45a/5BE3737A/t51.2885-19/11906329_960233084022564_1448528159_a.jpg,
            width=150,
            height=150
        ),
    external_lynx_url=null,
    following_count=0,
    latitude=0.0,
    longitude=0.0)
    */

    // TODO One to Many with Pictures
    // TODO Decide if picture size are necessary

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

    public InstagramUser() {}

    public InstagramUser(Boolean isPrivate, String username, String fullName, Long pk, String profilePicUrl,
                         Integer followerCount) {
        this.isPrivate = isPrivate;
        this.username = username;
        this.fullName = fullName;
        this.pk = pk;
        this.profilePicUrl = profilePicUrl;
        this.followerCount = followerCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public String getProfilePicUrl() {
        return profilePicUrl;
    }

    public void setProfilePicUrl(String profilePicUrl) {
        this.profilePicUrl = profilePicUrl;
    }

    public Integer getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(Integer followerCount) {
        this.followerCount = followerCount;
    }
}
