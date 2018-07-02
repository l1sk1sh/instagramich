package com.multiheaded.webapp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// TODO @hayle Замінив імена змінних (як на мене зрозуміліше) та форматування (по конвенції). Якщо проти, можемо оговорити code style

@Entity
public class Person {
    @Id
    private String instaUsername;
    private String fName;
    private String lName;

    public Person() {}

    public Person(String instaUsername, String fName, String lName) {
        this.instaUsername = instaUsername;
        this.fName = fName;
        this.lName = lName;
    }

    public String getInstaUsername() {
        return instaUsername;
    }

    public void setInstaUsername(String instaUsername) {
        this.instaUsername = instaUsername;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("First name: ").append(fName).append("" +
                "\nLast name: ").append(lName);
        return sb.toString();
    }
}
