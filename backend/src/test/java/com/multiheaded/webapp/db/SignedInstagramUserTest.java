package com.multiheaded.webapp.db;

import com.multiheaded.webapp.backend.Application;
import com.multiheaded.webapp.backend.domain.InstagramUser;
import com.multiheaded.webapp.backend.domain.SignedInstagramUser;
import com.multiheaded.webapp.backend.domain.User;
import com.multiheaded.webapp.backend.exception.ResourceNotFoundException;
import com.multiheaded.webapp.backend.repo.InstagramUserRepository;
import com.multiheaded.webapp.backend.repo.SignedInstagramUserRepository;
import com.multiheaded.webapp.backend.repo.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SignedInstagramUserTest {

    @Autowired
    private InstagramUserRepository iRepository;

    @Autowired
    private SignedInstagramUserRepository sRepository;

    @Autowired
    private UserRepository userRepository;

    private boolean isPrivate = false;
    private String username = "testuser0";
    private String fullName = "Velriy Grigorovich Stupka";
    private Long pk = 123890L;
    private String picUrl = "http://random.url";
    private int followerCount = 0;

    private InstagramUser user;

    @Before
    public void init() {
        user = new InstagramUser();
        user.setPrivate(isPrivate);
        user.setUsername(username);
        user.setFullName(fullName);
        user.setPk(pk);
        user.setProfilePicUrl(picUrl);
        user.setFollowerCount(followerCount);

        iRepository.save(user);
    }

    @Test
    public void saveNewSignedInstagramUser() {
        SignedInstagramUser sUser = new SignedInstagramUser();
        sUser.setPassword("asdsadasdasd");
        sUser.setInstagramUser(user);

        sRepository.save(sUser);
        System.out.println("");
    }
}
