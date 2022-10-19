package org.BinarAcademy.Challenge_4.controller;

import org.BinarAcademy.Challenge_4.model.schedule.Schedule;
import org.BinarAcademy.Challenge_4.service.ScheduleService.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/schedule")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @GetMapping()
    public List<Schedule> getAllSchedule(){
        return scheduleService.getAllSchedule();
    }

    @PostMapping()
    public void addNewSchedule(@RequestBody Schedule newSchedule){
        scheduleService.addNewSchedule(newSchedule);
    }

    @DeleteMapping(path = "{film_code}")
    public void deleteSchedule(
            @PathVariable("film_code") Integer film_code,
            @Param("tanggal")String tanggal_tayang
    ){
        LocalDate tanggal_tayang_date = LocalDate.parse(tanggal_tayang, DateTimeFormatter.ISO_LOCAL_DATE);
        scheduleService.deleteSchedule(film_code, tanggal_tayang_date);
    }

    @PutMapping(path = "{film_code}")
    public void updateSchedule(
            @PathVariable("film_code") Integer film_code,
            @RequestBody String tanggal_tayang
    ){
        LocalDate tanggal_tayang_date = LocalDate.parse(tanggal_tayang, DateTimeFormatter.ISO_LOCAL_DATE);
        scheduleService.updateSchedule(film_code,tanggal_tayang_date);
    }
}
