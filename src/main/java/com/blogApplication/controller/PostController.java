package com.blogApplication.controller;

import com.blogApplication.dto.PostDto;
import com.blogApplication.response.ApiResponse;
import com.blogApplication.response.ApplicationConstant;
import com.blogApplication.response.PageResponse;
import com.blogApplication.service.FileService;
import com.blogApplication.serviceimpl.PostServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostServiceImpl postServiceImpl;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
    private String path;

    @PostMapping("/user/{userid}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> addPost(
            @RequestBody PostDto postDto,
            @PathVariable Integer userid,
            @PathVariable Integer categoryId){

        PostDto post = postServiceImpl.createPost(postDto, userid, categoryId);

        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    // get post by using user id
    @GetMapping("/user/{id}/post")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer id){

        List<PostDto> postByUser = postServiceImpl.getPostByUser(id);
        return new ResponseEntity<>(postByUser,HttpStatus.OK);
    }

    //  Get Post Category wise
    @GetMapping("/category/{id}/post")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer id){

        List<PostDto> postsByCategory = postServiceImpl.getAllPostsByCategory(id);
        return new ResponseEntity<>(postsByCategory,HttpStatus.OK);
    }

    // Get Post by using PostId
    @GetMapping("/post/{id}")
    public ResponseEntity<PostDto> getPostByPostId(@PathVariable Integer id){
        PostDto postById = postServiceImpl.getPostById(id);
        return new ResponseEntity<>(postById,HttpStatus.OK);
    }

    // Get All Posts
    @GetMapping("/posts")
    public ResponseEntity <PageResponse> getAllPost(
            @RequestParam(value = "pageNumber",defaultValue = ApplicationConstant.PAGE_NUMBER,required = false)Integer pageNumber,
            @RequestParam(value="pageSize",defaultValue = ApplicationConstant.PAGE_SIZE,required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = ApplicationConstant.SORT_BY,required = false) String sortBy,
            @RequestParam(value="sortDir",defaultValue = ApplicationConstant.SORT_DIR,required = false) String sortDir)
    {
          PageResponse pageResponse= postServiceImpl.getAllPost(pageNumber,pageSize,sortBy,sortDir);
        return  new ResponseEntity<>(pageResponse,HttpStatus.OK);
    }

    // Update Post
    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId){
        PostDto updatePost = postServiceImpl.updatePost(postDto, postId);
       return new ResponseEntity<>(updatePost,HttpStatus.OK);
    }
    @DeleteMapping("/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId){
        postServiceImpl.deletePost(postId);
        return new ApiResponse("Post delete Successfully",true);
    }
    // Search Post by using keyword

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<PostDto>> searchPost(@PathVariable String keyword){
        List<PostDto> postDtoNew = postServiceImpl.searchPosts(keyword);
        return new ResponseEntity<List<PostDto>>(postDtoNew,HttpStatus.OK);
    }

    @PostMapping("/post/images/upload/{postId}")
    public ResponseEntity<PostDto> uploadImage(@RequestParam("images") MultipartFile images,
    @PathVariable Integer postId) throws IOException {
        PostDto postDto = postServiceImpl.getPostById(postId);
        String filename = fileService.uploadImage(path, images);
        postDto.setImageName(filename);
        PostDto updatedPost = postServiceImpl.updatePost(postDto, postId);
        return new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);
    }
    // Method to serve file

    @GetMapping(value = "post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {

        InputStream source = fileService.getSource(path, imageName);

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(source,response.getOutputStream());
    }

//    @PostMapping("/post/images/{postId}")
//    public ResponseEntity<PostDto> uploadImage(@RequestParam MultipartFile images,
//                                               @PathVariable Integer postId) throws IOException {
//        PostDto postDto = postServiceImpl.getPostById(postId);
//        String filename = fileService.uploadImage(path, images);
//        postDto.setImageName(filename);
//        PostDto updatedPost = postServiceImpl.updatePost(postDto, postId);
//        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
//    }
}


