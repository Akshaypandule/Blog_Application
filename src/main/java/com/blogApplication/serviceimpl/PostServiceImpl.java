package com.blogApplication.serviceimpl;


import com.blogApplication.dto.CategoryDto;
import com.blogApplication.dto.PostDto;
import com.blogApplication.entity.Category;
import com.blogApplication.entity.Post;
import com.blogApplication.entity.User;
import com.blogApplication.exception.ResourceNotFoundException;
import com.blogApplication.repository.CategoryRepo;
import com.blogApplication.repository.PostRepo;
import com.blogApplication.repository.UserRepo;
import com.blogApplication.response.PageResponse;
import com.blogApplication.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postrepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CategoryRepo categoryRepo;
    @Override
    public PostDto createPost(PostDto postDto, Integer userid, Integer categoryId)
    {

        User user = userRepo.findById(userid)
                .orElseThrow(() -> new ResourceNotFoundException("User", "User id", userid));

        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Category id", categoryId));

        Post post = modelMapper.map(postDto, Post.class);

        post.setImageName("default.png");
        post.setAddDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post post1 = postrepo.save(post);
        return  modelMapper.map(post1, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto,Integer postId)
    {
        Post post = postrepo.findById(postId).
                orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setImageName(postDto.getImageName());

        Post updatedPost = postrepo.save(post);
        PostDto postDto1 = new ModelMapper().map(updatedPost, PostDto.class);
        return postDto1;
    }

    @Override
    public PageResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {

        Sort sort=sortDir.equalsIgnoreCase("ase")?Sort.by(sortDir).ascending():Sort.by(sortDir).descending();

//        Sort sort=null;
//        if(sortDir.equalsIgnoreCase("ase")){
//            sort=Sort.by(sortDir).ascending();
//        }
//        else
//        {
//            sort=Sort.by(sortDir).descending();
//        }
        Pageable p=PageRequest.of(pageNumber,pageSize,sort);

        Page<Post> pagePost = postrepo.findAll(p);

        List<Post> allPosts = pagePost.getContent();

        //List<Post> allposts = postrepo.findAll(p);

        List<PostDto> postDto = allPosts.stream()
                .map((post) -> new ModelMapper().map(post, PostDto.class))
                .collect(Collectors.toList());

        PageResponse pageResponse=new PageResponse();
        pageResponse.setContent(postDto);
        pageResponse.setPageNumber(pagePost.getNumber());
        pageResponse.setPageSize(pagePost.getSize());
        pageResponse.setTotalElement(pagePost.getTotalElements());
        pageResponse.setLastPage(pagePost.isLast());

        return pageResponse;
    }

    @Override
    public PostDto getPostById(Integer postId)
    {
        Post post = postrepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Post id", postId));

        PostDto postDto = new ModelMapper().map(post, PostDto.class);
        return postDto;
    }

    @Override
    public void deletePost(Integer postId)
    {
        Post post = postrepo.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));
        postrepo.delete(post);

    }

    @Override
//    public List<PostDto> getAllPostsByCategory(Integer categoryId)
//    {
//        Category category = categoryRepo.findById(categoryId).
//                orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
//
//        List<Post> posts = postrepo.findByCategory(category);
//        List<PostDto> postdto = posts.stream().map((post) -> modelMapper.map(posts, PostDto.class)).collect(Collectors.toList());
//
//        return postdto;
//    }
    public List<PostDto> getAllPostsByCategory(Integer categoryId)
    {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));

        List<Post> posts = postrepo.findByCategory(category);

        List<PostDto> postDtos = posts.stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());

        return postDtos;
    }


    @Override
//    public List<PostDto> getPostByUser(Integer userId) {
//
//        User user = userRepo.findById(userId)
//                .orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));
//
//        List<Post> post = postrepo.findByUser(user);
//
//        List<PostDto> postDto = post.stream().map(posts -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
//
//        return postDto;
//    }
    public List<PostDto> getPostByUser(Integer userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));

        List<Post> posts = postrepo.findByUser(user);

        List<PostDto> postDtos = posts.stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());

        return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword)
    {
        List<Post> post = postrepo.findByTitleContaining(keyword);

        List<PostDto> postDto = post.stream().map((posts) ->
                modelMapper.map(posts, PostDto.class)).collect(Collectors.toList());
        return postDto;
    }
}
