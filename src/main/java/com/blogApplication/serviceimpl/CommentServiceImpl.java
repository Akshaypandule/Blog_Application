package com.blogApplication.serviceimpl;

import com.blogApplication.dto.CommentDto;
import com.blogApplication.entity.Comment;
import com.blogApplication.entity.Post;
import com.blogApplication.exception.ResourceNotFoundException;
import com.blogApplication.repository.CommentRepo;
import com.blogApplication.repository.PostRepo;
import com.blogApplication.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepo commentRepo;
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto addComment(CommentDto commentDto, Integer postId)
    {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));

        Comment comment = modelMapper.map(commentDto, Comment.class);

        comment.setPost(post);

        Comment AddComment = commentRepo.save(comment);

        return new ModelMapper().map(comment,CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {

        Comment comment = commentRepo.findById(commentId).
                orElseThrow(() -> new ResourceNotFoundException("Comment", "Comment id", commentId));

        commentRepo.delete(comment);

    }
}
