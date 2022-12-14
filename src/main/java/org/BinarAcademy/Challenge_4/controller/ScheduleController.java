package org.BinarAcademy.Challenge_4.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get All schedule")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Request Error Message"),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @GetMapping()
    public List<Schedule> getAllSchedule(){
        return scheduleService.getAllSchedule();
    }

    @Operation(summary = "Add a new schedule")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Request Error Message"),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @PostMapping()
    public void addNewSchedule(@RequestBody Schedule newSchedule){
        scheduleService.addNewSchedule(newSchedule);
    }

    @Operation(summary = "Delete Schedule By id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Request Error Message"),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @DeleteMapping(path = "{film_code}")
    public void deleteSchedule(
            @PathVariable("film_code") Integer film_code,
            @Param("tanggal")String tanggal_tayang
    ){
        LocalDate tanggal_tayang_date = LocalDate.parse(tanggal_tayang, DateTimeFormatter.ISO_LOCAL_DATE);
        scheduleService.deleteSchedule(film_code, tanggal_tayang_date);
    }

    @Operation(summary = "Update film by code")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Request Error Message"),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @PutMapping(path = "{film_code}")
    public void updateSchedule(
            @PathVariable("film_code") Integer film_code,
            @RequestBody String tanggal_tayang
    ){
        LocalDate tanggal_tayang_date = LocalDate.parse(tanggal_tayang, DateTimeFormatter.ISO_LOCAL_DATE);
        scheduleService.updateSchedule(film_code,tanggal_tayang_date);
    }
}
