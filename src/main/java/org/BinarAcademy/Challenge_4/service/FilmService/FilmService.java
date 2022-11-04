package org.BinarAcademy.Challenge_4.service.FilmService;

import org.BinarAcademy.Challenge_4.model.film.Film;
import org.BinarAcademy.Challenge_4.model.users.User;
import org.BinarAcademy.Challenge_4.repository.film.FilmRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class FilmService{

    private static final Logger LOG = LoggerFactory.getLogger(FilmService.class);

    @Autowired
    private final FilmRepository repository;

    @Autowired
    public FilmService(FilmRepository repository){
        this.repository = repository;
    }

    public void addNewFilm(Film film){
        List<Film> filmOption = repository.findFilmByNama(film.getNama());
        try{
            repository.save(film);
        }
        catch(Exception e){
            LOG.error(String.valueOf(e));
        }
    }

    public List<Film> getAllFilm(){
        return repository.findAll().stream().toList();
    }



    public void deleteFilm(String nama){
        List<Film> existFilm = repository.findFilmByNama(nama);
        try{
            repository.deleteAll(existFilm);
        }
        catch(Exception e){
            LOG.error(String.valueOf(e));
        }

    }

    @Transactional
    public void updateFilm(Integer code, String nama, int isPlaying){
        Film existFilm = repository.findFilmByCode(code);
        boolean changeBool;

        try {
            if (existFilm != null && nama != null && nama.length() > 0) {
                existFilm.setNama(nama);

                if (isPlaying == 1) changeBool = true;
                else changeBool = false;

                existFilm.setPlaying(changeBool);
            }
        }catch(Exception e){
            LOG.error(String.valueOf(e));
        }
    }
}