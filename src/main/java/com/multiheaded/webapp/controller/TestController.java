package com.multiheaded.webapp.controller;

import com.multiheaded.webapp.domain.Person;
import com.multiheaded.webapp.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// TODO Remove this. It is used for Docker configuration (to see if this shit works)
@Controller
public class TestController {
    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/")
    public String home() {
        return "/home";
    }

    @GetMapping("/sql")
    @ResponseBody
    public String sql() {
        for (Person person : personRepository.findAll()) {
            System.out.println("foo: " + person.getInstaUsername());
        }
        return "Who are you? What are you doing here? ALARM";
    }
}
