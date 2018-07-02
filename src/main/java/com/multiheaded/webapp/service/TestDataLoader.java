package com.multiheaded.webapp.service;

import com.multiheaded.webapp.domain.Person;
import com.multiheaded.webapp.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestDataLoader implements CommandLineRunner {
    private final PersonRepository repository;

    @Autowired
    public TestDataLoader(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {
        this.repository.save(new Person("frodobaggins89", "Frodo", "Baggins"));
    }

}
