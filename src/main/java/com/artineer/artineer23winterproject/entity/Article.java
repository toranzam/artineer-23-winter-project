package com.artineer.artineer23winterproject.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
// DB의 table와 패러다임을 맞추기위한 entity
// entity에서 데이터를 꺼내기 위해서는 Getter 필수!!
public class Article {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String content;

}
