package com.example.Library;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@FeignClient(name="users-service")
@LoadBalancerClient(name="users-service")
public interface UsersProxy {

    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public List<User> getAllUsers();

    @RequestMapping(value = "/users/{username}",method = RequestMethod.GET)
    public Optional<User> getUserByUsername(@PathVariable String username);

    @RequestMapping(value = "/users",method = RequestMethod.POST)
    public void postUsers(@RequestBody User user);

    @RequestMapping(value = "/users/{username}",method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable String username);

    @RequestMapping(value = "/users/{username}",method = RequestMethod.PUT)
    public void updateUser(@PathVariable String username,@RequestBody User newUser);



}
