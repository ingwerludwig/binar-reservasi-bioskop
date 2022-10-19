package org.BinarAcademy.Challenge_4.service.FilmService;

import org.BinarAcademy.Challenge_4.model.film.Film;
import org.BinarAcademy.Challenge_4.repository.film.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FilmService{
    @Autowired
    private final FilmRepository repository;

    @Autowired
    public FilmService(FilmRepository repository){
        this.repository = repository;
    }

    public void addNewFilm(Film film){
        List<Film> filmOption = repository.findFilmByNama(film.getNama());
        if(filmOption.size() > 0){
            throw new IllegalStateException("Film nama has been taken ");
        }
        repository.save(film);
    }

    public List<Film> getAllFilm(){

        return repository.findAll().stream().toList();
    }

    public void deleteFilm(String nama){
        List<Film> existFilm = repository.findFilmByNama(nama);
        repository.deleteAll(existFilm);
    }

    @Transactional
    public void updateFilm(Integer code, String nama, int isPlaying){
        Film existFilm = repository.findFilmByCode(code);
        boolean changeBool;

        if(existFilm != null && nama != null && nama.length()>0){
            existFilm.setNama(nama);
            if  (isPlaying==1)changeBool=true;
            else  changeBool=false;
            existFilm.setPlaying(changeBool);
        }
    }
}