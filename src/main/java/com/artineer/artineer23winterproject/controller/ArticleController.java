package com.artineer.artineer23winterproject.controller;

import com.artineer.artineer23winterproject.dto.ArticleDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {

    @GetMapping("/articles")
    public String showArticles() {
        return "article/articles";
    }

    @GetMapping("/articles/new")
    public String showNewArticle() {
        return "article/newArticle";
    }

    @PostMapping("/articles/new")
    public String createArticle(ArticleDto articleDto) {
        System.out.println("articleDto = " + articleDto);
        return null;
    }



}
