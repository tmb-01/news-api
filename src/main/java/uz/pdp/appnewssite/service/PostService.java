package uz.pdp.appnewssite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.appnewssite.entity.Post;
import uz.pdp.appnewssite.entity.Role;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.PostDto;
import uz.pdp.appnewssite.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public List<Post> getAll() {
        return postRepository.findAll();
    }

    public Optional<Post> getById(Long id) {
        Optional<Post> optionalPost = postRepository.findById(id);
        return optionalPost;
    }

    public ApiResponse savePost(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setUrl(postDto.getUrl());

        postRepository.save(post);
        return new ApiResponse("Post saved", true);
    }

    public ApiResponse updatePost(Long id, PostDto postDto) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setTitle(postDto.getTitle());
            post.setTitle(postDto.getDescription());
            post.setUrl(postDto.getUrl());

            postRepository.save(post);
            return new ApiResponse("post updated", true);
        }
        return new ApiResponse("post with this id not found", false);
    }

    public ApiResponse deletePost(Long id) {

        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
            return new ApiResponse("delete", true);
        }
        return new ApiResponse("post with this id is not found", false);

    }

}
