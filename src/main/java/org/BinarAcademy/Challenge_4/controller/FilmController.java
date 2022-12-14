package org.BinarAcademy.Challenge_4.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.BinarAcademy.Challenge_4.model.film.Film;
import org.BinarAcademy.Challenge_4.service.FilmService.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/film")
public class FilmController {
    @Autowired
    private FilmService filmService;

    @Operation(summary = "Get All Film")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Request Error Message"),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @GetMapping()
    public List<Film> getAllFilm(){
        return filmService.getAllFilm();
    }


    @Operation(summary = "Add a new film")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Request Error Message"),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @PostMapping()
    public void addNewFilm(@RequestBody Film newFilm){
        filmService.addNewFilm(newFilm);
    }

    @Operation(summary = "Delete film by nama")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Request Error Message"),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @DeleteMapping(path = "{nama}")
    public void deleteFilm(@PathVariable("nama") String nama){
        filmService.deleteFilm(nama);
    }

    @Operation(summary = "Update film by code and nama")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Request Error Message"),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @PutMapping(path = "{code}")
    public void updateFilm(
            @PathVariable("code") Integer code,
            @Param("nama") String nama,
            @Param("isPlaying") int isPlaying
    ){
        filmService.updateFilm(code, nama, isPlaying);
    }
//test
}