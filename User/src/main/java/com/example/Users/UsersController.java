package com.example.Users;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class UsersController {

    @Autowired
    UsersRepository usersRepository;

    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public List<User> getAllUsers()
    {
        return usersRepository.findAll();
    }

    @RequestMapping(value = "/users/{username}",method = RequestMethod.GET)
    public Optional<User> getUserByUsername(@PathVariable String username)
    {
        return usersRepository.findById(username);
    }

    @RequestMapping(value = "/users",method = RequestMethod.POST)
    public void postUsers(@RequestBody User user)
    {
        usersRepository.save(user);
    }

    @RequestMapping(value = "/users/{username}",method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable String username)
    {
        usersRepository.deleteById(username);
    }

    @RequestMapping(value = "/users/{username}",method = RequestMethod.PUT)
    public void updateUser(@PathVariable String username,@RequestBody User newUser)
    {
        Optional<User> oldUserOptional=usersRepository.findById(username);
    //TODO NOT FOUND EXCEPTION
        User oldUser=oldUserOptional.get();
        if(oldUser.getEmail()!=newUser.getEmail())
        {
            oldUser.setEmail(newUser.getEmail());
        }
        if(oldUser.getName()!=newUser.getName())
        {
            oldUser.setName(newUser.getName());
        }
        usersRepository.save(oldUser);


    }





}
