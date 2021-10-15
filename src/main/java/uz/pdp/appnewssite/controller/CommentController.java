package uz.pdp.appnewssite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.CommentDto;
import uz.pdp.appnewssite.service.CommentService;

@RestController
@RequestMapping("api/v1/post")
public class CommentController {

    @Autowired
    CommentService commentService;

    @PreAuthorize("hasAuthority('ADD_COMMENT')")
    @PostMapping
    public HttpEntity<?> addComment(@RequestBody CommentDto commentDto) {
        ApiResponse apiResponse = commentService.saveComment(commentDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize("hasAuthority('EDIT_COMMENT')")
    @PutMapping("{id}")
    public HttpEntity<?> updateComment(@PathVariable Long id, @RequestBody CommentDto commentDto) {
        ApiResponse apiResponse = commentService.updateComment(id, commentDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PreAuthorize("hasAuthority('DELETE_COMMENT')")
    @DeleteMapping("{id}")
    public HttpEntity<?> deleteComment(@PathVariable Long id) {
        ApiResponse apiResponse = commentService.deleteComment(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
