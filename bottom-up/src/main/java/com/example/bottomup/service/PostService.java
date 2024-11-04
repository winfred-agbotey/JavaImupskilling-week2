package com.example.bottomup.service;


import com.example.bottomup.model.Post;
import com.example.bottomup.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public List<Post> getPostsByUser(Long userId) {
        return postRepository.findByUserId(userId);
    }
}
