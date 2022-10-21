package org.BinarAcademy.Challenge_4.controller;

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

    @GetMapping()
    public List<Film> getAllFilm(){
        return filmService.getAllFilm();
    }

    @PostMapping()
    public void addNewFilm(@RequestBody Film newFilm){
        filmService.addNewFilm(newFilm);
    }

    @DeleteMapping(path = "{nama}")
    public void deleteFilm(@PathVariable("nama") String nama){
        filmService.deleteFilm(nama);
    }

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