package org.BinarAcademy.Challenge_4.repository.film;

import org.BinarAcademy.Challenge_4.model.film.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer> {

    @Query("SELECT f FROM Film f WHERE f.nama= ?1")
    List<Film> findFilmByNama(String nama);

    @Query("SELECT f FROM Film f WHERE f.code= ?1")
    Film findFilmByCode(int code);

    @Query("SELECT f FROM Film f WHERE f.isPlaying= true")
    List<Film> findFilmByIsPlaying();

}
