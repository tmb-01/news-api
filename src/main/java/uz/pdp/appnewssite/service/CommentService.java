package uz.pdp.appnewssite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.appnewssite.entity.Comment;
import uz.pdp.appnewssite.entity.Post;
import uz.pdp.appnewssite.entity.User;
import uz.pdp.appnewssite.entity.enums.RoleNames;
import uz.pdp.appnewssite.payload.ApiResponse;
import uz.pdp.appnewssite.payload.CommentDto;
import uz.pdp.appnewssite.payload.PostDto;
import uz.pdp.appnewssite.repository.CommentRepository;
import uz.pdp.appnewssite.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    public ApiResponse saveComment(CommentDto commentDto) {

        Optional<Post> optionalPost = postRepository.findById(commentDto.getPostId());

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();

            Comment comment = new Comment();
            comment.setCommentText(commentDto.getCommentText());
            comment.setPost(post);

            commentRepository.save(comment);
            return new ApiResponse("comment saved", true);
        }
        return new ApiResponse("post with this id is not exist", false);
    }

    public ApiResponse updateComment(Long id, CommentDto commentDto) {

        Optional<Comment> optionalComment = commentRepository.findById(id);

        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();

            Optional<Post> optionalPost = postRepository.findById(commentDto.getPostId());

            if (optionalPost.isPresent()) {
                Post post = optionalPost.get();

                comment.setCommentText(commentDto.getCommentText());
                comment.setPost(post);

                commentRepository.save(comment);
                return new ApiResponse("comment saved", true);
            }
            return new ApiResponse("post with this id is not exist", false);
        }
        return new ApiResponse("comment with this id is not exist", false);
    }

    public ApiResponse deleteComment(Long id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);

        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            boolean allow = false;
            for (GrantedAuthority authority : user.getAuthorities()) {
                if (authority.getAuthority().equals(RoleNames.DELETE_COMMENT.name())) {
                    allow = true;
                    break;
                }
            }

            if (comment.getCreatedBy().getId().equals(user.getId())) {
                allow = true;
            }

            if (allow) {
                commentRepository.deleteById(id);
                return new ApiResponse("deleted", true);
            }
            return new ApiResponse("You are not allowed to delete this comment", false);
        }
        return new ApiResponse("comment with this id is not found", false);
    }

}
