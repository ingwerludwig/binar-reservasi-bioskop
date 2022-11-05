package org.BinarAcademy.Challenge_4.services;

//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.json.JsonMapper;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.javafaker.Faker;
import org.BinarAcademy.Challenge_4.model.film.Film;
import org.BinarAcademy.Challenge_4.model.seats.Seat;
import org.BinarAcademy.Challenge_4.model.users.User;
import org.BinarAcademy.Challenge_4.repository.film.FilmRepository;
import org.BinarAcademy.Challenge_4.repository.user.UserRepository;
import org.BinarAcademy.Challenge_4.service.FilmService.FilmService;
import org.BinarAcademy.Challenge_4.service.UserService.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FilmServiceTest {
    @Mock
    private FilmRepository repository;
    private FilmService filmService;

    Faker faker;

//    ObjectMapper Obj;

    String result;

    @BeforeEach
    void setUp()
    {
//        Obj = JsonMapper.builder().findAndAddModules().build();
//        Obj.registerModule(new JavaTimeModule());
        this.filmService = new FilmService(this.repository);
        faker = new Faker(new Locale("en-US"));
    }

    @Test
    @DisplayName("Adding New Film Test")
    void addNewFilmTest(){
        assertNotNull(filmService);
        Film newfilm = new Film();
        newfilm.setCode(1);
        newfilm.setNama(faker.superhero().name());
        newfilm.setPlaying(false);
        repository.save(newfilm);

        when(repository.save(any(Film.class)))
                .thenReturn(newfilm);
        Film savedFilm = repository.save(newfilm);

        assertThat(savedFilm.getNama()).isNotNull();
//        result = Obj.writeValueAsString(savedFilm);
//        System.out.println("When creating Film : "+result);
    }

    @Test
    @DisplayName("Get Film Test")
    void getFilmTest() {
        String newFilmString;
        assertNotNull(filmService);
        Film newfilm = new Film();
        newfilm.setCode(1);
        newfilm.setNama(faker.superhero().name());
        newfilm.setPlaying(false);
        repository.save(newfilm);
//        newFilmString = Obj.writeValueAsString(newfilm);
//        System.out.println("When creating Film : "+newFilmString);

        List<Film> filmList = new ArrayList<>();
        filmList.add(newfilm);

        when(repository.findAll())
                .thenReturn(filmList);

        List<Film> fetchedFilm = filmService.getAllFilm();
        assertThat(fetchedFilm.size()).isGreaterThan(0);

//        for(Film f : fetchedFilm) {
//            result = Obj.writeValueAsString(f);
//            System.out.println("When retrieving film : " + result);
//        }
    }

    @Test
    @DisplayName("Delete Film Test")
    void deleteFilmTest()
    {
        assertNotNull(filmService);
        Film newfilm = new Film();
        newfilm.setCode(1);
        newfilm.setNama(faker.superhero().name());
        newfilm.setPlaying(false);
        repository.save(newfilm);

        List<Film> filmList = new ArrayList<>();
        filmList.add(newfilm);

        BDDMockito.given(repository.findFilmByNama(newfilm.getNama()))
                .willReturn(filmList);

        BDDMockito.willDoNothing().given(repository).deleteAll(filmList);

        filmService.deleteFilm(newfilm.getNama());

        Mockito.verify(repository, Mockito.times(1)).findFilmByNama(newfilm.getNama());
        Mockito.verify(repository, Mockito.times(1)).deleteAll(filmList);
    }
}
