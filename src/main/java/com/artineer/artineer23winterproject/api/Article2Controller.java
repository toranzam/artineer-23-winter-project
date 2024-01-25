package com.artineer.artineer23winterproject.api;


import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Article2Controller {

    @GetMapping("/articles/test")
    public String showArticleTestList() {

        return "articleApi/articlesApi";
    }


}
