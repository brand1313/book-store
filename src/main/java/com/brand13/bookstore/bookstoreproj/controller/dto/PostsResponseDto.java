package com.brand13.bookstore.bookstoreproj.controller.dto;

import com.brand13.bookstore.bookstoreproj.domain.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity) { //Lombok을 쓰면 변수가 4개가 된다. entity를 받으면 되니까 생성자를 선언했다.
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
