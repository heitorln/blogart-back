package com.example.blogart.services;

import com.example.blogart.domain.post.Post;
import com.example.blogart.dtos.post.PostResponseDTO;
import com.example.blogart.repositories.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    PostRepository repository;
    @Autowired
    TokenService tokenService;

    public PostResponseDTO createPost(Post post){
        // TODO timezone
        Timestamp currentTS = new Timestamp(System.currentTimeMillis());
        post.setCreatedAt(currentTS);

        Post createdPost = repository.save(post);

        return new PostResponseDTO(createdPost);
    }

    public List<PostResponseDTO> getAllPosts(){
        return repository.findAll().stream().map(PostResponseDTO::new).toList();
    }

    public PostResponseDTO getPostById(Long id){
        Optional<Post> optionalPost = repository.findById(id);

        return optionalPost.map(PostResponseDTO::new).orElse(null);
    }

    public void deletePost(Long postId, Long userId, String token){
        Optional<Post> optionalPost = repository.findById(postId);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();

            if (post.getUser().getId().equals(userId) || tokenService.validateToken(token, post.getUser().getUsername())){
                repository.deleteById(postId);
            } else {
                throw new AccessDeniedException("You are not authorized to delete this post.");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found with ID: " + postId);
        }
    }
}
