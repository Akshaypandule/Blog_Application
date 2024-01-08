package com.blogApplication.repository;

import com.blogApplication.entity.Category;
import com.blogApplication.entity.Post;
import com.blogApplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PostRepo extends JpaRepository<Post,Integer> {

    List<Post> findByUser(User user);
//
   List<Post> findByCategory(Category category);

   List<Post> findByTitleContaining(String title);
}
