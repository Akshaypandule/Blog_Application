package com.blogApplication.service;

import com.blogApplication.dto.UserDto;
import com.blogApplication.entity.User;

import java.util.List;

public interface UserService {

    public UserDto createUser(UserDto userDto);
    public UserDto updateUser(UserDto userdto,Integer userId);
    public UserDto getUserById(Integer userId);

    public List<UserDto> getAllUser();
    public void deleteUser(Integer id);



}
