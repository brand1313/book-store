package com.brand13.bookstore.bookstoreproj.controller;

import com.brand13.bookstore.bookstoreproj.controller.dto.PostsResponseDto;
import com.brand13.bookstore.bookstoreproj.controller.dto.PostsSaveRequestDto;
import com.brand13.bookstore.bookstoreproj.controller.dto.PostsUpdateRequestDto;
import com.brand13.bookstore.bookstoreproj.service.PostsService;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.KeycloakDeployment;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RestController
public class PostsApiController {

    private final PostsService postsService;
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    @Autowired
    public PostsApiController(PostsService postsService, HttpServletRequest request, HttpServletResponse response) {

        this.postsService = postsService;
        this.request = request;
        this.response = response;
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


    @GetMapping(value = "/logout")
    public void logout() throws ServletException, IOException {
        request.logout();
        response.sendRedirect("http://localhost:8080");
    }
}
