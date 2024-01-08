package com.blogApplication.controller;

import com.blogApplication.dto.CommentDto;
import com.blogApplication.entity.Comment;
import com.blogApplication.response.ApiResponse;
import com.blogApplication.serviceimpl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;


    @PostMapping("/post/{postId}/comment")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment,
                                                    @PathVariable Integer postId)
    {
        CommentDto commentDto = commentService.addComment(comment, postId);
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }
    @DeleteMapping("/comment/{commentId}")
    public ApiResponse deleteComment(@PathVariable Integer commentId){
        commentService.deleteComment(commentId);

        return new ApiResponse("Comment delete Successfully",true);
    }
}
