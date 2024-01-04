package com.artineer.artineer23winterproject.controller;

import com.artineer.artineer23winterproject.dto.ArticleDto;
import com.artineer.artineer23winterproject.entity.Article;
import com.artineer.artineer23winterproject.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final JdbcTemplate jdbcTemplate;
    // jdbc, jdbc template 2가지 있음
    // jdbc template 은 스프링이 지원해주는것

    private final ArticleRepository articleRepository;

    @GetMapping("/articles")
    public String showArticles(Model model) {

//        // data를 가져오기 위한 sql문
//        String sql = "select * from article";
//
//        // jdbcTemplate을 통해 DB에 쿼리를 날려서 데이터를 객체로 변환
//        List<ArticleDto> articleDto = jdbcTemplate.query(sql, new RowMapper<ArticleDto>() {
//            @Override
//            public ArticleDto mapRow(ResultSet rs, int rowNum) throws SQLException {
//                String title = rs.getString("title");
//                String content = rs.getString("content");
//                return new ArticleDto( title, content);
//            }
//        });

        List<Article> articles = articleRepository.findAll();


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

//        // data를 넣기위한 sql문
//        String sql = "insert into article (title, content) values(?, ?)";
//        jdbcTemplate.update(sql, articleDto.getTitle(), articleDto.getContent());

        Article article = Article.builder()
                .title(articleDto.getTitle())
                .content(articleDto.getContent())
                .build();

        articleRepository.save(article);


        return "redirect:/articles";
    }

    @GetMapping("/articles/{id}")
    public String showArticleDetail(@PathVariable("id")Long id, Model model) {

        Article article = articleRepository.findById(id)
                .orElseThrow(IllegalArgumentException::new);

        model.addAttribute("article", article);

        return "article/articleDetail";
    }





}
