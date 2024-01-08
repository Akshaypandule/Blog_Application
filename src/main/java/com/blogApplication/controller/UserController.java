package com.blogApplication.controller;

import com.blogApplication.dto.UserDto;
import com.blogApplication.serviceimpl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto)
    {
        UserDto user = userServiceImpl.createUser(userDto);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findUser(@PathVariable Integer id){
        UserDto userById = userServiceImpl.getUserById(id);

        return new ResponseEntity<>(userById,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id)
    {
        userServiceImpl.deleteUser(id);
    }
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> findAll(){
        return ResponseEntity.ok(userServiceImpl.getAllUser());
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable Integer id){
        UserDto updateUsers = userServiceImpl.updateUser(userDto, id);
        return ResponseEntity.ok(updateUsers);
    }

}
