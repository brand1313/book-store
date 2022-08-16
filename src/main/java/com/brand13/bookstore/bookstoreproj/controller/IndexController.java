package com.brand13.bookstore.bookstoreproj.controller;

import com.brand13.bookstore.bookstoreproj.controller.dto.PostsResponseDto;
import com.brand13.bookstore.bookstoreproj.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

    private final PostsService postsService;

    @Autowired
    public IndexController(PostsService postsService) {
        this.postsService = postsService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id); //객체끼리 받을 수 있다.
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
