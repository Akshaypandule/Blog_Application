package com.blogApplication.serviceimpl;

//import com.blogApplication.config.ModelMapper;
import com.blogApplication.dto.UserDto;
import com.blogApplication.entity.User;
import com.blogApplication.exception.ResourceNotFoundException;
import com.blogApplication.repository.UserRepo;
import com.blogApplication.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserDto createUser(UserDto userDto)
    {
        User user = dtoToUser(userDto);
        User save = userRepo.save(user);
        return userToDto(save);
    }

    @Override
    public UserDto updateUser(UserDto userdto, Integer userId) {

        User user = userRepo.findById(userId).
                orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        user.setName(userdto.getName());
        user.setEmail(userdto.getEmail());
        user.setPassword(userdto.getPassword());
        user.setAbout(userdto.getAbout());

        User updateduser = userRepo.save(user);
        UserDto userDto = userToDto(updateduser);

        return userDto;
    }
    @Override
    public UserDto getUserById(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(()
                -> new ResourceNotFoundException("User", "id", userId));
        return userToDto(user);
    }
    @Override
    public List<UserDto> getAllUser()
    {
        List<User> all = userRepo.findAll();
        List<UserDto> collects = all.stream().map(user -> userToDto(user)).collect(Collectors.toList());

        return  collects;
    }

    @Override
    public void deleteUser(Integer id) {
        User user = userRepo.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        userRepo.delete(user);

    }

    public User dtoToUser(UserDto userDto){
        User user = modelMapper.map(userDto,User.class);
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());
        return user;
    }

    public UserDto userToDto(User user){
        UserDto userdto = modelMapper.map(user, UserDto.class);

        // Manual DTO transfer
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());
        return userdto;

    }
}
