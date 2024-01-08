package com.blogApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private String title;
    private String description;
    private String imageName;
    private Date addDate;
    private UserDto user;
    private CategoryDto category;
    private Set<CommentDto> comments=new HashSet<>();
}
