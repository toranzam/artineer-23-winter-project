package com.artineer.artineer23winterproject.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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

    @Column(columnDefinition = "LONGTEXT")
    private String content;

    private String author;

    private LocalDateTime createdAt;

}
