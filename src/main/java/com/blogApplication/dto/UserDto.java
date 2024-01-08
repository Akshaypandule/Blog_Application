package com.blogApplication.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserDto {
    private Integer id;
    @NotEmpty
    @Size(min=4,message = "name must be min 4 characters")
    private String name;
    @Email(message = "Email address not Valid")
    private String email;
    @NotEmpty
    @Size(min=4,max = 10,message = "Password must be min 4 and max 10 characters")
    private String password;
    @NotEmpty(message = "Please enter the about information")
    private String about;
}
