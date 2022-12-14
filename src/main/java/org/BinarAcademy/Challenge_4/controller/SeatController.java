package org.BinarAcademy.Challenge_4.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Get All seat")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Request Error Message"),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @GetMapping()
    public List<Seat> getAllSeat(){
        return seatService.getAllSeat();
    }

    @Operation(summary = "Add new seat")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Request Error Message"),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @PostMapping
    public void addNewSeat(@RequestBody Seat newSeat){
        seatService.addNewSeat(newSeat);
    }

    @Operation(summary = "Delete seat by no")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Request Error Message"),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @DeleteMapping(path = "{no_kursi}")
    public void deleteSeat(
            @PathVariable("no_kursi") Integer no_kursi){
        seatService.deleteSeat(no_kursi);
    }

    @Operation(summary = "Update Seat by no and studio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "success", content = {
                    @Content(mediaType = "application/json")
            }),
            @ApiResponse(responseCode = "400", description = "Request Error Message"),
            @ApiResponse(responseCode = "500", description = "Server Error Message")
    })
    @PutMapping(path = "{no_kursi}")
    public void updateSeat(
            @PathVariable("no_kursi") Integer no_kursi,
            @Param("nama_studio") String nama_studio
    ){
        seatService.updateSeat(no_kursi, nama_studio);
    }
}
