package com.artineer.artineer23winterproject.repository;

import com.artineer.artineer23winterproject.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;


// JpaRepository를 상속하고 관리대상 entity와 관리대상 entity의 id타입을 기입
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
