package org.BinarAcademy.Challenge_4.service.SeatService;

import org.BinarAcademy.Challenge_4.model.seats.Seat;
import org.BinarAcademy.Challenge_4.model.users.User;
import org.BinarAcademy.Challenge_4.repository.film.FilmRepository;
import org.BinarAcademy.Challenge_4.repository.schedule.ScheduleRepository;
import org.BinarAcademy.Challenge_4.repository.seat.SeatRepository;
import org.BinarAcademy.Challenge_4.repository.user.UserRepository;
import org.BinarAcademy.Challenge_4.service.FilmService.FilmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SeatService {

    private static final Logger LOG = LoggerFactory.getLogger(SeatService.class);
    @Autowired
    private final SeatRepository repository;
    @Autowired
    public SeatService(SeatRepository seatRepository){
        this.repository = seatRepository;
    }

    public void addNewSeat(Seat newSeat) {
        Seat existSeat = repository.findSeatById(newSeat.getNo_kursi());
        if(existSeat != null) {
            throw new IllegalStateException("Seat id has been taken ");
        }

        try{
            repository.save(existSeat);
        }catch(Exception e){
            LOG.error(String.valueOf(e));
        }
    }

    @Transactional
    public void updateSeat(Integer no_kursi, String nama_studio) {
        Seat existSeat = repository.findSeatById(no_kursi);

        if(existSeat != null){
            existSeat.setNama_studio(nama_studio);
        }
    }
    public void deleteSeat(Integer no_kursi) {
        Seat existSeat = repository.findSeatById(no_kursi);

        try{
            repository.delete(existSeat);
        }catch(Exception e){
            LOG.error(String.valueOf(e));
        }
    }

    public Seat getAllBookedSeat(Integer no_kursi) {
        return repository.findSeatById(no_kursi);
    }
    public List<Seat> getAllBookedSeat() {
        return repository.findSeatByBooked().stream().toList();
    }
    public List<Seat> getAllStudioSeat(String nama_studio) {
        return repository.findSeatByNamaStudio(nama_studio).stream().toList();
    }
    public List<Seat> getAllSeat() {
        return repository.findAll().stream().toList();
    }

}
