package com.artineer.artineer23winterproject.api;


import com.artineer.artineer23winterproject.dto.article.ArticleResponseDto;
import com.artineer.artineer23winterproject.entity.Article;
import com.artineer.artineer23winterproject.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ArticleApiController {

    private final ArticleRepository articleRepository;

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponseDto>> showArticleData() {

        List<Article> articleList = articleRepository.findAll();

        List<ArticleResponseDto> collect = articleList.stream()
                .map(m -> ArticleResponseDto.builder()
                        .title(m.getTitle())
                        .content(m.getContent())
                        .build())
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(collect);

    }


}
