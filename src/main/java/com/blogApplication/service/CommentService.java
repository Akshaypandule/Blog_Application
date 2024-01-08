package com.blogApplication.service;

import com.blogApplication.dto.CommentDto;
public interface CommentService {

    CommentDto addComment(CommentDto commentDto,Integer postId);

    void deleteComment(Integer commentId);
}
