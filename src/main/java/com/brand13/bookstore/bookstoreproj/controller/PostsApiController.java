package com.brand13.bookstore.bookstoreproj.controller;

import com.brand13.bookstore.bookstoreproj.controller.dto.PostsResponseDto;
import com.brand13.bookstore.bookstoreproj.controller.dto.PostsSaveRequestDto;
import com.brand13.bookstore.bookstoreproj.controller.dto.PostsUpdateRequestDto;
import com.brand13.bookstore.bookstoreproj.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostsApiController {

    private final PostsService postsService;

    @Autowired
    public PostsApiController(PostsService postsService) {
        this.postsService = postsService;
    }

    /**
     * 등록
     *
     * @param requestDto
     * @return
     */

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    /**
     * 업데이트
     *
     * @param id
     * @param requestDto
     * @return
     */
    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }

    /**
     * 조회
     *
     * @param id
     * @return
     */
    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }
}
