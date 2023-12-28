package com.artineer.artineer23winterproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {


    @GetMapping("/")
    public String showIndexPage() {
        // Spring MVC View 리졸버
        return "index";

    }

}
