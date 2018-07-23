package com.multiheaded.webapp.backend.util;

import com.multiheaded.webapp.backend.domain.InstagramUser;
import com.multiheaded.webapp.backend.domain.SignedInstagramUser;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class MockModel {

    private static Random rand = new Random();

    public static InstagramUser mockInstagramUser() {
        InstagramUser iUser = new InstagramUser();
        iUser.setPrivate(rand.nextBoolean());
        iUser.setUsername(generateRandomString(rand.nextInt(20) + 10));
        iUser.setFullName(generateRandomString(rand.nextInt(10) + 5) + " " +
                generateRandomString(rand.nextInt(12) + 5));
        iUser.setPk(ThreadLocalRandom.current().nextLong(10000, 100000));
        iUser.setProfilePicUrl("http://" + (rand.nextInt(1000) + 100) + "/");
        iUser.setFollowerCount(rand.nextInt(2000) + 15);

        return iUser;
    }

    public static InstagramUser mockInstagramUser(String username) {
        InstagramUser iUser = mockInstagramUser();
        iUser.setUsername(username);

        return iUser;
    }

    public static SignedInstagramUser mockSignedInstagramUser(InstagramUser iUser, String password) {
        SignedInstagramUser sUser = new SignedInstagramUser();
        sUser.setPassword(password);
        sUser.setInstagramUser(iUser);

        return sUser;
    }

    private static String generateRandomString(int length) {
        return UUID.randomUUID().toString().substring(0, length).replaceAll("-", "");
    }
}
