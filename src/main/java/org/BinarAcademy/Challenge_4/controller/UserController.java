package org.BinarAcademy.Challenge_4.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get all user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Request Error Message"),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @GetMapping()
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @Operation(summary = "Add new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Request Error Message"),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @PostMapping
    public void addNewUser(@RequestBody User newUser){
        userService.addNewUser(newUser);
    }

    @Operation(summary = "Delete User by nama")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Request Error Message"),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @DeleteMapping(path = "{nama}")
    public void deleteUser(
            @PathVariable("nama") String nama){
        userService.deleteUser(nama);
    }

    @Operation(summary = "Update user by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Request Error Message"),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @PutMapping(path = "{id}")
    public void updateUser(
            @PathVariable("id") Integer id,
            @Param("nama") String nama,
            @Param("password") String password
    ){
        userService.updateUser(id, nama, password);
    }

}
