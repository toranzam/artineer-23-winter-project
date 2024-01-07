package com.artineer.artineer23winterproject.controller;

import com.artineer.artineer23winterproject.dto.ArticleDto;
import com.artineer.artineer23winterproject.dto.ArticleResponseDto;
import com.artineer.artineer23winterproject.dto.PageDto;
import com.artineer.artineer23winterproject.entity.Article;
import com.artineer.artineer23winterproject.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final JdbcTemplate jdbcTemplate;
    // jdbc, jdbc template 2가지 있음
    // jdbc template 은 스프링이 지원해주는것

    private final ArticleRepository articleRepository;

    @GetMapping("/articles")
    public String showArticles(Model model, PageDto pageDto) {


        // page 번호는 0번부터
        // pageDto.getPage()의 값은 1이 기본값이기때문에 -1
        Pageable pageable = PageRequest.of(
                pageDto.getPage() - 1,
                // 가져올 데이터양 기본 10으로 설정
                pageDto.getSize(),
                // 정렬기준은 "id"컬럼이며 내림차순으로 정렬
                Sort.by("id").descending());

        // 만든 Pageable 객체를 finAll 할때 파라미터로 넣으면 Page 로 감싸진 entity 가 나온다
        Page<Article> articles = articleRepository.findAll(pageable);

//        List<Article> articles = all.getContent();

        ArticleResponseDto articleResponseDto = ArticleResponseDto.builder()
                .pageDto(pageDto)
                .total(articleRepository.count())
                .build();


        model.addAttribute("dto", articleResponseDto);
        model.addAttribute("articleList", articles);

        return "article/articles";
    }

    @GetMapping("/articles/new")
    public String showNewArticle() {
        return "article/newArticle";
    }

    @PostMapping("/articles/new")
    public String createArticle(ArticleDto articleDto) {
        System.out.println("articleDto = " + articleDto);

        Article article = Article.builder()
                .title(articleDto.getTitle())
                .content(articleDto.getContent())
                .build();

        articleRepository.save(article);


        return "redirect:/articles";
    }

    @GetMapping("/articles/{id}")
    public String showArticleDetail(@PathVariable("id") Long id, Model model) {

        Article article = articleRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        model.addAttribute("article", article);

        return "article/articleDetail";
    }


}
