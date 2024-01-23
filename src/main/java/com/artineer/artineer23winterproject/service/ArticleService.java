package com.artineer.artineer23winterproject.service;

import com.artineer.artineer23winterproject.dto.ArticleDto;
import com.artineer.artineer23winterproject.dto.ArticleResponseDto;
import com.artineer.artineer23winterproject.dto.PageDto;
import com.artineer.artineer23winterproject.entity.Article;
import com.artineer.artineer23winterproject.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;


    public Article toEntity(ArticleDto articleDto, Principal principal) {
        Article article = Article.builder()
                .title(articleDto.getTitle())
                .content(articleDto.getContent())
                .author(principal.getName())
                .createdAt(LocalDateTime.now())
                .build();
        return article;
    }

    public Pageable toPageable(PageDto pageDto) {
        Pageable pageable = PageRequest.of(
                pageDto.getPage() - 1,
                // 가져올 데이터양 기본 10으로 설정
                pageDto.getSize(),
                // 정렬기준은 "id"컬럼이며 내림차순으로 정렬
                Sort.by("id").descending());
        return pageable;
    }


    public ArticleResponseDto toArticleresponseDto(PageDto pageDto) {
        ArticleResponseDto articleResponseDto = ArticleResponseDto.builder()
                .pageDto(pageDto)
                .total(articleRepository.count())
                .build();
        return articleResponseDto;
    }

}
