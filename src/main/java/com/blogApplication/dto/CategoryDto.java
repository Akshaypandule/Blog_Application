package com.blogApplication.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CategoryDto {
    private Integer categoryId;
    @NotBlank
    @Size(min=4,message = "Title required minimum 4 characters")
    private String categoryTitle;
    @NotBlank
    @Size(min=10,message = "Description required minimum 10 characters")
    private String categoryDescription;
}
