package org.BinarAcademy.Challenge_4.services;

//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.json.JsonMapper;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.javafaker.Faker;
import org.BinarAcademy.Challenge_4.model.film.Film;
import org.BinarAcademy.Challenge_4.model.schedule.Schedule;
import org.BinarAcademy.Challenge_4.model.users.User;
import org.BinarAcademy.Challenge_4.repository.film.FilmRepository;
import org.BinarAcademy.Challenge_4.repository.schedule.ScheduleRepository;
import org.BinarAcademy.Challenge_4.service.FilmService.FilmService;
import org.BinarAcademy.Challenge_4.service.ScheduleService.ScheduleService;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.data.repository.init.ResourceReader.Type.JSON;

@ExtendWith(MockitoExtension.class)
public class ScheduleServiceTest {

    @Mock
    private FilmRepository filmRepository;
    @Mock
    private ScheduleRepository scheduleRepository;
    private ScheduleService scheduleService;

    Faker faker;
    Film newfilm;
    Film savedFilm;
    Schedule newSchedule;
    List<Film> filmList;

    List<Schedule> scheduleList;
    LocalDate desiredLocalDate,tanggal_tayang_date;
    String desiredToString;
//    ObjectMapper Obj;
    String result;

    @BeforeEach
    void setUp()
    {
//        Obj = JsonMapper.builder().findAndAddModules().build();
//        Obj.registerModule(new JavaTimeModule());
        this.scheduleService = new ScheduleService(this.scheduleRepository, this.filmRepository);
        faker = new Faker(new Locale("en-US"));
        newfilm = new Film();
        newfilm.setCode(1);
        newfilm.setNama(faker.superhero().name());
        newfilm.setPlaying(false);
        filmRepository.save(newfilm);
        filmList = new ArrayList<>();
        scheduleList = new ArrayList<>();
        filmList.add(newfilm);
        when(filmRepository.save(any(Film.class)))
                .thenReturn(newfilm);
        savedFilm = filmRepository.save(newfilm);
        assertThat(savedFilm.getNama()).isNotNull();
    }

    @Test
    @DisplayName("Adding New Schedule Related to Film Test")
    void addNewScheduleTest(){
        assertNotNull(scheduleService);
        desiredLocalDate = LocalDate.of(2022, 11, 11);
        desiredToString = desiredLocalDate.toString();
        tanggal_tayang_date = LocalDate.parse(desiredToString, DateTimeFormatter.ISO_DATE);
        this.newSchedule = new Schedule();
        this.newSchedule.setId(1);
        this.newSchedule.setTanggal_tayang(tanggal_tayang_date);
        this.newSchedule.setJam_mulai(LocalTime.of(1, 1, 1, 1));
        this.newSchedule.setJam_selesai(LocalTime.of(2, 2, 2, 2));
        this.newSchedule.setHarga_tiket(50000.0);
        this.filmList.get(0);
        this.scheduleList.add(newSchedule);

        when(scheduleRepository.save(any(Schedule.class)))
                .thenReturn(this.newSchedule);
        Schedule savedSchedule = scheduleRepository.save(this.newSchedule);

        assertThat(savedSchedule.getId()).isNotNull();

//        result = Obj.writeValueAsString(savedSchedule);
//        System.out.println(result);

    }
}
