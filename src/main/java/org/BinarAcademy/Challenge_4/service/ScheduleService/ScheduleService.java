package org.BinarAcademy.Challenge_4.service.ScheduleService;

import org.BinarAcademy.Challenge_4.model.film.Film;
import org.BinarAcademy.Challenge_4.model.schedule.Schedule;
import org.BinarAcademy.Challenge_4.repository.film.FilmRepository;
import org.BinarAcademy.Challenge_4.repository.schedule.ScheduleRepository;
import org.BinarAcademy.Challenge_4.service.FilmService.FilmService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ScheduleService {
    private static final Logger LOG = LoggerFactory.getLogger(ScheduleService.class);

    @Autowired
    private final ScheduleRepository scheduleRepository;
    private final FilmRepository filmRepository;

    @Autowired
    public ScheduleService(ScheduleRepository repository, FilmRepository filmRepository) {
        this.scheduleRepository = repository;
        this.filmRepository = filmRepository;
    }

//    public Schedule findScheduleByFilmCodeAndTanggaltayang(int code, String tanggal_tayang_string){
//        LocalDate tanggal_tayang_date = LocalDate.parse(tanggal_tayang_string, DateTimeFormatter.ISO_DATE);
//        return scheduleRepository.findScheduleByFilmCodeAndTanggaltayang(code, tanggal_tayang_date);
//    }

//    public void addNewSchedule(String tanggal_tayang_string, String jam_mulai, String jam_selesai, Double harga_tiket, Integer film_code){
//        LocalDate tanggal_tayang_date = LocalDate.parse(tanggal_tayang_string, DateTimeFormatter.ISO_DATE);
//        LocalTime jam_mulai_time = LocalTime.parse(jam_mulai);
//        LocalTime jam_selesai_time = LocalTime.parse(jam_selesai);
//        Film existFilm = filmRepository.findFilmByCode(film_code);
//
//        Schedule schedule = new Schedule(
//                tanggal_tayang_date,
//                jam_mulai_time,
//                jam_selesai_time,
//                harga_tiket,
//                existFilm
//        );
//        scheduleRepository.save(schedule);
//    }
    public void addNewSchedule(Schedule schedule){
        Schedule bookedScheduleByFilmcodeAndTanggaltayang = scheduleRepository.findScheduleByFilmCodeAndTanggaltayang(schedule.getFilm().getCode(), schedule.getTanggal_tayang());

        if(filmRepository.findFilmByCode(schedule.getFilm().getCode()) == null){
            throw new IllegalStateException("Requested Film not found");
        }
        if(bookedScheduleByFilmcodeAndTanggaltayang != null){
            throw new IllegalStateException("Film with requested tanggal_tayang is exist ");
        }
        if(bookedScheduleByFilmcodeAndTanggaltayang.getHarga_tiket() <=0 ){
            throw new IllegalStateException("Film price cannot be zero and cannot be negative ");
        }
        int comparedTime = bookedScheduleByFilmcodeAndTanggaltayang.getJam_selesai().compareTo(bookedScheduleByFilmcodeAndTanggaltayang.getJam_mulai());
        if(comparedTime == 0){
            throw new IllegalStateException("Film start_time and ending_time cannot be the same");
        }else if(comparedTime < 0){
            throw new IllegalStateException("Film start_time cannot exceeed it's end time");
        }
        scheduleRepository.save(schedule);
    }

    public List<Schedule> getAllSchedule(){
        return scheduleRepository.findAll().stream().toList();
    }

    public void deleteSchedule(Integer film_code, LocalDate tanggal_tayang){
        Schedule existSchedule = scheduleRepository.findScheduleByFilmCodeAndTanggaltayang(film_code,tanggal_tayang);

        try{
            scheduleRepository.delete(existSchedule);
        }
        catch(Exception e){
            LOG.error(String.valueOf(e));
        }

    }

    @Transactional
    public void updateSchedule(Integer film_code, LocalDate tanggal_tayang){
        Schedule existSchedule = scheduleRepository.findScheduleByFilmCodeAndTanggaltayang(film_code,tanggal_tayang);
        boolean changeBool;

        try{
            if(film_code != null && tanggal_tayang != null){
                existSchedule.setTanggal_tayang(tanggal_tayang);
            }else{
                throw new IllegalStateException("Somethings wrong with input");
            }
        }
        catch(Exception e){
            LOG.error(String.valueOf(e));
        }
    }
}
