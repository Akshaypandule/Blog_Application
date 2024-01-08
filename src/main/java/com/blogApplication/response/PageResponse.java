package com.blogApplication.response;

import com.blogApplication.dto.PostDto;
import com.blogApplication.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PageResponse {

    private List<PostDto> content;
    private Integer pageNumber;
    private Integer pageSize;
    private long totalElement;
    private boolean isLastPage;
}
