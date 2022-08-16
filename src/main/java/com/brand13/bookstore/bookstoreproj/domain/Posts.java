package com.brand13.bookstore.bookstoreproj.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder //생성자와 마찬가지로 Builder도 생성 시점에 실행되나 정확하게 어떤 값을 변경해야 하는지 알 수 있다.
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        //값을 변경해주는 것 만으로 데이터베이스에 적용이 된다.
        this.title = title;
        this.content = content;
    }
}
