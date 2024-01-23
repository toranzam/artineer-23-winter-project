package com.artineer.artineer23winterproject.controller;

import com.artineer.artineer23winterproject.dto.ArticleDto;
import com.artineer.artineer23winterproject.dto.ArticleResponseDto;
import com.artineer.artineer23winterproject.dto.PageDto;
import com.artineer.artineer23winterproject.entity.Article;
import com.artineer.artineer23winterproject.repository.ArticleRepository;
import com.artineer.artineer23winterproject.service.ArticleService;
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

import java.security.Principal;
import java.time.LocalDateTime;
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

    private final ArticleService articleService;

    @GetMapping("/articles")
    public String showArticles(Model model, PageDto pageDto) {


        /* page 번호는 0번부터 */
        /* pageDto.getPage()의 값은 1이 기본값이기때문에 -1 */
        Pageable pageable = articleService.toPageable(pageDto);

        /* 만든 Pageable 객체를 finAll 할때 파라미터로 넣으면 Page 로 감싸진 entity 가 나온다 */
        Page<Article> all = articleRepository.findAll(pageable);

        /* Page 로 감사진 entity 를 꺼냄 */
        List<Article> articles = all.getContent();

        /* paging 처리에 필요한 값들을 초기화 필요값 (현재페이지, 가져올페이지갯수, 총데이터갯수) */
        ArticleResponseDto articleResponseDto = articleService.toArticleresponseDto(pageDto);


        /* paging 알고리즘 처리된 dto 를 모델에 등록 */
        model.addAttribute("dto", articleResponseDto);
        /* entity 를 모델에 등록 */
        model.addAttribute("articleList", articles);

        return "article/articles";
    }




    @GetMapping("/articles/new")
    public String showNewArticle() {
        return "article/newArticle";
    }

    @PostMapping("/articles/new")
    public String createArticle(ArticleDto articleDto, Principal principal) {

        Article article = articleService.toEntity(articleDto, principal);

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
