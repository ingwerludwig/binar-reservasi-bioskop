package org.BinarAcademy.Challenge_4.controller;

import org.BinarAcademy.Challenge_4.model.users.User;
import org.BinarAcademy.Challenge_4.service.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping()
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @PostMapping
    public void addNewUser(@RequestBody User newUser){
        userService.addNewUser(newUser);
    }

    @DeleteMapping(path = "{nama}")
    public void deleteUser(
            @PathVariable("nama") String nama){
        userService.deleteUser(nama);
    }

    @PutMapping(path = "{id}")
    public void updateUser(
            @PathVariable("id") Integer id,
            @Param("nama") String nama,
            @Param("password") String password
    ){
        userService.updateUser(id, nama, password);
    }

}
