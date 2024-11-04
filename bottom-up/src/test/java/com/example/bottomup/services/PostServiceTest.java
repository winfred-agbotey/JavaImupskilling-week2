package com.example.bottomup.services;


import com.example.bottomup.model.Post;
import com.example.bottomup.model.User;
import com.example.bottomup.repository.PostRepository;
import com.example.bottomup.service.PostService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class PostServiceTest {
    @Autowired
    private PostService postService;

    @MockBean
    private PostRepository postRepository;


    @Test
    void testCreatePost() {
        Post post = new Post("Post one", LocalDateTime.now(), new User("mawuli", "mawuli@gmail.com"));
        when(postRepository.save(ArgumentMatchers.any(Post.class))).thenReturn(post);

        Post result = postService.createPost(post);
        assertNotNull(result);
        assertEquals("Post one", result.getContent());
    }

    @Test
    void testGetPostsByUser() {
        User user = new User("winfred", "winfred@example.com");
        List<Post> posts = List.of(new Post("Post one", LocalDateTime.now(), user));
        when(postRepository.findByUserId(1L)).thenReturn(posts);

        List<Post> result = postService.getPostsByUser(1L);
        assertFalse(result.isEmpty());
        assertEquals("Post one", result.get(0).getContent());
    }
}
