package com.multiheaded.webapp.db;

import com.multiheaded.webapp.domain.User;
import com.multiheaded.webapp.repo.UserRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainDbTest {

    @Autowired
    private UserRepository userRepository;

    private String username = "valeriy2";
    private String email = "test@gmail.com";
    private String name = "Valeriy";
    private String password = "12345qqwe";

    @Before
    public void init() {

        User user = new User();

        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        user.setUsername(username);

        userRepository.save(user);
        userRepository.save(user);
    }

    /**
     * Test should find the author's book from the PostgreSQL database.
     */
    @Test
    public void testShouldFindUser() {

        Optional<User> user = userRepository.findByEmail(email);

        Assert.assertNotNull(user);

        System.out.println("The user " + user.toString() + " was found");
    }

    @After
    public void purge() {
        userRepository.deleteByEmail(email);
    }
}
