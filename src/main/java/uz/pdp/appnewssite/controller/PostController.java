package uz.pdp.appnewssite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appnewssite.entity.Post;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.PostDto;
import uz.pdp.appnewssite.service.PostService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/post")
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping
    public List<Post> getAll() {
        return postService.getAll();
    }

    @GetMapping("{id}")
    public HttpEntity<?> getById(@PathVariable Long id) {
        Optional<Post> optionalPost = postService.getById(id);
        if (optionalPost.isPresent()) {
            return ResponseEntity.ok(optionalPost.get());
        }
        return ResponseEntity.status(404).body(new ApiResponse("post with this id is not found", false));
    }

    @PreAuthorize("hasAuthority('ADD_POST')")
    @PostMapping
    public HttpEntity<?> addPost(@RequestBody PostDto postDto) {
        ApiResponse apiResponse = postService.savePost(postDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize("hasAuthority('EDIT_POST')")
    @PutMapping("{id}")
    public HttpEntity<?> updatePost(@PathVariable Long id, @RequestBody PostDto postDto) {
        ApiResponse apiResponse = postService.updatePost(id, postDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize("hasAuthority('DELETE_POST')")
    @DeleteMapping("{id}")
    public HttpEntity<?> deletePost(@PathVariable Long id) {
        ApiResponse apiResponse = postService.deletePost(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
