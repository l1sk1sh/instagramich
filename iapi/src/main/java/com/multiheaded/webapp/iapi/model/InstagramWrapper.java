package com.multiheaded.webapp.iapi.model;

import org.apache.http.client.CookieStore;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.springframework.stereotype.Component;

import java.io.*;

@Deprecated
public class InstagramWrapper extends Instagram4j implements Serializable {

    public InstagramWrapper() {
        super(null, null);
    }

    public InstagramWrapper(String username, String password) {
        super(username, password);
    }

    public InstagramWrapper(String username, String password, long userId, String uuid, CookieStore cookieStore) {
        super(username, password, userId, uuid, cookieStore);
    }

}
