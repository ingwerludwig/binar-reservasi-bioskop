package org.BinarAcademy.Challenge_4.controller;

import org.BinarAcademy.Challenge_4.model.seats.Seat;
import org.BinarAcademy.Challenge_4.model.users.User;
import org.BinarAcademy.Challenge_4.service.SeatService.SeatService;
import org.BinarAcademy.Challenge_4.service.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/seat")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @GetMapping()
    public List<Seat> getAllSeat(){
        return seatService.getAllSeat();
    }

    @PostMapping
    public void addNewSeat(@RequestBody Seat newSeat){
        seatService.addNewSeat(newSeat);
    }

    @DeleteMapping(path = "{no_kursi}")
    public void deleteSeat(
            @PathVariable("no_kursi") Integer no_kursi){
        seatService.deleteSeat(no_kursi);
    }

    @PutMapping(path = "{no_kursi}")
    public void updateSeat(
            @PathVariable("no_kursi") Integer no_kursi,
            @Param("nama_studio") String nama_studio
    ){
        seatService.updateSeat(no_kursi, nama_studio);
    }
}
