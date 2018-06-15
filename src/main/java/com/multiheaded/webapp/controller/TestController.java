package com.multiheaded.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// TODO Remove this. It is used for Docker configuration (to see if this shit works)
@Controller
public class TestController {

    @GetMapping("/")
    public String home() {
        return "/home";
    }

}
