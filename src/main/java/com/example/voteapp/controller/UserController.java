package com.example.voteapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import com.example.voteapp.domain.User;
import com.example.voteapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UserController{
    @Autowired
    UserRepository userRepository;
    @GetMapping(value = "/healthcheck", produces = "application/json; charset=utf-8")
    public String getHealthCheck()
    {
        return "{ \"isWorking\" : true }";
    }

    @GetMapping("/sign_in/{username}/{password}")
    public User getEmployee(@PathVariable String username, @PathVariable String password){
        User user = Optional.ofNullable(userRepository.findByUsername(username)).get();
        if(!isPasswordCorrect(user,password)){
            user = new User("","","","");
        }
        return user;
    }

    @PutMapping("/user/{id}")
    public Optional<User> updateEmployee(@RequestBody User newUser, @PathVariable String id){
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUsername(newUser.getUsername());
            user.setPassword(newUser.getPassword());
            user.setRole(newUser.getRole());
            userRepository.save(user);
        }
        return optionalUser;
    }

    @PostMapping("/sign_up")
    public User adduser(@RequestBody User newUser){
        if (isUserExit(newUser)) {
            return new User("","","","");
        }
        String id = String.valueOf(new Random().nextInt());
        User emp = new User(id, newUser.getUsername(), newUser.getPassword(), newUser.getRole());
        userRepository.insert(emp);
        return emp;
    }

    private boolean isUserExit(User user){
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByUsername(user.getUsername()));
        return optionalUser.isPresent();
    }

    private boolean isPasswordCorrect(User user , String password){
        return user.getPassword().equals(password) ;
    }


}