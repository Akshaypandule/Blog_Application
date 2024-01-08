package com.blogApplication.service;

import com.blogApplication.dto.PostDto;
import com.blogApplication.entity.Post;
import com.blogApplication.response.PageResponse;

import java.util.List;

public interface PostService {

    // Create
    PostDto createPost(PostDto postDto,Integer userid,Integer categoryId);

    // Update

    PostDto updatePost(PostDto postDto,Integer postId);

    // get All post
     PageResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);

     // get Single Post

    PostDto getPostById(Integer postId);

    // Delete Post

    void deletePost(Integer postId);

    // getAll Post by using Category

    public List<PostDto> getAllPostsByCategory(Integer categoryId);

    List<PostDto> getPostByUser(Integer userId);

    List<PostDto> searchPosts(String keyword);




}
