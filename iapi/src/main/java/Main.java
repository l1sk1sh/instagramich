import java.io.*;

import org.apache.http.client.CookieStore;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramGetMediaLikersRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramGetMediaLikersResult;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String username = "oliver_johnson_work";
        String password = "QWEasd123";

        Instagram4j instagram = Instagram4j.builder().username(username).password(password).build();
        instagram.setup();
        instagram.login();

        File cookiesFile = new File(username + ".txt");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(cookiesFile));
        oos.writeObject(instagram.getCookieStore());
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(cookiesFile));
        CookieStore cookieStore = (CookieStore) ois.readObject();
        ois.close();

        Instagram4j instagram2 = Instagram4j.builder().username(username)
                .password(password)
                .uuid(instagram.getUuid())
                .cookieStore(cookieStore)
                .build();
        instagram2.setup();

        InstagramGetMediaLikersResult tagFeed = instagram2.sendRequest(new InstagramGetMediaLikersRequest(1020304050L));
    }
}
