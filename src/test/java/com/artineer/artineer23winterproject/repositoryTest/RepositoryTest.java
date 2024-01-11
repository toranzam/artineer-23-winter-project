package com.artineer.artineer23winterproject.repositoryTest;


import com.artineer.artineer23winterproject.dto.ArticleResponseDto;
import com.artineer.artineer23winterproject.entity.Article;
import com.artineer.artineer23winterproject.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RepositoryTest {
    @Autowired
    ArticleRepository articleRepository;

    @Test
    public void insertData() {

        for (int i = 0; i <100 ; i++) {
            // 객체를 만들어서
            Article article = Article.builder()
                    .title("title" + i)
                    .content("content" + i)
                    .build();

            // 저장
            articleRepository.save(article);
        }

    }
}
