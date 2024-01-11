package com.artineer.artineer23winterproject.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
// DB의 table와 패러다임을 맞추기위한 entity
// entity에서 데이터를 꺼내기 위해서는 Getter 필수!!
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String content;

}
