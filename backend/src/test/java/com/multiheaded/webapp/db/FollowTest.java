package com.multiheaded.webapp.db;

import com.multiheaded.webapp.backend.Application;
import com.multiheaded.webapp.backend.domain.InstagramUser;
import com.multiheaded.webapp.backend.domain.SignedInstagramUser;
import com.multiheaded.webapp.backend.exception.ResourceNotFoundException;
import com.multiheaded.webapp.backend.repo.SignedInstagramUserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class FollowTest {
    private String username;

    @Autowired
    SignedInstagramUserRepository sRepository;

    @Before
    public void init() {
        SignedInstagramUser sUser = sRepository.findById(1L).orElseThrow(() ->
                new ResourceNotFoundException("SignedInstagramUser", "id", "1"));
        username = sUser.getInstagramUser().getUsername();
    }

    @Test
    public void findFollowers() {
        Set<InstagramUser> followers = sRepository.findFollowersBySUsername(username);
        System.out.println("Followers of signed user: " + username);
        System.out.println(Arrays.toString(followers.toArray()));

    }

    @Test
    public void findFollowings() {
        Set<InstagramUser> followings = sRepository.findFollowingsBySUsername(username);
        System.out.println("Followings of signed user: " + username);
        System.out.println(Arrays.toString(followings.toArray()));
    }

}
