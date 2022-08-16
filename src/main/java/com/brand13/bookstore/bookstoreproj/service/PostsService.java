package com.brand13.bookstore.bookstoreproj.service;

import com.brand13.bookstore.bookstoreproj.controller.dto.PostsListResponseDto;
import com.brand13.bookstore.bookstoreproj.controller.dto.PostsResponseDto;
import com.brand13.bookstore.bookstoreproj.controller.dto.PostsSaveRequestDto;
import com.brand13.bookstore.bookstoreproj.controller.dto.PostsUpdateRequestDto;
import com.brand13.bookstore.bookstoreproj.domain.Posts;
import com.brand13.bookstore.bookstoreproj.repository.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//@Transactional  //트랜잭션이 일어나는 메소드에만 transactional을 붙이면 된다.
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Autowired
    public PostsService(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        //save에서는 repository를 이용해 쿼리를 동작시켰지만 update에서는 repository를 사용하고 있지 않다.
        //entity의 값을 변경해주는 것만으로도 쿼리에 적용이 된다.
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postsRepository.delete(posts);
    }

    @Transactional(readOnly = true)
    public PostsResponseDto findById(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(posts);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
}
